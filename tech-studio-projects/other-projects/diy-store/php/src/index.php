<?php

session_start();
session_regenerate_id(true);
require_once("include_db.php");
if(isset($_POST["send"]))
{
	$sql="SELECT * FROM benutzer 
	WHERE userEmail=:email";

	$abfrage=$db->prepare($sql);
	$abfrage->bindParam(":email",$_POST["email"]);
	$abfrage->execute();
	$row=$abfrage->fetch();
	// If user is found, password is synchronized
	if($row !== false)
	{
		if($row["userPassword"])
		{
			// User recognized
			$_SESSION["userID"]=$row["userID"];
			$_SESSION["userName"]=$row["userName"];
			$_SESSION["userRole"]=$row["userRole"];
			// Forwarding
			//$location = "http://localhost/fernschule/baumarkt/src/Orders-oo.php";
			header("location: Orders-oo.php"); //http://localhost/fernschule/baumarkt/src/
		}//PW END			
	}//Check user END
	else
	{
		echo "E-Mail are Passwort not correct. Please try again.";
	}
}//send END

?>
<!DOCTYPE html>
<html>
<head>
	<title>DIY Store</title>
	<meta charset="UTF-8">
</head>
<body>
<div id="wrapper">
<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);  ?>">
E-Mail:<input type="email" name="email"><br>
Password:<input type="password" name="password"><br>
<input type="submit" name="send">
</form>
</div>
</body>
</html>