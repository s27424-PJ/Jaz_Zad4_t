package org.example.schroniskozwierzat;

import com.example.api.AnimalApi;
import com.example.api.model.AnimalRequest;
import com.example.api.model.AnimalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/animals")
public class AnimalController implements AnimalApi {
    private final AnimalService animalService;
    private final AnimalMapper animalMapper;

    @Autowired
    public AnimalController(AnimalService animalService, AnimalMapper animalMapper) {
        this.animalService = animalService;
        this.animalMapper = animalMapper;
    }

    @GetMapping
    public List<AnimalResponse> listAnimals() {
        List<Animal> animals = animalService.getAllAnimals();
        return animals.stream()
                .map(animalMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public AnimalResponse createAnimal(@RequestBody AnimalRequest animalRequest) {
        Animal animal = animalMapper.mapToAnimal(animalRequest);
        Animal savedAnimal = animalService.saveAnimal(animal);
        return animalMapper.toDto(savedAnimal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable UUID id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponse> updateAnimal(@PathVariable UUID id, @RequestBody AnimalRequest animalRequest) {
        Animal animal = animalMapper.mapToAnimal(animalRequest);
        animal.setId(id);
        Animal updatedAnimal = animalService.saveAnimal(animal);
        return ResponseEntity.ok(animalMapper.toDto(updatedAnimal));
    }
}
