package antoninovitale.dropcodechallenge.details.section.model;

/**
 * Created by antoninovitale on 29/08/2017.
 */
public class IngredientSectionModel {
    private final String name;

    private final String amount;

    private final IngredientType type;

    private final Add add;

    private Status status;

    public IngredientSectionModel(String name, String amount, IngredientType type, Status status,
                                  Add add) {
        this.name = name;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.add = add;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public IngredientType getType() {
        return type;
    }

    public Add getAdd() {
        return add;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}