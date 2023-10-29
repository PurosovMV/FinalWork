import controller.Controller;
import model.AnimalCard;
import model.DataService;
import model.AnimalService;
import view.View;

public class Main {
    public static void main(String[] args) {
        DataService dataService = new DataService("animals.csv");
        AnimalService animalService = new AnimalService(dataService);
        Controller controller = new Controller(animalService);
        View view = new View(controller);
        view.run();
    }
}
