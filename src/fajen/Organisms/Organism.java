package fajen.Organisms;

import fajen.World;

import java.awt.*;
import java.util.ArrayList;

abstract public class Organism implements Comparable<Organism>, ICodable
{

    public Color getColorOfTile()
    {
        return colorOfTile;
    }

    public void setColorOfTile(Color colorOfTile)
    {
        this.colorOfTile = colorOfTile;
    }

    public int getStrength()
    {
        return strength;
    }

    public void setStrength(int strength)
    {
        this.strength = strength;
    }

    public int getDexterity()
    {
        return dexterity;
    }

    public void setDexterity(int dexterity)
    {
        this.dexterity = dexterity;
    }

    public int getLifeTime()
    {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime)
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
    protected int strength;
    protected int dexterity;

    //Informations
    protected int lifeTime = 0;
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
        g.fillRect(position.x * world.getSizeOfTile().width, position.y * world.getSizeOfTile().height, dimension.width, dimension.height);
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
            if (this instanceof IShelly && ((IShelly) this).deflect(attackingOrganism))
            {
                return true;
            }
            this.dying();
            return false;
        }
        else
        {
            return true;
        }
    }

    abstract public Organism cloning (Point position);

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

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName();
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

    @Override
    public void decode(String orgString)
    {
        String [] parameters = orgString.split(" ");
        this.setPosition(new Point(Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2])));
        this.setStrength(Integer.parseInt(parameters[3]));
        this.setLifeTime(Integer.parseInt(parameters[4]));
    }

    @Override
    public String code ()
    {
        String encodedOrg;

        encodedOrg = this.getClass().getSimpleName() + " ";
        encodedOrg += this.position.x + " ";
        encodedOrg += this.position.y + " ";
        encodedOrg += this.strength + " ";
        encodedOrg += this.lifeTime;

        return encodedOrg;
    }
}
