/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package br.ufrn.imd.obd.commands;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufrn.imd.obd.enums.AvailableCommand;
import br.ufrn.imd.obd.exceptions.BusInitException;
import br.ufrn.imd.obd.exceptions.MisunderstoodCommandException;
import br.ufrn.imd.obd.exceptions.NoDataException;
import br.ufrn.imd.obd.exceptions.NonNumericResponseException;
import br.ufrn.imd.obd.exceptions.ResponseException;
import br.ufrn.imd.obd.exceptions.RunException;
import br.ufrn.imd.obd.exceptions.StoppedException;
import br.ufrn.imd.obd.exceptions.UnableToConnectException;
import br.ufrn.imd.obd.exceptions.UnknownErrorException;
import br.ufrn.imd.obd.exceptions.UnsupportedCommandException;

import static br.ufrn.imd.obd.utils.RegexUtils.BUS_INIT_PATTERN;
import static br.ufrn.imd.obd.utils.RegexUtils.COLON_PATTERN;
import static br.ufrn.imd.obd.utils.RegexUtils.DIGITS_LETTERS_PATTERN;
import static br.ufrn.imd.obd.utils.RegexUtils.SEARCHING_PATTERN;
import static br.ufrn.imd.obd.utils.RegexUtils.WHITESPACE_PATTERN;
import static br.ufrn.imd.obd.utils.RegexUtils.removeAll;

/**
 * Base OBD command.
 */
public abstract class ObdCommand implements IObdCommand {

    /**
     * Error classes to be tested in order
     */
    private static final List<Class<? extends ResponseException>> ERROR_CLASSES = new ArrayList<>(Arrays.asList(
            UnableToConnectException.class,
            BusInitException.class,
            MisunderstoodCommandException.class,
            NoDataException.class,
            StoppedException.class,
            UnknownErrorException.class,
            UnsupportedCommandException.class
    ));
    protected List<Integer> buffer = null;
    protected final AvailableCommand cmd;
    protected boolean imperialUnits = false;
    protected String rawData = null;
    protected Long responseDelayInMs = null;
    private long start;
    private long end;

    /**
     * Prevent empty instantiation
     */
    private ObdCommand() {
        cmd = null;
    }

    /**
     * Copy constructor.
     *
     * @param other the ObdCommand to be copied.
     */
    public ObdCommand(ObdCommand other) {
        this(other.cmd);
    }

    /**
     * Default constructor to use
     *
     * @param command the command to send
     */
    public ObdCommand(AvailableCommand command) {
        this.cmd = command;
        this.buffer = new ArrayList<>();
    }

    /**
     * Sends the OBD-II request and deals with the response.
     * <p>
     * This method CAN be overridden in fake commands.
     *
     * @param in  a {@link java.io.InputStream} object.
     * @param out a {@link java.io.OutputStream} object.
     * @throws java.io.IOException            if any.
     * @throws java.lang.InterruptedException if any.
     */
    public void run(InputStream in, OutputStream out) throws IOException, InterruptedException {
        // Only one command can write and read a data in one time.
        synchronized (ObdCommand.class) {
            start = System.currentTimeMillis();
            sendCommand(out);
            readResult(in);
            end = System.currentTimeMillis();
        }
    }

    /**
     * Sends the OBD-II request.
     * <p>
     * This method may be overridden in subclasses, such as ObMultiCommand or
     * TroubleCodesCommand.
     *
     * @param out The output stream.
     * @throws java.io.IOException            if any.
     * @throws java.lang.InterruptedException if any.
     */
    protected void sendCommand(OutputStream out) throws IOException, InterruptedException {
        // write to OutputStream (i.e.: a BluetoothSocket) with an added Carriage return
        out.write((cmd.getCommand() + "\r").getBytes());
        out.flush();
        if (responseDelayInMs != null && responseDelayInMs > 0) {
            Thread.sleep(responseDelayInMs);
        }
    }

    /**
     * Reads the OBD-II response.
     * <p>
     * This method may be overridden in subclasses, such as ObdMultiCommand.
     *
     * @param in a {@link java.io.InputStream} object.
     * @throws java.io.IOException if any.
     */
    protected void readResult(InputStream in) throws IOException {
        readRawData(in);
        checkForErrors();
        fillBuffer();
        performCalculations();
    }

    /**
     * <p>
     * readRawData.</p>
     *
     * @param in a {@link java.io.InputStream} object.
     * @throws java.io.IOException if any.
     */
    protected void readRawData(InputStream in) throws IOException {
        byte b;
        char c;
        StringBuilder res = new StringBuilder();

        // read until '>' arrives OR end of stream reached (-1)
        while ((b = (byte) in.read()) > -1 && (c = (char) b) != '>') {
            res.append(c);
        }

        rawData = removeAll(SEARCHING_PATTERN, res.toString());
        rawData = removeAll(WHITESPACE_PATTERN, rawData);  // removes all [ \t\n\x0B\f\r]
    }

    void checkForErrors() {
        for (Class<? extends ResponseException> errorClass : ERROR_CLASSES) {
            ResponseException messageError;

            try {
                messageError = errorClass.newInstance();
                messageError.setCommand(this.cmd);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RunException(e);
            }

            if (messageError.isError(rawData)) {
                throw messageError;
            }
        }
    }

