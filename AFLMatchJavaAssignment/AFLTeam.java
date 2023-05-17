import java.util.Scanner;
import java.io.*;

public class AFLTeam {
    private String name;
    private String coach; 
    private AFLPlayer[] lineup;
        public AFLTeam(String name)
        {
            this.name = name;
        }

        public AFLTeam(String name, String coach, AFLPlayer[] lineup)
        { 
            this.coach = coach;
            lineup = new AFLPlayer[22]; 
        }
        public void addPlayer(AFLPlayer player, AFLPlayer coach)
        {
            for(int i=0;i<lineup.length;i++)
            {
                lineup[i] = player;
            }
        }
        public String getName()
        {
            return name;
        }
        public String toString(){
            String temp = name + "\n" + coach + "\n";
            for(AFLPlayer line : lineup)
            {
                temp += line + "\n";
            }
            return temp;
        }

}