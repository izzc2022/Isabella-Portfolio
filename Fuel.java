import java.util.*;

public class Fuel
{
    public static void main(String[] args)
    {
        int n = 0;
        Scanner fuelAmount = new Scanner(System.in);
        System.out.println("Enter Amount of Litres: ");

        double litres = fuelAmount.nextDouble();
        System.out.println("You entered: " + litres + " L." );

        if (litres > 250) {
            System.out.println("Number of litres is greater than maximum amount (250L).");
        }
        else if (litres < 0) {
            System.out.println("Number of litres is lower than minimum amount (1L).");
        }
        else {
            while (n <= litres){
                double total = n * 1.75;
                System.out.println(total);
                n = n + 1;
            }
            double finalAnswer = litres * 1.75;
            System.out.println("Final price: $" + finalAnswer );

        }
    }

}
