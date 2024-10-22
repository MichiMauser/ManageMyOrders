package presentation;



import bll.LogBLL;
import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;

import model.Client;
import model.Log;
import model.Porder;
import model.Product;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class InsertController<T> implements ActionListener {
    HomeScreen hs;
    GenericPanel<T> genericPanel;
    Class<T> type;
    public InsertController(HomeScreen hs, GenericPanel<T> genericPanel, Class<T> type)
    {
        this.type = type;
        this.hs = hs;
        this.genericPanel = genericPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        GenericPanel<Product> productGenericPanel;
        GenericPanel<Porder> porderGenericPanel;
        GenericPanel<Client> clientGenericPanel;
        if(e.getSource().equals(genericPanel.getAddButton())){
            System.out.println("add");
            List<JTextField> textFieldList = genericPanel.getTextFields();
            List<String> toInsert = new ArrayList<>();
            switch (type.getSimpleName()) {
                case "Product": {
                    for (JTextField t : textFieldList) {
                        toInsert.add(t.getText());

                    }

                   double valueD = Double.parseDouble(toInsert.get(1));
                    int valueI = Integer.parseInt(toInsert.get(2));
                    Product product = new Product(toInsert.get(0), valueD, valueI);
                    ProductBLL productBLL = new ProductBLL();
                    try {
                        try {
                            productBLL.insert(product);
                            try {
                                GenericPanel<Product> genPan = new GenericPanel<>(Product.class,hs);
                                hs.remove(hs.getCurrPanel());
                                hs.setCurrPanel(genPan);
                                hs.add(hs.getCurrPanel());
                                hs.repaint();
                                hs.revalidate();
                            } catch (IllegalAccessException ex) {
                                throw new RuntimeException(ex);
                            }
                        }catch (IllegalArgumentException exec){
                            JOptionPane.showMessageDialog(new Frame(), exec.getMessage());
                        }
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }



                    break;
                }
                case "Client": {

                    for (JTextField t : textFieldList) {
                        toInsert.add(t.getText());
                    }
                    int valueI = Integer.parseInt(toInsert.get(2));
                    Client client = new Client(toInsert.get(0), toInsert.get(1), valueI);
                    ClientBLL clientBLL = new ClientBLL();
                    try {
                        try {
                            clientBLL.insert(client);
                            try {
                                GenericPanel<Client> genPan = new GenericPanel<>(Client.class,hs);
                                hs.remove(hs.getCurrPanel());
                                hs.setCurrPanel(genPan);
                                hs.add(hs.getCurrPanel());
                                hs.repaint();
                                hs.revalidate();
                            } catch (IllegalAccessException ex) {
                                throw new RuntimeException(ex);
                            }
                        }catch (IllegalArgumentException exce){
                            JOptionPane.showMessageDialog(new Frame(), exce.getMessage());
                        }
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }

                    break;
                }
                case "Porder":

                    for (JTextField t : textFieldList) {
                        toInsert.add(t.getText());
                    }
                    int v1 = Integer.parseInt(toInsert.get(0));
                    int v2 = Integer.parseInt(toInsert.get(1));
                    int v3 = Integer.parseInt(toInsert.get(2));
                    Porder porder = new Porder(v1, v2, v3);
                    Client client;
                    Product product;
                    ClientBLL clientBLL = new ClientBLL();
                    ProductBLL productBLL = new ProductBLL();
                    client = clientBLL.findById(v1);
                    product = productBLL.findById(v2);
                    OrderBLL orderBLL = new OrderBLL();

                    LogBLL billBLL = new LogBLL();
                    Log log = new Log(client.getFullName(), product.getName(), product.getPrice() * v3);
                    try {
                        try {
                            orderBLL.makeOrder(porder);
                            billBLL.insert(log);
                            try {
                                GenericPanel<Porder> genPan = new GenericPanel<>(Porder.class,hs);
                                hs.remove(hs.getCurrPanel());
                                hs.setCurrPanel(genPan);
                                hs.add(hs.getCurrPanel());
                                hs.repaint();
                                hs.revalidate();
                            } catch (IllegalAccessException ex) {
                                throw new RuntimeException(ex);
                            }
                        }catch(IllegalArgumentException | NoSuchElementException exec){
                            JOptionPane.showMessageDialog(null, exec.getMessage());
                        }
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }

                    break;
            }

        }
        else if(e.getSource().equals(genericPanel.getBackFromInsert())){
            System.out.println("back from insert");

            switch (type.getSimpleName()) {
                case "Product":
                    try {
                        productGenericPanel = new GenericPanel<>(Product.class, hs);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                    hs.remove(hs.getCurrPanel());
                    hs.setCurrPanel(productGenericPanel);
                    hs.add(hs.getCurrPanel());
                    hs.repaint();
                    hs.revalidate();
                    break;
                case "Client":
                    try {
                        clientGenericPanel = new GenericPanel<>(Client.class, hs);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                    hs.remove(hs.getCurrPanel());
                    hs.setCurrPanel(clientGenericPanel);
                    hs.add(hs.getCurrPanel());
                    hs.repaint();
                    hs.revalidate();
                    break;
                case "Porder":
                    try {
                        porderGenericPanel = new GenericPanel<>(Porder.class, hs);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                    hs.remove(hs.getCurrPanel());
                    hs.setCurrPanel(porderGenericPanel);
                    hs.add(hs.getCurrPanel());
                    hs.repaint();
                    hs.revalidate();
                    break;
            }
        }else if(e.getSource().equals(genericPanel.getBackFromUpdate())){
            System.out.println("back from update");
            switch (type.getSimpleName()) {
                case "Product":
                    try {
                        productGenericPanel = new GenericPanel<>(Product.class, hs);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                    hs.remove(hs.getCurrPanel());
                    hs.setCurrPanel(productGenericPanel);
                    hs.add(hs.getCurrPanel());
                    hs.repaint();
                    hs.revalidate();
                    break;
                case "Client":
                    try {
                        clientGenericPanel = new GenericPanel<>(Client.class, hs);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                    hs.remove(hs.getCurrPanel());
                    hs.setCurrPanel(clientGenericPanel);
                    hs.add(hs.getCurrPanel());
                    hs.repaint();
                    hs.revalidate();
                    break;
                case "Porder":
                    try {
                        porderGenericPanel = new GenericPanel<>(Porder.class, hs);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                    hs.remove(hs.getCurrPanel());
                    hs.setCurrPanel(porderGenericPanel);
                    hs.add(hs.getCurrPanel());
                    hs.repaint();
                    hs.revalidate();
                    break;
            }
        }else if(e.getSource().equals(genericPanel.getUpdateData())){
            System.out.println("update the data");
            List<JTextField> textFieldList = genericPanel.getTextFields();
            List<String> toInsert = new ArrayList<>();
            switch (type.getSimpleName()) {

                case "Product": {
                    for (JTextField t : textFieldList) {
                        toInsert.add(t.getText());

                    }
                    //System.out.println(toInsert);
                    double valueD = Double.parseDouble(toInsert.get(4));
                    int valueI = Integer.parseInt(toInsert.get(5));
                    Product product = new Product(toInsert.get(3), valueD, valueI);
                    product.setId(genericPanel.id);
                    ProductBLL productBLL = new ProductBLL();
                    try {
                        try {
                            productBLL.update(product);
                            try {
                                GenericPanel<Product> genPan = new GenericPanel<>(Product.class,hs);
                                hs.remove(hs.getCurrPanel());
                                hs.setCurrPanel(genPan);
                                hs.add(hs.getCurrPanel());
                                hs.repaint();
                                hs.revalidate();
                            } catch (IllegalAccessException ex) {
                                throw new RuntimeException(ex);
                            }
                        }catch (IllegalArgumentException exec){
                            JOptionPane.showMessageDialog(new Frame(), exec.getMessage());
                        }
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }



                    break;
                }
                case "Client": {
                    for (JTextField t : textFieldList) {
                        toInsert.add(t.getText());
                    }

                    int valueI = Integer.parseInt(toInsert.get(5));
                    Client client = new Client(toInsert.get(3), toInsert.get(4), valueI);
                    client.setId(genericPanel.id);
                    ClientBLL clientBLL = new ClientBLL();
                    try {
                        try {
                            clientBLL.update(client);
                            try {
                                GenericPanel<Client> genPan = new GenericPanel<>(Client.class,hs);
                                hs.remove(hs.getCurrPanel());
                                hs.setCurrPanel(genPan);
                                hs.add(hs.getCurrPanel());
                                hs.repaint();
                                hs.revalidate();
                            } catch (IllegalAccessException ex) {
                                throw new RuntimeException(ex);
                            }
                        }catch (IllegalArgumentException exce){
                            JOptionPane.showMessageDialog(new Frame(), exce.getMessage());
                        }
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }

                    break;
                }


            }
        }
    }
}
