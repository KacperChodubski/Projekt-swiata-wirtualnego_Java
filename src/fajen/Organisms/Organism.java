package fajen.Organisms;

import fajen.World;

import java.awt.*;
import java.util.ArrayList;

abstract public class Organism implements Comparable<Organism>
{

    public Color getColorOfTile()
    {
        return colorOfTile;
    }

    public void setColorOfTile(Color colorOfTile)
    {
        this.colorOfTile = colorOfTile;
    }

    public short getStrength()
    {
        return strength;
    }

    public void setStrength(short strength)
    {
        this.strength = strength;
    }

    public short getDexterity()
    {
        return dexterity;
    }

    public void setDexterity(short dexterity)
    {
        this.dexterity = dexterity;
    }

    public short getLifeTime()
    {
        return lifeTime;
    }

    public void setLifeTime(short lifeTime)
    {
        this.lifeTime = lifeTime;
    }

    public boolean isHorny()
    {
        return horny;
    }

    public void setHorny(boolean horny)
    {
        this.horny = horny;
    }

    public boolean isAlive()
    {
        return alive;
    }

    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }

    public Point getPosition()
    {
        return position;
    }

    public void setPosition(Point position)
    {
        this.position = position;
    }

    protected Organism(World world, short strength, short dexterity, Color color, Point position)
    {
        this.world = world;
        this.strength = strength;
        this.dexterity = dexterity;
        this.position = position;
        this.colorOfTile = color;
    }

    //Stats
    protected short strength;
    protected short dexterity;

    //Informations
    protected short lifeTime = 0;
    protected boolean horny = false;
    protected boolean alive = true;
    protected Point position;

    //Graphic
    protected Color colorOfTile;

    //World
    protected World world;

    public void draw(Dimension dimension, Graphics g)
    {
        g.setColor(this.colorOfTile);
        g.fillRect(position.x * world.getSizeOfTile(), position.y * world.getSizeOfTile(), dimension.width, dimension.height);
    }

    abstract public void action();

    public void dying()
    {
        this.alive = false;
        this.world.map.setOrganismOnTile(this.position, null);
        this.world.listOfOrganisms.remove(this);
    }

    public void moveOnPosition(Point position)
    {
        world.map.moveOrganism(this, position);

        this.position = position;
    }

    public void fight(Organism attackingOrganism, Organism defendingOrganism)
    {
        System.out.print("Walka - ");
        System.out.print("Atakujacy: " + attackingOrganism.getClass().getSimpleName());
        System.out.print(" Broniacy: " + defendingOrganism.getClass().getSimpleName());
        System.out.println();
        boolean attackResult = attackingOrganism.attack(defendingOrganism);
        if (attackResult && attackingOrganism.isAlive())
        {
            defendingOrganism.defence(attackingOrganism);
        }
    }



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

    boolean defence(Organism attackingOrganism)
    {
        if (this.strength < attackingOrganism.getStrength())
        {
            if (this instanceof IJumper && ((IJumper) this).escape())
            {
                return true;
            }
            if (this instanceof IToxic)
            {
                attackingOrganism.dying();
                return true;
            }
            if (this instanceof ISteroid)
            {
                ((ISteroid) this).boost(attackingOrganism);
            }
            this.dying();
            return false;
        }
        else
        {
            return true;
        }
    }

    abstract protected Organism cloning (Point position);

    @Override
    public int compareTo(Organism org)
    {
        if (org.getDexterity() - this.getDexterity() != 0)
        {
            return org.getDexterity() - this.getDexterity();
        }
        else
        {
            return org.getLifeTime() - this.getLifeTime();
        }
    }

    protected ArrayList<Point> gettingAvailableFields (short range)
    {
        ArrayList<Point> availableFields = new ArrayList<Point>();

        for (int x = -range; x <= range; x++)
        {
            for (int y = -range; y <= range; y++)
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
}
