package model;

import model.Animals.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimalCard {
    public String card(Animal animal) {

        String commandsString = cardListToStr(animal.getCommands());

        if (animal instanceof Dog) {
            return String.format("%s;%s;%s" , animal.getName(), commandsString, "Собака");
        } else if (animal instanceof Cat) {
            return String.format("%s;%s;%s",  animal.getName(), commandsString, "Кот");
        } else if (animal instanceof Hamster) {
            return String.format("%s;%s;%s",  animal.getName(), commandsString, "Хомяк");
        } else if (animal instanceof Horse) {
            return String.format("%s;%s;%s",  animal.getName(), commandsString, "Лошадь");
        } else if (animal instanceof Camel) {
            return String.format("%s;%s;%s",  animal.getName(), commandsString, "Верблюд");
        } else if (animal instanceof Donkey) {
            return String.format("%s;%s;%s",  animal.getName(), commandsString ,  "Осёл");
        } else {
            throw new IllegalArgumentException("Ошибка! Объект неизвестного типа");
        }

    }

    public static String cardListToStr(List<String> str) {
        StringBuilder result = new StringBuilder();
        for (String string : str) {
            if (!string.isEmpty()) {
                result.append(string);
                result.append(", ");
            }
        }
        if (!result.isEmpty()) {
            result.setLength(result.length() - 2);
        }
        return String.valueOf(result);
    }

    public Animal card(String line) {
        String[] lines = line.split(";");
        if (lines.length < 3) {
            throw new IllegalArgumentException("Недопустимый формат данных в файле animals.scv!");
        }
        String[] lineCommands = lines[1].split(", ");
        List<String> listCommands = new ArrayList<>(Arrays.asList(lineCommands));
        try {
            return switch (lines[2]) {
                case ("Собака") -> new Dog(lines[0], listCommands);
                case ("Кот") -> new Cat(lines[0], listCommands);
                case ("Хомяк") -> new Hamster(lines[0], listCommands);
                case ("Лошадь") -> new Horse(lines[0], listCommands);
                case ("Верблюд") -> new Camel(lines[0], listCommands);
                case ("Осёл") -> new Donkey(lines[0], listCommands);
                default -> throw new IllegalArgumentException("Недопустимый формат данных в файле animals.scv!");
            };
        } catch (NumberFormatException e) {
            System.out.println("Недопустимый формат данных в файле animals.scv!");
        }
        return null;
    }
}
