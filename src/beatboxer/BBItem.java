/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beatboxer;

/**
 *
 * @author kunal
 */
public class BBItem {
    private int Id;
    private String name;
    public BBItem(int Id,String name){
        this.Id = Id;
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getId(){
        return this.Id;
    }
}
