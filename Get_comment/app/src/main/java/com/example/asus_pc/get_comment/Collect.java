package com.example.asus_pc.get_comment;

/**
 * Created by asus-pc on 2015/11/30.
 */
public class Collect {
    private String book_name;
    private String book_writer;
    private String book_id;
    private String collect_id;
    private String user_id;
    private String borrow_date;
    private String book_publish;
    private String book_status;
    private String user_name;
    private String pic_url;

    public Collect(String book_name,String book_writer,String book_id,String collect_id,String user_id,String book_status,String borrow_date,String book_publish,String user_name,String  pic_url) {
        setBook_name(book_name);
        setBook_writer(book_writer);
        setBook_id(book_id);
        setCollect_id(collect_id);
        setUser_id(user_id);
        setBorrow_date(borrow_date);
        setBook_publish(book_publish);
        setBook_status(book_status);
        setUser_name(user_name);
        setPic_url(pic_url);

    }
    public String getPic_url(){return pic_url;}
    public String getBook_name() {
        return book_name;
    }
    public String getBook_writer(){return book_writer;}
    public String getBook_id(){return book_id;}
    public String getCollect_id(){return collect_id;}
    public String getUser_id(){return user_id;}
    public String getBook_status(){return book_status;}
    public String getBook_publish(){return book_publish;}
    public String getBorrow_date(){
        return borrow_date;
    }
    public String getUser_name(){
        return user_name;
    }

    public void setPic_url(String pic_url){this.pic_url=pic_url;}
    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
    public void setBook_writer(String book_writer){
        this.book_writer=book_writer;
    }
    public void setBook_id(String book_id){
        this.book_id=book_id;
    }
    public void setCollect_id(String collect_id){
        this.collect_id=collect_id;
    }
    public void setUser_id(String user_id){
        this.user_id=user_id;
    }
    public void setBook_publish(String book_publish){this.book_publish=book_publish;}
    public void setBook_status(String book_status){this.book_status=book_status;}
    public void setBorrow_date(String borrow_date){
        this.borrow_date=borrow_date;
    }
    public void setUser_name(String user_name){
        this.user_name=user_name;
    }
}
