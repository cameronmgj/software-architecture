package pt2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TournamentUI {
    CompetitorList compList = new CompetitorList();
    String table;
    private void processTournament(int choice){
      switch (choice) {
            case 1:
                compList.readFile();
                break;
            case 2:
                table = compList.createTable();
                break;
            case 3: //print table
                table = compList.createTable();
                System.out.println(table);
                break;
            case 4: //write table to file
                table = compList.createTable();
                try (PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {
                    writer.println(table);
                } catch (IOException e) {
                    System.out.println("error writing to file");
                }
                break;
            case 5:
                System.out.println(compList.highestScoring().getFullName());
                break;
            case 6:
                System.out.println(compList.highestScore());
                break;
            case 7:
                System.out.println(compList.highestSingleScore().getFullName());
                break;
            case 8:
                System.out.println(compList.averageOverallScore());
                break;
            case 9:
                System.out.println(compList.averageSingleScore());
                break;
            case 10: //get frequency of a score
                System.out.print("enter a score to check the frequency of: ");
                Double score = Double.parseDouble(new Scanner(System.in).nextLine());
                System.out.println(compList.totalScoreFrequency(score));
                break;
            case 11: //get competitor details from their id
                System.out.print("enter a competitor number to check details for: ");
                int id = Integer.parseInt(new Scanner(System.in).nextLine());
                System.out.println(compList.detailsFromCompNo(id));
                break;
        }
    }


    public static void main(String[] args){
        int choice = 10000;
        TournamentUI tournament0 = new TournamentUI();
        while (choice != 0) {
            System.out.println("-------\nmenu\n-------\n 1 - read file\n 2 - create table\n 3 - print table\n 4 - write table to file\n 5 - get competitor with highest individual score\n 6 - get highest overall score\n 7 - get competitor with highest individual score\n 8 - get average overall score\n 9 - get average individual score\n 10 - get frequency of a minimum score\n 11 - get competitor details\n 0 - exit");
            choice = Integer.parseInt(new Scanner(System.in).nextLine());
            tournament0.processTournament(choice);
        }
    }
}
