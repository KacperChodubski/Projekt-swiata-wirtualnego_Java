package fajen;

import fajen.Organisms.Animals.*;
import fajen.Organisms.Organism;
import fajen.Organisms.Plants.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;


public class World extends JPanel
{

    private static final int MAX_RANDOM_ANIMALS = 10;
    public ArrayList<Organism> listOfAllOrganisms = new ArrayList<>();

    public Dimension getWorldDimension()
    {
        return worldDimension;
    }

    public void setWorldDimension(Dimension dimension)
    {
        this.worldDimension.height = dimension.height - 2 * MARGIN;
        this.worldDimension.width = dimension.width - 2 * MARGIN;
        int sizeOfTileY = this.getWorldDimension().height / this.map.getSizeY();
        int sizeOfTileX = this.getWorldDimension().width / this.map.getSizeX();
        this.setSizeOfTile(new Dimension(sizeOfTileX, sizeOfTileY));
    }

    private Dimension worldDimension = new Dimension();

    public KeyEvent getPressedKey()
    {
        return pressedKey;
    }

    public void setPressedKey(KeyEvent pressedKey)
    {
        this.pressedKey = pressedKey;
    }

    private KeyEvent pressedKey = null;
    public short MARGIN = 100;

    public World()
    {
        listOfOrganisms = new PriorityQueue<>();
        addingToListOfAllOrg();
        map = new Map();
        createRandomWorld();
    }

    public World(int x, int y)
    {
        listOfOrganisms = new PriorityQueue<>();
        addingToListOfAllOrg();
        map = new Map(x, y);
        createRandomWorld();
    }

    private void addingToListOfAllOrg ()
    {
        listOfAllOrganisms.add(new Bubal(this, new Point(0,0)));
        listOfAllOrganisms.add(new Ship(this, new Point(0,0)));
        listOfAllOrganisms.add(new Wolf(this, new Point(0,0)));
        listOfAllOrganisms.add(new Fox(this, new Point(0,0)));
        listOfAllOrganisms.add(new Tortoise(this, new Point(0,0)));
        listOfAllOrganisms.add(new Grass(this, new Point(0,0)));
        listOfAllOrganisms.add(new Guarana(this, new Point(0,0)));
        listOfAllOrganisms.add(new Milt(this, new Point(0,0)));
        listOfAllOrganisms.add(new SosnowskiBorcht(this, new Point(0,0)));
        listOfAllOrganisms.add(new WolfBerry(this, new Point(0,0)));
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
        this.repaint();
        for (Organism org: listOfOrganisms)
        {
            org.draw(sizeOfTile, g);
        }
    }

    public boolean addOrganism (Organism organism)
    {
        if (organism != null && map.isInBoard(organism.getPosition()) && map.getOrganismFromTile(organism.getPosition()) == null)
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
        PriorityQueue<Organism> arr =  new PriorityQueue<>(listOfOrganisms);
        Organism org = arr.poll();
        while (org != null)
        {
            if (org.isAlive())
            {
                org.action();
            }
            else
            {
                listOfOrganisms.remove(org);
            }
            org = arr.poll();
        }
    }

    public void createRandomWorld()
    {

        this.listOfOrganisms.clear();
        Random r = new Random();
        for (Organism listOfAllOrganism : listOfAllOrganisms)
        {
            int numberOfAnimalsOfOneType = r.nextInt(MAX_RANDOM_ANIMALS + 1);
            for (int j = 0; j < numberOfAnimalsOfOneType; j++)
            {
                short randX = (short) (r.nextInt(this.map.getSizeX()));
                short randY = (short) (r.nextInt(this.map.getSizeY()));
                Point randPosition = new Point(randX, randY);
                addOrganism(listOfAllOrganism.cloning(randPosition));
            }
        }
        short randX = (short) (r.nextInt(this.map.getSizeX()));
        short randY = (short) (r.nextInt(this.map.getSizeY()));
        Point randPosition = new Point(randX, randY);
        this.addOrganism(new Human(this, randPosition));

    }


    public void save ()
    {
        try
        {
            FileWriter fileWriter = new FileWriter("saves/save.txt");
            fileWriter.write(this.map.getSizeX() + " " + this.map.getSizeY() + "\n");
            for (Organism organism: this.listOfOrganisms)
            {
                fileWriter.write(organism.code() + "\n");
            }
            fileWriter.close();

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void resizeWorld (int w, int h)
    {
        map = new Map(w, h);
        int sizeOfTileY = this.getWorldDimension().height / this.map.getSizeY();
        int sizeOfTileX = this.getWorldDimension().width / this.map.getSizeX();
        this.setSizeOfTile(new Dimension(sizeOfTileX, sizeOfTileY));
    }

    public void readSave ()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("saves/save.txt"));
            String mapString = reader.readLine();
            String[] mapParameters = mapString.split(" ");
            int mapX = Integer.parseInt(mapParameters[0]);
            int mapY = Integer.parseInt(mapParameters[1]);
            this.resizeWorld(mapX, mapY);
            String orgString = reader.readLine();
            this.listOfOrganisms = new PriorityQueue<>();
            while (orgString != null)
            {
                String orgName = orgString.split(" ")[0];
                if (orgName.equals("Human"))
                {
                    Organism orgToAdd = new Human(this, new Point());
                    orgToAdd.decode(orgString);
                    this.addOrganism(orgToAdd);
                }
                for (Organism org: listOfAllOrganisms)
                {
                    if (org.getClass().getSimpleName().equals(orgName))
                    {
                        Organism orgToAdd = org.cloning(new Point());
                        orgToAdd.decode(orgString);
                        this.addOrganism(orgToAdd);
                    }
                }
                orgString = reader.readLine();
            }

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
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
