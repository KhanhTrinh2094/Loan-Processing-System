/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

import java.util.Vector;


public class Fine {
    public int FineID,from,to,rate;

    public Fine() {
    }

    public Fine(int FineID, int from, int to, int rate) {
        this.FineID = FineID;
        this.from = from;
        this.to = to;
        this.rate = rate;
    }
    
    public Vector toVector(){
        Vector v = new Vector();
        v.add(FineID);
        v.add(from);
        v.add(to);
        v.add(rate);
        return v;
    }
}
