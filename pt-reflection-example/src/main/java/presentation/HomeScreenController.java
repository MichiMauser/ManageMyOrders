package presentation;

import model.Log;
import model.Client;
import model.Porder;
import model.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controls the actions performed on the home screen.
 */
public class HomeScreenController implements ActionListener {

    HomeScreen hs;

    GenericPanel<Client> clientPanel;
    GenericPanel<Product> productPanel;
    GenericPanel<Porder> porderPanel;

    GenericPanel<Log> billPanel;

    /**
     * Constructs a HomeScreenController with a given HomeScreen.
     * @param hs The HomeScreen associated with this controller.
     */
    public HomeScreenController(HomeScreen hs){
        this.hs = hs;
    }

    /**
     * Performs actions based on the user's interaction with the home screen.
     * @param e The ActionEvent representing the user's action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = hs.comboBox.getSelectedItem().toString();
        if(s.equals("Clients")){
            try {
                clientPanel = new GenericPanel<>(Client.class, hs);
                hs.insertBackState = clientPanel;
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
            hs.remove(hs.getCurrPanel());
            hs.displayPanel = clientPanel;
            hs.add(clientPanel);
            hs.repaint();
            hs.revalidate();
        }else if(s.equals("Products")){
            try {
                productPanel = new GenericPanel<>(Product.class, hs);
                hs.insertBackState = productPanel;
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
            hs.remove(hs.getCurrPanel());
            hs.displayPanel = productPanel;
            hs.add(productPanel);
            hs.repaint();
            hs.revalidate();
        }else if(s.equals("Orders")){
            try {
                porderPanel = new GenericPanel<>(Porder.class, hs);
                hs.insertBackState = porderPanel;
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }

            hs.remove(hs.getCurrPanel());
            hs.displayPanel = porderPanel;
            hs.add(porderPanel);
            hs.repaint();
            hs.revalidate();
        }else if(s.equals("Bills")){
            try {
                billPanel = new GenericPanel<>(Log.class, hs);
                hs.insertBackState = billPanel;
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
            hs.remove(hs.getCurrPanel());
            hs.displayPanel = billPanel;
            hs.add(billPanel);
            hs.repaint();
            hs.revalidate();
        }
    }
}