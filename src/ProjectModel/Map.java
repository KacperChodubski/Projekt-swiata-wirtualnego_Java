package ProjectModel;

import ProjectModel.Organisms.Organism;

import java.awt.*;

public class Map
{
    private static final short DEFAULT_MAP_SIZE = 20;
    private Organism[][] mapOfOrganism;

    public int getSizeY()
    {
        return sizeY;
    }
    public int getSizeX()
    {
        return sizeX;
    }

    private int sizeY;
    private int sizeX;

    Map ()
    {
        this.mapOfOrganism = new Organism[DEFAULT_MAP_SIZE][DEFAULT_MAP_SIZE];
        sizeY = DEFAULT_MAP_SIZE;
        sizeX = DEFAULT_MAP_SIZE;
    }

    Map(int x, int y)
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

    public boolean isInBoard (Point p)
    {
        return p.x < sizeX && p.x >= 0 && p.y < sizeY && p.y >= 0;
    }

}
