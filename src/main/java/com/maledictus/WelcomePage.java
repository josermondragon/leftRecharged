package com.maledictus;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;


public class WelcomePage extends JFrame {

    private JPanel panelWelcome;
    private JLabel title;
    private JButton btnGo;
    private JTextPane youMadeItTheTextPane;

    private static final String IMG_PATH = "data/imageIntro.jpeg";
    private static final String IMG_PATH2 = "data/instructions.PNG";
    private static final String IMG_PATH3 = "data/map.png";
    private static final String IMG_PATH4 = "data/rcommands.png";
    private static final String IMG_PATH5 = "data/roptions.png";

    public static void InitImage() {
        JFrame f = new JFrame();
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 700, 600);
        JButton close = new JButton("START GAME");
        panel.add(close);
        ImageIcon img;  //***
        img = new ImageIcon(WelcomePage.class.getClassLoader().getResource(IMG_PATH)); //***
        JLabel label = new JLabel(); //***
        label.setIcon(img);  //***
        panel.add(label);
        panel.setBackground(new Color(0, 0, 0));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane();
        f.setLayout(null);
        f.add(panel);
        f.setSize(700, 600);
        f.setUndecorated(true);                              //remove upper bar
        f.setVisible(true);
        f.getContentPane().setBackground(Color.black);
        f.setLocationRelativeTo(null);//center the frame

        close.addActionListener(e -> {
            f.dispose();
            javax.swing.SwingUtilities.invokeLater(() -> {
                Game game = null;
                try {
                    game = new Game();
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException exp) {
                    exp.printStackTrace();
                }
                try {
                    assert game != null;
                    game.initiateGame();
                } catch (IOException | org.json.simple.parser.ParseException | ParseException | UnsupportedAudioFileException | LineUnavailableException exp) {
                    exp.printStackTrace();
                }
            });
        });
        close.setBackground(new Color(0, 0, 0));
        close.setForeground(Color.WHITE);

    }

    public static void Instructions()  {
        JFrame f1 = new JFrame();
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 700, 500);
        JButton close2 = new JButton("Close");
        panel.add(close2);
        ImageIcon img ;  //***
        img = new ImageIcon(WelcomePage.class.getClassLoader().getResource(IMG_PATH2)); //***
        JLabel label = new JLabel(); //***
        label.setIcon(img);  //***
        panel.add(label);
        panel.setBackground(new Color(221, 221, 221));
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.getContentPane();
        f1.setLayout(null);
        f1.add(panel);
        f1.setUndecorated(true);
        f1.setVisible(true);
        f1.setSize(700, 500);
        f1.getContentPane().setBackground(Color.GRAY);
        f1.setLocationRelativeTo(null);
        close2.addActionListener(e -> {
            f1.dispose();
        });
        close2.setBackground(new Color(221, 221, 221));
        close2.setForeground(Color.BLACK);


    }
    public static void Map () throws IOException {


        JFrame f2 = new JFrame();
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 500, 700);
        JButton close2 = new JButton("Close");
        panel.add(close2);
        ImageIcon img ;  //***
        img = new ImageIcon(WelcomePage.class.getClassLoader().getResource(IMG_PATH3)); //***
        JLabel label = new JLabel(); //***
        label.setIcon(img);  //***
        panel.add(label);
        panel.setBackground(new Color(255, 255, 255));
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.getContentPane();
        f2.setLayout(null);
        f2.add(panel);
        f2.setUndecorated(true);
        f2.setVisible(true);
        f2.setSize(500, 700);
        f2.getContentPane().setBackground(Color.white);
        f2.setLocationRelativeTo(null);
        close2.addActionListener(e -> {
            f2.dispose();
        });
        close2.setBackground(new Color(221, 221, 221));
        close2.setForeground(Color.BLACK);

    }


    public static void Commands() throws IOException{

        JFrame f3 = new JFrame();
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 700, 500);
        JButton close2 = new JButton("Close");
        panel.add(close2);

        ImageIcon img;  //***
        img = new ImageIcon(WelcomePage.class.getClassLoader().getResource(IMG_PATH4)); //***
        JLabel label = new JLabel(); //***
        label.setIcon(img);  //***


        panel.add(label);
        panel.setBackground(new Color(221, 221, 221));
        f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f3.getContentPane();
        f3.setLayout(null);
        f3.add(panel);
        f3.setUndecorated(true);
        f3.setVisible(true);
        f3.setSize(700, 500);
        f3.getContentPane().setBackground(Color.GRAY);
        f3.setLocationRelativeTo(null);
        close2.addActionListener(e -> {
            f3.dispose();
        });
        close2.setBackground(new Color(221, 221, 221));
        close2.setForeground(Color.BLACK);


    }



    public static void Options() throws IOException{

        JFrame f4 = new JFrame();
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 600, 500);
        JButton close2 = new JButton("Close");
        panel.add(close2);
        ImageIcon img ;  //***
        img = new ImageIcon(WelcomePage.class.getClassLoader().getResource(IMG_PATH5)); //***
        JLabel label = new JLabel(); //***
        label.setIcon(img);  //***
        panel.add(label);
        panel.setBackground(new Color(221, 221, 221));
        f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f4.getContentPane();
        f4.setLayout(null);
        f4.add(panel);
        f4.setUndecorated(true);
        f4.setVisible(true);
        f4.setSize(600, 500);
        f4.getContentPane().setBackground(Color.GRAY);
        f4.setLocationRelativeTo(null);
        close2.addActionListener(e -> {
            f4.dispose();
        });
        close2.setBackground(new Color(221, 221, 221));
        close2.setForeground(Color.BLACK);


    }


}




