package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.Porder;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonController<T> implements ActionListener {

    GenericPanel<T> panel;
    GenericPanel<Client> cPanel;
    GenericPanel<Porder> poPanel;
    GenericPanel<Product> prPanel;
    HomeScreen hs;
    JPanel insertPanel;
    Class<T> type;

    public ButtonController(GenericPanel<T> genericPanel, HomeScreen hs, JPanel insertPanel, Class<T> type){
        panel = genericPanel;
        this.type = type;
        this.hs = hs;
        this.insertPanel = insertPanel;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(panel.getBack())){
            hs.remove(hs.getCurrPanel());
            hs.setCurrPanel(hs.getLastPanel());
            hs.add(hs.getLastPanel());
            hs.repaint();
            hs.revalidate();
            System.out.println("back");
        }else if( e.getSource().equals(panel.getUpdate())){
            System.out.println("update");
            int selectRow = panel.getDataTable().getSelectedRow();
            DefaultTableModel model = panel.getTableModel();
            List<JTextField> textField;
            if(selectRow != -1){
                textField = panel.getTextFields();
                Object object1;
                Object object2;
                Object object3;
                Object object4;
                switch (type.getSimpleName()) {
                    case "Client":
                        object4 = model.getValueAt(selectRow,0);
                        object1 = model.getValueAt(selectRow,1);
                        object2 = model.getValueAt(selectRow,2);
                        object3 = model.getValueAt(selectRow,3);
                        panel.setId((Integer)object4);
                        textField.get(3).setText((String) object1);
                        textField.get(4).setText((String) object2);
                        String conv = String.valueOf((Integer) object3);
                        textField.get(5).setText((conv));
                        try {
                            cPanel = new GenericPanel<>(Client.class,hs);
                            hs.insertBackState = cPanel;
                        } catch (IllegalAccessException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;

                        case "Product":
                        object4 = model.getValueAt(selectRow,0);
                        object1 = model.getValueAt(selectRow,1);
                        object2 = model.getValueAt(selectRow,2);
                        object3 = model.getValueAt(selectRow,3);
                        panel.setId((Integer)object4);
                        textField.get(3).setText((String) object1);
                        String conv1 = String.valueOf((Double) object2);
                        String conv2 = String.valueOf((Integer) object3);
                        textField.get(4).setText(conv1);
                        textField.get(5).setText(conv2);

                      /*  hs.remove(hs.getCurrPanel());
                        hs.displayPanel = prPanel;
                        hs.add(prPanel);
                        hs.repaint();
                        hs.revalidate();*/
                        break;


                }
            }


            hs.remove(hs.getCurrPanel());
            hs.setCurrPanel(panel.getUpdatePanel());
            hs.add(hs.getCurrPanel());
            hs.repaint();
            hs.revalidate();

        }else if(e.getSource().equals(panel.getInsert())){
            System.out.println("insert");
            hs.remove(hs.getCurrPanel());
            hs.setCurrPanel(panel.getInsertPanel());
            hs.add(hs.getCurrPanel());
            hs.repaint();
            hs.revalidate();
        }else if(e.getSource().equals(panel.getDelete())){
            System.out.println("delete");
            int selectRow = panel.getDataTable().getSelectedRow();
            DefaultTableModel model = panel.getTableModel();
            if(selectRow != -1){
                Object Id = model.getValueAt(selectRow, 0);
                switch (type.getSimpleName()) {
                    case "Client":

                        ClientBLL clientBLL = new ClientBLL();
                        Client client = new Client((Integer) Id);
                        try {
                            clientBLL.delete(client);
                        } catch (IllegalAccessException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            cPanel = new GenericPanel<>(Client.class,hs);
                            hs.insertBackState = cPanel;
                        } catch (IllegalAccessException ex) {
                            throw new RuntimeException(ex);
                        }
                        hs.remove(hs.getCurrPanel());
                        hs.displayPanel = cPanel;
                        hs.add(cPanel);
                        hs.repaint();
                        hs.revalidate();

                        break;
                    case "Product":
                        ProductBLL productBLL = new ProductBLL();
                        Product product = new Product((Integer) Id);
                        try {
                            productBLL.delete(product);
                        } catch (IllegalAccessException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            prPanel = new GenericPanel<>(Product.class, hs);
                            hs.insertBackState = prPanel;
                        } catch (IllegalAccessException ex) {
                            throw new RuntimeException(ex);
                        }
                        hs.remove(hs.getCurrPanel());
                        hs.displayPanel = prPanel;
                        hs.add(prPanel);
                        hs.repaint();
                        hs.revalidate();
                        break;
                    case "Porders":
                        OrderBLL orderBLL = new OrderBLL();
                        Porder porder = new Porder((Integer) Id);
                        try {
                            orderBLL.delete(porder);
                        } catch (IllegalAccessException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            poPanel = new GenericPanel<>(Porder.class, hs);
                            hs.insertBackState = poPanel;
                        } catch (IllegalAccessException ex) {
                            throw new RuntimeException(ex);
                        }
                        hs.remove(hs.getCurrPanel());
                        hs.displayPanel = poPanel;
                        hs.add(poPanel);
                        hs.repaint();
                        hs.revalidate();
                        break;
                }
            }


        }
    }
}
/*
hs.remove(hs.getCurrPanel());
        hs.setLastPanel(hs.getCurrPanel());
        hs.setCurrPanel(panel);
        hs.add(hs.getCurrPanel());
        hs.repaint();
        hs.revalidate();
        System.out.println("back");*/

       /* hs.remove(hs.displayPanel);
        hs.displayPanel = insertPanel;
        hs.add(insertPanel);
        hs.repaint();
        hs.revalidate();*/