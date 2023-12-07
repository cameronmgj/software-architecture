package pt2;
import java.util.ArrayList;
import java.io.*;
public class Competitor {
    private ArrayList<CMGJCompetitor> competitorList = new ArrayList<CMGJCompetitor>();

    public Competitor() {}

    public static void main(String args[]) {
        CMGJCompetitor c0 = new CMGJCompetitor("novice", 1, "France", 97, "John", "Lee");
        CMGJCompetitor c1 = new CMGJCompetitor("expert", 2, "Spain", 7, "Jo", "Leehn");
        CMGJCompetitor c2 = new CMGJCompetitor("beginner", 3, "France", 64, "Mary", "Tudor");

        c0.addScore(3);
        c0.addScore(2);
        c0.addScore(1);
        c0.addScore(4);
        c0.addScore(3);

        c0.joinCategory("race", 1);
        c0.joinCategory("tennis", 17);
        c0.joinCategory("football", 20);
        c0.removeCategory("race");
        System.out.println("age: " + c0.getAge());
        System.out.println("level: " + c0.getLevel());
        System.out.println("lname: " + c0.getLname());
        System.out.println("country: " + c0.getCountry());
        System.out.println("compno.: " + c0.getCompetitorNumber());
        System.out.println("name: " + c0.getFullName());
        System.out.println("cat: " + c0.getChosenCategories());
        System.out.println("fname: " + c0.getFname());
        System.out.println("id: " + c0.getID());
        System.out.println("id for race: " + c0.getIdForCategory("race"));
        System.out.println("id for tennis: " + c0.getIdForCategory("tennis"));
        System.out.println("id for football: " + c0.getIdForCategory("football"));
        System.out.println("scores: " + c0.getScoreArray());
    }

    public double getOverallScore(CMGJCompetitor c) {
        double total = 0;
        for (int i=0; i < c.getScoreArray().size(); i++) {
            total *= (double) c.getScoreArray().get(i);
        }
        total = Math.pow(total, 1/c.getScoreArray().size());
        switch(c.getLevel()) {
            case "novice":
                total += 2;
                break;
            case "beginner":
                total += 1;
                break;
            case "intermediate":
                total += 0.5;
                break;
        }
        return total;
    }
    public String getFullDetails(CMGJCompetitor c) {
        return "Competitor No: " + c.getCompetitorNumber() + ", Name: " + c.getFullName() + ", Country: " + c.getCountry() + ", Level: " + c.getLevel() + ", Age: " + c.getAge() + ", Scores: " + c.getScoreArray() + ", Overall Score: " + getOverallScore(c) + ".";
    }
    public String getShortDetails(CMGJCompetitor c) {
        String initials = "";
        initials = initials + c.getFname().charAt(0) + c.getLname().charAt(0);
        return "CN " + c.getCompetitorNumber() + " (" + initials + ") " + " has overall score " + getOverallScore(c) + ".";
    }
}
