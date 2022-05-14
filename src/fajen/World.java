package fajen;

import fajen.Organisms.Animals.*;
import fajen.Organisms.Organism;
import fajen.Organisms.Plants.*;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class World extends JPanel
{

    private static final int MAX_RANDOM_ANIMALS = 10;

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
        createRandomWorld();
    }

    public World(short x, short y)
    {
        listOfOrganisms = new PriorityQueue<Organism>();
        map = new Map(x, y);
        createRandomWorld();
    }

    public Dimension getSizeOfTile()
    {
        return sizeOfTile;
    }

    public void setSizeOfTile(Dimension sizeOfTile)
    {
        this.sizeOfTile = sizeOfTile;
    }

    private Dimension sizeOfTile;

    public Map map;
    public PriorityQueue<Organism> listOfOrganisms;



    void draw(Graphics g)
    {
        for (Organism org: listOfOrganisms)
        {
            org.draw(sizeOfTile, g);
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
        listOfAllOrganisms.add(new Ship(this, new Point(0,0)));
        listOfAllOrganisms.add(new Wolf(this, new Point(0,0)));
        listOfAllOrganisms.add(new Fox(this, new Point(0,0)));
        listOfAllOrganisms.add(new Tortoise(this, new Point(0,0)));
        listOfAllOrganisms.add(new Grass(this, new Point(0,0)));
        listOfAllOrganisms.add(new Guarana(this, new Point(0,0)));
        listOfAllOrganisms.add(new Milt(this, new Point(0,0)));
        listOfAllOrganisms.add(new SosnowskiBorcht(this, new Point(0,0)));
        //listOfAllOrganisms.add(new WolfBerry(this, new Point(0,0)));

        for (int i = 0; i < listOfAllOrganisms.size(); i++)
        {
            Random r = new Random();
            int numberOfAnimalsOfOneType = r.nextInt(MAX_RANDOM_ANIMALS + 1);
            for (int j = 0; j < numberOfAnimalsOfOneType; j++)
            {
                short randX = (short) (r.nextInt(this.map.getSizeX()));
                short randY = (short) (r.nextInt(this.map.getSizeY()));
                Point randPosition = new Point(randX, randY);
                addOrganism(listOfAllOrganisms.get(i).cloning(randPosition));
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
