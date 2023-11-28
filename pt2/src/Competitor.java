import java.util.ArrayList;

public class Competitor {
    private ArrayList<CMGJCompetitor> competitorList;


    public Competitor() {

    }

    public double getOverallScore(CMGJCompetitor c) {
        double total = 0;
        for (int i=0; i < c.getScores().size(); i++) {
            total += (double) c.getScores().get(i);
        }
        return total;
    }
    public String getFullDetails(CMGJCompetitor c) {
        return "Competitor No: " + c.getCompetitorNumber() + ", Name: " + c.getFullName() + ", Country: " + c.getCountry() + ", Level: " + c.getLevel() + ", Overall Score: " + getOverallScore(c) + ".";
    }
    public String getShortDetails(CMGJCompetitor c) {
        String initials = "";
        initials = initials + c.getFname().charAt(0) + c.getLname().charAt(0);
        return "CN " + c.getCompetitorNumber() + " (" + initials + ") " + " has overall score " + getOverallScore(c) + ".";
    }
}
