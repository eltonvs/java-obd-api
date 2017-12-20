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
package br.ufrn.imd.obd.commands.control;

import java.util.Locale;

import br.ufrn.imd.obd.commands.ObdCommand;
import br.ufrn.imd.obd.commands.SystemOfUnits;
import br.ufrn.imd.obd.enums.AvailableCommand;

/**
 * Distance traveled since Malfunction Indicator Light (MIL) was on.
 */
public class DistanceMILOnCommand extends ObdCommand implements SystemOfUnits {

    private int km = 0;

    /**
     * Default constructor.
     */
    public DistanceMILOnCommand() {
        super(AvailableCommand.DISTANCE_TRAVELED_MIL_ON);
    }

    /**
     * Copy constructor.
     *
     * @param other a {@link DistanceMILOnCommand} object.
     */
    public DistanceMILOnCommand(DistanceMILOnCommand other) {
        super(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performCalculations() {
        // ignore first two bytes [01 31] of the response
        km = buffer.get(2) * 256 + buffer.get(3);
    }

    /**
     * <p>getFormattedResult.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFormattedResult() {
        return imperialUnits
                ? String.format(Locale.getDefault(), "%.2f%s", getImperialUnit(), getResultUnit())
                : String.format(Locale.getDefault(), "%d%s", km, getResultUnit());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCalculatedResult() {
        return imperialUnits ? String.valueOf(getImperialUnit()) : String.valueOf(km);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResultUnit() {
        return imperialUnits ? "m" : "km";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getImperialUnit() {
        return km * 0.621371192F;
    }

    /**
     * <p>Getter for the field <code>km</code>.</p>
     *
     * @return a int.
     */
    public int getKm() {
        return km;
    }

}
