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
package br.ufrn.imd.obdandroidapi.commands.protocol;

import br.ufrn.imd.obdandroidapi.enums.AvailableCommand;

/**
 * Reset the OBD connection.
 */
public class ObdResetCommand extends ObdProtocolCommand {

    /**
     * <p>Constructor for ObdResetCommand.</p>
     */
    public ObdResetCommand() {
        this(0L);
    }

    public ObdResetCommand(Long delay) {
        super(AvailableCommand.RESET_OBD);
        setResponseTimeDelay(delay);
    }

    /**
     * <p>Constructor for ObdResetCommand.</p>
     *
     * @param other a {@link ObdResetCommand} object.
     */
    public ObdResetCommand(ObdResetCommand other) {
        super(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFormattedResult() {
        return getResult();
    }

}
