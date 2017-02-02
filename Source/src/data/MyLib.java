package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLib {
    
   
    private static String ServerName, Port, UserName, Password, DatabaseName;
    private Properties connectionParameters;
    private final String fileServer = "config.properties";
    
    
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
    
    public static Connection getCon(){
        
//        try {
//            //1, nap driver loai 4
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            
//            //2. tao 1 ket noi den csdl sem2_demo
//            String url = "jdbc:sqlserver://127.0.0.1:1433;database=loan";
//            con = DriverManager.getConnection(url, "sa", "123");
//
//        } catch (ClassNotFoundException ex) {
//            System.out.println("Loi sai nap driver !");
//            ex.printStackTrace();
//        } catch (SQLException ex){
//            System.out.println("Loi sai ket noi CSDL");
//            ex.printStackTrace();
//        }
//        
//        return con;
        Connection con = null;
        MyLib a = new MyLib();
        a.setConnectParemeterFile();
        try {           
            String url = "jdbc:sqlserver://" + ServerName + ":" + Port + ";databasename=" + DatabaseName;
            con = DriverManager.getConnection(url, UserName, Password);
        } catch (SQLException ex) {
            Logger.getLogger(MyLib.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

}


    

