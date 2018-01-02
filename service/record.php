<?php
	//导入数据库连接文件
	require_once('mysqli_connect.php');

	//接受android端以POST方式提交的数据
	$user_id=$_POST['user_id'];
	$book_name=$_POST['book_name'];
	$record_content=$_POST['record_content'];
	$book_id=$_POST['book_id'];
	$user_name=$_POST['user_name'];
	
	//向数据表record中写入数据
	$q="insert into record (user_id,book_id,book_name,record_content,user_name)  values ('$user_id','$book_id','$book_name','$record_content','$user_name')";
	$r=@mysqli_query($dbc,$q);
	if($r){
	 	$response["success"]=1;
	}else{
		$response["success"]=0;
	}

	//以JSON格式封装
	echo json_encode($response);
	mysqli_close($dbc);
  
?>