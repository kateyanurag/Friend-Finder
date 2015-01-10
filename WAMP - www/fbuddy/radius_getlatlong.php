<?php
	

		if(isset($_GET["lattitude"]) && isset($_GET["longitude"]) && isset($_GET["radius"])
		{
		//include the PHP-MySQL Library
		include_once("mysqllibrary.php");
		
		//now insert the record in the database
                $latt  = $_GET["lattitude"];
		$longt = $_GET["longitude"];
		$radius = $_GET["radius"];
                 echo "Rahul";
function calc($tlatt,$tlongt)
	{

	  	$temp = ((cos($tlatt) * cos($tlongt) * cos($latt) * cos($longt)) + (cos($tlatt) * 
                          sin($tlongt) * cos($latt) * sin($longt)) + (sin($tlatt) * sin($latt)));
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
		
                echo $result[$count]['id'];
		$temp= calc($result[$count]['lattitude'],$result[$count]['longitude']);
		echo $temp;	
         }
    
		$insert_result = executeQuery($query);
		
		if($insert_result != -1)
			echo "Inserted Successfully via PHP";
		else
			echo "Cannot insert to database";
			
		//close the connection
		mysqlEnd();
}
	
?>