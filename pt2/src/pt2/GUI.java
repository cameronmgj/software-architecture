package pt2;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.swing.*;

public class GUI extends JFrame  implements ActionListener
{
    CompetitorList compList = new CompetitorList();

    JTextField result;
    JTextField searchField;
    JButton search, shortDeets, fullDeets, editDeets, removeComp, addScore;
    JScrollPane scrollList;
    JButton showListById, showListByName, close, filterByLvl, filterByCntry;
    JTextArea displayList;

    public GUI()
    {
        //set up window title
        setTitle("competitors");
        //disable standard close button
        setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);

        setupSouthPanel();
        setupNorthPanel();
        setupCenterPanel();

        compList.readFile();
        String table = compList.createTable();

        //pack and set visible
        pack();
        setVisible(true);

    }

    private void setupCenterPanel() {
        displayList = new JTextArea(15,20);
        displayList.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
        displayList.setEditable(false);
        scrollList = new JScrollPane(displayList);
        this.add(scrollList,BorderLayout.CENTER);
    }

    private void setupSouthPanel() {
        //search panel contains label, text field and button
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1,3));
        searchPanel.add(new JLabel("Enter ID"));
        searchField = new JTextField(5);
        searchPanel.add(searchField);
        search = new JButton("Search");
        searchPanel.add(search);
        //specify action when button is pressed
        search.addActionListener(this) ;



        shortDeets = new JButton("Short Details");
        searchPanel.add(shortDeets);
        //specify action when button is pressed
        shortDeets.addActionListener(this) ;

        fullDeets = new JButton("Full Details");
        searchPanel.add(fullDeets);
        //specify action when button is pressed
        fullDeets.addActionListener(this) ;

        editDeets = new JButton("Edit Level");
        searchPanel.add(editDeets);
        //specify action when button is pressed
        editDeets.addActionListener(this) ;

        removeComp = new JButton("Remove");
        searchPanel.add(removeComp);
        //specify action when button is pressed
        removeComp.addActionListener(this) ;

        addScore = new JButton("Add Score");
        searchPanel.add(addScore);
        //specify action when button is pressed
        addScore.addActionListener(this) ;



        //Set up the area where the results will be displayed.
        result= new JTextField(25);
        result.setEditable(false);

        //set up south panel containing 2 previous areas
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2,1));
        southPanel.add(searchPanel);
        southPanel.add(result);

        //add south panel to the content pane
        this.add(southPanel, BorderLayout.SOUTH);
    }

    private void setupNorthPanel() {
        //add north panel containing some buttons
        JPanel northPanel = new JPanel();
        showListById = new JButton("List By ID");
        showListById.addActionListener(this);

        showListByName = new JButton("List By Name");
        showListByName.addActionListener(this);

        filterByLvl = new JButton("Filter by Level");
        filterByLvl.addActionListener(this);

        filterByCntry = new JButton("Filter by Country");
        filterByCntry.addActionListener(this);

        close = new JButton("Close");
        close.addActionListener(this);

        northPanel.add (showListById);
        northPanel.add(showListByName);
        northPanel.add (filterByLvl);
        northPanel.add(filterByCntry);
        northPanel.add(close);
        this.add(northPanel, BorderLayout.NORTH);
    }

    //come here when button is clicked
    //find which button and act accordingly
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == search) {
            search();
        }
        else if (e.getSource() == shortDeets) {
            int id = Integer.parseInt(searchField.getText().trim());
            result.setText(compList.detailsFromCompNo(id));
        }
        else if (e.getSource() == fullDeets) {
            int id = Integer.parseInt(searchField.getText().trim());
            result.setText(compList.fullDetailsFromCompNo(id));
        }
        else if (e.getSource() == editDeets) {
            int id = Integer.parseInt(searchField.getText().trim());
            for (int i=0; i < compList.competitors.size(); i++) {
                if (compList.competitors.get(i).getID() == id) {
                    String l = JOptionPane.showInputDialog("New Level: ");
                    compList.competitors.get(i).setLevel(l);
                    break;
                }
            }
        }
        else if (e.getSource() == removeComp) {
            int id = Integer.parseInt(searchField.getText().trim());
            for (int i=0; i < compList.competitors.size(); i++) {
                if (compList.competitors.get(i).getID() == id) {
                    compList.competitors.remove(i);
                    break;
                }
            }
        }
        else if (e.getSource() == addScore) {
            int id = Integer.parseInt(searchField.getText().trim());
            for (int i=0; i < compList.competitors.size(); i++) {
                if (compList.competitors.get(i).getID() == id) {
                    compList.competitors.get(i).addScore(Double.parseDouble(JOptionPane.showInputDialog("Add Score: ")));
                    break;
                }
            }
        }
        else if (e.getSource() == showListById) {
            CompetitorList tempList = new CompetitorList();
            for (GeneralCompetitor c : compList.competitors) {
                tempList.competitors.add(c); // Assuming Competitor has a copy constructor
            }
            tempList.competitors.sort((o1, o2)
                    -> Integer.compare(o1.getCompetitorNumber(), o2.getCompetitorNumber()));
            String table = tempList.createTable();
            displayList.setText(table);
        }
        else if (e.getSource() == showListByName ) {
            CompetitorList tempList = new CompetitorList();
            for (GeneralCompetitor c : compList.competitors) {
                tempList.competitors.add(c); // Assuming Competitor has a copy constructor
            }
            tempList.competitors.sort((o1, o2)
                    -> o1.getFullName().compareTo(o2.getFullName()));
            String table = tempList.createTable();
            displayList.setText(table);
        }
        else if (e.getSource() == filterByLvl ) {
            String inputLvl = JOptionPane.showInputDialog("Level: ");
            CompetitorList tempList = new CompetitorList();
            for (GeneralCompetitor c : compList.competitors) {
                tempList.competitors.add(c); // Assuming Competitor has a copy constructor
            }
            tempList.competitors.removeIf(obj -> !obj.getLevel().equals(inputLvl));
            String table = tempList.createTable();
            displayList.setText(table);
        }
        else if (e.getSource() == filterByCntry ) {
            String inputCntry = JOptionPane.showInputDialog("Country: ");
            CompetitorList tempList = new CompetitorList();
            for (GeneralCompetitor c : compList.competitors) {
                tempList.competitors.add(c); // Assuming Competitor has a copy constructor
            }
            tempList.competitors.removeIf(obj -> !obj.getCountry().equals(inputCntry));
            String table = tempList.createTable();
            displayList.setText(table);
        }
        else if (e.getSource() == close) {
            JOptionPane.showMessageDialog(this,
                    "Closing down ..");
            saveToFile();
            System.exit(0);
        }
    }

    private void search() {
        //get search text and search staff list
        //setting result text
        int searchString = Integer.parseInt(searchField.getText().trim());
        String c = compList.detailsFromCompNo(searchString);
        if (c != null ) {
            result.setText(c.toString());
        }
        else
            result.setText("not found");
    }

    private void saveToFile(){
        String table = compList.createTable();
        try (PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {
            writer.println(table);
        }
        catch (IOException e) {
            System.out.println("error writing to file");
        }
    }

}
