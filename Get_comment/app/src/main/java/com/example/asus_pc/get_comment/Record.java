package com.example.asus_pc.get_comment;

/**
 * Created by asus-pc on 2015/11/30.
 */
public class Record {
    private String user_id;
    private String record_content;
    private String record_date;
    private String book_name;
    private String book_id;
    private String user_name;
    private String record_id;

    public Record(String book_name,String record_content,String record_date,String user_id,String book_id,String user_name,String record_id){
        setBook_name(book_name);
        setRecord_content(record_content);
        setRecord_date(record_date);
        setBook_id(book_id);
        setUser_id(user_id);
        setUser_name(user_name);
        setRecord_id(record_id);
    }
    public String getBook_name(){
        return book_name;
    }
    public String getUser_name(){
        return user_name;
    }
    public String getBook_id(){
        return book_id;
    }
    public String getUser_id(){return user_id;}
    public String getRecord_content(){return record_content;}
    public String getRecord_date(){return record_date;}
    public String getRecord_id(){return record_id;}

    public void setBook_name(String book_name){
        this.book_name=book_name;
    }
    public void setBook_id(String book_id){
        this.book_id=book_id;
    }
    public void setUser_name(String user_name){
        this.user_name=user_name;
    }
    public void setUser_id(String user_id){this.user_id=user_id;}
    public void setRecord_content(String record_content){this.record_content=record_content;}
    public void setRecord_date(String record_date){this.record_date=record_date;}
    public void setRecord_id(String record_id){this.record_id=record_id;}

}
