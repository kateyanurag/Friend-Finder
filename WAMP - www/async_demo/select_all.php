<?php
	include_once("mysqllibrary.php");
	//Fetch the results and display them
	$query = "SELECT * FROM tblWallPost";
	mysqlStart('localhost','dbfasynctask');
	//get the required results
	$result = searchDatabase($query,array('id','message'));
	//go through each result, and display it to the user
	for($count=0;$count<sizeof($result);$count++)
	{
		echo $result[$count]['id'] . "," . $result[$count]['message'] . "EOR";
	}
?>