/**
 * Created by young on 6/19/17.
 */

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class RockPaperScissors {

    // selection frame
    JFrame frameInitial;
    JPanel topPanel;
    JButton rock;
    JButton paper;
    JButton scissors;
    JLabel welcome;
    // game frame
    JFrame frameFinal;
    JPanel west;
    JLabel centerWest;
    JPanel east;
    JLabel northEast;
    JButton southEast;
    JLabel selectionPerson;
    JLabel selectionAI;
    // determining selections
    int person;
    int ai;

    public RockPaperScissors() throws IOException {
        // images
        BufferedImage img = ImageIO.read(new File("rock.jpg"));
        Image imgNew = img.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        BufferedImage img2 = ImageIO.read(new File("paper.jpg"));
        Image imgNew2 = img2.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        BufferedImage img3 = ImageIO.read(new File("scissors.jpg"));
        Image imgNew3 = img3.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        // AI selection
        Random randomizer = new Random();
        this.selectionAI = new JLabel();
        int random = randomizer.nextInt(300);
        if (random < 100) {
            this.selectionAI.setIcon(new ImageIcon(imgNew));
            this.ai = 0;
        } else if (random >= 100 && random < 200) {
            this.selectionAI.setIcon(new ImageIcon(imgNew2));
            this.ai = 1;
        } else if (random >= 200 && random < 300) {
            this.selectionAI.setIcon(new ImageIcon(imgNew3));
            this.ai = 2;
        }
        // selection frame
        this.welcome = new JLabel("Welcome to Rock, Paper, Scissors! Choose one to play the game!");
        this.centerWest = new JLabel("vs.", SwingConstants.CENTER);
        this.selectionPerson = new JLabel();

        this.rock = new JButton();
        this.rock.setIcon(new ImageIcon(imgNew));
        this.rock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameInitial.setVisible(false);
                frameFinal.setVisible(true);
                selectionPerson.setIcon(new ImageIcon(imgNew));
                try {
                    generateResults(0);
                } catch (IOException k) {
                    // do nothing
                }
            }
        });
        this.paper = new JButton();
        this.paper.setIcon(new ImageIcon(imgNew2));
        this.paper.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameInitial.setVisible(false);
                frameFinal.setVisible(true);
                selectionPerson.setIcon(new ImageIcon(imgNew2));
                try {
                    generateResults(1);
                } catch (IOException k) {
                    // do nothing
                }
            }
        });
        this.scissors = new JButton();
        this.scissors.setIcon(new ImageIcon(imgNew3));
        this.scissors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameInitial.setVisible(false);
                frameFinal.setVisible(true);
                selectionPerson.setIcon(new ImageIcon(imgNew3));
                try {
                    generateResults(2);
                } catch (IOException k) {
                    // do nothing
                }
            }
        });

        this.topPanel = new JPanel(new GridBagLayout());
        this.topPanel.add(this.rock);
        this.topPanel.add(this.paper);
        this.topPanel.add(this.scissors);

        this.frameInitial = new JFrame();
        this.frameInitial.setLayout(new BorderLayout());
        this.frameInitial.add(this.topPanel, BorderLayout.CENTER);
        this.frameInitial.add(this.welcome, BorderLayout.NORTH);
        this.frameInitial.pack();
        this.frameInitial.setResizable(false);
        this.frameInitial.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frameInitial.setVisible(true);

        // game frame
        this.southEast = new JButton("Restart");
        this.southEast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameFinal.setVisible(false);
                try {
                    RockPaperScissors a = new RockPaperScissors();
                } catch (IOException k) {
                    // do nothing
                }
            }
        });

        this.west = new JPanel(new BorderLayout());
        this.west.add(this.selectionPerson, BorderLayout.NORTH);
        this.west.add(this.centerWest, BorderLayout.CENTER);
        this.west.add(this.selectionAI, BorderLayout.SOUTH);

        this.northEast = new JLabel(" wins!");

        this.east = new JPanel(new BorderLayout());
        this.east.add(this.northEast, BorderLayout.NORTH);
        this.east.add(this.southEast, BorderLayout.SOUTH);

        this.frameFinal = new JFrame();
        this.frameFinal.setLayout(new BorderLayout());
        this.frameFinal.add(this.west, BorderLayout.WEST);
        this.frameFinal.add(this.east, BorderLayout.EAST);
        this.frameFinal.pack();
        this.frameFinal.setSize(300, 300);
        this.frameFinal.setResizable(false);
        this.frameFinal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frameFinal.setVisible(false);
    }

    private void generateResults(int personSelect) throws IOException {
        this.person = personSelect;
        // who wins?
        this.northEast.setText(whoWins() + " wins!");
    }

    private String whoWins() {
        if (this.person == this.ai) {
            return "NO ONE";
        } else if ((this.ai == 0 && this.person == 2) || (this.ai == 1 && this.person == 0) ||
                  (this.ai == 2 && this.person == 1)) {
            return "COMPUTER";
        } else if ((this.person == 0 && this.ai == 2) || (this.person == 1 && this.ai == 0) ||
                  (this.person == 2 && this.ai == 1)) {
            return "PLAYER";
        }
        return "";
    }
}
