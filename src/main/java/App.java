import controller.ClientController;
import ui.ElectricaUI;

public class App {
    public static void main(String[] args) {
        ClientController ctrl = new ClientController(false);
        ElectricaUI ui = new ElectricaUI(ctrl);
        ui.Run();
    }
}
