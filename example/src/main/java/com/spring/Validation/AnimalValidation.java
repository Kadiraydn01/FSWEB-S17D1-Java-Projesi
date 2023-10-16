package com.spring.Validation;

import com.spring.entity.Animal;

import java.util.Map;

public class AnimalValidation {
    public static boolean isValid(int id){
        return !(id <0);
    }
    public static boolean isMapContainKey(Map<Integer, Animal> animals, int id){
        return animals.containsKey(id);
    }
    public static boolean isAnimalCredentialsValid(Animal animal){
        return !(animal.getId()<= 0 || animal.getName() == null ||animal.getName().isEmpty());
    }
}