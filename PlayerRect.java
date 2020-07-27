package KittyGame;

public class PlayerRect
{
    //Instance Variables for player
    private int x;
    private int y;
    private int width;
    private int height;
    //private int vX;
    
    public PlayerRect()
    {
        //Setting the Rectangle instance variables
        x=450;
        y=610;
        width=80;
        height=80;
        //vX=0;
    }
    
    public String toString()
    {
        
       return ("x cord "+x)+(" y cord is "+y)+(" width is "+ width)+(" Heigt is "+height)/*+(" velocity is "+vX)*/;
      
    }
    
    //accessor methods
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    /*public int getVelX()
    {
        return vX;
    }*/
    
    //mutator methods
    public void setX(int a)
    {
        x=a;
        
    }
    public void setY(int a)
    {
        y=a;
        
    }
    public void setWidth(int a)
    {
        width=a;
        
    }
    public void setHeight(int a)
    {
        height=a;
        
    }
    /*public int setVelocity(int a)
    {
        vX=a;
        return a;
    }*/
    
    //Move method
    //Accepts an int that sets the xVelocity of the rectangle to a value
    public void moveLeft()
    {
        setX(x-25);
    }
    public void moveRight()
    {
        setX(x+25);
    }
    
}