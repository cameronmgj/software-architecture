import java.util.ArrayList;

public class Competitor {
    private ArrayList<CMGJCompetitor> competitorList;


    public void main() {
        CMGJCompetitor c0 = new CMGJCompetitor("novice", 0, "France", 97, "John", "Lee");
        CMGJCompetitor c1 = new CMGJCompetitor("expert", 1, "Spain", 7, "Jo", "Leehn");
        CMGJCompetitor c2 = new CMGJCompetitor("beginner", 2, "France", 64, "Mary", "Tudor");

        competitorList.add(c0);
        competitorList.add(c1);
        competitorList.add(c2);
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
        return "Competitor No: " + c.getCompetitorNumber() + ", Name: " + c.getFullName() + ", Country: " + c.getCountry() + ", Level: " + c.getLevel() + ", Overall Score: " + getOverallScore(c) + ".";
    }
    public String getShortDetails(CMGJCompetitor c) {
        String initials = "";
        initials = initials + c.getFname().charAt(0) + c.getLname().charAt(0);
        return "CN " + c.getCompetitorNumber() + " (" + initials + ") " + " has overall score " + getOverallScore(c) + ".";
    }
}
