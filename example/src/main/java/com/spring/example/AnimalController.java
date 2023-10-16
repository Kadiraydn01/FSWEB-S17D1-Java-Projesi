package com.spring.example;

import com.spring.Validation.AnimalValidation;
import com.spring.entity.Animal;
import com.spring.response.AnimalResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/animal")
public class AnimalController {
    private Map<Integer, Animal> animals ;

    @PostConstruct
    public void init(){
    animals= new HashMap<>();
    }
    @GetMapping("/")
    public List<Animal> get(){
        return animals.values().stream().toList();
    }
    @GetMapping("/{id}")
public AnimalResponse get (@PathVariable int id){
        if(!AnimalValidation.isValid(id)){
            return new AnimalResponse(null,"id is not valid",400);
        }
        if(!AnimalValidation.isMapContainKey(animals,id)){
            return new AnimalResponse(null,"Animal is not exist" ,400);
        }
        return new AnimalResponse(animals.get(id),"Success",200);
    }
    @PostMapping("/")
    public AnimalResponse save(@RequestBody Animal animal){
        if(!AnimalValidation.isMapContainKey(animals, animal.getId())){
            return new AnimalResponse(null,"Animal is not exist" ,400);
        }
        if (!AnimalValidation.isAnimalCredentialsValid(animal)){
            return new AnimalResponse(null,"Animal credentials are not valid",400);
        }
        animals.put(animal.getId(),animal);
        return new AnimalResponse(animals.get(animal.getId()),"Success",200);
    }
    @PutMapping("/{id}")
    public AnimalResponse update(@PathVariable int id,@RequestBody Animal animal){
        if(!AnimalValidation.isValid(id)){
            return new AnimalResponse(null,"Animal is not valid" ,400);
        }if (!AnimalValidation.isAnimalCredentialsValid(animal)){
            return new AnimalResponse(null,"Animal credentials are not valid" ,400);
        }
        animals.put(id,new Animal(animal.getName(),id));
        return new AnimalResponse(animals.get(animal.getId()),"Success",200);
    }

    @DeleteMapping("/{id}")
    public AnimalResponse deleteAnimal(@PathVariable int id){
        if (!AnimalValidation.isMapContainKey(animals,id)){
            return new AnimalResponse(null, "Animal is already exist", 400);
        }
        Animal animal = animals.get(id);
        animals.remove(id);
        return new AnimalResponse(animal,"Success",200);
    }



}
