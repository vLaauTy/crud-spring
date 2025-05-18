package com.registro2.CRUD.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registro2.CRUD.model.Checklist;
import com.registro2.CRUD.service.ChecklistService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/checklists")
public class ChecklistController {
    private final ChecklistService service;

    public ChecklistController(ChecklistService service) {
        this.service = service;
    }

    @GetMapping
    public List<Checklist> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Checklist crear(@RequestBody Checklist checklist) {

        return service.save(checklist);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Checklist> getById(@RequestParam Long id) {
        Checklist checklist = service.findById(id);
        if (checklist != null) {
            return ResponseEntity.ok(checklist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Checklist> update(@RequestParam Long id, @RequestBody Checklist checklist) {
        Checklist existingChecklist = service.findById(id);
        if (existingChecklist != null) {
            checklist.setId(id);
            return ResponseEntity.ok(service.save(checklist));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        Checklist checklist = service.findById(id);
        if (checklist != null) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
