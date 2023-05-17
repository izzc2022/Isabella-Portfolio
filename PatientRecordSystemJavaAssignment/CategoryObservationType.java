import java.util.Arrays;

class CategoryObservationType extends ObservationType {
    private String[] categories;

    public CategoryObservationType(String code, String name, String[] categories) {
        super(code, name);
        this.categories = categories;
    }

    public String[] getCategories() {
        return categories;
    }

    public String getDetails() {
        return super.toString();
    }

    @Override
    public String toString() {
        return super.toString() + Arrays.toString(categories);
    }
}