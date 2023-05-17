import java.util.Scanner;
import java.io.*;

public class AFLTeamMember {
	protected String name;
	protected String position;

    public void setName(String name){
        this.name=name;
    }
    public void setPosition(String position){

        String[] position_array = new String[8];
        position_array[0] = "FB";
        position_array[1] = "HB";
        position_array[2] = "C";
        position_array[3] = "HF";
        position_array[4] = "FF";
        position_array[5] = "FOL";
        position_array[6] = "IC";
        position_array[7] = "COACH";

        boolean check = false;
        for (int p = 0; p < 8; p++) {
            if (position_array[p].equals(position)){
                check = true;
                this.position=position;
            }
        }
        if (check == false) {
            System.out.println("ERROR: Position is invalid. Please try again.");
        }

    }
    public AFLTeamMember(String name, String position)
    {
       setName(name);
	   setPosition(position);
    }
    public String toString(){
        return (name + ", " + position);
    }
}
