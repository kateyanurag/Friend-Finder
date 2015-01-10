<?php
   if(isset($_GET["txtId"]))
   {
   $id = $_GET["txtId"];
	include_once('mysqllibrary.php');
	
	$query = "SELECT mbit FROM tbl_chat WHERE id=$id;";
	mysqlStart('localhost','fbuddy_database');
	
	$res = searchDatabase($query,array('mbit'));
	if($res[0]['mbit'] == 1)
	{
		//update the status to be zero
		echo 1;
		$query = "UPDATE tbl_chat SET mbit=0;";
		executeQuery($query);
	}
	else
	{
		echo 0;
	}
	mysqlEnd();
	}
?>