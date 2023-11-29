import java.util.ArrayList;
public class CMGJCompetitor {
    private String level;
    private int competitorNumber;
    private String country;
    private int age;
    private String fname;
    private String lname;
    private ArrayList<String> chosenCategories;
    private ArrayList<Integer> categoryIDs;
    private ArrayList<Double> scoreList;

    public CMGJCompetitor(String lvl, int id, String cntry, int a, String fnm, String lnm) {
        level = lvl;
        competitorNumber = id;
        country = cntry;
        age = a;
        fname = fnm;
        lname = lnm;
    }

    public int getID() {
        return 0;
    }
    public int getCompetitorNumber(){
        return competitorNumber;
    }
    public String getCountry() { return country; }
    public int getAge() { return age; }
    public String getLevel(){
        return level;
    }
    public String getFname() {
        return fname;
    }
    public String getLname() {
        return lname;
    }
    public String getFullName() {
        return fname + " " + lname;
    }
    public void joinCategory(String cat, int id) {
        chosenCategories.add(cat);
        categoryIDs.add(id);
    }
    public void removeCategory(String cat) {
        for (int i=0; i < chosenCategories.size(); i++) {
            if (chosenCategories.get(i) == cat) {
                chosenCategories.remove(i);
                categoryIDs.remove(i);
            }
        }
    }
    public ArrayList getChosenCategories(){
        return chosenCategories;
    }
    public int getIdForCategory(String cat){
        return 0;
    }
    public void addScore(double s) {
        scoreList.add(s);
    }
    public ArrayList getScoreArray(){
        return scoreList;
    }
}