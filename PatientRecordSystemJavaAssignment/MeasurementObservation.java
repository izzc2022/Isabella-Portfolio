public class MeasurementObservation extends Observation {

    private double value;

    public MeasurementObservation(ObservationType type, double value) {
        super(type);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String toString() {
        if (getType() instanceof MeasurementObservationType) {
            MeasurementObservationType measurementType = (MeasurementObservationType) getType();
            return getType().getCode() + "; " + measurementType.getUnit();
        } else {
            return getType().getCode();
        }
    }    
}