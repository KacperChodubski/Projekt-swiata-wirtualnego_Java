package fajen.Organisms;

import fajen.World;

import java.awt.*;

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

    Organism(short strength, short dexterity, Color color, Point position)
    {
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

    public void draw(Graphics g, Dimension dimension)
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

    abstract void collision(Organism organism);
    abstract boolean attack(Organism defendingOrganism);
    abstract boolean defence(Organism attackingOrganism);

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
}
