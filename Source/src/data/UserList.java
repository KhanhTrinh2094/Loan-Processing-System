package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserList {
     // tra ve collection chua ds cac lop hoc trong bang tbBach
    public ArrayList<User> getList(){
        ArrayList<User> uList = new ArrayList<>();
        //tao ket noi den csdl
        Connection cn = data.MyLib.getCon();
        
        if(cn != null){
            try {
                //tao doi tuong statement chua link truy van SQL
                Statement st = cn.createStatement();
                //Cho thi hinh truy van
                ResultSet rs = st.executeQuery("SELECT * FROM tbUser where role<>0");
                //xu li ket qua 
                //duyet cac dong trong rs =>bList
                while(rs.next()){
                    //doc du lieu cac cot tuong ung trong dong hien tai
                    String user = rs.getString(1);
                    String pass = rs.getString(2);
                    String fname = rs.getString(4);
                    int role = rs.getInt(3);
                    
                    uList.add(new User(user, pass, fname, role));
                    
                }
                //dong rs,conection
                rs.close();
                cn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return uList;
        
    }
    
    public boolean insert(User u) {
        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua lenh insert
            String sql = "insert tbUser values (?, default, ?, ?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            //fill du lieu cho cac tham so
            pst.setString(1,u.user);
            
            pst.setInt(2, u.role);
            pst.setString(3, u.fname);

            //cho thi hành lệnh
            pst.executeUpdate();
            //đóng kết nối
            cn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean update(User u) {

        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua linh insert
                String sql = "update tbUser set [password]=? where [user]=?";

            PreparedStatement pst = cn.prepareStatement(sql);

            //fill du lieu cho cac tham so
            
            pst.setString(1, u.pass);
            pst.setString(2, u.user);
           


            //cho thi hanh lenh 
            pst.executeUpdate();

            //dong ket noi
            cn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
    public boolean updateFullname(String userID, int role, String fname) {

        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua linh insert
                String sql = "update tbUser set [role]=?,[fullname]=? where [user]=?";

            PreparedStatement pst = cn.prepareStatement(sql);

            //fill du lieu cho cac tham so
            
            pst.setInt(1,role );
            pst.setString(2,fname);
            pst.setString(3, userID);
            
           


            //cho thi hanh lenh 
            pst.executeUpdate();

            //dong ket noi
            cn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
    public boolean updatepass(String userID) {

        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua linh insert
                String sql = "update tbUser set [password]='123456' where [user]=?";

            PreparedStatement pst = cn.prepareStatement(sql);

            //fill du lieu cho cac tham so
            
            
            pst.setString(1, userID);
            
           


            //cho thi hanh lenh 
            pst.executeUpdate();

            //dong ket noi
            cn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
    public boolean delete(String user) {
        Connection cn = MyLib.getCon();
        try {
            
                String sql = "delete from tbUser where [user] = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1,user);
                pst.executeUpdate();
                
                cn.close();
            
            }catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
            return true;
    }
    public User isAuthenticate(String id, String pass) {
        User u = null;
        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL
                String sql = "select * from tbUser where [user]=? and [password]=?";
                PreparedStatement st = cn.prepareStatement(sql);

                //set gia tri cho cac tham so
                st.setString(1, id);
                st.setString(2, pass);
                //cho thi hanh linh truy van
                ResultSet rs = st.executeQuery();

                //xu ly ket qua: 


                //Duyet cac dong trong rs => bList
                if (rs.next()) {
                    u = new User();


                    //doc du lieu cua cac cot tuong ung trong dong hien tai
                    u.user = rs.getString(1);
                    u.pass = rs.getString(2);
                    u.role = rs.getInt(3);
                    u.fname = rs.getString(4);
                    

                }

                //dong resultset, connection
                rs.close();
                cn.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return u;
    }
}
