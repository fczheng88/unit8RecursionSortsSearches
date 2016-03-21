import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class FractalPanel extends JPanel
{
    private double lDT; //left "delta" Theta for left child's angle from parent
    private double rDT; //right "delta" Theta for right child's angle from parent

    private double lCLMult; //left child's length multiplier
    private double rCLMult; //right child's length multiplier

    private Point2D.Double oParentBottom; //bottom of the parent
    private double oParentLength = 35; //based off of parentBottom and parentTop
    private double oTheta = 90; // angle of the parent
    
    private int maxIterations;
    private int colorChangePerIter;
    
    private Color startColor = new Color(0,255,255);
    
    public FractalPanel(int iterations, double parentLength, double parentTheta, Point2D.Double parentBottom, double leftMult, double rightMult, double leftDeltaTheta, double rightDeltaTheta, int PANEL_WIDTH, int PANEL_HEIGHT)
    {
        setBackground(Color.BLACK);
        setPreferredSize (new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        
        oParentLength = parentLength;
        oTheta = parentTheta;
        oParentBottom = parentBottom;
        
        lCLMult = leftMult;
        rCLMult = rightMult;
        
        lDT = leftDeltaTheta;
        rDT = rightDeltaTheta;
        
        maxIterations = iterations;
        colorChangePerIter = 254/iterations;
    }

public void setLeftAngle(double lA)
{
    lDT = lA;
}
public void setRightAngle(double rA)
{
    rDT = rA;
}
    public void setOrder(int newOrder)
    {
        maxIterations = newOrder;
        colorChangePerIter = 254/maxIterations;
    }
    public void drawFractal(Graphics g2, Point2D.Double parentTop, double pL, double theta, int iters, Color parentColor)
    {
        if(iters>=maxIterations || parentTop.getX() < 0 || parentTop.getY() < 0 ||pL <=1)//put end condition here
        {
            return;
        }
        double lCL = pL*lCLMult;
        double rCL = pL*rCLMult;
        
        double lCDX = lCL*Math.cos(Math.toRadians(theta+lDT));
        double lCDY = lCL*Math.sin(Math.toRadians(theta+lDT));
        
        double rCDX = rCL*Math.cos(Math.toRadians(theta-rDT));
        double rCDY = rCL*Math.sin(Math.toRadians(theta-rDT));

        Point2D.Double leftChildTop = new Point2D.Double(parentTop.getX()+lCDX, parentTop.getY()+lCDY);
        Point2D.Double rightChildTop = new Point2D.Double(parentTop.getX()+rCDX, parentTop.getY()+rCDY);
        
        Color colorLeft = new Color(parentColor.getRed(), parentColor.getGreen()-colorChangePerIter, parentColor.getBlue());
        Color colorRight = new Color(parentColor.getRed(), parentColor.getGreen(), parentColor.getBlue()-colorChangePerIter);
       
        g2.setColor (colorLeft);
        g2.drawLine((int)parentTop.getX(), (int)parentTop.getY(), (int)leftChildTop.getX(), (int)leftChildTop.getY());
        //next iteration: Left Child
        drawFractal(g2, leftChildTop, lCL, theta+lDT, iters+1, colorLeft);
        
        g2.setColor (colorRight);
        g2.drawLine((int)parentTop.getX(), (int)parentTop.getY(), (int)rightChildTop.getX(), (int)rightChildTop.getY());
        //next iteration: Right Child
        drawFractal(g2, rightChildTop, rCL, theta-rDT,  iters+1, colorRight);

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        
        
        g.setColor (startColor);

        Graphics2D g2 = (Graphics2D) g;
        g2.translate(0.0, getHeight());
        g2.scale(1.0, -1.0);

        Point2D.Double oParentTop = new Point2D.Double(oParentBottom.getX()+oParentLength*Math.cos(Math.toRadians(oTheta)), oParentBottom.getY()+oParentLength*Math.sin(Math.toRadians(oTheta)));
        
        
        //draw first parent:

        g2.drawLine((int)oParentBottom.getX(), (int)oParentBottom.getY(), (int)oParentTop.getX(), (int)oParentTop.getY());
        
        System.out.println("drewparentline");
        this.drawFractal(g2, oParentTop, oParentLength, oTheta, 0, startColor);//new Color(0,122,122));
    }
}