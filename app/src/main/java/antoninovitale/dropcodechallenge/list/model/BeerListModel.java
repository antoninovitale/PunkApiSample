package antoninovitale.dropcodechallenge.list.model;

/**
 * Created by antoninovitale on 31/08/2017.
 */
public class BeerListModel implements IBeerListModel {
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTagLine() {
        return tagLine;
    }

    @Override
    public String getAbvPercentage() {
        return abvPercentage;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public Attribute getAttribute() {
        return attribute;
    }

}