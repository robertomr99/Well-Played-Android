<?php
            require_once("../conexion.php");
               
                if(!$conn){
                   echo "Error: no se pudo conectar a MySQL." . PHP_EOL . "</br>";
                   echo "codigo del error: " . mysqli_connect_errno() . "</br>";
                   echo "Descripcion del error: " . PHP_EOL . "</br>";

                }else{
                   
                    $sql = "SELECT iVictorias, iDerrotas, fWinRate FROM USUARIO_JUEGO WHERE 
                    iIdJuego = (SELECT iIdJuego FROM JUEGO WHERE sNombre = '".$_GET["txtJuego"]."') AND 
                    iIdUsuario = (SELECT iIdUsuario FROM USUARIO WHERE sUser = '".$_GET["txtUsuario"]."')";

                    $result = $conn->query($sql);   
                    $json = json_encode ($result ->fetch_assoc());
                    echo $json;
                    mysqli_close($conn);
                }
                    
                  
    
          
?>