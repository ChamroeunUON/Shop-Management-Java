


import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumn;
import java.sql.*;
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
public class supplier extends javax.swing.JFrame {
     Connection conn;
    ResultSet rs;
    PreparedStatement pst;

    /**
     * Creates new form supplier
     */
    public supplier() {
        super("Smart camTeCH Version 1.0");
       /*jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
        TableColumn col=jTable1.getColumnModel().getColumn(0);
        col.setPreferredWidth(40);
        
        col=jTable1.getColumnModel().getColumn(1);
        col.setPreferredWidth(40);
        
        col=jTable1.getColumnModel().getColumn(2);
        col.setPreferredWidth(70);
        
        col=jTable1.getColumnModel().getColumn(3);
        col.setPreferredWidth(70);
        
        col=jTable1.getColumnModel().getColumn(4);
        col.setPreferredWidth(40);
        
        col=jTable1.getColumnModel().getColumn(5);
        col.setPreferredWidth(40);
        jTable1.getTableHeader().setDefaultRenderer(new supplier());*/
      /* TableColumn column = null;
    for (int i = 0; i < 6; i++) {
        column = jTable1.getColumnModel().getColumn(i);
        if (i == 1) {
            column.setPreferredWidth(100); //sport column is bigger
        }else if(i==0){
            column.setPreferredWidth(50);
        }
        else if(i==2){
            column.setPreferredWidth(50);
        }
        else if(i==3){
            column.setPreferredWidth(50);
        }else if(i==4){
           column.setPreferredWidth(50); 
        }else{
            column.setPreferredWidth(50);
        }
    }   */
        initComponents();
        conn=javaconnection.ConnecrDb();
         setResizable(false);
        // setIcon();
        jcom();
        Update_table();
         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Logo.png")));
         
        // jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
         /*TableColumn t=jTable1.getColumnModel().getColumn(0);
         t.setPreferredWidth(50);
         
         t=jTable1.getColumnModel().getColumn(1);
         t.setPreferredWidth(50);
         
         t=jTable1.getColumnModel().getColumn(2);
         t.setPreferredWidth(50);
         
         t=jTable1.getColumnModel().getColumn(3);
         t.setPreferredWidth(50);
         t=jTable1.getColumnModel().getColumn(4);
         t.setPreferredWidth(50);
         t=jTable1.getColumnModel().getColumn(5);
         t.setPreferredWidth(50);
         
         jTable1.getTableHeader().setDefaultRenderer(new HeaderColor());*/
      /*   try{
         jTable1.getColumnModel().getColumn(0).setPreferredWidth(27);
jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
jTable1.getColumnModel().getColumn(3).setPreferredWidth(90);
jTable1.getColumnModel().getColumn(4).setPreferredWidth(90);
//jTable1.getColumnModel().getColumn(6).setPreferredWidth(120);
jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
         }catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
         }*/
        
    }
    public void Update_table()
    {
        try{
            String A="Active";
            pst=conn.prepareStatement("SELECT ID,Name,Staff_ID,Phone,Email,Address FROM supplier where status='"+A+"';");
            rs=pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            CountCus();
            txt_id.setEnabled(false);
            btn_update.setEnabled(false);
            btn_save.setEnabled(false);
            rs.close();
            pst.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
     public void CountCus()
    {
        try
        {
              int count=jTable1.getRowCount();
        
        String C=""+count;
        txt_count.setText(C);
        }catch(Exception e)
        {
            
        }
      
    }
      public void tableJoint(){
         btn_save.setEnabled(false);
         btn_update.setEnabled(false);
        int row=jTable1.getSelectedRow();
        String Table_Click=(jTable1.getModel().getValueAt(row,0).toString());
        //String Table_Click1=(jTable1.getModel().getValueAt(row,8).toString());
          try{
            String sql="select * from supplier where id='"+Table_Click+"'";// AND status='"Active"';//select * from Billings WHERE BillingTotal < 5000 AND BillingDate <= '2002-07-01'
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
                    if(rs.next()){
                        byte[] ImageIcon=rs.getBytes("image"); 
                     ImageIcon image = new ImageIcon(ImageIcon);
                    Image im = image.getImage();
                    Image myImg = im.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(),Image.SCALE_SMOOTH);
                    ImageIcon newImage = new ImageIcon(myImg);
                    lbl_image.setIcon(newImage);
                    
                    txt_id.setText(rs.getString("ID"));
                    txt_name.setText(rs.getString("Name"));
                 
                    cbo_emp.setSelectedItem(rs.getString("Staff_ID"));
                   
                    txt_phone.setText(rs.getString("Phone"));
                    
                  
                  
               
                    txt_email.setText(rs.getString("Email"));
                    
          
                    txt_address.setText(rs.getString("Address"));
                       
                   
                    
                    }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        }
public boolean checkNull()
{
    if(txt_phone.getText().equals("")){
        if(txt_name.getText().equals("")){
           
             JOptionPane.showMessageDialog(null,"Input Supplier Name");
                 txt_name.requestFocus();
                 return false;
            }
        JOptionPane.showMessageDialog(null,"Input Supplier Phone!!");
        txt_phone.requestFocus();
    return false;
    }  
    else{
            return true;
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
       
    public ImageIcon ResizeImage(String imgPath,byte[] im){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
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
        txt_id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_address = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        txt_phone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        cbo_emp = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_count = new javax.swing.JTextField();
        txt_search = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btn_browse = new javax.swing.JButton();
        lbl_image = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_save = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txt_id.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_id.setForeground(new java.awt.Color(153, 0, 51));
        txt_id.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 0));
        jLabel3.setText("Email     :");

        jLabel1.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 0));
        jLabel1.setText("ID            :");

        jLabel5.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 0));
        jLabel5.setText("Company        :");

        txt_address.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_address.setForeground(new java.awt.Color(153, 0, 51));
        txt_address.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 0));
        jLabel2.setText("Staff_ID  :");

        jLabel4.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 0));
        jLabel4.setText("Address          :");

        txt_email.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_email.setForeground(new java.awt.Color(153, 0, 51));
        txt_email.setEnabled(false);

        txt_phone.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_phone.setForeground(new java.awt.Color(153, 0, 51));
        txt_phone.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 0));
        jLabel6.setText("Phone             :");

        txt_name.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_name.setForeground(new java.awt.Color(153, 0, 51));
        txt_name.setEnabled(false);
        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameActionPerformed(evt);
            }
        });

        cbo_emp.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        cbo_emp.setForeground(new java.awt.Color(153, 0, 51));
        cbo_emp.setEnabled(false);
        cbo_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_empActionPerformed(evt);
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
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbo_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(3, 3, 3)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbo_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jTable1.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jTable1.setForeground(new java.awt.Color(153, 0, 51));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable1KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        txt_count.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N

        txt_search.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 0));
        jLabel8.setText("Total Suppliers    :");

        btn_browse.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        btn_browse.setForeground(new java.awt.Color(153, 153, 0));
        btn_browse.setText("Browse");
        btn_browse.setEnabled(false);
        btn_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_browseActionPerformed(evt);
            }
        });

        lbl_image.setBackground(new java.awt.Color(204, 204, 255));
        lbl_image.setOpaque(true);

        btn_save.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        btn_save.setForeground(new java.awt.Color(255, 51, 0));
        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Save_ico.png"))); // NOI18N
        btn_save.setText("Save");
        btn_save.setEnabled(false);
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        btn_delete.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        btn_delete.setForeground(new java.awt.Color(255, 51, 0));
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Delete-icon.png"))); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.setEnabled(false);
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 51, 0));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/edit-validated-icon.png"))); // NOI18N
        jButton12.setText("Edit");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        btn_update.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        btn_update.setForeground(new java.awt.Color(255, 51, 0));
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/update-icon.png"))); // NOI18N
        btn_update.setText("Update");
        btn_update.setEnabled(false);
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 51, 0));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Go-back-icon.png"))); // NOI18N
        jButton14.setText("Back");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 51, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/New_ico.png"))); // NOI18N
        jButton4.setText("New");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 51, 0));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Close-icon.png"))); // NOI18N
        jButton15.setText("Close");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_update)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton16.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 51, 0));
        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Refresh-icon.png"))); // NOI18N
        jButton16.setText("Refresh");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon("C:\\Shop Management\\Images\\lo1new.png")); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Supplier.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 0));
        jLabel10.setText("Search Here :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(17, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 769, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton16)
                                .addGap(13, 13, 13))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txt_count, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel9)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(btn_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel10))
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_count, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(816, 630));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        if(checkNull()&&ImgPath==null){
          try
        {
            String sql="UPDATE supplier SET ID=?,Name=?,Staff_ID=?,Phone=?,Email=?,Address=? WHERE ID=?";
            pst=conn.prepareStatement(sql);
                pst.setString(1,txt_id.getText());
                pst.setString(2,txt_name.getText());
                pst.setString(3,(String)cbo_emp.getSelectedItem());
                pst.setString(4,txt_phone.getText());
                pst.setString(5,txt_email.getText());
                pst.setString(6,txt_address.getText());
               // FileInputStream fs=new FileInputStream(new File(ImgPath));
                //pst.setBinaryStream(7,fs,fs.available());
                pst.setString(7,txt_id.getText());
            pst.executeUpdate();
            rs.close();
            pst.close();
            JOptionPane.showMessageDialog(null,"Updated Successfully!!");
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }  
        }else if(checkNull()&&ImgPath!=null){
             try
        {
            String sql="UPDATE `shopdatabase`.`supplier` SET `ID`=?,`Name`=?,`Staff_ID`=?,`Phone`=?,`Email`=?,`Address`=?,`image`=? WHERE `ID`=?";
            pst=conn.prepareStatement(sql);
                pst.setString(1,txt_id.getText());
                pst.setString(2,txt_name.getText());
                pst.setString(3,(String)cbo_emp.getSelectedItem());
                pst.setString(4,txt_phone.getText());
                pst.setString(5,txt_email.getText());
                pst.setString(6,txt_address.getText());
                FileInputStream fs=new FileInputStream(new File(ImgPath));
                pst.setBinaryStream(7,fs,fs.available());
                pst.setString(8,txt_id.getText());
                
            pst.executeUpdate();
            rs.close();
            pst.close();
            JOptionPane.showMessageDialog(null,"Updated Successfully!!");
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }  
        }
          
    }//GEN-LAST:event_btn_updateActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
         txt_name.requestFocus();
            txt_name.setEnabled(true);
            cbo_emp.setEnabled(true);
            txt_phone.setEnabled(true);
            txt_email.setEnabled(true);
            txt_address.setEnabled(true);
            btn_save.setEnabled(true);
            btn_browse.setEnabled(true);
        btn_update.setEnabled(true);
        btn_save.setEnabled(false);
        btn_delete.setEnabled(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
         try
        {
            String sql="UPDATE `shopdatabase`.`supplier` SET `status`='Deleted' WHERE `id`='"+txt_id.getText()+"'";
            pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            rs.close();
            pst.close();
            JOptionPane.showMessageDialog(null,"Supplier has been deleted");
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        Home ob=new Home();
        ob.setVisible(true);
    }//GEN-LAST:event_jButton14ActionPerformed
    String ImgPath=null;
    ImageIcon myImage=null;
    private ImageIcon format=null;
    InputStream img = null;
    private void btn_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_browseActionPerformed

        // TODO add your handling code here:
            JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
       
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg","png","gif");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            
             try {
                 File selectedFile = file.getSelectedFile();
                 String path = selectedFile.getAbsolutePath();
                 lbl_image.setIcon(ResizeImage(path,null));
                  ImgPath = path;
                 img = new FileInputStream(new File(ImgPath));
                
             } catch (FileNotFoundException ex) {
                Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
             } finally {
                 try {
                     img.close();
                 } catch (IOException ex) {
                     Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
        }
        else{
            JOptionPane.showMessageDialog(null,"Not Selected Image!!");
        }
      
    }//GEN-LAST:event_btn_browseActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        txt_name.setEnabled(false);
        txt_email.setEnabled(false);
        txt_phone.setEnabled(false);
        txt_address.setEnabled(false);
        btn_browse.setEnabled(false);
        cbo_emp.setEnabled(false);
        Update_table();  
    }//GEN-LAST:event_jButton16ActionPerformed

    private void cbo_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_empActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_empActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
        if(checkNull()){
            
            if(ImgPath!=null){
        try{
            FileInputStream fi=new FileInputStream(new File(ImgPath)); 
            
           String sql="insert into supplier(ID,Name,Staff_ID,Phone,Email,Address,status,image) values(?,?,?,?,?,?,?,?)";
           pst=conn.prepareStatement(sql);
                pst.setString(1,txt_id.getText());
                pst.setString(2,txt_name.getText());
                pst.setString(3,(String)cbo_emp.getSelectedItem());
                
                pst.setString(4,txt_phone.getText());
                
                if(txt_email.getText().equals("")){
                    pst.setString(5,"NA");
                }else pst.setString(5,txt_email.getText());
                
                if(txt_address.getText().equals("")){
                    pst.setString(6,"NA");
                }else pst.setString(6,txt_address.getText());
                 pst.setString(7,"Active");
                pst.setBinaryStream(8,fi,fi.available());
               
           
            pst.execute();
           JOptionPane.showMessageDialog(null, "Information has been saved!!");
           Update_table();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
         }else if(ImgPath==null){
                JOptionPane.showMessageDialog(null,"Please choose supplier's Photo");
                
                }
            
        }
    }//GEN-LAST:event_btn_saveActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        lbl_image.setIcon(null);
        try{
            txt_id.setEnabled(true);
            txt_name.setText("");
            txt_email.setText("");
            txt_address.setText("");
            txt_phone.setText("");
            txt_name.requestFocus();
            txt_name.setEnabled(true);
            cbo_emp.setEnabled(true);
            txt_phone.setEnabled(true);
            txt_email.setEnabled(true);
            txt_address.setEnabled(true);
            btn_save.setEnabled(true);
            btn_browse.setEnabled(true);
            String sql="SELECT max(id) FROM shopdatabase.supplier";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                int S=rs.getInt(1)+1;
                
                String s=""+S;
                txt_id.setText(s);
          
            }
            
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        btn_delete.setEnabled(false);
        txt_name.setEnabled(false);
        txt_email.setEnabled(false);
        txt_phone.setEnabled(false);
        txt_address.setEnabled(false);
        btn_browse.setEnabled(false);
        cbo_emp.setEnabled(false);
        tableJoint();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        // TODO add your handling code here:
        txt_name.setEnabled(false);
        txt_email.setEnabled(false);
        txt_phone.setEnabled(false);
        txt_address.setEnabled(false);
        btn_browse.setEnabled(false);
        cbo_emp.setEnabled(false);
        tableJoint();
    }//GEN-LAST:event_jTable1KeyTyped
DefaultTableModel mode;
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
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new supplier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_browse;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cbo_emp;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_image;
    private javax.swing.JTextField txt_address;
    private javax.swing.JTextField txt_count;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_phone;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
