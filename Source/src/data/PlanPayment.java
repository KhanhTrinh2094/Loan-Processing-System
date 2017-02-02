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
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author tndkh_000
 */
public class PlanPayment {

    int ky, ID;
    double goctrongky, laitrongky, tongcong, gocconlai, fine;
    Date NgayBatdau, Ngaythanhtoan, date;
    String Status, inden;

    public PlanPayment(int ky, double goctrongky, double gocconlai, double laitrongky, double tongcong) {

        this.ky = ky;
        this.goctrongky = goctrongky;
        this.laitrongky = laitrongky;
        this.gocconlai = gocconlai;
        this.tongcong = goctrongky + laitrongky;
    }

    public PlanPayment(int ID, int ky, double goctrongky, double gocconlai, double laitrongky, double tongcong, Date NgayBatdau, Date Ngaythanhtoan, String Status, String inden, double fine, Date date) {
        this.ID = ID;
        this.ky = ky;
        this.goctrongky = goctrongky;
        this.laitrongky = laitrongky;
        this.gocconlai = gocconlai;
        this.tongcong = goctrongky + laitrongky;
        this.NgayBatdau = NgayBatdau;
        this.Ngaythanhtoan = Ngaythanhtoan;
        this.Status = Status;
        this.fine = fine;
        this.inden = inden;
        this.date = date;
    }

    public PlanPayment() {
    }

    public Vector toVector() {
        Vector v = new Vector();
        NumberFormat fomat = new DecimalFormat("#,##0.00");

        v.add(ky);

        v.add(fomat.format(goctrongky));
        v.add(fomat.format(gocconlai));
        v.add(fomat.format(laitrongky));
        v.add(fomat.format(tongcong));

        return v;
    }

    public Vector toVector2() {
        Vector v = new Vector();
        NumberFormat fomat = new DecimalFormat("#,##0.00");
        v.add(ky);
        v.add(fomat.format(goctrongky));
        v.add(fomat.format(laitrongky));
        v.add(fomat.format(fine));
        v.add(fomat.format(tongcong));
        v.add(fomat.format(gocconlai));
        v.add(Ngaythanhtoan);
        v.add(Status);
        v.add(date);

        return v;
    }

    public boolean addlisttiDatabase(PlanPayment b) {

        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        try {
            //tao doi tuong chua linh insert
            String sql = "insert tbPaymentDetails values (?,?,?,?,?,?,?,?,default,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);

            //fill du lieu cho cac tham so
            pst.setInt(1, b.ID);
            pst.setInt(2, b.ky);
            pst.setDouble(3, b.goctrongky);
            pst.setDouble(4, b.laitrongky);
            pst.setDouble(5, b.tongcong);
            pst.setDouble(6, b.gocconlai);
            pst.setDate(7, b.NgayBatdau);
            pst.setDate(8, b.Ngaythanhtoan);
            pst.setString(9, b.Status);
            pst.setString(10, b.inden);
            pst.setDouble(11, b.fine);
            pst.setDate(12, b.date);
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

    public ArrayList<PlanPayment> getacc(int ContracId) {

        ArrayList<PlanPayment> dList = new ArrayList<>();

        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL

                String sql = "select * from tbPaymentDetails where contractID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                //cho thi hanh linh truy van
                pst.setString(1, Integer.toString(ContracId));
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    //doc du lieu cua cac cot tuong ung trong dong hien tai

                    int month = rs.getInt(2);
                    double principal = rs.getDouble(3);
                    double interest = rs.getDouble(4);
                    double total = rs.getDouble(5);
                    double remaining = rs.getDouble(6);
                    Date bgDate = rs.getDate(7);
                    Date paymentDate = rs.getDate(8);
                    Date date = rs.getDate(13);
                    String status = rs.getString(10);
                    String inden = rs.getString(11);
                    double fine = rs.getDouble(12);
                    dList.add(new PlanPayment(ContracId, month, principal, remaining, interest, total, bgDate, paymentDate, status, inden, fine, date));

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

    public int getremain(int contracID) {

        int remain = 0;

        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL

                String sql = "select sum(principal) from tbPaymentDetails where contractID = ? and paymentStatus ='Paid' group by paymentStatus";
                PreparedStatement pst = cn.prepareStatement(sql);
                //cho thi hanh linh truy van
                pst.setInt(1, contracID);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    remain = rs.getInt(1);

                }

                //dong resultset, connection
                rs.close();
                cn.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        return remain;

    }

    public int getP(int contracID) {

        int remain = 0;

        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL

                String sql = "select sum(principal) from tbPaymentDetails where contractID = ? and paymentStatus ='Paid' group by paymentStatus";
                PreparedStatement pst = cn.prepareStatement(sql);
                //cho thi hanh linh truy van
                pst.setInt(1, contracID);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    remain = rs.getInt(1);

                }

                //dong resultset, connection
                rs.close();
                cn.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        return remain;

    }

    public boolean getNomarl(int contracID) {

        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL

                String sql = "select t1.contractID,count(*) from tbContract as t1 join tbPaymentDetails as t2 on t1.contractID= t2.contractID where t2.paymentStatus='Overdue' and t1.contractID=? group by t1.contractID";
                PreparedStatement pst = cn.prepareStatement(sql);
                //cho thi hanh linh truy van
                pst.setInt(1, contracID);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    rs.close();
                    cn.close();
                    return false;

                } else {
                    rs.close();
                    cn.close();
                    return true;
                }

                //dong resultset, connection
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return false;

    }
}
