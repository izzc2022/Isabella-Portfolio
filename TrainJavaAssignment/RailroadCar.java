public abstract class RailroadCar
{
    private String id;
    private double tareWeight;

    public RailroadCar(String id, double tareWeight)
    {
        this.id = id;
        this.tareWeight = tareWeight;
    }

    public double getTareWeight()
    {
        return tareWeight;
    }

    public String getId()
    {
        return id;
    }

    public abstract double calculateTotalWeight();

    public String getDetails()
    {
        String details = "Railroad details \n";
        return details + "ID: " + id + "\nTare Weight: " + tareWeight;
    }

    public String toString()
    {
        return getDetails();
    }
}