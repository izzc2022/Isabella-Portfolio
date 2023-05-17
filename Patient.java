public class Patient 
{
    private String name;
    private String id;
    private Observation[] observations;
    private int observationCount;

    public Patient(String id, String name)
    {
       this.name = name;
       this.id = id;
       this.observations = new Observation[50];
       this.observationCount = 0;
    }

    public String getID()
    {
        // useful getter methods are implemented for usage within the PRS class
        return id;
    }

    public String getName()
    {
        return name;
    }

    // method that returns an array of observations that are of the type Observation
    public Observation[] getObservations()
    {
        return observations;
    }
    
    public void addObservation(Observation observation) {
        // the add observation method is implemented here, to assist with ensuring that the correct instance of a patient is obstaining the correct observations.
        if (observationCount < observations.length) {
            observations[observationCount] = observation;
            observationCount++;
        }
    }

    public String getDetails() {
        String result = id + "; " + name;
        return result;
    }
    
    public String toString() {
        String text = "\nPatient ID: " + id + "\n";
        text += "Name: " + name + "\n";
        text += "Observations: \n";
        for (int i = 0; i < observationCount; i++) {
            // Observations for each patient can be of eitehr a measurement type or category type, so they are differentiated here in order to obtain the correct results and details associated with their type.
            if (observations[i] instanceof MeasurementObservation) {
                MeasurementObservation measurementObs = (MeasurementObservation) observations[i];
                text += measurementObs.getType().getCode() + "; ";
                text += measurementObs.getValue() + "\n";
            }
            else if (observations[i] instanceof CategoryObservation) {
                CategoryObservation categoryObs = (CategoryObservation) observations[i];
                text += categoryObs.getType().getCode() + "; ";
                text += categoryObs.getCategory() + "\n";
            }
        }
        if (observationCount == 0) 
        {
            text += "No observations available";
        }
        return text;
    }    
}

