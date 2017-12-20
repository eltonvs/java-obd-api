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
package br.ufrn.imd.obd.commands.engine;


import br.ufrn.imd.obd.commands.PercentageObdCommand;
import br.ufrn.imd.obd.enums.AvailableCommand;

/**
 * <p>AbsoluteLoadCommand class.</p>
 */
public class AbsoluteLoadCommand extends PercentageObdCommand {

    /**
     * Default constructor.
     */
    public AbsoluteLoadCommand() {
        super(AvailableCommand.ABS_LOAD);
    }

    /**
     * Copy constructor.
     *
     * @param other a {@link AbsoluteLoadCommand} object.
     */
    public AbsoluteLoadCommand(AbsoluteLoadCommand other) {
        super(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performCalculations() {
        // ignore first two bytes [hh hh] of the response
        int a = buffer.get(2);
        int b = buffer.get(3);
        percentage = (a * 256 + b) * 100f / 255;
    }

    /**
     * <p>getRatio.</p>
     *
     * @return a double.
     */
    public double getRatio() {
        return percentage;
    }

}
