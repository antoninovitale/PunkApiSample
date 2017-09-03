package antoninovitale.dropcodechallenge.details.section.model;

/**
 * Created by antoninovitale on 31/08/2017.
 */
public enum Status {
    IDLE, RUNNING, PAUSED, DONE;

    public static String getStatusLabel(Status s, String idle, String running, String paused,
                                        String done) {
        String label = null;
        switch (s) {
            case IDLE:
                label = idle;
                break;
            case RUNNING:
                label = running;
                break;
            case PAUSED:
                label = paused;
                break;
            case DONE:
                label = done;
                break;
            default:
                break;
        }
        return label;
    }

}