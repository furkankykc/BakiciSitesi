/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author furkankykc
 */
public class ConnectionManager {

    private static String url = "jdbc:mysql://localhost:3306/bakici?useSSL=false";
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String username = "root";
    private static String password = "8989323846q";
    private static Connection con;

    public static Connection getConnection() {
        if (con == null) {
            try {
                Class.forName(driverName);
                try {
                    con = DriverManager.getConnection(url, username, password);

                } catch (SQLException ex) {
                    System.out.println("Failed to create the database connection.");
                }
            } catch (ClassNotFoundException ex) {
                System.out.println("Driver not found.");
            }
        }
        return con;
    }

    public static void main(String[] args) {
        try {
            Connection con = ConnectionManager.getConnection();
            //con.close();

        } catch (Exception ex) {

        }

    }
}
