public class RefrigeratedUnits extends RailroadCar
{
    private double goodsWeight;
    private double coolantWeight;

    public RefrigeratedUnits(String id, double tareWeight, double goodsWeight, double coolantWeight)
    {
        super(id, tareWeight);
        this.goodsWeight = goodsWeight;
        this.coolantWeight = coolantWeight;
    }

    public double getGoodsWeight()
    {
        return goodsWeight;
    }

    public double getCoolantWeight()
    {
        return coolantWeight;
    }

    public double calculateTotalWeight()
    {
        double total = getTareWeight() + goodsWeight + coolantWeight;
        return total;
    }

    public String getDetails()
    {
        String details = "Refrigated Units details \n";
        return details + "ID: " + getId() + "\nTare Weight: " + getTareWeight() + "\nGoods Weight: " + goodsWeight + "\nCoolant Weight: " + coolantWeight + "\nTotal Weight: " + calculateTotalWeight();
    }

    public String toString()
    {
        return getDetails();
    }
}