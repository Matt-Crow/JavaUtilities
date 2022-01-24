package toDoList.gui;

/**
 *
 * @author Matt Crow
 */
public class GuiLauncher {
    public static void launch(){
        TaskCreator taskCreator = new TaskCreator();
        Panel panel = new Panel(taskCreator);
        Window window = new Window(panel);
    }
}
