package toDoList.gui;

/**
 *
 * @author Matt Crow
 */
public class GuiLauncher {
    public static void launch(){
        TaskCreator taskCreator = new TaskCreator();
        TaskTableModel model = new TaskTableModel();
        Panel panel = new Panel(taskCreator, model);
        new Window(panel);
    }
}
