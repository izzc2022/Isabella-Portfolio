public class CategoryObservation extends Observation {
    
    private String category;

    public CategoryObservation(ObservationType type, String category) {
        super(type);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public String toString() {
        return getType().toString() + " " + category;
    }    
}