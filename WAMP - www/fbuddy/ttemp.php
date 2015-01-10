<?php
	if(isset($_GET["lattitude"]) && isset($_GET["longitude"]))	
{
	//include the PHP-MySQL Library
	include_once("mysqllibrary.php");
	$latt=$_GET["lattitude"];
	$longt=$_GET["longitude"];
	
	//Fetch the results and display them
	$query = "SELECT * FROM tbllogin";
	mysqlStart('localhost','fbuddy_database');

	//get the required results
	$result = searchDatabase($query,array('id','name','phno','flagbit','lattitude','longitude'));

	//go through each result, and display it to the user
	for($count=0;$count<sizeof($result);$count++)
	{

		echo $result[$count]['id'] . "," . $result[$count]['name'] . "," . $result[$count]['phno'] . "," . $result[$count]['flagbit'] . "," . $result[$count]['lattitude'] . "," . $result[$count]['longitude'] . "EOR" ;
         
	}
} 
?>