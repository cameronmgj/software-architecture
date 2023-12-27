package pt2;

import java.util.ArrayList;

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

    public int getID() {
        return competitorNumber;
    }
    public int getCompetitorNumber(){
        return competitorNumber;
    }
    public String getCountry() { return country; }
    public int getAge() { return age; }
    public String getLevel(){
        return level;
    }
    public String getFname() { return fname; }
    public String getLname() {
        return lname;
    }
    public String getFullName() {
        return fname + " " + lname;
    }
    public void joinCategory(String cat, int id) {
        chosenCategories.add(cat);
        categoryIDs.add((Integer) id);
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
        int id = 0;
        for (int i=0; i < chosenCategories.size(); i++) {
            if (chosenCategories.get(i) == cat) {
                id = categoryIDs.get(i);
            }
        }
        return id;
    }
    public void addScore(double s) {
        scoreList.add((Double) s);
    }
    public ArrayList getScoreArray(){
        return scoreList;
    }

    public abstract double getOverallScore();
    abstract String getFullDetails();
    public String getShortDetails() {
        String initials = "";
        initials = initials + getFname().charAt(0) + getLname().charAt(0);
        return "CN " + getCompetitorNumber() + " (" + initials + ") " + " has overall score " + getOverallScore() + ".";
    }
}
