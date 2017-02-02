package data;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

public class Grade {

    public String gradeID;
    public Double salary,collateral,maxAmount;
    public int minPeriod,maxPeriod;

    public Grade() {
    }

    public Grade(String gradeID,  Double salary, Double collateral, Double maxAmount, int minPeriod, int maxPeriod) {
        this.gradeID = gradeID;
        
        this.salary = salary;
        this.collateral = collateral;
        this.maxAmount = maxAmount;
        this.minPeriod = minPeriod;
        this.maxPeriod = maxPeriod;
    }

    @Override
    public String toString() {
        return "Grade{" + "gradeID=" + gradeID + ", salary=" + salary + ", collateral=" + collateral + ", maxAmount=" + maxAmount + ", minPeriod=" + minPeriod + ", maxPeriod=" + maxPeriod + '}';
    }

    
   
public Vector toVector() {
        Vector v = new Vector();
         NumberFormat fomat = new DecimalFormat("#,###.##");
        v.add(gradeID);
       
        v.add(fomat.format(salary));
        v.add(fomat.format(collateral));
        v.add(fomat.format(maxAmount));
        v.add(minPeriod);
        v.add(maxPeriod);

        return v;

    }
}
