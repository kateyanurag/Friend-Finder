<?php

	if(isset($_GET["id"]))
{
	$id=$_GET["id"];
	//include the PHP-MySQL Library
	include_once("mysqllibrary.php");
	$query="SELECT lattitude,longitude FROM tbl_login WHERE id = " . $id . ";";
	mysqlStart('localhost','fbuddy_database');
	//$get_result = executeQuery($query);
	//get the required results
	$get_result = searchDatabase($query,array('lattitude','longitude'));
	 
	if($get_result!=(-1)) 
	{
		echo $get_result[0]['lattitude'] . "," . $get_result[0]['longitude'];
	}
	
	else
	echo "Unsuccessfull retrieval";

}
?>