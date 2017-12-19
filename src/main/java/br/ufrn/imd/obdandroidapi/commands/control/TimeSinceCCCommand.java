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


import java.util.Locale;

import br.ufrn.imd.obdandroidapi.commands.ObdCommand;
import br.ufrn.imd.obdandroidapi.enums.AvailableCommand;

/**
 * Time Traveled since codes cleared-up.
 */
public class TimeSinceCCCommand extends ObdCommand {

    private int value = 0;

    /**
     * Default constructor.
     */
    public TimeSinceCCCommand() {
        super(AvailableCommand.TIME_SINCE_TC_CLEARED);
    }

    /**
     * Copy constructor.
     *
     * @param other a {@link TimeSinceCCCommand} object.
     */
    public TimeSinceCCCommand(TimeSinceCCCommand other) {
        super(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performCalculations() {
        // ignore first two bytes [01 4D] of the response
        value = buffer.get(2) * 256 + buffer.get(3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFormattedResult() {
        // determine time
        final String hh = String.format(Locale.getDefault(), "%02d", value / 60);
        final String mm = String.format(Locale.getDefault(), "%02d", value % 60);
        return String.format("%s:%s:00", hh, mm);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCalculatedResult() {
        return String.valueOf(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResultUnit() {
        return "min";
    }

}
