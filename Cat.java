package KittyGame;

public class Cat
{
    private int x;
    private int y;
    private int diam;
    private double yV;
    
    public Cat()
    {
        x=(int)((Math.random()*474)+201);
        y=(int)(Math.random()*2500*-1);
        diam=45;
        yV=3.5;
        
    }
    
    public String toString()
    {
        return ("x cord "+x)+(" y cord is "+y)+(" diam is "+ diam)+(" velocity is "+yV);
    }
    
    //Accessor methods
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getDiam()
    {
        return diam;
    }
    public double getVelocityY()
    {
        return yV;
    }
    
    //Mutator methods
    public void setX(int a)
    {
        x=a;
    }
    public void setY(int a)
    {
        y=a;
    }
    public void setDiam(int a)
    {
        diam =a;
    }
    public void setVelocityY(double a)
    {
        yV=a;
    }
    
    public void moveDown()
    {
        y+=yV;
    }
}