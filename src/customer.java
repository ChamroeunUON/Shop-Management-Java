
//import com.sun.glass.events.KeyEvent;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ba Boo
 */
public class customer extends javax.swing.JFrame {
Connection con;
    PreparedStatement pst;
    ResultSet rs;
    /**
     * Creates new form customer
     */
    public customer() {
        super("Customer Info");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Logo.png")));
        setResizable(false);
        con=javaconnection.ConnecrDb();
        initComponents();
        jcom();
        //tbl_customer.requestDefaultFocus();
         Update_table();
    }
public void jcom()
{
    try{
        String C="Active";
        String x="Cleaner";
        String y="Security";
    String sql="select * from emp where status='"+C+"' AND position!='"+x+"' AND position!='"+y+"'";
    pst=con.prepareStatement(sql);
    rs=pst.executeQuery();
        while(rs.next())
        {
            cbo_emp.addItem(rs.getString(1));
        }
    }catch(Exception e)
    {
        JOptionPane.showMessageDialog(null,"JComcobox function :"+e);
    }
}
public boolean checkNull()
{
  
        if(txt_id.getText().equals("")||txt_name.getText().equals("")||txt_phone.getText().equals("")||txt_date.getDate()==null)
        {
            return false;
        }
        else
        {
            return true;
        }
    
}
public void Update_table()
{
    try{
        String A="Active";
        String sql="select id,staff_ID,name,gender,date,phone,address,decription from customer where status='"+A+"'";
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery();
       
            tbl_customer.setModel(DbUtils.resultSetToTableModel(rs));
            //tableJoint();
            count();
        
        
    }catch(Exception e)
    {
        JOptionPane.showMessageDialog(null,e);
    }
}
       public void tableJoint()
        {
             btn_insert.setEnabled(false);
        int row=tbl_customer.getSelectedRow();
        String tb1=(tbl_customer.getModel().getValueAt(row,0).toString());
        
        try{
            String sql="SELECT id,name,gender,staff_ID,date,phone,address,decription FROM shopdatabase.customer where id='"+tb1+"'";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next())
            {
                txt_id.setText(rs.getString("id"));
                txt_name.setText(rs.getString("name"));
                cbo_gender.setSelectedItem(rs.getString("gender"));
                cbo_emp.setSelectedItem(rs.getString("staff_ID"));
                  String dateValue = rs.getString("date"); 
                    java.util.Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateValue);
                 txt_date.setDate(date);
                 txt_phone.setText(rs.getString("phone"));
                 txt_dec.setText(rs.getString("decription"));
                 txt_address.setText(rs.getString("address"));
            }
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        }

 public void count()
    {
        try
        {
              int count=tbl_customer.getRowCount();
        
        String C=""+count;
        txt_cus.setText(C);
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
            
        }
      
public boolean buttonNew(boolean b)
{
    if(true){
     cbo_emp.setEnabled(true);
    //txt_id.setEnabled(true);
    txt_name.setEnabled(true);
    cbo_gender.setEnabled(true);
    txt_date.setEnabled(true);
    txt_phone.setEnabled(true);
    txt_dec.setEnabled(true);
    txt_address.setEnabled(true);
    //btn_delete.setEnables()
    //btn_update.setEnabled(true);
    //btn_save.setEnabled(true);
    btn_insert.setEnabled(true);
    return true;
    }
    else
    {
        txt_id.setEnabled(false);
    txt_name.setEnabled(false);
    cbo_gender.setEnabled(false);
    txt_date.setEnabled(false);
    txt_phone.setEnabled(false);
    txt_dec.setEnabled(false);
    txt_address.setEnabled(false);
    //btn_delete.setEnables()
    //btn_update.setEnabled(true);
    //btn_save.setEnabled(true);
    //btn_insert.setEnabled(true);
        return false;
    }
    
    
    //_.setEnabled(true);
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btn_delete = new javax.swing.JButton();
        btn_insert = new javax.swing.JButton();
        btn_new = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        btn_close = new javax.swing.JButton();
        btn_back1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_customer = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_phone = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbo_gender = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txt_date = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        txt_dec = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cbo_emp = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txt_address = new javax.swing.JTextField();
        txt_search = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_cus = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 0, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
        );

        jPanel3.setForeground(new java.awt.Color(153, 0, 0));
        jPanel3.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N

        btn_delete.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        btn_delete.setForeground(new java.awt.Color(153, 51, 0));
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Delete-icon.png"))); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.setEnabled(false);
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_insert.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        btn_insert.setForeground(new java.awt.Color(153, 51, 0));
        btn_insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/button-round-arrow-right-icon.png"))); // NOI18N
        btn_insert.setText("Save");
        btn_insert.setEnabled(false);
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        btn_new.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        btn_new.setForeground(new java.awt.Color(153, 51, 0));
        btn_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/New_ico.png"))); // NOI18N
        btn_new.setText("New");
        btn_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newActionPerformed(evt);
            }
        });

        btn_edit.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        btn_edit.setForeground(new java.awt.Color(153, 51, 0));
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/edit-validated-icon.png"))); // NOI18N
        btn_edit.setText("Edit");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_update.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        btn_update.setForeground(new java.awt.Color(153, 51, 0));
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Refresh-icon.png"))); // NOI18N
        btn_update.setText("Update");
        btn_update.setEnabled(false);
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_back.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        btn_back.setForeground(new java.awt.Color(153, 51, 0));
        btn_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Refresh-icon.png"))); // NOI18N
        btn_back.setText("Refresh");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        btn_close.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        btn_close.setForeground(new java.awt.Color(153, 51, 0));
        btn_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Close-icon.png"))); // NOI18N
        btn_close.setText("Close");
        btn_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_closeActionPerformed(evt);
            }
        });

        btn_back1.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        btn_back1.setForeground(new java.awt.Color(153, 51, 0));
        btn_back1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Go-back-icon.png"))); // NOI18N
        btn_back1.setText("Back");
        btn_back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_back1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(btn_new, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_insert)
                .addGap(18, 18, 18)
                .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_update)
                .addGap(18, 18, 18)
                .addComponent(btn_delete)
                .addGap(11, 11, 11)
                .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_back1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_close, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_new, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_close, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_back1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        tbl_customer.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        tbl_customer.setForeground(new java.awt.Color(102, 102, 0));
        tbl_customer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbl_customer.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tbl_customerMouseMoved(evt);
            }
        });
        tbl_customer.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                tbl_customerMouseWheelMoved(evt);
            }
        });
        tbl_customer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_customerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbl_customerMouseEntered(evt);
            }
        });
        tbl_customer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_customerKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbl_customerKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_customer);

        jPanel1.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 153, 0));
        jLabel3.setText("Cus_Phone      :");

        txt_phone.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_phone.setForeground(new java.awt.Color(102, 102, 0));
        txt_phone.setEnabled(false);
        txt_phone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_phoneKeyTyped(evt);
            }
        });

        txt_id.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_id.setForeground(new java.awt.Color(102, 102, 0));
        txt_id.setEnabled(false);

        txt_name.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_name.setForeground(new java.awt.Color(102, 102, 0));
        txt_name.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 153, 0));
        jLabel2.setText("Cus_ID              :");

        jLabel1.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 153, 0));
        jLabel1.setText("Cus_Name        :");

        jLabel7.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 153, 0));
        jLabel7.setText("Gender             :");

        cbo_gender.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        cbo_gender.setForeground(new java.awt.Color(102, 102, 0));
        cbo_gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        cbo_gender.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 153, 0));
        jLabel9.setText("Decription        :");

        txt_date.setForeground(new java.awt.Color(102, 102, 0));
        txt_date.setDateFormatString("dd/MM/yyyy");
        txt_date.setEnabled(false);
        txt_date.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 153, 0));
        jLabel10.setText("Register Date  :");

        txt_dec.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_dec.setForeground(new java.awt.Color(102, 102, 0));
        txt_dec.setEnabled(false);
        txt_dec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_decActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 153, 0));
        jLabel11.setText("Address         :");

        cbo_emp.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        cbo_emp.setForeground(new java.awt.Color(102, 102, 0));
        cbo_emp.setEnabled(false);
        cbo_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_empActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 153, 0));
        jLabel4.setText("Staff_ID           :");

        txt_address.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_address.setForeground(new java.awt.Color(102, 102, 0));
        txt_address.setEnabled(false);
        txt_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_addressActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(199, 199, 199))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_phone, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                    .addComponent(txt_name))
                                .addGap(29, 29, 29)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_dec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_gender, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(cbo_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbo_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(txt_dec, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        txt_search.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_search.setForeground(new java.awt.Color(204, 0, 51));
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Shop Management\\Images\\lo1new.png")); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Shop Management\\Images\\CUSTOMER.png")); // NOI18N

        jLabel8.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 153, 0));
        jLabel8.setText("Total Customers :");

        txt_cus.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        txt_cus.setForeground(new java.awt.Color(204, 0, 51));

        jLabel12.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 153, 0));
        jLabel12.setText("Search");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(87, 87, 87)
                                        .addComponent(jLabel6))
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 6, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_cus, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        setSize(new java.awt.Dimension(958, 628));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_decActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_decActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_decActionPerformed

    private void txt_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_addressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_addressActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        try{
            String D="Deleted";
            pst=con.prepareStatement("update customer set status='"+D+"' where id='"+txt_id.getText()+"'");
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Deleted Successfully!!");
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
         if(checkNull()){
            
        
             try
                {
            txt_id.setEnabled(false);
           
            //FileInputStream fi=new FileInputStream(new File(ImgPath));
            //btn_browse.setEnabled(false);
            String sql="UPDATE `shopdatabase`.`customer` SET `id`=?, `name`=?, `gender`=?, `staff_ID`=?, `date`=?, `phone`=?, `address`=?, `decription`=? WHERE `id`=?";
            pst=con.prepareStatement(sql);
                //int id = Integer.parseInt(txt_id.getText());
                pst.setString(1,txt_id.getText());
                pst.setString(2,txt_name.getText());
                pst.setString(3,(String)cbo_gender.getSelectedItem());
                pst.setString(4,(String)cbo_emp.getSelectedItem());
                 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");//, Locale.getDefault());
                 String dob = sdf.format(txt_date.getDate());
                 //String dos = sdf.format(txt_start.getDate());
                pst.setString(5,dob);
                pst.setString(6,txt_phone.getText());
                pst.setString(7,txt_address.getText());
                pst.setString(8,txt_dec.getText());
                pst.setString(9,txt_id.getText());
                //pst.setString(10,);
               // pst.setBinaryStream(10,fi,fi.available());
              //  pst.setString(10,txt_id.getText());  
                pst.executeUpdate();
               JOptionPane.showMessageDialog(null,"Updated Successfully!!");
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
         }       
       
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        btn_insert.setEnabled(false);
        btn_update.setEnabled(true);
        btn_delete.setEnabled(true);
        txt_id.setEnabled(false);
        try
        {
               txt_name.setEnabled(true);
               cbo_gender.setEnabled(true);
               txt_date.setEnabled(true);
               txt_phone.setEnabled(true);
                txt_dec.setEnabled(true);
                txt_address.setEnabled(true);
                cbo_emp.setEnabled(true);
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        // TODO add your handling code here:
        Update_table();
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_closeActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_btn_closeActionPerformed

    private void btn_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newActionPerformed
         try{
            String sql="SELECT max(id) FROM shopdatabase.customer";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                int S=rs.getInt(1)+1;
                String s=""+S;
                txt_id.setText(s);
           
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        buttonNew(true);
    }//GEN-LAST:event_btn_newActionPerformed

    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
        // TODO add your handling code here:
        if(checkNull()){
        try
        {
            SimpleDateFormat sd=new SimpleDateFormat("dd/MM/yyyy");
            String dor=sd.format(txt_date.getDate());
            String A="Active";
            //String sql="INSERT INTO `shopdatabase`.`customer` (`id`, `name`, `gender`, `date`, `phone`, `address`, `decr`) VALUES (?,?,?,?,?,?,?);";
            String sql="INSERT INTO `shopdatabase`.`customer` (`id`, `name`, `gender`,staff_ID, `date`, `phone`, `address`, `decription`,`status`) VALUES('"+txt_id.getText()+"', '"+txt_name.getText()+"', '"+(String)cbo_gender.getSelectedItem()+"','"+(String)cbo_emp.getSelectedItem()+"', '"+dor+"', '"+txt_phone.getText()+"', '"+txt_address.getText()+"', '"+txt_dec.getText()+"','"+A+"');";
           /* pst=con.prepareStatement(sql);
            pst.setString(1,txt_id.getText());
            pst.setString(2,txt_name.getText());
            pst.setString(3, (String)cbo_gender.getSelectedItem());
            //SimpleDateFormat sd=new SimpleDateFormat("dd//MM//yyyy");
            //String dor=sd.format(txt_date.getDate());
            pst.setString(4,dor);
            pst.setString(5,txt_phone.getText());
            pst.setString(6,txt_address.getText());
            pst.setString(7,txt_dec.getText());*/
            pst=con.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Information has been added!!");
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Need more requirement information!!");
        }
    }//GEN-LAST:event_btn_insertActionPerformed

    private void btn_back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_back1ActionPerformed
        // TODO add your handling code here:
            setVisible(false);
        Home ob=new Home();
        ob.setVisible(true);
    }//GEN-LAST:event_btn_back1ActionPerformed

    private void tbl_customerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_customerMouseClicked
        // TODO add your handling code here:
        tableJoint();
    }//GEN-LAST:event_tbl_customerMouseClicked

    private void tbl_customerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_customerKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_customerKeyPressed

    private void tbl_customerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_customerMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_tbl_customerMouseEntered

    private void tbl_customerKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_customerKeyTyped
        // TODO add your handling code here:
        tableJoint();
    }//GEN-LAST:event_tbl_customerKeyTyped

    private void cbo_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_empActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_empActionPerformed

    private void tbl_customerMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_tbl_customerMouseWheelMoved
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tbl_customerMouseWheelMoved

    private void tbl_customerMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_customerMouseMoved
        // TODO add your handling code here
     
    }//GEN-LAST:event_tbl_customerMouseMoved
DefaultTableModel mode;
    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        // TODO add your handling code here:
          
                 try{
        mode=(DefaultTableModel)tbl_customer.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) tbl_customer.getModel())); 
        sorter.setRowFilter(RowFilter.regexFilter(txt_search.getText()));
        tbl_customer.setRowSorter(sorter);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);   
         }
    }//GEN-LAST:event_txt_searchKeyReleased

    private void txt_phoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_phoneKeyTyped
        // TODO add your handling code here:
            char c=evt.getKeyChar();
if(Character.isDigit(c)||(c==KeyEvent.VK_BACK_SPACE)|| (c==KeyEvent.VK_DELETE))
{
} else {
    getToolkit().beep();
    evt.consume();
        }
    }//GEN-LAST:event_txt_phoneKeyTyped

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
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new customer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_back1;
    private javax.swing.JButton btn_close;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_new;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cbo_emp;
    private javax.swing.JComboBox<String> cbo_gender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_customer;
    private javax.swing.JTextField txt_address;
    private javax.swing.JTextField txt_cus;
    private com.toedter.calendar.JDateChooser txt_date;
    private javax.swing.JTextField txt_dec;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_phone;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
