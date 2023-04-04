package ProjectModel.Organisms.Animals;

import ProjectModel.Organisms.Animal;
import ProjectModel.Organisms.Organism;
import ProjectModel.World;

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
    public Organism cloning(Point position)
    {
        return new Wolf(this.world, position);
    }
}
