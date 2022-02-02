<?php
    require_once("../conexion.php");

  
    $sql = "SELECT * FROM JUEGO";

    $result = $conn->query($sql);


    $myArray = array();

    while($fila = mysqli_fetch_assoc($result)){ 
        $myArray[] = array ('iIdJuego' => $fila["iIdJuego"],
                            'sNombre' => $fila["sNombre"],
                            'sDescripcion' => $fila["sDescripcion"],
                            'sFoto' => $fila["sFoto"],
                            'sVideo' => $fila["sVideo"]);
                            }

    $json =  json_encode ($myArray);

    echo $json;
    mysqli_close($conn);


?>