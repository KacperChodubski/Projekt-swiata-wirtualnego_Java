package fajen.Organisms.Animals;

import fajen.Organisms.Animal;
import fajen.Organisms.IAllBurning;
import fajen.Organisms.Organism;
import fajen.World;

import java.awt.*;

public class Human extends Animal implements IAllBurning
{
    private static final int MAGIC_LEFT = 37;
    private static final int MAGIC_UP = 38;
    private static final int MAGIC_RIGHT = 39;
    private static final int MAGIC_DOWN = 40;
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
    protected Point positionAfterMovement()
    {
        Point nextPosition = new Point(this.position);
        if(world.getPressedKey().getKeyCode() == MAGIC_LEFT)
        {
            nextPosition.x -= this.movementRange;
        }
        if(world.getPressedKey().getKeyCode() == MAGIC_UP)
        {
            nextPosition.y -= this.movementRange;
        }
        if(world.getPressedKey().getKeyCode() == MAGIC_RIGHT)
        {
            nextPosition.x += this.movementRange;
        }
        if(world.getPressedKey().getKeyCode() == MAGIC_DOWN)
        {
            nextPosition.y += this.movementRange;
        }
        if (this.movementValidation(nextPosition))
        {
            return nextPosition;
        }
        return null;

    }

    @Override
    public void burningLands(int range)
    {
        if (abilityDuration > 0)
        {
            for (int x = -range; x <= range; x++)
            {
                for (int y = -range; y <= range; y++)
                {
                    if (this.position.equals(new Point(x, y)))
                    {
                        continue;
                    }
                    this.world.map.setOrganismOnTile(new Point(x, y), null);
                }
            }
        }
    }
}
