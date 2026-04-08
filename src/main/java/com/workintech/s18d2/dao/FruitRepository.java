package com.workintech.s18d2.dao;

import com.workintech.s18d2.entity.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface FruitRepository extends JpaRepository<Fruit, Long> {

    // Testin 113. satırda aradığı metot
    @Query("SELECT f FROM Fruit f ORDER BY f.price DESC")
    List<Fruit> getByPriceDesc();

    // Testin 121. satırda aradığı metot
    @Query("SELECT f FROM Fruit f ORDER BY f.price ASC")
    List<Fruit> getByPriceAsc();

    // Testin 129. satır civarı aradığı metot
    @Query("SELECT f FROM Fruit f WHERE f.name LIKE %:name%")
    List<Fruit> searchByName(String name);
}