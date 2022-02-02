<?php
               
               require_once("../conexion.php");
               
                if(!$conn){
                   echo "Error: no se pudo conectar a MySQL." . PHP_EOL . "</br>";
                   echo "codigo del error: " . mysqli_connect_errno() . "</br>";
                   echo "Descripcion del error: " . PHP_EOL . "</br>";

                }else{
                   
                    $sql = "SELECT * FROM EQUIPO WHERE iIdEquipo NOT IN (SELECT iIdEquipo FROM EQUIPO_USUARIO WHERE iIdUsuario = (SELECT iIdUsuario FROM USUARIO WHERE sUser = '". $_GET["txtUsuario"]."'))";


                    $result = $conn->query($sql);
                    
                    $equipos_usuario = array();

                    while($fila = mysqli_fetch_assoc($result)){ // Coge todas las filas mientras que haya y las guarda en cada iteracion en la variable fila
                        $equipos_usuario[] = array (
                        'iIdEquipo' => $fila["iIdEquipo"],
                        'sNombre' => $fila["sNombre"],
                            'sFoto' => $fila["sFoto"],
                            'iMiembros' => $fila["iMiembros"]);
                    }
                    
              
                }
                    $json =  json_encode ($equipos_usuario);

                    echo $json;
                    mysqli_close($conn);
    

?>