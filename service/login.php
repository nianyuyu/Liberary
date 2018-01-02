<?php
	//导入数据库连接文件
	require_once("mysqli_connect.php"); 

	//接收来自POST方式提交的值（用户键入的值）
	$user_id=$_POST['user_id'];   
	$user_password=$_POST['user_password']; 

	//查询用户键入的值和数据库中的值是否一致
	$q="select * from users where user_id=$user_id and user_password='$user_password'";
	$r=mysqli_query($dbc,$q);
	$num=mysqli_num_rows($r);
	$response=array();
	$row=mysqli_fetch_array($r,MYSQL_ASSOC);
	if($num>0){
		$response["success"]=1;
		$response["user_name"]=$row['user_name'];
	}else{
		$response["success"]=0;
	}

	//将查询结果封装成JSON格式
	echo json_encode($response);
	//关闭数据库连接
	mysqli_close($dbc);  

?>