package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RoleList {

    public RoleList() {

    }

    public ArrayList<Role> getList() {
        ArrayList<Role> rList = new ArrayList<>();
        //tao ket noi den csdl
        Connection cn = data.MyLib.getCon();

        if (cn != null) {
            try {
                //tao doi tuong statement chua link truy van SQL
                Statement st = cn.createStatement();
                //Cho thi hinh truy van
                ResultSet rs = st.executeQuery("SELECT * FROM tbRole");
                //xu li ket qua 
                //duyet cac dong trong rs =>bList
                while (rs.next()) {
                    //doc du lieu cac cot tuong ung trong dong hien tai
                    int role = rs.getInt(1);
                    String des = rs.getString(2);

                    rList.add(new Role(role, des));

                }
                //dong rs,conection
                rs.close();
                cn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return rList;
    }

    public boolean insert(Role b) {
        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua lenh insert
            String sql = "insert tbRole values (?, ?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            //fill du lieu cho cac tham so
            pst.setInt(1, b.role);
            pst.setString(2, b.des);

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

    public boolean update(Role b) {
        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua lenh insert
            String sql = "update tbRole set description = ?,roleID=? where roleID=? ";
            PreparedStatement pst = cn.prepareStatement(sql);
            //fill du lieu cho cac tham so
            pst.setInt(2, b.role);
            pst.setString(1, b.des);
            pst.setInt(3, b.role);
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

    public boolean delete(Integer rID) {
        Connection cn = MyLib.getCon();
        try {
            
                String sql = "delete from tbRole where roleID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1,rID);
                pst.executeUpdate();
                cn.close();
            
            }catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
            return true;
    }
}
