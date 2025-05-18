package com.registro2.CRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.registro2.CRUD.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario

}
