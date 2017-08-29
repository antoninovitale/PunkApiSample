package antoninovitale.dropcodechallenge.details.section.model;

/**
 * Created by antoninovitale on 29/08/2017.
 */
public class IngredientSectionModel {
    private String name;

    private String amount;

    public IngredientSectionModel(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

}