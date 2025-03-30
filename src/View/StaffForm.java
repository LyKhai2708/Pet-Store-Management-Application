/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;
import javax.swing.table.DefaultTableModel;
import DAO.StaffDAO;
import java.util.ArrayList;
import Model.Staff;
import javax.swing.JOptionPane;
/**
 *
 * @author 84896
 */
public class StaffForm extends javax.swing.JFrame {
    String[] columm = new String[]{"Staff_id","Staff_name","Nation","phone","email","Staff_Media"};
    DefaultTableModel table = new DefaultTableModel();
    
    /**
     * Creates new form StaffForm
     */
    public StaffForm() {
        initComponents();
        StaffDAO StaffDATA = new StaffDAO();
        ArrayList<Staff> list = StaffDATA.getAll();
        StaffTable.setModel(table);
        table.setColumnIdentifiers(columm);
        addData(list);
        fillBox();
    }
    public void addData(ArrayList<Staff> list) {
        for(Staff st : list){
            table.addRow(new String[]{st.getId(), st.getName(), st.getNation(), st.getPhone(), st.getEmail(),st.getSocialmedia()});
        }
    }
    public void fillBox(){
       for(String s : columm){
       SearchStaffBox.addItem(s);
       }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AddStaffDialog = new javax.swing.JDialog();
        Staffphonetxt = new javax.swing.JTextField();
        ConfirmStaff = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        StaffSocialMediatxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Staffidtext = new javax.swing.JTextField();
        Staffnametxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Staffemailtxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        StaffNationtxt = new javax.swing.JTextField();
        Refresh = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        StaffTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        SearchStaffBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        searchStafftxt = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        addStaff = new javax.swing.JButton();
        UPDATEStaff = new javax.swing.JButton();
        DeleteStaff = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        AddStaffDialog.setModal(true);

        Staffphonetxt.setToolTipText("");

        ConfirmStaff.setText("Confirm");
        ConfirmStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmStaffActionPerformed(evt);
            }
        });

        jLabel9.setText("Social Media");

        jLabel3.setText("Add Staff");

        jLabel4.setText("Name");

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

        jLabel5.setText("ID");

        Staffemailtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StaffemailtxtActionPerformed(evt);
            }
        });

        jLabel6.setText("Email");

        jLabel7.setText("Phone");

        jLabel8.setText("Nation");

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
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addGroup(AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                        .addComponent(jLabel3)))
                .addContainerGap(181, Short.MAX_VALUE))
        );
        AddStaffDialogLayout.setVerticalGroup(
            AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddStaffDialogLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Staffidtext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Staffnametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StaffNationtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddStaffDialogLayout.createSequentialGroup()
                        .addComponent(Staffphonetxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(Staffemailtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(AddStaffDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(StaffSocialMediatxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addComponent(jLabel7))
                .addGap(30, 30, 30)
                .addComponent(ConfirmStaff)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Refresh.setText("Refresh");
        Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshActionPerformed(evt);
            }
        });

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
        jScrollPane1.setViewportView(StaffTable);
        if (StaffTable.getColumnModel().getColumnCount() > 0) {
            StaffTable.getColumnModel().getColumn(0).setResizable(false);
            StaffTable.getColumnModel().getColumn(1).setResizable(false);
            StaffTable.getColumnModel().getColumn(2).setResizable(false);
            StaffTable.getColumnModel().getColumn(3).setResizable(false);
        }

        jScrollPane2.setViewportView(jScrollPane1);

        SearchStaffBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchStaffBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Search");

        searchStafftxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchStafftxtActionPerformed(evt);
            }
        });
        searchStafftxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchStafftxtKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SearchStaffBox, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchStafftxt, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchStaffBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(searchStafftxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        addStaff.setText("ADD");
        addStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStaffActionPerformed(evt);
            }
        });

        UPDATEStaff.setText("UPDATE");
        UPDATEStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UPDATEStaffActionPerformed(evt);
            }
        });

        DeleteStaff.setText("DELETE");
        DeleteStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteStaffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DeleteStaff)
                    .addComponent(addStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UPDATEStaff))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(addStaff)
                .addGap(18, 18, 18)
                .addComponent(UPDATEStaff)
                .addGap(18, 18, 18)
                .addComponent(DeleteStaff)
                .addGap(0, 166, Short.MAX_VALUE))
        );

        jLabel2.setText("Staff List");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(300, 300, 300)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(293, 293, 293)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Refresh))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Refresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(13, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStaffActionPerformed
        AddStaffDialog.setVisible(true);
    }//GEN-LAST:event_addStaffActionPerformed

    private void RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshActionPerformed
         table.setRowCount(0);
         StaffDAO StaffDATA = new StaffDAO();
         ArrayList<Staff> list = StaffDATA.getAll();
         addData(list);
    }//GEN-LAST:event_RefreshActionPerformed

    private void SearchStaffBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchStaffBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchStaffBoxActionPerformed

    private void searchStafftxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchStafftxtKeyReleased
          StaffDAO StaffDATA = new StaffDAO();
          ArrayList<Staff> list = StaffDATA.search((String)SearchStaffBox.getSelectedItem(),searchStafftxt.getText());
          table.setRowCount(0);
          addData(list);
          if(searchStafftxt.getText().equals("")){
              list = StaffDATA.getAll();
              table.setRowCount(0);
              addData(list);
            }
    }//GEN-LAST:event_searchStafftxtKeyReleased

    private void UPDATEStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UPDATEStaffActionPerformed
        StaffDAO staffDATA = new StaffDAO();
        int result = 0;
        for(int i = 0 ; i < table.getRowCount();i++) {
            if(table.getValueAt(i, 0).equals("") || 
            table.getValueAt(i, 1).equals("") || 
            table.getValueAt(i, 2).equals("") || 
            table.getValueAt(i, 3).equals("") || 
            table.getValueAt(i, 4).equals("") ||
            table.getValueAt(i, 5).equals("")){
           JOptionPane.showMessageDialog(StaffForm.this,"Please enter full information");
           break;
         }else{
            Staff st = new Staff();
            st.setId((String)table.getValueAt(i, 0));
            st.setName((String)table.getValueAt(i,1));
            st.setNation((String)table.getValueAt(i,2));
            st.setPhone((String)table.getValueAt(i,3));
            st.setEmail((String)table.getValueAt(i,4));
            st.setSocialmedia((String)table.getValueAt(i,5));
            result = staffDATA.update(st); //result = 0 => that bai , result > 0 => thanh cong
           }
        }
        if(result == 0 ){
          JOptionPane.showMessageDialog(StaffForm.this,"Cannot Update");
        }else {
            JOptionPane.showMessageDialog(StaffForm.this,"Updated!!!");
        }
        table.setRowCount(0);
        ArrayList<Staff> list = staffDATA.getAll();
        addData(list);
        
    }//GEN-LAST:event_UPDATEStaffActionPerformed

    private void DeleteStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteStaffActionPerformed
        StaffDAO StaffDATA = new StaffDAO();
        int row = StaffTable.getSelectedRow();
        if(row == -1){
           JOptionPane.showMessageDialog(StaffForm.this, "Please choose staff to delete!");
        }else {
            int cf = JOptionPane.showConfirmDialog(StaffForm.this, "Are you sure to delete this staff?");
            if(cf == JOptionPane.YES_OPTION) {
                String id = String.valueOf(table.getValueAt(row, 0));
                StaffDATA.delete(id);
                table.setRowCount(0);
                ArrayList<Staff> list = StaffDATA.getAll();
                addData(list);
            }
        }
    }//GEN-LAST:event_DeleteStaffActionPerformed

    private void ConfirmStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmStaffActionPerformed
        StaffDAO staffDATA = new StaffDAO();
        if( Staffidtext.getText().equals("") ||
            Staffnametxt.getText().equals("") ||
            StaffNationtxt.getText().equals("") ||
            Staffphonetxt.getText().equals("") ||
            Staffemailtxt.getText().equals("") ||
            StaffSocialMediatxt.getText().equals("")){
            JOptionPane.showMessageDialog(rootPane,"Missing information");
        }else if(Staffidtext.getText().length() > 3){
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

    private void searchStafftxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchStafftxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchStafftxtActionPerformed

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
            java.util.logging.Logger.getLogger(StaffForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaffForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaffForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaffForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StaffForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog AddStaffDialog;
    private javax.swing.JButton ConfirmStaff;
    private javax.swing.JButton DeleteStaff;
    private javax.swing.JButton Refresh;
    private javax.swing.JComboBox<String> SearchStaffBox;
    private javax.swing.JTextField StaffNationtxt;
    private javax.swing.JTextField StaffSocialMediatxt;
    private javax.swing.JTable StaffTable;
    private javax.swing.JTextField Staffemailtxt;
    private javax.swing.JTextField Staffidtext;
    private javax.swing.JTextField Staffnametxt;
    private javax.swing.JTextField Staffphonetxt;
    private javax.swing.JButton UPDATEStaff;
    private javax.swing.JButton addStaff;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField searchStafftxt;
    // End of variables declaration//GEN-END:variables
}
