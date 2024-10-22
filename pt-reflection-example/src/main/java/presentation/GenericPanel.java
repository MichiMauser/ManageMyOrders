package presentation;

import dao.*;
//import jdk.vm.ci.runtime.JVMCIBackend;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GenericPanel<T> extends JPanel {
    private List<T> data;
    private Class<T> type;
    private List<JTextField> textFields= new ArrayList<>();
    private List<JLabel> labels = new ArrayList<>();
    private JButton back = new JButton("back");
    private JButton backFromInsert = new JButton("back");
    private JButton backFromUpdate = new JButton("back");
    private JButton insert = new JButton("insert");
   private JButton update = new JButton("update");
   private JButton updateData = new JButton("update data");
   private JButton delete = new JButton("delete");
   private JButton addButton = new JButton("Add");
   private JButton select = new JButton("select");
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private JPanel buttonPanel = new JPanel();
    private JPanel tablePanel = new JPanel();
    private final JPanel primaryPanel = new JPanel();
    private JPanel insertPanel = new JPanel();
    private JPanel updatePanel = new JPanel();
    public JPanel lastPanel = primaryPanel;
    public HomeScreen hs;
    InsertController<T> insertController ;
    ButtonController<T> buttonController ;
    ButtonController<T> updateController;
    JTable dataTable;
    DefaultTableModel tableModel;
    Integer id;

    @SuppressWarnings("unchecked")
    public GenericPanel(Class<T> type, HomeScreen hs) throws IllegalAccessException {
        this.type = type;
        this.hs = hs;
        this.setLayout(new BorderLayout());
        insertController = new InsertController<T>(hs,this, type);
        insertPanel = createInsertPanel(type);
        updatePanel = createUpdatePanel(type);
        buttonController = new ButtonController<T>(this, hs, insertPanel, type);
        updateController = new ButtonController<T>(this,hs,updatePanel,type);
        buttonPanel = buttonPanel(type);
        tablePanel = createTable(type);
        primaryPanel.add(buttonPanel);
        primaryPanel.add(tablePanel);
        add(primaryPanel);

    }

    JPanel buttonPanel(Class<T> type){
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new FlowLayout());
        back.addActionListener(buttonController);
        update.addActionListener(buttonController);
        insert.addActionListener(buttonController);
        delete.addActionListener(buttonController);

        buttonPane.setPreferredSize(new Dimension(50,400));;
        buttonPane.add(back);
        if(!type.getSimpleName().equals("Log")){

            buttonPane.add(insert);
            buttonPane.add(delete);
            buttonPane.add(update);
        }

        return buttonPane;
    }
    JPanel createInsertPanel(Class<T> type){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        String[] colNames = getCNames(type);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        for(String name: colNames){
            if(!name.equals("Id")) {
                JLabel label = new JLabel(name);
                JTextField textField = new JTextField("", 20);
                textFields.add(textField);
                gbc.gridy++;
                panel.add(label, gbc);
                gbc.gridx++;
                panel.add(textField, gbc);
                gbc.gridx = 0;
            }

        }
        gbc.gridy++;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        addButton.addActionListener(insertController);
        panel.add(addButton, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;

        backFromInsert.addActionListener(insertController);
        panel.add(backFromInsert, gbc);

        return panel;
    }
    public JPanel createUpdatePanel(Class<T> type){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        String[] colNames = getCNames(type);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        for(String name: colNames){
            if(!name.equals("Id")) {
                JLabel label = new JLabel(name);
                JTextField textField = new JTextField("", 20);
                textFields.add(textField);
                gbc.gridy++;
                panel.add(label, gbc);
                gbc.gridx++;
                panel.add(textField, gbc);
                gbc.gridx = 0;
            }

        }
        gbc.gridy++;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        updateData.addActionListener(insertController);
        panel.add(updateData, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;

        panel.add(backFromUpdate, gbc);
        backFromUpdate.addActionListener(insertController);
        panel.add(backFromUpdate, gbc);
        return panel;
    }
    private List<T> takeData(Class<T> type) {

        if (type.getSimpleName().equals("Product")) {
            return (List<T>) new ProductDAO().findAll();
        } else if (type.getSimpleName().equals("Client")) {
            return (List<T>) new ClientDAO().findAll();
        } else if (type.getSimpleName().equals("Porder")) {
            return (List<T>) new OrderDAO().findAll();
        }else if (type.getSimpleName().equals("Log")) {
            return (List<T>) new LogDAO().findAll();
        }
        return new ArrayList<>();
    }


    private String[] getCNames(Class<T> data) {
        Field[] fields = data.getDeclaredFields();
        String[] colNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            colNames[i] = fields[i].getName();
            //System.out.println(colNames[i]);
        }
        return colNames;
    }

    private Object[] getData(T data) throws IllegalAccessException {
        Field[] fields = data.getClass().getDeclaredFields();
        Object[] objData = new Object[fields.length];

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            objData[i] = fields[i].get(data);
        }
        return objData;
    }

    public JPanel createTable(Class<T> type) throws IllegalAccessException {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(700,400));
        JTable table = new JTable();
        String[] cNames = getCNames(type);

        tableModel = new DefaultTableModel(cNames, 0);
        data = takeData(type);
        for (T line : data) {
            Object[] rowData = getData(line);
            tableModel.addRow(rowData);
        }
        table.setModel(tableModel);
        dataTable = table;
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        tablePanel = panel;
        return panel;
    }

    public JButton getBack() {
        return back;
    }

    public JButton getUpdate() {
        return update;
    }

    public JButton getInsert() {
        return insert;
    }

    public JButton getDelete() {
        return delete;
    }

    public JPanel getPrimaryPanel() {
        return primaryPanel;
    }

    public JPanel getInsertPanel() {
        return insertPanel;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getBackFromInsert() {
        return backFromInsert;
    }

    public List<JTextField> getTextFields() {
        return textFields;
    }

    public JPanel getTablePanel() {
        return tablePanel;
    }

    public JButton getSelect() {
        return select;
    }

    public JTable getDataTable() {
        return dataTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JPanel getUpdatePanel() {
        return updatePanel;
    }

    public JButton getBackFromUpdate() {
        return backFromUpdate;
    }

    public JButton getUpdateData() {
        return updateData;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}




