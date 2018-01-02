package com.example.asus_pc.get_comment;

/**
 * Created by asus-pc on 2015/11/20.
 */
public class Detail {

    private String record_date;
    private String record_content;
    private String user_name;



    public Detail(String record_date,String record_content,String user_name){
        setRecord_date(record_date);
        setRecord_content(record_content);
        setUser_name(user_name);


    }

    public String getRecord_date(){
        return record_date;
    }
    public String getRecord_content(){
        return record_content;
    }
    public String getUser_name(){return user_name;}


    public void setRecord_date(String record_date){
        this.record_date=record_date;
    }
    public void setRecord_content(String record_content){
        this.record_content=record_content;
    }
    public void setUser_name(String user_name){this.user_name=user_name;}
}
