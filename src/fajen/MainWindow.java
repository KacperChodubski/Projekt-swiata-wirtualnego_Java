package fajen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame
{
    public static final String MAIN_WINDOW_TITLE = "Fajen okno";
    public MainWindow(Dimension dimension, World world)
    {
        setSize(dimension);
        setTitle(MAIN_WINDOW_TITLE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //world.setBounds(new Rectangle(0, 0, 300, 300));
        add(world);
        addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                Graphics g = getContentPane().getGraphics();
                world.nextTurn();
                g.clearRect(0,0, world.getWidth(), world.getHeight());
                world.draw(getContentPane().getGraphics());
            }
        });

        getContentPane().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                System.out.print(e.getX() + " " + e.getY());
                System.out.println();
            }
        });


        setVisible(true);
    }
}
