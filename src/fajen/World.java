package fajen;

import fajen.Organisms.Organism;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

public class World extends JPanel
{

    public World()
    {
        listOfOrganisms = new PriorityQueue<Organism>();
        map = new Map();
    }

    public World(short x, short y)
    {
        listOfOrganisms = new PriorityQueue<Organism>();
        map = new Map(x, y);
    }

    public short getSizeOfTile()
    {
        return sizeOfTile;
    }

    public void setSizeOfTile(short sizeOfTile)
    {
        this.sizeOfTile = sizeOfTile;
    }

    private short sizeOfTile;

    public Map map;
    public PriorityQueue<Organism> listOfOrganisms;


    void draw(Graphics g)
    {
        for (Organism org: listOfOrganisms)
        {
            org.draw(g, new Dimension(sizeOfTile, sizeOfTile));
        }
    }

    public boolean addOrganism (Organism organism)
    {
        if (map.getOrganismFromTile(organism.getPosition()) == null)
        {
            map.setOrganismOnTile(organism.getPosition(), organism);
            this.listOfOrganisms.add(organism);
            return true;
        }
        else
        {
            return false;
        }
    }

    void nextTurn()
    {
        Object[] arr =  listOfOrganisms.toArray();
        for (int i = 0; i < arr.length; i++)
        {
            if(arr[i] instanceof Organism organism)
            {
                organism.action();
            }
        }
    }


    @Override
    public void paintComponent(Graphics g)
    {
        //pierwsze rysowanie
        super.paintComponent(g);
        draw(g);
    }

}
