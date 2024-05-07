/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ConnectMSSQL {

    public Connection connection;
      
    public void connectDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=My Exam Week;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM Course");

            while (resultSet.next()) {

                String id = resultSet.getString("Courseid");
                String name = resultSet.getString("Name");
                String credit = resultSet.getString("Credit");
                
                String tbdata[]={id,name,credit};
             
               
                
               
                
                
//                 System.out.print("Course id"+" "+id+" ");
//                 System.out.print("Name"+" "+name+" ");
//                 System.out.println("Course id"+" "+credit);
                 
                 

            }
            
              connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
