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
package br.ufrn.imd.obdandroidapi.commands.temperature;


import java.util.Locale;

import br.ufrn.imd.obdandroidapi.commands.ObdCommand;
import br.ufrn.imd.obdandroidapi.commands.SystemOfUnits;

/**
 * Abstract temperature command.
 */
public abstract class TemperatureCommand extends ObdCommand implements SystemOfUnits {

    private float temperature = 0.0f;

    /**
     * Default constructor.
     *
     * @param cmd a {@link java.lang.String} object.
     */
    public TemperatureCommand(String cmd) {
        super(cmd);
    }

    /**
     * Copy constructor.
     *
     * @param other a {@link TemperatureCommand} object.
     */
    public TemperatureCommand(TemperatureCommand other) {
        super(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performCalculations() {
        // ignore first two bytes [hh hh] of the response
        temperature = buffer.get(2) - 40f;
    }


    /**
     * {@inheritDoc}
     * <p>
     * Get values from 'buff', since we can't rely on char/string for
     * calculations.
     */
    @Override
    public String getFormattedResult() {
        return imperialUnits
                ? String.format(Locale.getDefault(), "%.1f%s", getImperialUnit(), getResultUnit())
                : String.format(Locale.getDefault(), "%.0f%s", temperature, getResultUnit());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCalculatedResult() {
        return imperialUnits ? String.valueOf(getImperialUnit()) : String.valueOf(temperature);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResultUnit() {
        return imperialUnits ? "F" : "C";
    }

    /**
     * <p>Getter for the field <code>temperature</code>.</p>
     *
     * @return the temperature in Celsius.
     */
    public float getTemperature() {
        return temperature;
    }

    /**
     * <p>getImperialUnit.</p>
     *
     * @return the temperature in Fahrenheit.
     */
    public float getImperialUnit() {
        return temperature * 1.8f + 32;
    }

    /**
     * <p>getKelvin.</p>
     *
     * @return the temperature in Kelvin.
     */
    public float getKelvin() {
        return temperature + 273.15f;
    }

    /**
     * <p>getName.</p>
     *
     * @return the OBD command name.
     */
    public abstract String getName();

}
