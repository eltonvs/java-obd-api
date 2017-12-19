/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
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
 * <p>ModuleVoltageCommand class.</p>
 */
public class ModuleVoltageCommand extends ObdCommand {

    // Equivalent ratio (V)
    private double voltage = 0f;

    /**
     * Default constructor.
     */
    public ModuleVoltageCommand() {
        super(AvailableCommand.CONTROL_MODULE_VOLTAGE);
    }

    /**
     * Copy constructor.
     *
     * @param other a {@link ModuleVoltageCommand} object.
     */
    public ModuleVoltageCommand(ModuleVoltageCommand other) {
        super(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performCalculations() {
        // ignore first two bytes [hh hh] of the response
        int a = buffer.get(2);
        int b = buffer.get(3);
        voltage = (a * 256 + b) / 1000f;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFormattedResult() {
        return String.format(Locale.getDefault(), "%.1f%s", voltage, getResultUnit());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResultUnit() {
        return "V";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCalculatedResult() {
        return String.valueOf(voltage);
    }

    /**
     * <p>Getter for the field <code>voltage</code>.</p>
     *
     * @return a double.
     */
    public double getVoltage() {
        return voltage;
    }

}
