
//import com.sun.glass.events.KeyEvent;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
import static org.eclipse.persistence.platform.database.oracle.plsql.OraclePLSQLTypes.Int;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ba Boo
 */
public class Import extends javax.swing.JFrame {
    DefaultTableModel mode;
 Connection conn;
    ResultSet rs;
    PreparedStatement pst;

    /**
     * Creates new form Import
     */
    public Import() {
        super("Import Product!");
        setResizable(false);
        initComponents();
        conn=javaconnection.ConnecrDb();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Logo.png")));
        jcomEmp();
        jcomSup();
        comBind();
        Update_table();
        //Update_Detail();
        //jCom_Pro();
       // pro_joint();
    }
      TableModel model=new DefaultTableModel();
    public void CountCus()
    {
        try
        {
              int count=jTable1.getRowCount();
        
        String C=""+count;
        lbl_count.setText(C);
        }catch(Exception e)
        {
            
        }
      
    }
    public void sum(){
        double sum=0;
        DecimalFormat dollar = new DecimalFormat("#,##0.00");
        try{
           for(int i=0;i<jTable2.getRowCount();++i){
               sum=sum+Float.parseFloat((String) jTable2.getValueAt(i,5)); 
               
           }
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        String F="$ "+dollar.format(sum);
        txt_total.setText(F);
       // txt_total.setText(F);
    }
    public void Update_Detail()
    {
        try{
            pst=conn.prepareStatement("SELECT Product_ID,Category,Product_Name,Unit_Price,Quantity,Amount FROM shopdatabase.import_detail");
            rs=pst.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
           
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
 public void showDetail(){
     try{
         int row=jTable1.getSelectedRow();
         String click=(jTable1.getModel().getValueAt(row, 0).toString());
         String sql="Select Product_ID,Category,Product_Name,Unit_Price,Quantity,Amount from import_detail where Import_ID='"+click+"'";
         pst=conn.prepareStatement(sql);
         rs=pst.executeQuery();
         jTable2.setModel(DbUtils.resultSetToTableModel(rs));
         /*while(rs.next()){
             if(click.equals(rs.getString(1))){
                // String query="select * from"
                try{
                   
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
             }
         }*/
     }catch(Exception e){
         JOptionPane.showMessageDialog(null,e);
     }
 }
 public void Update_table()
    {
        try{
            pst=conn.prepareStatement("SELECT Import_id,Sup_id,Staff_id,Date,Total_Amount FROM shopdatabase.import");
            rs=pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            CountCus();
            rs.close();
            pst.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
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
                  txt_pro_name.setText(rs.getString(3));
                  txt_cat.setText(rs.getString(2));
                  txt_unit.setText(rs.getString(4));
                  
              }
          }catch(Exception e){
              JOptionPane.showMessageDialog(null,e);
          }
      }
      
      
  } 
 
    public void addTable(){
        String import_id=txt_import_id.getText();
            String emp_id=(String)cbo_emp.getSelectedItem();
            String sup_id=(String)cbo_sup.getSelectedItem();
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
            String Date=sdf.format(txt_date.getDate());
            String amount=txt_total.getText();
            String row[]={import_id,emp_id,sup_id,Date,amount};
             DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
            model.addRow(row);
    }
   
     public void jcomSup()
{
    try{
    String SQL="select * from supplier where status='Active'";//"select * from emp where status='"+x+"' AND position!='"+y+"' AND position!='"+z+"'";
    pst=conn.prepareStatement(SQL);
    rs=pst.executeQuery();
        while(rs.next())
        {
          
            cbo_sup.addItem(rs.getString("ID"));
         
        }
    }catch(Exception e)
    {
        JOptionPane.showMessageDialog(null,"JComcobox function :"+e);
    }
}
    public void jcomEmp()
{
    try{
        String x="Active";
        String y="Cleaner";
        String z="Security";
    String SQL="select * from emp where status='Active' and position!='Cleaner'and position!='Security'";//"select * from emp where status='"+x+"' AND position!='"+y+"' AND position!='"+z+"'";
    pst=conn.prepareStatement(SQL);
    rs=pst.executeQuery();
        while(rs.next())
        {
            cbo_emp.addItem(rs.getString("id"));
        }
    }catch(Exception e)
    {
        JOptionPane.showMessageDialog(null,"JComcoboxEMP function :"+e);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_unit = new javax.swing.JTextField();
        txt_quantity = new javax.swing.JTextField();
        txt_date = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        txt_pro_name = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbo_sup = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbo_emp = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txt_import_id = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_cat = new javax.swing.JTextField();
        cbo_pro = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_total = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btn_new = new javax.swing.JButton();
        btn_insert = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        btn_close = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        lbl_count = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForeground(new java.awt.Color(204, 255, 204));

        jLabel5.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 0));
        jLabel5.setText("Import_Date      :");

        jLabel6.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 0));
        jLabel6.setText("Unit_Stock        :");

        jLabel7.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 0));
        jLabel7.setText("Quantity            :");

        txt_unit.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        txt_unit.setForeground(new java.awt.Color(153, 0, 0));
        txt_unit.setEnabled(false);

        txt_quantity.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        txt_quantity.setForeground(new java.awt.Color(153, 0, 0));
        txt_quantity.setEnabled(false);
        txt_quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_quantityKeyTyped(evt);
            }
        });

        txt_date.setForeground(new java.awt.Color(153, 0, 0));
        txt_date.setDateFormatString("dd/MM/yyyy");
        txt_date.setEnabled(false);
        txt_date.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 0));
        jLabel11.setText("Pro_ Name        :");

        txt_pro_name.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        txt_pro_name.setForeground(new java.awt.Color(153, 0, 0));
        txt_pro_name.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 0));
        jLabel8.setText("Category            :");

        cbo_sup.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        cbo_sup.setForeground(new java.awt.Color(153, 0, 0));
        cbo_sup.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 0));
        jLabel3.setText("Supplier_ID      :");

        cbo_emp.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        cbo_emp.setForeground(new java.awt.Color(153, 0, 0));
        cbo_emp.setEnabled(false);
        cbo_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_empActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 0));
        jLabel2.setText("Staff_ID            :");

        txt_import_id.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        txt_import_id.setForeground(new java.awt.Color(153, 0, 0));
        txt_import_id.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 0));
        jLabel1.setText("Import_ID          :");

        jLabel4.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 0));
        jLabel4.setText("Product_ID       :");

        txt_cat.setFont(new java.awt.Font("Lucida Sans", 1, 14)); // NOI18N
        txt_cat.setForeground(new java.awt.Color(153, 0, 0));
        txt_cat.setEnabled(false);

        cbo_pro.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        cbo_pro.setForeground(new java.awt.Color(153, 0, 0));
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_import_id, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(txt_date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_cat, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cbo_emp, 0, 198, Short.MAX_VALUE)
                    .addComponent(txt_unit)
                    .addComponent(cbo_pro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cbo_sup, 0, 212, Short.MAX_VALUE)
                    .addComponent(txt_pro_name)
                    .addComponent(txt_quantity))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_import_id, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txt_pro_name, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4)
                                        .addComponent(cbo_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_cat, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(txt_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_unit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jTable1.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jTable1.setForeground(new java.awt.Color(153, 0, 0));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Import_ID", "Supplier_ID", "Staff_ID", "Date", "Total Amount"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
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

        txt_total.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 51));
        jLabel10.setText("Total Amount   :");

        jTable2.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jTable2.setForeground(new java.awt.Color(51, 102, 0));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product_ID", "Category", "Product_Name", "Unit_Price", "Quantity", "Amount"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        btn_new.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        btn_new.setForeground(new java.awt.Color(255, 0, 0));
        btn_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/New_ico.png"))); // NOI18N
        btn_new.setText("New");
        btn_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newActionPerformed(evt);
            }
        });

        btn_insert.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        btn_insert.setForeground(new java.awt.Color(255, 0, 0));
        btn_insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Shopping-cart-insert-icon.png"))); // NOI18N
        btn_insert.setText("Insert");
        btn_insert.setEnabled(false);
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        btn_save.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        btn_save.setForeground(new java.awt.Color(255, 0, 0));
        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Save_ico.png"))); // NOI18N
        btn_save.setText("Save");
        btn_save.setEnabled(false);
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_back.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        btn_back.setForeground(new java.awt.Color(255, 0, 0));
        btn_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Go-back-icon.png"))); // NOI18N
        btn_back.setText("Back");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        btn_close.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        btn_close.setForeground(new java.awt.Color(255, 0, 0));
        btn_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/exit-icon.png"))); // NOI18N
        btn_close.setText("Close");
        btn_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_closeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_new, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_close, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_insert, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_new, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_close, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btn_save, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        txt_search.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 0, 0));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Refresh-icon.png"))); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 51));
        jLabel12.setText("Number of import   :");

        lbl_count.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        lbl_count.setForeground(new java.awt.Color(255, 0, 51));

        jLabel9.setIcon(new javax.swing.ImageIcon("C:\\Shop Management\\Images\\lo1new.png")); // NOI18N

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 0, 51));
        jLabel13.setText("IMPORT INFORMATION");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_search, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(393, 393, 393))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(jLabel12)
                        .addGap(22, 22, 22)
                        .addComponent(lbl_count)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(35, 35, 35)
                        .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(245, 245, 245)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(lbl_count)))
        );

        setSize(new java.awt.Dimension(1232, 567));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        Home ob=new Home();
        ob.setVisible(true);
    }//GEN-LAST:event_btn_backActionPerformed
     String fileName=null;
    InputStream img = null;
    String ImgPath=null;
    ImageIcon myImage=null;
    private ImageIcon format=null;
 /*public static boolean isEmpty(JTable jTable2) {
    if (jTable2 != null && jTable2.getModel() != null) {
        return jTable2.getModel().getRowCount() <= 0 ? true : false;
    }
    return false;
}
*/
    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
     
        
        
        
        /*try{
        String Id = null;
       String S=(String)cbo_pro.getSelectedItem();
       String qty=txt_quantity.getText();
       String uni=txt_unit.getText();
       String listQty=null;
       String UNIT=null;
       int i = 0;
        if(!(isEmpty(jTable2))){
            for(i=0;i<jTable2.getRowCount();++i){
                Id=jTable2.getValueAt(i,0).toString();
                qty=jTable2.getValueAt(i,4).toString().replace("pcs","");
                UNIT=jTable2.getValueAt(i,3).toString().replace("$", "");
                if(Id.equals(S)){
                    qty=listQty;
                    uni=UNIT;
                    break;
                }
            }
            
        }
         DefaultTableModel mod = (DefaultTableModel) jTable2.getModel();
         if(Id.equals(S)){
             
                   Float K=Float.parseFloat(listQty);
                   Float P=Float.parseFloat(txt_quantity.getText());
                   float N=K+P;
                   
                   float M;
                   Float UN=Float.parseFloat(txt_unit.getText());
                   M=N*UN;
                   mod.setValueAt(N, i, 4);
                   mod.setValueAt(""+M,i,5);
         }else{
             float total;
            String ID =(String)cbo_pro.getSelectedItem();
            String cat=txt_cat.getText();
            String name=txt_pro_name.getText();
            Float unit=Float.parseFloat(txt_unit.getText());
           Float Q=Float.parseFloat(txt_quantity.getText());
           String U=txt_unit.getText();
           String QTY=txt_quantity.getText();
           total=Q*unit;
           String T=""+total;
           String[] row={ID,cat,name,"$"+unit,Q+" pcs",T};
           mod.addRow(new Object[]{row});
         }
      }catch(Exception e){
          JOptionPane.showMessageDialog(null,e);
      }*/
