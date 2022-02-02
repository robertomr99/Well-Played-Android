<?php
    //require_once("../conexion.php");

  
    $sql = "SELECT * FROM PRODUCTOS";

    $result = $conn->query($sql);


    $myArray = array();

    while($fila = mysqli_fetch_assoc($result)){ 
        $myArray[] = array ('iIdProducto' => $fila["iIdProducto"],
                            'sNombre' => $fila["sNombre"],
                            'iPrecio' => $fila["iPrecio"],
                            'sFoto' => $fila["sFoto"],
                            'iIdCategoria' => $fila["iIdCategoria"]);
                            }

    $json =  json_encode ($myArray);

    echo $json;
    mysqli_close($conn);


?>