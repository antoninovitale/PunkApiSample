package antoninovitale.dropcodechallenge.details.section.model;

/**
 * Created by antoninovitale on 30/08/2017.
 */
public class MethodSectionModel {

    private final String temp;

    private final long duration;

    private long remainingTime;

    private Status status;

    public MethodSectionModel(String temp, long duration, Status status) {
        this.temp = temp;
        this.duration = duration;
        this.status = status;
    }

    public String getTemp() {
        return temp;
    }

    public long getDuration() {
        return duration;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(long remainingTime) {
        this.remainingTime = remainingTime;
    }

}