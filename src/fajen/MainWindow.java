package fajen;

import fajen.Organisms.Organism;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame
{
    private static final int magicHeight = 15;
    private static final int magicWidth = 13;
    public static final String MAIN_WINDOW_TITLE = "Fajen okno";
    public MainWindow(Dimension dimension, World world)
    {
        setSize(dimension);
        getContentPane().setBackground(Color.decode("#383838"));
        setTitle(MAIN_WINDOW_TITLE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        world.setBounds(world.MARGIN - magicWidth, world.MARGIN - magicHeight, world.getWorldDimension().width, world.getWorldDimension().height);
        addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                Graphics g = world.getGraphics();
                world.setPressedKey(e);
                //System.out.println(e);
                world.nextTurn();
                world.repaint();
                world.draw(g);
                if (e.getKeyChar() == 'r')
                {
                    world.readSave();
                }
                if (e.getKeyChar() == 's')
                {
                    world.save();
                }
            }
        });
        add(world);
        world.setVisible(true);

        world.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (world.getSizeOfTile() != null)
                {
                    Point pos = new Point(e.getX() / world.getSizeOfTile().width, e.getY() / world.getSizeOfTile().height);
                    Organism organism = world.map.getOrganismFromTile(pos);
                    System.out.print(pos.x + " " + pos.y + " ");
                    if (organism == null)
                    {
                        System.out.println("Pusty");
                    } else
                    {
                        System.out.println(organism.getClass().getSimpleName());
                    }
                }
            }
        });

        setLayout(null);
        setVisible(true);
    }
}
