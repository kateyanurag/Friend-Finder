<?php
	include_once("mysqllibrary.php");
	//Fetch the results and display them
	$query = "SELECT * FROM tblpersonalinformation";
	mysqlStart('localhost','dbfStudents');
	//get the required results
	$result = searchDatabase($query,array('id','name','age','address','contact','other_information'));
	//go through each result, and display it to the user
	for($count=0;$count<sizeof($result);$count++)
	{
		echo $result[$count]['id'] . "," . $result[$count]['name'] . "," . $result[$count]['age'] . "," . $result[$count]['address'] . "," . $result[$count]['contact'] . "," . $result[$count]['other_information'] . "EOR";
	}
?>