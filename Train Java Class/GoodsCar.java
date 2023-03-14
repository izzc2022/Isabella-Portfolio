public class GoodsCar extends RailroadCar
{
    private double goodsWeight;

    public GoodsCar(String id, double tareWeight, double goodsWeight)
    {
        super(id, tareWeight);
        this.goodsWeight = goodsWeight;
    }

    public double getGoodsWeight()
    {
        return goodsWeight;
    }

    public double calculateTotalWeight()
    {
        double total = getTareWeight() + goodsWeight;
        return total;
    }

    public String getDetails()
    {
        String details = "Goods Car details \n";
        return details + "ID: " + getId() + "\nTare Weight: " + getTareWeight() + "\nGoods Weight: " + goodsWeight + "\nTotal Weight: " + calculateTotalWeight();
    }

    public String toString()
    {
        return getDetails();
    }
}