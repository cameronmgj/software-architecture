package pt2;

public class Novice extends GeneralCompetitor {
    public Novice(int id, String cntry, int a, String fnm, String lnm) {
        super("novice", id, cntry, a, fnm, lnm);
    }

    public double getOverallScore() {
        double total = 1;
        for (int i=0; i < getScoreArray().size(); i++) {
            total *= (double) getScoreArray().get(i);
        }
        total = Math.pow(total, 1.0/getScoreArray().size());
        total += 2;
        return total;
    }
    public String getFullDetails() {
        return "Competitor No: " + getCompetitorNumber() + ", Name: " + getFullName() + ", Country: " + getCountry() + ", Level: novice, Age: " + getAge() + ", Scores: " + getScoreArray() + ", Overall Score: " + getOverallScore() + ".";
    }

}
