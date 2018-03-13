import controller.ClientController;
import ui.ElectricaUI;

public class App {
    public static void main(String[] args) {
        ClientController ctrl = new ClientController();
        ElectricaUI ui = new ElectricaUI(ctrl);
        ui.Run();
    }
}
