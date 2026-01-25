package com.angel.apivuelos.controller;

import com.angel.apivuelos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public String login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
        String token = usuarioService.login(username, pwd);
        if (token != null) {
            return token;
        } else {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }
    }
}
