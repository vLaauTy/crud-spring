package com.registro2.CRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registro2.CRUD.model.Checklist;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario

}
