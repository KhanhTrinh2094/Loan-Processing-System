/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Mihawk
 */
public class Server {
    
    String ServerName, Port, UserName, Password, DatabaseName;
    Connection cn;
    Properties connectionParameters;
    private final String fileServer = "config.properties";

    Server() {
        this.ServerName = "";
        this.DatabaseName="";
        this.UserName = "";
        this.Password = "";
        this.Port = "";
    }
    
     public int setConnectParemeterFile() {
        connectionParameters = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileServer);
            connectionParameters.load(fis);
            ServerName = connectionParameters.getProperty("ServerName");
            DatabaseName = connectionParameters.getProperty("DatabaseName");
            UserName = connectionParameters.getProperty("UserName");
            Password = connectionParameters.getProperty("Password");
            Port = connectionParameters.getProperty("Port");
            fis.close();
            return 0;
        } catch (IOException ex) {
            return -1;
        }
    }
     
     public Server(String ServerName, String DatabaseName, String UserName, String Password, String Port) {
        this.ServerName = ServerName;
        this.DatabaseName=DatabaseName;
        this.UserName = UserName;
        this.Password = Password;
        this.Port = Port;
    }
     
     public int openConnection() {
        try {
            loadDriver();
            setConnectionParameters();
            return 0;
        } catch (ClassNotFoundException e) {
            return -1;
        } catch (SQLException e) {
            return -1;
        }
    }
      private void loadDriver() throws ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    }
    
    /*
     * Method set connection parameters driver
     */

    private void setConnectionParameters() throws SQLException {
        String url = "jdbc:sqlserver://" + ServerName + ":" + Port + ";databasename=" + DatabaseName;
        cn = DriverManager.getConnection(url, UserName, Password);
    }
      public int openConnectionFromFile() {
        int loadFileStatus = setConnectParemeterFile();
        if (loadFileStatus == -1) {
            return -1;
        }
        if (loadFileStatus == 0) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://" + ServerName + ":" + Port + ";databasename=" + DatabaseName;
                cn = DriverManager.getConnection(url, UserName, Password);
            } catch (ClassNotFoundException e) {
                return -1;
            } catch (SQLException e) {
                return -1;
            }
        }
        return 0;
    }
      
      public void saveConnectionParametersToFile(String ServerName, String DatabaseName,String UserName, String Password, String Port) {
        connectionParameters = new Properties();
        FileOutputStream fsOut = null;
        try {
            fsOut = new FileOutputStream(fileServer);
            connectionParameters.setProperty("ServerName", ServerName);
            connectionParameters.setProperty("DatabaseName", DatabaseName);
            connectionParameters.setProperty("UserName", UserName);
            connectionParameters.setProperty("Password", Password);
            connectionParameters.setProperty("Port", Port);
            connectionParameters.store(fsOut, "---LMS Server configuration file---");
            fsOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
       
      public Connection getConnection() {
        return cn;
    }
      
}
