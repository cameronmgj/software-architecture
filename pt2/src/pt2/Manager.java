package pt2;

import java.lang.Integer;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Manager {
    public static void main(String[] args) {
        CompetitorList compList = new CompetitorList();
        compList.readFile();

        String table = compList.createTable();
        System.out.println(table);

        try (PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {
            writer.println(table);
        } catch (IOException e) {
            System.out.println("error writing to file");
        }

        System.out.print("enter a competitor number: ");
        int id = Integer.parseInt(new Scanner(System.in).nextLine());
        System.out.println(compList.detailsFromCompNo(id));

        // The string to write to the file
        String content = "hello";
    }
}