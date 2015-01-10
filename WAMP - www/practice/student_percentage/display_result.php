<?php
	
	function calculate_percentage($marks1,$marks2,$marks3)
	{
		$percentage = ($marks1 + $marks2 + $marks3) / 300;
		$percentage = $percentage * 100;
		return $percentage;
	}
	
	function find_result($percentage)
	{
		if($percentage > 0 && $percentage < 40)
		{
			return "Fail";
		}
		else if($percentage >= 40 && $percentage < 60)
		{
			return "Grade C";
		}
		else if($percentage >= 60 && $percentage < 75)
		{
			return "Grade B";
		}
		else if($percentage >= 75 && $percentage < 90)
		{
			return "Grade A";
		}
		else if($percentage >= 90)
		{
			return "Grade A+";
		}
	}
	
	if( isset($_POST['txtName']) && isset($_POST['txtMarks1']) && isset($_POST['txtMarks2']) && isset($_POST['txtMarks3']))
	{
		$name = $_POST['txtName'];
		$marks1 = $_POST['txtMarks1'];
		$marks2 = $_POST['txtMarks2'];
		$marks3 = $_POST['txtMarks3'];
		
		//find the percentage
		$percentage = calculate_percentage($marks1,$marks2,$marks3);
		$result = find_result($percentage);
		
		echo "$name's result<br/>Percentage:$percentage<br/>Result:$result";
	}
	else
	{
		echo "Please come to this page with the help of the Home Page<br/>";
	}
	
?>