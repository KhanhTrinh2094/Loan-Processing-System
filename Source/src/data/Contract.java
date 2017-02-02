/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Date;
import java.util.Vector;

/**
 *
 * @author tndkh_000
 */
public class Contract {

    public int ID, acc, period;
    public double loanAmount, collateral;
    public String loanType, grade, status, loanStatus;
    public Date day;

    public Contract() {
    }

    public Contract(int ID, int acc, String loanType, double loanAmount, double collateral, String grade, int period, Date day, String status, String loanStatus) {
        this.ID = ID;
        this.acc = acc;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.collateral = collateral;
        this.grade = grade;
        this.period = period;
        this.day = day;
        this.status = status;
        this.loanStatus = loanStatus;
    }

    @Override
    public String toString() {
        return "Contract{" + "ID=" + ID + ", acc=" + acc + ", period=" + period + ", loanAmount=" + loanAmount + ", collateral=" + collateral + ", loanType=" + loanType + ", grade=" + grade + ", status=" + status + ", loanStatus=" + loanStatus + ", day=" + day + '}';
    }

    public Vector toVector() {
        Vector v = new Vector();

        v.add(ID);
        v.add(acc);
        v.add(loanType);
        v.add(loanAmount);
        v.add(collateral);
        v.add(grade);
        v.add(period);
        v.add(day);
        v.add(status);
        v.add(loanStatus);

        return v;

    }
    
     public Vector toVectorOver() {
        Vector v = new Vector();

        v.add(ID);
        v.add(acc);       
        v.add(status);
        return v;

    }
}
