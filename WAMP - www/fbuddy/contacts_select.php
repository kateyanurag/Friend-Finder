<?php
	include_once("mysqllibrary.php");
	//Fetch the results and display them
	$query = "SELECT phno FROM tbllogin WHERE flagbit = 1";
	mysqlStart('localhost','fbuddy_database');
	//get the required results
	$result = searchDatabase($query,array('phno'));
	//go through each result, and display it to the user
	for($count=0;$count<sizeof($result);$count++)
	{
		echo $result[$count]['phno'] . "EOR";
	}

?>