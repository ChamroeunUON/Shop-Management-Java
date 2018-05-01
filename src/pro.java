/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ba Boo
 */
public class pro {
    public String name;
    public String cat;
    public float unit;
    public int id;
    public pro(String name,String cat,Float unit){
        //this.id=ID;
        this.name=name;
        this.cat=cat;
        this.unit=unit;
    }
    public String getName(){
        return name;
    }
    public void setId(String Name){
        this.name=Name;
    }
     public String getCat(){
        return cat;
    }
    public void setCat(String Cat){
        this.cat=Cat;
    }
     public Float getUnit(){
        return unit;
    }
    public void setUnit(Float Unit){
        this.unit=Unit;
    }
   /* public int getID(){
        return id;
    }
    public void setId(int ID){
        this.id=ID;
    }*/
   
    
}
