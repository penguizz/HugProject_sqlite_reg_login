package com.example.penguinn.hugproject;

/**
 * Created by delaroy on 3/27/17.
 */
public class User {

    private int id;
    private String stu_fname;
    private String stu_lname;
    private String phone;
    private String password;

    private String parent_fname;
    private String parent_lname;
    private String parent_phone;
    private String parent_email;

    public String getStu_lname() {
        return stu_lname;
    }

    public void setStu_lname(String stu_lname) {
        this.stu_lname = stu_lname;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getStu_fname(){
        return stu_fname;
    }

    public void setStu_fname(String stu_fname){
        this.stu_fname = stu_fname;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPassword(){
       return password;
    }

    public void setPassword(String password){
        this.password = password;
    }


    public String getParent_fname() {
        return parent_fname;
    }

    public void setParent_fname(String parent_fname) {
        this.parent_fname = parent_fname;
    }

    public String getParent_lname() {
        return parent_lname;
    }

    public void setParent_lname(String parent_lname) {
        this.parent_lname = parent_lname;
    }

    public String getParent_phone() {
        return parent_phone;
    }

    public void setParent_phone(String parent_phone) {
        this.parent_phone = parent_phone;
    }

    public String getParent_email() {
        return parent_email;
    }

    public void setParent_email(String parent_email) {
        this.parent_email = parent_email;
    }
}
