
//import Icons_Button.supplier;
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
import java.text.DecimalFormat;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
public class product extends javax.swing.JFrame {
     Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    public product() {
        super("Smart camTeCH Version 1.0");
        initComponents();
        conn=javaconnection.ConnecrDb();
        
          Update_table();
        jcom();
        //jcom_search();
         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("Logo.png")));
          setResizable(false);
    }
       /*  public void jcom_search()
{
    try{
        String C="Active";
        String x="Cleaner";
        String y="Security";
    String sql="select * from shopdatabase.category where status='Active'";
    pst=conn.prepareStatement(sql);
    rs=pst.executeQuery("select Category from shopdatabase.category where status='Active'");
        while(rs.next())
        {
            cbo_search.addItem(rs.getString("Category"));
            
        }
    }catch(Exception e)
    {
        JOptionPane.showMessageDialog(null,"JComcobox function :"+e);
    }
    
}*/
    public void jcom()
{
    try{
        String C="Active";
        String x="Cleaner";
        String y="Security";
    String sql="select * from shopdatabase.category where status='Active'";
    pst=conn.prepareStatement(sql);
    rs=pst.executeQuery("select Category from shopdatabase.category where status='Active'");
        while(rs.next())
        {
            cbo_cat.addItem(rs.getString("Category"));
            
        }
    }catch(Exception e)
    {
        JOptionPane.showMessageDialog(null,"JComcobox function :"+e);
    }
    
}
    public void Update_table()
    {
        try{
            String A="Active";
            pst=conn.prepareStatement("SELECT Product_ID,Category,Product_Name,Unit_stock,Quantity,Sale_Unit,Description FROM product where status='"+A+"';");
            rs=pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            CountCus();
            txt_id.setEnabled(false);
            btn_update.setEnabled(false);
            btn_save.setEnabled(false);
          
           
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
        
        //String Table_Click1=(jTable1.getModel().getValueAt(row,8).toString());
          try{
            int row=jTable1.getSelectedRow();
            String Table_Click=(jTable1.getModel().getValueAt(row,0).toString());
            String sql="select * from product where Product_ID='"+Table_Click+"'";// AND status='"Active"';//select * from Billings WHERE BillingTotal < 5000 AND BillingDate <= '2002-07-01'
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
                    while(rs.next()){
                    byte[] ImageIcon=rs.getBytes("image"); 
                    ImageIcon image = new ImageIcon(ImageIcon);
                    Image im = image.getImage();
                    Image myImg = im.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(),Image.SCALE_SMOOTH);
                    ImageIcon newImage = new ImageIcon(myImg);
                    lbl_image.setIcon(newImage);
                    txt_id.setText(rs.getString("Product_ID"));
                    txt_name.setText(rs.getString("Product_Name"));
                    cbo_cat.setSelectedItem(rs.getString("Category"));
                    txt_stock.setText(rs.getString("Unit_Stock"));
                     txt_qty.setText(rs.getString("Quantity"));
                    txt_sale.setText(rs.getString("Sale_Unit"));
      
                    txt_dec.setText(rs.getString("Description"));
                       
                   
                    
                    }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        }
public boolean checkNull()
{
    if(txt_qty.getText().equals("")){
        if(txt_name.getText().equals("")){
              if(txt_stock.getText().equals("")){
             JOptionPane.showMessageDialog(null,"Input Product's Name");
                 txt_name.requestFocus();
                 return false;
              }
              JOptionPane.showMessageDialog(null,"Please Input Unit Price!!");
            }
        JOptionPane.showMessageDialog(null,"Input Quantity Product!!");
    return false;
    }  
    else{
            return true;
            }
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

        jPanel1 = new javax.swing.JPanel();
        txt_id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_dec = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_sale = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_stock = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_qty = new javax.swing.JTextField();
        cbo_cat = new javax.swing.JComboBox<>();
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
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txt_id.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_id.setForeground(new java.awt.Color(153, 0, 51));
        txt_id.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 0));
        jLabel3.setText("Category   :");

        jLabel1.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 0));
        jLabel1.setText("ID               :");

        jLabel5.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 0));
        jLabel5.setText("Product_Name       :");

        txt_dec.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_dec.setForeground(new java.awt.Color(153, 0, 51));
        txt_dec.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 0));
        jLabel4.setText("Description     :");

        txt_sale.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_sale.setForeground(new java.awt.Color(153, 0, 51));
        txt_sale.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 0));
        jLabel6.setText("Sale_Unit                    :");

        txt_name.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_name.setForeground(new java.awt.Color(153, 0, 51));
        txt_name.setEnabled(false);
        txt_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 0));
        jLabel10.setText("Unit_Stock               :");

        txt_stock.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_stock.setForeground(new java.awt.Color(153, 0, 51));
        txt_stock.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 153, 0));
        jLabel11.setText("Quantity    :");

        txt_qty.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        txt_qty.setForeground(new java.awt.Color(153, 0, 51));
        txt_qty.setEnabled(false);
        txt_qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_qtyActionPerformed(evt);
            }
        });

        cbo_cat.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        cbo_cat.setForeground(new java.awt.Color(153, 51, 0));
        cbo_cat.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel4))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(53, 53, 53)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbo_cat, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(42, 42, 42)
                        .addComponent(txt_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_qty)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_name, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(txt_sale)))
                    .addComponent(txt_dec))
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_cat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_sale, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_dec, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
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
        jLabel8.setText("Total Products    :");

        btn_browse.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        btn_browse.setForeground(new java.awt.Color(153, 153, 0));
        btn_browse.setText("Browse");
        btn_browse.setEnabled(false);
        btn_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_browseActionPerformed(evt);
            }
        });

        lbl_image.setBackground(new java.awt.Color(255, 102, 102));
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
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("PRODUCTS INFORMATION");

        jLabel9.setFont(new java.awt.Font("Lucida Bright", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 0));
        jLabel9.setText("Search Here  :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_browse, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))))
                .addGap(23, 23, 23))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(125, 125, 125)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txt_count, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(208, 208, 208)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel9))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_count, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(23, 23, 23))
        );

        setSize(new java.awt.Dimension(987, 667));
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
            String sql="UPDATE product SET Product_ID='"+txt_id.getText()+"',Category='"+(String)cbo_cat.getSelectedItem()+"',Product_Name='"+txt_name.getText()+"',Unit_Stock='"+txt_stock.getText()+"',Quantity='"+txt_qty.getText()+"',Sale_Unit='"+txt_sale.getText()+"',Description='"+txt_dec.getText()+"' WHERE Product_ID='"+txt_id.getText()+"'";
            pst=conn.prepareStatement(sql);
              /*  pst.setString(1,txt_id.getText());
                pst.setString(2,(String)cbo_cat.getSelectedItem());
                pst.setString(3,txt_name.getText());
                
                pst.setString(4,txt_stock.getText());
               pst.setString(5,txt_qty.getText());
                
                pst.setString(6,txt_sale.getText());
               
                pst.setString(7,txt_dec.getText());
               // FileInputStream fs=new FileInputStream(new File(ImgPath));
                //pst.setBinaryStream(7,fs,fs.available());
                pst.setString(8,txt_id.getText());*/
            pst.executeUpdate();
            Update_table();
            //rs.close();
            //pst.close();
            JOptionPane.showMessageDialog(null,"Updated Successfully!!");
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }  
        }else if(checkNull()&&ImgPath!=null){
             try
        {
            String sql="UPDATE `shopdatabase`.`Product` SET `Product_ID`=?,`Product_Name`=?,`Category`=?,`Unit_Stock`=?,`Quantity`=?,`Sale_Unit`=?,`image`=?,Description=? WHERE `Product_ID`=?";
            pst=conn.prepareStatement(sql);
                pst.setString(1,txt_id.getText());
                pst.setString(2,txt_name.getText());
                pst.setString(3,(String)cbo_cat.getSelectedItem());
                pst.setString(4,txt_stock.getText());
                pst.setString(5,txt_qty.getText());
                pst.setString(6,txt_sale.getText());
                FileInputStream fs=new FileInputStream(new File(ImgPath));
                 pst.setBinaryStream(7,fs,fs.available());
                pst.setString(8,txt_dec.getText());
                pst.setString(9,txt_id.getText());
                 pst.executeUpdate();
                Update_table();
               
            //rs.close();
            //pst.close();
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
         txt_dec.setEnabled(true);
            txt_name.setEnabled(true);
            cbo_cat.setEnabled(true);
            txt_sale.setEnabled(true);
            //txt_qty.setEnabled(true);
            txt_stock.setEnabled(true);
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
            String sql="UPDATE `shopdatabase`.`product` SET `status`='Deleted' WHERE `Product_ID`='"+txt_id.getText()+"'";
            pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            //rs.close();
            //pst.close();
            JOptionPane.showMessageDialog(null,"Product has been deleted");
            Update_table();
            
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

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        // TODO add your handling code here:
         DecimalFormat dollar = new DecimalFormat("#,##0.00");
        
        Float qty = Float.parseFloat(txt_qty.getText());
        //Float unit = Float.parseFloat(txt_sale.getText());
        //Float stock = Float.parseFloat(txt_stock.getText());
        
        qty=0.2f;
        String Q=""+qty;
        if(checkNull()){
            if(ImgPath!=null){
        try{
            FileInputStream fi=new FileInputStream(new File(ImgPath)); 
           String sql="insert into product(Product_ID,Category,Product_Name,Unit_Stock,Quantity,Sale_Unit,image,status,Description) values(?,?,?,?,?,?,?,?,?)";
           pst=conn.prepareStatement(sql);
                int ID = Integer.parseInt(txt_id.getText());
                pst.setInt(1,ID);
                pst.setString(2,(String)cbo_cat.getSelectedItem());
                pst.setString(3,txt_name.getText());
                 pst.setString(4,txt_stock.getText());
                pst.setString(5,txt_qty.getText());
                pst.setString(6,txt_sale.getText());
                pst.setBinaryStream(7, fi,fi.available());
                pst.setString(8,"Active");
                pst.setString(9,txt_dec.getText());
            pst.execute();
           JOptionPane.showMessageDialog(null, "Information has been saved!!");
           Update_table();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Fuck!!");
        }
         }else if(ImgPath==null){
                JOptionPane.showMessageDialog(null,"Please choose supplier's Photo");
                
                }
            
        }
        
    }//GEN-LAST:event_btn_saveActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try{
            txt_qty.setText("0");
            lbl_image.setIcon(null);
            txt_id.setEnabled(false);
           // txt_qty.setEnabled(false);
            txt_stock.setEnabled(true);
            txt_name.setText("");
            cbo_cat.setEnabled(false);
            txt_dec.setEnabled(false);
            //txt_amount.setText("");
            txt_name.requestFocus();
            txt_name.setEnabled(true);
            cbo_cat.setEnabled(true);
            //txt_amount.setEnabled(true);
            txt_qty.setEnabled(true);
            txt_dec.setEnabled(true);
            btn_save.setEnabled(true);
            btn_browse.setEnabled(true);
            txt_sale.setEnabled(true);
            conn=javaconnection.ConnecrDb();
            String sql="SELECT max(Product_ID) FROM shopdatabase.product";
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
        txt_qty.setEnabled(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        btn_delete.setEnabled(false);
        txt_name.setEnabled(false);
        txt_qty.setEnabled(false);
        txt_sale.setEnabled(false);
        txt_dec.setEnabled(false);
        btn_browse.setEnabled(false);
        cbo_cat.setEnabled(false);
        //tableJoint();
        tableJoint();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        // TODO add your handling code here:
        txt_name.setEnabled(false);
        txt_qty.setEnabled(false);
        txt_sale.setEnabled(false);
        txt_dec.setEnabled(false);
        btn_browse.setEnabled(false);
        cbo_cat.setEnabled(false);
        //tableJoint();
        tableJoint();
    }//GEN-LAST:event_jTable1KeyTyped

    private void txt_qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_qtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_qtyActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
        tableJoint();
    }//GEN-LAST:event_jTable1KeyPressed
DefaultTableModel mode;
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
                new product().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_browse;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cbo_cat;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JTextField txt_count;
    private javax.swing.JTextField txt_dec;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_qty;
    private javax.swing.JTextField txt_sale;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_stock;
    // End of variables declaration//GEN-END:variables
}
