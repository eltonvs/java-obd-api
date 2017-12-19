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
package br.ufrn.imd.obdandroidapi.commands.control;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

import br.ufrn.imd.obdandroidapi.commands.ObdCommand;
import br.ufrn.imd.obdandroidapi.enums.AvailableCommand;

/**
 * It is not needed no know how many DTC are stored. Because when no DTC are
 * stored response will be NO DATA And where are more messages it will be stored
 * in frames that have 7 bytes. In one frame are stored 3 DTC. If we find out
 * DTC P0000 that mean no message are we can end.
 */
public abstract class GenericTroubleCodeCommand extends ObdCommand {

    /**
     * Constant <code>dtcLetters={'P', 'C', 'B', 'U'}</code>
     */
    protected static final char[] dtcLetters = { 'P', 'C', 'B', 'U' };
    /**
     * Constant <code>hexArray="0123456789ABCDEF".toCharArray()</code>
     */
    protected static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    private static final Pattern CARRIAGE_PATTERN = Pattern.compile("[\r\n]");
    private static final Pattern CARRIAGE_COLON_PATTERN = Pattern.compile("[\r\n].:");

    protected final Set<String> troubleCodes = new TreeSet<>();

    /**
     * <p>
     * Constructor for TroubleCodesCommand.
     * </p>
     */
    public GenericTroubleCodeCommand(AvailableCommand command) {
        super(command);
    }

    /**
     * Copy Constructor.
     *
     * @param other a {@link GenericTroubleCodeCommand} object.
     */
    public GenericTroubleCodeCommand(GenericTroubleCodeCommand other) {
        super(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performCalculations() {
        final String result = getResult();
        String workingData;
        int startIndex = 0;  // Header size.

        String canOneFrame = removeCarriage(result);
        int canOneFrameLength = canOneFrame.length();

        if (canOneFrameLength <= 16 && canOneFrameLength % 4 == 0) {  // CAN(ISO-15765) protocol one frame.
            workingData = canOneFrame;  // 43yy[codes]
            startIndex = 4;  // Header is 43yy, yy showing the number of data items.
        } else if (result.contains(":")) {  // CAN(ISO-15765) protocol two and more frames.
            workingData = removeCarriageColon(result);  // xxx43yy[codes]
            startIndex = 7;  // Header is xxx43yy, xxx is bytes of information to follow, yy showing the
            // number of data items.
        } else {  // ISO9141-2, KWP2000 Fast and KWP2000 5Kbps (ISO15031) protocols.
            workingData = removeCarriageNumber(result);
        }

        for (int begin = startIndex; begin < workingData.length(); begin += 4) {
            StringBuilder dtc = new StringBuilder();
            byte b1 = hexStringToByteArray(workingData.charAt(begin));
            int ch1 = ((b1 & 0xC0) >> 6);
            int ch2 = ((b1 & 0x30) >> 4);
            dtc.append(dtcLetters[ch1]).append(hexArray[ch2]).append(workingData.substring(begin + 1, begin + 4));

            String dtcStr = dtc.toString();
            if ("P0000".equals(dtcStr)) {
                return;
            }
            troubleCodes.add(dtcStr);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillBuffer() {
        // Empty method
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void readRawData(InputStream in) throws IOException {
        byte b;
        StringBuilder res = new StringBuilder();

        // read until '>' arrives OR end of stream reached (and skip ' ')
        char c;
        while (true) {
            b = (byte) in.read();
            c = (char) b;

            // -1 if the end of the stream is reached
            // or read until '>' arrives
            if (b == -1 || c == '>') {
                break;
            }

            // skip ' '
            if (c != ' ') {
                res.append(c);
            }
        }

        rawData = res.toString().trim();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFormattedResult() {
        return getCalculatedResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCalculatedResult() {
        StringBuilder sb = new StringBuilder();
        for (String code : troubleCodes) {
            sb.append(code).append(" ");
        }
        return "[ " + sb.toString() + "]";
    }

    private String removeCarriage(String str) {
        return CARRIAGE_PATTERN.matcher(str).replaceAll("");
    }

    private String removeCarriageColon(String str) {
        return CARRIAGE_COLON_PATTERN.matcher(str).replaceAll("");
    }

    protected abstract String removeCarriageNumber(String str);

    private byte hexStringToByteArray(char s) {
        return (byte) (Character.digit(s, 16) << 4);
    }

}
