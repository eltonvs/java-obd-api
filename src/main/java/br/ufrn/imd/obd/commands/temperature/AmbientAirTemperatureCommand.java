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
 * Ambient Air Temperature.
 */
public class AmbientAirTemperatureCommand extends TemperatureCommand {

    /**
     * <p>Constructor for AmbientAirTemperatureCommand.</p>
     */
    public AmbientAirTemperatureCommand() {
        super(AvailableCommand.AMBIENT_AIR_TEMP);
    }

    /**
     * <p>Constructor for AmbientAirTemperatureCommand.</p>
     *
     * @param other a {@link TemperatureCommand} object.
     */
    public AmbientAirTemperatureCommand(TemperatureCommand other) {
        super(other);
    }

}
