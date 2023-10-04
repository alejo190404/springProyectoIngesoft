package com.ingesoft.bikemap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ingesoft.bikemap.logic.ServicioUsuario;

@SpringBootTest
public class CURegistrarUsuarioTest {
    
    @Autowired
    ServicioUsuario servicio;

    @Test
    void probarLogin() throws Exception{
        
        try {servicio.RegistrarUsuario("Alejandro",
                                "alejo190404",
                                "secret",
                                "secret",
                                "alejo@gmail.com",
                                "alejo@gmail.com");

        
        } catch (Exception e) {
            fail();
        }

    }

}
