<?php
	//导入数据库连接文件
	require_once("mysqli_connect.php");

	$n=0;
	$arr=null;
	//遍历recommend数据表中所有的数据
 	$q = "select pic_url,user_name,book_id,book_name,recommend_date from  recommend";
  	$r = mysqli_query($dbc, $q);
	while($row=mysqli_fetch_array($r)){
		$arr[$n++]= array(
						"pic_url"=>$row['pic_url'],
						"user_name"=>$row['user_name'],
						"book_id"=>$row['book_id'],
						"book_name"=>$row['book_name'],
						"recommend_date"=>$row['recommend_date']
						);
	}

	//将查询结果封装成JSON格式
	echo json_encode($arr);
?>