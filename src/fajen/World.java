package fajen;

import javax.swing.*;
import java.awt.*;

public class World extends JPanel
{
    public World()
    {

    }

    public Map map;












    @Override
    public void paintComponent(Graphics g)
    {
        //pierwsze rysowanie
        super.paintComponent(g);
        rysuj(g);
    }

    void rysuj(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(200, 200, 200, 200);
    }

    void rysuj2(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(200, 200, 200, 200);
    }
}
