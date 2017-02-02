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
public class DetailsList {
    // tra ve collection chua ds cac lop hoc trong bang tbBatch

    public ArrayList<Details> getList() {
        ArrayList<Details> dList = new ArrayList<>();

        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL
                Statement st = cn.createStatement();

                //cho thi hanh linh truy van
                ResultSet rs = st.executeQuery("select indentifyCard,name,grade,loanAmount,status,period, date from tbDetails");

                //xu ly ket qua: 
                //Duyet cac dong trong rs => bList
                while (rs.next()) {
                    //doc du lieu cua cac cot tuong ung trong dong hien tai


                    String indent = rs.getString(1);
                    String name = rs.getString(2);
                    String grade = rs.getString(3);
                    double loanAmount = rs.getDouble(4);
                    String status = rs.getString(5);
                    int period = rs.getInt(6);
                    String date = rs.getString(7);


                    dList.add(new Details(indent, name, grade, loanAmount, status, period, date));


                }

                //dong resultset, connection
                rs.close();
                cn.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        return dList;
    }

    public Details getdetail(int contractID) {

        Details contract = null;
        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();
        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL


                //cho thi hanh linh truy van
                String sql = "select indentifyCard,name,grade,loanAmount,status,period, date from tbDetails where contractID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, contractID);

                ResultSet rs = pst.executeQuery();


                //xu ly ket qua: 
                //Duyet cac dong trong rs => bList
                while (rs.next()) {
                    //doc du lieu cua cac cot tuong ung trong dong hien tai

                    String indent = rs.getString(1);
                    String name = rs.getString(2);
                    String grade = rs.getString(3);
                    double loanAmount = rs.getDouble(4);
                    String status = rs.getString(5);
                    int period = rs.getInt(6);
                    String date = rs.getString(7);


                    contract = new Details(indent, name, grade, loanAmount, status, period, date);


                }

                //dong resultset, connection
                rs.close();
                cn.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return contract;
    }

    public ArrayList<Details> getcuslist() {
        ArrayList<Details> bList = new ArrayList<>();

        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL
                Statement st = cn.createStatement();

                //cho thi hanh linh truy van
                ResultSet rs = st.executeQuery("select * from tbCusList");

                //xu ly ket qua: 
                //Duyet cac dong trong rs => bList
                while (rs.next()) {
                    //doc du lieu cua cac cot tuong ung trong dong hien tai



                    int accountNo = rs.getInt(1);

                    String name = rs.getString(2);
                    String indent = rs.getString(3);
                    String loanType = rs.getString(4);
                    int iRate = rs.getInt(5);
                    Double loanAmount = rs.getDouble(6);
                    PlanPayment Paid = new PlanPayment();
                    double remaining = loanAmount - Paid.getremain(accountNo);

                    //Double remaining = rs.getDouble(5);
                    int period = rs.getInt(7);
                    String date = rs.getDate(8).toString();
                    String status = rs.getString(9);



                    bList.add(new Details(accountNo, name, indent, loanType, iRate, loanAmount, remaining, period, date, status));


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
    
    public boolean update(int contractID,int mounth,String status,Date date ) {

        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua linh insert
            String sql = "update tbPaymentDetails set paymentStatus=?,date = ? where contractID=? and month=?";

            PreparedStatement pst = cn.prepareStatement(sql);

            //fill du lieu cho cac tham so
            pst.setString(1, status);
            pst.setDate(2, date);
            pst.setInt(3, contractID);
            pst.setInt(4, mounth);
            
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
