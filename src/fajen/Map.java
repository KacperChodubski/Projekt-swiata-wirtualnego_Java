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
    }

    Map(short y, short x)
    {
        this.mapOfOrganism = new Organism[y][x];
        sizeX = x;
        sizeY = y;
    }

    public void setOrganismOnTile (short y, short x, Organism org)
    {
        this.mapOfOrganism[y][x] = org;
    }

    public void moveOrganism (Organism organism, Point newPosition)
    {
        if (mapOfOrganism[newPosition.y][newPosition.x] == null)
        {
            mapOfOrganism[newPosition.y][newPosition.x] = organism;
            Point organismPosition = organism.getPosition();
            mapOfOrganism[organismPosition.y][organismPosition.x] = null;
        }
    }

}