    /**
     * <p>fillBuffer.</p>
     */
    protected void fillBuffer() {
        /*
         * Imagine the following response 41 0c 00 0d.
         *
         * ELM sends strings!! So, ELM puts spaces between each "byte". And pay
         * attention to the fact that I've put the word byte in quotes, because 41
         * is actually TWO bytes (two chars) in the socket. So, we must do some more
         * processing..
         */
        rawData = removeAll(WHITESPACE_PATTERN, rawData); // removes all [ \t\n\x0B\f\r]

        /*
         * Data may have echo or informative text like "INIT BUS..." or similar.
         * The response ends with two carriage return characters. So we need to take
         * everything from the last carriage return before those two (trimmed above).
         */
        rawData = removeAll(BUS_INIT_PATTERN, rawData);
        rawData = removeAll(COLON_PATTERN, rawData);

        if (!DIGITS_LETTERS_PATTERN.matcher(rawData).matches()) {
            throw new NonNumericResponseException(rawData);
        }

        // read string each two chars
        buffer.clear();
        int initialChar = 0;
        int finalChar = 2;
        while (finalChar <= rawData.length()) {
            buffer.add(Integer.decode("0x" + rawData.substring(initialChar, finalChar)));
            initialChar = finalChar;
            finalChar += 2;
        }
    }

    /**
     * This method exists so that for each command, there must be a method that is
     * called only once to perform calculations.
     */
    protected abstract void performCalculations();

    /**
     * <p>getResult.</p>
     *
     * @return the raw command response in string representation.
     */
    public String getResult() {
        return rawData;
    }

    /**
     * <p>getFormattedResult.</p>
     *
     * @return a formatted command response in string representation.
     */
    public abstract String getFormattedResult();

    /**
     * The unit of the result, as used in {@link #getFormattedResult()}
     *
     * @return a String representing a unit or "", never null
     */
    public String getResultUnit() {
        return "";  // no unit by default
    }

    /**
     * <p>getName.</p>
     *
     * @return the OBD command name.
     */
    public String getName() {
        return this.cmd.getValue();
    }

    /**
     * <p>getCommandPID.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public final String getCommandPID() {
        return cmd.getCommand().substring(3);
    }

    /**
     * <p>getMap.</p>
     *
     * @return a {@link java.util.Map} object.
     */
    @Override
    public Map<String, String> getMap() {
        Map<String, String> retMap = new HashMap<>();
        if (cmd != null) {
            retMap.put(cmd.name(), getCalculatedResult());
        }
        return retMap;
    }

    /**
     * Resend this command.
     *
     * @param out a {@link java.io.OutputStream} object.
     * @throws java.io.IOException            if any.
     * @throws java.lang.InterruptedException if any.
     */
    protected void resendCommand(OutputStream out) throws IOException, InterruptedException {
        out.write("\r".getBytes());
        out.flush();
        if (responseDelayInMs != null && responseDelayInMs > 0) {
            Thread.sleep(responseDelayInMs);
        }
    }

    /**
     * <p>getCalculatedResult.</p>
     *
     * @return the command response in string representation, without formatting.
     */
    public abstract String getCalculatedResult();

    /**
     * <p>Getter for the field <code>buffer</code>.</p>
     *
     * @return a list of integers
     */
    protected List<Integer> getBuffer() {
        return buffer;
    }

    /**
     * <p>isImperialUnits.</p>
     *
     * @return true if imperial units are used, or false otherwise
     */
    public boolean isImperialUnits() {
        return imperialUnits;
    }

    /**
     * Set to 'true' if you want to use imperial units, false otherwise. By
     * default this value is set to 'false'.
     *
     * @param isImperial a boolean.
     */
    public void setImperialUnits(boolean isImperial) {
        this.imperialUnits = isImperial;
    }

    /**
     * Time the command waits before returning from #sendCommand()
     *
     * @return delay in ms (may be null)
     */
    public Long getResponseTimeDelay() {
        return responseDelayInMs;
    }

    /**
     * Time the command waits before returning from #sendCommand()
     *
     * @param responseDelayInMs a Long (can be null)
     */
    public void setResponseTimeDelay(Long responseDelayInMs) {
        this.responseDelayInMs = responseDelayInMs;
    }

    /**
     * <p>Getter for the field <code>start</code>.</p>
     *
     * @return a long.
     */
    public long getStart() {
        return start;
    }

    /**
     * <p>Setter for the field <code>start</code>.</p>
     *
     * @param start a long.
     */
    public void setStart(long start) {
        this.start = start;
    }

    /**
     * <p>Getter for the field <code>end</code>.</p>
     *
     * @return a long.
     */
    public long getEnd() {
        return end;
    }

    /**
     * <p>Setter for the field <code>end</code>.</p>
     *
     * @param end a long.
     */
    public void setEnd(long end) {
        this.end = end;
    }

    /**
     * <p>Getter the elapsed time.</p>
     *
     * @return a long.
     */
    @Override
    public long getElapsedTime() {
        return end - start;
    }

    /**
     * <p>getCommandMode.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public final String getCommandMode() {
        return cmd.getCommand().length() >= 2 ? cmd.getCommand().substring(0, 2) : cmd.getCommand();
    }

    @Override
    public int hashCode() {
        return cmd.getCommand() != null ? cmd.getCommand().hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ObdCommand that = (ObdCommand) o;

        return cmd == that.cmd || cmd != null && that.cmd != null && cmd.toString().equals(that.cmd.toString());
    }

    @Override
    public String toString() {
        return getName() + " = " + getFormattedResult() + "\n";
    }
}
