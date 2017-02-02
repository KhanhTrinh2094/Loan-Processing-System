/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Date;
import java.util.Vector;

public class Customer {

    public int acc, salary;
    public String name, icard, address, organization, phone;
    public Date Dob;

    public Customer(int acc, String name, String icard, Date Dob, String address, String organization, String phone, int salary) {
        this.acc = acc;
        this.name = name;
        this.icard = icard;
        this.Dob = Dob;
        this.address = address;
        this.organization = organization;
        this.phone = phone;
        this.salary = salary;
    }

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" + "acc=" + acc + ", salary=" + salary + ", name=" + name + ", icard=" + icard + ", Dob=" + Dob + ", address=" + address + ", organization=" + organization + ", phone=" + phone + '}';
    }

    public Vector toVector() {
        Vector v = new Vector();

        v.add(acc);
        v.add(name);
        v.add(icard);
        v.add(Dob);
        v.add(address);
        v.add(organization);
        v.add(phone);
        v.add(salary);
        return v;
    }
}
