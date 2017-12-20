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
package br.ufrn.imd.obd.utils;

import java.util.regex.Pattern;

/**
 * Class with some Regex Helpers.
 *
 * @author Elton Viana
 * @version $Id: $Id
 */

public class RegexUtils {
    public static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s");
    public static final Pattern BUS_INIT_PATTERN = Pattern.compile("(BUS INIT)|(BUSINIT)|(\\.)");
    public static final Pattern SEARCHING_PATTERN = Pattern.compile("SEARCHING");
    public static final Pattern DIGITS_LETTERS_PATTERN = Pattern.compile("([0-9A-F])+");
    public static final Pattern COLON_PATTERN = Pattern.compile(":");
    public static final Pattern STARTS_WITH_ALPHANUM_PATTERN = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);

    /**
     * Prevent instantiation
     */
    private RegexUtils() {
    }

    public static String replaceAll(Pattern pattern, String input, String replacement) {
        return pattern.matcher(input).replaceAll(replacement);
    }

    public static String removeAll(Pattern pattern, String input) {
        return pattern.matcher(input).replaceAll("");
    }
}
