<?php
	if(isset($_GET["lattitude"]) && isset($_GET["longitude"]) && isset($_GET["radius"]) )	
{
	//include the PHP-MySQL Library
	include_once("mysqllibrary.php");
	$latt=$_GET["lattitude"];
	$longt=$_GET["longitude"];
	$rad=$_GET["radius"];
function calc($tlatt,$tlongt)
	{

	  	$temp = ((cos($tlatt * 3.14/180) * cos($tlongt * 3.14/180) * cos($latt * 3.14/180) * cos($longt * 3.14/180)) + (cos($tlatt * 3.14/180) * 
                          sin($tlongt * 3.14/180) * cos($latt * 3.14/180) * sin($longt * 3.14/180)) + (sin($tlatt * 3.14/180) * sin($latt * 3.14/180)));
		$temp1 = (acos($temp)) * 6378.1 * 1000;
                
		return $temp1;	
	}
	//Fetch the results and display them
	$query = "SELECT * FROM tbllogin";
	mysqlStart('localhost','fbuddy_database');

	//get the required results
	$result = searchDatabase($query,array('id','name','phno','flagbit','lattitude','longitude'));

	//go through each result, and display it to the user
	for($count=0;$count<sizeof($result);$count++)
	{
		$temp= calc($result[$count]['lattitude'],$result[$count]['longitude']);
		
		echo $temp . "EOR" ;
              //  echo $result[$count]['id'] ;
	//echo "hello" . $latt . " " . $longt . " " . $rad;
} 
} 
?>