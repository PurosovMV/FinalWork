package model.Animals;

import model.AnimalCard;
import model.PackAnimal;

import java.util.List;

public class Donkey extends PackAnimal {
    public Donkey(String name, List<String> commands) {
        super(name, commands);
    }

    @Override
    public String toString() {
        String commandsString = AnimalCard.cardListToStr(this.getCommands());
        return "Вьючное животное. Осёл. Имя: " + this.getName() + ". Команды: " + commandsString;
    }
}
