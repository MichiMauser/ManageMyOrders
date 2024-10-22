package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the main screen of the management system.
 */
public class HomeScreen extends JFrame {

    JButton viewBut = new JButton("View Table");
    String[] op = {"Clients", "Products", "Orders", "Bills"};
    JComboBox<String> comboBox = new JComboBox<>(op);
    JPanel displayPanel = new JPanel(new GridBagLayout());
    JPanel lastPanel = displayPanel;
    JPanel insertBackState;
    HomeScreenController action = new HomeScreenController(this);

    /**
     * Constructs the HomeScreen with default settings and components.
     */
    public HomeScreen() {
        this.setTitle("Management System");
        this.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.gridx = 0;
        cons.gridy = 0;
        cons.insets = new Insets(10,10,10,10);

        displayPanel.add(comboBox, cons);
        cons.gridy = 1;
        viewBut.addActionListener(action);
        displayPanel.add(viewBut, cons);
        add(displayPanel);

        this.setSize(800,600);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Retrieves the last displayed panel.
     * @return The last displayed panel.
     */
    public JPanel getLastPanel() {
        return lastPanel;
    }

    /**
     * Sets the last displayed panel.
     * @param panel The panel to set as the last displayed panel.
     */
    public void setLastPanel(JPanel panel){
        lastPanel = panel;
    }

    /**
     * Retrieves the current displayed panel.
     * @return The current displayed panel.
     */
    public JPanel getCurrPanel() {
        return displayPanel;
    }

    /**
     * Sets the current displayed panel.
     * @param panel The panel to set as the current displayed panel.
     */
    public void setCurrPanel(JPanel panel){
        displayPanel = panel;
    }
}