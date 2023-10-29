package controller;

import model.Animal;
import model.AnimalService;

import java.util.List;

public class Controller {
    private final AnimalService animalService;

    public Controller(AnimalService animalService) {
        this.animalService = animalService;
    }

    public void validateAnimalData(Animal animal) {
        if (animal.getName().isEmpty()) {
            throw new IllegalStateException("Пустое поле!");
        }
    }

    public void saveAnimal(Animal animal) {
        validateAnimalData(animal);
        animalService.createAnimal(animal);
    }

    public Animal readAnimal(String name) throws Exception {
        List<Animal> animals = animalService.getAllAnimals();
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                return animal;
            }
        }
        throw new Exception("Животное не найдено!");
    }

    public List<Animal> readAnimals() {
        return animalService.getAllAnimals();
    }

    public boolean checkAnimal(String name) {
        List<Animal> animals = animalService.getAllAnimals();
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void teachNewCommand(String name, List<String> newCommands) {
        Animal animal = null;
        try {
            animal = readAnimal(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        animal.addCommands(newCommands);
        animalService.updateAnimal(animal);
    }
}
