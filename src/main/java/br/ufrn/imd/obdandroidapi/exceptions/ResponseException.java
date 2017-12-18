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
package br.ufrn.imd.obdandroidapi.exceptions;

/**
 * Generic message error
 *
 * @author pires
 * @version $Id: $Id
 */
public class ResponseException extends RuntimeException {

    private final String message;
    private final boolean matchRegex;

    private String response;
    private String command;

    /**
     * <p>Constructor for ResponseException.</p>
     *
     * @param message a {@link String} object.
     */
    protected ResponseException(String message) {
        this(message, false);
    }

    /**
     * <p>Constructor for ResponseException.</p>
     *
     * @param message    a {@link String} object.
     * @param matchRegex a boolean.
     */
    protected ResponseException(String message, boolean matchRegex) {
        this.message = message;
        this.matchRegex = matchRegex;
    }

    private static String clean(String s) {
        return s == null ? "" : s.replaceAll("\\s", "").toUpperCase();
    }

    /**
     * <p>isError.</p>
     *
     * @param response a {@link String} object.
     * @return a boolean.
     */
    public boolean isError(String response) {
        this.response = response;
        if (matchRegex) {
            return clean(response).matches(clean(message));
        } else {
            return clean(response).contains(clean(message));
        }
    }

    /**
     * <p>Setter for the field <code>command</code>.</p>
     *
     * @param command a {@link String} object.
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return "Error running " + command + ", response: " + response;
    }

}
