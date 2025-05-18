package com.registro2.CRUD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.registro2.CRUD.model.Checklist;
import com.registro2.CRUD.repository.ChecklistRepository;

@Service
public class ChecklistService {
    @Autowired
    private final ChecklistRepository repo;

    public ChecklistService(ChecklistRepository repo) {
        this.repo = repo;
    }

    public List<Checklist> findAll() {
        return repo.findAll();
    }

    public Checklist findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Checklist save(Checklist checklist) {
        return repo.save(checklist);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

}
