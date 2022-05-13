package fajen.Organisms.Animals;

import fajen.Organisms.Animal;
import fajen.Organisms.Organism;
import fajen.World;

import java.awt.*;

public class Wolf extends Animal
{

    private static final short STRENGTH = 9;
    private static final short DEXTERITY = 5;
    private static final Color COLOR = Color.BLACK;

    public Wolf(World world, Point position)
    {
        super(world, STRENGTH, DEXTERITY, COLOR, position);
    }

    @Override
    protected Organism cloning(Point position)
    {
        return new Wolf(this.world, position);
    }
}
