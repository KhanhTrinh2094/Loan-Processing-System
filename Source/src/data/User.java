package data;

import java.util.Vector;

public class User {
    public String user,pass,fname;
    public int role;
    
    public User(){
        
    }

    public User(String user, String pass, String fname, int role) {
        this.user = user;
        this.pass = pass;
        this.fname = fname;
        this.role = role;
    }
    
    public Vector toVector(){
        Vector v= new Vector();
        
        v.add(user);
        
        v.add(role);
        v.add(fname);
        return v;
    }
}
