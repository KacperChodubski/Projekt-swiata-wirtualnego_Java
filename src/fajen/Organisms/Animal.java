package fajen.Organisms;

import fajen.booleanPoint;

import java.awt.*;
import java.util.Random;

abstract public class Animal extends Organism
{
    private static final short BASIC_MOVEMENT_RANGE = 1;
    private static final short BASIC_REPRODUCE_RANGE = 1;

    protected short movementRange = BASIC_MOVEMENT_RANGE;

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
            if (this.getClass().isInstance(orgOnPosToMove))
            {
                reproduce(orgOnPosToMove);
            }
            else
            {

            }
        }
        else
        {
            this.world.map.moveOrganism(this, nextPosition);
            if (nextPosition.x > world.map.getSizeX() || nextPosition.y > world.map.getSizeY())
            {
                System.out.println("chuj");
            }
            this.position = nextPosition;
        }

    }



    private Point positionAfterMovement()
    {
        Point nextPosition;
        do
        {
            nextPosition = new Point(position);
            Random r = new Random();
            short direction = (short)(r.nextInt(8));
            switch (direction)
            {
                case N -> nextPosition.y -= this.movementRange;
                case NE ->
                {
                    nextPosition.y -= this.movementRange;
                    nextPosition.x += this.movementRange;
                }
                case E -> nextPosition.x += this.movementRange;
                case SE ->
                {
                    nextPosition.y += this.movementRange;
                    nextPosition.x += this.movementRange;
                }
                case S -> nextPosition.y += this.movementRange;
                case SW ->
                {
                    nextPosition.y += this.movementRange;
                    nextPosition.x -= this.movementRange;
                }
                case W -> nextPosition.x -= this.movementRange;
                case NW ->
                {
                    nextPosition.y -= this.movementRange;
                    nextPosition.x -= this.movementRange;
                }
                default -> System.err.println("Nieznany kierunek");
            }
        } while (!this.movementValidation(nextPosition));
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
        booleanPoint[][] availableFields = gettingAvailableFieldsToBirth();
        if (availableFields == null)
        {
            return null;
        }
        Random r = new Random();
        int randX;
        int randY;

        do
        {
            randX = r.nextInt(availableFields.length);
            randY = r.nextInt(availableFields[randX].length);
        }while (!availableFields[randX][randY].bool);
        return availableFields[randX][randY].point;
    }

    private booleanPoint[][] gettingAvailableFieldsToBirth ()
    {
        booleanPoint[][] availableFields = new booleanPoint[BASIC_REPRODUCE_RANGE*2 + 1][BASIC_REPRODUCE_RANGE*2 + 1];
        boolean atleastOneAvailableField = false;
        int pos_x = this.position.x;
        int pos_y = this.position.y;
        for (int x = 0; x < availableFields.length; x++)
        {
            for (int y = 0; y < availableFields.length; y++)
            {
                availableFields[x][y] = new booleanPoint();
                availableFields[x][y].point.x = this.position.x - BASIC_REPRODUCE_RANGE + x;
                availableFields[x][y].point.y = this.position.y - BASIC_REPRODUCE_RANGE + y;
                boolean xInBorad = availableFields[x][y].point.x < this.world.map.getSizeX() && availableFields[x][y].point.x >= 0;
                boolean yInBorad = availableFields[x][y].point.y < this.world.map.getSizeY() && availableFields[x][y].point.y >= 0;
                if (xInBorad && yInBorad && this.world.map.getOrganismFromTile(availableFields[x][y].point) == null )
                {
                    availableFields[x][y].bool = true;
                    atleastOneAvailableField = true;
                }
                else
                {
                    availableFields[x][y].bool = false;
                }
            }
        }
        if (atleastOneAvailableField)
        {
            return availableFields;
        }
        return null;
    }

    private boolean movementValidation(Point position)
    {
        if(position.x >= 0 && position.y >= 0)
        {
            if(position.x < this.world.map.getSizeX() && position.y < this.world.map.getSizeY())
            {
                return true;
            }
        }
        return false;
    }

}
