<?php
require_once("../conexion.php");
               
if(!$conn){
    echo "Error: no se pudo conectar a MySQL." . PHP_EOL . "</br>";
    echo "codigo del error: " . mysqli_connect_errno() . "</br>";
    echo "Descripcion del error: " . PHP_EOL . "</br>";

}else{


$sql = "SELECT sEmail , sCodigo FROM USUARIO WHERE sEmail = '". $_GET["txtEmail"]."'";
$result = $conn->query($sql);

$json =  json_encode ($result->fetch_assoc());


}



echo $json;
mysqli_close($conn);

?>
