public class MeasurementObservationType extends ObservationType { 
    // The super class provides a handful of the information for simplicity and ease of use and implementation

    private String unit;

    public MeasurementObservationType(String code, String name, String unit) {
        super(code,name);
        this.unit = unit;
    }
    
    public String getUnit() {
        return unit;
    }

    public String getDetails() {
        return super.toString() + unit;
    }

    public String observationDetails()
    {
        return super.getCode() + unit;
    }

    @Override // an override of the superclasses toString method to ensure the new and relevant details are updated and implemented instead.
    public String toString() {
        return super.toString() + unit;
    }
}
