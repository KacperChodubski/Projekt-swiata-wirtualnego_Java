package fajen.Organisms;

import fajen.World;

import java.awt.*;

public class Ship extends Animal
{
    private static final short SHEEP_STRENGTH = 4;
    private static final short SHEEP_DEXTERITY = 4;
    public Ship(World world, Point position)
    {
        this.dexterity = SHEEP_DEXTERITY;
        this.strength = SHEEP_STRENGTH;
        this.colorOfTile = Color.GRAY;
        this.world = world;
        this.position = position;
    }

    @Override
    protected Organism cloning(Point position)
    {
        return new Ship(this.world, position);
    }

}