// TODO add your handling cde here:

        if(txt_quantity.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please input qty of product!!");
            txt_quantity.requestFocus();
        }else if(txt_quantity.getText()!=null){
             DefaultTableModel mod = (DefaultTableModel) jTable2.getModel();
             DecimalFormat dollar = new DecimalFormat("#,##0.00");
             DecimalFormat doll = new DecimalFormat("0.00");
             String ID =(String)cbo_pro.getSelectedItem();
             String cat=txt_cat.getText();
             String name=txt_pro_name.getText();
             Float unit=Float.parseFloat(txt_unit.getText());
             Float qty=Float.parseFloat(txt_quantity.getText());
             String U=txt_unit.getText();
           String QTY=txt_quantity.getText();
           float total=(float)(0.2f);
           total=qty*unit;
           String T=""+doll.format(total);//dollar.format(total);
           String[] row={ID,cat,name,"$"+unit,qty+" pcs",T};
           mod.addRow(row);
           sum();   
        }
       /*  model=(DefaultTableModel) jTable2.getModel();
          String ID=(String)cbo_pro.getSelectedItem();
         for(int i=0;i<model.getRowCount();++i){
            String tblID=model.getValueAt(i,0).toString();
            if(ID.equals(tblID)){
               Float Q=Float.parseFloat(model.getValueAt(i,4).toString().replaceAll("pcs",""));
              // Float qty=Float.parseFloat(txt_);
               Float qty=Float.parseFloat(txt_quantity.getText());
               Float U=Float.parseFloat(txt_unit.getText());
               Float S;
                S = Q+qty;
                Float T=S*U;
                model.setValueAt(S+"pcs",i,4);
                model.setValueAt(T,i,5);
            }
            sum();
             
         }*/
        
        /*     
        
            DefaultTableModel mod = (DefaultTableModel) jTable2.getModel();
            DecimalFormat dollar = new DecimalFormat("#,##0.00");
            float total=0.2f;
          // String im=txt_import_id.getText();
            String ID =(String)cbo_pro.getSelectedItem();
            String cat=txt_cat.getText();
            String name=txt_pro_name.getText();
          
           Float unit=Float.parseFloat(txt_unit.getText());
           Float qty=Float.parseFloat(txt_quantity.getText());// qty=txt_quantity.getText();
           //String total=txt_total.getText();
           String U=txt_unit.getText();
           String QTY=txt_quantity.getText();
           total=qty*unit;
           String T=""+total;//dollar.format(total);
           String[] row={ID,cat,name,"$"+unit,qty+" pcs",T};
           mod.addRow(row);
            
         
          double sum=0;
           
           for(i=0;i<jTable2.getRowCount();++i){
               sum=sum+Float.parseFloat((String) jTable2.getValueAt(i,5)); 
           }
           String F="$ "+dollar.format(sum);
           txt_total.setText(F);           
                   
    
}    

        }*/
    }//GEN-LAST:event_btn_insertActionPerformed
  
    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
      
     
      
       
        String s;  
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        String date=sdf.format(txt_date.getDate());
        try{
           // s = DecimalFormat.getCurrencyInstance(Locale.getDefault()).parse (txt_total.getText()).toString();   
            //double d=Double.valueOf(txt_total.getText().replace("$",""));
            String sql="insert into Import values('"+txt_import_id.getText()+"','"+(String)cbo_sup.getSelectedItem()+"','"+(String)cbo_emp.getSelectedItem()+"','"+date+"','"+txt_total.getText()+"')";
            pst=conn.prepareStatement(sql);
            pst.execute();
          //   JOptionPane.showMessageDialog(null, "Inserted into Import Table!!");
            try{
                 model=(DefaultTableModel) jTable2.getModel();
                for(int i=0;i<model.getRowCount();++i){
                    //for()
                    String sql1="insert into import_detail(Import_ID,Product_ID,Category,Product_Name,Unit_Price,Quantity,Amount) "
                            + "values('"+txt_import_id.getText()+"','"+(String) model.getValueAt(i,0)+"','"+(String) model.getValueAt(i,1)+"'"
                            + ",'"+(String) model.getValueAt(i,2)+"','"+(String) model.getValueAt(i,3)+"','"+(String) model.getValueAt(i,4)+"','"+(String) model.getValueAt(i,5)+"')";
                   /* pst.setString(1,txt_import_id.getText());
                    pst.setString(2,(String) model.getValueAt(i,0));
                    pst.setString(3,(String) model.getValueAt(i,1));
                    pst.setString(4,(String) model.getValueAt(i,2));
                    pst.setString(5,(String) model.getValueAt(i,3));
                    pst.setString(6,(String) model.getValueAt(i,4));
                    pst.setString(7,(String) model.getValueAt(i,5));*/
                    pst=conn.prepareStatement(sql1);
                    pst.executeUpdate();
                    Update_table();
                    //pst.close();
                   // conn.close();
                   
                   try{
                       String sq1="select * from product";
                   pst=conn.prepareStatement(sq1);
                   rs=pst.executeQuery();
                   String D=(String)model.getValueAt(i,0).toString();
                   
                   while(rs.next()){
                   if(D.equals(rs.getString("Product_ID"))){
                       Float Q=Float.parseFloat(model.getValueAt(i,4).toString().replaceAll("pcs",""));
                       Float A=rs.getFloat("Quantity");
                       Float X=A+Q;
                       String sq2="update product set Quantity='"+X+"' where Product_ID='"+D+"'";
                       pst=conn.prepareStatement(sq2);
                       pst.executeUpdate();
                      // JOptionPane.showMessageDialog(null,"Product Updated!!");
                   }
                   }
                   }catch(Exception e){
                       JOptionPane.showMessageDialog(null,"Update"+e);
                   }
                   
                }
                JOptionPane.showMessageDialog(null,"Successfully");
                Update_table();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
       /* try{
            for(int j=0;j<jTable2.getRowCount();++j){
                String id=txt_import_id.getText();
                String cat=txt_cat.getText();
                String emo=(String)cbo_emp.getSelectedItem();
                
            String sql="Insert into import_detail values()";
            
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }*/
    }//GEN-LAST:event_btn_saveActionPerformed

    private void btn_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_closeActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_btn_closeActionPerformed

    private void btn_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newActionPerformed
        // TODO add your handling code here:
       // jTable2.setModel(DbUtils.resultSetToTableModel(null));
       //jTable2.setModel(new DefaultTableModel());
       DefaultTableModel tMOdel = (DefaultTableModel) jTable2.getModel();
        tMOdel.setRowCount(0);
        cbo_pro.setEnabled(true);
        btn_save.setEnabled(true);
        btn_insert.setEnabled(true);
        txt_quantity.setEnabled(true);
        cbo_emp.setEnabled(true);
        cbo_sup.setEnabled(true);
        txt_date.setEnabled(true);
       // txt_import_id.setEnabled(true);
        try{
             String sql="SELECT max(import_id) FROM shopdatabase.Import";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
               
                int S=rs.getInt(1)+1;
                String s=""+S;
                txt_import_id.setText(s);
            
          
            }
            
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
        
    }//GEN-LAST:event_btn_newActionPerformed

    private void cbo_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_empActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_empActionPerformed

    private void cbo_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_proActionPerformed

       // Integer chosenNumber = (Integer)cbo_pro.getSelectedItem();
        //String sql="Select * from product where Product_ID='Active'";
        
    /*  
      try{
      javaconnection mq=new javaconnection();
      int s = (Integer) cbo_pro.getSelectedIndex();
      ArrayList<pro>list=mq.getData(s);
      for(int i=0;i<list.size();++i){
         // if(s==i){
          txt_pro_name.setText(list.get(i).getName());
          txt_cat.setText(list.get(i).getCat());
          String D=""+list.get(i).getUnit();
          txt_unit.setText(D);
        //  }
      }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }*/
        
 /*      public ArrayList<pro>getDat(String catID){
    ArrayList<pro>List=new ArrayList<pro>();
    try{
        String sql="Select Product_Name,Category,Unit_Stock from product where Product_Id='"+catID+"'";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        pro p;
        while(rs.next()){
            p=new pro(rs.getString("Product_Name"), rs.getString("Category"),rs.getFloat("Unit_Stock"));
            List.add(p);
          
        }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,e);
    }
      return List;
}  */
    /*     try{
        String C="Active";
        String x="Cleaner";
        String y="Security";
     
    String sql="select * from shopdatabase.product where status='Active'";
    pst=conn.prepareStatement(sql);
    rs=pst.executeQuery("select * from shopdatabase.product where status='Active'");
        while(rs.next())
        {
            cbo_pro.addItem(rs.getString("Product_ID"));
        }
       
     
    }catch(Exception e)
    {
        JOptionPane.showMessageDialog(null,"JComcobox function :"+e);
    }
    finally{
        try{
            rs.close();
            pst.close();
        }catch(Exception e){
            
        }
    }*/
        
        
    /*    String S=(String)cbo_pro.getSelectedItem();
        try{
            String Sql="Select * from product where Product_ID='"+S+"' and status='Active'";
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
            while(rs.next()){
                if(S.equals(rs.getString(1))){
                txt_pro_name.setText(rs.getString(3));
                txt_cat.setText(rs.getString(2));
                txt_unit.setText(rs.getString(4));
                }
           }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }*/
     /* try{
       String S=(String)cbo_pro.getSelectedItem();
            String Sql="Select * from product where Product_ID='"+S+"' and status='Active'";
            pst=conn.prepareStatement(Sql);
            rs=pst.executeQuery();
           
               if(S.equals(rs.getString(1))){
                txt_pro_name.setText(rs.getString(3));
                txt_cat.setText(rs.getString(2));
                txt_unit.setText(rs.getString(4));
                }
           
      }catch(Exception e){
          JOptionPane.showMessageDialog(null,e);
      }*/
    }//GEN-LAST:event_cbo_proActionPerformed

    private void cbo_proPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cbo_proPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        String S=(String)cbo_pro.getSelectedItem();
        String sql="select * from product where Product_ID='"+S+"'";
        try{
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                txt_pro_name.setText(rs.getString(3));
                txt_cat.setText(rs.getString(2));
                txt_unit.setText(rs.getString(4));
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
    }//GEN-LAST:event_cbo_proPopupMenuWillBecomeInvisible

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        showDetail();
        sum();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
       // showDetail();
        //sum();
    }//GEN-LAST:event_jTable1KeyPressed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        Update_table();
        DefaultTableModel tMOdel = (DefaultTableModel) jTable2.getModel();
        tMOdel.setRowCount(0);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTable1MouseEntered

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        // TODO add your handling code here:
         showDetail();
        sum();
    }//GEN-LAST:event_jTable1KeyTyped

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        // TODO add your handling code here:
        
                 try{
        mode=(DefaultTableModel)jTable1.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) jTable1.getModel())); 
        sorter.setRowFilter(RowFilter.regexFilter(txt_search.getText()));
        jTable1.setRowSorter(sorter);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);   
         }
          
    }//GEN-LAST:event_txt_searchKeyReleased

    private void txt_quantityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_quantityKeyTyped
        // TODO add your handling code here:
           char c=evt.getKeyChar();
if(!Character.isDigit(c)&&(KeyEvent.VK_BACK_SPACE!=c)&& (c!=KeyEvent.VK_DELETE))
{
    getToolkit().beep();
    evt.consume();
} else {
}
    }//GEN-LAST:event_txt_quantityKeyTyped

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
            java.util.logging.Logger.getLogger(Import.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Import.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Import.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Import.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Import().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_close;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_new;
    private javax.swing.JButton btn_save;
    private javax.swing.JComboBox<String> cbo_emp;
    private javax.swing.JComboBox<String> cbo_pro;
    private javax.swing.JComboBox<String> cbo_sup;
    private javax.swing.JButton jButton15;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lbl_count;
    private javax.swing.JTextField txt_cat;
    private com.toedter.calendar.JDateChooser txt_date;
    private javax.swing.JTextField txt_import_id;
    private javax.swing.JTextField txt_pro_name;
    private javax.swing.JTextField txt_quantity;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField txt_unit;
    // End of variables declaration//GEN-END:variables
}

