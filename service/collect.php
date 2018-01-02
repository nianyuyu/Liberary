<?php
	//导入数据库连接文件
	require_once('mysqli_connect.php');

	//获取android以get方式提交的数据
	$book_id=$_GET['book_id'];
	$user_id=$_GET['user_id'];

	//将收藏的书籍，用户信息写入collect表中
	$q="insert into collect(user_id,book_id) values('$user_id','$book_id')";
	$r=mysqli_query($dbc,$q);
	if($r){
	 	$response["success"]=1;
	}else{
		$response["success"]=0;
	}
	
	//将查询结果封装成JSON格式
	echo json_encode($response);

	//关闭数据库连接
	mysqli_close($dbc);  
?>