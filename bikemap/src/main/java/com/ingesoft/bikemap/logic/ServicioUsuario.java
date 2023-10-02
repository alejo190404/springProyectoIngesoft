package com.ingesoft.bikemap.logic;

import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ingesoft.bikemap.dataAccess.RepositorioUsuario;
import com.ingesoft.bikemap.dominio.Usuario;

import ch.qos.logback.core.boolex.Matcher;

@Service
public class ServicioUsuario {
    
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public void CrearUsuario(
        String nombreCompleto, //1
        String nombreUsuario, //2
        String contrasenia, //4
        String confirmacionContrasenia, //6
        String correoRecuperacion, //8
        String confirmacionCorreoRecuperacion /*11*/ ){
            
        Usuario u = new Usuario();

        //3. Validar que no existe nombre de usuario

        Optional<Usuario> usuario = repositorioUsuario.findOne(u);

        if (usuario.isEmpty()){
            throw new Exception("Este nombre de usuario ya existe. Prueba con otro diferente");
        }
        
        //5. Validar contraseña con mínimo 6 caracteres

        if (contrasenia.length() < 6){
            throw new Exception("Tu contraseña debe tener al menos 6 caracteres.");
        }

        //7. Validar que contraseñas coinciden

        if (!contrasenia.equals(confirmacionContrasenia)){
            throw new Exception("Las contraseñas no coinciden.");
        }

        //9. Validar sintaxis de correo

        String regexCorreos = "^(.+)@(\\S+)$";

        Pattern pattern = Pattern.compile(regexCorreos);

        Matcher matcher = pattern.matcher(correoRecuperacion);
        System.out.println(email +" : "+ matcher.matches());
        

        //10. Validar que no existe correo de recuperacion



        //12. Validar que correos coincidan



        //Despues de todas las validaciones, añadir nuevo Usuario a la BD


    }

}
