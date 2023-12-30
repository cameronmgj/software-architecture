package pt2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * stores and allows handling of list of competitors in the system
 */

public class CompetitorList {
    ArrayList<GeneralCompetitor> competitors = new ArrayList<GeneralCompetitor>();
    public CompetitorList() {}

    /**
     * reads data from file to table.
     */
    void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("RunCompetitor.csv"));
            String line = reader.readLine();

            while (line != null && !line.isEmpty()) {
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0].trim());
                String fnm = fields[1].trim();
                String lnm = fields[2].trim();
                int age = Integer.parseInt(fields[3].trim());
                String gndr = fields[4].trim();
                String cntry = fields[5].trim();
                String lvl = fields[6].trim();
                if (lvl.equals("novice")) {
                    competitors.add(new Novice(id, cntry, age, fnm, lnm));
                }
                else if (lvl.equals("intermediate")) {
                    competitors.add(new Intermediate(id, cntry, age, fnm, lnm));
                }
                else if (lvl.equals("beginner")) {
                    competitors.add(new Beginner(id, cntry, age, fnm, lnm));
                }
                GeneralCompetitor currentCompetitor  = competitors.get(competitors.size() -1);
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

    /**
     * @return table of data reformatted.
     */
    String createTable() {
        String s = "";
        for (int i = 0; i < competitors.size(); i++) {
            s += (competitors.get(i)).getFullDetails() + "\n";
        }

        s += "\nHighest Overall Scoring Competitor: " + highestScoring().getShortDetails() + "\n";
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

    /**
     * @return highest scoring competitor.
     */
    GeneralCompetitor highestScoring() {
        GeneralCompetitor c = competitors.get(0);
        for (int i = 1; i < competitors.size(); i++) {
            if (competitors.get(i).getOverallScore() > c.getOverallScore()) {
                c = competitors.get(i);
            }
        }
        return c;
    }

    /**
     * @return highest score achieved.
     */
    Double highestScore() {
        GeneralCompetitor c = competitors.get(0);
        Double score = c.getOverallScore();
        for (int i = 1; i < competitors.size(); i++) {
            if (competitors.get(i).getOverallScore() > score) {
                score = competitors.get(i).getOverallScore();
            }
        }
        return score;
    }

    /**
     * @return highest individual game score achieved.
     */
    GeneralCompetitor highestSingleScore() {
        GeneralCompetitor c = competitors.get(0);
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

    /**
     * @return average single score of all competitors.
     */
    Double averageSingleScore() {
        GeneralCompetitor c = competitors.get(0);
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

    /**
     * @return average overall score of all competitors.
     */
    Double averageOverallScore() {
        GeneralCompetitor c = competitors.get(0);
        Double score = 0.0;
        int count = 0;
        for (int i = 0; i < competitors.size(); i++) {
            c = competitors.get(i);
            score += competitors.get(i).getOverallScore();
            count++;
        }
        return score/count;
    }

    /**
     * @param score minimum score to include.
     * @return frequency count of scores above value given.
     */
    int totalScoreFrequency(Double score) {
        int count = 0;
        for (int i = 0; i < competitors.size(); i++) {
            if (competitors.get(i).getOverallScore() > score) {
                count++;
            }
        }
        return count;
    }

    /**
     * @param id competitor id number.
     * @return competitor short details.
     */
    String detailsFromCompNo(int id) {
        int count = 0;
        for (int i = 0; i < competitors.size(); i++) {
            if (competitors.get(i).getCompetitorNumber() == id) {
                return competitors.get(i).getShortDetails();
            }
        }
        return "id does not exist in system";
    }

    /**
     * @param id competitor id number.
     * @return competitor full details.
     */
    String fullDetailsFromCompNo(int id) {
        int count = 0;
        for (int i = 0; i < competitors.size(); i++) {
            if (competitors.get(i).getCompetitorNumber() == id) {
                return competitors.get(i).getFullDetails();
            }
        }
        return "id does not exist in system";
    }
}
