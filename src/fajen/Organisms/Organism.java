package fajen.Organisms;

import fajen.World;

import java.awt.*;

abstract public class Organism
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

    protected short strength;
    protected short dexterity;
    protected short lifeTime;
    protected boolean horny;
    protected boolean alive;
    protected Point position;
    protected Color colorOfTile;
    protected World world;

    public void draw(Graphics g, Dimension dimension)
    {
        g.setColor(this.colorOfTile);
        g.fillRect(position.x * world.getSizeOfTile(), position.y * world.getSizeOfTile(), dimension.width, dimension.height);
    }

    abstract void action();

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
