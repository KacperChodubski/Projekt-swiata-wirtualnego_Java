package ProjectModel.Organisms.Animals;

import ProjectModel.Organisms.Animal;
import ProjectModel.Organisms.Organism;
import ProjectModel.World;

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
    public Organism cloning(Point position)
    {
        return new Ship(this.world, position);
    }

}
