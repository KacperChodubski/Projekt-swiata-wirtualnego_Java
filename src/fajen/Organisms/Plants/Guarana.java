package fajen.Organisms.Plants;

import fajen.Organisms.ISteroid;
import fajen.Organisms.Organism;
import fajen.Organisms.Plant;
import fajen.World;

import java.awt.*;

public class Guarana extends Plant implements ISteroid
{
    private static final Color COLOR = Color.red;
    private static final short STRENGTH_BOOST = 3;

    public Guarana(World world, Point position)
    {
        super(world, COLOR, position);
    }


    @Override
    protected Organism cloning(Point position)
    {
        return new Guarana(this.world, position);
    }

    @Override
    public void boost(Organism org)
    {
        org.setStrength((short) (org.getStrength() + STRENGTH_BOOST));
    }
}
