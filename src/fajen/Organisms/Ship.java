package fajen.Organisms;

import fajen.World;

import java.awt.*;

public class Ship extends Animal
{
    private static final short SHEEP_STRENGTH = 4;
    private static final short SHEEP_DEXTERITY = 4;
    public Ship(World world, Point position)
    {
        this.dexterity = SHEEP_DEXTERITY;
        this.strength = SHEEP_STRENGTH;
        this.colorOfTile = Color.GRAY;
        this.world = world;
        this.position = position;
        world.listOfOrganisms.add(this);
        world.map.setOrganismOnTile(position, this); // do wyjebania bo bedzie zle
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
