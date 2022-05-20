package fajen;

import fajen.Organisms.Organism;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame
{
    private static final int magicHeight = 15;
    private static final int magicWidth = 13;

    private static final int BOX_Y = 0;
    private static final int BOX_WIDTH = 500;
    private static final int BOX_HEIGHT = 50;

    private static final int SAVE_BUTTON_WIDTH = 80;
    private static final int SAVE_BUTTON_HEIGHT = 40;
    private static final int SAVE_BUTTON_X = 100;
    private static final int SAVE_BUTTON_Y = 700;

    private static final int NEXT_TURN_BUTTON_WIDTH = 80;
    private static final int NEXT_TURN_BUTTON_HEIGHT = 40;
    private static final int NEXT_TURN_BUTTON_X = 400;
    private static final int NEXT_TURN_BUTTON_Y = 700;

    private static final int LOAD_BUTTON_WIDTH = 80;
    private static final int LOAD_BUTTON_HEIGHT = 40;
    private static final int LOAD_BUTTON_X = 250;
    private static final int LOAD_BUTTON_Y = 700;

    public static final String MAIN_WINDOW_TITLE = "Fajen okno";
    JComboBox <Organism> boxOfOrganisms;
    public MainWindow(Dimension dimension, World world)
    {
        gettingSizeOfMap(world);
        boxOfOrganisms = new JComboBox<Organism>();
        for (Organism org: world.listOfAllOrganisms)
        {
            boxOfOrganisms.addItem(org);
        }
        boxOfOrganisms.setFocusable(false);
        boxOfOrganisms.setVisible(true);
        boxOfOrganisms.setBounds(dimension.width/2 - BOX_WIDTH/2, BOX_Y, BOX_WIDTH, BOX_HEIGHT);

        addingSaveButton(world);

        addingLoadButton(world);

        addingNextTurnButton(world);

        getContentPane().add(boxOfOrganisms);
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
                world.nextTurn();
                world.setPressedKey(null);
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

                    if (boxOfOrganisms.getSelectedItem() instanceof Organism org)
                    {
                        world.addOrganism(org.cloning(pos));
                        world.draw(world.getGraphics());
                    }

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


    private void addingSaveButton(World world)
    {
        JButton saveButton = new JButton("Save");
        saveButton.setFocusable(false);
        saveButton.setBounds(SAVE_BUTTON_X, SAVE_BUTTON_Y, SAVE_BUTTON_WIDTH, SAVE_BUTTON_HEIGHT);
        add(saveButton);
        saveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                world.save();
                world.draw(world.getGraphics());
            }
        });
    }

    private void addingLoadButton (World world)
    {
        JButton loadButton = new JButton("Load");
        loadButton.setFocusable(false);
        loadButton.setBounds(LOAD_BUTTON_X, LOAD_BUTTON_Y, LOAD_BUTTON_WIDTH, LOAD_BUTTON_HEIGHT);
        add(loadButton);
        loadButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                world.readSave();
                world.draw(world.getGraphics());
            }
        });
    }

    private void addingNextTurnButton(World world)
    {
        JButton nextTurnButton = new JButton("Next turn");
        nextTurnButton.setFocusable(false);
        nextTurnButton.setBounds(NEXT_TURN_BUTTON_X, NEXT_TURN_BUTTON_Y, NEXT_TURN_BUTTON_WIDTH, NEXT_TURN_BUTTON_HEIGHT);
        add(nextTurnButton);
        nextTurnButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                world.nextTurn();
                world.draw(world.getGraphics());
            }
        });
    }

    private void gettingSizeOfMap (World world)
    {
        try
        {
            int x = Integer.parseInt(JOptionPane.showInputDialog(this, "Szerokosc:", "20"));
            int y = Integer.parseInt(JOptionPane.showInputDialog(this, "Wysokosc:", "20"));
            world.resizeWorld(x, y);
            world.createRandomWorld();
        }
        catch (NumberFormatException nfe)
        {
            System.err.println("Rozmiar mapy musi byc liczba");
        }
    }
}
