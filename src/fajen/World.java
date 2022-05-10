package fajen;

import fajen.Organisms.Organism;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class World extends JPanel
{

    public short getSizeOfTile()
    {
        return sizeOfTile;
    }

    public void setSizeOfTile(short sizeOfTile)
    {
        this.sizeOfTile = sizeOfTile;
    }

    private short sizeOfTile;
    public World()
    {
        listOfOrganisms = new ArrayList<Organism>();
        map = new Map();
    }

    public World(short x, short y)
    {
        listOfOrganisms = new ArrayList<Organism>();
        map = new Map(x, y);
    }

    public Map map;
    public ArrayList <Organism> listOfOrganisms;


    void draw(Graphics g)
    {
        for (Organism org: listOfOrganisms)
        {
            org.draw(g, new Dimension(sizeOfTile, sizeOfTile));
        }
    }





    @Override
    public void paintComponent(Graphics g)
    {
        //pierwsze rysowanie
        super.paintComponent(g);
        draw(g);
    }


    void rysuj2(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(200, 200, 200, 200);
    }
}
