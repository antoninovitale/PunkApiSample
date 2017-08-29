package antoninovitale.dropcodechallenge.details.section.model;

import antoninovitale.dropcodechallenge.util.Utils;

/**
 * Created by antoninovitale on 28/08/2017.
 */
public class HeaderSectionModel {
    private String name;

    private String abv;

    private String description;

    public HeaderSectionModel(String name, double abv, String description) {
        this.name = name;
        this.abv = Utils.formatPercentage(abv);
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getAbv() {
        return abv;
    }

    public String getDescription() {
        return description;
    }

}