import java.util.Scanner;
import java.io.*;

public class AFLPlayer extends AFLTeamMember{
    private String number;
    private boolean captain;

    public AFLPlayer(String number, String name, String position, boolean captain)
    { 
        super(name,position);
        int integer = Integer.valueOf(number);
        if (integer >= 0){
            this.number = number; 
        }
        else {
            System.out.println("ERROR: The given player number is NOT a valid positive number.");
        }
        if (captain == true){
            this.captain=captain;
        
        }
    }
    public String getNumber()
    {
        return number;
    }
    public String toString(){
        String temp = "[" + number + "]" + " " +name + ", " + position + " " ;
        if(captain==true)
        {
            temp += " (c)";
        }
        return temp;
    }
}
