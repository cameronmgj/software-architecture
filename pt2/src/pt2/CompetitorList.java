package pt2;

import javax.lang.model.type.ArrayType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CompetitorList {
    ArrayList<CMGJCompetitor> competitors = new ArrayList<CMGJCompetitor>();
    public CompetitorList() {
        CMGJCompetitor c0 = new CMGJCompetitor("novice", 1, "France", 97, "John", "Lee");
        CMGJCompetitor c1 = new CMGJCompetitor("expert", 2, "Spain", 7, "Jo", "Leehn");
        CMGJCompetitor c2 = new CMGJCompetitor("beginner", 3, "France", 64, "Mary", "Tudor");
        competitors.add(c0);
        competitors.add(c1);
        competitors.add(c2);
    }


    void readFile() {
        try {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader("RunCompetitor.csv"));
            String line = reader.readLine();

            while (line != null) {////////////////////////////////////////////////////////////////////////////////////////ITS NOT READING
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0].trim());
                String fnm = fields[1].trim();
                String lnm = fields[2].trim();
                int age = Integer.parseInt(fields[3].trim());
                String gndr = fields[4].trim();
                String cntry = fields[5].trim();
                String lvl = fields[6].trim();

                competitors.add(new CMGJCompetitor(lvl, id, cntry, age, fnm, lnm));
                CMGJCompetitor currentCompetitor  = competitors.get(competitors.size());
                for (int i = 7; i < fields.length; i++) {
                    currentCompetitor.addScore(Integer.parseInt(fields[i]));
                }
            }
            reader.close();
        }
        catch (Exception e)
        {

        }
    }

    String createTable() {
        String s = "";
        for (int i = 0; i < competitors.size(); i++) {
            s += Competitor.getFullDetails(competitors.get(i)) + "\n";
        }
        return s;
    }
}
