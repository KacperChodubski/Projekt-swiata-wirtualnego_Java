package fajen.Organisms;

import fajen.Organisms.Organism;
import fajen.World;
import fajen.booleanPoint;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

abstract public class Animal extends Organism
{
    private static final short BASIC_MOVEMENT_RANGE = 1;
    private static final short BASIC_REPRODUCE_RANGE = 1;

    protected short movementRange = BASIC_MOVEMENT_RANGE;

    protected Animal(World world, short strength, short dexterity, Color color, Point position)
    {
        super(world, strength, dexterity, color, position);
    }

    @Override
    public void action()
    {
        this.horny = true;
        if (lifeTime > 0)
        {
            this.movement();
        }
        this.lifeTime++;
    }

    protected void movement()
    {
        Point nextPosition = this.positionAfterMovement();
        if (nextPosition != null)
        {
            if (this.world.map.getOrganismFromTile(nextPosition) != null)
            {
                Organism orgOnPosToMove = this.world.map.getOrganismFromTile(nextPosition);
                collision(orgOnPosToMove);
                if (this.world.map.getOrganismFromTile(nextPosition) == null)
                {
                    this.moveOnPosition(nextPosition);
                }
            } else
            {
                this.moveOnPosition(nextPosition);
            }
        }

    }

    protected Point positionAfterMovement()
    {
        Point nextPosition;
        ArrayList<Point> availableTiles =  new ArrayList<Point>();
        nextPosition = new Point(position);
        Random r = new Random();
        for (int x = -movementRange; x <= movementRange; x++)
        {
            for (int y = -movementRange; y <= movementRange; y++)
            {
                Point tileToBeChecked = new Point(this.position.x + x, this.position.y + y);

                if (movementValidation(tileToBeChecked))
                {
                    availableTiles.add(tileToBeChecked);
                }
            }
        }

        if (availableTiles.size() == 0)
        {
            return null;
        }

        nextPosition = availableTiles.get(r.nextInt(availableTiles.size()));

        return nextPosition;
    }

    private void reproduce (Organism otherOrganism)
    {
        if (otherOrganism.horny && this.horny)
        {
            this.horny = false;
            otherOrganism.horny = false;
            Point positionOfBirth = selectingPositionOfBirth();
            if (positionOfBirth != null)
            {
                Organism newOrg = this.cloning(positionOfBirth);
                world.addOrganism(newOrg);
            }

        }
    }

    private Point selectingPositionOfBirth()
    {
        ArrayList<Point> availableFields = gettingAvailableFields(BASIC_REPRODUCE_RANGE);
        if (availableFields.size() == 0)
        {
            return null;
        }
        Random r = new Random();
        int randomPoint = r.nextInt(availableFields.size());
        return availableFields.get(randomPoint);
    }

    protected boolean movementValidation(Point position)
    {
        if (!this.world.map.isInBoard(position))
        {
            return false;
        }
        if(this.position.equals(position))
        {
            return false;
        }
        if (this instanceof ISmart && !((ISmart) this).isThisSmartMove(position))
        {
            return false;
        }

        return true;
    }


    private void collision(Organism organism)
    {
        if (this.getClass().isInstance(organism))
        {
            reproduce(organism);
        }
        else
        {
            fight(this, organism);
        }
    }


}
