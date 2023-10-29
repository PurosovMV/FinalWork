package model.Animals;

import model.AnimalCard;
import model.Pet;

import java.util.List;

public class Hamster extends Pet {
    public Hamster(String name, List<String> commands) {
        super(name, commands);
    }

    @Override
    public String toString() {
        String commandsString = AnimalCard.cardListToStr(this.getCommands());
        return "Домашнее животное. Хомяк. Имя: " + this.getName() + ". Команды: " + commandsString;
    }
}
