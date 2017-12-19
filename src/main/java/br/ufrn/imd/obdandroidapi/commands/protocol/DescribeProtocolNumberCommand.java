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
package br.ufrn.imd.obdandroidapi.commands.protocol;

import br.ufrn.imd.obdandroidapi.commands.ObdCommand;
import br.ufrn.imd.obdandroidapi.enums.AvailableCommand;
import br.ufrn.imd.obdandroidapi.enums.ObdProtocols;

/**
 * Describe the Protocol by Number.
 * It returns a number which represents the current
 * obdProtocol. If the automatic search function is also
 * enabled, the number will be preceded with the letter
 * ‘A’. The number is the same one that is used with the
 * set obdProtocol and test obdProtocol commands.
 *
 * @since 1.0-RC12
 */
public class DescribeProtocolNumberCommand extends ObdCommand {

    private ObdProtocols obdProtocol = ObdProtocols.AUTO;

    /**
     * <p>Constructor for DescribeProtocolNumberCommand.</p>
     */
    public DescribeProtocolNumberCommand() {
        super(AvailableCommand.DESCRIBE_PROTOCOL_NUMBER);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method exists so that for each command, there must be a method that is
     * called only once to perform calculations.
     */
    @Override
    protected void performCalculations() {
        String result = getResult();
        char protocolNumber;
        if (result.length() == 2) {//the obdProtocol was set automatic and its format A#
            protocolNumber = result.charAt(1);
        } else {
            protocolNumber = result.charAt(0);
        }
        ObdProtocols[] protocols = ObdProtocols.values();
        for (ObdProtocols protocol : protocols) {
            if (protocol.getValue().getCommand().endsWith(String.valueOf(protocolNumber))) {
                this.obdProtocol = protocol;
                break;
            }
        }
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
        return obdProtocol.name();
    }

    /**
     * <p>Getter for the field <code>obdProtocol</code>.</p>
     *
     * @return a {@link ObdProtocols} object.
     */
    public ObdProtocols getObdProtocol() {
        return obdProtocol;
    }

}
