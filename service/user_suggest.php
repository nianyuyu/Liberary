<?php
	//导入数据库连接文件
	require_once('mysqli_connect.php');

	//接受以POST方式提交的值
	$user_id=$_POST['user_id'];
	$user_name=$_POST['user_name'];
	$suggest_content=$_POST['suggest_content'];
	
	//向数据表suggest中写入数据
	$q="insert into suggest (user_id,user_name,suggest_content)  values ('$user_id','$user_name','$suggest_content')";
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