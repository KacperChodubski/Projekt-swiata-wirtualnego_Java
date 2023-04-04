package ProjectModel.Organisms.Plants;

import ProjectModel.Organisms.IToxic;
import ProjectModel.Organisms.Organism;
import ProjectModel.Organisms.Plant;
import ProjectModel.World;

import java.awt.*;

public class WolfBerry extends Plant implements IToxic
{
    private static final Color COLOR = Color.decode("#61068f");
    private static final short STRENGTH = 99;

    public WolfBerry(World world, Point position)
    {
        super(world, COLOR, position);
        this.strength = STRENGTH;
    }


    @Override
    public Organism cloning(Point position)
    {
        return new WolfBerry(this.world, position);
    }
}
