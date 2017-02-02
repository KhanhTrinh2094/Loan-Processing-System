/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author tndkh_000
 */
public class PlanPayment2 {
        int ky;
    String goctrongky, laitrongky, tongcong, gocconlai,fine;
    

    public PlanPayment2(int ky, String goctrongky, String gocconlai, String laitrongky, String tongcong) {

        this.ky = ky;
        this.goctrongky = goctrongky;
        this.laitrongky = laitrongky;
        this.gocconlai = gocconlai;
        this.tongcong = tongcong;
    }


    public Vector toVector() {
        Vector v = new Vector();
        
        
        
        v.add(ky);
        
        v.add(goctrongky);
        v.add(gocconlai);
        v.add(laitrongky);
        v.add(tongcong);


        return v;
    }
}
