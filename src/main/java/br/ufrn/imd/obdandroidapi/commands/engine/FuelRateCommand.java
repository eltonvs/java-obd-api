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
package br.ufrn.imd.obdandroidapi.commands.engine;


import java.util.Locale;

import br.ufrn.imd.obdandroidapi.commands.ObdCommand;
import br.ufrn.imd.obdandroidapi.enums.AvailableCommand;

/**
 * Engine Fuel Rate.
 */
public class FuelRateCommand extends ObdCommand {

    private double fuelRate = -1f;

    /**
     * Default constructor.
     */
    public FuelRateCommand() {
        super(AvailableCommand.ENGINE_FUEL_RATE);
    }

    @Override
    protected void performCalculations() {
        // ignore first two bytes [41 0C] of the response ((A*256)+B)/20
        fuelRate = (buffer.get(2) * 256 + buffer.get(3)) / 20f;
    }

    @Override
    public String getFormattedResult() {
        return String.format(Locale.getDefault(), "%.0f%s", fuelRate, getResultUnit());
    }

    @Override
    public String getCalculatedResult() {
        return String.valueOf(fuelRate);
    }

    @Override
    public String getResultUnit() {
        return "L/h";
    }

    /**
     * Copy constructor.
     *
     * @param other a {@link FuelRateCommand} object.
     */
    public FuelRateCommand(FuelRateCommand other) {
        super(other);
    }

}
