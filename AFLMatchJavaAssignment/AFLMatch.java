import java.util.Scanner;
import java.io.*;

public class AFLMatch{
    private AFLTeam homeTeam;
    private AFLTeam awayTeam;

    boolean playerCount = false;
    private void loadTeamFromFile(String filename, boolean isHomeTeam) throws IOException{

        int count = 0;

        File teamFile = new File(filename); 

        Scanner team = new Scanner(teamFile);

        String teamName = team.nextLine();
        if(isHomeTeam)
        {
            homeTeam = new AFLTeam(teamName);
        }
        else{
            awayTeam = new AFLTeam(teamName);
        }

        for (int c=0; c<1; c++){ // coach loop
            String coachName = team.nextLine();
            String position = coachName.substring(coachName.indexOf(","));
            position = position.replace(", ","");
            coachName = coachName.substring(0,coachName.indexOf(","));

            AFLTeamMember coach = new AFLTeamMember(coachName,position);
        }

          while(team.hasNextLine()) // player loadup
          {
            String playerFull = team.nextLine();

            String[] sp = playerFull.split(", ",4);
            String number = sp[0];

            String fullName = sp[1];
            String[] name = fullName.split(" ",2);
            String first = name[0];
            String last = name[1];

            String position = sp[2];     

            boolean captain=false;
            if(sp.length==4){
                if(sp[3].equals("c")){
                    captain=true;
                }
            }

            count = count + 1; // number of players should be 22

            if (count <= 22){
            AFLPlayer player = new AFLPlayer(number, fullName, position, captain);
            }
        }
        if (count != 22){
            System.out.println("ERROR: The number of players is not equal to 22. Match is unable to start");
            playerCount=true;
        }
    }
    
    public static void main (String[] args) throws IOException {
        if (args.length!=2){
            System.out.println("Exactly two command line arguments expected, 0 provided.");
        }
        else{
            String ht = args[0];
            String at = args[1];

            AFLMatch match = new AFLMatch();

            match.loadTeamFromFile(ht,true);
            match.loadTeamFromFile(at,false);

            int homeGoals = 0;
            int homeBehinds = 0;
            int awayGoals = 0;
            int awayBehinds = 0;

            int totalGoalHome = 0;
            int totalBehindHome = 0;
            int totalGoalAway = 0;
            int totalBehindAway = 0;

            int totalScoreHome = homeGoals + homeBehinds;
            int totalScoreAway = awayGoals + awayBehinds;

            boolean homeWinner = false;

            boolean gameOn = true;

            if (gameOn && !match.playerCount) {
                System.out.println("GAME START");
            }

            while (gameOn && !match.playerCount){
                Scanner keyboard = new Scanner(System.in);  

                System.out.println("Which team scored?");
                String teamScored = keyboard.nextLine(); 

                if (totalScoreHome > totalScoreAway){
                    homeWinner = true;
                }

                if (teamScored.equals("f")){
                    System.out.println("FULL TIME");
                    if (totalScoreHome == totalScoreAway){
                    
                        System.out.println(match.homeTeam.getName()+ " " + totalGoalHome + "." + totalBehindHome + " (" + totalScoreHome + ")" + " drew with " + match.awayTeam.getName() + " " + totalGoalAway + "." + totalBehindAway + " (" + totalScoreAway + ")" );
                    }
                    else if (homeWinner == false)  {
                        System.out.println(match.homeTeam.getName()+ " " + totalGoalHome + "." + totalBehindHome + " (" + totalScoreHome + ")" + " was defeated by " + match.awayTeam.getName() + " " +  totalGoalAway + "." + totalBehindAway + " (" + totalScoreAway + ")" );
                    }
                    else if (homeWinner == true){
                        System.out.println(match.homeTeam.getName()+ " " +  totalGoalHome + "." + totalBehindHome + " (" + totalScoreHome + ")" + " defeated " + match.awayTeam.getName() + " " +  totalGoalAway + "." + totalBehindAway + " (" + totalScoreAway + ")" );
                    }
                    break;
                }

                if (teamScored.equals("h")){

                    System.out.println("Goal or behind?");
                    String score = keyboard.nextLine(); 

                    if (score.equals("g")){
                        homeGoals = homeGoals + 6;
                        totalGoalHome = totalGoalHome + 1;
                    }
                    else if (score.equals("b")){
                        homeBehinds = homeBehinds + 1;
                        totalBehindHome = totalBehindHome + 1;
                    }
                    else {
                        System.out.println("INCORRECT INPUT. Try Again");
                    }
                    totalScoreHome = homeGoals + homeBehinds;
                    System.out.println("The current score is " + totalGoalHome + "." + totalBehindHome + " (" + totalScoreHome + ")" + " to "  + totalGoalAway + "." + totalBehindAway + " (" + totalScoreAway + ")" );
                }
                else if (teamScored.equals("a")){

                    System.out.println("Goal or behind?");
                    String score = keyboard.nextLine(); 

                    if (score.equals("g")){
                        awayGoals = awayGoals + 6;
                        totalGoalAway = totalGoalAway + 1;
                    }
                    else if (score.equals("b")) {
                        awayBehinds = awayBehinds + 1;
                        totalBehindAway = totalBehindAway + 1;
                    }
                    else {
                        System.out.println("INCORRECT INPUT. Try Again.");
                    }
                    totalScoreAway = awayGoals + awayBehinds;
                    System.out.println("The current score is " + totalGoalHome + "." + totalBehindHome + " (" + totalScoreHome + ")" + " to "  + totalGoalAway + "." + totalBehindAway + " (" + totalScoreAway + ")" );
                }
                else {
                    System.out.println("INCORRECT INPUT. Try Again.");
                    totalScoreAway = awayGoals + awayBehinds;
                    System.out.println("The current score is " + totalGoalHome + "." + totalBehindHome + " (" + totalScoreHome + ")" + " to "  + totalGoalAway + "." + totalBehindAway + " (" + totalScoreAway + ")" );
                }
            }  
        }           
    }
}
