package model.Animals;

import model.AnimalCard;
import model.PackAnimal;

import java.util.List;

public class Camel extends PackAnimal {
    public Camel(String name, List<String> commands) {
        super(name, commands);
    }

    @Override
    public String toString() {
        String commandsString = AnimalCard.cardListToStr(this.getCommands());
        return "Вьючное животное. Верблюд. Имя: " + this.getName() + ". Команды: " + commandsString;
    }
}
