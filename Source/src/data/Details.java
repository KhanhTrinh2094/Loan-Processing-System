/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

/**
 *
 * @author tndkh_000
 */
public class Details {

    public String indent, name, grade, status, date, loanType;
    public int period, accountNo, iRate;
    public double loanAmount, remaining;

    public Details() {
    }

    public Details(String indent, String name, String grade, double loanAmount, String status, int period, String date) {
        this.indent = indent;
        this.name = name;
        this.grade = grade;
        this.status = status;
        this.loanAmount = loanAmount;
        this.period = period;
        this.date = date;
    }

    public Details(int accountNo, String name, String indent, String loanType, int iRate, double loanAmount, double remaining, int period, String date, String status) {
        this.accountNo = accountNo;
        this.name = name;
        this.indent = indent;
        this.loanType = loanType;
        this.iRate = iRate;
        this.loanAmount = loanAmount;
        this.remaining = remaining;
        this.period = period;
        this.date = date;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Details{" + "indent=" + indent + ", name=" + name + ", grade=" + grade + ", status=" + status + ", loanAmount=" + loanAmount + '}';
    }

    public Vector toVector() {
        Vector v = new Vector();
        NumberFormat fomat = new DecimalFormat("#,###.00");
        v.add(indent);
        v.add(name);
        v.add(grade);
        v.add(fomat.format(loanAmount));
        v.add(status);


        return v;
    }

    public Vector toVector2() {
        Vector v = new Vector();
        NumberFormat fomat = new DecimalFormat("#,###.00");
        v.add(accountNo);
        v.add(name);
        v.add(indent);
        v.add(loanType);
        v.add(iRate);
        v.add(fomat.format(loanAmount));
        v.add(fomat.format(remaining));
        v.add(period);
        v.add(date);
        v.add(status);

        return v;
    }
}
