package antoninovitale.dropcodechallenge.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Amount implements Serializable {
    @SerializedName("value")
    @Expose
    private double value;

    @SerializedName("unit")
    @Expose
    private String unit;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}