package antoninovitale.dropcodechallenge.details.section.model;

/**
 * Created by antoninovitale on 31/08/2017.
 */
public enum Add {
    START, MIDDLE, END, NONE;

    public static Add getAdd(String add) {
        if (add == null) return NONE;

        switch (add) {
            case "start":
                return START;
            case "middle":
                return MIDDLE;
            case "end":
                return END;
            default:
                return NONE;
        }
    }

}