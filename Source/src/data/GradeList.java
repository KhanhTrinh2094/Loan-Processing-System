/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GradeList {

    public ArrayList<Grade> getList() {
        ArrayList<Grade> gList = new ArrayList<>();

        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL
                Statement st = cn.createStatement();

                //cho thi hanh linh truy van
                ResultSet rs = st.executeQuery("select * from tbGrade where gradeID<>'FAIL'");

                //xu ly ket qua: 
                //Duyet cac dong trong rs => bList
                while (rs.next()) {
                    //doc du lieu cua cac cot tuong ung trong dong hien tai

                    String gradeID = rs.getString(1);

                    double salary = rs.getDouble(2);
                    double collateral = rs.getDouble(3);
                    double maxAmount = rs.getDouble(4);
                    int minPeriod = rs.getInt(5);
                    int maxPeriod = rs.getInt(6);

                    gList.add(new Grade(gradeID, salary, collateral, maxAmount, minPeriod, maxPeriod));

                }

                //dong resultset, connection
                rs.close();
                cn.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        return gList;
    }
    public ArrayList<Grade> getListAll() {
        ArrayList<Grade> gList = new ArrayList<>();

        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL
                Statement st = cn.createStatement();

                //cho thi hanh linh truy van
                ResultSet rs = st.executeQuery("select * from tbGrade order by salary");

                //xu ly ket qua: 
                //Duyet cac dong trong rs => bList
                while (rs.next()) {
                    //doc du lieu cua cac cot tuong ung trong dong hien tai

                    String gradeID = rs.getString(1);

                    double salary = rs.getDouble(2);
                    double collateral = rs.getDouble(3);
                    double maxAmount = rs.getDouble(4);
                    int minPeriod = rs.getInt(5);
                    int maxPeriod = rs.getInt(6);

                    gList.add(new Grade(gradeID, salary, collateral, maxAmount, minPeriod, maxPeriod));

                }

                //dong resultset, connection
                rs.close();
                cn.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        return gList;
    }

    public boolean insert(Grade b) {

        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua linh insert
            String sql = "insert tbGrade values (?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);

            //fill du lieu cho cac tham so
            pst.setString(1, b.gradeID);
            pst.setDouble(2, b.salary);
            pst.setDouble(3, b.collateral);
            pst.setDouble(4, b.maxAmount);
            pst.setInt(5, b.minPeriod);
            pst.setInt(6, b.maxPeriod);

            //cho thi hanh linh 
            pst.executeUpdate();

            //dong ket noi
            cn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean update(Grade b) {

        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua linh insert
            String sql = "update tbGrade set salary=?, collateral=?,maxAmount=?,minPeriod=?,maxPeriod=? where gradeID=?";

            PreparedStatement pst = cn.prepareStatement(sql);

            //fill du lieu cho cac tham so
            pst.setString(6, b.gradeID);

            pst.setDouble(1, b.salary);
            pst.setDouble(2, b.collateral);
            pst.setDouble(3, b.maxAmount);
            pst.setInt(4, b.minPeriod);
            pst.setInt(5, b.maxPeriod);

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

    public Grade getGrade(String rID) {

        Connection cn = MyLib.getCon();
        Grade rq = null;
        if (cn != null) {
            try {
                String sql = "select * from tbGrade where gradeID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, rID);

                ResultSet rs = pst.executeQuery();

                //xu ly ket qua: 
                //Duyet cac dong trong rs => bList
                while (rs.next()) {
                    //doc du lieu cua cac cot tuong ung trong dong hien tai

                    String gradeID = rs.getString(1);
                    Double salary = rs.getDouble(2);
                    Double col = rs.getDouble(3);
                    Double maxAmount = rs.getDouble(4);
                    int minPer = rs.getInt(5);
                    int maxPer = rs.getInt(6);

                    rq = new Grade(gradeID, salary, col, maxAmount, minPer, maxPer);

                }

                //dong resultset, connection
                rs.close();
                cn.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        return rq;
    }

    public boolean delete(String gID) {

        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua linh insert
            String sql = "delete from tbGrade where gradeID=?";
            PreparedStatement pst = cn.prepareStatement(sql);

            //fill du lieu cho cac tham so
            pst.setString(1, gID);

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
}
