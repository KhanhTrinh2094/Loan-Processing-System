/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author tndkh_000
 */
public class RequestList {
    // tra ve collection chua ds cac lop hoc trong bang tbBatch

    public ArrayList<Request> getList() {
        ArrayList<Request> bList = new ArrayList<>();

        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL
                Statement st = cn.createStatement();

                //cho thi hanh linh truy van
                ResultSet rs = st.executeQuery("select * from tbRequest");

                //xu ly ket qua: 
                //Duyet cac dong trong rs => bList
                while (rs.next()) {
                    //doc du lieu cua cac cot tuong ung trong dong hien tai

                    int rqID = rs.getInt(1);
                    int period = rs.getInt(13);
                    Double salary = rs.getDouble(8);
                    String name = rs.getString(2);
                    String icard = rs.getString(3);
                    String address = rs.getString(5);
                    String organization = rs.getString(6);
                    String phone = rs.getString(7);
                    Date Dob = rs.getDate(4);
                    Double loanAmount = rs.getDouble(10);
                    Double collateral = rs.getDouble(11);
                    String loanType = rs.getString(9);
                    String grade = rs.getString(12);
                    String status = rs.getString(15);
                    String loanStatus = rs.getString(16);
                    Date day = rs.getDate(14);
                     int irate = rs.getInt(17);




                    bList.add(new Request(rqID, period, salary, name, icard, address, organization, phone, Dob, loanAmount, collateral, loanType, grade, status, loanStatus, day,irate));


                }

                //dong resultset, connection
                rs.close();
                cn.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        return bList;
    }
    

    public ArrayList<Request> getListPending() {
        ArrayList<Request> bList = new ArrayList<>();

        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL
                Statement st = cn.createStatement();

                //cho thi hanh linh truy van
                ResultSet rs = st.executeQuery("select * from tbRequest where status = 'Pending' ");

                //xu ly ket qua: 
                //Duyet cac dong trong rs => bList
                while (rs.next()) {
                    //doc du lieu cua cac cot tuong ung trong dong hien tai

                    int rqID = rs.getInt(1);
                    int period = rs.getInt(13);
                    Double salary = rs.getDouble(8);
                    String name = rs.getString(2);
                    String icard = rs.getString(3);
                    String address = rs.getString(5);
                    String organization = rs.getString(6);
                    String phone = rs.getString(7);
                    Date Dob = rs.getDate(4);
                    Double loanAmount = rs.getDouble(10);
                    Double collateral = rs.getDouble(11);
                    String loanType = rs.getString(9);
                    String grade = rs.getString(12);
                    String status = rs.getString(15);
                    String loanStatus = rs.getString(16);
                    Date day = rs.getDate(14);
                    int irate = rs.getInt(17);




                    bList.add(new Request(rqID, period, salary, name, icard, address, organization, phone, Dob, loanAmount, collateral, loanType, grade, status, loanStatus, day,irate));


                }

                //dong resultset, connection
                rs.close();
                cn.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        return bList;
    }

    public Request getRequest(int rID) {

        Connection cn = MyLib.getCon();
        Request rq = null;
        if (cn != null) {
            try {
                String sql = "select * from tbRequest where requestID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, rID);

                ResultSet rs = pst.executeQuery();

                //xu ly ket qua: 
                //Duyet cac dong trong rs => bList
                while (rs.next()) {
                    //doc du lieu cua cac cot tuong ung trong dong hien tai

                    int rqID = rs.getInt(1);
                    int period = rs.getInt(13);
                    Double salary = rs.getDouble(8);
                    String name = rs.getString(2);
                    String icard = rs.getString(3);
                    String address = rs.getString(5);
                    String organization = rs.getString(6);
                    String phone = rs.getString(7);
                    Date Dob = rs.getDate(4);
                    Double loanAmount = rs.getDouble(10);
                    Double collateral = rs.getDouble(11);
                    String loanType = rs.getString(9);
                    String grade = rs.getString(12);
                    String status = rs.getString(15);
                    String loanStatus = rs.getString(16);
                    Date day = rs.getDate(14);
                    int irate = rs.getInt(17);

                    rq = new Request(rqID, period, salary, name, icard, address, organization, phone, Dob, loanAmount, collateral, loanType, grade, status, loanStatus, day,irate);


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

    public boolean insert(Request b) {

        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua linh insert
            String sql = "insert tbRequest values (?,?,?,?,?,?,?,?,?,?,?,?,default,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);

            //fill du lieu cho cac tham so

            pst.setString(1, b.name);
            pst.setString(2, b.icard);
            pst.setDate(3, b.Dob);
            pst.setString(4, b.address);
            pst.setString(5, b.organization);
            pst.setString(6, b.phone);
            pst.setDouble(7, b.salary);
            pst.setString(8, b.loanType);
            pst.setDouble(9, b.loanAmount);
            pst.setDouble(10, b.collateral);
            pst.setString(11, b.grade);
            pst.setInt(12, b.period);
            pst.setString(13, "Pending");
            pst.setString(14, b.loanStatus);
            pst.setInt(15, b.irate);




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

    public boolean update(Request b) {

        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua linh insert
            String sql = "update tbRequest set status=? where requestID=?";

            PreparedStatement pst = cn.prepareStatement(sql);

            //fill du lieu cho cac tham so
            pst.setInt(2, b.rqID);
            pst.setString(1, b.status);


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
    public boolean updatestatus(int rqID,String status) {

        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua linh insert
            String sql = "update tbRequest set status=? where requestID=?";

            PreparedStatement pst = cn.prepareStatement(sql);

            //fill du lieu cho cac tham so
            pst.setInt(2, rqID);
            pst.setString(1, status);


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

    public boolean delete(int bNo) {

        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua linh insert
            String sql = "delete from tbRequest where requestID=?";
            PreparedStatement pst = cn.prepareStatement(sql);

            //fill du lieu cho cac tham so
            pst.setInt(1, bNo);


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
