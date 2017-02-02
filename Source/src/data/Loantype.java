
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Vector;

/**
 *
 * @author tndkh_000
 */
public class Loantype {
    String loanName;
    int iRate;

    public Loantype() {
    }

    public Loantype(String loanName, int iRate) {
        this.loanName = loanName;
        this.iRate = iRate;
    }

    @Override
    public String toString() {
        return "Loantype{" + "loanName=" + loanName + ", iRate=" + iRate + '}';
    }
    public Vector toVector2() {
        Vector v = new Vector();

        v.add(loanName);
        v.add(iRate);
       

        return v;

    }
    public Vector toVector() {
        Vector v = new Vector();

        v.add(loanName);
       
       

        return v;

    }
}
