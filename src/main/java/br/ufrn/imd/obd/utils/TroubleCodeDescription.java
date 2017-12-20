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
package br.ufrn.imd.obd.utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import br.ufrn.imd.obd.R;

/**
 * Class to provide a description to a trouble code.
 */
public class TroubleCodeDescription {

    private static final String TAG = TroubleCodeDescription.class.getName();
    private static TroubleCodeDescription instance = null;
    private JSONObject troubleCodes = new JSONObject();

    /**
     * Empty private constructor to prevent instantiation
     */
    private TroubleCodeDescription() {
    }

    /**
     * Default constructor
     */
    private TroubleCodeDescription(Context context) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.trouble_codes)))) {
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            troubleCodes = new JSONObject(sb.toString());
        } catch (IOException | JSONException e) {
            Log.e(TAG, "TroubleCodeDescription: Error", e);
        }
    }

    public static TroubleCodeDescription getInstance(Context context) {
        if (instance == null) {
            instance = new TroubleCodeDescription(context);
        }

        return instance;
    }

    public String getTroubleCodeDescription(String dtc) {
        return troubleCodes.optString(dtc);
    }

}
