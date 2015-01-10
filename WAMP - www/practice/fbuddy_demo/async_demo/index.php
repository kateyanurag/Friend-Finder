<html>
	<body>
		<?php
			if(isset($_GET["txtMessage"]))
			{
				//include the PHP-MySQL Library
				include_once("mysqllibrary.php");
				
				//now insert the record in the database
				$message = $_GET["txtMessage"];
				
				$query = "INSERT INTO tblWallPost (message) VALUES ('" . $message . "');";
				mysqlStart('localhost','dbfasynctask');
				$insert_result = executeQuery($query);
				
				if($insert_result != -1)
				{
					echo "Inserted Successfully via PHP";
					
					//Also update the Flag to true
					$query = "UPDATE tblflag SET flag=1";
					executeQuery($query);
				}
				else
					echo "Cannot insert to database";
					
				//close the connection
				mysqlEnd();
			}
		?>
		<form method="GET" action="#">
			Message:
			<input type="TEXT" name="txtMessage" id="txtMessage"></input> <br/>
			<input type="SUBMIT" name="btnSubmit" id="btnSubmit" value="Insert Wall Post"/>
			<input type="RESET" name="btnReset" id="btnReset" value="Clear"/>
		</form>
		
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
				echo $result[$count]['message'] . "<br/>";
			}
		?>
	</body>
</html>