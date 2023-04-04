package ProjectModel.Organisms;

import ProjectModel.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

abstract public class Plant extends Organism
{
    private static final short BASIC_DEXTERITY = 0;
    private static final short BASIC_STRENGTH = 0;
    private static final short BASIC_SPREAD_CHANCE = 10;
    private static final short BASIC_SPREAD_RANGE = 1;

    protected Plant(World world, Color color, Point position)
    {
        super(world, BASIC_STRENGTH, BASIC_DEXTERITY, color, position);
    }

    @Override
    public void action()
    {
        if (lifeTime > 0)
        {
            if (this instanceof IMad)
            {
                for (int i = 0; i < ((IMad) this).getLevelOfMadness(); i++)
                {
                    this.spread();
                }
            }
            else
            {
                this.spread();
            }
        }
        this.lifeTime++;
    }

    private void spread ()
    {
        Random r = new Random();
        if (r.nextInt(100) < BASIC_SPREAD_CHANCE)
        {
            ArrayList<Point> availableFields = this.gettingAvailableFields(BASIC_SPREAD_RANGE);
            if (availableFields.size() > 0)
            {
                world.addOrganism(this.cloning(availableFields.get( r.nextInt( availableFields.size() ) ) ) );
            }
        }
    }


}
