/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ba Boo
 */
public class staffClass {
    private int id;
    private String name;
    private String gender;
    
    private String position;
    private int salary;
    private String BOD;
   
    private String address;
     private String phone;
    private String dateS;
    
    private String status;
    private byte[] image;
    public staffClass(int id,String name,String gender,String position,int salary,String BOD,String address,String phone,String dateS,String status,byte[] image)
    {
        this.id=id;
        this.name=name;
        this.gender=gender;
        this.position=position;
        this.salary=salary;
        this.BOD=BOD;
        this.address=address;
        this.phone=phone;
        this.dateS=dateS;
        this.status=status;
        this.image=image;
    }
    public int getID()
    {
        return id;
    }
    public String getName()
    {
       return name; 
    }
    public String getGender()
    {
        return gender;
    }
    public String getPosition()
    {
        return position;
    }
    public int getSalary()
    {
        return salary;
    }
    public String getDOB()
    {
        return BOD;
    }
    public String getPhone()
    {
        return phone;
    }
    public String getDateS()
    {
        return dateS;
    }
    public byte[] image()
    {
        return image;
    }
    public String getStatus()
    {
        return status;
    }
    public String getAddress()
    {
        return address;
    }

       
    
}
