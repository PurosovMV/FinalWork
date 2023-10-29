package model;

import java.util.ArrayList;
import java.util.List;

public class AnimalService {
    private final AnimalCard animalCard = new AnimalCard();
    private final DataService dataService;

    public AnimalService(DataService dataService) {
        this.dataService = dataService;
    }

    public List<Animal> getAllAnimals() {
        List<String> lines = dataService.readAllLines();
        List<Animal> animals = new ArrayList<>();
        for (String line : lines) {
            animals.add(animalCard.card(line));
        }
        return animals;
    }

    private void saveAnimals(List<Animal> animals) {
        List<String> lines = new ArrayList<>();
        for (Animal animal : animals) {
            lines.add(animalCard.card(animal));
        }
        dataService.saveAllLines(lines);
    }

    private void saveAnimal(Animal animal, List<Animal> animals) {
        animals.add(animal);
        saveAnimals(animals);
    }

    public void createAnimal(Animal animal) {
        List<Animal> animals = getAllAnimals();
        saveAnimal(animal, animals);
    }

    private Animal findAnimal(String name, List<Animal> animals) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                return animal;
            }
        }
        throw new IllegalStateException("Животное не найдено!");
    }

    public void deleteAnimal(String name) {
        List<Animal> animals = getAllAnimals();
        animals.remove(findAnimal(name, animals));
        saveAnimals(animals);
    }

    public void updateAnimal(Animal animal) {
        deleteAnimal(animal.getName());
        List<Animal> animals = getAllAnimals();
        saveAnimal(animal, animals);
    }
}
