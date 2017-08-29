package antoninovitale.dropcodechallenge.details.section.model;

/**
 * Created by antoninovitale on 29/08/2017.
 */
public class IngredientSectionModel {
    private final String name;

    private final String amount;

    private final String add;

    public IngredientSectionModel(String name, String amount, String add) {
        this.name = name;
        this.amount = amount;
        this.add = add;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public String getAdd() {
        return add;
    }

}