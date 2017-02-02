package data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FineList {

    public ArrayList<Fine> getList() {
        ArrayList<Fine> fList = new ArrayList<>();

        //tao ket noi den CSDL
        Connection cn = MyLib.getCon();

        if (cn != null) {
            try {
                //tao doi tuong Statement chua linh truy van SQL
                Statement st = cn.createStatement();

                //cho thi hanh linh truy van
                ResultSet rs = st.executeQuery("select * from tbFine");

                //xu ly ket qua: 
                //Duyet cac dong trong rs => bList
                while (rs.next()) {
                    //doc du lieu cua cac cot tuong ung trong dong hien tai

                    int id = rs.getInt(1);
                    int from = rs.getInt(2);
                    int to = rs.getInt(3);
                    int rate = rs.getInt(4);
                    fList.add(new Fine(id, from, to, rate));

                }

                //dong resultset, connection
                rs.close();
                cn.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

        return fList;
    }

    public void call() {

        Connection cn = MyLib.getCon();
        try {
            CallableStatement cstmt = cn.prepareCall("exec uspSetCurDate");

            cstmt.execute();
          
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }
     public void call2() {

        Connection cn = MyLib.getCon();
        try {
  
            CallableStatement cstmtt = cn.prepareCall("exec uspSetStatus");
          
            cstmtt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }
}
