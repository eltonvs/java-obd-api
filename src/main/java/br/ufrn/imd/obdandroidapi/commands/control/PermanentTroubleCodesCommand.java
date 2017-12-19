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
package br.ufrn.imd.obdandroidapi.commands.control;


import java.util.regex.Pattern;

import br.ufrn.imd.obdandroidapi.enums.AvailableCommand;

/**
 * It is not needed no know how many DTC are stored.
 * Because when no DTC are stored response will be NO DATA
 * And where are more messages it will be stored in frames that have 7 bytes.
 * In one frame are stored 3 DTC.
 * If we find out DTC P0000 that mean no message are we can end.
 */
public class PermanentTroubleCodesCommand extends GenericTroubleCodeCommand {

    private static final Pattern CARRIAGE_NUMBER_PATTERN = Pattern.compile("^4A|[\r\n]4A|[\r\n]");

    /**
     * <p>Constructor for PermanentTroubleCodesCommand.</p>
     */
    public PermanentTroubleCodesCommand() {
        super(AvailableCommand.PERMANENT_TROUBLE_CODES);
    }

    /**
     * Copy constructor.
     *
     * @param other a {@link PermanentTroubleCodesCommand} object.
     */
    public PermanentTroubleCodesCommand(PermanentTroubleCodesCommand other) {
        super(other);
    }

    @Override
    protected String removeCarriageNumber(String str) {
        return CARRIAGE_NUMBER_PATTERN.matcher(str).replaceAll("");
    }

}
