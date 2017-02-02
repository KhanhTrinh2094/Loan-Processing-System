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

/**
 *
 * @author tndkh_000
 */
public class LoanTypeList {
     public ArrayList<Loantype> getList() {
        ArrayList<Loantype> lList = new ArrayList<>();

        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL
                Statement st = cn.createStatement();

                //cho thi hanh linh truy van
                ResultSet rs = st.executeQuery("select * from tbLoanType");

                //xu ly ket qua: 
                //Duyet cac dong trong rs => bList
                while (rs.next()) {
                    //doc du lieu cua cac cot tuong ung trong dong hien tai

                    String loanName = rs.getString(1);
                                      
                    int iRate = rs.getInt(2);
                 
                    lList.add(new Loantype(loanName, iRate));


                }

                //dong resultset, connection
                rs.close();
                cn.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        return lList;
    }

    public boolean insert(Loantype b) {

        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua linh insert
            String sql = "insert tbLoanType values (?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);

            //fill du lieu cho cac tham so


            pst.setString(1, b.loanName);
            pst.setInt(2, b.iRate);
            



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

    public boolean update(Loantype b) {

        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua linh insert
            String sql = "update tbLoanType set interestRate=? where loanName=?";

            PreparedStatement pst = cn.prepareStatement(sql);

            //fill du lieu cho cac tham so


            pst.setString(2, b.loanName);
            
            pst.setDouble(1, b.iRate);
          

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

    public boolean delete(String gID) {

        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua linh insert
            String sql = "delete from tbLoanType where loanName=?";
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
    public int getRate(String loanName) {

        int iRate = 0;


        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL

                String sql = "select interestRate from tbLoanType where loanName=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                //cho thi hanh linh truy van
                pst.setString(1, loanName);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    iRate = rs.getInt(1);



                }

                //dong resultset, connection
                rs.close();
                cn.close();





            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        return iRate;

    }
    
}
