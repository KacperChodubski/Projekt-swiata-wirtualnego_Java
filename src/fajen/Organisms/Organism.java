package fajen.Organisms;

import fajen.World;

import java.awt.*;

abstract public class Organism
{
    protected static final short N = 0;
    protected static final short NE = 1;
    protected static final short E = 2;
    protected static final short SE = 3;
    protected static final short S = 4;
    protected static final short SW = 5;
    protected static final short W = 6;
    protected static final short NW = 7;

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

    }

    public void moveOnPosition(Point position)
    {
        world.map.moveOrganism(this, position);

        this.position = position;
    }

    abstract void collision(Organism organism);
    public void fight(Organism organism){};
    abstract boolean attack(Organism organism);
    abstract boolean defence(Organism organism);
}
