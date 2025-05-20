package com.registro2.CRUD.controller;

import com.registro2.CRUD.model.Usuario;
import com.registro2.CRUD.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "formulario_usuario";
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "usuarios";
    }

    @PostMapping("/nuevo")
    public String crearUsuario(@ModelAttribute Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return "redirect:/usuarios/nuevo?exito";
    }
}
