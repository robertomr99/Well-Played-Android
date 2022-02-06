<?php
            require_once("../conexion.php");
               
                if(!$conn){
                   echo "Error: no se pudo conectar a MySQL." . PHP_EOL . "</br>";
                   echo "codigo del error: " . mysqli_connect_errno() . "</br>";
                   echo "Descripcion del error: " . PHP_EOL . "</br>";
                 


                }else{
              
                    $sql = "SELECT iIdUsuario,sUser, sFoto FROM USUARIO WHERE iIdUsuario IN (SELECT iIdUsuario FROM PETICIONES WHERE iIdEquipo = ".$_GET["txtEquipo"].")";
            
                    $result = $conn->query($sql);

                    $usuarios = array();

                    while($fila = mysqli_fetch_assoc($result)){ // Coge todas las filas mientras que haya y las guarda en cada iteracion en la variable fila
                        $usuarios[] = array ('iIdUsuario' => $fila["iIdUsuario"],
                        'sUser' => $fila["sUser"],
                                            'sFoto' => $fila["sFoto"]); 
                    }

                    $json =  json_encode ($usuarios);

                    echo $json;
                    
                    mysqli_close($conn);

                }

?>