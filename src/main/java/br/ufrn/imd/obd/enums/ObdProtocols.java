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
package br.ufrn.imd.obd.enums;

/**
 * All OBD protocols.
 */
public enum ObdProtocols {

    /**
     * Auto select protocol and save.
     */
    AUTO(AvailableCommand.PROTOCOL_AUTO),

    /**
     * 41.6 kbaud
     */
    SAE_J1850_PWM(AvailableCommand.PROTOCOL_SAE_J1850_PWM),

    /**
     * 10.4 kbaud
     */
    SAE_J1850_VPW(AvailableCommand.PROTOCOL_SAE_J1850_VPW),

    /**
     * 5 baud init
     */
    ISO_9141_2(AvailableCommand.PROTOCOL_ISO_9141_2),

    /**
     * 5 baud init
     */
    ISO_14230_4_KWP(AvailableCommand.PROTOCOL_ISO_14230_4_KWP),

    /**
     * Fast init
     */
    ISO_14230_4_KWP_FAST(AvailableCommand.PROTOCOL_ISO_14230_4_KWP_FAST),

    /**
     * 11 bit ID, 500 kbaud
     */
    ISO_15765_4_CAN(AvailableCommand.PROTOCOL_ISO_15765_4_CAN),

    /**
     * 29 bit ID, 500 kbaud
     */
    ISO_15765_4_CAN_B(AvailableCommand.PROTOCOL_ISO_15765_4_CAN_B),

    /**
     * 11 bit ID, 250 kbaud
     */
    ISO_15765_4_CAN_C(AvailableCommand.PROTOCOL_ISO_15765_4_CAN_C),

    /**
     * 29 bit ID, 250 kbaud
     */
    ISO_15765_4_CAN_D(AvailableCommand.PROTOCOL_ISO_15765_4_CAN_D),

    /**
     * 29 bit ID, 250 kbaud (user adjustable)
     */
    SAE_J1939_CAN(AvailableCommand.PROTOCOL_SAE_J1939_CAN);


    private final AvailableCommand value;

    ObdProtocols(AvailableCommand value) {
        this.value = value;
    }

    /**
     * <p>Getter for the field <code>value</code>.</p>
     *
     * @return a char.
     */
    public AvailableCommand getValue() {
        return value;
    }
}
