package ProjectModel.Organisms.Plants;

import ProjectModel.Organisms.IMad;
import ProjectModel.Organisms.Organism;
import ProjectModel.Organisms.Plant;
import ProjectModel.World;

import java.awt.*;

public class Milt extends Plant implements IMad
{
    private static final Color COLOR = Color.yellow;
    private static final int NUMBER_OF_SPREADING = 3;

    public Milt(World world, Point position)
    {
        super(world, COLOR, position);
    }


    @Override
    public Organism cloning(Point position)
    {
        return new Milt(this.world, position);
    }

    @Override
    public int getLevelOfMadness()
    {
        return NUMBER_OF_SPREADING;
    }
}
