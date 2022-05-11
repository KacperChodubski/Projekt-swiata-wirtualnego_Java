package fajen;

import fajen.Organisms.Organism;

import java.awt.*;

public class Map
{
    private static final short DEFAULT_MAP_SIZE = 20;
    private Organism[][] mapOfOrganism;

    public short getSizeY()
    {
        return sizeY;
    }
    public short getSizeX()
    {
        return sizeX;
    }

    private short sizeY;
    private short sizeX;

    Map ()
    {
        this.mapOfOrganism = new Organism[DEFAULT_MAP_SIZE][DEFAULT_MAP_SIZE];
        sizeY = DEFAULT_MAP_SIZE;
        sizeX = DEFAULT_MAP_SIZE;
    }

    Map(short y, short x)
    {
        this.mapOfOrganism = new Organism[x][y];
        sizeX = x;
        sizeY = y;
    }

    public Organism getOrganismFromTile (Point position)
    {
        return mapOfOrganism[position.x][position.y];
    }
    public void setOrganismOnTile (Point position, Organism org)
    {
        mapOfOrganism[position.x][position.y] = org;
    }

    public void moveOrganism (Organism organism, Point newPosition)
    {
        if (mapOfOrganism[newPosition.x][newPosition.y] == null)
        {
            mapOfOrganism[newPosition.x][newPosition.y] = organism;
            Point organismPosition = organism.getPosition();
            mapOfOrganism[organismPosition.x][organismPosition.y] = null;
        }
    }

}
