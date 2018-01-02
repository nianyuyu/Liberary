<?php
	//导入数据库连接文件
	require_once("mysqli_connect.php");

	//接受来自POST方式提交的值
	$new_password=$_POST['new_password'];
	$user_id=$_POST['user_id'];
	$pass_password=$_POST['pass_password'];
	
	//查询user表中的密码是否正确
	$q="select * from users where user_id='$user_id' and user_password='$pass_password'";
	$r=mysqli_query($dbc,$q);
	$num=mysqli_num_rows($r);
	$response=array();

	if($num>0){
		//更新users表中的密码
		$q1="update  users set user_password='$new_password'  where user_id='$user_id'";
		$r1=mysqli_query($dbc,$q1);
		if($r1){
			$response["success"]=1;//成功
		}else{
			$response["success"]=2;//服务器错误
		}
	}else{
		$response["success"]=0;//旧密码有误
	}

echo json_encode($response);
mysqli_close($dbc);

?>