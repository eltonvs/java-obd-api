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
package br.ufrn.imd.obdandroidapi.commands.pressure;

import br.ufrn.imd.obdandroidapi.enums.AvailableCommand;

/**
 * Barometric pressure.
 */
public class BarometricPressureCommand extends PressureCommand {

    /**
     * <p>Constructor for BarometricPressureCommand.</p>
     */
    public BarometricPressureCommand() {
        super(AvailableCommand.BAROMETRIC_PRESSURE);
    }

    /**
     * <p>Constructor for BarometricPressureCommand.</p>
     *
     * @param other a {@link PressureCommand} object.
     */
    public BarometricPressureCommand(PressureCommand other) {
        super(other);
    }

}
