<?php
            require_once("../conexion.php");
               
                if(!$conn){
                   echo "Error: no se pudo conectar a MySQL." . PHP_EOL . "</br>";
                   echo "codigo del error: " . mysqli_connect_errno() . "</br>";
                   echo "Descripcion del error: " . PHP_EOL . "</br>";

                }else{
                   
                    $sql = "SELECT sNombre, sFoto, sDescripcion, sVideo FROM JUEGO WHERE iIdJuego IN (SELECT iIdJuego FROM USUARIO_JUEGO WHERE iIdUsuario = (SELECT iIdUsuario FROM USUARIO WHERE sUser = '". $_GET["txtUsuario"]."'))";


                    $result = $conn->query($sql);
                    
                    $myArray = array();

                    while($fila = mysqli_fetch_assoc($result)){ // Coge todas las filas mientras que haya y las guarda en cada iteracion en la variable fila
                        $myArray[] = array ('sNombre' => $fila["sNombre"],
                            'sFoto' => $fila["sFoto"],
                            'sDescripcion' => $fila["sDescripcion"],
                            'sVideo' => $fila["sVideo"]);
                    }
                    
              
                }
                    $json =  json_encode ($myArray);

                    echo $json;
                    mysqli_close($conn);
    
          
?>