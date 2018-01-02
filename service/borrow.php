<?php
	//导入数据库连接文件
	require_once("mysqli_connect.php");
	if(isset($_GET['user_id'])){
		$arr=null;
		$n=0;
		$user_id=$_GET['user_id'];

		//查询用户id在book表中的借阅情况
		$q="select pic_url,user_id,book_name,borrow_date,book_id,user_name from book where user_id=$user_id and book_status=1";
		$r=mysqli_query($dbc,$q);
		while($row=mysqli_fetch_array($r)){
			$arr[$n++]=array("pic_url"=>$row['pic_url'],"user_id"=>$row['user_id'],"book_name"=>$row['book_name'],"borrow_date"=>$row['borrow_date'],"book_id"=>$row['book_id'],"user_name"=>$row['user_name']);
		}
		
		//将查询结果封装成JSON格式
		echo json_encode($arr);
	}
?>