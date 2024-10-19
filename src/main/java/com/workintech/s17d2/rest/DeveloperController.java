package com.workintech.s17d2.rest;

import com.workintech.s17d2.dto.DeveloperResponse;
import com.workintech.s17d2.model.*;
import com.workintech.s17d2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/developers")//localhost:8585/workintech/developer/path
public class DeveloperController {

    public Map<Integer, Developer> developers = new HashMap<>();
    private Taxable taxable;

    @Autowired
    public DeveloperController(Taxable taxable) {
        this.taxable = taxable;
    }

    @Autowired

    @PostConstruct
    public void init() {
        this.developers = new HashMap<>();
        developers.put(1, new JuniorDeveloper(1, "buse ", 50000d));
        developers.put(2, new MidDeveloper(2, "ebu", 70000d));
        developers.put(3, new SeniorDeveloper(3, "BİLAL", 90000d));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeveloperResponse save(@RequestBody Developer developer) {
        Developer createdDeveloper = DeveloperFactory.createDeveloper(developer, taxable);
        if (Objects.nonNull(createdDeveloper)) {
            developers.put(createdDeveloper.getId(), createdDeveloper);
        }
        return new DeveloperResponse(createdDeveloper, "create işlemi başarılı", HttpStatus.CREATED.value());
    }

    @GetMapping
    public List<Developer> getAllDevelopers() {
        return developers.values().stream().toList();
    }

    @GetMapping("/{id}")
    public DeveloperResponse getById(@PathVariable("id") int id) {
        Developer foundDeveloper = this.developers.get(id);
        if (foundDeveloper == null) {
            return new DeveloperResponse(null, id + "ile search yapıldığında kayıt bulunur.", HttpStatus.NOT_FOUND.value());
        }
        return new DeveloperResponse(foundDeveloper, "İD İLE SEARCH BAŞARILI", HttpStatus.OK.value());
    }


    @PutMapping("/{id}")
    public DeveloperResponse update(@PathVariable("id") int id, @RequestBody Developer developer) {
        developer.setId(id);
        Developer newDeveloper = DeveloperFactory.createDeveloper(developer, taxable);
        this.developers.put(id, newDeveloper);
        return new DeveloperResponse(newDeveloper, "update başarılı",HttpStatus.OK.value());
    }

    @DeleteMapping("/{id}")
    public DeveloperResponse delete (@PathVariable("id") int id) {
        Developer removedDeveloper = this.developers.get(id);
        if (removedDeveloper == null) {
            return new DeveloperResponse(null, "Developer bulunamadı.", HttpStatus.NOT_FOUND.value());
        }
        this.developers.remove(id);
        return new DeveloperResponse(removedDeveloper, "silme başarılı", HttpStatus.OK.value());
    }
}