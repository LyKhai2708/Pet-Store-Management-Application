/*
[239,235,220]
[248,218,218]
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
public class AdminPage extends javax.swing.JFrame {
    String[] Usercolumm = new String[]{"Staff_id","username","password"};
    String[] Customercolumm = new String[]{"CustomerCCCD", "CustomerName","DateOfBirth", "Cus_Phone", "Email", "Address", "SocialMedia", "Gender", "Nation"};
    String[] Petcolumm = new String[]{"Pet_ID", "Pet_Type", "Pet_Breed","Pet_Name", "Color","Age", "Weight", "Picture", "Gender","Shots", "Flush","Heath","Price"};
    String[] Productcolumm = new String[]{"Product_ID", "Product_Name", "Price_issue", "Price_sell", "Stock", "Receipt_Day" };
    String[] Invoicecolumm = new String[]{"Pet/Product ID","Pet/Product Name","Amount","Price"};
    String[] Staffcolumm = new String[]{"Staff_id","Staff_name","Nation","phone","email","Staff_Media"};
    String[] Revenuecolumm = new String[]{"InvoiceID","StaffID","CustomerID","SellDay","TotalCost"};
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    DefaultTableModel Usertable = new DefaultTableModel();
    DefaultTableModel Stafftable = new DefaultTableModel();
    DefaultTableModel Customertable = new DefaultTableModel();
    DefaultTableModel Producttable = new DefaultTableModel();
    DefaultTableModel Pettable = new DefaultTableModel();
    DefaultTableModel Invoicetable = new DefaultTableModel();
    DefaultTableModel Revenuetable = new DefaultTableModel();
    float productprice = 0;
    float dummy = 0;
    float totalprice = 0;
    String imagepath = "C:\\Users\\84896\\Documents\\NetBeansProjects\\DBMS\\src\\icons\\dog.png";
    String current = "";
    /**
     * Creates new form NewAdminPage
     */
    public AdminPage() {
  
        initComponents();
        /*
        them du lieu cho bang User
        */
        UsersDAO UserDATA = new UsersDAO();
        ArrayList<Users> list = UserDATA.getAll();
        UsersTable.setModel(Usertable);
        Usertable.setColumnIdentifiers(Usercolumm);
        addUserData(list);
        addRoleBoxData();
        addIDBoxData();
        /*
        them du lieu cho bang Customer
        */
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
       
        /*
        them du lieu cho bang Staff
        */
        StaffDAO StaffDATA = new StaffDAO();
        ArrayList<Staff> stafflist = StaffDATA.getAll();
        StaffTable.setModel(Stafftable);
        Stafftable.setColumnIdentifiers(Staffcolumm);
        addStaffData(stafflist);
        fillStaffBox();
        
        /*
        them cot cho bang Revenue
        */
        RevenueTable.setModel(Revenuetable);
        Revenuetable.setColumnIdentifiers(Revenuecolumm);
    }
    
    //STAFF
    public void addStaffData(ArrayList<Staff> list) {
        for(Staff st : list){
            Stafftable.addRow(new String[]{st.getId(), st.getName(), st.getNation(), st.getPhone(), st.getEmail(),st.getSocialmedia()});
        }
    }
    public void fillStaffBox(){
       for(String s : Staffcolumm){
       SearchStaffBox.addItem(s);
       }
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
    //USER
    public void addUserData(ArrayList<Users> list) {
        for(Users u : list){
            Usertable.addRow(new String[]{u.getId(), u.getUsername(), u.getPassword()});
        }
    }
     public void addRoleBoxData(){
         RoleDAO roleDATA = new RoleDAO();
         ArrayList<Role> list = roleDATA.getAll();
         for(Role r : list){
             RoleBoxu.addItem(String.valueOf(r.getRole_id()));
         }
     }
      public void addIDBoxData(){
         StaffDAO staffDATA = new StaffDAO();
         ArrayList<Staff> list = staffDATA.getAll();
         for(Staff s : list){
             IDBoxu.addItem(String.valueOf(s.getId()));
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

        AddUserDialog = new javax.swing.JDialog();
        Confirm = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        IDBoxu = new javax.swing.JComboBox<>();
        RoleBoxu = new javax.swing.JComboBox<>();
        usernametxt = new javax.swing.JTextField();
        passwordtxt = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        passwordcftxt = new javax.swing.JPasswordField();
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
        Button1 = new javax.swing.JRadioButton();
        Button2 = new javax.swing.JRadioButton();
        ConfirmCustomerButton = new javax.swing.JButton();
        cusBirthday = new com.toedter.calendar.JDateChooser();
        GenderButtonGroup = new javax.swing.ButtonGroup();
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
        Receiptdate = new com.toedter.calendar.JDateChooser();
        AddStaffDialog = new javax.swing.JDialog();
        Staffphonetxt = new javax.swing.JTextField();
        ConfirmStaff = new javax.swing.JButton();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        StaffSocialMediatxt = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        Staffidtext = new javax.swing.JTextField();
        Staffnametxt = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        Staffemailtxt = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        StaffNationtxt = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Staff = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        UPDATEStaff = new javax.swing.JButton();
        DeleteStaff = new javax.swing.JButton();
        addStaff = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        StaffTable = new javax.swing.JTable();
        jLabel61 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        SearchStaffBox = new javax.swing.JComboBox<>();
        jLabel62 = new javax.swing.JLabel();
        searchStafftxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        Account = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        SearchAccounttxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Update = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        Add = new javax.swing.JButton();
        Scroll = new javax.swing.JScrollPane();
        UsersTable = new javax.swing.JTable();
        Customer = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
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
        revenue = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        Startday = new com.toedter.calendar.JDateChooser();
        Finishday = new com.toedter.calendar.JDateChooser();
        jLabel71 = new javax.swing.JLabel();
        Calculate = new javax.swing.JButton();
        Revenuetxt = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        RevenueTable = new javax.swing.JTable();
        RefreshRevenue = new javax.swing.JButton();

        AddUserDialog.setBackground(new java.awt.Color(248, 218, 218));
        AddUserDialog.setMinimumSize(new java.awt.Dimension(478, 400));
        AddUserDialog.setModal(true);

        Confirm.setText("Confirm");
        Confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmActionPerformed(evt);
            }
        });

        jLabel3.setText("Staff ID");

        jLabel4.setText("Username");

        jLabel5.setText("Password");

        jLabel6.setText("Role");

        jLabel7.setText("Add Account");

        RoleBoxu.setMinimumSize(new java.awt.Dimension(500, 400));
        RoleBoxu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoleBoxuActionPerformed(evt);
            }
        });

        passwordtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordtxtActionPerformed(evt);
            }
        });

        jLabel8.setText("Confirm password");

        javax.swing.GroupLayout AddUserDialogLayout = new javax.swing.GroupLayout(AddUserDialog.getContentPane());
        AddUserDialog.getContentPane().setLayout(AddUserDialogLayout);
        AddUserDialogLayout.setHorizontalGroup(
            AddUserDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddUserDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddUserDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddUserDialogLayout.createSequentialGroup()
                        .addGroup(AddUserDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AddUserDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel8)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addGroup(AddUserDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IDBoxu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(usernametxt)
                            .addComponent(passwordtxt)
                            .addComponent(passwordcftxt, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                            .addComponent(RoleBoxu, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(AddUserDialogLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(Confirm)))
                        .addGap(69, 69, 69))
                    .addGroup(AddUserDialogLayout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(jLabel7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        AddUserDialogLayout.setVerticalGroup(
            AddUserDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddUserDialogLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddUserDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(IDBoxu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(AddUserDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(usernametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(AddUserDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(passwordtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(AddUserDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(passwordcftxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(AddUserDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RoleBoxu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(29, 29, 29)
                .addComponent(Confirm)
                .addGap(23, 23, 23))
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

        GenderButtonGroup.add(Button1);
        Button1.setText("Male");
        Button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button1ActionPerformed(evt);
            }
        });

        GenderButtonGroup.add(Button2);
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
                                    .addComponent(cusBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(24, 24, 24)
                .addGroup(AddCustomerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddCustomerDialogLayout.createSequentialGroup()
                        .addGroup(AddCustomerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel17)
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
                        .addComponent(ConfirmCustomerButton))
                    .addComponent(cusBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

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

        Receiptdate.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout AddProductDialogLayout = new javax.swing.GroupLayout(AddProductDialog.getContentPane());
        AddProductDialog.getContentPane().setLayout(AddProductDialogLayout);
        AddProductDialogLayout.setHorizontalGroup(
            AddProductDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(Priceissuetxt)))
                .addGap(100, 100, 100))
            .addGroup(AddProductDialogLayout.createSequentialGroup()
                .addGap(299, 299, 299)
                .addGroup(AddProductDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ConfirmProduct)
                    .addComponent(jLabel37))
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
                        .addGap(19, 69, Short.MAX_VALUE))
                    .addGroup(AddProductDialogLayout.createSequentialGroup()
                        .addComponent(Receiptdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ConfirmProduct)
                        .addGap(18, 18, 18))))
        );

        AddStaffDialog.setMinimumSize(new java.awt.Dimension(400, 470));
        AddStaffDialog.setModal(true);

        Staffphonetxt.setToolTipText("");

        ConfirmStaff.setText("Confirm");
        ConfirmStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmStaffActionPerformed(evt);
            }
        });

        jLabel63.setText("Social Media");

        jLabel64.setText("Add Staff");

        jLabel65.setText("Name");

        Staffidtext.setToolTipText("Only 3 character");
        Staffidtext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StaffidtextMouseClicked(evt);
            }
        });

        Staffnametxt.setToolTipText("");
        Staffnametxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StaffnametxtActionPerformed(evt);
            }
        });

        jLabel66.setText("ID");

        Staffemailtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StaffemailtxtActionPerformed(evt);
            }
        });

        jLabel67.setText("Email");

        jLabel68.setText("Phone");

        jLabel69.setText("Nation");

        StaffNationtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StaffNationtxtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddStaffDialogLayout = new javax.swing.GroupLayout(AddStaffDialog.getContentPane());
        AddStaffDialog.getContentPane().setLayout(AddStaffDialogLayout);
        AddStaffDialogLayout.setHorizontalGroup(
            AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddStaffDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63)
                    .addGroup(AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(StaffSocialMediatxt, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(StaffNationtxt, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(Staffnametxt, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(Staffphonetxt, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(Staffemailtxt, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
                    .addComponent(Staffidtext))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(AddStaffDialogLayout.createSequentialGroup()
                .addGroup(AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddStaffDialogLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(ConfirmStaff))
                    .addGroup(AddStaffDialogLayout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jLabel64)))
                .addContainerGap(181, Short.MAX_VALUE))
        );
        AddStaffDialogLayout.setVerticalGroup(
            AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddStaffDialogLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(Staffidtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(Staffnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StaffNationtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddStaffDialogLayout.createSequentialGroup()
                        .addComponent(Staffphonetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel67)
                            .addComponent(Staffemailtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(StaffSocialMediatxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel63)))
                    .addComponent(jLabel68))
                .addGap(30, 30, 30)
                .addComponent(ConfirmStaff)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Welcome to Only Pet");
        setMinimumSize(new java.awt.Dimension(1047, 660));

        Staff.setBackground(new java.awt.Color(239, 235, 220));

        jPanel14.setBackground(new java.awt.Color(239, 235, 220));

        UPDATEStaff.setBackground(new java.awt.Color(248, 218, 218));
        UPDATEStaff.setText("UPDATE");
        UPDATEStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UPDATEStaffActionPerformed(evt);
            }
        });

        DeleteStaff.setBackground(new java.awt.Color(248, 218, 218));
        DeleteStaff.setText("DELETE");
        DeleteStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteStaffActionPerformed(evt);
            }
        });

        addStaff.setBackground(new java.awt.Color(248, 218, 218));
        addStaff.setText("ADD");
        addStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStaffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(UPDATEStaff, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(DeleteStaff, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(addStaff)
                .addGap(56, 56, 56)
                .addComponent(UPDATEStaff)
                .addGap(50, 50, 50)
                .addComponent(DeleteStaff)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jScrollPane5.setBackground(new java.awt.Color(239, 235, 220));

        StaffTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(StaffTable);

        jScrollPane4.setViewportView(jScrollPane5);

        jLabel61.setFont(new java.awt.Font("Fontype Animal Pet", 0, 24)); // NOI18N
        jLabel61.setText("Staff List");

        jPanel15.setBackground(new java.awt.Color(239, 235, 220));

        SearchStaffBox.setBackground(new java.awt.Color(248, 218, 218));
        SearchStaffBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchStaffBoxActionPerformed(evt);
            }
        });

        jLabel62.setText("Search");

        searchStafftxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchStafftxtKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SearchStaffBox, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchStafftxt, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchStaffBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62)
                    .addComponent(searchStafftxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        jButton1.setBackground(new java.awt.Color(248, 218, 218));
        jButton1.setText("Log Out");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout StaffLayout = new javax.swing.GroupLayout(Staff);
        Staff.setLayout(StaffLayout);
        StaffLayout.setHorizontalGroup(
            StaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StaffLayout.createSequentialGroup()
                .addGroup(StaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StaffLayout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addGroup(StaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(StaffLayout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(StaffLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(351, 351, 351)
                        .addComponent(jLabel61)))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        StaffLayout.setVerticalGroup(
            StaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StaffLayout.createSequentialGroup()
                .addGroup(StaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(StaffLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(StaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel61)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(StaffLayout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Staff", Staff);

        Account.setBackground(new java.awt.Color(239, 235, 220));

        jPanel1.setBackground(new java.awt.Color(239, 235, 220));

        jLabel1.setFont(new java.awt.Font("Fontype Animal Pet", 0, 24)); // NOI18N
        jLabel1.setText("Staff Account");

        jPanel2.setBackground(new java.awt.Color(239, 235, 220));

        SearchAccounttxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchAccounttxtActionPerformed(evt);
            }
        });
        SearchAccounttxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchAccounttxtKeyReleased(evt);
            }
        });

        jLabel2.setText("Search");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SearchAccounttxt, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchAccounttxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(385, 385, 385)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(239, 235, 220));

        Update.setBackground(new java.awt.Color(248, 218, 218));
        Update.setText("UPDATE");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });

        Delete.setBackground(new java.awt.Color(248, 218, 218));
        Delete.setText("DELETE");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        Add.setBackground(new java.awt.Color(248, 218, 218));
        Add.setText("ADD");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Delete))
                .addGap(0, 16, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(Add)
                .addGap(56, 56, 56)
                .addComponent(Update)
                .addGap(47, 47, 47)
                .addComponent(Delete)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        UsersTable.setModel(new javax.swing.table.DefaultTableModel(
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
        Scroll.setViewportView(UsersTable);

        javax.swing.GroupLayout AccountLayout = new javax.swing.GroupLayout(Account);
        Account.setLayout(AccountLayout);
        AccountLayout.setHorizontalGroup(
            AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AccountLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(AccountLayout.createSequentialGroup()
                .addContainerGap(160, Short.MAX_VALUE)
                .addComponent(Scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );
        AccountLayout.setVerticalGroup(
            AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AccountLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AccountLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91))
                    .addGroup(AccountLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Account", Account);

        Customer.setBackground(new java.awt.Color(239, 235, 220));

        jLabel9.setFont(new java.awt.Font("Fontype Animal Pet", 0, 24)); // NOI18N
        jLabel9.setText("Customers Information");

        jPanel4.setBackground(new java.awt.Color(239, 235, 220));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 861, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(239, 235, 220));

        SearchCustomerBox.setBackground(new java.awt.Color(248, 218, 218));

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

        jPanel5.setBackground(new java.awt.Color(239, 235, 220));

        AddCustomer.setBackground(new java.awt.Color(248, 218, 218));
        AddCustomer.setText("ADD");
        AddCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCustomerActionPerformed(evt);
            }
        });

        UpdateCustomer.setBackground(new java.awt.Color(248, 218, 218));
        UpdateCustomer.setText("UPDATE");
        UpdateCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateCustomerActionPerformed(evt);
            }
        });

        DeleteCustomer.setBackground(new java.awt.Color(248, 218, 218));
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

        javax.swing.GroupLayout CustomerLayout = new javax.swing.GroupLayout(Customer);
        Customer.setLayout(CustomerLayout);
        CustomerLayout.setHorizontalGroup(
            CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CustomerLayout.createSequentialGroup()
                        .addGap(0, 123, Short.MAX_VALUE)
                        .addGroup(CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(CustomerLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(49, 49, 49))
            .addGroup(CustomerLayout.createSequentialGroup()
                .addGap(349, 349, 349)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CustomerLayout.setVerticalGroup(
            CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(CustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(CustomerLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Customer", Customer);

        Pet.setBackground(new java.awt.Color(239, 235, 220));

        jPanel8.setBackground(new java.awt.Color(239, 235, 220));

        DeletePet.setBackground(new java.awt.Color(248, 218, 218));
        DeletePet.setText("DELETE");
        DeletePet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeletePetActionPerformed(evt);
            }
        });

        UpdatePet.setBackground(new java.awt.Color(248, 218, 218));
        UpdatePet.setText("UPDATE");
        UpdatePet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdatePetActionPerformed(evt);
            }
        });

        addPetButton.setBackground(new java.awt.Color(248, 218, 218));
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

        jPanel9.setBackground(new java.awt.Color(239, 235, 220));

        jLabel36.setText("Search");

        SearchPettxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchPettxtActionPerformed(evt);
            }
        });
        SearchPettxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchPettxtKeyReleased(evt);
            }
        });

        SearchBoxPet.setBackground(new java.awt.Color(248, 218, 218));

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

        Updatepicture.setBackground(new java.awt.Color(248, 218, 218));
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
                        .addContainerGap(38, Short.MAX_VALUE))
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
                                        .addGap(250, 250, 250)))))
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
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pet", Pet);

        Product.setBackground(new java.awt.Color(239, 235, 220));

        jPanel10.setBackground(new java.awt.Color(239, 235, 220));

        AddProduct.setBackground(new java.awt.Color(248, 218, 218));
        AddProduct.setText("ADD");
        AddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddProductActionPerformed(evt);
            }
        });

        DeleteProduct.setBackground(new java.awt.Color(248, 218, 218));
        DeleteProduct.setText("DELETE");
        DeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteProductActionPerformed(evt);
            }
        });

        UpdateProduct.setBackground(new java.awt.Color(248, 218, 218));
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

        jPanel11.setBackground(new java.awt.Color(239, 235, 220));

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

        jPanel16.setBackground(new java.awt.Color(239, 235, 220));

        SearchProductBox.setBackground(new java.awt.Color(248, 218, 218));

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
                .addContainerGap(128, Short.MAX_VALUE))
            .addGroup(ProductLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(422, 422, 422))
        );

        jTabbedPane1.addTab("Product", Product);

        Invoice.setBackground(new java.awt.Color(239, 235, 220));
        Invoice.setMinimumSize(new java.awt.Dimension(1023, 650));

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

        jPanel12.setBackground(new java.awt.Color(239, 235, 220));

        jLabel46.setText("StaffID");

        jLabel47.setText("CustomerID");

        jLabel48.setText("StaffName");

        staffnametxt.setEditable(false);

        jLabel49.setText("CustomerName");

        staffidcbiv.setBackground(new java.awt.Color(248, 218, 218));
        staffidcbiv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        staffidcbiv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                staffidcbivItemStateChanged(evt);
            }
        });

        customeridcbiv.setBackground(new java.awt.Color(248, 218, 218));
        customeridcbiv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        customeridcbiv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                customeridcbivItemStateChanged(evt);
            }
        });

        customernametxt.setEditable(false);

        jLabel50.setText("InvoiceID");

        jLabel51.setText("Date");

        date.setBackground(new java.awt.Color(239, 235, 220));

        refreshiv.setBackground(new java.awt.Color(248, 218, 218));
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
                .addContainerGap(143, Short.MAX_VALUE))
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

        Add1.setBackground(new java.awt.Color(248, 218, 218));
        Add1.setText("Add");
        Add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Add1ActionPerformed(evt);
            }
        });

        print.setBackground(new java.awt.Color(248, 218, 218));
        print.setText("Print");
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        Save.setBackground(new java.awt.Color(248, 218, 218));
        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(239, 235, 220));

        jLabel52.setText("ProductName");

        jLabel53.setText("Amount");

        jLabel54.setText("PetName");

        jLabel55.setText("PetType");

        jLabel56.setText("ProductID");

        jLabel57.setText("PetID");

        jLabel58.setText("Price");

        jLabel59.setText("Price");

        productidcbiv.setBackground(new java.awt.Color(248, 218, 218));
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

        petidcbiv.setBackground(new java.awt.Color(248, 218, 218));
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

        DeleteItem.setBackground(new java.awt.Color(248, 218, 218));
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

        revenue.setBackground(new java.awt.Color(239, 235, 220));

        jLabel70.setText("From Date");

        Startday.setBackground(new java.awt.Color(239, 235, 220));
        Startday.setMinimumSize(new java.awt.Dimension(100, 30));

        Finishday.setBackground(new java.awt.Color(239, 235, 220));
        Finishday.setMinimumSize(new java.awt.Dimension(88, 22));

        jLabel71.setText("To Date:");

        Calculate.setBackground(new java.awt.Color(248, 218, 218));
        Calculate.setText("Calculate");
        Calculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CalculateActionPerformed(evt);
            }
        });

        Revenuetxt.setEditable(false);

        jLabel72.setText("Revenue");

        jPanel17.setBackground(new java.awt.Color(239, 235, 220));

        RevenueTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane6.setViewportView(RevenueTable);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
        );

        RefreshRevenue.setBackground(new java.awt.Color(248, 218, 218));
        RefreshRevenue.setText("Refresh");
        RefreshRevenue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshRevenueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout revenueLayout = new javax.swing.GroupLayout(revenue);
        revenue.setLayout(revenueLayout);
        revenueLayout.setHorizontalGroup(
            revenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(revenueLayout.createSequentialGroup()
                .addGap(293, 293, 293)
                .addComponent(Calculate)
                .addGap(67, 67, 67)
                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Revenuetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(revenueLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(revenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(revenueLayout.createSequentialGroup()
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Finishday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(revenueLayout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addGap(18, 18, 18)
                        .addComponent(Startday, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RefreshRevenue)
                .addGap(102, 102, 102))
        );
        revenueLayout.setVerticalGroup(
            revenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(revenueLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(revenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(revenueLayout.createSequentialGroup()
                        .addComponent(RefreshRevenue)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(revenueLayout.createSequentialGroup()
                        .addGroup(revenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Startday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel70))
                        .addGap(16, 16, 16)
                        .addGroup(revenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Calculate)
                            .addComponent(Revenuetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel72))
                        .addGap(15, 15, 15)
                        .addGroup(revenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Finishday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Revenue", revenue);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1047, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SearchAccounttxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchAccounttxtActionPerformed

    }//GEN-LAST:event_SearchAccounttxtActionPerformed

    private void SearchAccounttxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchAccounttxtKeyReleased
        UsersDAO UserDATA = new UsersDAO();
        ArrayList<Users> list = UserDATA.search("Staff_id",SearchAccounttxt.getText());
        Usertable.setRowCount(0);
        addUserData(list);
        if(SearchAccounttxt.getText().equals("")){
            list = UserDATA.getAll();
            Usertable.setRowCount(0);
            addUserData(list);
        }
    }//GEN-LAST:event_SearchAccounttxtKeyReleased

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        AddUserDialog.setVisible(true);
    }//GEN-LAST:event_AddActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed
        UsersDAO UserDATA = new UsersDAO();
        int result = 0;
        for(int i = 0 ; i < Usertable.getRowCount();i++) {
            if(Usertable.getValueAt(i, 0).equals("") ||
                Usertable.getValueAt(i, 1).equals("") ||
                Usertable.getValueAt(i, 2).equals("")){
                JOptionPane.showMessageDialog(this,"Please enter full information");
                break;
            }else{
                Users u = new Users();
                u.setId((String)Usertable.getValueAt(i, 0));
                u.setUsername((String)Usertable.getValueAt(i,1));
                u.setPassword((String)Usertable.getValueAt(i,2));
                result = UserDATA.update(u);//result = 0 => that bai , result > 0 => thanh cong
            }
        }
        if(result == 0 ){
            JOptionPane.showMessageDialog(this,"Cannot Update");
        }else {
            JOptionPane.showMessageDialog(this,"Updated!!!");
        }
        Usertable.setRowCount(0);
        ArrayList<Users> list = UserDATA.getAll();
        addUserData(list);
    }//GEN-LAST:event_UpdateActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        UsersDAO UserDATA = new UsersDAO();
        int row = UsersTable.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "Please choose account to delete!");
        }else {
            int cf = JOptionPane.showConfirmDialog(this, "Are you sure to delete this account?");
            if(cf == JOptionPane.YES_OPTION) {
                String id = String.valueOf(Usertable.getValueAt(row, 0));
                UserDATA.delete(id);
                Usertable.setRowCount(0);
                ArrayList<Users> list = UserDATA.getAll();
                addUserData(list);
                JOptionPane.showMessageDialog(this, "Delete Success!");
            }
        }
    }//GEN-LAST:event_DeleteActionPerformed

    private void ConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmActionPerformed
        UsersDAO userDATA = new UsersDAO();
        if(passwordtxt.getText().isBlank() || passwordcftxt.getText().isBlank() || usernametxt.getText().isBlank()){
            JOptionPane.showMessageDialog(this, "Missing information");
        }else if(!passwordcftxt.getText().equals(passwordtxt.getText())){
            JOptionPane.showMessageDialog(this, "Invalid confirm password");
        }else {
            String id = String.valueOf(IDBoxu.getSelectedItem());
            String username = usernametxt.getText();
            String password = passwordtxt.getText();
            int role = Integer.parseInt(String.valueOf(RoleBoxu.getSelectedItem()));
            Users user = new Users(id,username,password,role);
            int result = userDATA.insert(user);
            if(result == 0){
                JOptionPane.showMessageDialog(this, "Add Failed!!");
            }else
            {
                Usertable.setRowCount(0);
                ArrayList<Users> list = userDATA.getAll();
                addUserData(list);
                JOptionPane.showMessageDialog(this, "Add Successful!!");
            }
        }
    }//GEN-LAST:event_ConfirmActionPerformed

    private void RoleBoxuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoleBoxuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RoleBoxuActionPerformed

    private void passwordtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordtxtActionPerformed

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
                JOptionPane.showMessageDialog(this, "Delete Success!");
            }
        }
    }//GEN-LAST:event_DeleteCustomerActionPerformed

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
        if(cusCCCDtxt.getText().isBlank() || cusNametxt.getText().isBlank() || cusBirthday.getDate().equals(null) ||
            cusPhonetxt.getText().isBlank() || cusEmailtxt.getText().isBlank() || cusAddresstxt.getText().isBlank() ||
            cusSocialMediatxt.getText().isBlank() || cusNationtxt.getText().isBlank() ||
            (!GenderButtonGroup.isSelected(Button1.getModel()) && !GenderButtonGroup.isSelected(Button2.getModel()))  ){
            JOptionPane.showMessageDialog(this, "Missing information");
        }else {
            try {
                String CCCD = cusCCCDtxt.getText();
                String Name = cusNametxt.getText();
                String DateValue = dateFormat.format(cusBirthday.getDate());
                Date Birthday = dateFormat.parse(DateValue);
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
                    Customertable.setRowCount(0);
                    ArrayList<Customers> list = cusDATA.getAll();
                    addCustomerData(list);
                    JOptionPane.showMessageDialog(this, "Add Successful!!");
                }
            }catch (ParseException ex) {
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_ConfirmCustomerButtonActionPerformed

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
                Pettable.setRowCount(0);
                ArrayList<Pet> list = petDATA.getAll();
                addPetData(list);
                JOptionPane.showMessageDialog(this, "Add Successful!!");
            }

        }
    }//GEN-LAST:event_ConfirmPetActionPerformed

    private void addPetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPetButtonActionPerformed
        AddPetDialog.setVisible(true);
        DialogPictureLabel.setIcon(ResizeImage(imagepath,DialogPictureLabel));
    }//GEN-LAST:event_addPetButtonActionPerformed

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
                JOptionPane.showMessageDialog(this, "Delete Success!");
            }
        }
    }//GEN-LAST:event_DeletePetActionPerformed

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
                    Producttable.setRowCount(0);
                    ArrayList<PetProduct> list = productDATA.getAll();
                    addProductData(list);
                    JOptionPane.showMessageDialog(this, "Add Successful!!");
                }
            }catch(Exception e){
                System.out.println("Error :" + e);
            }
        }
    }//GEN-LAST:event_ConfirmProductActionPerformed

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
                JOptionPane.showMessageDialog(this, "Delete Success!");
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
        ArrayList<PetProduct> list = productDATA.search(String.valueOf(SearchProductBox.getSelectedItem()),SearchProducttxt.getText());
        Producttable.setRowCount(0);
        addProductData(list);
        if(SearchProducttxt.getText().equals("")){
            list = productDATA.getAll();
            Producttable.setRowCount(0);
            addProductData(list);
        }
    }//GEN-LAST:event_SearchProducttxtKeyReleased

    private void IDProducttxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDProducttxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IDProducttxtActionPerformed

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
        date.setDate(null);
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

    private void addStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStaffActionPerformed
        AddStaffDialog.setVisible(true);
    }//GEN-LAST:event_addStaffActionPerformed

    private void UPDATEStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UPDATEStaffActionPerformed
        StaffDAO staffDATA = new StaffDAO();
        int result = 0;
        for(int i = 0 ; i < Stafftable.getRowCount();i++) {
            if(Stafftable.getValueAt(i, 0).equals("") ||
                Stafftable.getValueAt(i, 1).equals("") ||
                Stafftable.getValueAt(i, 2).equals("") ||
                Stafftable.getValueAt(i, 3).equals("") ||
                Stafftable.getValueAt(i, 4).equals("") ||
                Stafftable.getValueAt(i, 5).equals("")){
                JOptionPane.showMessageDialog(this,"Please enter full information");
                break;
            }else{
                Staff st = new Staff();
                st.setId((String)Stafftable.getValueAt(i, 0));
                st.setName((String)Stafftable.getValueAt(i,1));
                st.setNation((String)Stafftable.getValueAt(i,2));
                st.setPhone((String)Stafftable.getValueAt(i,3));
                st.setEmail((String)Stafftable.getValueAt(i,4));
                st.setSocialmedia((String)Stafftable.getValueAt(i,5));
                result = staffDATA.update(st); //result = 0 => that bai , result > 0 => thanh cong
            }
        }
        if(result == 0 ){
            JOptionPane.showMessageDialog(this,"Cannot Update");
        }else {
            JOptionPane.showMessageDialog(this,"Updated!!!");
        }
        Stafftable.setRowCount(0);
        ArrayList<Staff> list = staffDATA.getAll();
        addStaffData(list);

    }//GEN-LAST:event_UPDATEStaffActionPerformed

    private void DeleteStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteStaffActionPerformed
        StaffDAO StaffDATA = new StaffDAO();
        int row = StaffTable.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "Please choose staff to delete!");
        }else {
            int cf = JOptionPane.showConfirmDialog(this, "Are you sure to delete this staff?");
            if(cf == JOptionPane.YES_OPTION) {
                String id = String.valueOf(Stafftable.getValueAt(row, 0));
                UsersDAO userDATA = new UsersDAO();
                ArrayList<Users> test = userDATA.getbyID(id);
                if(!test.isEmpty()){
                    JOptionPane.showMessageDialog(this, "Please delete this staff account first");
                }else{
                StaffDATA.delete(id);
                Stafftable.setRowCount(0);
                ArrayList<Staff> list = StaffDATA.getAll();
                addStaffData(list);
                JOptionPane.showMessageDialog(this, "Delete Success!");
                }
            }
        }
    }//GEN-LAST:event_DeleteStaffActionPerformed

    private void SearchStaffBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchStaffBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchStaffBoxActionPerformed

    private void searchStafftxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchStafftxtKeyReleased

        StaffDAO StaffDATA = new StaffDAO();
        ArrayList<Staff> list = StaffDATA.search((String)SearchStaffBox.getSelectedItem(),searchStafftxt.getText());
        Stafftable.setRowCount(0);
        addStaffData(list);
        if(searchStafftxt.getText().equals("")){
            list = StaffDATA.getAll();
            Stafftable.setRowCount(0);
            addStaffData(list);
        }
    }//GEN-LAST:event_searchStafftxtKeyReleased

    private void ConfirmStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmStaffActionPerformed
        StaffDAO staffDATA = new StaffDAO();
        if( Staffidtext.getText().equals("") ||
            Staffnametxt.getText().equals("") ||
            StaffNationtxt.getText().equals("") ||
            Staffphonetxt.getText().equals("") ||
            Staffemailtxt.getText().equals("") ||
            StaffSocialMediatxt.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Missing information");
        }else if(Staffidtext.getText().length() > 3 || !(staffDATA.getbyID(Staffidtext.getText()).isEmpty())){
            JOptionPane.showMessageDialog(rootPane,"Invalid ID");
        }else if(!Staffemailtxt.getText().endsWith("@gmail.com")){
            JOptionPane.showMessageDialog(rootPane,"Invalid gmail");
        }else{
            String id = Staffidtext.getText();
            String name = Staffnametxt.getText();
            String Nation = StaffNationtxt.getText();
            String phone = Staffphonetxt.getText();
            String email = Staffemailtxt.getText();
            String SocialMedia = StaffSocialMediatxt.getText();
            Staff st = new Staff(id,name,Nation,phone,email,SocialMedia);
            int result = staffDATA.insert(st);
            if(result == 0){
                JOptionPane.showMessageDialog(rootPane,"Cannot Add Staff");
            }else{
                Stafftable.setRowCount(0);
                ArrayList<Staff> list = staffDATA.getAll();
                addStaffData(list);
                JOptionPane.showMessageDialog(rootPane,"Add Staff Successful!!!");
            }
        }
    }//GEN-LAST:event_ConfirmStaffActionPerformed

    private void StaffidtextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StaffidtextMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_StaffidtextMouseClicked

    private void StaffnametxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StaffnametxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StaffnametxtActionPerformed

    private void StaffemailtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StaffemailtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StaffemailtxtActionPerformed

    private void StaffNationtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StaffNationtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StaffNationtxtActionPerformed

    private void CalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CalculateActionPerformed
        try {
            float totalcost = 0;
            String sDateValue = dateFormat.format(Startday.getDate());
            Date StartDay = dateFormat.parse(sDateValue);
            String fDateValue = dateFormat.format(Finishday.getDate());
            Date FinishDay = dateFormat.parse(fDateValue);
            InvoiceDAO iv = new InvoiceDAO();
            ArrayList<Invoice> list = iv.findbyDate(StartDay, FinishDay);
            for(Invoice i : list){
                Revenuetable.addRow(new String[] {i.getInvoiceID(),
                                                  i.getStaffID(), i.getCustomerCCCD(),
                                                  String.valueOf(i.getSellDay()),
                                                  String.valueOf(i.getTotal_Price())});
                totalcost += i.getTotal_Price();
            }
                Revenuetxt.setText(String.valueOf(totalcost));
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_CalculateActionPerformed

    private void RefreshRevenueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshRevenueActionPerformed
        Revenuetable.setRowCount(0);
        Startday.setDate(null);
        Finishday.setDate(null);
        Revenuetxt.setText("");
    }//GEN-LAST:event_RefreshRevenueActionPerformed

    private void SearchPettxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchPettxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchPettxtActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Account;
    private javax.swing.JButton Add;
    private javax.swing.JButton Add1;
    private javax.swing.JButton AddCustomer;
    private javax.swing.JDialog AddCustomerDialog;
    private javax.swing.JDialog AddPetDialog;
    private javax.swing.JButton AddProduct;
    private javax.swing.JDialog AddProductDialog;
    private javax.swing.JDialog AddStaffDialog;
    private javax.swing.JDialog AddUserDialog;
    private javax.swing.JRadioButton Button1;
    private javax.swing.JRadioButton Button2;
    private javax.swing.JButton Calculate;
    private javax.swing.JButton ChoosePictureButton;
    private javax.swing.JButton Confirm;
    private javax.swing.JButton ConfirmCustomerButton;
    private javax.swing.JButton ConfirmPet;
    private javax.swing.JButton ConfirmProduct;
    private javax.swing.JButton ConfirmStaff;
    private javax.swing.JPanel Customer;
    private javax.swing.JTable CustomersTable;
    private javax.swing.JButton Delete;
    private javax.swing.JButton DeleteCustomer;
    private javax.swing.JButton DeleteItem;
    private javax.swing.JButton DeletePet;
    private javax.swing.JButton DeleteProduct;
    private javax.swing.JButton DeleteStaff;
    private javax.swing.JLabel DialogPictureLabel;
    private javax.swing.JRadioButton FemaleRadioButton;
    private com.toedter.calendar.JDateChooser Finishday;
    private javax.swing.JRadioButton FlushNoButton;
    private javax.swing.JRadioButton FlushYesButton;
    private javax.swing.ButtonGroup GenderButtonGroup;
    private javax.swing.JComboBox<String> IDBoxu;
    private javax.swing.JTextField IDProducttxt;
    private javax.swing.JPanel Invoice;
    private javax.swing.JTable InvoiceTable;
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
    private javax.swing.JButton RefreshRevenue;
    private javax.swing.JTable RevenueTable;
    private javax.swing.JTextField Revenuetxt;
    private javax.swing.JComboBox<String> RoleBoxu;
    private javax.swing.JButton Save;
    private javax.swing.JScrollPane Scroll;
    private javax.swing.JScrollPane ScrollPane;
    private javax.swing.JTextField SearchAccounttxt;
    private javax.swing.JComboBox<String> SearchBoxPet;
    private javax.swing.JComboBox<String> SearchCustomerBox;
    private javax.swing.JTextField SearchPettxt;
    private javax.swing.JComboBox<String> SearchProductBox;
    private javax.swing.JTextField SearchProducttxt;
    private javax.swing.JComboBox<String> SearchStaffBox;
    private javax.swing.JTextField Searchcustomertxt;
    private javax.swing.JRadioButton ShotsNoButton;
    private javax.swing.JRadioButton ShotsYesButton;
    private javax.swing.JPanel Staff;
    private javax.swing.JTextField StaffNationtxt;
    private javax.swing.JTextField StaffSocialMediatxt;
    private javax.swing.JTable StaffTable;
    private javax.swing.JTextField Staffemailtxt;
    private javax.swing.JTextField Staffidtext;
    private javax.swing.JTextField Staffnametxt;
    private javax.swing.JTextField Staffphonetxt;
    private com.toedter.calendar.JDateChooser Startday;
    private javax.swing.JTextField Stocktxt;
    private javax.swing.JButton UPDATEStaff;
    private javax.swing.JButton Update;
    private javax.swing.JButton UpdateCustomer;
    private javax.swing.JButton UpdatePet;
    private javax.swing.JButton UpdateProduct;
    private javax.swing.JButton Updatepicture;
    private javax.swing.JTable UsersTable;
    private javax.swing.JButton addPetButton;
    private javax.swing.JButton addStaff;
    private javax.swing.JTextField agetxt;
    private javax.swing.JFormattedTextField amountivtxt;
    private javax.swing.JTextField colortxt;
    private javax.swing.JTextField cusAddresstxt;
    private com.toedter.calendar.JDateChooser cusBirthday;
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPasswordField passwordcftxt;
    private javax.swing.JPasswordField passwordtxt;
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
    private javax.swing.JPanel revenue;
    private javax.swing.JTextField searchStafftxt;
    private javax.swing.JComboBox<String> staffidcbiv;
    private javax.swing.JTextField staffnametxt;
    private javax.swing.JTextField totalcosttxt;
    private javax.swing.JTextField usernametxt;
    private javax.swing.JTextField weighttxt;
    // End of variables declaration//GEN-END:variables
}
