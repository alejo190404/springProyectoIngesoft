package com.ingesoft.bikemap.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingesoft.bikemap.dataAccess.RepositorioUsuario;
import com.ingesoft.bikemap.dominio.Usuario;



@Service
public class ServicioUsuario {
    
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public void RegistrarUsuario(
        String nombreCompleto, //1
        String nombreUsuario, //2
        String contrasenia, //4
        String confirmacionContrasenia, //6
        String correoRecuperacion, //8
        String confirmacionCorreoRecuperacion /*11*/ ) throws Exception{
            
        Usuario uPrueba = new Usuario();


        //3. Validar que no existe nombre de usuario

        uPrueba = repositorioUsuario.findByLogin(nombreUsuario);
        
        if (uPrueba != null){
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
        
        if(!matcher.matches()){
            throw new Exception("Este no es un email válido. Prueba con otro diferente.");
        }
        

        //10. Validar que no existe correo de recuperacion

        uPrueba = repositorioUsuario.findByCorreoRecuperacion(correoRecuperacion);

        if (uPrueba != null){
            throw new Exception("Este correo ya existe en el sistema. Verifica si ya tienes una cuenta o prueba con uno diferente");
        }

        //12. Validar que correos coincidan

        if(!confirmacionCorreoRecuperacion.equals(correoRecuperacion)){
            throw new Exception ("Los correos no coinciden.");
        }

        //Despues de todas las validaciones, añadir nuevo Usuario a la BD

        Usuario u = new Usuario();
        u.setNombreCompleto(nombreCompleto);
        u.setLogin(nombreUsuario);
        u.setContraseña(contrasenia);
        u.setCorreoRecuperacion(correoRecuperacion);
        
        repositorioUsuario.save(u);
    }

    public void IniciarSesion(
        String login, //1
        String contrasenia /*4*/) throws Exception{

        Usuario u = new Usuario();

        //2. Validar que usuario exista

        u = repositorioUsuario.findByLogin(login);

        if (u == null){
            throw new Exception("El usuario con login (" + login + ") no existe. Registralo.");
        }

        //4. Validar contraseña

        if (!contrasenia.equals(u.getContraseña())){
            throw new Exception("La contraseña ingresada no coincide.");
        }

        //5. Ingresar al sistema
        return;
    }

    
}
