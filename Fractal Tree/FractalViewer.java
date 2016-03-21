import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class FractalViewer implements ActionListener
{

    private int WIDTH = 600;
    private int HEIGHT = 700;

    private final int minOrder = 1, maxOrder = 20;

    private JButton orderUp, orderDown, laUp, laDown, raUp, raDown;
    private JLabel orderLabel, leftAngle, rightAngle;
    private FractalPanel drawing;
    private JPanel panel, tools;
    private JFrame frame;

    private int initIters;
    private double initLeftAngle, initRightAngle;
    //-----------------------------------------------------------------
    //  Sets up the components for the applet.
    //-----------------------------------------------------------------
    public static void main(String[] args)
    {
        FractalViewer viewer = new FractalViewer( 10, 20, 20 );
    }

    public FractalViewer(int iters, double lA, double rA)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = (int)screenSize.getWidth();
        HEIGHT =(int)screenSize.getHeight()-75;

        initIters =iters;
        initLeftAngle = lA;
        initRightAngle = rA;

        drawing = new FractalPanel(iters,                                  //number of iterations
            30,                                 //length of initial piece
            90,                                 //"theta" of initial piece
            new Point2D.Double(WIDTH/2, 300),    //Coordinates of the Bottom of initial pice
            0.90,                               //left child Length mutiplier
            0.90,                               //right child length multiplier
            lA,                                 //left child angle difference
            rA,                                  //right child angle difference
            WIDTH,
            HEIGHT
        );

        tools = new JPanel ();
        tools.setLayout (new BoxLayout(tools, BoxLayout.X_AXIS));
        tools.setBackground (Color.yellow);
        tools.setOpaque (true);

        orderLabel = new JLabel ("Iterations: " + iters);
        orderLabel.setForeground (Color.black);
        orderUp = new JButton (new ImageIcon ("increase.gif"));
        orderUp.setPressedIcon (new ImageIcon ("increasePressed.gif"));
        orderUp.setMargin (new Insets (0, 0, 0, 0));
        orderUp.addActionListener (this);
        orderDown = new JButton (new ImageIcon ("decrease.gif"));
        orderDown.setPressedIcon (new ImageIcon ("decreasePressed.gif"));
        orderDown.setMargin (new Insets (0, 0, 0, 0));
        orderDown.addActionListener (this);

        leftAngle = new JLabel ("Left Angle: "+ lA);
        leftAngle.setForeground (Color.black);
        laUp = new JButton (new ImageIcon ("increase.gif"));
        laUp.setPressedIcon (new ImageIcon ("increasePressed.gif"));
        laUp.setMargin (new Insets (0, 0, 0, 0));
        laUp.addActionListener (this);
        laDown = new JButton (new ImageIcon ("decrease.gif"));
        laDown.setPressedIcon (new ImageIcon ("decreasePressed.gif"));
        laDown.setMargin (new Insets (0, 0, 0, 0));
        laDown.addActionListener (this);

        rightAngle = new JLabel ("Right Angle: "+ rA);
        rightAngle.setForeground (Color.black);
        raUp = new JButton (new ImageIcon ("increase.gif"));
        raUp.setPressedIcon (new ImageIcon ("increasePressed.gif"));
        raUp.setMargin (new Insets (0, 0, 0, 0));
        raUp.addActionListener (this);
        raDown = new JButton (new ImageIcon ("decrease.gif"));
        raDown.setPressedIcon (new ImageIcon ("decreasePressed.gif"));
        raDown.setMargin (new Insets (0, 0, 0, 0));
        raDown.addActionListener (this);

        tools.add (orderLabel);
        tools.add (orderUp);
        tools.add (orderDown);

        tools.add(leftAngle);
        tools.add(laUp);
        tools.add(laDown);

        tools.add(rightAngle);
        tools.add(raUp);
        tools.add(raDown);

        tools.add (Box.createHorizontalStrut (20));

        
        panel = new JPanel();
        panel.add (tools);
        panel.add (drawing);

        frame = new JFrame();
        frame.setTitle("Fractal Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.add(panel);
        frame.setVisible(true);
    }
    //-----------------------------------------------------------------
    //  Determines which button was pushed, and sets the new order
    //  if it is in range.
    //-----------------------------------------------------------------
    public void actionPerformed (ActionEvent event)
    {
        if (event.getSource() == orderUp)
        {
            initIters++;
            if (initIters <= maxOrder)
            {
                orderLabel.setText ("Iterations: " + initIters);
                drawing.setOrder (initIters);
                frame.repaint();
            }
            else
                initIters--;
        }
        else if(event.getSource() == orderDown)
        {
            initIters--;
            if (initIters >= minOrder)
            {
                orderLabel.setText ("Iterations: " + initIters);
                drawing.setOrder (initIters);
                frame.repaint();
            }
            else
                initIters++;
        }
        else if(event.getSource() == laUp)
        {
            initLeftAngle++;
            leftAngle.setText("Left Angle: "+ initLeftAngle);
            drawing.setLeftAngle(initLeftAngle);
            frame.repaint();
        }
        else if(event.getSource() == laDown)
        {
            initLeftAngle--;
            leftAngle.setText("Left Angle: "+ initLeftAngle);
            drawing.setLeftAngle(initLeftAngle);
            frame.repaint();
        }
        else if(event.getSource() == raUp)
        {
            initRightAngle++;
            rightAngle.setText("Right Angle: "+ initRightAngle);
            drawing.setRightAngle(initRightAngle);
            frame.repaint();
        }
        else if(event.getSource() == raDown)
        {
            initRightAngle--;
            rightAngle.setText("Right Angle: "+ initRightAngle);
            drawing.setRightAngle(initRightAngle);
            frame.repaint();
        }   

    }
}