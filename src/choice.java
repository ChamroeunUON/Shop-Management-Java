
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ba Boo
 */
public class choice extends javax.swing.JFrame {
 Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    /**
     * Creates new form choice
     */
    public choice() {
        
        initComponents();
        conn=javaconnection.ConnecrDb();
         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Logo.png")));
        check();
        
        
    }
    public void check(){
       ButtonGroup bt=new ButtonGroup();
       bt.add(rd_pay);
       bt.add(rd_balance);
      
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rd_pay = new javax.swing.JRadioButton();
        rd_balance = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        txt_deposit = new javax.swing.JTextField();
        btn_ok = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Choice", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 24), new java.awt.Color(153, 0, 0))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(102, 0, 102));
        jPanel1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N

        rd_pay.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        rd_pay.setForeground(new java.awt.Color(102, 0, 102));
        rd_pay.setText("Pay All");
        rd_pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_payActionPerformed(evt);
            }
        });

        rd_balance.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        rd_balance.setForeground(new java.awt.Color(102, 0, 102));
        rd_balance.setText("Balance");
        rd_balance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rd_balanceActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 102, 0));
        jLabel1.setText("Deposit");

        txt_deposit.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        txt_deposit.setForeground(new java.awt.Color(153, 51, 0));
        txt_deposit.setText("$");
        txt_deposit.setEnabled(false);
        txt_deposit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_depositKeyTyped(evt);
            }
        });

        btn_ok.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        btn_ok.setForeground(new java.awt.Color(0, 51, 0));
        btn_ok.setText("Ok");
        btn_ok.setEnabled(false);
        btn_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_okActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 51, 0));
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_deposit))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(48, Short.MAX_VALUE)
                        .addComponent(btn_ok, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(rd_pay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rd_balance)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rd_pay)
                    .addComponent(rd_balance))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_deposit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(btn_ok))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(266, 227));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        Order ob=new Order();
        
        ob.show(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void rd_balanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_balanceActionPerformed
        // TODO add your handling code here:
        btn_ok.setEnabled(true);
        if(rd_balance.isSelected())
            txt_deposit.setText("");
            txt_deposit.setEnabled(true);
            
    }//GEN-LAST:event_rd_balanceActionPerformed

    private void rd_payActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rd_payActionPerformed
        // TODO add your handling code here:
        btn_ok.setEnabled(true);
        if(rd_pay.isSelected())
            txt_deposit.setText("$");
            txt_deposit.setEnabled(false);
    }//GEN-LAST:event_rd_payActionPerformed

    private void btn_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_okActionPerformed
        if(rd_balance.isSelected()){
             try{
         Float DEP=Float.parseFloat(txt_deposit.getText());
        Float TOT=Float.parseFloat(Order.lbl_total.getText().replaceAll("[$,]","").trim());
        DecimalFormat Dolll=new DecimalFormat("0.00");
        String TT=Dolll.format(TOT/2);
            if(DEP==null||DEP==0){
                JOptionPane.showMessageDialog(null,"Please deposit balance 50% equal or greater than $ "+TT);
                txt_deposit.requestFocus();
            }
            else if(DEP<0){
                JOptionPane.showMessageDialog(null,"Please check money\nCan not less than Zero!!");
                txt_deposit.setText("");
                txt_deposit.requestFocus();
            }
            else if(DEP<TOT/2.00){
                JOptionPane.showMessageDialog(null,"Please deposit balance 50% equal or greater than $ "+TT);
                txt_deposit.setText("");
                txt_deposit.requestFocus();
            }
            else if(DEP>TOT){
                JOptionPane.showMessageDialog(null,"The Balance is greater that total amount\nPlease Check the balance againt");
                txt_deposit.setText("");
                txt_deposit.requestFocus();
            }
            else if(DEP>=TOT/2.00){
         
        try{  // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel)Order.jTable2.getModel();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
            String date=sdf.format(Order.txt_date.getDate());
        try{
            DecimalFormat doll=new DecimalFormat("#,##0.00");
            Float DE=Float.parseFloat(txt_deposit.getText());
            String De="$"+doll.format(DE);
            //String T=Order.lbl_total.getText().replaceAll("$","");
            Float To=Float.parseFloat(Order.lbl_total.getText().replaceAll("[$,]", ""));
            String TO="$"+doll.format(To);
            Float B=To-DE;
            String Ba="$"+doll.format(B);
            String sql="INSERT INTO `shopdatabase`.`order` (`Order_ID`, `Staff_id`, `Cus_id`, `Total_Amount`, `Date`, `Deposit`, `Balance`,`status`) VALUES ('"+Order.txt_order.getText()+"', '"+(String)Order.cbo_emp.getSelectedItem()+"', '"+(String)Order.cbo_cus.getSelectedItem()+"', '"+Order.lbl_total.getText()+"', '"+date+"', '"+De+"', '"+Ba+"','Active')";
           // String //sql="insert into order valuesINSERT INTO `shopdatabase`.`order` (`Order_ID`, `Staff_id`, `Cus_id`, `Total_Amount`, `Date`, `Deposit`, `Balance`) VALUES('"+txt_order.getText()+"','"+(String)cbo_emp.getSelectedItem()+"'"
                  //  + ",'"+(String)cbo_cus.getSelectedItem()+"','"+txt_total.getText()+"','"+date+"','"+txt_deposit.getText()+"','"+txt_balance.getText()+"')";
                  //  + "('"+txt_order.getText()+"','"+(String)cbo_emp.getSelectedItem()+"','"+(String)cbo_cus.getSelectedItem()+"',"
                    //+ "'"+txt_total.getText()+"','"+date+"','"+txt_deposit.getText()+"','"+txt_balance.getText()+"')";
            pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            try{
                // DefaultTableModel mod = new DefaultTableModel();
                 //JTable table = new JTable(mod);
                DefaultTableModel mod=(DefaultTableModel)Order.jTable2.getModel();
                for(int i=0;i<Order.jTable2.getRowCount();i++){
                     String sql1="insert into order_detail values('"+Order.txt_order.getText()+"','"+(String)mod.getValueAt(i,0)+"',"
                             + "'"+(String)mod.getValueAt(i,1)+"','"+(String)mod.getValueAt(i,3)+"','"+(String)mod.getValueAt(i,2)+"','"+(String)mod.getValueAt(i,4)+"')";
                     pst=conn.prepareStatement(sql1);
                     pst.executeUpdate();
                     try{
                       String sq1="select * from product";
                   pst=conn.prepareStatement(sq1);
                   rs=pst.executeQuery();
                   String D=(String)model.getValueAt(i,0).toString();
                   while(rs.next()){
                   if(D.equals(rs.getString("Product_ID"))){
                       Float Q=Float.parseFloat(model.getValueAt(i,3).toString().replaceAll("PCS",""));
                       Float A=rs.getFloat("Quantity");
                       Float X=A-Q;
                       String sq2="update product set Quantity='"+X+"' where Product_ID='"+D+"'";
                       pst=conn.prepareStatement(sq2);
                       pst.executeUpdate();
                         setVisible(false);
                        Order ob=new Order();
                        ob.show(true);
        
                   }
                   }
                  
                   }catch(Exception e){
                       JOptionPane.showMessageDialog(null,"Update"+e);
                   }
                    // JOptionPane.showMessageDialog(null,"Inserted into Import Detail!!");
                }
               
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No OK!!"+e);
        }    
      }catch(Exception e){
          JOptionPane.showMessageDialog(null,e);
      }
      JOptionPane.showMessageDialog(null,"You already paid for deposit $"+txt_deposit.getText()+"");
        }
            }catch(Exception e){
           JOptionPane.showMessageDialog(null,"Can not null");
           txt_deposit.setText("");
           txt_deposit.requestFocus();
       }
        }
       
        if(rd_pay.isSelected()){
              try{  // TODO add your handling code here:
        DefaultTableModel model=(DefaultTableModel)Order.jTable2.getModel();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
            String date=sdf.format(Order.txt_date.getDate());
        try{
            DecimalFormat doll=new DecimalFormat("#,##0.00");
            Float DE=Float.parseFloat(Order.lbl_total.getText().replaceAll("[$,]", ""));
            String De="$"+doll.format(DE);
            //String T=Order.lbl_total.getText().replaceAll("$","");
            Float To=Float.parseFloat(Order.lbl_total.getText().replaceAll("[$,]", ""));
            String TO="$"+doll.format(To);
            Float B=To-DE;
            String Ba="$"+doll.format(B);
            String sql="INSERT INTO `shopdatabase`.`order` (`Order_ID`, `Staff_id`, `Cus_id`, `Total_Amount`, `Date`, `Deposit`, `Balance`,`status`) VALUES ('"+Order.txt_order.getText()+"', '"+(String)Order.cbo_emp.getSelectedItem()+"', '"+(String)Order.cbo_cus.getSelectedItem()+"', '"+Order.lbl_total.getText()+"', '"+date+"', '"+De+"', '"+Ba+"','Active')";
           // String //sql="insert into order valuesINSERT INTO `shopdatabase`.`order` (`Order_ID`, `Staff_id`, `Cus_id`, `Total_Amount`, `Date`, `Deposit`, `Balance`) VALUES('"+txt_order.getText()+"','"+(String)cbo_emp.getSelectedItem()+"'"
                  //  + ",'"+(String)cbo_cus.getSelectedItem()+"','"+txt_total.getText()+"','"+date+"','"+txt_deposit.getText()+"','"+txt_balance.getText()+"')";
                  //  + "('"+txt_order.getText()+"','"+(String)cbo_emp.getSelectedItem()+"','"+(String)cbo_cus.getSelectedItem()+"',"
                    //+ "'"+txt_total.getText()+"','"+date+"','"+txt_deposit.getText()+"','"+txt_balance.getText()+"')";
            pst=conn.prepareStatement(sql);
            pst.executeUpdate();
        //  JOptionPane.showMessageDialog(null,"Information has been saved!!");
            try{
                DefaultTableModel mod=(DefaultTableModel)Order.jTable2.getModel();
                for(int i=0;i<Order.jTable2.getRowCount();++i){
                     String sql1="insert into order_detail values('"+Order.txt_order.getText()+"','"+(String)mod.getValueAt(i,0)+"',"
                             + "'"+(String)mod.getValueAt(i,1)+"','"+(String)mod.getValueAt(i,3)+"','"+(String)mod.getValueAt(i,2)+"','"+(String)mod.getValueAt(i,4)+"')";
                     pst=conn.prepareStatement(sql1);
                     pst.executeUpdate();
                     try{
                       String sq1="select * from product";
                   pst=conn.prepareStatement(sq1);
                   rs=pst.executeQuery();
                   String D=(String)model.getValueAt(i,0).toString();
                   while(rs.next()){
                   if(D.equals(rs.getString("Product_ID"))){
                       Float Q=Float.parseFloat(model.getValueAt(i,3).toString().replaceAll("PCS",""));
                       Float A=rs.getFloat("Quantity");
                       Float X=A-Q;
                       String sq2="update product set Quantity='"+X+"' where Product_ID='"+D+"'";
                       pst=conn.prepareStatement(sq2);
                       pst.executeUpdate();
                         setVisible(false);
                    Order ob=new Order();
                    ob.show(true);
        
                   }
                   }
                   // JOptionPane.showMessageDialog(null,"Product Updated!!");
                   }catch(Exception e){
                       JOptionPane.showMessageDialog(null,"Update"+e);
                   }
                    // JOptionPane.showMessageDialog(null,"Inserted into Import Detail!!");
                }
               
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No OK!!"+e);
        }
         JOptionPane.showMessageDialog(null,"Information has been saved!!");
      }catch(Exception e){
          JOptionPane.showMessageDialog(null,e);
      }
              
        }
       
    
    }//GEN-LAST:event_btn_okActionPerformed
 private boolean isNumber(char ch){
        return ch >= '0' && ch <= '9';
    }
  private boolean isValidSignal(char ch){
        if( (txt_deposit.getText() == null || "".equals(txt_deposit.getText().trim()) ) && ch == '-'){
            return true;
        }

        return false;
    }
private boolean validatePoint(char ch){
        if(ch != '.'){
            return false;
        }
        if(txt_deposit.getText() == null || "".equals(txt_deposit.getText().trim())){
            txt_deposit.setText("0.");
            return false;
        }
        else if("-".equals(txt_deposit.getText())){
            txt_deposit.setText("");
        }
        

        return true;
    }

    private void txt_depositKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_depositKeyTyped
        // TODO add your handling code here:
        char ch = evt.getKeyChar();
                if (!isNumber(ch) && !isValidSignal(ch) && !validatePoint(ch)  && ch != '\b') {
                    evt.consume();
                }
                
    /*   char c=evt.getKeyChar();
if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE))&&!isValidSignal(c))
{
    getToolkit().beep();
    evt.consume();
}*/
    }//GEN-LAST:event_txt_depositKeyTyped

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
            java.util.logging.Logger.getLogger(choice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(choice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(choice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(choice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new choice().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ok;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rd_balance;
    private javax.swing.JRadioButton rd_pay;
    private javax.swing.JTextField txt_deposit;
    // End of variables declaration//GEN-END:variables

   
}
