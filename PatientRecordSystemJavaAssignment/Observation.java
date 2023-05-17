public abstract class Observation
{
    private ObservationType type; // main variable of the class is its type, based predominantly on the ObservationType class

    public Observation(ObservationType type) {
        this.type = type;
    }

    public ObservationType getType()
    {
        return type;
    }

    public String toString() {
        // data obtained primarility from the ObservationType class
        return type.toString();
    }    
}

