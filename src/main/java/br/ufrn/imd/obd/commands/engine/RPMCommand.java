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
package br.ufrn.imd.obd.commands.engine;


import java.util.Locale;

import br.ufrn.imd.obd.commands.ObdCommand;
import br.ufrn.imd.obd.enums.AvailableCommand;

/**
 * Displays the current engine revolutions per minute (RPM).
 */
public class RPMCommand extends ObdCommand {

    private int rpm = -1;

    /**
     * Default constructor.
     */
    public RPMCommand() {
        super(AvailableCommand.ENGINE_RPM);
    }

    /**
     * Copy constructor.
     *
     * @param other a {@link RPMCommand} object.
     */
    public RPMCommand(RPMCommand other) {
        super(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performCalculations() {
        // ignore first two bytes [41 0C] of the response((A*256)+B)/4
        rpm = (buffer.get(2) * 256 + buffer.get(3)) / 4;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFormattedResult() {
        return String.format(Locale.getDefault(), "%d%s", rpm, getResultUnit());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCalculatedResult() {
        return String.valueOf(rpm);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResultUnit() {
        return "RPM";
    }

    /**
     * <p>getRPM.</p>
     *
     * @return a int.
     */
    public int getRPM() {
        return rpm;
    }

}
