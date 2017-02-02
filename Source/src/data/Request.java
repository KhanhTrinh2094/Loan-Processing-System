/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

/**
 *
 * @author tndkh_000
 */
public class Request {

    public int rqID, period,irate;
    public double salary;
    public String name, icard, address, organization, phone;
    public Date Dob;
    public double loanAmount, collateral;
    public String loanType, grade, status, loanStatus;
    public Date day;
    

    public Request(int rqID, int period, double salary, String name, String icard, String address, String organization, String phone, Date Dob, double loanAmount, double collateral, String loanType, String grade, String status, String loanStatus, Date day,int irate) {
        this.rqID = rqID;
        this.period = period;
        this.salary = salary;
        this.name = name;
        this.icard = icard;
        this.address = address;
        this.organization = organization;
        this.phone = phone;
        this.Dob = Dob;
        this.loanAmount = loanAmount;
        this.collateral = collateral;
        this.loanType = loanType;
        this.grade = grade;
        this.status = status;
        this.loanStatus = loanStatus;
        this.day = day;
        this.irate = irate;
    }

    public Request() {
    }
    public Vector toVector() {
        Vector v = new Vector();
        NumberFormat fomat = new DecimalFormat("#,###.00");
        v.add(rqID);
        v.add(icard);
        v.add(name);
        v.add(grade);
        v.add(fomat.format(loanAmount));
        v.add(day);
        v.add(status);
        
        return v;
    }
    
}

