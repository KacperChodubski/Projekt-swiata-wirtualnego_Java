package fajen;

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
                world.nextTurn();
                world.repaint();
                world.draw(g);
            }
        });
        add(world);
        world.setVisible(true);

        getContentPane().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                System.out.print(e.getX() + " " + e.getY());
                System.out.println();
            }
        });

        setLayout(null);
        setVisible(true);
    }
}
