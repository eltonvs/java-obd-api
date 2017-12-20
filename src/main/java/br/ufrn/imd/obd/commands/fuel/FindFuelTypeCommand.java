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

import br.ufrn.imd.obd.commands.ObdCommand;
import br.ufrn.imd.obd.enums.AvailableCommand;
import br.ufrn.imd.obd.enums.FuelType;

/**
 * This command is intended to determine the vehicle fuel type.
 */
public class FindFuelTypeCommand extends ObdCommand {

    private int fuelType = 0;

    /**
     * Default constructor.
     */
    public FindFuelTypeCommand() {
        super(AvailableCommand.FUEL_TYPE);
    }

    /**
     * Copy constructor
     *
     * @param other a {@link FindFuelTypeCommand} object.
     */
    public FindFuelTypeCommand(FindFuelTypeCommand other) {
        super(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performCalculations() {
        // ignore first two bytes [hh hh] of the response
        fuelType = buffer.get(2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFormattedResult() {
        try {
            return FuelType.fromValue(fuelType).getDescription();
        } catch (NullPointerException e) {
            return "-";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCalculatedResult() {
        return String.valueOf(fuelType);
    }

}
