package data;

import java.util.Vector;


public class Role {
    public int role;
    public String des;
    
    public Role(){
        
    }

    public Role(int role, String des) {
        this.role = role;
        this.des = des;
    }
    
    public Vector toVector(){
        Vector v = new Vector();
        v.add(role);
        v.add(des);
        return v;
    }
}
