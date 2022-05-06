import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame
{

    public static final String MAIN_WINDOW_TITLE = "Fajen okno";

    public MainWindow(Dimension dimension)
    {
        setSize(dimension);
        setTitle(MAIN_WINDOW_TITLE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
