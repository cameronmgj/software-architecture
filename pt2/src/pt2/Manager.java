package pt2;

/**
 * runs the gui
 */

public class Manager {
    /**
     * displays the gui on screen.
     */
    private void showGUI() {
        GUI gui = new GUI();
        gui.setVisible(true);
    }
    public static void main(String[] args) {
        Manager m0 = new Manager();
        m0.showGUI();
    }
}