package antoninovitale.dropcodechallenge.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MashTemp implements Serializable {
    @SerializedName("temp")
    @Expose
    private Temp temp;

    @SerializedName("duration")
    @Expose
    private double duration;

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

}