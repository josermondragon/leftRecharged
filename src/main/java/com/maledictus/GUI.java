package com.maledictus;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GUI extends JFrame {

    private static JTextPane jta;
    private static JTextField inputtedUser;
    private static GUI instance;
    private String userInput = "";


    public JTextField getInputtedUser() {
        return this.inputtedUser;
    }

    public GUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame fj = new JFrame("MALEDICTUS... Cuz some things have to be done right...not left...");
        LayerUI<JPanel> layerUI = new SpotlightLayerUI();
        JPanel panel1 = createPanel();
        JLayer<JPanel> jlayer = new JLayer<JPanel>(panel1, layerUI);
        fj.add(jlayer);
        fj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fj.setSize(800, 600);
        fj.setLocationRelativeTo(null);
        fj.setVisible(true);

        }
    private static JPanel createPanel() {
        GridBagConstraints abc = new GridBagConstraints();
        abc.fill = GridBagConstraints.BOTH;
        //panel1 that holds name (label and text field), btn for map and instructions
        JPanel panel1 = new JPanel();
        //setting up the grid
        GridBagLayout layout = new GridBagLayout();
        panel1.setLayout(layout);
        //where the game information renders
        JLabel game = new JLabel("Game: ");
        jta = new JTextPane();
        jta.setBackground(Color.BLACK);
        jta.setEditable(false);
        abc.gridx = 0;
        abc.gridy = 0;
        panel1.add(game, abc);
        abc.gridx = 0;
        abc.gridy = 1;
        //panel1.add(textScrollPane(jta),abc);
        panel1.add(textScrollPane(jta), abc);
        //user input
        JLabel userInput = new JLabel("Type here your option or command: ");
        inputtedUser = new JTextField("", 15);
        abc.gridx = 0;
        abc.gridy = 2;
        panel1.add(userInput, abc);
        abc.gridx = 0;
        abc.gridy = 3;
        panel1.add(inputtedUser, abc);

        //utilities like command(img) and options(img)
        ImageIcon img = new ImageIcon();  //***
        img = new ImageIcon(GUI.class.getClassLoader().getResource("data/commands.png")); //***
        JLabel label = new JLabel(); //***
        label.setIcon(img);  //***
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    WelcomePage.Commands();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        ImageIcon img2 = new ImageIcon();  //***
        img2 = new ImageIcon(GUI.class.getClassLoader().getResource("data/options.png")); //***
        JLabel label1 = new JLabel(); //***
        label1.setIcon(img2);  //***
        label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    WelcomePage.Options();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        abc.gridx = 30;
        abc.gridy = 1;
        panel1.add(label, abc);
        abc.gridx = 60;
        abc.gridy = 1;
        panel1.add(label1, abc);

        //button for map and instructions to show themselves.
        JButton map = new JButton("map");
        JButton instructions = new JButton("instructions");
        abc.gridx = 30;
        abc.gridy = 2;
        panel1.add(map, abc);
        map.addActionListener(e -> {
            try {
                buttonPressed2();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        abc.gridx = 60;
        abc.gridy = 2;
        panel1.add(instructions, abc);
        instructions.addActionListener(e -> {
            try {
                buttonPressed();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        return panel1;
    }

        public static GUI getInstance () {
            if (instance == null) {
                instance = new GUI();
            }
            return instance;
        }
        // Area that show the text in the game in real time
        private static JTextArea textArea () {
            JTextArea output = new JTextArea();
            output.setLineWrap(true); // Text return to line, so no horizontal scrollbar
            output.setForeground(Color.BLACK);
            output.setBackground(Color.WHITE);

            return output;
        }

        private static JScrollPane textScrollPane (JTextPane jta){
            JScrollPane scrollPane = new JScrollPane(jta);
            scrollPane.setBounds(0, 0, 500, 500);
            scrollPane.getViewport().setViewPosition(new Point(0, 0));
            return scrollPane;
        }

        static void buttonPressed () throws IOException {
            WelcomePage.Instructions();

        }

        static void buttonPressed2 () throws IOException {
            WelcomePage.Map();
        }

        private void userInputEnter (KeyEvent arg0){
            if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
                String inputtedUser = "";
                jta.setText("Hello" + inputtedUser);
                System.out.println("Enter was pressed");
            }

        }

        public void buttonAddText (String string){
        Document doc = jta.getDocument();
            try {
                SimpleAttributeSet set = new SimpleAttributeSet();
                StyleConstants.setForeground(set, Color.WHITE);
                doc.insertString(doc.getLength(), string, set);

            } catch (BadLocationException exp) {
                exp.printStackTrace();
            }
        }

    public void buttonAddText (String string, Color color){
        Document doc = jta.getDocument();
        try {
            SimpleAttributeSet set = new SimpleAttributeSet();
            StyleConstants.setForeground(set, color);
            doc.insertString(doc.getLength(), string, set);

        } catch (BadLocationException exp) {
            exp.printStackTrace();
        }
    }
    //Decorate main frame with Jlayer (Simulates a lamp in a dark house)
    class SpotlightLayerUI extends LayerUI<JPanel> {
        private boolean mActive;
        private int mX, mY;

        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            JLayer jlayer = (JLayer)c;
            jlayer.setLayerEventMask(
                    AWTEvent.MOUSE_EVENT_MASK |
                            AWTEvent.MOUSE_MOTION_EVENT_MASK
            );
        }

        @Override
        public void uninstallUI(JComponent c) {
            JLayer jlayer = (JLayer)c;
            jlayer.setLayerEventMask(0);
            super.uninstallUI(c);
        }

        @Override
        public void paint (Graphics g, JComponent c) {
            Graphics2D g2 = (Graphics2D)g.create();

            // Paint the view.
            super.paint (g2, c);

            if (mActive) {
                // Create a radial gradient, transparent in the middle.
                java.awt.geom.Point2D center = new java.awt.geom.Point2D.Float(mX, mY);
                float radius = 72;
                float[] dist = {0.0f, 1.0f};
                Color[] colors = {new Color(0.0f, 0.0f, 0.0f, 0.0f), Color.BLACK};
                RadialGradientPaint p =
                        new RadialGradientPaint(center, radius, dist, colors);
                g2.setPaint(p);
                g2.setComposite(AlphaComposite.getInstance(
                        AlphaComposite.SRC_OVER, .6f));
                g2.fillRect(0, 0, c.getWidth(), c.getHeight());
            }

            g2.dispose();
        }

        @Override
        protected void processMouseEvent(MouseEvent e, JLayer l) {
            if (e.getID() == MouseEvent.MOUSE_ENTERED) mActive = true;
            if (e.getID() == MouseEvent.MOUSE_EXITED) mActive = false;
            l.repaint();
        }

        @Override
        protected void processMouseMotionEvent(MouseEvent e, JLayer l) {
            Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), l);
            mX = p.x;
            mY = p.y;
            l.repaint();
        }

}
}