package antoninovitale.dropcodechallenge.util;

/**
 * Created by antoninovitale on 29/08/2017.
 */

public class Utils {

    public static String formatPercentage(double value) {
        return String.format("%s%%", value);
    }

    public static String getAttributeLabel(double abv, double ibu, String attributeStrong, String
            attributeBitter) {
        String label = null;
        if (abv > 10 && ibu > 90) {
            label = String.format("%s & %s", attributeStrong, attributeBitter);
        } else if (abv > 10) {
            label = attributeStrong;
        } else if (ibu > 90) {
            label = attributeBitter;
        }
        return label;
    }

}