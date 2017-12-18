package br.ufrn.imd.obdandroidapi.commands.control;

import java.util.regex.Matcher;

import br.ufrn.imd.obdandroidapi.commands.PersistentCommand;
import br.ufrn.imd.obdandroidapi.enums.AvailableCommandNames;

import static br.ufrn.imd.obdandroidapi.utils.RegexUtils.STARTS_WITH_ALPHANUM_PATTERN;

/**
 * Vehicle Identification Number (VIN).
 */
public class VinCommand extends PersistentCommand {
    private String vin = "";

    /**
     * Default constructor.
     */
    public VinCommand() {
        super("09 02");
    }

    /**
     * Copy constructor.
     *
     * @param other a {@link VinCommand} object.
     */
    public VinCommand(VinCommand other) {
        super(other);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performCalculations() {
        final String result = getResult();
        String workingData;

        if (result.contains(":")) {  // CAN(ISO-15765) protocol.
            // 9 is xxx490201, xxx is bytes of information to follow.
            workingData = result.replaceAll(".:", "").substring(9);
            Matcher m = STARTS_WITH_ALPHANUM_PATTERN.matcher(convertHexToString(workingData));
            if (m.find()) {
                workingData = result.replaceAll("0:49", "").replaceAll(".:", "");
            }
        } else {  // ISO9141-2, KWP2000 Fast and KWP2000 5Kbps (ISO15031) protocols.
            workingData = result.replaceAll("49020.", "");
        }
        vin = convertHexToString(workingData).replaceAll("[\u0000-\u001f]", "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFormattedResult() {
        return getCalculatedResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return AvailableCommandNames.VIN.getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCalculatedResult() {
        return vin;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillBuffer() {
        // Empty method
    }

    private String convertHexToString(String hex) {
        StringBuilder sb = new StringBuilder();
        // 49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {

            // grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            // convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            // convert the decimal to character
            sb.append((char) decimal);
        }

        return sb.toString();
    }

}
