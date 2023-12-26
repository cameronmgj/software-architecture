package pt2;

import javax.lang.model.type.ArrayType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CompetitorList {
    ArrayList<CMGJCompetitor> competitors = new ArrayList<CMGJCompetitor>();
    public CompetitorList() {
        /*
        CMGJCompetitor c0 = new CMGJCompetitor("novice", 1, "France", 97, "John", "Lee");
        CMGJCompetitor c1 = new CMGJCompetitor("expert", 2, "Spain", 7, "Jo", "Leehn");
        CMGJCompetitor c2 = new CMGJCompetitor("beginner", 3, "France", 64, "Mary", "Tudor");
        competitors.add(c0);
        competitors.add(c1);
        competitors.add(c2);
         */
    }


    void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("RunCompetitor.csv"));
            String line = reader.readLine();

            while (line != null && !line.isEmpty()) {////////////////////////////////////////////////////////////////////////////////////////ITS NOT READING
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0].trim());
                String fnm = fields[1].trim();
                String lnm = fields[2].trim();
                int age = Integer.parseInt(fields[3].trim());
                String gndr = fields[4].trim();
                String cntry = fields[5].trim();
                String lvl = fields[6].trim();

                competitors.add(new CMGJCompetitor(lvl, id, cntry, age, fnm, lnm));
                CMGJCompetitor currentCompetitor  = competitors.get(competitors.size() -1);
                for (int i = 7; i < fields.length; i++) {
                    currentCompetitor.addScore(Integer.parseInt(fields[i]));
                }
                line = reader.readLine();
            }
            reader.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    String createTable() {
        String s = "";
        for (int i = 0; i < competitors.size(); i++) {
            s += Competitor.getFullDetails(competitors.get(i)) + "\n";
        }

        s += "\nHighest Overall Scoring Competitor: " + Competitor.getShortDetails(highestScoring()) + "\n";
        s += "Highest Overall Score: " + highestScore() + "\n";
        s += "Highest Single Score obtained by: " + highestScoring().getFullName() + "\n";
        s += "Average Single Score: " + averageSingleScore() + "\n";
        s += "Average Overall Score: " + averageOverallScore() + "\n";

        s +="\n";
        for (Double i=9.0; i >= 0.0; i--) {
            s += "Frequency of total > " + i.intValue() + ": " + totalScoreFrequency(i) + "\n";
        }
        return s;
    }

    CMGJCompetitor highestScoring() {
        CMGJCompetitor c = competitors.get(0);
        for (int i = 1; i < competitors.size(); i++) {
            if (Competitor.getOverallScore(competitors.get(i)) > Competitor.getOverallScore(c)) {
                c = competitors.get(i);
            }
        }
        return c;
    }

    Double highestScore() {
        CMGJCompetitor c = competitors.get(0);
        Double score = Competitor.getOverallScore(c);
        for (int i = 1; i < competitors.size(); i++) {
            if (Competitor.getOverallScore(competitors.get(i)) > score) {
                score = Competitor.getOverallScore(competitors.get(i));
            }
        }
        return score;
    }


    CMGJCompetitor highestSingleScore() {
        CMGJCompetitor c = competitors.get(0);
        Double highestFound = 0.0;
        for (int i = 0; i < competitors.size(); i++) {
            for (int j = 0; j < c.getScoreArray().size(); j++) {
                if ((Double)competitors.get(i).getScoreArray().get(j) > highestFound) {
                    c = competitors.get(i);
                    highestFound = (Double)competitors.get(i).getScoreArray().get(j);
                }
            }
        }
        return c;
    }

    Double averageSingleScore() {
        CMGJCompetitor c = competitors.get(0);
        Double score = 0.0;
        int scoresCount = 0;
        for (int i = 0; i < competitors.size(); i++) {
            for (int j = 0; j < c.getScoreArray().size(); j++) {
                score += (Double)competitors.get(i).getScoreArray().get(j);
                scoresCount++;
            }
        }
        score = score / scoresCount;
        return score;
    }

    Double averageOverallScore() {
        CMGJCompetitor c = competitors.get(0);
        Double score = 0.0;
        int count = 0;
        for (int i = 0; i < competitors.size(); i++) {
            c = competitors.get(i);
            score += Competitor.getOverallScore(competitors.get(i));
            count++;
        }
        return score/count;
    }

    int totalScoreFrequency(Double score) {
        int count = 0;
        for (int i = 0; i < competitors.size(); i++) {
            if (Competitor.getOverallScore(competitors.get(i)) > score) {
                count++;
            }
        }
        return count;
    }

    String detailsFromCompNo(int id) {
        int count = 0;
        for (int i = 0; i < competitors.size(); i++) {
            if (competitors.get(i).getCompetitorNumber() == id) {
                return Competitor.getShortDetails(competitors.get(i));
            }
        }
        return "id does not exist in system";
    }
}
