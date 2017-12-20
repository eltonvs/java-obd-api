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

import br.ufrn.imd.obd.commands.ObdCommand;
import br.ufrn.imd.obd.enums.AvailableCommand;

/**
 * Reset trouble codes.
 */
public class ResetTroubleCodesCommand extends ObdCommand {

    /**
     * <p>Constructor for ResetTroubleCodesCommand.</p>
     */
    public ResetTroubleCodesCommand() {
        super(AvailableCommand.RESET_TROUBLE_CODES);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performCalculations() {
        // Empty method
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFormattedResult() {
        return getResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCalculatedResult() {
        return getResult();
    }

}
