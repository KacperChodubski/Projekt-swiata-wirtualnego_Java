package fajen;

import fajen.Organisms.Animals.Bubal;
import fajen.Organisms.Organism;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class World extends JPanel
{

    public Dimension getWorldDimension()
    {
        return worldDimension;
    }

    public void setWorldDimension(Dimension dimension)
    {
        this.worldDimension.height = dimension.height - 2 * MARGIN;
        this.worldDimension.width = dimension.width - 2 * MARGIN;
    }

    private Dimension worldDimension = new Dimension();
    public short MARGIN = 50;

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
            org.draw(new Dimension(sizeOfTile, sizeOfTile), g);
        }
    }

    public boolean addOrganism (Organism organism)
    {
        if (map.isInBoard(organism.getPosition()) && map.getOrganismFromTile(organism.getPosition()) == null)
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
                if (organism.isAlive())
                {
                    organism.action();
                }
            }
        }
    }

    private void createRandomWorld()
    {
        ArrayList<Organism> listOfAllOrganisms = new ArrayList<Organism>();
        listOfAllOrganisms.add(new Bubal(this, new Point(0,0)));

    }


    @Override
    public void paintComponent(Graphics g)
    {
        //pierwsze rysowanie
        super.paintComponent(g);
        draw(g);
    }

}
