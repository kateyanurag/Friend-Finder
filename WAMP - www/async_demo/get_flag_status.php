<?php
	include_once('mysqllibrary.php');
	
	$query = "SELECT * FROM tblFlag";
	mysqlStart('localhost','dbfasynctask');
	
	$res = searchDatabase($query,array('flag'));
	if($res[0]['flag'] == 1)
	{
		//update the status to be zero
		echo 1;
		$query = "UPDATE tblFlag SET flag=0";
		executeQuery($query);
	}
	else
	{
		echo 0;
	}
	mysqlEnd();
?>