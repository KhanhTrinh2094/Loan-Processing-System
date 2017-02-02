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
public class ContractList {
    // tra ve collection chua ds cac lop hoc trong bang tbBatch

    public ArrayList<Contract> getList() {
        ArrayList<Contract> bList = new ArrayList<>();

        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL
                Statement st = cn.createStatement();

                //cho thi hanh linh truy van
                ResultSet rs = st.executeQuery("select * from tbContract");

                //xu ly ket qua: 
                //Duyet cac dong trong rs => bList
                while (rs.next()) {
                    //doc du lieu cua cac cot tuong ung trong dong hien tai

                    int ID = rs.getInt(1);
                    int acc = rs.getInt(2);
                    String loanType = rs.getString(3);
                    Double loanAmount = rs.getDouble(4);
                    Double collateral = rs.getDouble(5);
                    String grade = rs.getString(6);
                    int period = rs.getInt(7);

                    Date day = rs.getDate(8);
                    String status = rs.getString(9);
                    String loanStatus = rs.getString(10);


                    bList.add(new Contract(ID, acc, loanType, loanAmount, collateral, grade, period, day, status, loanStatus));


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

    public int insert(Contract b) {
        int Id = 0;
        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua linh insert
            String sql = "insert tbContract values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //fill du lieu cho cac tham so


            pst.setInt(1, b.acc);
            pst.setString(2, b.loanType);
            pst.setDouble(3, b.loanAmount);
            pst.setDouble(4, b.collateral);
            pst.setString(5, b.grade);
            pst.setInt(6, b.period);

            pst.setDate(7, b.day);
            pst.setString(8, b.status);
            pst.setString(9, b.loanStatus);



            //cho thi hanh linh 
            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating Contract failed, no rows affected.");
            }

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating Contract failed, no ID obtained.");
                }
            }
            //dong ket noi
            cn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return Id;
        }

        return Id;
    }

    public boolean update(Contract b) {

        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua linh insert
            String sql = "update tbContract set accountNo=?,loanType=?, loanAmount=?,collateral=?,grade=?,period=?,date=?,status=?,loanStatus=? where contractID=?";

            PreparedStatement pst = cn.prepareStatement(sql);

            //fill du lieu cho cac tham so
            pst.setInt(1, b.acc);
            pst.setString(2, b.loanType);
            pst.setDouble(3, b.loanAmount);
            pst.setDouble(4, b.collateral);
            pst.setString(5, b.grade);
            pst.setInt(6, b.period);

            pst.setDate(7, b.day);
            pst.setString(8, b.status);
            pst.setString(9, b.loanStatus);
            pst.setInt(10, b.acc);


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
    public boolean updateNomarl(int contracID) {

        Connection cn = MyLib.getCon();
        try {
            //tao doi tuong chua linh insert
            String sql = "update tbContract set status='Normal' where contractID=?";

            PreparedStatement pst = cn.prepareStatement(sql);

            //fill du lieu cho cac tham so
            pst.setInt(1, contracID);
            


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
            String sql = "delete from tbContract where contractID=?";
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

    public int getacc(String identify) {

        int acc = 0;


        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL

                String sql = "select accountNo from tbCustomerDetails where indentifyCard=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                //cho thi hanh linh truy van
                pst.setString(1, identify);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    acc = rs.getInt(1);



                }

                //dong resultset, connection
                rs.close();
                cn.close();





            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        return acc;

    }

    public ArrayList<Integer> getContractID(String identify) {
        ArrayList<Integer> bList = new ArrayList<>();
        int acc = 0;


        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL

                String sql = "select contractID from tbDetails where indentifyCard=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                //cho thi hanh linh truy van
                pst.setString(1, identify);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    acc = rs.getInt(1);
                    bList.add(acc);
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

    public Contract getcontrac(int accountNo) {
        int acc = 0;
        Contract contract = null;
        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();
        if (cn != null) {
            try {
                

                //cho thi hanh linh truy van
                String sql = "select * from tbContract where accountNo=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, accountNo);
                ResultSet rs = pst.executeQuery();


                //xu ly ket qua: 
                //Duyet cac dong trong rs => bList
                while (rs.next()) {
                    //doc du lieu cua cac cot tuong ung trong dong hien tai

                    int ID = rs.getInt(1);
                    acc = rs.getInt(2);
                    String loanType = rs.getString(3);
                    Double loanAmount = rs.getDouble(4);
                    Double collateral = rs.getDouble(5);
                    String grade = rs.getString(6);
                    int period = rs.getInt(7);

                    Date day = rs.getDate(8);
                    String status = rs.getString(9);
                    String loanStatus = rs.getString(10);


                    contract = new Contract(ID, acc, loanType, loanAmount, collateral, grade, period, day, status, loanStatus);


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

    public double SumLoan(String indentify) {
        double sum = 0;

        Connection cn = MyLib.getCon();
        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL
                Statement st = cn.createStatement();

                //cho thi hanh linh truy van
                String sql = "select sum(principal) from tbPaymentDetails group by indentifyCard,paymentStatus having indentifyCard=? and paymentStatus<>'Paid'";

                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, indentify);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    sum = rs.getInt(1);



                }

                //dong resultset, connection
                rs.close();
                cn.close();


            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return sum;
    }
    public ArrayList<Contract> getListLate(int accountNo) {
        ArrayList<Contract> bList = new ArrayList<>();

        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        if (cn != null) {
            try {
                String sql = "select * from tbContract where accountNo=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, accountNo);
                ResultSet rs = pst.executeQuery();

               
                while (rs.next()) {
                    //doc du lieu cua cac cot tuong ung trong dong hien tai

                    int ID = rs.getInt(1);
                    int acc = rs.getInt(2);
                    String loanType = rs.getString(3);
                    Double loanAmount = rs.getDouble(4);
                    Double collateral = rs.getDouble(5);
                    String grade = rs.getString(6);
                    int period = rs.getInt(7);

                    Date day = rs.getDate(8);
                    String status = rs.getString(9);
                    String loanStatus = rs.getString(10);


                    bList.add(new Contract(ID, acc, loanType, loanAmount, collateral, grade, period, day, status, loanStatus));


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

    public int getConID(Date sqlDatecon) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
