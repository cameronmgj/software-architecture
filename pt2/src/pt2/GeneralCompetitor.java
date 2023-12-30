package pt2;

import java.util.ArrayList;

/**
 * superclass for competitors
 */

abstract class GeneralCompetitor {
    private String level;
    private int competitorNumber;
    private String country;
    private int age;
    private String fname;
    private String lname;
    private ArrayList<String> chosenCategories = new ArrayList<String>();
    private ArrayList<Integer> categoryIDs = new ArrayList<Integer>();
    private ArrayList<Double> scoreList = new ArrayList<Double>();

    public GeneralCompetitor(String lvl, int id, String cntry, int a, String fnm, String lnm) {
        level = lvl;
        competitorNumber = id;
        country = cntry;
        age = a;
        fname = fnm;
        lname = lnm;
    }

    /**
     * same as getcompetitornumber() - deprecated
     * @return competitor id number.
     */
    public int getID() {
        return competitorNumber;
    }

    /**
     * @return competitor id number.
     */
    public int getCompetitorNumber(){
        return competitorNumber;
    }

    /**
     * @return competitor country.
     */
    public String getCountry() { return country; }

    /**
     * @return competitor age.
     */
    public int getAge() { return age; }

    /**
     * @return competitor level.
     */
    public String getLevel(){
        return level;
    }

    /**
     * @param l competitor new level.
     */
    public void setLevel(String l){
        level = l;
    }

    /**
     * @return competitor first name.
     */
    public String getFname() { return fname; }

    /**
     * @return competitor last name.
     */
    public String getLname() {
        return lname;
    }

    /**
     * @return competitor full name.
     */
    public String getFullName() {
        return fname + " " + lname;
    }

    /**
     * @param cat category to join.
     * @param id competitor id no.
     */
    public void joinCategory(String cat, int id) {
        chosenCategories.add(cat);
        categoryIDs.add((Integer) id);
    }

    /**
     * @param cat category to remove.
     */
    public void removeCategory(String cat) {
        for (int i=0; i < chosenCategories.size(); i++) {
            if (chosenCategories.get(i) == cat) {
                chosenCategories.remove(i);
                categoryIDs.remove(i);
            }
        }
    }

    /**
     * @return competitor's category list.
     */
    public ArrayList getChosenCategories(){
        return chosenCategories;
    }

    /**
     * @return id for a category.
     */
    public int getIdForCategory(String cat){
        int id = 0;
        for (int i=0; i < chosenCategories.size(); i++) {
            if (chosenCategories.get(i) == cat) {
                id = categoryIDs.get(i);
            }
        }
        return id;
    }

    /**
     * @param s new score to add.
     */
    public void addScore(double s) {
        scoreList.add((Double) s);
    }

    /**
     * @return competitor score list.
     */
    public ArrayList getScoreArray(){
        return scoreList;
    }

    /**
     * @return competitor total score.
     */
    public abstract double getOverallScore();

    /**
     * @return competitor full details.
     */
    abstract String getFullDetails();

    /**
     * @return competitor brief details.
     */
    public String getShortDetails() {
        String initials = "";
        initials = initials + getFname().charAt(0) + getLname().charAt(0);
        return "CN " + getCompetitorNumber() + " (" + initials + ") " + " has overall score " + getOverallScore() + ".";
    }
}
