<?php
	//导入数据库连接文件
	require_once('mysqli_connect.php');
	//接受用户键入的关键字
	if(isset($_GET['book_name'])){
		$arr=null;
		$n=0;
		$book_name=$_GET['book_name'];

		//根据关键字查询book表中相关的书名,作者名
		$q="select * from book where book_name like '%$book_name%' or book_writer like '%$book_name%'";
		$r=mysqli_query($dbc,$q);
		while($row=mysqli_fetch_array($r)){	
			$arr[$n++]=array("pic_url"=>$row['pic_url'],"borrow_date"=>$row['borrow_date'],"book_status"=>$row['book_status'],"book_publish"=>$row['book_publish'],"book_id"=>$row['book_id'],"book_writer"=>$row['book_writer'],"book_name"=>$row['book_name']);
		}
		//将查询数据封装成JSON格式
		echo json_encode($arr);
	}
?>