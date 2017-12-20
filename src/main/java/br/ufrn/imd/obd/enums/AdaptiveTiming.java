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
package br.ufrn.imd.obd.enums;

/**
 * Select one of the Fuel Trim percentage banks to access.
 */
public enum AdaptiveTiming {

    ADAPTIVE_TIMING_OFF(AvailableCommand.ADAPTIVE_TIMING_OFF),
    ADAPTIVE_TIMING_AUTO_1(AvailableCommand.ADAPTIVE_TIMING_AUTO_1),
    ADAPTIVE_TIMING_AUTO_2(AvailableCommand.ADAPTIVE_TIMING_AUTO_2);

    private final AvailableCommand value;

    AdaptiveTiming(final AvailableCommand value) {
        this.value = value;
    }

    /**
     * <p>Getter for the field <code>value</code>.</p>
     *
     * @return a int.
     */
    public AvailableCommand getValue() {
        return value;
    }

}
