/*
 * Class Name:    Tester
 *
 * Author:        Your Name
 * Creation Date: Friday, March 10 2023, 17:16 
 * Last Modified: Friday, March 10 2023, 17:17
 * 
 * Class Description:
 *
 */
import java.util.*;
public class Tester
{

   public static void displayAllCars(RailroadCar[] train, int carCount)
   {
      int passengers = 0;
      for (int i = 0; i < carCount; ++i) {
         System.out.println(train[i] + "\n");
         if (train[i].getClass().getName().equals("PassengerCarriages"))
         {
            PassengerCarriages temp = (PassengerCarriages) train[i];
            passengers += temp.getNumberOfPassengers();
         }
      }
      System.out.print("Number of Total Passengers: "+ passengers);
   }
   public static void main(String[] args)
   {
      RailroadCar[] train = new RailroadCar[5];
      int carCount = 0;
      train[carCount++] = new UnclassifiedOnes("Thomas", 3.5);
      train[carCount++] = new RefrigeratedUnits("Bill", 11, 4,5);
      train[carCount++] = new GoodsCar("Bob", 9, 7);
      train[carCount++] = new PassengerCarriages("Sandy", 0.76, 14);
      train[carCount++] = new PassengerCarriages("Sally", 0.3, 14);

      System.out.println("");
      // Add code to perform the required tasks
      // Add or modify data if necessary
      displayAllCars(train,carCount);
   }
}

