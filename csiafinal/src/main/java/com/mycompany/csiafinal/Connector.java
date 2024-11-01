
package com.mycompany.csiafinal;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Connector {       
    public static Connection conn(){
        try {
            String url = "jdbc:ucanaccess://C:/Users/HP/Documents/IA.accdb";
            Connection conn = DriverManager.getConnection(url);
            return conn;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

   
}
