/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.*;
import java.util.ArrayList;
import DAO.*;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

/**
 *
 * @author 84896
 */
public class StaffPage extends javax.swing.JFrame {
    String[] Customercolumm = new String[]{"CustomerCCCD", "CustomerName","DateOfBirth", "Cus_Phone", "Email", "Address", "SocialMedia", "Gender", "Nation"};
    String[] Petcolumm = new String[]{"Pet_ID", "Pet_Type", "Pet_Breed","Pet_Name", "Color","Age", "Weight", "Picture", "Gender","Shots", "Flush","Heath","Price"};
    String[] Productcolumm = new String[]{"Product_ID", "Product_Name", "Price_issue", "Price_sell", "Stock", "Receipt_Day" };
    String[] Invoicecolumm = new String[]{"Pet/Product ID","Pet/Product Name","Amount","Price"};
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DefaultTableModel Customertable = new DefaultTableModel();
    DefaultTableModel Producttable = new DefaultTableModel();
    DefaultTableModel Pettable = new DefaultTableModel();
    DefaultTableModel Invoicetable = new DefaultTableModel();
    float productprice = 0;
    float dummy = 0;
    float totalprice = 0;
    String imagepath = "C:\\Users\\84896\\Documents\\NetBeansProjects\\DBMS\\src\\icons\\dog.png";
    String current = "";
    /**
     * Creates new form Main_Page
     */
    public StaffPage() {
        initComponents();
        
        CustomersDAO cusDATA = new CustomersDAO();
        ArrayList<Customers> cuslist = cusDATA.getAll();
        CustomersTable.setModel(Customertable);
        Customertable.setColumnIdentifiers(Customercolumm);
        addCustomerData(cuslist);
        addCustomerSearchBoxData();
        /*
        them du lieu cho bang Pet
        */
        PetDAO petDATA = new PetDAO();
        ArrayList<Pet> petlist = petDATA.getAll();
        PetTable.setModel(Pettable);
        Pettable.setColumnIdentifiers(Petcolumm);
        addPetData(petlist);
        addSearchPetBoxData();
        /*
        them du lieu cho bang Product
        */
        PetProductDAO productDATA = new PetProductDAO();
        ArrayList<PetProduct> productlist = productDATA.getAll();
        ProductTable.setModel(Producttable);
        Producttable.setColumnIdentifiers(Productcolumm);
        addProductData(productlist);
        addSearchProductBoxData();
        /*
        Them du lieu cho Invoice
        */
        addStaffId();
        addCustomerId();
        addProductId();
        addPetId();
        InvoiceTable.setModel(Invoicetable);
        Invoicetable.setColumnIdentifiers(Invoicecolumm);
    }
    //customer
    public void addCustomerData(ArrayList<Customers> list) {
        for(Customers c : list){
            Customertable.addRow(new String[]{c.getCustomerCCCD(), c.getCustomerName(), String.valueOf(c.getDateOfBirth()), c.getCus_Phone(),c.getEmail(),c.getAddress(),c.getSocialMedia(),c.getGender(),c.getNation()});
        }
    }
     public void addCustomerSearchBoxData(){
         for(String o : Customercolumm){
             SearchCustomerBox.addItem(o);
         }
     } 
      //PET
      public void addPetData(ArrayList<Pet> list) {
        for(Pet c : list){
            Pettable.addRow(new String[]{c.getPet_ID(), c.getPet_Type(), c.getPet_Breed(), c.getPet_Name(), c.getColor(),String.valueOf(c.getAge()), String.valueOf(c.getWeight()), c.getPicture(), c.getGender(), c.getShots(), c.getFlush(), c.getHeath(), String.valueOf(c.getPrice())});
        }
    }
     public void addSearchPetBoxData(){
         for(String o : Petcolumm){
             SearchBoxPet.addItem(o);
         }
     }
    public ImageIcon ResizeImage(String ImagePath, JLabel picture){
        ImageIcon MyImg = new ImageIcon(ImagePath);
        Image img = MyImg.getImage();
        Image newImg = img.getScaledInstance(picture.getWidth(), picture.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    public void RefreshPet(){
        Pettable.setRowCount(0);
        PetDAO petDATA = new PetDAO();
        ArrayList<Pet> list = petDATA.getAll();
        addPetData(list);
    }
    //PRODUCT
    public void addProductData(ArrayList<PetProduct> list) {
        for(PetProduct p : list){
            Producttable.addRow(new String [] {p.getProduct_ID(),p.getProduct_Name(),String.valueOf(p.getPrice_issue()),String.valueOf(p.getPrice_sell()),String.valueOf(p.getStock()),String.valueOf(p.getReceipt_Day())});
        }
    }
     public void addSearchProductBoxData(){
         for(String o : Productcolumm){
             SearchProductBox.addItem(o);
         }
     }
     //INVOICE
     public void addStaffId(){
    StaffDAO staffDATA = new StaffDAO();
    ArrayList<Staff> list = staffDATA.getAll();
    for(Staff st : list){
    staffidcbiv.addItem(st.getId());
    }
    }
    public void fillStaffName(){
    StaffDAO staffDATA = new StaffDAO();
    if(staffidcbiv.getSelectedItem().equals("Select")){
       staffnametxt.setText("");
    }else{
    ArrayList<Staff> list = staffDATA.getbyID(String.valueOf(staffidcbiv.getSelectedItem()));
    staffnametxt.setText(list.get(0).getName());
    }
    }
    public void addCustomerId(){
    CustomersDAO cusDATA = new CustomersDAO();
    ArrayList<Customers> list = cusDATA.getAll();
    for(Customers st : list){
    customeridcbiv.addItem(st.getCustomerCCCD());
    }
    }
    public void fillCustomerName(){
    if(customeridcbiv.getSelectedItem().equals("Select")){
     customernametxt.setText("");
    }else{
     CustomersDAO cusDATA = new CustomersDAO();
    ArrayList<Customers> list = cusDATA.getbyID(String.valueOf(customeridcbiv.getSelectedItem()));
    customernametxt.setText(list.get(0).getCustomerName());
    }
    }
    public void addProductId(){
    PetProductDAO productDATA = new PetProductDAO();
    ArrayList<PetProduct> list = productDATA.getAll();
    for(PetProduct pp : list){
    productidcbiv.addItem(pp.getProduct_ID());
    }
    }
    public void fillProductDATA(){
    if(productidcbiv.getSelectedItem().equals("Select")){
    productnameivtxt.setText("");
    }else{
     PetProductDAO productDATA = new PetProductDAO();
     ArrayList<PetProduct> list = productDATA.getbyID(String.valueOf(productidcbiv.getSelectedItem()));
    productnameivtxt.setText(list.get(0).getProduct_Name());
    productprice = list.get(0).getPrice_sell();
    }}
    public void addPetId(){
    PetDAO PetDATA = new PetDAO();
    ArrayList<Pet> list = PetDATA.getAll();
    for(Pet p : list){
    petidcbiv.addItem(p.getPet_ID());
    }
    }
    public void fillPetName(){
    if(petidcbiv.getSelectedItem().equals("Select")){
    petnameivtxt.setText("");
    pettypeivtxt.setText("");
    petpriceivtxt.setText("");
    }else{
    PetDAO PetDATA = new PetDAO();
    ArrayList<Pet> list = PetDATA.getbyID(String.valueOf(petidcbiv.getSelectedItem()));
    petnameivtxt.setText(list.get(0).getPet_Name());
    pettypeivtxt.setText(list.get(0).getPet_Type());
    petpriceivtxt.setText(String.valueOf(list.get(0).getPrice()));
    }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AddPetDialog = new javax.swing.JDialog();
        jLabel21 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        petidtxt = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        pettypetxt = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        petbreedtxt = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        petnametxt = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        colortxt = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        agetxt = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        weighttxt = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        ChoosePictureButton = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        ShotsYesButton = new javax.swing.JRadioButton();
        ShotsNoButton = new javax.swing.JRadioButton();
        FlushYesButton = new javax.swing.JRadioButton();
        FlushNoButton = new javax.swing.JRadioButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        healthtxt = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        MaleRadioButton = new javax.swing.JRadioButton();
        FemaleRadioButton = new javax.swing.JRadioButton();
        pricetxt = new javax.swing.JFormattedTextField();
        DialogPictureLabel = new javax.swing.JLabel();
        ConfirmPet = new javax.swing.JButton();
        AddProductDialog = new javax.swing.JDialog();
        jLabel37 = new javax.swing.JLabel();
        IDProducttxt = new javax.swing.JTextField();
        ProductNametxt = new javax.swing.JTextField();
        Stocktxt = new javax.swing.JTextField();
        Priceissuetxt = new javax.swing.JTextField();
        Priceselltxt = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        ConfirmProduct = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        Receiptdate = new com.toedter.calendar.JDateChooser();
        AddCustomerDialog = new javax.swing.JDialog();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cusAddresstxt = new javax.swing.JTextField();
        cusNametxt = new javax.swing.JTextField();
        cusPhonetxt = new javax.swing.JTextField();
        cusEmailtxt = new javax.swing.JTextField();
        cusCCCDtxt = new javax.swing.JTextField();
        cusSocialMediatxt = new javax.swing.JTextField();
        cusNationtxt = new javax.swing.JTextField();
        cusBirthdaytxt = new javax.swing.JFormattedTextField();
        Button1 = new javax.swing.JRadioButton();
        Button2 = new javax.swing.JRadioButton();
        ConfirmCustomerButton = new javax.swing.JButton();
        GenderButtonGroup = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Customer = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        SearchCustomerBox = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        Searchcustomertxt = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        AddCustomer = new javax.swing.JButton();
        UpdateCustomer = new javax.swing.JButton();
        DeleteCustomer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        CustomersTable = new javax.swing.JTable();
        Logout = new javax.swing.JButton();
        Pet = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        DeletePet = new javax.swing.JButton();
        UpdatePet = new javax.swing.JButton();
        addPetButton = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        SearchPettxt = new javax.swing.JTextField();
        SearchBoxPet = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        PetTable = new javax.swing.JTable();
        PictureLabel = new javax.swing.JLabel();
        Updatepicture = new javax.swing.JButton();
        Product = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        AddProduct = new javax.swing.JButton();
        DeleteProduct = new javax.swing.JButton();
        UpdateProduct = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        ScrollPane = new javax.swing.JScrollPane();
        ProductTable = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        SearchProductBox = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        SearchProducttxt = new javax.swing.JTextField();
        Invoice = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        InvoiceTable = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        staffnametxt = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        staffidcbiv = new javax.swing.JComboBox<>();
        customeridcbiv = new javax.swing.JComboBox<>();
        customernametxt = new javax.swing.JTextField();
        invoiceidtxt = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        date = new com.toedter.calendar.JDateChooser();
        refreshiv = new javax.swing.JButton();
        Add1 = new javax.swing.JButton();
        print = new javax.swing.JButton();
        Save = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        productidcbiv = new javax.swing.JComboBox<>();
        productnameivtxt = new javax.swing.JTextField();
        amountivtxt = new javax.swing.JFormattedTextField();
        productpriceivtxt = new javax.swing.JFormattedTextField();
        pettypeivtxt = new javax.swing.JTextField();
        petidcbiv = new javax.swing.JComboBox<>();
        petnameivtxt = new javax.swing.JTextField();
        petpriceivtxt = new javax.swing.JFormattedTextField();
        jLabel60 = new javax.swing.JLabel();
        totalcosttxt = new javax.swing.JTextField();
        DeleteItem = new javax.swing.JButton();

        AddPetDialog.setMinimumSize(new java.awt.Dimension(725, 650));
        AddPetDialog.setResizable(false);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel21.setText("Add Pet");

        petidtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                petidtxtActionPerformed(evt);
            }
        });

