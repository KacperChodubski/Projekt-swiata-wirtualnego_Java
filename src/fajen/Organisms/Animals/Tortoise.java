package fajen.Organisms.Animals;

import fajen.Organisms.Animal;
import fajen.Organisms.IShelly;
import fajen.Organisms.Organism;
import fajen.World;

import java.awt.*;

public class Tortoise extends Animal implements IShelly
{
    private static final short STRENGTH = 2;
    private static final short DEXTERITY = 1;
    private static final Color COLOR = Color.decode("#00470d");
    private static final int DEFLECT_VALUE = 5;

    public Tortoise(World world, Point position)
    {
        super(world, STRENGTH, DEXTERITY, COLOR, position);
    }

    @Override
    public Organism cloning(Point position)
    {
        return new Tortoise(this.world, position);
    }

    @Override
    public boolean deflect(Organism attacker)
    {
        if (attacker.getStrength() < DEFLECT_VALUE)
        {
            return true;
        }
        return false;
    }
}
