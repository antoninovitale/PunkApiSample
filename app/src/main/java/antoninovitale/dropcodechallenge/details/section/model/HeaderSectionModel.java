package antoninovitale.dropcodechallenge.details.section.model;

/**
 * Created by antoninovitale on 28/08/2017.
 */
public class HeaderSectionModel {
    private final String name;

    private final String abvPercentage;

    private final String description;

    public HeaderSectionModel(String name, String abvPercentage, String description) {
        this.name = name;
        this.abvPercentage = abvPercentage;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getAbvPercentage() {
        return abvPercentage;
    }

    public String getDescription() {
        return description;
    }

}