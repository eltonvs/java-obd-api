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
package br.ufrn.imd.obd.commands.fuel;

import java.util.Locale;

import br.ufrn.imd.obd.commands.ObdCommand;
import br.ufrn.imd.obd.enums.AvailableCommand;

/**
 * Fuel Consumption Rate per hour.
 */
public class ConsumptionRateCommand extends ObdCommand {

    private float fuelRate = -1f;

    /**
     * <p>Constructor for ConsumptionRateCommand.</p>
     */
    public ConsumptionRateCommand() {
        super(AvailableCommand.FUEL_CONSUMPTION_RATE);
    }

    /**
     * <p>Constructor for ConsumptionRateCommand.</p>
     *
     * @param other a {@link ConsumptionRateCommand} object.
     */
    public ConsumptionRateCommand(ConsumptionRateCommand other) {
        super(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performCalculations() {
        // ignore first two bytes [hh hh] of the response
        fuelRate = (buffer.get(2) * 256 + buffer.get(3)) * 0.05f;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFormattedResult() {
        return String.format(Locale.getDefault(), "%.1f%s", fuelRate, getResultUnit());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCalculatedResult() {
        return String.valueOf(fuelRate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResultUnit() {
        return "L/h";
    }

    /**
     * <p>getLitersPerHour.</p>
     *
     * @return a float.
     */
    public float getLitersPerHour() {
        return fuelRate;
    }

}
