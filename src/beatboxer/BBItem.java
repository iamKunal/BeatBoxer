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
    protected int Id;
    protected String name;
    public BBItem(int Id,String name){
        this.Id = Id;
        this.name = name;
    }
    public BBItem(){
        this.Id = 0;
        this.name = "";
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
    @Override
    public String toString(){
        return this.name;
    }
}