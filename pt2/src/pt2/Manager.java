package pt2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Integer;

public class Manager {
    public static void main(String[] args) {
        CompetitorList compList = new CompetitorList();
        compList.readFile();
        System.out.println(compList.createTable());
        System.out.println("grra");
    }
}