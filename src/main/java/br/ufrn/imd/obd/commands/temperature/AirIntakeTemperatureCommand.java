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
package br.ufrn.imd.obd.commands.temperature;

import br.ufrn.imd.obd.enums.AvailableCommand;

/**
 * Temperature of intake air.
 */
public class AirIntakeTemperatureCommand extends TemperatureCommand {

    /**
     * <p>Constructor for AirIntakeTemperatureCommand.</p>
     */
    public AirIntakeTemperatureCommand() {
        super(AvailableCommand.AIR_INTAKE_TEMP);
    }

    /**
     * <p>Constructor for AirIntakeTemperatureCommand.</p>
     *
     * @param other a {@link AirIntakeTemperatureCommand} object.
     */
    public AirIntakeTemperatureCommand(AirIntakeTemperatureCommand other) {
        super(other);
    }

}
