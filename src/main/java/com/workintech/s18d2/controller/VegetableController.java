package com.workintech.s18d2.controller;

import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.services.VegetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vegetable")
public class VegetableController {
    @Autowired
    private VegetableService vegetableService;

    @GetMapping("/")
    public List<Vegetable> getByPriceAsc() {
        return  vegetableService.getByPriceAsc();
    }

    @GetMapping("/{id}")
    public Vegetable getById(@PathVariable long id) {
        return vegetableService.getById(id);
    }

    @GetMapping("/desc")
    public List<Vegetable> getByPriceDesc() {
        return vegetableService.getByPriceDesc();
    }

    @PostMapping("/")
    public Vegetable save(@RequestBody Vegetable vegetable) {
        return vegetableService.save(vegetable);
    }

    @PostMapping("/{name}")
    public List <Vegetable> searchByName(@PathVariable String name) {
        return vegetableService.searchByName(name);
    }
}
