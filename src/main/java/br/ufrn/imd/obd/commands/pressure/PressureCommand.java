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
package br.ufrn.imd.obd.commands.pressure;


import java.util.Locale;

import br.ufrn.imd.obd.commands.ObdCommand;
import br.ufrn.imd.obd.commands.SystemOfUnits;
import br.ufrn.imd.obd.enums.AvailableCommand;

/**
 * Abstract pressure command.
 */
public abstract class PressureCommand extends ObdCommand implements SystemOfUnits {

    protected int tempValue = 0;
    protected int pressure = 0;

    /**
     * Default constructor
     *
     * @param cmd a {@link AvailableCommand} object.
     */
    public PressureCommand(AvailableCommand cmd) {
        super(cmd);
    }

    /**
     * Copy constructor.
     *
     * @param other a {@link PressureCommand} object.
     */
    public PressureCommand(PressureCommand other) {
        super(other);
    }

    /**
     * Some PressureCommand subclasses will need to implement this method in
     * order to determine the final kPa value.
     * <p>
     * *NEED* to read tempValue
     *
     * @return a int.
     */
    protected int preparePressureValue() {
        return buffer.get(2);
    }

    /**
     * <p>performCalculations.</p>
     */
    protected void performCalculations() {
        // ignore first two bytes [hh hh] of the response
        pressure = preparePressureValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFormattedResult() {
        return imperialUnits
                ? String.format(Locale.getDefault(), "%.1f%s", getImperialUnit(), getResultUnit())
                : String.format(Locale.getDefault(), "%d%s", pressure, getResultUnit());
    }

    /**
     * <p>getMetricUnit.</p>
     *
     * @return the pressure in kPa
     */
    public int getMetricUnit() {
        return pressure;
    }

    /**
     * <p>getImperialUnit.</p>
     *
     * @return the pressure in psi
     */
    public float getImperialUnit() {
        return pressure * 0.145037738F;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCalculatedResult() {
        return imperialUnits ? String.valueOf(getImperialUnit()) : String.valueOf(pressure);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResultUnit() {
        return imperialUnits ? "psi" : "kPa";
    }

}