        jLabel22.setText("Pet_ID");

        pettypetxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pettypetxtActionPerformed(evt);
            }
        });

        jLabel23.setText("Pet_Type");

        jLabel24.setText("Pet_Breed");

        jLabel25.setText("Pet_Name");

        jLabel26.setText("Color");

        jLabel27.setText("Age");

        jLabel28.setText("Weigth");

        jLabel29.setText("Picture");

        ChoosePictureButton.setText("Choose Picture");
        ChoosePictureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChoosePictureButtonActionPerformed(evt);
            }
        });

        jLabel30.setText("Gender");

        jLabel31.setText("Shots");

        ShotsYesButton.setText("Yes");

        ShotsNoButton.setText("No");

        FlushYesButton.setText("Yes");

        FlushNoButton.setText("No");
        FlushNoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FlushNoButtonActionPerformed(evt);
            }
        });

        jLabel32.setText("Flush");

        jLabel33.setText("Health");

        jLabel34.setText("Price");

        MaleRadioButton.setText("Male");

        FemaleRadioButton.setText("Female");

        pricetxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(petidtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(294, 294, 294)
                                        .addComponent(jLabel32)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(FlushYesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ChoosePictureButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ShotsYesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(pettypetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel30)
                                .addGap(18, 18, 18)
                                .addComponent(MaleRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(FemaleRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(ShotsNoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(FlushNoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(209, 209, 209))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(petbreedtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(petnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(agetxt, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                                    .addComponent(colortxt)))
                            .addComponent(jLabel27)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(22, 22, 22)
                                .addComponent(weighttxt)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel34))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(healthtxt, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                    .addComponent(pricetxt))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(petidtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel29)
                    .addComponent(ChoosePictureButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pettypetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel30)
                    .addComponent(MaleRadioButton)
                    .addComponent(FemaleRadioButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(petbreedtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(ShotsYesButton)
                    .addComponent(ShotsNoButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(petnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FlushYesButton)
                    .addComponent(FlushNoButton)
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(colortxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel26)
                        .addComponent(jLabel33)
                        .addComponent(healthtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(agetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(pricetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(weighttxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        DialogPictureLabel.setMaximumSize(new java.awt.Dimension(400, 300));
        DialogPictureLabel.setMinimumSize(new java.awt.Dimension(200, 150));
        DialogPictureLabel.setPreferredSize(new java.awt.Dimension(200, 150));

        ConfirmPet.setText("Confirm");
        ConfirmPet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmPetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddPetDialogLayout = new javax.swing.GroupLayout(AddPetDialog.getContentPane());
        AddPetDialog.getContentPane().setLayout(AddPetDialogLayout);
        AddPetDialogLayout.setHorizontalGroup(
            AddPetDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddPetDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddPetDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddPetDialogLayout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(AddPetDialogLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DialogPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(201, 201, 201))))
            .addGroup(AddPetDialogLayout.createSequentialGroup()
                .addGap(317, 317, 317)
                .addComponent(ConfirmPet)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        AddPetDialogLayout.setVerticalGroup(
            AddPetDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddPetDialogLayout.createSequentialGroup()
                .addGroup(AddPetDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddPetDialogLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel21))
                    .addComponent(DialogPictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ConfirmPet)
                .addGap(17, 17, 17))
        );

        AddProductDialog.setModal(true);

        jLabel37.setText("Add Product");

        IDProducttxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDProducttxtActionPerformed(evt);
            }
        });

        ProductNametxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductNametxtActionPerformed(evt);
            }
        });

        jLabel38.setText("ID");

        jLabel39.setText("Price issue");

        jLabel40.setText("Name");

        jLabel41.setText("Receipt day");

        jLabel42.setText("Price sell");

        jLabel43.setText("Stock");

        ConfirmProduct.setText("Confirm");
        ConfirmProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmProductActionPerformed(evt);
            }
        });

        Close.setText("Close");
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });

        Receiptdate.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout AddProductDialogLayout = new javax.swing.GroupLayout(AddProductDialog.getContentPane());
        AddProductDialog.getContentPane().setLayout(AddProductDialogLayout);
        AddProductDialogLayout.setHorizontalGroup(
            AddProductDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddProductDialogLayout.createSequentialGroup()
                .addGroup(AddProductDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AddProductDialogLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(AddProductDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(AddProductDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ProductNametxt)
                            .addComponent(IDProducttxt)
                            .addComponent(Receiptdate, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                        .addGap(141, 141, 141)
                        .addGroup(AddProductDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AddProductDialogLayout.createSequentialGroup()
                                .addGroup(AddProductDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(AddProductDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(AddProductDialogLayout.createSequentialGroup()
                                        .addComponent(Stocktxt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(Priceselltxt)))
                            .addGroup(AddProductDialogLayout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Priceissuetxt))))
                    .addGroup(AddProductDialogLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Close)
                        .addGap(90, 90, 90)
                        .addComponent(ConfirmProduct)
                        .addGap(118, 118, 118)))
                .addGap(100, 100, 100))
            .addGroup(AddProductDialogLayout.createSequentialGroup()
                .addGap(305, 305, 305)
                .addComponent(jLabel37)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AddProductDialogLayout.setVerticalGroup(
            AddProductDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddProductDialogLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddProductDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IDProducttxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Priceissuetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39))
                .addGap(37, 37, 37)
                .addGroup(AddProductDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProductNametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Priceselltxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel42))
                .addGap(40, 40, 40)
                .addGroup(AddProductDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddProductDialogLayout.createSequentialGroup()
                        .addGroup(AddProductDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Stocktxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41)
                            .addComponent(jLabel43))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(AddProductDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ConfirmProduct)
                            .addComponent(Close))
                        .addGap(19, 19, 19))
                    .addGroup(AddProductDialogLayout.createSequentialGroup()
                        .addComponent(Receiptdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        AddCustomerDialog.setMinimumSize(new java.awt.Dimension(500, 400));
        AddCustomerDialog.setModal(true);
        AddCustomerDialog.setResizable(false);

        jLabel11.setText("CCCD");

        jLabel12.setText("Name");

        jLabel13.setText("Birthday");

        jLabel14.setText("Phone");

        jLabel15.setText("Email");

        jLabel16.setText("Nation");

        jLabel17.setText("Gender");

        jLabel18.setText("SocialMedia");

        jLabel19.setText("Address");

        jLabel20.setText("Add Customer");

        cusNationtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cusNationtxtActionPerformed(evt);
            }
        });

        cusBirthdaytxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));

        Button1.setText("Male");
        Button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button1ActionPerformed(evt);
            }
        });

        Button2.setText("Female");
        Button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button2ActionPerformed(evt);
            }
        });

        ConfirmCustomerButton.setText("Confirm");
        ConfirmCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmCustomerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddCustomerDialogLayout = new javax.swing.GroupLayout(AddCustomerDialog.getContentPane());
        AddCustomerDialog.getContentPane().setLayout(AddCustomerDialogLayout);
        AddCustomerDialogLayout.setHorizontalGroup(
            AddCustomerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddCustomerDialogLayout.createSequentialGroup()
                .addGroup(AddCustomerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AddCustomerDialogLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(AddCustomerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AddCustomerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(AddCustomerDialogLayout.createSequentialGroup()
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cusEmailtxt, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                                .addGroup(AddCustomerDialogLayout.createSequentialGroup()
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cusPhonetxt))
                                .addGroup(AddCustomerDialogLayout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cusBirthdaytxt))
                                .addGroup(AddCustomerDialogLayout.createSequentialGroup()
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cusNametxt)))
                            .addGroup(AddCustomerDialogLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cusCCCDtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(AddCustomerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AddCustomerDialogLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(AddCustomerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ConfirmCustomerButton)
                            .addComponent(jLabel20))))
                .addGap(18, 18, 18)
                .addGroup(AddCustomerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cusAddresstxt, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cusSocialMediatxt, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cusNationtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AddCustomerDialogLayout.createSequentialGroup()
                        .addComponent(Button1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Button2)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        AddCustomerDialogLayout.setVerticalGroup(
            AddCustomerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddCustomerDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addGap(43, 43, 43)
                .addGroup(AddCustomerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel19)
                    .addComponent(cusAddresstxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cusCCCDtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(AddCustomerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel18)
                    .addComponent(cusNametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cusSocialMediatxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(AddCustomerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17)
                    .addComponent(cusBirthdaytxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Button1)
                    .addComponent(Button2))
                .addGap(23, 23, 23)
                .addGroup(AddCustomerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(cusPhonetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cusNationtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(AddCustomerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cusEmailtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(ConfirmCustomerButton)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel9.setFont(new java.awt.Font("Fontype Animal Pet", 0, 24)); // NOI18N
        jLabel9.setText("Customers Information");

        jLabel10.setText("Search");

        Searchcustomertxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchcustomertxtKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SearchCustomerBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Searchcustomertxt, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Searchcustomertxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchCustomerBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        AddCustomer.setText("ADD");
        AddCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCustomerActionPerformed(evt);
            }
        });

        UpdateCustomer.setText("UPDATE");
        UpdateCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateCustomerActionPerformed(evt);
            }
        });

        DeleteCustomer.setText("DELETE");
        DeleteCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteCustomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DeleteCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AddCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UpdateCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(AddCustomer)
                .addGap(50, 50, 50)
                .addComponent(UpdateCustomer)
                .addGap(53, 53, 53)
                .addComponent(DeleteCustomer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        CustomersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(CustomersTable);

        Logout.setText("Log Out");
        Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CustomerLayout = new javax.swing.GroupLayout(Customer);
        Customer.setLayout(CustomerLayout);
        CustomerLayout.setHorizontalGroup(
            CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CustomerLayout.createSequentialGroup()
                .addContainerGap(142, Short.MAX_VALUE)
                .addGroup(CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CustomerLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(104, 104, 104))
            .addGroup(CustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Logout)
                .addGap(325, 325, 325)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CustomerLayout.setVerticalGroup(
            CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(Logout))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(437, 437, 437))
        );

        jTabbedPane1.addTab("Customer", Customer);

        DeletePet.setText("DELETE");
        DeletePet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeletePetActionPerformed(evt);
            }
        });

        UpdatePet.setText("UPDATE");
        UpdatePet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdatePetActionPerformed(evt);
            }
        });

        addPetButton.setText("ADD");
        addPetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(UpdatePet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DeletePet)
                    .addComponent(addPetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(addPetButton)
                .addGap(54, 54, 54)
                .addComponent(UpdatePet)
                .addGap(60, 60, 60)
                .addComponent(DeletePet)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jLabel35.setFont(new java.awt.Font("Fontype Animal Pet", 0, 24)); // NOI18N
        jLabel35.setText("List of Pet");

        jLabel36.setText("Search");

        SearchPettxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchPettxtKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SearchBoxPet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SearchPettxt, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(SearchPettxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchBoxPet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        PetTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        PetTable.setMinimumSize(new java.awt.Dimension(100, 120));
        PetTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PetTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(PetTable);

        Updatepicture.setText("Update Picture");
        Updatepicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdatepictureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PetLayout = new javax.swing.GroupLayout(Pet);
        Pet.setLayout(PetLayout);
        PetLayout.setHorizontalGroup(
            PetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PetLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(PetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PetLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(98, Short.MAX_VALUE))
                    .addGroup(PetLayout.createSequentialGroup()
                        .addGroup(PetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PetLayout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PetLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(PetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PetLayout.createSequentialGroup()
                                        .addComponent(Updatepicture)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PetLayout.createSequentialGroup()
                                        .addComponent(jLabel35)
                                        .addGap(280, 280, 280)))))
                        .addComponent(PictureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138))))
        );
        PetLayout.setVerticalGroup(
            PetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PictureLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PetLayout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(19, 19, 19)
                        .addComponent(Updatepicture)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pet", Pet);

        AddProduct.setText("ADD");
        AddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddProductActionPerformed(evt);
            }
        });

        DeleteProduct.setText("DELETE");
        DeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteProductActionPerformed(evt);
            }
        });

        UpdateProduct.setText("UPDATE");
        UpdateProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(DeleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(UpdateProduct)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(AddProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(AddProduct)
                .addGap(52, 52, 52)
                .addComponent(UpdateProduct)
                .addGap(50, 50, 50)
                .addComponent(DeleteProduct)
                .addContainerGap(164, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        jLabel45.setFont(new java.awt.Font("Fontype Animal Pet", 0, 24)); // NOI18N
        jLabel45.setText("Product Information");

        ProductTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        ScrollPane.setViewportView(ProductTable);

        jLabel44.setText("Search");

        SearchProducttxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchProducttxtKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel44)
                .addGap(18, 18, 18)
                .addComponent(SearchProductBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SearchProducttxt, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchProductBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(SearchProducttxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ProductLayout = new javax.swing.GroupLayout(Product);
        Product.setLayout(ProductLayout);
        ProductLayout.setHorizontalGroup(
            ProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProductLayout.createSequentialGroup()
                .addGroup(ProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ProductLayout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ProductLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ProductLayout.createSequentialGroup()
                        .addGap(403, 403, 403)
                        .addComponent(jLabel45)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ProductLayout.setVerticalGroup(
            ProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProductLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(ProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ProductLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ProductLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(112, Short.MAX_VALUE))
            .addGroup(ProductLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(422, 422, 422))
        );

        jTabbedPane1.addTab("Product", Product);

        Invoice.setMinimumSize(new java.awt.Dimension(1023, 632));

        InvoiceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(InvoiceTable);

        jLabel46.setText("StaffID");

        jLabel47.setText("CustomerID");

        jLabel48.setText("StaffName");

        staffnametxt.setEditable(false);

        jLabel49.setText("CustomerName");

        staffidcbiv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        staffidcbiv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                staffidcbivItemStateChanged(evt);
            }
        });

        customeridcbiv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        customeridcbiv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                customeridcbivItemStateChanged(evt);
            }
        });

        customernametxt.setEditable(false);

        jLabel50.setText("InvoiceID");

        jLabel51.setText("Date");

        refreshiv.setText("Refresh");
        refreshiv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshivActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel50)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(invoiceidtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(staffidcbiv, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(staffnametxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49))
                .addGap(31, 31, 31)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(customeridcbiv, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customernametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(203, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(refreshiv)
                .addGap(29, 29, 29))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refreshiv)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jLabel47)
                    .addComponent(staffidcbiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customeridcbiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(invoiceidtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50))
                .addGap(47, 47, 47)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(customernametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49)
                            .addComponent(staffnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel48))
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel51))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        Add1.setText("Add");
        Add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add1ActionPerformed(evt);
            }
        });

        print.setText("Print");
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        jLabel52.setText("ProductName");

        jLabel53.setText("Amount");

        jLabel54.setText("PetName");

        jLabel55.setText("PetType");

        jLabel56.setText("ProductID");

        jLabel57.setText("PetID");

        jLabel58.setText("Price");

        jLabel59.setText("Price");

        productidcbiv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        productidcbiv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                productidcbivItemStateChanged(evt);
            }
        });

        productnameivtxt.setEditable(false);

        amountivtxt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        amountivtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                amountivtxtKeyReleased(evt);
            }
        });

        productpriceivtxt.setEditable(false);

        pettypeivtxt.setEditable(false);

        petidcbiv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        petidcbiv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                petidcbivItemStateChanged(evt);
            }
        });

        petnameivtxt.setEditable(false);

        petpriceivtxt.setEditable(false);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(petidcbiv, 0, 152, Short.MAX_VALUE)
                    .addComponent(productidcbiv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(59, 59, 59)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52)
                    .addComponent(jLabel54))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(productnameivtxt, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(petnameivtxt))
                .addGap(44, 44, 44)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel55)
                        .addGap(18, 18, 18)
                        .addComponent(pettypeivtxt))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel53)
                        .addGap(18, 18, 18)
                        .addComponent(amountivtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58)
                    .addComponent(jLabel59))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(productpriceivtxt, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(petpriceivtxt))
                .addGap(16, 16, 16))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(jLabel52)
                    .addComponent(jLabel53)
                    .addComponent(jLabel58)
                    .addComponent(productidcbiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productnameivtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amountivtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productpriceivtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(jLabel54)
                    .addComponent(jLabel55)
                    .addComponent(jLabel59)
                    .addComponent(pettypeivtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(petidcbiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(petnameivtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(petpriceivtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel60.setText("Total Price");

        totalcosttxt.setEditable(false);

        DeleteItem.setText("Delete");
        DeleteItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout InvoiceLayout = new javax.swing.GroupLayout(Invoice);
        Invoice.setLayout(InvoiceLayout);
        InvoiceLayout.setHorizontalGroup(
            InvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3)
            .addGroup(InvoiceLayout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(DeleteItem)
                .addGap(35, 35, 35)
                .addComponent(Add1)
                .addGap(31, 31, 31)
                .addComponent(Save)
                .addGap(31, 31, 31)
                .addComponent(print)
                .addGap(96, 96, 96)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalcosttxt, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        InvoiceLayout.setVerticalGroup(
            InvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InvoiceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(InvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Add1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DeleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InvoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(totalcosttxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel60)))
                .addGap(47, 47, 47))
        );

        jTabbedPane1.addTab("Invoicing", Invoice);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1107, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void petidtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_petidtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_petidtxtActionPerformed

    private void pettypetxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pettypetxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pettypetxtActionPerformed

    private void ChoosePictureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChoosePictureButtonActionPerformed
        JFileChooser k = new JFileChooser("C:\\Users\\84896\\Documents\\NetBeansProjects\\DBMS\\src\\icons");
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("picture","png", "jpg");
        k.setFileFilter(imageFilter);
        k.setDialogTitle("Open file");
        k.showOpenDialog(null);
        File filename = k.getSelectedFile();
        imagepath = filename.getAbsolutePath();
        if (imagepath != null){
            DialogPictureLabel.setIcon(ResizeImage(String.valueOf(imagepath),DialogPictureLabel));
            System.out.println(imagepath);
        }else{
            JOptionPane.showMessageDialog(rootPane, "Please select your picture!!");
        }
    }//GEN-LAST:event_ChoosePictureButtonActionPerformed

    private void FlushNoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FlushNoButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FlushNoButtonActionPerformed

    private void ConfirmPetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmPetActionPerformed
        PetDAO petDATA = new PetDAO();
        if(petidtxt.getText().isBlank() || pettypetxt.getText().isBlank() || petbreedtxt.getText().isBlank() ||
            petnametxt.getText().isBlank() || colortxt.getText().isBlank() || agetxt.getText().isBlank() ||
            weighttxt.getText().isBlank() || healthtxt.getText().isBlank() || pricetxt.getText().isBlank() ||
            (!MaleRadioButton.isSelected() && !FemaleRadioButton.isSelected()) ||
            (!ShotsYesButton.isSelected() && !ShotsNoButton.isSelected()) ||
            (!FlushYesButton.isSelected() && !FlushNoButton.isSelected())){
            JOptionPane.showMessageDialog(this, "Missing information");
        }else {
            String PetId = petidtxt.getText();
            String pettype = pettypetxt.getText();
            String petbreed = petbreedtxt.getText();
            String petname = petnametxt.getText();
            String color = colortxt.getText();
            int age = Integer.parseInt(agetxt.getText());
            Float weight = Float.parseFloat(weighttxt.getText());
            String health = healthtxt.getText();
            Float price = Float.parseFloat(pricetxt.getText());
            String picture = imagepath;
            if(picture.equals("C:\\Users\\84896\\Documents\\NetBeansProjects\\DBMS\\src\\icons\\dog.png")){
                JOptionPane.showMessageDialog(this, "Please choose picture");
                return;
            }
            String Gender = "";
            if(MaleRadioButton.isSelected()){
                Gender += "Male";
            }else if(FemaleRadioButton.isSelected()){
                Gender += "Female";
            }
            String Shots = "";
            if(ShotsYesButton.isSelected()){
                Shots += "Yes";
            }else if(ShotsNoButton.isSelected()){
                Shots += "No";
            }
            String Flush ="";
            if(FlushYesButton.isSelected()){
                Flush += "Yes";
            }else if(FlushNoButton.isSelected()){
                Flush += "No";
            }

            Pet p = new Pet(PetId,pettype,petbreed,petname,color, age,weight,picture,Gender, Shots, Flush, health,price);
            int result = petDATA.insert(p);
            if(result == 0){
                JOptionPane.showMessageDialog(this, "Add Failed!!");
            }else
            {
                JOptionPane.showMessageDialog(this, "Add Successful!!");
                Pettable.setRowCount(0);
                ArrayList<Pet> list = petDATA.getAll();
                addPetData(list);
            }

        }
    }//GEN-LAST:event_ConfirmPetActionPerformed

    private void IDProducttxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDProducttxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDProducttxtActionPerformed

    private void ProductNametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductNametxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProductNametxtActionPerformed

    private void ConfirmProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmProductActionPerformed
        PetProductDAO productDATA = new PetProductDAO();
        ArrayList<PetProduct> l1st = productDATA.getbyID(IDProducttxt.getText());
        if(IDProducttxt.getText().isBlank() || ProductNametxt.getText().isBlank() || Priceissuetxt.getText().isBlank() || Priceselltxt.getText().isBlank() || Stocktxt.getText().isBlank()){
            JOptionPane.showMessageDialog(rootPane, "Missing information");
        }else if(!l1st.isEmpty()){
            JOptionPane.showMessageDialog(this, "ID already used");
        }else {
            try{
                PetProduct pp = new PetProduct();
                pp.setProduct_ID(IDProducttxt.getText());
                pp.setProduct_Name(ProductNametxt.getText());
                pp.setPrice_issue(Float.parseFloat(Priceissuetxt.getText()));
                pp.setPrice_sell(Float.parseFloat(Priceselltxt.getText()));
                pp.setStock(Integer.parseUnsignedInt(Stocktxt.getText()));
                String date = dateFormat.format(Receiptdate.getDate());
                pp.setReceipt_Day(dateFormat.parse(date));
                int result = productDATA.insert(pp);
                if(result == 0){
                    JOptionPane.showMessageDialog(this, "Add Failed!!");
                }else
                {
                    JOptionPane.showMessageDialog(this, "Add Successful!!");
                    Producttable.setRowCount(0);
                    ArrayList<PetProduct> list = productDATA.getAll();
                    addProductData(list);
                }
            }catch(Exception e){
                System.out.println("Error :" + e);
            }
        }
    }//GEN-LAST:event_ConfirmProductActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        AddProductDialog.setVisible(false);
    }//GEN-LAST:event_CloseActionPerformed

    private void SearchcustomertxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchcustomertxtKeyReleased
        CustomersDAO cusDATA = new CustomersDAO();
        ArrayList<Customers> list = cusDATA.search((String)SearchCustomerBox.getSelectedItem(),Searchcustomertxt.getText());
        Customertable.setRowCount(0);
        addCustomerData(list);
        if(Searchcustomertxt.getText().equals("")){
            list = cusDATA.getAll();
            Customertable.setRowCount(0);
            addCustomerData(list);
        }
    }//GEN-LAST:event_SearchcustomertxtKeyReleased

    private void AddCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCustomerActionPerformed
        AddCustomerDialog.setVisible(true);
    }//GEN-LAST:event_AddCustomerActionPerformed

    private void UpdateCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateCustomerActionPerformed
        CustomersDAO cusDATA = new CustomersDAO();
        int result = 0;
        for(int i = 0 ; i < Customertable.getRowCount();i++) {
            if(Customertable.getValueAt(i, 0).equals("") ||
                Customertable.getValueAt(i, 1).equals("") ||
                Customertable.getValueAt(i, 2).equals("") ||
                Customertable.getValueAt(i, 3).equals("") ||
                Customertable.getValueAt(i, 4).equals("") ||
                Customertable.getValueAt(i, 5).equals("") ||
                Customertable.getValueAt(i, 6).equals("") ||
                Customertable.getValueAt(i, 7).equals("") ||
                Customertable.getValueAt(i, 8).equals("")){
                JOptionPane.showMessageDialog(this,"Please enter full information");
                break;
            }else{
                try{
                    Customers cus = new Customers();
                    cus.setCustomerCCCD((String)Customertable.getValueAt(i, 0));
                    cus.setCustomerName((String)Customertable.getValueAt(i,1));
                    String DateValue = (String)Customertable.getValueAt(i,2);
                    Date birth = dateFormat.parse(DateValue);
                    cus.setDateOfBirth(birth);
                    cus.setCus_Phone((String)Customertable.getValueAt(i,3));
                    cus.setEmail((String)Customertable.getValueAt(i,4));
                    cus.setAddress((String)Customertable.getValueAt(i,5));
                    cus.setSocialMedia((String)Customertable.getValueAt(i,6));
                    cus.setGender((String)Customertable.getValueAt(i,7));
                    cus.setNation((String)Customertable.getValueAt(i,8));
                    result = cusDATA.update(cus); //result = 0 => that bai , result > 0 => thanh cong
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
            }

        }
        if(result == 0 ){
            JOptionPane.showMessageDialog(this,"Cannot Update");
        }else {
            JOptionPane.showMessageDialog(this,"Updated!!!");
        }
        Customertable.setRowCount(0);
        ArrayList<Customers> list = cusDATA.getAll();
        addCustomerData(list);
    }//GEN-LAST:event_UpdateCustomerActionPerformed

    private void DeleteCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteCustomerActionPerformed
        CustomersDAO cusDATA = new CustomersDAO();
        int row = CustomersTable.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "Please choose customer to delete!");
        }else {
            int cf = JOptionPane.showConfirmDialog(this, "Are you sure to delete this customer?");
            if(cf == JOptionPane.YES_OPTION) {
                String id = String.valueOf(Customertable.getValueAt(row, 0));
                cusDATA.delete(id);
                Customertable.setRowCount(0);
                ArrayList<Customers> list = cusDATA.getAll();
                addCustomerData(list);
            }
        }
    }//GEN-LAST:event_DeleteCustomerActionPerformed

    private void DeletePetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeletePetActionPerformed
        PetDAO petDATA = new PetDAO();
        int row = PetTable.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "Please choose Pet to delete!");
        }else {
            int cf = JOptionPane.showConfirmDialog(this, "Are you sure to delete this Pet?");
            if(cf == JOptionPane.YES_OPTION) {
                String id = String.valueOf(PetTable.getValueAt(row, 0));
                petDATA.delete(id);
                Pettable.setRowCount(0);
                ArrayList<Pet> list = petDATA.getAll();
                addPetData(list);
            }
        }
    }//GEN-LAST:event_DeletePetActionPerformed

    private void UpdatePetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdatePetActionPerformed
        PetDAO PetDATA = new PetDAO();
        int result = 0;
        for(int i = 0 ; i < Pettable.getRowCount();i++) {
            if(Pettable.getValueAt(i, 0).equals("") ||
                Pettable.getValueAt(i, 1).equals("") ||
                Pettable.getValueAt(i, 2).equals("") ||
                Pettable.getValueAt(i, 3).equals("") ||
                Pettable.getValueAt(i, 4).equals("") ||
                Pettable.getValueAt(i, 5).equals("") ||
                Pettable.getValueAt(i, 6).equals("") ||
               Pettable.getValueAt(i, 7).equals("") ||
                Pettable.getValueAt(i, 8).equals("") ||
                Pettable.getValueAt(i, 9).equals("") ||
                Pettable.getValueAt(i, 10).equals("") ||
                Pettable.getValueAt(i, 11).equals("") ||
                Pettable.getValueAt(i, 12).equals("")){
                JOptionPane.showMessageDialog(this,"Please enter full information");
                return;
            }else{
                Pet pet = new Pet();
                pet.setPet_ID((String)Pettable.getValueAt(i, 0));
                pet.setPet_Type((String)Pettable.getValueAt(i,1));
                pet.setPet_Breed((String)Pettable.getValueAt(i,2));
                pet.setPet_Name((String)Pettable.getValueAt(i,3));
                pet.setColor((String)Pettable.getValueAt(i,4));
                pet.setAge(Integer.parseInt((String)Pettable.getValueAt(i, 5)));
                pet.setWeight(Float.parseFloat((String)Pettable.getValueAt(i, 6)));
                pet.setPicture((String)Pettable.getValueAt(i,7));
                pet.setGender((String)Pettable.getValueAt(i,8));
                pet.setShots((String)Pettable.getValueAt(i,9));
                pet.setFlush((String)Pettable.getValueAt(i,10));
                pet.setHeath((String)Pettable.getValueAt(i,11));
                pet.setPrice(Float.parseFloat((String)Pettable.getValueAt(i, 12)));
                result = PetDATA.update(pet);
            }
        }
        JOptionPane.showMessageDialog(this,"Updated!!!");
        RefreshPet();
    }//GEN-LAST:event_UpdatePetActionPerformed

    private void addPetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPetButtonActionPerformed
        AddPetDialog.setVisible(true);
        DialogPictureLabel.setIcon(ResizeImage(imagepath,DialogPictureLabel));
    }//GEN-LAST:event_addPetButtonActionPerformed

    private void SearchPettxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchPettxtKeyReleased
        PetDAO petDATA = new PetDAO();
        ArrayList<Pet> list = petDATA.search((String)SearchBoxPet.getSelectedItem(),SearchPettxt.getText());
        Pettable.setRowCount(0);
        addPetData(list);
        if(SearchPettxt.getText().equals("")){
            list = petDATA.getAll();
            Pettable.setRowCount(0);
            addPetData(list);
        }
    }//GEN-LAST:event_SearchPettxtKeyReleased

    private void PetTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PetTableMouseClicked
        int row = PetTable.getSelectedRow();
        current = String.valueOf(PetTable.getValueAt(row, 0));
        PetDAO petDATA = new PetDAO();
        ArrayList<Pet> list = petDATA.getbyID(current);
        PictureLabel.setIcon(ResizeImage(list.get(0).getPicture(),PictureLabel));
    }//GEN-LAST:event_PetTableMouseClicked

    private void UpdatepictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdatepictureActionPerformed
        if(current.equals("")){
            JOptionPane.showMessageDialog(this, "Please choose Picture to update!");
        }else{
            try{
                JFileChooser k = new JFileChooser("C:\\Users\\84896\\Documents\\NetBeansProjects\\DBMS\\src\\icons");
                FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("picture","png", "jpg");
                k.setFileFilter(imageFilter);
                k.setDialogTitle("Open file");
                k.showOpenDialog(this);
                File filename = k.getSelectedFile();
                String newImage = filename.getAbsolutePath();
                PetDAO petDATA = new PetDAO();
                ArrayList<Pet> getpet = petDATA.getbyID(current);
                getpet.get(0).setPicture(newImage);
                petDATA.update(getpet.get(0));
                PictureLabel.setIcon(ResizeImage(newImage,PictureLabel));
                RefreshPet();
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_UpdatepictureActionPerformed

    private void AddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddProductActionPerformed
        AddProductDialog.setVisible(true);
    }//GEN-LAST:event_AddProductActionPerformed

    private void DeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteProductActionPerformed
        PetProductDAO cusDATA = new PetProductDAO();
        int row = ProductTable.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "Please choose product to delete!");
        }else {
            int cf = JOptionPane.showConfirmDialog(this, "Are you sure to delete this product?");
            if(cf == JOptionPane.YES_OPTION) {
                String id = String.valueOf(Producttable.getValueAt(row, 0));
                cusDATA.delete(id);
                Producttable.setRowCount(0);
                ArrayList<PetProduct> list = cusDATA.getAll();
                addProductData(list);
            }
        }
    }//GEN-LAST:event_DeleteProductActionPerformed

    private void UpdateProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateProductActionPerformed
        PetProductDAO productDATA = new PetProductDAO();
        int result = 0;
        for(int i = 0 ; i < Producttable.getRowCount();i++) {
            if(Producttable.getValueAt(i, 0).equals("") ||
                Producttable.getValueAt(i, 1).equals("") ||
                Producttable.getValueAt(i, 2).equals("") ||
                Producttable.getValueAt(i, 3).equals("") ||
                Producttable.getValueAt(i, 4).equals("") ||
                Producttable.getValueAt(i, 5).equals("")){
                JOptionPane.showMessageDialog(this,"Please enter full information");
                break;
            }else{
                try{
                    PetProduct pp = new PetProduct();
                    pp.setProduct_ID(String.valueOf(ProductTable.getValueAt(i, 0)));
                    pp.setProduct_Name(String.valueOf(ProductTable.getValueAt(i, 1)));
                    pp.setPrice_issue(Float.parseFloat(String.valueOf(ProductTable.getValueAt(i, 2))));
                    pp.setPrice_sell(Float.parseFloat(String.valueOf(ProductTable.getValueAt(i, 3))));
                    pp.setStock(Integer.parseInt(String.valueOf(ProductTable.getValueAt(i, 4))));
                    String DateValue = String.valueOf(ProductTable.getValueAt(i,5));
                    Date birth = dateFormat.parse(DateValue);
                    pp.setReceipt_Day(birth);
                    result = productDATA.update(pp); //result = 0 => that bai , result > 0 => thanh cong
                }catch (ParseException ex) {
                    System.out.println("Error " + ex);
                }
            }
        }
        if(result == 0 ){
            JOptionPane.showMessageDialog(this,"Cannot Update");
        }else {
            JOptionPane.showMessageDialog(this,"Updated!!!");
        }
        Producttable.setRowCount(0);
        ArrayList<PetProduct> list = productDATA.getAll();
        addProductData(list);
    }//GEN-LAST:event_UpdateProductActionPerformed

    private void SearchProducttxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchProducttxtKeyReleased
        PetProductDAO productDATA = new PetProductDAO();
        ArrayList<PetProduct> list = productDATA.search((String)SearchProductBox.getSelectedItem(),SearchProducttxt.getText());
        Producttable.setRowCount(0);
        addProductData(list);
        if(SearchProducttxt.getText().equals("")){
            list = productDATA.getAll();
            Producttable.setRowCount(0);
            addProductData(list);
        }
    }//GEN-LAST:event_SearchProducttxtKeyReleased

    private void staffidcbivItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_staffidcbivItemStateChanged
        fillStaffName();
    }//GEN-LAST:event_staffidcbivItemStateChanged

    private void customeridcbivItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_customeridcbivItemStateChanged
        fillCustomerName();
    }//GEN-LAST:event_customeridcbivItemStateChanged

    private void refreshivActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshivActionPerformed
        invoiceidtxt.setText("");
        productidcbiv.setSelectedIndex(0);
        petidcbiv.setSelectedIndex(0);
        customeridcbiv.setSelectedIndex(0);
        staffidcbiv.setSelectedIndex(0);
        productnameivtxt.setText("");
        staffnametxt.setText("");
        petnameivtxt.setText("");
        pettypeivtxt.setText("");
        petpriceivtxt.setText("");
        Invoicetable.setRowCount(0);
        totalcosttxt.setText("");
    }//GEN-LAST:event_refreshivActionPerformed

    private void Add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Add1ActionPerformed

        if(!productidcbiv.getSelectedItem().equals("Select")){
            PetProductDAO productDATA = new PetProductDAO();
            ArrayList<PetProduct> listp = productDATA.getbyID(String.valueOf(productidcbiv.getSelectedItem()));
            if(amountivtxt.getText().isBlank()){
                JOptionPane.showMessageDialog(rootPane, "Please enter the amount");
                return;
            }else if(listp.get(0).getStock() - Integer.parseInt(amountivtxt.getText()) < 0 || listp.get(0).getStock() == 0){
                JOptionPane.showMessageDialog(rootPane, "Only "+ listp.get(0).getStock() +" left in stock");
            }else{
                Invoicetable.addRow(new String[] {String.valueOf(productidcbiv.getSelectedItem()),String.valueOf(listp.get(0).getProduct_Name()),amountivtxt.getText(),productpriceivtxt.getText()});
                dummy += Float.parseFloat(productpriceivtxt.getText());
                totalcosttxt.setText(String.valueOf(dummy));
            }
        }if(!petidcbiv.getSelectedItem().equals("Select")){
            PetDAO petDATA = new PetDAO();
            ArrayList<Pet> listp = petDATA.getbyID(String.valueOf(petidcbiv.getSelectedItem()));
            Invoicetable.addRow(new String[] {String.valueOf(petidcbiv.getSelectedItem()),String.valueOf(listp.get(0).getPet_Name()),"1",petpriceivtxt.getText()});
            dummy += Float.parseFloat(petpriceivtxt.getText());
            totalcosttxt.setText(String.valueOf(dummy));
        }else if(!petidcbiv.getSelectedItem().equals("Select") && !productidcbiv.getSelectedItem().equals("Select")){
            JOptionPane.showMessageDialog(rootPane, "Please choose your item or pet before add");
        }
    }//GEN-LAST:event_Add1ActionPerformed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        try{
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Invoice");
            HSSFRow row = null;
            Cell cell = null;

            row = sheet.createRow(0);
            cell = row.createCell(0,CellType.STRING);
            cell.setCellValue("Staff Name ");

            cell = row.createCell(1,CellType.STRING);
            cell.setCellValue(staffnametxt.getText());

            row = sheet.createRow(1);
            cell =row.createCell(0,CellType.STRING);
            cell.setCellValue("Customer Name ");

            cell =row.createCell(1,CellType.STRING);
            cell.setCellValue(customernametxt.getText());

            row = sheet.createRow(2);
            cell = row.createCell(2,CellType.STRING);
            cell.setCellValue("INVOICE");

            row = sheet.createRow (3);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("ID");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Name");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Amount");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Price");
            int i;
            for(i = 0 ; i < Invoicetable.getRowCount(); i++){
                row = sheet.createRow(4+i);
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(i+1);
                cell = row.createCell(1,CellType.STRING);
                cell.setCellValue(String.valueOf(Invoicetable.getValueAt(i, 0)));
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(String.valueOf(Invoicetable.getValueAt(i, 1)));
                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(Integer.parseInt(String.valueOf(Invoicetable.getValueAt(i, 2))));
                cell = row.createCell(4, CellType.NUMERIC);
                cell.setCellValue(Float.parseFloat(String.valueOf(Invoicetable.getValueAt(i, 3))));
            }
            row = sheet.createRow(4+i+1);
            cell = row.createCell(0,CellType.STRING);
            cell.setCellValue("Date");
            cell = row.createCell(1,CellType.STRING);
            cell.setCellValue(dateFormat.format(date.getDate()));
            cell = row.createCell(3,CellType.STRING);
            cell.setCellValue("Total");
            cell = row.createCell(4,CellType.NUMERIC);
            cell.setCellValue(totalprice);

            File f = new File("C:\\Users\\84896\\Desktop\\EXEL\\Invoice" + invoiceidtxt.getText() +".xls");
            try{
                FileOutputStream fos = new FileOutputStream(f);
                workbook.write(fos);
                fos.close();
            }catch(FileNotFoundException ex){
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(rootPane, "Print Success");
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Error");
        }
    }//GEN-LAST:event_printActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        try {
            if(invoiceidtxt.getText().isBlank() || customeridcbiv.getSelectedItem().equals("Select") || staffidcbiv.getSelectedItem().equals("Select") || Invoicetable.getRowCount() == 0){
                JOptionPane.showMessageDialog(this, "Missing information to save");
            }
            int result = 0;
            Detail_InvoiceDAO diDATA = new Detail_InvoiceDAO();
            InvoiceDAO ivDATA = new InvoiceDAO();
            String InvoiceID = invoiceidtxt.getText();
            if(!ivDATA.getbyID(invoiceidtxt.getText()).isEmpty()){
                JOptionPane.showMessageDialog(this, "ID has already used");
                return;
            }else{
                String DateValue = dateFormat.format(date.getDate());
                Date day = dateFormat.parse(DateValue);
                String StaffID  = String.valueOf(staffidcbiv.getSelectedItem());
                String CustomerID = String.valueOf(customeridcbiv.getSelectedItem());
                float Totalcost = Float.parseFloat(totalcosttxt.getText());
                Invoice iv = new Invoice(InvoiceID,StaffID,CustomerID,day, Totalcost);
                result += ivDATA.insert(iv);
                for(int i = 0 ; i < Invoicetable.getRowCount(); i++){
                    PetDAO petDATA = new PetDAO();
                    PetProductDAO productDATA = new PetProductDAO();
                    ArrayList<Pet> pet = petDATA.getbyID(String.valueOf(Invoicetable.getValueAt(i, 0)));
                    ArrayList<PetProduct> product = productDATA.getbyID(String.valueOf(Invoicetable.getValueAt(i,0)));
                    if(pet.isEmpty()){
                        String ProductID = product.get(0).getProduct_ID();
                        int amount = Integer.parseInt(String.valueOf(Invoicetable.getValueAt(i, 2)));
                        product.get(0).setStock(product.get(0).getStock() - amount);
                        productDATA.update(product.get(0));
                        float price = Float.parseFloat(String.valueOf(Invoicetable.getValueAt(i, 3)));
                        Detail_Invoice di = new Detail_Invoice(InvoiceID,ProductID,null,amount,price);
                        result += diDATA.insert(di);
                    }else if(product.isEmpty()){
                        String PetID = pet.get(0).getPet_ID();
                        int amount = 1;
                        petDATA.delete(PetID);
                        float price = Float.parseFloat(String.valueOf(Invoicetable.getValueAt(i, 3)));
                        Detail_Invoice di = new Detail_Invoice(InvoiceID,null,PetID,amount,price);
                        result += diDATA.insert(di);
                    }
                }
                if(!(result == Invoicetable.getRowCount()+1)){
                    JOptionPane.showMessageDialog(this, "Save Failed!!");
                    System.out.println(result);
                }else
                {
                    JOptionPane.showMessageDialog(this, "Save Successful!!");
                    totalprice = dummy;
                    dummy = 0;
                }
            }
        }catch (ParseException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_SaveActionPerformed

    private void productidcbivItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_productidcbivItemStateChanged

        fillProductDATA();
        amountivtxt.setText("");
        productpriceivtxt.setText("");
    }//GEN-LAST:event_productidcbivItemStateChanged

    private void amountivtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountivtxtKeyReleased
        int amount;
        if(amountivtxt.getText().isBlank()){
            amount = 0;
            productpriceivtxt.setText(String.valueOf(amount * productprice));
        }else {
            amount = Integer.parseInt(amountivtxt.getText());
            productpriceivtxt.setText(String.valueOf(amount * productprice));
        }
    }//GEN-LAST:event_amountivtxtKeyReleased

    private void petidcbivItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_petidcbivItemStateChanged
        fillPetName();
    }//GEN-LAST:event_petidcbivItemStateChanged

    private void DeleteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteItemActionPerformed
        int row = InvoiceTable.getSelectedRow();
        dummy = dummy - Float.parseFloat(String.valueOf(Invoicetable.getValueAt(row, 3)));
        totalcosttxt.setText(String.valueOf(dummy));
        Invoicetable.removeRow(row);
    }//GEN-LAST:event_DeleteItemActionPerformed

    private void cusNationtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cusNationtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cusNationtxtActionPerformed

    private void Button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button1ActionPerformed

    private void Button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button2ActionPerformed

    private void ConfirmCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmCustomerButtonActionPerformed
        CustomersDAO cusDATA = new CustomersDAO();
        if(cusCCCDtxt.getText().isBlank() || cusNametxt.getText().isBlank() || cusBirthdaytxt.getText().isBlank() ||
            cusPhonetxt.getText().isBlank() || cusEmailtxt.getText().isBlank() || cusAddresstxt.getText().isBlank() ||
            cusSocialMediatxt.getText().isBlank() || cusNationtxt.getText().isBlank() ||
            (!GenderButtonGroup.isSelected(Button1.getModel()) && !GenderButtonGroup.isSelected(Button2.getModel()))  ){
            JOptionPane.showMessageDialog(this, "Missing information");
        }else {
            try {
                String CCCD = cusCCCDtxt.getText();
                String Name = cusNametxt.getText();
                Date Birthday = dateFormat.parse(cusBirthdaytxt.getText());
                String Phone = cusPhonetxt.getText();
                String Email = cusEmailtxt.getText();
                String Address = cusAddresstxt.getText();
                String SocialMedia = cusSocialMediatxt.getText();
                String Gender = "";
                if(Button1.isSelected()){
                    Gender += "Male";
                }else if(Button2.isSelected()){
                    Gender += "Female";
                }
                String Nation = cusNationtxt.getText();
                Customers cus = new Customers(CCCD,Name,Phone,Email,Address, SocialMedia,Gender,Nation,Birthday);

                int result = cusDATA.insert(cus);
                if(result == 0){
                    JOptionPane.showMessageDialog(this, "Add Failed!!");
                }else
                {
                    JOptionPane.showMessageDialog(this, "Add Successful!!");
                    Customertable.setRowCount(0);
                    ArrayList<Customers> list = cusDATA.getAll();
                    addCustomerData(list);
                }
            }catch (ParseException ex) {
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_ConfirmCustomerButtonActionPerformed

    private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutActionPerformed
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_LogoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StaffPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaffPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaffPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaffPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaffPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add1;
    private javax.swing.JButton AddCustomer;
    private javax.swing.JDialog AddCustomerDialog;
    private javax.swing.JDialog AddPetDialog;
    private javax.swing.JButton AddProduct;
    private javax.swing.JDialog AddProductDialog;
    private javax.swing.JRadioButton Button1;
    private javax.swing.JRadioButton Button2;
    private javax.swing.JButton ChoosePictureButton;
    private javax.swing.JButton Close;
    private javax.swing.JButton ConfirmCustomerButton;
    private javax.swing.JButton ConfirmPet;
    private javax.swing.JButton ConfirmProduct;
    private javax.swing.JPanel Customer;
    private javax.swing.JTable CustomersTable;
    private javax.swing.JButton DeleteCustomer;
    private javax.swing.JButton DeleteItem;
    private javax.swing.JButton DeletePet;
    private javax.swing.JButton DeleteProduct;
    private javax.swing.JLabel DialogPictureLabel;
    private javax.swing.JRadioButton FemaleRadioButton;
    private javax.swing.JRadioButton FlushNoButton;
    private javax.swing.JRadioButton FlushYesButton;
    private javax.swing.ButtonGroup GenderButtonGroup;
    private javax.swing.JTextField IDProducttxt;
    private javax.swing.JPanel Invoice;
    private javax.swing.JTable InvoiceTable;
    private javax.swing.JButton Logout;
    private javax.swing.JRadioButton MaleRadioButton;
    private javax.swing.JPanel Pet;
    private javax.swing.JTable PetTable;
    private javax.swing.JLabel PictureLabel;
    private javax.swing.JTextField Priceissuetxt;
    private javax.swing.JTextField Priceselltxt;
    private javax.swing.JPanel Product;
    private javax.swing.JTextField ProductNametxt;
    private javax.swing.JTable ProductTable;
    private com.toedter.calendar.JDateChooser Receiptdate;
    private javax.swing.JButton Save;
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JComboBox<String> SearchBoxPet;
    private javax.swing.JComboBox<String> SearchCustomerBox;
    private javax.swing.JTextField SearchPettxt;
    private javax.swing.JComboBox<String> SearchProductBox;
    private javax.swing.JTextField SearchProducttxt;
    private javax.swing.JTextField Searchcustomertxt;
    private javax.swing.JRadioButton ShotsNoButton;
    private javax.swing.JRadioButton ShotsYesButton;
    private javax.swing.JTextField Stocktxt;
    private javax.swing.JButton UpdateCustomer;
    private javax.swing.JButton UpdatePet;
    private javax.swing.JButton UpdateProduct;
    private javax.swing.JButton Updatepicture;
    private javax.swing.JButton addPetButton;
    private javax.swing.JTextField agetxt;
    private javax.swing.JFormattedTextField amountivtxt;
    private javax.swing.JTextField colortxt;
    private javax.swing.JTextField cusAddresstxt;
    private javax.swing.JFormattedTextField cusBirthdaytxt;
    private javax.swing.JTextField cusCCCDtxt;
    private javax.swing.JTextField cusEmailtxt;
    private javax.swing.JTextField cusNametxt;
    private javax.swing.JTextField cusNationtxt;
    private javax.swing.JTextField cusPhonetxt;
    private javax.swing.JTextField cusSocialMediatxt;
    private javax.swing.JComboBox<String> customeridcbiv;
    private javax.swing.JTextField customernametxt;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JTextField healthtxt;
    private javax.swing.JTextField invoiceidtxt;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField petbreedtxt;
    private javax.swing.JComboBox<String> petidcbiv;
    private javax.swing.JTextField petidtxt;
    private javax.swing.JTextField petnameivtxt;
    private javax.swing.JTextField petnametxt;
    private javax.swing.JFormattedTextField petpriceivtxt;
    private javax.swing.JTextField pettypeivtxt;
    private javax.swing.JTextField pettypetxt;
    private javax.swing.JFormattedTextField pricetxt;
    private javax.swing.JButton print;
    private javax.swing.JComboBox<String> productidcbiv;
    private javax.swing.JTextField productnameivtxt;
    private javax.swing.JFormattedTextField productpriceivtxt;
    private javax.swing.JButton refreshiv;
    private javax.swing.JComboBox<String> staffidcbiv;
    private javax.swing.JTextField staffnametxt;
    private javax.swing.JTextField totalcosttxt;
    private javax.swing.JTextField weighttxt;
    // End of variables declaration//GEN-END:variables
}
