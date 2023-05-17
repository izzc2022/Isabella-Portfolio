public class PassengerCarriages extends RailroadCar
{
    private int numberOfPassengers;

    public PassengerCarriages(String id, double tareWeight, int numberOfPassengers)
    {
        super(id, tareWeight);
        this.numberOfPassengers = numberOfPassengers;
    }

    public double getNumberOfPassengers()
    {
        return numberOfPassengers;
    }

    public double calculateTotalWeight()
    {
        double total = getTareWeight() + (numberOfPassengers * 105);
        return total;
    }

    public String getDetails()
    {
        String details = "Passenger Carriages details \n";
        return details + "ID: " + getId() + "\nTare Weight: " + getTareWeight() + "\nNumber Of Passengers: " + numberOfPassengers + "\nTotal Weight: " + calculateTotalWeight();
    }

    public String toString()
    {
        return getDetails();
    }
}