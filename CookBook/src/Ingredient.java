public class Ingredient {
    public String name;
    public String quantity;
    public String measurement_unit;

    public Ingredient(String name, String quantity, String measurement_unit) {
        this.name = name;
        this.quantity = quantity;
        this.measurement_unit = measurement_unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeasurement_unit() {
        return measurement_unit;
    }

    public void setMeasurement_unit(String measurement_unit) {
        this.measurement_unit = measurement_unit;
    }
}
