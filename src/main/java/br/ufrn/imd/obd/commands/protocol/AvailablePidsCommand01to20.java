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
package br.ufrn.imd.obd.commands.protocol;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.obd.commands.ObdCommand;
import br.ufrn.imd.obd.enums.AvailableCommand;

/**
 * Retrieve available PIDs ranging from 21 to 40.
 */
public class AvailablePidsCommand01to20 extends GenericAvailablePidsCommand {

    private List<Class<? extends ObdCommand>> supportedCommands = new ArrayList<>();

    /**
     * Default constructor.
     *
     * @param command a {@link String} object.
     */
    public AvailablePidsCommand01to20() {
        super(AvailableCommand.PIDS_01_20, 0);
    }

    /**
     * Copy constructor.
     *
     * @param other a {@link AvailablePidsCommand01to20} object.
     */
    public AvailablePidsCommand01to20(AvailablePidsCommand01to20 other) {
        super(other);
    }

}
