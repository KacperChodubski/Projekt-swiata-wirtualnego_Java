package ProjectModel.Organisms.Plants;

import ProjectModel.Organisms.Organism;
import ProjectModel.Organisms.Plant;
import ProjectModel.World;

import java.awt.*;

public class Grass extends Plant
{
    private static final Color COLOR = Color.green;

    public Grass(World world, Point position)
    {
        super(world, COLOR, position);
    }


    @Override
    public Organism cloning(Point position)
    {
        return new Grass(this.world, position);
    }
}
