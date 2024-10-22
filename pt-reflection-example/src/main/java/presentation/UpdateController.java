package presentation;

import model.Client;
import model.Porder;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateController<T> implements ActionListener {
    HomeScreen hs;
    GenericPanel<T> genericPanel;
    Class<T> type;
    public UpdateController(HomeScreen hs, GenericPanel<T> genericPanel, Class<T> type){
        this.type = type;
        this.hs = hs;
        this.genericPanel = genericPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
