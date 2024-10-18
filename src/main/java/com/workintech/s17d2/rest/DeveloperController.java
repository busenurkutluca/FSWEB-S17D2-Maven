package com.workintech.s17d2.rest;

import com.workintech.s17d2.model.*;
import com.workintech.s17d2.tax.DeveloperTax;
import com.workintech.s17d2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/developers")
public class DeveloperController  {

    private Map<Integer, Developer> developers = new HashMap<>();
    public Taxable taxable;

@Autowired
    public DeveloperController(Taxable taxable) {
        this.taxable = taxable;
    }

    @Autowired

    @PostConstruct
    public void init() {
    this.developers=new HashMap<>();
        developers.put(1, new JuniorDeveloper(Experience.JUNİOR,1,"buse ", 50000.0));
        developers.put(2, new MidDeveloper(Experience.MİD, 2,"ebu", 70000.0));
        developers.put(3, new SeniorDeveloper(Experience.SENİOR, 3, "BİLAL",90000.0));
    }
    @GetMapping
    public List<Developer> getAllDevelopers() {
        return new ArrayList<>(developers.values());
    }

    @GetMapping("/{id}")
    public Developer getDeveloperById(@PathVariable int id) {
        return developers.get(id);
    }

    @PostMapping
    public Developer addDeveloper(@RequestBody Developer developer) {

        return developer;
    }

    @PutMapping("/{id}")
    public Developer updateDeveloper(@PathVariable int id, @RequestBody Developer developer) {
        developers.put(id, developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    public void deleteDeveloper(@PathVariable int id) {
        developers.remove(id);
    }
}
