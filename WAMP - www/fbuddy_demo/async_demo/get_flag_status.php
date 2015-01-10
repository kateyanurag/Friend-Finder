<?php
	include_once('mysqllibrary.php');
	
	$query = "SELECT flagbit FROM tbl_login where id=$id;";
	mysqlStart('localhost','fbuddy_database');
	
	$res = searchDatabase($query,array('flag'));
	if($res[0]['flag'] == 1)
	{
		//update the status to be zero
		echo 1;
		$query = "UPDATE tbl_login SET flag=0";
		executeQuery($query);
	}
	else
	{
		echo 0;
	}
	mysqlEnd();
?>