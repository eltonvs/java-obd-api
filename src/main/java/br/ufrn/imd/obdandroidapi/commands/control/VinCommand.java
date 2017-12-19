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

import java.util.regex.Matcher;

import br.ufrn.imd.obdandroidapi.commands.PersistentCommand;
import br.ufrn.imd.obdandroidapi.enums.AvailableCommand;

import static br.ufrn.imd.obdandroidapi.utils.RegexUtils.STARTS_WITH_ALPHANUM_PATTERN;

/**
 * Vehicle Identification Number (VIN).
 */
public class VinCommand extends PersistentCommand {
    private String vin = "";

    /**
     * Default constructor.
     */
    public VinCommand() {
        super(AvailableCommand.VIN);
    }

    /**
     * Copy constructor.
     *
     * @param other a {@link VinCommand} object.
     */
    public VinCommand(VinCommand other) {
        super(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performCalculations() {
        final String result = getResult();
        String workingData;

        if (result.contains(":")) {  // CAN(ISO-15765) protocol.
            // 9 is xxx490201, xxx is bytes of information to follow.
            workingData = result.replaceAll(".:", "").substring(9);
            Matcher m = STARTS_WITH_ALPHANUM_PATTERN.matcher(convertHexToString(workingData));
            if (m.find()) {
                workingData = result.replaceAll("0:49", "").replaceAll(".:", "");
            }
        } else {  // ISO9141-2, KWP2000 Fast and KWP2000 5Kbps (ISO15031) protocols.
            workingData = result.replaceAll("49020.", "");
        }
        vin = convertHexToString(workingData).replaceAll("[\u0000-\u001f]", "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFormattedResult() {
        return getCalculatedResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCalculatedResult() {
        return vin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillBuffer() {
        // Empty method
    }

    private String convertHexToString(String hex) {
        StringBuilder sb = new StringBuilder();
        // 49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {

            // grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            // convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            // convert the decimal to character
            sb.append((char) decimal);
        }

        return sb.toString();
    }

}
