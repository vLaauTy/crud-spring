package com.registro2.CRUD.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.registro2.CRUD.model.Checklist;
import com.registro2.CRUD.model.Item;
import com.registro2.CRUD.repository.ItemRepository;
import com.registro2.CRUD.service.ChecklistService;

@Controller
// Los controladores gestionan las solicitudes que llegan a una URL específica
// y determinan qué vista (HTML) devolver.
@RequestMapping("/checklists")
// Esta anotación indica que todas las rutas definidas en esta clase comenzarán
// con /checklists.
public class WebController {
    private final ChecklistService checklistService;
    // Aquí se está declarando un atributo de tipo ChecklistService.
    //
    // Está marcado como final, lo cual significa que solo se puede inicializar una
    // vez (en el constructor).
    //
    // Este es el servicio que contiene toda la lógica para interactuar con la base
    // de datos para las listas.

    private final ItemRepository itemRepo;
    // ItemRepository es un repositorio que permite acceder directamente a los datos
    // de
    // los Item en la base de datos.
    //
    // Esto se usa para manipular los ítems de una lista directamente, sin pasar por
    // el servicio.

    public WebController(ChecklistService checklistService, ItemRepository itemRepo) {
        this.checklistService = checklistService;
        this.itemRepo = itemRepo;
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("checklist", new Checklist());
        return "formulario_lista";
    }

    @PostMapping
    public String guardarChecklist(@ModelAttribute Checklist checklist) {
        checklistService.save(checklist);
        return "redirect:/checklists";
    }

    @GetMapping
    public String listarChecklists(Model model) {
        model.addAttribute("checklists", checklistService.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        Checklist checklist = checklistService.findById(id);
        model.addAttribute("checklist", checklist);
        model.addAttribute("nuevoItem", new Item());
        return "detalle";
    }

    @PostMapping("/{id}/items")
    public String agregarItem(@PathVariable Long id, @ModelAttribute Item nuevoItem) {
        Checklist checklist = checklistService.findById(id);
        nuevoItem.setChecklist(checklist);
        itemRepo.save(nuevoItem);
        return "redirect:/checklists/" + id;
    }

    // Dentro de WebController.java
    // 1) Muestra el formulario con datos para editar una checklist existente
    @GetMapping("/{id}/editar")
    public String editarFormulario(@PathVariable Long id, Model model) {
        Checklist checklist = checklistService.findById(id);
        model.addAttribute("checklist", checklist);
        return "formulario_lista";
    }

    // 2) Elimina la checklist y redirige al listado
    @GetMapping("/{id}/eliminar")
    public String eliminarChecklist(@PathVariable Long id) {
        checklistService.deleteById(id);
        return "redirect:/checklists";
    }

    @GetMapping("/{checklistId}/items/{itemId}/toggle")
    public String toggleItem(@PathVariable Long checklistId, @PathVariable Long itemId) {
        Item item = itemRepo.findById(itemId).orElseThrow();
        item.setCompletado(!item.isCompletado());
        itemRepo.save(item);
        return "redirect:/checklists/" + checklistId;
    }
}
