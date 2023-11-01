package by.es.springsecurity.controller;

import by.es.springsecurity.model.Developer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/developers")
public class DevelopersRestController {

    private List<Developer> developers = Stream.of(
            new Developer(1L, "Ivan", "Ivanov"),
            new Developer(2L, "Petr", "Petrov"),
            new Developer(3L, "Misha", "Sidorov")
    ).collect(Collectors.toList());

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping
    public List<Developer> getAll() {
        return developers;
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/{id}")
    public Developer getById(@PathVariable long id) {
        return developers.stream()
                .filter(developer -> developer.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @PreAuthorize("hasAuthority('WRITE')")
    @PostMapping
    public Developer create(@RequestBody Developer developer) {
        this.developers.add(developer);
        return developer;
    }

    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        developers.removeIf(developer -> developer.getId() == id);
    }
}
