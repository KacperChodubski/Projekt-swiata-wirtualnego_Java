package ProjectModel.Organisms.Animals;

import ProjectModel.Organisms.Animal;
import ProjectModel.Organisms.IAllBurning;
import ProjectModel.Organisms.Organism;
import ProjectModel.World;

import java.awt.*;

public class Human extends Animal implements IAllBurning
{
    private static final int MAGIC_LEFT = 37;
    private static final int MAGIC_UP = 38;
    private static final int MAGIC_RIGHT = 39;
    private static final int MAGIC_DOWN = 40;
    private static final int MAGIC_ABILITY = 85;
    private static final short STRENGTH = 5;
    private static final short DEXTERITY = 5;
    private static final short BASIC_ABILITY_DURATION = 5;
    private static final int ABILITY_RANGE = 1;
    private static final Color COLOR = Color.blue;

    public Human(World world, Point position)
    {
        super(world, STRENGTH, DEXTERITY, COLOR, position);
    }
    private int abilityDuration = 0;

    @Override
    public Organism cloning(Point position)
    {
        return null;
    }

    @Override
    public void action()
    {
        if (lifeTime > 0)
        {
            if (this.world.getPressedKey() == null)
            {

            }
            else if (this.world.getPressedKey().getKeyCode() == MAGIC_ABILITY)
            {
                this.abilityDuration = BASIC_ABILITY_DURATION;
            }
            else
            {
                this.movement();
            }
            if (this instanceof IAllBurning)
            {
                this.burningLands(ABILITY_RANGE);
            }
        }
        this.lifeTime++;
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
    public void decode(String orgString)
    {
        String [] parameters = orgString.split(" ");
        this.setPosition(new Point(Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2])));
        this.setStrength(Integer.parseInt(parameters[3]));
        this.setLifeTime(Integer.parseInt(parameters[4]));
        this.abilityDuration = Integer.parseInt(parameters[5]);
    }

    @Override
    public String code ()
    {
        String encodedOrg;

        encodedOrg = this.getClass().getSimpleName() + " ";
        encodedOrg += this.position.x + " ";
        encodedOrg += this.position.y + " ";
        encodedOrg += this.strength + " ";
        encodedOrg += this.lifeTime + " ";
        encodedOrg += this.abilityDuration;

        return encodedOrg;
    }
    @Override
    public void burningLands(int range)
    {
        if (abilityDuration > 0)
        {
            abilityDuration--;
            for (int x = -range; x <= range; x++)
            {
                for (int y = -range; y <= range; y++)
                {
                    Point posOfDestruction = new Point(this.position);
                    posOfDestruction.x += x;
                    posOfDestruction.y += y;
                    if (this.position.equals(posOfDestruction) || !this.world.map.isInBoard(posOfDestruction))
                    {
                        continue;
                    }
                    Organism deadOrganism =  this.world.map.getOrganismFromTile(posOfDestruction);
                    if (deadOrganism != null)
                    {
                        System.out.println(deadOrganism.getClass().getSimpleName() + " zostal spalony zywcem");
                        deadOrganism.dying();
                    }
                }
            }
        }
    }
}
