package ProjectModel.Organisms.Animals;

import ProjectModel.Organisms.Animal;
import ProjectModel.Organisms.IJumper;
import ProjectModel.Organisms.Organism;
import ProjectModel.World;

import java.awt.*;
import java.util.Random;

public class Bubal extends Animal implements IJumper
{
    private static final int CHANCE_OF_ESCAPING = 50;
    private static final short STRENGTH = 4;
    private static final short DEXTERITY = 4;
    private static final short MOVEMENT_RANGE = 2;
    private static final Color COLOR = Color.decode("#4D0000");

    public Bubal(World world, Point position)
    {
        super(world, STRENGTH, DEXTERITY, COLOR, position);
        this.movementRange = MOVEMENT_RANGE;
    }

    @Override
    public Organism cloning(Point position)
    {
        return new Bubal(this.world, position);
    }

    @Override
    public boolean escape()
    {
        Random r = new Random();
        return r.nextInt(100) < CHANCE_OF_ESCAPING;
    }
}
