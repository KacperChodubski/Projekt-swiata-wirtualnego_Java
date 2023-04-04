package ProjectModel.Organisms.Plants;

import ProjectModel.Organisms.ISteroid;
import ProjectModel.Organisms.Organism;
import ProjectModel.Organisms.Plant;
import ProjectModel.World;

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
    public Organism cloning(Point position)
    {
        return new Guarana(this.world, position);
    }

    @Override
    public void boost(Organism org)
    {
        org.setStrength((short) (org.getStrength() + STRENGTH_BOOST));
    }
}
