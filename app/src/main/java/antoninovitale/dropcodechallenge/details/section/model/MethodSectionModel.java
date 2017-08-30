package antoninovitale.dropcodechallenge.details.section.model;

/**
 * Created by antoninovitale on 30/08/2017.
 */
public class MethodSectionModel {

    private final String temp;

    private final long duration;

    public MethodSectionModel(String temp, long duration) {
        this.temp = temp;
        this.duration = duration;
    }

    public String getTemp() {
        return temp;
    }

    public long getDuration() {
        return duration;
    }

}