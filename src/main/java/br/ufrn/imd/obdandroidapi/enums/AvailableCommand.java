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
package br.ufrn.imd.obdandroidapi.enums;

/**
 * Names of all available commands.
 */
public enum AvailableCommand {
    AIR_INTAKE_TEMP("Air Intake Temperature", "01 0F"),
    AMBIENT_AIR_TEMP("Ambient Air Temperature", "01 46"),
    ENGINE_COOLANT_TEMP("Engine Coolant Temperature", "01 05"),
    BAROMETRIC_PRESSURE("Barometric Pressure", "01 33"),
    FUEL_PRESSURE("Fuel Pressure", "01 0A"),
    INTAKE_MANIFOLD_PRESSURE("Intake Manifold Pressure", "01 0B"),
    ENGINE_LOAD("Engine Load", "01 04"),
    ENGINE_RUNTIME("Engine Runtime", "01 1F"),
    ENGINE_RPM("Engine RPM", "01 0C"),
    SPEED("Vehicle Speed", "01 0D"),
    MAF("Mass Air Flow", "01 10"),
    THROTTLE_POS("Throttle Position", "01 11"),
    TROUBLE_CODES("Trouble Codes", "03"),
    PENDING_TROUBLE_CODES("Pending Trouble Codes", "07"),
    PERMANENT_TROUBLE_CODES("Permanent Trouble Codes", "0A"),
    RESET_TROUBLE_CODES("Reset Trouble Codes", "04"),
    FUEL_LEVEL("Fuel Level", "01 2F"),
    FUEL_TYPE("Fuel Type", "01 51"),
    FUEL_CONSUMPTION_RATE("Fuel Consumption Rate", "01 5E"),
    TIMING_ADVANCE("Timing Advance", "01 0E"),
    DTC_NUMBER("Diagnostic Trouble Codes", "01 01"),
    EQUIV_RATIO("Command Equivalence Ratio", "01 44"),
    DISTANCE_TRAVELED_AFTER_CODES_CLEARED("Distance since codes cleared", "01 31"),
    CONTROL_MODULE_VOLTAGE("Control Module Power Supply", "01 42"),
    FUEL_RAIL_PRESSURE("Fuel Rail Pressure", "01 23"),
    VIN("Vehicle Identification Number (VIN)", "09 02"),
    DISTANCE_TRAVELED_MIL_ON("Distance traveled with MIL on", "01 21"),
    TIME_TRAVELED_MIL_ON("Time run with MIL on", "01 4D"),
    TIME_SINCE_TC_CLEARED("Time since trouble codes cleared", "01 4E"),
    REL_THROTTLE_POS("Relative throttle position", "01 45"),
    PIDS_01_20("Available PIDs 01-20", "01 00"),
    PIDS_21_40("Available PIDs 21-40", "01 20"),
    PIDS_41_60("Available PIDs 41-60", "01 40"),
    ABS_LOAD("Absolute load", "01 43"),
    ENGINE_OIL_TEMP("Engine oil temperature", "01 5C"),
    AIR_FUEL_RATIO("Air/Fuel Ratio", "01 44"),
    WIDEBAND_AIR_FUEL_RATIO("Wideband Air/Fuel Ratio", "01 34"),
    DESCRIBE_PROTOCOL("Describe protocol", "AT DP"),
    DESCRIBE_PROTOCOL_NUMBER("Describe protocol number", "AT DPN"),
    IGNITION_MONITOR("Ignition monitor", "AT IGN"),
    SHORT_TERM_BANK_1("Short Term Fuel Trim Bank 1", "01 06"),
    LONG_TERM_BANK_1("Long Term Fuel Trim Bank 1", "01 07"),
    SHORT_TERM_BANK_2("Short Term Fuel Trim Bank 2", "01 08"),
    LONG_TERM_BANK_2("Long Term Fuel Trim Bank 2", "01 09"),
    ADAPTIVE_TIMING_OFF("Adaptive Timing Control Off", "AT AT 0"),
    ADAPTIVE_TIMING_AUTO_1("Adaptive Timing Control Auto 1", "AT AT 1"),
    ADAPTIVE_TIMING_AUTO_2("Adaptive Timing Control Auto 2", "AT AT 2"),
    PROTOCOL_CLOSE("Protocol Close", "AT PC"),
    ECHO_OFF("Echo Off", "AT E0"),
    HEADERS_OFF("Headers disabled", "AT H0"),
    LINE_FEED_OFF("Line Feed Off", "AT L0"),
    SPACES_OFF("Spaces Off", "AT S0"),
    RESET_OBD("Reset OBD", "AT Z"),
    WARM_START("WarmStart OBD", "AT WS"),
    SET_TIMEOUT("Set Timeout", "AT ST 0"),
    PROTOCOL_AUTO("Select Protocol - Auto", "AT SP 0"),
    PROTOCOL_SAE_J1850_PWM("Select Protocol - SAE J1850 PWM", "AT SP 1"),
    PROTOCOL_SAE_J1850_VPW("Select Protocol - SAE J1850 VPW", "AT SP 2"),
    PROTOCOL_ISO_9141_2("Select Protocol - ISO 9141-2", "AT SP 3"),
    PROTOCOL_ISO_14230_4_KWP("Select Protocol - ISO 14230-4 (KWP 5BAUD)", "AT SP 4"),
    PROTOCOL_ISO_14230_4_KWP_FAST("Select Protocol - ISO 14230-4 (KWP FAST)", "AT SP 5"),
    PROTOCOL_ISO_15765_4_CAN("Select Protocol - ISO 15765-4 (CAN 11/500)", "AT SP 6"),
    PROTOCOL_ISO_15765_4_CAN_B("Select Protocol - ISO 15765-4 (CAN 29/500)", "AT SP 7"),
    PROTOCOL_ISO_15765_4_CAN_C("Select Protocol - ISO 15765-4 (CAN 11/250)", "AT SP 8"),
    PROTOCOL_ISO_15765_4_CAN_D("Select Protocol - ISO 15765-4 (CAN 29/250)", "AT SP 9"),
    PROTOCOL_SAE_J1939_CAN("Select Protocol - SAE J1939 (CAN 29/250)", "AT SP A"),
    CUSTOM_COMMAND("Custom Command", "");

    private final String value;
    private String command;

    /**
     * @param value Command description
     */
    AvailableCommand(String value, String command) {
        this.value = value;
        this.command = command;
    }

    /**
     * <p>Getter for the field <code>value</code>.</p>
     *
     * @return a {@link String} object.
     */
    public final String getValue() {
        return value;
    }

    /**
     * <p>Getter for the field <code>command</code>.</p>
     *
     * @return a {@link String} object.
     */
    public final String getCommand() {
        return command;
    }

    @Override
    public String toString() {
        return this.value + " [" + this.command + "]";
    }

    public static class CustomCommand {

        private CustomCommand() {
        }

        public static AvailableCommand getTimeout(int timeout) {
            SET_TIMEOUT.command = "AT ST " + Integer.toHexString(0xFF & timeout);
            return SET_TIMEOUT;
        }

        public static AvailableCommand rawCommand(String command) {
            CUSTOM_COMMAND.command = command;
            return CUSTOM_COMMAND;
        }
    }

}
