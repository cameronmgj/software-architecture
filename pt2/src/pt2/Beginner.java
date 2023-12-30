package pt2;

/**
 * subclass for novice beginner competitors
 */

public class Beginner extends GeneralCompetitor {
    public Beginner(int id, String cntry, int a, String fnm, String lnm) {
        super("beginner", id, cntry, a, fnm, lnm);
    }

    public double getOverallScore() {
        double total = 1;
        for (int i=0; i < getScoreArray().size(); i++) {
            total *= (double) getScoreArray().get(i);
        }
        total = Math.pow(total, 1.0/getScoreArray().size());
        total += 1;
        return total;
    }
    public String getFullDetails() {
        return "Competitor No: " + getCompetitorNumber() + ", Name: " + getFullName() + ", Country: " + getCountry() + ", Level: beginner, Age: " + getAge() + ", Scores: " + getScoreArray() + ", Overall Score: " + getOverallScore() + ".";
    }

}
