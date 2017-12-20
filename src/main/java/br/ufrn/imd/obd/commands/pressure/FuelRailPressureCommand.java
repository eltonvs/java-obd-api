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
package br.ufrn.imd.obd.commands.pressure;

import br.ufrn.imd.obd.enums.AvailableCommand;

/**
 * <p>FuelRailPressureCommand class.</p>
 */
public class FuelRailPressureCommand extends PressureCommand {

    /**
     * <p>Constructor for FuelRailPressureCommand.</p>
     */
    public FuelRailPressureCommand() {
        super(AvailableCommand.FUEL_RAIL_PRESSURE);
    }

    /**
     * <p>Constructor for FuelRailPressureCommand.</p>
     *
     * @param other a {@link FuelRailPressureCommand} object.
     */
    public FuelRailPressureCommand(FuelRailPressureCommand other) {
        super(other);
    }

    /**
     * {@inheritDoc}
     * <p>
     * TODO describe of why we multiply by 3
     */
    @Override
    protected final int preparePressureValue() {
        int a = buffer.get(2);
        int b = buffer.get(3);
        return ((a * 256) + b) * 10;
    }

}
