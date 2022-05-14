package fajen.Organisms.Animals;

import fajen.Organisms.Animal;
import fajen.Organisms.IAllBurning;
import fajen.Organisms.Organism;
import fajen.World;

import java.awt.*;

public class Human extends Animal implements IAllBurning
{
    private static final short STRENGTH = 5;
    private static final short DEXTERITY = 5;
    private static final short BASIC_ABILITY_DURATION = 5;
    private static final Color COLOR = Color.blue;

    public Human(World world, Point position)
    {
        super(world, STRENGTH, DEXTERITY, COLOR, position);
    }
    private short abilityDuration = 0;

    @Override
    public Organism cloning(Point position)
    {
        return new Human(this.world, position);
    }

    @Override
    public void action ()
    {

    }

    @Override
    protected void movement(){}

    @Override
    public void burningLands(int range)
    {
        for (int x = -range; x <= range; x++)
        {
            for (int y = -range; y <= range; y++)
            {
                if (this.position.equals(new Point(x, y)))
                {
                    continue;
                }
                this.world.map.setOrganismOnTile(new Point(x,y), null);
            }
        }
    }
}
