package antoninovitale.dropcodechallenge.list.model;

/**
 * Created by antoninovitale on 31/08/2017.
 */
public class BeerListModel {
    private String name;

    private String tagLine;

    private String abvPercentage;

    private String imageUrl;

    private Attribute attribute;

    public BeerListModel(String name, String tagLine, String abvPercentage, Attribute attribute,
                         String imageUrl) {
        this.name = name;
        this.tagLine = tagLine;
        this.abvPercentage = abvPercentage;
        this.attribute = attribute;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getTagLine() {
        return tagLine;
    }

    public String getAbvPercentage() {
        return abvPercentage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Attribute getAttribute() {
        return attribute;
    }

}