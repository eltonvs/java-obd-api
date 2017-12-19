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

import br.ufrn.imd.obdandroidapi.enums.AvailableCommandNames;

/**
 * Retrieve available PIDs ranging from 41 to 60.
 */
public class AvailablePidsCommand41to60 extends AvailablePidsCommand {

    /**
     * Default constructor.
     */
    public AvailablePidsCommand41to60() {
        super("01 40");
    }

    /**
     * Copy constructor.
     *
     * @param other a {@link AvailablePidsCommand} object.
     */
    public AvailablePidsCommand41to60(AvailablePidsCommand41to60 other) {
        super(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return AvailableCommandNames.PIDS_41_60.getValue();
    }
}
