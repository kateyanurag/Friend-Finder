<html>
	<body>
		<?php
			if(isset($_POST["txtName"]) && isset($_POST["txtAge"]) && isset($_POST["txtAddress"]) && isset($_POST["txtContact"]) && isset($_POST["txtOtherInformation"]))
			{
				//include the PHP-MySQL Library
				include_once("mysqllibrary.php");
				
				//now insert the record in the database
				$name = $_POST["txtName"];
				$age = $_POST["txtAge"];
				$address = $_POST["txtAddress"];
				$contact = $_POST["txtContact"];
				$other_information = $_POST["txtOtherInformation"];
				
				$query = "INSERT INTO tblpersonalinformation (name,age,address,contact,other_information) VALUES ('" . $name . "'," . $age . ",'" . $address . "','" . $contact . "','" . $other_information . "');";
				mysqlStart('localhost','dbfStudents');
				$insert_result = executeQuery($query);
				
				if($insert_result != -1)
					echo "Inserted Successfully<br/>";
				else
					echo "Cannot insert to database <br/>";
					
				//close the connection
				mysqlEnd();
			}
		?>
		<form method="POST" action="#">
			ID:
			<input type="TEXT" name="txtID" id="txtID"></input> <br/>
			Name:
			<input type="TEXT" name="txtName" id="txtName"></input> <br/>
			Age:
			<input type="TEXT" name="txtAge" id="txtAge"></input> <br/>
			Address:
			<input type="TEXT" name="txtAddress" id="txtAddress"></input> <br/>
			Contact:
			<input type="TEXT" name="txtContact" id="txtContact"></input> <br/>
			Other Information:
			<input type="TEXT" name="txtOtherInformation" id="txtOtherInformation"></input> <br/>
			<input type="SUBMIT" name="btnSubmit" id="btnSubmit" value="Insert"/>
			<input type="RESET" name="btnReset" id="btnReset" value="Clear"/>
		</form>
		
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
				echo $result[$count]['name'] . "," . $result[$count]['age'] . "," . $result[$count]['address'] . "," . $result[$count]['contact'] . "<br/>";
			}
		?>
	</body>
</html>