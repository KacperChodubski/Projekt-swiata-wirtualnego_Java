package fajen.Organisms;

import fajen.booleanPoint;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

abstract public class Animal extends Organism
{
    private static final short BASIC_MOVEMENT_RANGE = 1;
    private static final short BASIC_REPRODUCE_RANGE = 1;

    protected short movementRange = BASIC_MOVEMENT_RANGE;

    Animal(short strength, short dexterity, Color color, Point position)
    {
        super(strength, dexterity, color, position);
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

    private void movement()
    {
        Point nextPosition = this.positionAfterMovement();
        if (this.world.map.getOrganismFromTile(nextPosition) != null)
        {
            Organism orgOnPosToMove = this.world.map.getOrganismFromTile(nextPosition);
            collision(orgOnPosToMove);
        }
        else
        {
            this.moveOnPosition(nextPosition);
        }

    }

    private Point positionAfterMovement()
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
        ArrayList<Point> availableFields = gettingAvailableFieldsToBirth();
        if (availableFields.size() == 0)
        {
            return null;
        }
        Random r = new Random();
        int randomPoint = r.nextInt(availableFields.size());
        return availableFields.get(randomPoint);
    }

    private ArrayList<Point> gettingAvailableFieldsToBirth ()
    {
        ArrayList<Point> availableFields = new ArrayList<Point>();

        for (int x = -BASIC_REPRODUCE_RANGE; x <= BASIC_REPRODUCE_RANGE; x++)
        {
            for (int y = -BASIC_REPRODUCE_RANGE; y <= BASIC_REPRODUCE_RANGE; y++)
            {
                Point tileToBeChecked = new Point(this.position.x + x, this.position.y + y);
                if (world.map.isInBoard(tileToBeChecked) && world.map.getOrganismFromTile(tileToBeChecked) == null)
                {
                    availableFields.add(tileToBeChecked);
                }
            }
        }
        return availableFields;
    }

    private boolean movementValidation(Point position)
    {

        if (!this.world.map.isInBoard(position))
        {
            return false;
        }

        return true;
    }


    @Override
    void collision(Organism organism)
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



    @Override
    boolean attack(Organism defendingOrganism)
    {
        if (this.strength >= defendingOrganism.getStrength())
        {
            return true;
        }
        else
        {
            this.dying();
            return false;
        }
    }

    @Override
    boolean defence(Organism attackingOrganism)
    {
        if (this.strength < attackingOrganism.getStrength())
        {
            this.dying();
            return false;
        }
        else
        {
            return true;
        }
    }

}
