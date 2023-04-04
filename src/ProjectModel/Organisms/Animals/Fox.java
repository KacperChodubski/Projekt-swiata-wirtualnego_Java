package ProjectModel.Organisms.Animals;

import ProjectModel.Organisms.Animal;
import ProjectModel.Organisms.ISmart;
import ProjectModel.Organisms.Organism;
import ProjectModel.World;

import java.awt.*;

public class Fox extends Animal implements ISmart
{
    private static final short STRENGTH = 3;
    private static final short DEXTERITY = 7;
    private static final Color COLOR = Color.decode("#fc7703");

    public Fox(World world, Point position)
    {
        super(world, STRENGTH, DEXTERITY, COLOR, position);
    }

    @Override
    public Organism cloning(Point position)
    {
        return new Fox(this.world, position);
    }


    @Override
    public boolean isThisSmartMove(Point nextPosition)
    {
        Organism org = this.world.map.getOrganismFromTile(nextPosition);
        if (org != null && org.getStrength() > this.getStrength())
        {
            return false;
        }
        return true;
    }
}
