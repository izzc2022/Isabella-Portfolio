
// By Isabella C - Java Assignment 1, 2022,

/* NOTE TO USER: Some of the code that was used for testing and verification of methods and lines has been commented out in order to show
developer thought processes and original work. Other comments have also been made in areas where code needed to be described or further explained */ 

import java.util.Scanner;
import java.io.*;

public class HexaicosadecimalNumber {

	private String stringRepresentation;
	private double doubleRepresentation;

    public HexaicosadecimalNumber(String stringRepresentation, double doubleRepresentation)
    {
        this.stringRepresentation = stringRepresentation;
	    this.doubleRepresentation = doubleRepresentation;
    }

    public double hexaicosadecimalStringToDouble(String stringRepresentation)
	{
        double accumulator = 0;
        // int length = stringRepresentation.length(); 
        // System.out.println("length is" + " " + length);
        String nonFractional="";
        boolean isNeg = stringRepresentation.startsWith("-"); // boolean setting for negative value
        stringRepresentation = stringRepresentation.replace("-",""); // finds and replaces the minus ('-') symbol in order to perform calculations
        if(stringRepresentation.contains("."))
        {
        nonFractional = stringRepresentation.substring(0,stringRepresentation.indexOf(".")); // splits string and works with the portion before the decimal place ('.')
        }
        else{
            nonFractional = stringRepresentation;
        }
        for (int i = 0; i<nonFractional.length(); i++) {
            int baseValue = (nonFractional.charAt(i) - 'a');
            accumulator = accumulator + baseValue * Math.pow(26, nonFractional.length() - i - 1);

        }
        if (stringRepresentation.contains(".")){ // splits string and works with the portion after the decimal place ('.')
            int halfIndex = stringRepresentation.indexOf(".");
            String half = stringRepresentation.substring(halfIndex+1);
               for (int i = 0; i<half.length(); i++) {
                    int baseValue = (half.charAt(i) - 'a');
                    accumulator = accumulator + baseValue * Math.pow(26, (- i - 1));
                   
                    
                }
        }
        if(isNeg==true) // checks if string was negative, based on the previous boolean setting
        {
            accumulator = accumulator * -1; 
        }
        return accumulator;
	}

	public String doubleToHexaicosadecimalString(double doubleRepresentation)
	{
        boolean isNeg = doubleRepresentation < 0; // boolean setting for negative value
        double doubleRepresentationAbs = Math.abs(doubleRepresentation);
        int part1 = (int)doubleRepresentationAbs;
        int temp = part1;
        // System.out.println("ABSOLUTE: " + doubleRepresentationAbs);
        String stringAccumulator1 = "";

        while (temp > 0) {
            int currValue = temp % 26;
            char ascii = (char)(currValue + 'a');
            //System.out.println("character");
            //System.out.println(ascii);
            stringAccumulator1 = ascii + stringAccumulator1 ;
            temp = temp/26 ;
        }

        if (doubleRepresentationAbs > part1)
        {

            stringAccumulator1 = stringAccumulator1 + ".";
            double fractionPart = doubleRepresentationAbs - part1;
            int dp = 6; 
            while(dp > 0)
            {
                fractionPart = fractionPart * 26;
                int intPart = (int) fractionPart;
                fractionPart = fractionPart-intPart;
                char ascii = (char)('a' + intPart);
                // System.out.println("character");
                // System.out.println(ascii);
                stringAccumulator1 = stringAccumulator1 + ascii ;
                dp = dp - 1;
            }
        }

        if(isNeg==true)
        {
            stringAccumulator1 = "-" + stringAccumulator1 ;
        }

        return stringAccumulator1 + " (" + doubleRepresentation + ")"  ;
        
	}

    public static void main(String[] args) throws IOException {

        Scanner keyboard = new Scanner(System.in);  
        int loop = 1; // boolean setting of loop in the ON position
        while (loop == 1){
            System.out.println("Please enter 'h' to convert from hexaicosadecmial to decimal, or 'd' to convert from decimal, or 'q' to quit:");
            String mode = keyboard.nextLine(); 
            mode = mode.toLowerCase(); 
        
            if (mode.equals("h")) {
                System.out.println("Please enter hexaicosadecmial value:");
                String inputHex = keyboard.nextLine(); 
                inputHex = inputHex.toLowerCase(); 
                HexaicosadecimalNumber stringToDec = new HexaicosadecimalNumber(inputHex, 0);
                System.out.println(inputHex.toString() + " (" + stringToDec.hexaicosadecimalStringToDouble(inputHex) + ")"); // use of the toString() method to print integer value
            }
            else if (mode.equals("d")) {
                System.out.println("Please enter decimal value:");
                double inputDec = keyboard.nextDouble(); 
                keyboard.nextLine(); //clear /n 
                HexaicosadecimalNumber decToString = new HexaicosadecimalNumber("", inputDec);
                System.out.println(decToString.doubleToHexaicosadecimalString(inputDec));
            }
            else if (mode.equals("q")) {
                System.out.println("You have chosen to exit the program. See you next time!");
                loop = 0; // boolean setting of loop in the OFF position
            }
            else {
                System.out.println("INCORRECT INPUT");
            }
            // System.out.println("mode selected is: " + mode);   

        }
    }   
}
