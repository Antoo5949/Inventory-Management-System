/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Antoo
 */
public class Products extends javax.swing.JFrame {
 
// public void show_names(){
//     for(String namess:names){
//     
//         System.out.println(namess);
//     }
//    
//
//}
    /**
     * Creates new form Products
     */
    public Products() {
        initComponents();
    }
    public static ArrayList<String> names = new ArrayList<String>();
   
   public  void Addname(String Name){

   names.add(Name);

       
   }
   
     public Connection connection;
     public void show_prot() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=Inventory;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT Products.Product_Name, Category.Category_Name, Products.Product_Quantity as [Products in Hand] "
                            + "FROM   Category INNER JOIN Products "
                            + "ON Category.Category_id = Products.Category_id" );

                     


            while (resultSet.next()) {

                String Products_Name = resultSet.getString(1);
                String Category_Name = resultSet.getString(2);
                String Products_price = resultSet.getString(3);
                Addname(Products_Name);
                
               
                String tbdata[]={Products_Name,Category_Name,Products_price};
                
                DefaultTableModel tblmodel=(DefaultTableModel) product_table.getModel();
                tblmodel.addRow(tbdata);
               
                
              

            }
            
              connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     

      
     
        
           public void supplier_fiter() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=Inventory;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());
            String sql="SELECT     Products.Product_Name, Category.Category_Name, .Supplier.Name\n" +
"FROM         Category INNER JOIN\n" +
"                      Products ON dbo.Category.Category_id = Products.Category_id INNER JOIN\n" +
"                      Supplier ON dbo.Products.Supplier_id = Supplier.Supplier_Id\n" +
"                      where Supplier.Name=?;";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,namebox.getSelectedItem().toString());
            ResultSet resultSet = statement
                    .executeQuery();
            
            

            while (resultSet.next()) {
                
                

               
                String Products_Name = resultSet.getString(1);
                String Category_Name = resultSet.getString(2);
                
                
               
                String tbdata[]={Products_Name,Category_Name};
                
                DefaultTableModel tblmodel=(DefaultTableModel) category_table2.getModel();
                tblmodel.addRow(tbdata);
               
                
              

            }
            
              connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
  public void category_filter() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=Inventory;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());
            String sql="select Category.Category_Name,SUM(Products.Product_Quantity)as Quantity\n" +
"From Category inner join Products\n" +
"on Category.Category_id=Products.Category_id\n" +
"group by Category.Category_Name\n" +
"having Category.Category_Name=?";


            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, categorybox.getSelectedItem().toString());
            ResultSet resultSet = statement
                    .executeQuery();
            
            

            while (resultSet.next()) {
                
                

               
                String category_name = resultSet.getString(1);
                String quantity = resultSet.getString(2);
                
               String tbdata[]={category_name,quantity};
                
                DefaultTableModel tblmodel=(DefaultTableModel) category_table.getModel();
                tblmodel.addRow(tbdata);
                

            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    public void addItem(){
        Supplier s= new Supplier();
       
        for(String namess:s.names){
        
          namebox.addItem(namess);
        }
        namebox.setSelectedIndex(-1);   
    }
     public void add_Category(){
        Add_Category c= new Add_Category();
       
        for(String namess:c.names){
        
          categorybox.addItem(namess);
        }
        categorybox.setSelectedIndex(-1);   
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        product_table = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        category_table = new javax.swing.JTable();
        search = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        category_table2 = new javax.swing.JTable();
        namebox = new javax.swing.JComboBox<>();
        go_back = new javax.swing.JButton();
        categorybox = new javax.swing.JComboBox<>();
        category_search = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        product_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Category", "Products in Hand"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(product_table);

        category_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Category", "Quantity"
            }
        ));
        jScrollPane2.setViewportView(category_table);

        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        category_table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Category Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(category_table2);

        namebox.setToolTipText("\n");
        namebox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameboxMouseClicked(evt);
            }
        });
        namebox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameboxActionPerformed(evt);
            }
        });

        go_back.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        go_back.setIcon(new javax.swing.ImageIcon("C:\\Users\\ankur\\OneDrive\\Documents\\NetBeansProjects\\Connect\\icons\\icons8-left-arrow-50.png")); // NOI18N
        go_back.setText("Go Back");
        go_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                go_backMouseClicked(evt);
            }
        });

        categorybox.setToolTipText("\n");
        categorybox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoryboxMouseClicked(evt);
            }
        });
        categorybox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryboxActionPerformed(evt);
            }
        });

        category_search.setText("Search");
        category_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                category_searchMouseClicked(evt);
            }
        });
        category_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                category_searchActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Supplierwise Product Name");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Categorywise Product Quantity");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(categorybox, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(category_search)
                                .addGap(191, 191, 191)
                                .addComponent(namebox, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(search))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(go_back)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(540, 540, 540)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(93, 93, 93)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(592, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(go_back, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(categorybox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(category_search)
                            .addComponent(namebox, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search))
                        .addGap(66, 66, 66)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(171, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(296, 296, 296)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(456, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
         DefaultTableModel tblmodel=(DefaultTableModel) category_table2.getModel();
         tblmodel.setRowCount(0);
        supplier_fiter();
    }//GEN-LAST:event_searchActionPerformed

    private void nameboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameboxActionPerformed
        // TODO add your handling code here:
         DefaultTableModel tblmodel=(DefaultTableModel) category_table2.getModel();
         tblmodel.setRowCount(0);
        
    }//GEN-LAST:event_nameboxActionPerformed

    private void go_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_go_backMouseClicked
        // TODO add your handling code here:
        Initialization i= new Initialization();
        i.setVisible(true);
        dispose();
    }//GEN-LAST:event_go_backMouseClicked

    private void nameboxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameboxMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_nameboxMouseClicked

    private void categoryboxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryboxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryboxMouseClicked

    private void categoryboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryboxActionPerformed
        // TODO add your handling code here:
         DefaultTableModel tblmodel=(DefaultTableModel) category_table.getModel();
         tblmodel.setRowCount(0);
    }//GEN-LAST:event_categoryboxActionPerformed

    private void category_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_category_searchActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_category_searchActionPerformed

    private void category_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_category_searchMouseClicked
        // TODO add your handling code here:
        DefaultTableModel tblmodel=(DefaultTableModel) category_table.getModel();
         tblmodel.setRowCount(0);
        category_filter();
        
    }//GEN-LAST:event_category_searchMouseClicked

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton category_search;
    private javax.swing.JTable category_table;
    private javax.swing.JTable category_table2;
    private javax.swing.JComboBox<String> categorybox;
    private javax.swing.JButton go_back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JComboBox<String> namebox;
    private javax.swing.JTable product_table;
    private javax.swing.JButton search;
    // End of variables declaration//GEN-END:variables
}
