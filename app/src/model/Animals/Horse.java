package model.Animals;

import model.AnimalCard;
import model.PackAnimal;

import java.util.List;

public class Horse extends PackAnimal {
    public Horse(String name, List<String> commands) {
        super(name, commands);
    }

    @Override
    public String toString() {
        String commandsString = AnimalCard.cardListToStr(this.getCommands());
        return "Вьючное животное. Лошадь. Имя: " + this.getName() + ". Команды: " + commandsString;
    }
}
