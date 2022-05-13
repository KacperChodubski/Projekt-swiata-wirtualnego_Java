package fajen.Organisms.Animals;

import fajen.Organisms.Animal;
import fajen.Organisms.Organism;
import fajen.World;

import java.awt.*;

public class Ship extends Animal
{
    private static final short STRENGTH = 4;
    private static final short DEXTERITY = 4;
    private static final Color COLOR = Color.GRAY;

    public Ship(World world, Point position)
    {
        super(world, STRENGTH, DEXTERITY, COLOR, position);
    }

    @Override
    protected Organism cloning(Point position)
    {
        return new Ship(this.world, position);
    }

}
