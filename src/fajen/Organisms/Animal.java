package fajen.Organisms;

import java.awt.*;

abstract public class Animal extends Organism
{
    private static final short BASIC_MOVEMENT_RANGE = 1;

    protected short movementRange = BASIC_MOVEMENT_RANGE;

    @Override
    public void action()
    {
        this.horny = true;
        this.lifeTime++;
        this.movement();
    }

    private void movement()
    {
        Point nextPosition = this.positionAfterMovement();
        if (this.world.map.getOrganismFromTile(nextPosition) != null)
        {

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
            short direction = (short)(Math.random() * 7);
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
