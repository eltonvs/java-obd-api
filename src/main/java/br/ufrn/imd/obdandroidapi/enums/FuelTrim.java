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
package br.ufrn.imd.obdandroidapi.enums;

/**
 * Select one of the Fuel Trim percentage banks to access.
 */
public enum FuelTrim {

    SHORT_TERM_BANK_1(AvailableCommand.SHORT_TERM_BANK_1),
    LONG_TERM_BANK_1(AvailableCommand.LONG_TERM_BANK_1),
    SHORT_TERM_BANK_2(AvailableCommand.SHORT_TERM_BANK_2),
    LONG_TERM_BANK_2(AvailableCommand.LONG_TERM_BANK_2);

    private final AvailableCommand value;

    FuelTrim(final AvailableCommand value) {
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
