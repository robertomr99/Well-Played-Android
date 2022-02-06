<?php
    require_once("../conexion.php");

  
    $sql = "SELECT * FROM USUARIO";

    $result = $conn->query($sql);


    $myArray = array();

    while($fila = mysqli_fetch_assoc($result)){ 
        $myArray[] = array ('iIdUsuario' => $fila["iIdUsuario"],
                            'sEmail' => $fila["sEmail"],
                            'sUser' => $fila["sUser"],
                            'sPassword' => $fila["sPassword"],
                            'sFechaNacimiento' => $fila["sFechaNacimiento"],
                            'iPais' => $fila["iPais"],
                            'iMonedas' => $fila["iMonedas"],
                            'iAdmin' => $fila["iAdmin"],
                            'sFoto' => $fila["sFoto"],
                            'sCodigo' => $fila["sCodigo"]); 
    }

    $json =  json_encode ($myArray);

    echo $json;
    mysqli_close($conn);


?>