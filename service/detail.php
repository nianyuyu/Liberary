<?php
	require_once('mysqli_connect.php');
	if(isset($_GET['book_id'])){
	$arr=null;
	$n=0;
	$book_id=$_GET['book_id'];
	$q="select * from record where book_id ='$book_id'";
	$r=mysqli_query($dbc,$q);

	printf("%s\n",mysqli_error($dbc));
	while($row=mysqli_fetch_array($r)){
		
		$arr[$n++]=array("user_name"=>$row['user_name'],"record_date"=>$row['record_date'],"record_content"=>$row['record_content']);

	}
	echo json_encode($arr);
	}
?>