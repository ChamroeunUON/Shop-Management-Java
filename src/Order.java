
//import com.sun.glass.events.KeyEvent;
//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import static org.eclipse.persistence.platform.database.oracle.plsql.OraclePLSQLTypes.Int;
public class Order extends javax.swing.JFrame {
 Connection conn;
    PreparedStatement pst;
    ResultSet rs;
 DefaultTableModel mode;   
    public Order() {
        super("Order Info");
        setResizable(false);
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Logo.png")));
        conn=javaconnection.ConnecrDb();
        jcom();
        comBind();
        comBindCUS();
        Update_table();
        
    }
  
  /*  public void showText(){
        DecimalFormat doll = new DecimalFormat("#,##0.00");
        try{
         int row=jTable1.getSelectedRow();
         String click=(jTable1.getModel().getValueAt(row, 0).toString());
            String sql="select Total_Amount,Deposit,Balance from order where Order_ID='"+click+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                String T=rs.getString("Total_Amount").replaceAll("$","");
                String To="$ "+doll.format(T);
                txt_total.setText(To);
                
                String D=rs.getString("deposit").replaceAll("$", "");
                String De="$ "+doll.format(D);
                txt_deposit.setText(De);
                
                String B=rs.getString("Balance");
                String Ba="$ "+doll.format(B);
                txt_balance.setText(Ba);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }*/
    public void showDetail(){
     try{
         int row=jTable1.getSelectedRow();
         String click=(jTable1.getModel().getValueAt(row, 0).toString());
         String sql="Select Product_id,Pro_Name,Sale_Unit,Quantity,Amount from order_detail where order_id='"+click+"'";
         pst=conn.prepareStatement(sql);
         rs=pst.executeQuery();
         jTable2.setModel(DbUtils.resultSetToTableModel(rs));
         try{
             String sql1="Select Total_Amount,Deposit,Balance from shopdatabase.`order` where Order_ID='"+click+"'";
             pst=conn.prepareStatement(sql1);
             rs=pst.executeQuery();
             while(rs.next()){
                // if(rs.getString("Order_ID").equals(click)){
                 //lbl_total1.setText("Total Amount      :  ");
                 lbl_deposit1.setText("Paid                :");
                 lbl_balance1.setText("Balance            :");
                 lbl_total.setText(rs.getString("Total_Amount"));
                 lbl_deposit.setText(rs.getString("Deposit"));
                 lbl_balance.setText(rs.getString("Balance"));
                // }
             }
         }catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
         }
          }catch(Exception e){
         JOptionPane.showMessageDialog(null,e);
     }
        
 }
    public void Update_table()
    {
        try{
            pst=conn.prepareStatement("SELECT Order_ID,Staff_ID,Cus_id,Date,Total_Amount FROM shopdatabase.order where status='Active'");
            rs=pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            //CountCus();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
     public void sum(){
        double sum=0;
        DecimalFormat dollar = new DecimalFormat("#,##0.00");
        try{
           for(int i=0;i<jTable2.getRowCount();++i){
               sum=sum+Float.parseFloat((String) jTable2.getValueAt(i,4)); 
               
           }
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        String F="$ "+dollar.format(sum);
        
        lbl_total.setText(F);
       // txt_total.setText(F);
    }
       public void comBindCUS(){
      javaconnection mq=new javaconnection();
      Connection con=mq.ConnecrDb();
      Statement st;
      try{
          st=conn.createStatement();
          rs=st.executeQuery("Select id from customer where status='Active'");
          //String sql="Select Product_ID from product";
          while(rs.next()){
              //int item[]={rs.getInt(1)};
            //  int T=rs.getInt(1);
              cbo_cus.addItem(rs.getString(1));
              
          }
          
      }catch(Exception e){
          JOptionPane.showMessageDialog(null,e);
      }finally{
          try{
              String sql="select * from customer where id='"+(String)cbo_cus.getSelectedItem()+"'";
              pst=conn.prepareStatement(sql);
              rs=pst.executeQuery();
              if(rs.next()){
                  txt_cus_name.setText(rs.getString(2));
                  //txt_cat.setText(rs.getString(2));
                  //txt_unit.setText(rs.getString(5));
                  
              }
          }catch(Exception e){
              JOptionPane.showMessageDialog(null,e);
          }
      }
      
      
  } 
     public void comBind(){
      javaconnection mq=new javaconnection();
      Connection con=mq.ConnecrDb();
      Statement st;
      try{
          st=con.createStatement();
          rs=st.executeQuery("Select Product_ID from product");
          //String sql="Select Product_ID from product";
          while(rs.next()){
              //int item[]={rs.getInt(1)};
            //  int T=rs.getInt(1);
              cbo_pro.addItem(rs.getString(1));
              
          }
          
      }catch(Exception e){
          JOptionPane.showMessageDialog(null,e);
      }finally{
          try{
              String sql="select * from product where Product_ID='"+(String)cbo_pro.getSelectedItem()+"'";
              pst=conn.prepareStatement(sql);
              rs=pst.executeQuery();
              if(rs.next()){
                  txt_name.setText(rs.getString(3));
                  //txt_cat.setText(rs.getString(2));
                  txt_unit.setText(rs.getString(5));
              }
          }catch(Exception e){
              JOptionPane.showMessageDialog(null,e);
          }
      }
      
      
  } 
        public void jcom()
{
    try{
        String x="Active";
        String y="Cleaner";
        String z="Security";
    String SQL="select * from emp where status='Active' and position!='Cleaner'and position!='Security';";//"select * from emp where status='"+x+"' AND position!='"+y+"' AND position!='"+z+"'";
    pst=conn.prepareStatement(SQL);
    rs=pst.executeQuery();
        while(rs.next())
        {
            cbo_emp.addItem(rs.getString("id"));
        }
    }catch(Exception e)
    {
        JOptionPane.showMessageDialog(null,"JComcobox function :"+e);
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

        jTextField14 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txt_qty = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_order = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_unit = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        txt_date = new com.toedter.calendar.JDateChooser();
        cbo_pro = new javax.swing.JComboBox<>();
        cbo_cus = new javax.swing.JComboBox<>();
        cbo_emp = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txt_cus_name = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_deposit = new javax.swing.JLabel();
        lbl_balance = new javax.swing.JLabel();
        lbl_total = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_insert = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        btn_update = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        lbl_total1 = new javax.swing.JLabel();
        lbl_deposit1 = new javax.swing.JLabel();
        lbl_balance1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 153, 0));
        jLabel5.setText("Quantity         ");

        txt_qty.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_qty.setForeground(new java.awt.Color(102, 102, 0));
        txt_qty.setEnabled(false);
        txt_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_qtyKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 153, 0));
        jLabel7.setText("Unit_Price  ");

        jLabel9.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 153, 0));
        jLabel9.setText("Cus_ID       ");

        jLabel2.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 153, 0));
        jLabel2.setText("Product_ID      ");

        txt_order.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_order.setForeground(new java.awt.Color(102, 102, 0));
        txt_order.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 153, 0));
        jLabel4.setText("Staff_ID           ");

        txt_unit.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_unit.setForeground(new java.awt.Color(102, 102, 0));
        txt_unit.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 153, 0));
        jLabel6.setText("Order_date    ");

        jLabel14.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 153, 0));
        jLabel14.setText("Pro_Name  ");

        txt_name.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_name.setForeground(new java.awt.Color(102, 102, 0));
        txt_name.setEnabled(false);
        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameActionPerformed(evt);
            }
        });

        txt_date.setForeground(new java.awt.Color(102, 102, 0));
        txt_date.setDateFormatString("dd/MM/yyyy");
        txt_date.setEnabled(false);
        txt_date.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N

        cbo_pro.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        cbo_pro.setForeground(new java.awt.Color(102, 102, 0));
        cbo_pro.setEnabled(false);
        cbo_pro.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbo_proPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cbo_pro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_proActionPerformed(evt);
            }
        });

        cbo_cus.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        cbo_cus.setForeground(new java.awt.Color(102, 102, 0));
        cbo_cus.setEnabled(false);
        cbo_cus.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cbo_cusPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        cbo_emp.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        cbo_emp.setForeground(new java.awt.Color(102, 102, 0));
        cbo_emp.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 153, 0));
        jLabel13.setText("Cus_Name   ");

        txt_cus_name.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_cus_name.setForeground(new java.awt.Color(102, 102, 0));
        txt_cus_name.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 153, 0));
        jLabel1.setText("Order_ID       ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_cus_name)
                    .addComponent(txt_order)
                    .addComponent(txt_name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbo_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_unit, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbo_cus, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbo_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_order, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_cus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbo_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_cus_name, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_unit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbl_deposit.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        lbl_deposit.setForeground(new java.awt.Color(153, 0, 102));
        lbl_deposit.setText("                               ");

        lbl_balance.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        lbl_balance.setForeground(new java.awt.Color(153, 0, 102));
        lbl_balance.setText("                                           ");

        lbl_total.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        lbl_total.setForeground(new java.awt.Color(153, 0, 102));
        lbl_total.setText("                           ");

        jTable1.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jTable1.setForeground(new java.awt.Color(153, 0, 153));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order_ID", "Staff_id", "Cus_ID", "Date", "Total_Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable1KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton3.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(204, 0, 51));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/New_ico.png"))); // NOI18N
        jButton3.setText("New");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(204, 0, 51));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Go-back-icon.png"))); // NOI18N
        jButton8.setText("Back");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        btn_save.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        btn_save.setForeground(new java.awt.Color(204, 0, 51));
        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Save_ico.png"))); // NOI18N
        btn_save.setText("Save");
        btn_save.setEnabled(false);
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_insert.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        btn_insert.setForeground(new java.awt.Color(204, 0, 51));
        btn_insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/new order-icon.png"))); // NOI18N
        btn_insert.setText("Insert");
        btn_insert.setEnabled(false);
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(204, 0, 51));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/exit-icon.png"))); // NOI18N
        jButton10.setText("Close");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jButton14.setForeground(new java.awt.Color(204, 0, 51));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Refresh-icon.png"))); // NOI18N
        jButton14.setText("Refresh");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });

        btn_update.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        btn_update.setForeground(new java.awt.Color(204, 0, 51));
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/update-icon.png"))); // NOI18N
        btn_update.setText("Update");
        btn_update.setEnabled(false);
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 0, 0));
        jLabel15.setText("Search Here");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton14)
                .addGap(26, 26, 26)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_insert)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_update)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton10)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable2.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jTable2.setForeground(new java.awt.Color(153, 0, 153));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pro_ID", "Pro_Name", "Sale_Unit", "QTY", "Amount"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        lbl_total1.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        lbl_total1.setForeground(new java.awt.Color(102, 153, 0));
        lbl_total1.setText("Grand Total    :");

        lbl_deposit1.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        lbl_deposit1.setForeground(new java.awt.Color(102, 153, 0));
        lbl_deposit1.setText("                           ");

        lbl_balance1.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        lbl_balance1.setForeground(new java.awt.Color(102, 153, 0));
        lbl_balance1.setText("                            ");

        jLabel8.setIcon(new javax.swing.ImageIcon("C:\\Shop Management\\Images\\lo1new.png")); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 0, 51));
        jLabel10.setText("ORDER INFORMATION");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(980, 980, 980)
                .addComponent(jLabel3))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(234, 234, 234))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(567, 567, 567)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(lbl_total1)
                                                .addGap(18, 18, 18)
                                                .addComponent(lbl_total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lbl_deposit1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lbl_balance1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(lbl_deposit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lbl_balance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(8, 8, 8)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_total1)
                    .addComponent(lbl_total, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_deposit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_deposit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_balance, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_balance1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1057, 660));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
         
       
        /* DefaultTableModel model=(DefaultTableModel)jTable2.getModel();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
            String date=sdf.format(txt_date.getDate());
        try{
            String sql="INSERT INTO `shopdatabase`.`order` (`Order_ID`, `Staff_id`, `Cus_id`, `Total_Amount`, `Date`, `Deposit`, `Balance`) VALUES ('"+txt_order.getText()+"', '"+(String)cbo_emp.getSelectedItem()+"', '"+(String)cbo_cus.getSelectedItem()+"', '"+txt_total.getText()+"', '"+date+"', '"+txt_deposit.getText()+"', '"+txt_balance.getText()+"')";
           // String //sql="insert into order valuesINSERT INTO `shopdatabase`.`order` (`Order_ID`, `Staff_id`, `Cus_id`, `Total_Amount`, `Date`, `Deposit`, `Balance`) VALUES('"+txt_order.getText()+"','"+(String)cbo_emp.getSelectedItem()+"'"
                  //  + ",'"+(String)cbo_cus.getSelectedItem()+"','"+txt_total.getText()+"','"+date+"','"+txt_deposit.getText()+"','"+txt_balance.getText()+"')";
                  //  + "('"+txt_order.getText()+"','"+(String)cbo_emp.getSelectedItem()+"','"+(String)cbo_cus.getSelectedItem()+"',"
                    //+ "'"+txt_total.getText()+"','"+date+"','"+txt_deposit.getText()+"','"+txt_balance.getText()+"')";
            pst=conn.prepareStatement(sql);
            pst.executeUpdate();
          JOptionPane.showMessageDialog(null,"Information has been saved!!");
            try{
                DefaultTableModel mod=(DefaultTableModel)jTable2.getModel();
                for(int i=0;i<jTable2.getRowCount();++i){
                     String sql1="insert into order_detail values('"+txt_order.getText()+"','"+(String)mod.getValueAt(i,0)+"',"
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
                      // JOptionPane.showMessageDialog(null,"Product Updated!!");
                      Update_table();
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
        }    */
        DecimalFormat doll=new DecimalFormat("#,##0.00");
         String T=lbl_total.getText();
        Float To=Float.parseFloat(T.replaceAll("[$,]",""));
        String BBB=doll.format(To/2);
            try{
             String deposit=JOptionPane.showInputDialog("Please input deposit at lease 50% \n$ "+BBB+"or greater ");
           Float DE=Float.parseFloat(deposit);
          // float DDD=To/2;
           if(DE>=To/2 && DE<=To){
                    try{  
        DefaultTableModel model=(DefaultTableModel)Order.jTable2.getModel();
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
            String date=sdf.format(Order.txt_date.getDate());
        try{
            
            String De="$"+doll.format(DE);
            //String T=Order.lbl_total.getText().replaceAll("$","");
            
            String TO="$"+doll.format(To);
            Float B=To-DE;
            String Ba="$"+doll.format(B);
            String sql="INSERT INTO `shopdatabase`.`order` (`Order_ID`, `Staff_id`, `Cus_id`, `Total_Amount`, `Date`, `Deposit`, `Balance`,`status`) "
                    + "VALUES ('"+Order.txt_order.getText()+"', '"+(String)Order.cbo_emp.getSelectedItem()+"',"
                    + " '"+(String)Order.cbo_cus.getSelectedItem()+"', '"+Order.lbl_total.getText()+"',"
                    + " '"+date+"', '"+De+"', '"+Ba+"','Active')";
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
                      
                   }
                   }
                   // JOptionPane.showMessageDialog(null,"Product Updated!!");
                   }catch(Exception e){
                       JOptionPane.showMessageDialog(null,"Update"+e);
                   }
                    // JOptionPane.showMessageDialog(null,"Inserted into Import Detail!!");
                    Update_table();
                }
               
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No OK!!"+e);
        }
         JOptionPane.showMessageDialog(null,"Information has been saved!!");
         // invoice();
      }catch(Exception e){
          JOptionPane.showMessageDialog(null,e);
      }    
           }
           if(DE<=0 || DE<To/2){
               JOptionPane.showMessageDialog(null,"Please deposit at lease 50%"
                       + "Equal or Greater that '"+To/2+"'");
           }
           if(DE>To){
               JOptionPane.showMessageDialog(null,"Please check balance again It's greater than total amount\n");
           }
            }catch(Exception e){
                   JOptionPane.showMessageDialog(null,e);
                   }
        
    }//GEN-LAST:event_btn_saveActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        Home ob=new Home();
        ob.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameActionPerformed

    private void cbo_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_proActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_proActionPerformed

    private void cbo_proPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbo_proPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
          String S=(String)cbo_pro.getSelectedItem();
        String sql="select * from product where Product_ID='"+S+"'";
        try{
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                txt_name.setText(rs.getString(3));
                //txt_cat.setText(rs.getString(2));
                txt_unit.setText(rs.getString(5));
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_cbo_proPopupMenuWillBecomeInvisible

    private void cbo_cusPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbo_cusPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
         String S=(String)cbo_cus.getSelectedItem();
        String sql="select * from customer where id='"+S+"'";
        try{
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                txt_cus_name.setText(rs.getString(2));
              
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_cbo_cusPopupMenuWillBecomeInvisible

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        btn_update.setEnabled(false);
        lbl_total.setText("");
        lbl_deposit.setText("");
        lbl_deposit1.setText("");
        lbl_balance.setText("");
        lbl_balance1.setText("");
        txt_order.setEnabled(true);
        cbo_pro.setEnabled(true);
        cbo_cus.setEnabled(true);
        cbo_emp.setEnabled(true);
        txt_date.setEnabled(true);
        txt_qty.setEnabled(true);
        btn_insert.setEnabled(true);
        btn_save.setEnabled(true);
         DefaultTableModel tMOdel = (DefaultTableModel) jTable2.getModel();
        tMOdel.setRowCount(0);
        
        try{
           
            String sql="SELECT max(Order_ID) FROM shopdatabase.order";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                int S=rs.getInt(1)+1;
                String s=""+S;
                txt_order.setText(s);
           
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
      DefaultTableModel tMOdel = (DefaultTableModel) jTable2.getModel();
        //tMOdel.setRowCount(0);
         int i = 0;
int rowcount= jTable2.getRowCount();
          if(txt_date.getDate()==null){ JOptionPane.showMessageDialog(null,"Please Select Date!!!");
          txt_date.requestFocus();
        }else if(txt_qty.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please Input QTY!!!");
            txt_qty.requestFocus();
        }
        else{
            
           Integer ID = Integer.parseInt((String)cbo_pro.getSelectedItem().toString().trim());
           mode=(DefaultTableModel)jTable2.getModel();
           
           //JOptionPane.showMessageDialog(null,"Item has Inserted");
          
           if(rowcount>0){
           for(i=0; i<rowcount; i++){
               //JOptionPane.showMessageDialog(null,""+ID+"\n"+jTable2.getModel().getValueAt(i,0)); 
               int idcompare=Integer.parseInt(jTable2.getValueAt(i, 0).toString());
               if(ID==idcompare){
                   JOptionPane.showMessageDialog(null,"Item has Inserted");
                   return;
               }        
           }
           }
           if(rowcount==i){
                 try{
                    Float QT=Float.parseFloat(txt_qty.getText());
                    String sql="select Quantity from product where Product_ID='"+(String)cbo_pro.getSelectedItem()+"'";
                    pst=conn.prepareStatement(sql);
                    rs=pst.executeQuery();
                    while(rs.next()){
                        Float QY=rs.getFloat("Quantity");//Float.parseFloat(rs.getString("Quantity"));
                        if(QY<QT){
                            JOptionPane.showMessageDialog(null,"Not Enough Product In stock");
                        }else if(QY>=QT){
                              try{
            DecimalFormat doll=new DecimalFormat("#,##0.00");
            String id=(String)cbo_pro.getSelectedItem();
            String name=txt_name.getText();
            String unit=txt_unit.getText();
            String qty=txt_qty.getText();
            Float Q=Float.parseFloat(txt_qty.getText());
            Float U=Float.parseFloat(txt_unit.getText());
            Float A=Q*U;
            String UN="$ "+doll.format(U);
            String QTY=doll.format(Q)+" PCS";
            DecimalFormat dol=new DecimalFormat("0.00");
            String amount=""+dol.format(A);
            DefaultTableModel mod=(DefaultTableModel)jTable2.getModel();
            String row[]={id,name,UN,QTY,amount};
            mod.addRow(row);
              double sum=0;
        DecimalFormat dollar = new DecimalFormat("#,##0.00");
        try{
           for(i=0;i<jTable2.getRowCount();++i){
               sum=sum+Float.parseFloat((String) jTable2.getValueAt(i,4)); 
           }
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        String F="$"+dollar.format(sum);
      
        lbl_total.setText(F);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
                        }
                    }
                    
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,e);
                }
           }
           }
          
    }//GEN-LAST:event_btn_insertActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        showDetail();
        btn_update.setEnabled(true);
        btn_save.setEnabled(false);
        btn_insert.setEnabled(false);
     //   showText();
       
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        // TODO add your handling code here:
         showDetail();
    }//GEN-LAST:event_jTable1KeyTyped

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
         Update_table();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        String T=lbl_total.getText();
        String D=lbl_deposit.getText();
        Float To=Float.parseFloat(T.replaceAll("[$,]",""));
        Float De=Float.parseFloat(D.replaceAll("[$,]",""));
        if(T.equals(D)){    
            if (JOptionPane.showConfirmDialog(null, "For this customer already paid\n Are you sure?", "WARNING",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        // yes option
                     try{
          // JOptionPane.showMessageDialog(null,"For this customer already paid!");
             int row=jTable1.getSelectedRow();
            String click=(jTable1.getModel().getValueAt(row, 0).toString());
            String sql="UPDATE `order` SET `status`='Inactive' WHERE (`Order_ID`='"+click+"')";
            pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Successfully!!");
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
            } else {
                
        // no option
           //Order ob=new Order();
           //ob.show();
            }
       /* try{
           JOptionPane.showMessageDialog(null,"For this customer already paid!");
             int row=jTable1.getSelectedRow();
            String click=(jTable1.getModel().getValueAt(row, 0).toString());
            String sql="UPDATE `order` SET `status`='Inactive' WHERE (`Order_ID`='"+click+"')";
            pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Successfully!!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }*/
        }
        if(To>De){
            try{
               String balance=JOptionPane.showInputDialog("This Customer already paid '"+lbl_deposit.getText()+"'\n Please input the left amount '"+lbl_balance.getText()+"'");
           Float B=Float.parseFloat(lbl_balance.getText().replaceAll("[$,]",""));
           Float Ba=Float.parseFloat(balance);
           //JOptionPane.showMessageDialog(null,""+B+"\n"+Ba);
           if(Ba.equals(B)){
               try{
          // JOptionPane.showMessageDialog(null,"For this customer already paid!");
             int row=jTable1.getSelectedRow();
            String click=(jTable1.getModel().getValueAt(row, 0).toString());
            String sql="UPDATE `order` SET `status`='Inactive' WHERE (`Order_ID`='"+click+"')";
            pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            Update_table();
            JOptionPane.showMessageDialog(null,"Successfully!!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
           }
           else if(Ba<B){
               try{
                   JOptionPane.showMessageDialog(null,"Not Enought!!\n Please charge the left amount "+lbl_balance.getText());
               }catch(Exception e){
                   JOptionPane.showMessageDialog(null,e);
               }
           }
           else if(Ba>B){
               JOptionPane.showMessageDialog(null,"The left amount is only "+lbl_balance.getText()+"\n Please try again");
           }
             
            }catch(Exception e){
                
            }
           // JOptionPane.showMessageDialog(null,"Info");
          
        }
    }//GEN-LAST:event_btn_updateActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased

      TableRowSorter<TableModel> sorter; 
     sorter = new TableRowSorter<>(((DefaultTableModel) jTable1.getModel()));
    sorter.setRowFilter(RowFilter.regexFilter(txt_search.getText()));
    jTable1.setRowSorter(sorter);
    }//GEN-LAST:event_txt_searchKeyReleased

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
     
    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_qtyKeyTyped
        // TODO add your handling code here:
           char c=evt.getKeyChar();
if(Character.isDigit(c)|| (c==KeyEvent.VK_DELETE))
{
} else {
    getToolkit().beep();
    evt.consume();
        }
    }//GEN-LAST:event_txt_qtyKeyTyped

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
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Order().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    public static javax.swing.JComboBox<String> cbo_cus;
    public static javax.swing.JComboBox<String> cbo_emp;
    public static javax.swing.JComboBox<String> cbo_pro;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JTable jTable1;
    public static javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JLabel lbl_balance;
    private javax.swing.JLabel lbl_balance1;
    private javax.swing.JLabel lbl_deposit;
    private javax.swing.JLabel lbl_deposit1;
    public static javax.swing.JLabel lbl_total;
    private javax.swing.JLabel lbl_total1;
    public static javax.swing.JTextField txt_cus_name;
    public static com.toedter.calendar.JDateChooser txt_date;
    public static javax.swing.JTextField txt_name;
    public static javax.swing.JTextField txt_order;
    public static javax.swing.JTextField txt_qty;
    private javax.swing.JTextField txt_search;
    public static javax.swing.JTextField txt_unit;
    // End of variables declaration//GEN-END:variables
    private Collection getOrderProducts(){
        ArrayList<Products> arrayList=new ArrayList();
        for(int i=0;i<jTable2.getRowCount();++i){
            String pname=(String) jTable2.getValueAt(i, 0);
            String price=(String) jTable1.getValueAt(i, 1);
            int qty=(int) jTable2.getValueAt(i, 2);
            String amount=(String) jTable1.getValueAt(i, 3);
            arrayList.add(new Products(pname, price, qty, amount));
            
            
        }
       
     return arrayList;
        
    }
    private void invoice() {
     try {
         JasperReport jr=JasperCompileManager.compileReport("C:\\Shop Management\\src\\Invoice.jrxml");
         HashMap para=new HashMap();
         para.put("Order_ID",txt_order.getText());
         para.put("Cus_ID",cbo_cus.getSelectedItem().toString());
         para.put("Staff_ID",cbo_emp.getSelectedItem().toString());
         para.put("Date", txt_date.getDate());

         JRBeanCollectionDataSource jcs=new JRBeanCollectionDataSource(getOrderProducts());
         JasperPrint jp=JasperFillManager.fillReport(jr, para,jcs);
         JasperViewer.viewReport(jp,false);
     } catch (JRException ex) {
         Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    
}
