/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author user
 */
public class Koneksi {
    private String dbDriver = "com.mysql.jdbc.Driver";
    private String dbUsername = "kapit20";
    private String dbPass = "1sampai8";
    private String dbUrl = "jdbc:mysql://206.189.94.183/morohealthy";

    private Connection conn;
    
    public Koneksi(){
        try {
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbUrl, dbUsername, dbPass);
        } catch (Exception e) {
            conn = null;
        }
    }

    public Connection getConn() {
        return conn;
    }
    
}
