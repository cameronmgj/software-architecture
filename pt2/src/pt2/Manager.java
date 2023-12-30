package pt2;

public class Manager {
    private void showGUI() {
        GUI gui = new GUI();
        gui.setVisible(true);
    }
    public static void main(String[] args) {
        Manager m0 = new Manager();
        m0.showGUI();
    }
}