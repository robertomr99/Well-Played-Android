<?php
            require_once("../conexion.php");
               
                if(!$conn){
                   echo "Error: no se pudo conectar a MySQL." . PHP_EOL . "</br>";
                   echo "codigo del error: " . mysqli_connect_errno() . "</br>";
                   echo "Descripcion del error: " . PHP_EOL . "</br>";
                 


                }else{
              
                    
                    $sql = "INSERT INTO USUARIO(sEmail,sUser,sPassword,sFechaNacimiento,iPais,iMonedas,iAdmin,sFoto,sCodigo) VALUES ('".$_GET["txtEmail"]. "' , '".$_GET["txtUsuario"]."' , '".$_GET["txtPass"]."' ,'".$_GET["txtFechaNacimiento"]."' , ".$_GET["txtPais"].",".$_GET["txtMonedas"].",".$_GET["txtAdministrador"].",'".$_GET["txtFoto"]."','".$_GET["txtCodigo"]."')";
            

                    echo $sql;

                    if(mysqli_query($conn, $sql)){
                        
                        echo "OK.";

                    } else {
                        echo "ERROR: " . mysqli_error($conn);
                    }
                    
                    mysqli_close($conn);

                }

?>