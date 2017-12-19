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
package br.ufrn.imd.obdandroidapi.commands;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Container for multiple {@link ObdCommand} instances.
 */
public class ObdCommandGroup implements IObdCommand {
    private final List<ObdCommand> commands;

    /**
     * Default constructor.
     */
    public ObdCommandGroup() {
        this.commands = new ArrayList<>();
    }

    /**
     * Add ObdCommand to list of ObdCommands.
     *
     * @param command a {@link ObdCommand} object.
     */
    public void add(ObdCommand command) {
        this.commands.add(command);
    }

    /**
     * Removes ObdCommand from the list of ObdCommands.
     *
     * @param command a {@link ObdCommand} object.
     */
    public void remove(ObdCommand command) {
        this.commands.remove(command);
    }

    /**
     * Iterate all commands, send them and read response.
     *
     * @param in  a {@link java.io.InputStream} object.
     * @param out a {@link java.io.OutputStream} object.
     * @throws java.io.IOException            if any.
     * @throws java.lang.InterruptedException if any.
     */
    @Override
    public void run(InputStream in, OutputStream out) throws IOException, InterruptedException {
        for (ObdCommand command : commands) {
            command.run(in, out);
        }
    }

    @Override
    public String getResult() {
        StringBuilder res = new StringBuilder();
        for (ObdCommand command : commands) {
            res.append(command.getResult()).append(",");
        }

        return res.toString();
    }

    /**
     * <p>getFormattedResult.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    @Override
    public String getFormattedResult() {
        StringBuilder res = new StringBuilder();
        for (ObdCommand command : commands) {
            res.append(command.getFormattedResult()).append(",");
        }

        return res.toString();
    }

    @Override
    public String getResultUnit() {
        StringBuilder res = new StringBuilder();
        for (ObdCommand command : commands) {
            res.append(command.getResultUnit()).append(",");
        }

        return res.toString();
    }

    @Override
    public String getName() {
        StringBuilder res = new StringBuilder();
        for (ObdCommand command : commands) {
            res.append(command.getName()).append(",");
        }

        return res.toString();
    }

    @Override
    public String getCommandPID() {
        StringBuilder res = new StringBuilder();
        for (ObdCommand command : commands) {
            res.append(command.getCommandPID()).append(",");
        }

        return res.toString();
    }

    @Override
    public Map<String, String> getMap() {
        Map<String, String> retMap = new HashMap<>();
        for (ObdCommand command : commands) {
            Map<String, String> cmdMap = command.getMap();
            for (Map.Entry<String, String> entry : cmdMap.entrySet()) {
                retMap.put(entry.getKey(), entry.getValue());
            }
        }
        return retMap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ObdCommand command : commands) {
            sb.append(command.toString());
        }
        return sb.toString();
    }
}
