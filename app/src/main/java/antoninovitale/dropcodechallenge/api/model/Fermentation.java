package antoninovitale.dropcodechallenge.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Fermentation implements Serializable {

    @SerializedName("temp")
    @Expose
    private Temp temp;

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

}