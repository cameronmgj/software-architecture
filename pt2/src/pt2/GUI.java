package pt2;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;

/**
 * Simple GUI for viewing and light processing
 */

public class GUI extends JFrame  implements ActionListener
{
    CompetitorList compList = new CompetitorList();
    JTextField result;
    JTextField searchField;
    JButton search, shortDeets, fullDeets, editDeets, removeComp, addScore;
    JScrollPane scrollList;
    JButton showListById, showListByName, close, filterByLvl, filterByCntry;
    JTextArea displayList;

    int currentState = 0;
    public GUI()
    {
        setTitle("competitors");
        setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);

        setupSouthPanel();
        setupNorthPanel();
        setupCenterPanel();

        compList.readFile();
        refreshTable();
        pack();
        setVisible(true);
    }

    /**
     * Create the center panel.
     */
    private void setupCenterPanel() {
        displayList = new JTextArea(15,20);
        displayList.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
        displayList.setEditable(false);
        scrollList = new JScrollPane(displayList);
        this.add(scrollList,BorderLayout.CENTER);
    }

    /**
     * Create the bottom panel.
     */
    private void setupSouthPanel() {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1,3));
        searchPanel.add(new JLabel("Enter ID"));
        searchField = new JTextField(5);
        searchPanel.add(searchField);
        search = new JButton("Search");
        searchPanel.add(search);
        search.addActionListener(this) ;

        shortDeets = new JButton("Short Details");
        searchPanel.add(shortDeets);
        shortDeets.addActionListener(this) ;

        fullDeets = new JButton("Full Details");
        searchPanel.add(fullDeets);
        fullDeets.addActionListener(this) ;

        editDeets = new JButton("Edit Level");
        searchPanel.add(editDeets);
        editDeets.addActionListener(this) ;

        removeComp = new JButton("Remove");
        searchPanel.add(removeComp);
        removeComp.addActionListener(this) ;

        addScore = new JButton("Add Score");
        searchPanel.add(addScore);
        addScore.addActionListener(this) ;

        result= new JTextField(25);
        result.setEditable(false);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2,1));
        southPanel.add(searchPanel);
        southPanel.add(result);

        this.add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Create the top panel.
     */
    private void setupNorthPanel() {
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

    /**
     * Handle interrupts from gui.
     * @param e the button clicked.
     */
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
            refreshTable();
        }
        else if (e.getSource() == removeComp) {
            int id = Integer.parseInt(searchField.getText().trim());
            for (int i=0; i < compList.competitors.size(); i++) {
                if (compList.competitors.get(i).getID() == id) {
                    compList.competitors.remove(i);
                    break;
                }
            }
            refreshTable();
        }
        else if (e.getSource() == addScore) {
            int id = Integer.parseInt(searchField.getText().trim());
            for (int i=0; i < compList.competitors.size(); i++) {
                if (compList.competitors.get(i).getID() == id) {
                    compList.competitors.get(i).addScore(Double.parseDouble(JOptionPane.showInputDialog("Add Score: ")));
                    break;
                }
            }
            refreshTable();
        }
        else if (e.getSource() == showListById) {
            currentState = 1;
            refreshTable();
        }
        else if (e.getSource() == showListByName ) {
            currentState = 2;
            refreshTable();
        }
        else if (e.getSource() == filterByLvl ) {
            currentState = 3;
            refreshTable();
        }
        else if (e.getSource() == filterByCntry ) {
            currentState = 4;
            refreshTable();
        }
        else if (e.getSource() == close) {
            JOptionPane.showMessageDialog(this,
                    "Closing down ..");
            saveToFile();
            System.exit(0);
        }
    }

    /**
     * search for competitor by id.
     */
    private void search() {
        int searchString = Integer.parseInt(searchField.getText().trim());
        String c = compList.detailsFromCompNo(searchString);
        if (c != null ) {
            result.setText(c.toString());
        }
        else
            result.setText("not found");
    }

    /**
     * Export the table to file.
     */
    private void saveToFile() {
        String table = compList.createTable();
        try (PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {
            writer.println(table);
        }
        catch (IOException e) {
            System.out.println("error writing to file");
        }
    }

    /**
     * Updates table display (center panel) on gui.
     */
    private void refreshTable() {
        String table;
        CompetitorList tempList = new CompetitorList();
        switch (currentState) {
            case 0:
                table = compList.createTable();
                displayList.setText(table);
                break;
            case 1: //list by id
                for (GeneralCompetitor c : compList.competitors) {
                    tempList.competitors.add(c);
                }
                tempList.competitors.sort((o1, o2)
                        -> Integer.compare(o1.getCompetitorNumber(), o2.getCompetitorNumber()));
                table = tempList.createTable();
                displayList.setText(table);
                break;
            case 2: //list by name
                for (GeneralCompetitor c : compList.competitors) {
                    tempList.competitors.add(c);
                }
                tempList.competitors.sort((o1, o2)
                        -> o1.getFullName().compareTo(o2.getFullName()));
                table = tempList.createTable();
                displayList.setText(table);
                break;
            case 3: //filter by level
                String inputLvl = JOptionPane.showInputDialog("Level: ");
                for (GeneralCompetitor c : compList.competitors) {
                    tempList.competitors.add(c);
                }
                tempList.competitors.removeIf(obj -> !obj.getLevel().equals(inputLvl));
                table = tempList.createTable();
                displayList.setText(table);
                break;
            case 4: //filter by country
                String inputCntry = JOptionPane.showInputDialog("Country: ");
                for (GeneralCompetitor c : compList.competitors) {
                    tempList.competitors.add(c);
                }
                tempList.competitors.removeIf(obj -> !obj.getCountry().equals(inputCntry));
                table = tempList.createTable();
                displayList.setText(table);
                break;
        }
    }

}
