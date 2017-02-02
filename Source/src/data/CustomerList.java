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

public class CustomerList {
    // tra ve collection chua ds cac lop hoc trong bang tbBatch

    public ArrayList<Customer> getList() {
        ArrayList<Customer> bList = new ArrayList<>();

        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL
                Statement st = cn.createStatement();

                //cho thi hanh linh truy van
                ResultSet rs = st.executeQuery("select * from tbCustomerDetails");

                //xu ly ket qua: 
                //Duyet cac dong trong rs => bList
                while (rs.next()) {
                    //doc du lieu cua cac cot tuong ung trong dong hien tai

                    int acc = rs.getInt(1);

                    String name = rs.getString(2);
                    String icard = rs.getString(3);
                    Date Dob = rs.getDate(4);
                    String address = rs.getString(5);
                    String organization = rs.getString(6);
                    String phone = rs.getString(7);
                    int salary = rs.getInt(8);
                    bList.add(new Customer(acc, name, icard, Dob, address, organization, phone, salary));


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

    public boolean insert(Customer b) {

        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua linh insert
            String sql = "insert tbCustomerDetails values (?,?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);

            //fill du lieu cho cac tham so


            pst.setString(1, b.name);
            pst.setString(2, b.icard);
            pst.setDate(3, b.Dob);
            pst.setString(4, b.address);
            pst.setString(5, b.organization);
            pst.setString(6, b.phone);
            pst.setInt(7, b.salary);


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

    public boolean update(Customer b) {

        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua linh insert
            String sql = "update tbCustomerDetails set name=?,indentifyCard=?, Dob=?,address=?,organization=?,phone=?,grossSalary=? where accountNo=?";

            PreparedStatement pst = cn.prepareStatement(sql);

            //fill du lieu cho cac tham so
            pst.setString(1, b.name);
            pst.setString(2, b.icard);
            pst.setDate(3, b.Dob);
            pst.setString(4, b.address);
            pst.setString(5, b.organization);
            pst.setString(6, b.phone);
            pst.setInt(7, b.salary);
            pst.setInt(8, b.acc);

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
            String sql = "delete from tbCustomerDetails where accountNo=?";
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

    public Customer getcus(String identify) {

        int acc = 0;
        Customer cus = null;
        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();
        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL
                Statement st = cn.createStatement();

                //cho thi hanh linh truy van
                String sql = "select * from tbCustomerDetails where indentifyCard=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, identify);
                
                ResultSet rs = pst.executeQuery();
                

                //xu ly ket qua: 
                //Duyet cac dong trong rs => bList
                while (rs.next()) {
                    //doc du lieu cua cac cot tuong ung trong dong hien tai

                    acc = rs.getInt(1);

                    String name = rs.getString(2);
                    String icard = rs.getString(3);
                    Date Dob = rs.getDate(4);
                    String address = rs.getString(5);
                    String organization = rs.getString(6);
                    String phone = rs.getString(7);
                    int salary = rs.getInt(8);
                    cus = new Customer(acc, name, icard, Dob, address, organization, phone, salary);


                }

                //dong resultset, connection
                rs.close();
                cn.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return cus;
    }
}
