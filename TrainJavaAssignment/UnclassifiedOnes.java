public class UnclassifiedOnes extends RailroadCar
{

    public UnclassifiedOnes(String id, double tareWeight)
    {
        super(id, tareWeight);
    }

    public double calculateTotalWeight()
    {
        double total = getTareWeight();
        return total;
    }

    public String getDetails()
    {
        String details = "Unclassified Car details \n";
        return details + "ID: " + getId() + "\nTare Weight: " + getTareWeight() + "\nTotal Weight: " + calculateTotalWeight();
    }

    public String toString()
    {
        return getDetails();
    }
}