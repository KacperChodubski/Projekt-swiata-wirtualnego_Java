package ProjectModel.Organisms.Plants;

import ProjectModel.Organisms.Animal;
import ProjectModel.Organisms.IToxic;
import ProjectModel.Organisms.Organism;
import ProjectModel.Organisms.Plant;
import ProjectModel.World;

import java.awt.*;
import java.util.ArrayList;

public class SosnowskiBorcht extends Plant implements IToxic
{
    private static final Color COLOR = Color.decode("#8f1106");
    private static final short KILLING_RANGE = 1;

    public SosnowskiBorcht(World world, Point position)
    {
        super(world, COLOR, position);
    }


    @Override
    public Organism cloning(Point position)
    {
        return new SosnowskiBorcht(this.world, position);
    }

    @Override
    public void action()
    {
        if (lifeTime > 0)
        {
            ArrayList<Organism> organismsToKill = this.holocaust();
            if (organismsToKill.size() > 0)
            {
                System.out.print("Barszcz zabija ");
                for (Organism organism : organismsToKill)
                {
                    organism.dying();
                    System.out.print(organism.getClass().getSimpleName() + " ");
                }
                System.out.println();
            }
        }
        this.lifeTime++;
    }

    private ArrayList<Organism> holocaust()
    {
        ArrayList<Organism> organismsToKill = new ArrayList<Organism>();
        for (int x = -KILLING_RANGE; x <= KILLING_RANGE; x++)
        {
            for (int y = -KILLING_RANGE; y <= KILLING_RANGE; y++)
            {
                Point tileToCheck = new Point(this.position.x + x, this.position.y + y);
                if (this.position.equals(tileToCheck))
                {
                    continue;
                }
                if (this.world.map.isInBoard(tileToCheck))
                {
                    Organism org = this.world.map.getOrganismFromTile(tileToCheck);
                    if (org instanceof Animal)
                    {
                        organismsToKill.add(this.world.map.getOrganismFromTile(tileToCheck));
                    }
                }
            }
        }
        return organismsToKill;
    }
}
