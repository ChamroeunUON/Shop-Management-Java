/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bsamsaa Boo
 */
//import java.util.ArrayList;
//import java.util.List;

//import com.sun.glass.events.KeyEvent;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
public class staff extends javax.swing.JFrame {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    DefaultTableModel mode;
    /**
     * Creates new form staff
     */
    public staff() {
        super("Add New Staff");
        setResizable(false);
        // setIcon();
         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Logo.png")));
         con=javaconnection.ConnecrDb();
         initComponents();
         Update_table();
            
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
         btn_insert.setEnabled(false);
         btn_update.setEnabled(false);
        int row=jTable1.getSelectedRow();
        String Table_Click=(jTable1.getModel().getValueAt(row,0).toString());
        //String Table_Click1=(jTable1.getModel().getValueAt(row,8).toString());
          try{
            String sql="select * from emp where id='"+Table_Click+"'";// AND status='"Active"';//select * from Billings WHERE BillingTotal < 5000 AND BillingDate <= '2002-07-01'
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
                    while(rs.next()){
                        if(rs.getString("id").equals(Table_Click)){
                               byte[] ImageIcon=rs.getBytes("image"); 
                     ImageIcon image = new ImageIcon(ImageIcon);
                    Image im = image.getImage();
                    Image myImg = im.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(),Image.SCALE_SMOOTH);
                    ImageIcon newImage = new ImageIcon(myImg);
                    lbl_image.setIcon(newImage);
                    
                    txt_id.setText(rs.getString("id"));
                    txt_name.setText(rs.getString("name"));
                 
                    cbo_gender.setSelectedItem(rs.getString("gender"));
                    txt_position.setSelectedItem(rs.getString("position"));
                    txt_salary.setText(rs.getString("salary"));
                    
                    String dateValue = rs.getString("DOB"); 
                    java.util.Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateValue);

                  
                    txt_DOB.setDate(date);
                    txt_phone.setText(rs.getString("phone"));
                    
                    String date_s = rs.getString("DOB"); 
                    java.util.Date s_date = new SimpleDateFormat("dd/MM/yyyy").parse(date_s);
                    txt_start.setDate(s_date);
                    txt_address.setText(rs.getString("address")); 
                          //  JOptionPane.showMessageDialog(null,"OK");
                        }
                     
                    }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        }
   DefaultTableModel mod=new DefaultTableModel(); 
    public void addTable()
    {
         String id;
           String name;
           String gender;
           String position;
           String salary;
           String phone;
            String status;
            String address;
            
           SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
           String dob = sdf.format(txt_DOB.getDate());
           String dos = sdf.format(txt_start.getDate());
           
          id=txt_id.getText().trim();
          name=txt_name.getText().trim();
          position=txt_position.getSelectedItem().toString();
          salary=txt_salary.getText().trim();
          phone=txt_phone.getText().trim();
          salary="$ "+salary;
         // status=txt_status.getSelectedItem().toString();
          address=txt_address.getText().trim();
          gender=(String)cbo_gender.getSelectedItem().toString();
          String[] row={id,name,gender,position,salary,dob,phone,address,"status"};
          mod=(DefaultTableModel) jTable1.getModel();
          mod.addRow(row);
    }
    public void Update_table()
    {
        try{
            String s="Active";
            pst=con.prepareStatement("SELECT id,name,gender,position,DOB,phone,start_date,salary,address FROM shopdatabase.emp where status='"+s+"';");
            rs=pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            CountCus();
            txt_id.setEnabled(false);
            btn_update.setEnabled(false);
            rs.close();
            pst.close();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    public boolean checkNull()
    {
        if(txt_id.getText().equals("")||txt_name.getText().equals("")||txt_start.getDate()==null||txt_DOB.getDate()==null||txt_phone.getText().equals("")||txt_address.getText().equals(""))
        {
            return false;
        }else
        {
              return true;
          }
         
    }
    String ImgPath=null;
    ImageIcon myImage=null;
    private ImageIcon format=null;
String st;

    public void buttonNew()
    {
        txt_id.setEnabled(false);
        //txt_id.setText("10001");
        txt_id.setFocusable(true);
        txt_name.setEnabled(true);
        txt_name.setText("");
        cbo_gender.setEnabled(true);
        txt_position.setEnabled(true);
        txt_DOB.setEnabled(true);
        txt_DOB.setCalendar(null);
        txt_phone.setEnabled(true);
        txt_phone.setText("");
        txt_start.setEnabled(true);
        txt_DOB.setCalendar(null);txt_start.setCalendar(null);
        txt_salary.setEnabled(true);
        txt_salary.setText("");
        //txt_status.setEnabled(true);
        //txt_status.setText("");
        txt_address.setEnabled(true);
        txt_address.setText("");
       // btn_delete.setEnabled(true);
        btn_insert.setEnabled(true);
        //btn_save.setEnabled(true);
        btn_edit.setEnabled(true);
        btn_update.setEnabled(true);
      
        btn_browse.setEnabled(true);
      
       
        }
      
    public ImageIcon ResizeImage(String imgPath,byte[] im){
        ImageIcon MyImage = new ImageIcon(imgPath);
        Image img = MyImage.getImage();
        Image newImage = img.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(),Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImage);
        return image;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        txt_phone = new javax.swing.JTextField();
        txt_salary = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbo_gender = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_DOB = new com.toedter.calendar.JDateChooser();
        txt_start = new com.toedter.calendar.JDateChooser();
        txt_position = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txt_address = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lbl_image = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_new = new javax.swing.JButton();
        btn_insert = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_refresh = new javax.swing.JButton();
        btn_back = new javax.swing.JButton();
        btn_back1 = new javax.swing.JButton();
        btn_browse = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txt_count = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_search = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setFont(new java.awt.Font("Lucida Bright", 1, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Plantagenet Cherokee", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 0));
        jLabel3.setText("Staff_Name  :");

        jLabel6.setFont(new java.awt.Font("Plantagenet Cherokee", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 204, 0));
        jLabel6.setText("DOB         :");

        txt_id.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        txt_id.setForeground(new java.awt.Color(102, 0, 0));
        txt_id.setCaretColor(new java.awt.Color(153, 0, 0));
        txt_id.setEnabled(false);

        txt_name.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_name.setForeground(new java.awt.Color(102, 0, 0));
        txt_name.setCaretColor(new java.awt.Color(153, 0, 0));
        txt_name.setEnabled(false);

        txt_phone.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_phone.setForeground(new java.awt.Color(102, 0, 0));
        txt_phone.setCaretColor(new java.awt.Color(153, 0, 0));
        txt_phone.setEnabled(false);
        txt_phone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_phoneKeyTyped(evt);
            }
        });

        txt_salary.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_salary.setForeground(new java.awt.Color(102, 0, 0));
        txt_salary.setCaretColor(new java.awt.Color(153, 0, 0));
        txt_salary.setEnabled(false);
        txt_salary.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_salaryKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Plantagenet Cherokee", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 204, 0));
        jLabel4.setText("Position    :");

        jLabel2.setFont(new java.awt.Font("Plantagenet Cherokee", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 0));
        jLabel2.setText("Gender        :");

        jLabel7.setFont(new java.awt.Font("Plantagenet Cherokee", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 204, 0));
        jLabel7.setText("Start_Date  :");

        cbo_gender.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N
        cbo_gender.setForeground(new java.awt.Color(102, 0, 0));
        cbo_gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        cbo_gender.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Plantagenet Cherokee", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 0));
        jLabel1.setText("Staff_ID       :");

        jLabel9.setFont(new java.awt.Font("Plantagenet Cherokee", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 204, 0));
        jLabel9.setText("Salary        :");

        txt_DOB.setForeground(new java.awt.Color(51, 0, 0));
        txt_DOB.setDateFormatString("dd/MM/yyyy");
        txt_DOB.setEnabled(false);
        txt_DOB.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N

        txt_start.setForeground(new java.awt.Color(51, 0, 0));
        txt_start.setDateFormatString("dd/MM/yyyy");
        txt_start.setEnabled(false);
        txt_start.setFont(new java.awt.Font("Lucida Bright", 1, 12)); // NOI18N

        txt_position.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_position.setForeground(new java.awt.Color(102, 0, 0));
        txt_position.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manager", "Accountant", "Assistance", "Cleaner", "Security" }));
        txt_position.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Plantagenet Cherokee", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 204, 0));
        jLabel11.setText("Phone       :");

        txt_address.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_address.setForeground(new java.awt.Color(102, 0, 0));
        txt_address.setCaretColor(new java.awt.Color(153, 0, 0));
        txt_address.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Plantagenet Cherokee", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 204, 0));
        jLabel8.setText("Address      :");

        lbl_image.setBackground(new java.awt.Color(153, 153, 0));
        lbl_image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        lbl_image.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_name, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_id, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbo_gender, 0, 158, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_position, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_DOB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(txt_start, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_salary, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_position, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(txt_start, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbo_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txt_salary, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_DOB, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        jPanel1.setForeground(new java.awt.Color(255, 153, 51));

        btn_new.setBackground(new java.awt.Color(255, 255, 255));
        btn_new.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btn_new.setForeground(new java.awt.Color(255, 102, 0));
        btn_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/New_ico.png"))); // NOI18N
        btn_new.setText("New");
        btn_new.setIconTextGap(5);
        btn_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_newActionPerformed(evt);
            }
        });

        btn_insert.setBackground(new java.awt.Color(255, 255, 255));
        btn_insert.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btn_insert.setForeground(new java.awt.Color(255, 102, 0));
        btn_insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Save_ico.png"))); // NOI18N
        btn_insert.setText("Save");
        btn_insert.setEnabled(false);
        btn_insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertActionPerformed(evt);
            }
        });

        btn_update.setBackground(new java.awt.Color(255, 255, 255));
        btn_update.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btn_update.setForeground(new java.awt.Color(255, 102, 0));
        btn_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/update-icon.png"))); // NOI18N
        btn_update.setText("Update");
        btn_update.setEnabled(false);
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setBackground(new java.awt.Color(255, 255, 255));
        btn_delete.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btn_delete.setForeground(new java.awt.Color(255, 102, 0));
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Delete-icon.png"))); // NOI18N
        btn_delete.setText("Delete");
        btn_delete.setEnabled(false);
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_edit.setBackground(new java.awt.Color(255, 255, 255));
        btn_edit.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btn_edit.setForeground(new java.awt.Color(255, 102, 0));
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/edit-validated-icon.png"))); // NOI18N
        btn_edit.setText("Edit");
        btn_edit.setEnabled(false);
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_refresh.setBackground(new java.awt.Color(255, 255, 255));
        btn_refresh.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btn_refresh.setForeground(new java.awt.Color(255, 102, 0));
        btn_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Refresh-icon.png"))); // NOI18N
        btn_refresh.setText("Refresh");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        btn_back.setBackground(new java.awt.Color(255, 255, 255));
        btn_back.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btn_back.setForeground(new java.awt.Color(255, 102, 0));
        btn_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Go-back-icon.png"))); // NOI18N
        btn_back.setText("Back");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });

        btn_back1.setBackground(new java.awt.Color(255, 255, 255));
        btn_back1.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        btn_back1.setForeground(new java.awt.Color(255, 102, 0));
        btn_back1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons_Button/Close-icon.png"))); // NOI18N
        btn_back1.setText("Close");
        btn_back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_back1ActionPerformed(evt);
            }
        });

        btn_browse.setBackground(new java.awt.Color(255, 255, 255));
        btn_browse.setFont(new java.awt.Font("Lucida Fax", 1, 12)); // NOI18N
        btn_browse.setForeground(new java.awt.Color(0, 204, 51));
        btn_browse.setText("Browse");
        btn_browse.setEnabled(false);
        btn_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_browseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_new, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_refresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_back1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addComponent(btn_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_insert, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_new, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_back, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_back1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_browse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        jLabel12.setFont(new java.awt.Font("Plantagenet Cherokee", 3, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 0, 51));
        jLabel12.setText("Total Staffs    :");

        txt_count.setFont(new java.awt.Font("Lucida Bright", 3, 24)); // NOI18N
        txt_count.setForeground(new java.awt.Color(204, 0, 51));
        txt_count.setEnabled(false);

        jLabel13.setIcon(new javax.swing.ImageIcon("C:\\Shop Management\\Images\\lo1new.png")); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Shop Management\\Images\\sta.png")); // NOI18N

        jTable1.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jTable1.setForeground(new java.awt.Color(0, 204, 51));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTable1MouseMoved(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
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

        txt_search.setFont(new java.awt.Font("Khmer OS", 2, 18)); // NOI18N
        txt_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_searchMouseClicked(evt);
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

        jLabel14.setFont(new java.awt.Font("Plantagenet Cherokee", 3, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 0, 51));
        jLabel14.setText("Search");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_count, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(jLabel5)
                                .addGap(413, 413, 413)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_search, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_count, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1158, 622));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
      String fileName=null;
    InputStream img = null;
    private void btn_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_browseActionPerformed

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
                 Logger.getLogger(staff.class.getName()).log(Level.SEVERE, null, ex);
             } finally {
                 try {
                     img.close();
                 } catch (IOException ex) {
                     Logger.getLogger(staff.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
        }
        else{
            JOptionPane.showMessageDialog(null,"Not Selected Image!!");
        }
      
    }//GEN-LAST:event_btn_browseActionPerformed
   
    SimpleDateFormat sm=new SimpleDateFormat();
    private void btn_insertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertActionPerformed
         
        if(checkNull())
             {
                 if(ImgPath!=null){
                try{
                    String sql="INSERT INTO emp(id,name,gender,position,DOB,phone,start_date,salary,status,address,image) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
                    pst=con.prepareStatement(sql);
                    pst.setString(1,txt_id.getText());
                    pst.setString(2,txt_name.getText());
                    pst.setString(3,(String)cbo_gender.getSelectedItem());
   
                    pst.setString(4,(String)txt_position.getSelectedItem());
                 
                     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");//, Locale.getDefault());
                     String dob = sdf.format(txt_DOB.getDate());
                    pst.setString(5,dob);
                    pst.setString(6,txt_phone.getText());
                    String dos = sdf.format(txt_start.getDate());
                    pst.setString(7,dos);
                    pst.setString(8,"$ "+txt_salary.getText());
                    pst.setString(9,"Active");
                    pst.setString(10,txt_address.getText());
                   
                    FileInputStream fi=new FileInputStream(new File(ImgPath)); 
                  //InputStream img = new FileInputStream(ImgPath);
                  //pst.setBlob(11,img);
                   pst.setBinaryStream(11,fi,fi.available());
                  
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Information has been added!!");
                    //addTable();
                    Update_table();
                      //  }
                }catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null,e);
                }
                 }
                 else
                     JOptionPane.showMessageDialog(null,"please choose your photo!!");  
          }
         else
                {
                        JOptionPane.showMessageDialog(null,"Need More Requirements Information!!");
                        }
        Update_table();
        
              
 /*       if(txt_id.getText()==null||txt_name.getText()==null||txt_DOB==null||txt_start==null){
            JOptionPane.showMessageDialog(null,"Please Input More Requirements Fields");
            }
        else{
      
           String id;
           String name;
           String position;
           String salary;
           String phone;
            String salar;
            String status;
            String address;
           SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
           String dob = sdf.format(txt_DOB.getDate());
           String s = sdf.format(txt_start.getDate());
           
          id=txt_id.getText().trim();
          name=txt_name.getText().trim();
          position=txt_position.getSelectedItem().toString();
          salary=txt_salary.getText().trim();
          phone=txt_phone.getText().trim();
          salar=salary+"$";
          status=txt_status.getText().trim();
          address=txt_address.getText().trim();
          String gender=cbo_gender.getSelectedItem().toString();
         //if(txt_id!=null||txt_name!=null){
          String[] row={id,name,gender,position,salar,dob,phone,s,address,status};
          mod=(DefaultTableModel) jTable1.getModel();
          mod.addRow(row);
   
    }//GEN-LAST:event_btn_insertActionPerformed
    */
          }          

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed

        setVisible(false);
        Home ob=new Home();
        ob.setVisible(true);
    }//GEN-LAST:event_btn_backActionPerformed

    private void btn_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_newActionPerformed
    lbl_image.setIcon(null);
        try{
             buttonNew();
            String sql="SELECT max(id) FROM shopdatabase.emp";
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
     
    }//GEN-LAST:event_btn_newActionPerformed
         
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
         // mod=new DefaultTableModel();
          
        
    }//GEN-LAST:event_formWindowOpened
    //public ImageIcon foramt=null;
  //  public ImageIcon image=null;
    
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        btn_edit.setEnabled(true);
        
        btn_delete.setEnabled(false);
        btn_update.setEnabled(false);
        tableJoint();
        //tableJoint();
       
       
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
        
      
        
    }//GEN-LAST:event_jTable1MouseEntered

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped

         txt_id.setEnabled(false);
         tableJoint();
     
    }//GEN-LAST:event_jTable1KeyTyped

    private void jTable1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseMoved
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jTable1MouseMoved

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
        txt_id.setEnabled(false);
        
        try
        {
//            if (evt.getKeyCode()==KeyEvent.VK_DOWN||evt.getKeyCode()==KeyEvent.VK_UP) 
                {
                    tableJoint();
                }
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Arrow Key");
        }
       
    }//GEN-LAST:event_jTable1KeyPressed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        try{
            
 txt_id.setEnabled(false);
       
        txt_id.setFocusable(true);
        txt_name.setEnabled(true);
       
        cbo_gender.setEnabled(true);
        txt_position.setEnabled(true);
        txt_DOB.setEnabled(true);
      
        txt_phone.setEnabled(true);
      
        txt_start.setEnabled(true);
      
        txt_salary.setEnabled(true);
     
       // txt_status.setEnabled(true);
        //txt_status.setText("");
        txt_address.setEnabled(true);
        
        tableJoint();
        btn_browse.setEnabled(true);
        //btn_save.setEnabled(false);
        btn_update.setEnabled(true);
        btn_insert.setEnabled(false);
        btn_delete.setEnabled(true);

             //btn_insert.setEnabled(false);
       
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
       
        
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        try
        {
            String sql="UPDATE `shopdatabase`.`emp` SET `status`='Deleted' WHERE `id`='"+txt_id.getText()+"'";
            pst=con.prepareStatement(sql);
            pst.executeUpdate();
            rs.close();
            pst.close();
            JOptionPane.showMessageDialog(null,"Staff has been deleted");
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        // TODO add your handling code here:
        
        //buttonNew();
       
        
       
        Update_table();
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        /*Icon icon = lbl_image.getIcon();
        BufferedImage bi = new BufferedImage(icon.getIconWidth(),
        icon.getIconHeight(),BufferedImage.TYPE_INT_RGB);*/
       // lbl_image.setIcon(new ImageIcon());
        if(checkNull()&&ImgPath==null){
            
        
             try
                {
            txt_id.setEnabled(false);
           
            //FileInputStream fi=new FileInputStream(new File(ImgPath));
            //btn_browse.setEnabled(false);
             int row=jTable1.getSelectedRow();
            String Table_Click=(jTable1.getModel().getValueAt(row,0).toString());
            String sql="UPDATE `shopdatabase`.`emp` SET `id`=?, `name`=?, `gender`=?, `position`=?, `DOB`=?, `phone`=?, `start_date`=?, `salary`=?, `address`=? WHERE `id`='"+Table_Click+"'";
            pst=con.prepareStatement(sql);
                int id = Integer.parseInt(txt_id.getText());
                pst.setString(1,txt_id.getText());
                pst.setString(2,txt_name.getText());
                pst.setString(3,(String)cbo_gender.getSelectedItem());
                pst.setString(4,(String)txt_position.getSelectedItem());
                 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");//, Locale.getDefault());
                 String dob = sdf.format(txt_DOB.getDate());
                 String dos = sdf.format(txt_start.getDate());
                pst.setString(5,dob);
                pst.setString(6,txt_phone.getText());
                pst.setString(7,dos);
                pst.setString(8,txt_salary.getText());
                pst.setString(9,txt_address.getText());
                //pst.setString(10,);
               // pst.setBinaryStream(10,fi,fi.available());
              //  pst.setString(10,Table_Click);  
                pst.executeUpdate();
               JOptionPane.showMessageDialog(null,"Updated Successfully!!");
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
                
        }else if(ImgPath!=null&&checkNull())
        {
              try
                {
            txt_id.setEnabled(false);
           
            FileInputStream fi=new FileInputStream(new File(ImgPath));
            //btn_browse.setEnabled(false);
            String sql="UPDATE `shopdatabase`.`emp` SET `id`=?, `name`=?, `gender`=?, `position`=?, `DOB`=?, `phone`=?, `start_date`=?, `salary`=?, `address`=?,`image`=? WHERE `id`=?";
            pst=con.prepareStatement(sql);
                //int id = Integer.parseInt(txt_id.getText());
                pst.setString(1,txt_id.getText());
                pst.setString(2,txt_name.getText());
                pst.setString(3,(String)cbo_gender.getSelectedItem());
                pst.setString(4,(String)txt_position.getSelectedItem());
                 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");//, Locale.getDefault());
                 String dob = sdf.format(txt_DOB.getDate());
                 String dos = sdf.format(txt_start.getDate());
                pst.setString(5,dob);
                pst.setString(6,txt_phone.getText());
                pst.setString(7,dos);
                pst.setString(8,txt_salary.getText());
                pst.setString(9,txt_address.getText());
                //pst.setString(10,);
                pst.setBinaryStream(10,fi,fi.available());
                pst.setString(11,txt_id.getText());  
                pst.executeUpdate();
               JOptionPane.showMessageDialog(null,"Updated Successfully!!");
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
            //JOptionPane.showMessageDialog(null,"Need Change Photo, Please Select Photo!!");
        }
    }//GEN-LAST:event_btn_updateActionPerformed
          
    private void btn_back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_back1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_btn_back1ActionPerformed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        // TODO add your handling code here:
        boolean b=false;
       
                 try{
        mode=(DefaultTableModel)jTable1.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) jTable1.getModel())); 
        sorter.setRowFilter(RowFilter.regexFilter(txt_search.getText()));
        jTable1.setRowSorter(sorter);
      
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);   
         }
        
         
    }//GEN-LAST:event_txt_searchKeyReleased

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
     
       
    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_searchMouseClicked
        // TODO add your handling code here:
          Update_table();
    }//GEN-LAST:event_txt_searchMouseClicked

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTable1MousePressed

    private void txt_phoneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_phoneKeyTyped
        // TODO add your handling code here:
             char c=evt.getKeyChar();
//if(Character.isDigit(c)||(c==KeyEvent.VK_BACKSPACE)|| (c==KeyEvent.VK_DELETE))
//{
//} else {
//    getToolkit().beep();
//    evt.consume();
//        }
    }//GEN-LAST:event_txt_phoneKeyTyped

    private void txt_salaryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_salaryKeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
//if(Character.isDigit(c)||(c==KeyEvent.VK_BACKSPACE)|| (c==KeyEvent.VK_DELETE))
//{
//} else {
//    getToolkit().beep();
//    evt.consume();
//        }
    }//GEN-LAST:event_txt_salaryKeyTyped

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
            java.util.logging.Logger.getLogger(staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new staff().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_back1;
    private javax.swing.JButton btn_browse;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_insert;
    private javax.swing.JButton btn_new;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cbo_gender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private com.toedter.calendar.JDateChooser txt_DOB;
    private javax.swing.JTextField txt_address;
    private javax.swing.JTextField txt_count;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_phone;
    private javax.swing.JComboBox<String> txt_position;
    private javax.swing.JTextField txt_salary;
    private javax.swing.JTextField txt_search;
    private com.toedter.calendar.JDateChooser txt_start;
    // End of variables declaration//GEN-END:variables
}
