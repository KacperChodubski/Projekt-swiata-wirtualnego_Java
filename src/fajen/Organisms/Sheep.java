package fajen.Organisms;

import fajen.World;

import java.awt.*;

public class Sheep extends Animal
{
    public Sheep(World world, Point position)
    {
        this.colorOfTile = Color.GRAY;
        this.world = world;
        this.position = position;
        world.listOfOrganisms.add(this);
    }

    @Override
    void action()
    {

    }

    @Override
    void collision(Organism organism)
    {

    }

    @Override
    boolean attack(Organism organism)
    {
        return false;
    }

    @Override
    boolean defence(Organism organism)
    {
        return false;
    }
}
