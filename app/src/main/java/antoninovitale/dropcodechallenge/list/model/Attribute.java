package antoninovitale.dropcodechallenge.list.model;

/**
 * Created by antoninovitale on 31/08/2017.
 */
public enum Attribute {
    STRONG, BITTER, STRONG_BITTER, NONE;

    public static Attribute getAttribute(double abv, double ibu) {
        Attribute attribute;
        if (abv > 10 && ibu > 90) {
            attribute = Attribute.STRONG_BITTER;
        } else if (abv > 10) {
            attribute = Attribute.STRONG;
        } else if (ibu > 90) {
            attribute = Attribute.BITTER;
        } else {
            attribute = Attribute.NONE;
        }
        return attribute;
    }

    public static String getAttributeLabel(Attribute attribute, String attributeStrong, String
            attributeBitter) {
        String label = null;
        switch (attribute) {
            case STRONG:
                label = attributeStrong;
                break;
            case BITTER:
                label = attributeBitter;
                break;
            case STRONG_BITTER:
                label = String.format("%s & %s", attributeStrong, attributeBitter);
                break;
            default:
                break;
        }
        return label;
    }

}