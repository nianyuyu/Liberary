package com.example.asus_pc.get_comment;

public class Borrow {
    private String book_name;
    private String borrow_date;
    private String book_id;
    private String user_name;
    private String user_id;
    private String pic_url;
    public Borrow(String book_name,String borrow_date,String book_id,String user_name,String user_id,String pic_url){
        setBook_name(book_name);
        setBorrow_date(borrow_date);
        setBook_id(book_id);
        setUser_name(user_name);
        setUser_id(user_id);
        setPic_url(pic_url);
    }
    public String getBook_name(){
        return book_name;
    }
    public String getUser_name(){
        return user_name;
    }
    public String getUser_id(){return  user_id;}
    public String getPic_url(){return pic_url;}


    public String getBook_id(){
        return book_id;
    }
    public String getBorrow_date(){
        return borrow_date;
    }
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
    public void setBorrow_date(String borrow_date){
        this.borrow_date=borrow_date;
    }
    public void  setPic_url(String pic_url){this.pic_url=pic_url;}
}
