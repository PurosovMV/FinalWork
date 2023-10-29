package model.Animals;

import model.AnimalCard;
import model.Pet;

import java.util.List;

public class Dog extends Pet {
    public Dog(String name, List<String> commands) {
        super(name, commands);
    }

    @Override
    public String toString() {
        String commandsString = AnimalCard.cardListToStr(this.getCommands());
        return "Домашнее животное. Собака. Имя: " + this.getName() + ". Команды: " + commandsString;
    }
}
