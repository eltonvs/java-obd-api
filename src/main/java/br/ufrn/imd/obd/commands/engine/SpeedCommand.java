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
import br.ufrn.imd.obd.commands.SystemOfUnits;
import br.ufrn.imd.obd.enums.AvailableCommand;

/**
 * Current speed.
 */
public class SpeedCommand extends ObdCommand implements SystemOfUnits {

    private int metricSpeed = 0;

    /**
     * Default constructor.
     */
    public SpeedCommand() {
        super(AvailableCommand.SPEED);
    }

    /**
     * Copy constructor.
     *
     * @param other a {@link SpeedCommand} object.
     */
    public SpeedCommand(SpeedCommand other) {
        super(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performCalculations() {
        // Ignore first two bytes [hh hh] of the response.
        metricSpeed = buffer.get(2);
    }

    /**
     * <p>Getter for the field <code>metricSpeed</code>.</p>
     *
     * @return the speed in metric units.
     */
    public int getMetricSpeed() {
        return metricSpeed;
    }

    /**
     * <p>getImperialSpeed.</p>
     *
     * @return the speed in imperial units.
     */
    public float getImperialSpeed() {
        return getImperialUnit();
    }

    /**
     * Convert from km/h to mph
     *
     * @return a float.
     */
    public float getImperialUnit() {
        return metricSpeed * 0.621371192F;
    }

    /**
     * <p>getFormattedResult.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFormattedResult() {
        return imperialUnits
                ? String.format(Locale.getDefault(), "%.2f%s", getImperialUnit(), getResultUnit())
                : String.format(Locale.getDefault(), "%d%s", getMetricSpeed(), getResultUnit());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCalculatedResult() {
        return imperialUnits ? String.valueOf(getImperialUnit()) : String.valueOf(getMetricSpeed());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResultUnit() {
        return imperialUnits ? "mph" : "km/h";
    }

}
