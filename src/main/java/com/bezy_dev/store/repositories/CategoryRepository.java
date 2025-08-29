package com.bezy_dev.store.repositories;

import com.bezy_dev.store.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}
