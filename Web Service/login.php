<?php

$dbname ="db_pedulicovid19";
$user = "root";
$pass = "";
$host = "localhost";

$conn=mysqli_connect($host, $user, $pass, $dbname);

 $email = $_POST ['email'];
 $password = $_POST['password'];

$query = "SELECT email, password FROM user WHERE email = '$email' and password = '$password'";

$res=mysqli_query($conn, $query);
$row=mysqli_fetch_assoc($res);
if($row['email'] ==$email && $row['password']==$password)
{
	echo json_encode(array('response'=>true));
}
else
{
	echo json_encode(array('response'=>false));
}

?>