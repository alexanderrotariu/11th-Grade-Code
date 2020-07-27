package KittyGame;

public class Bullet
{
    private int x;
    private int y;
    private int diam;
    private int velX;
    private int velY;
    
    public Bullet()
    {
        x=120;
        y=635;
        diam=5;
        velX=3;
        velY=0;
    }
    
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getvelX()
    {
        return velX;
    }
    public int getVelY()
    {
        return velY;
    }
    public int getDiam()
    {
        return diam;
    }
    
    /*public int getVelX()
    {
        return vX;
    }*/
    
    //mutator methods
    public void setVelX(int a)
    {
        velX=a;
        
    }
    public void setVelY(int a)
    {
        velY=a;
        
    }
    public void setDiam(int a)
    {
        diam=a;
        
    }
    public void setX(int a)
    {
        x=a;
    }
    public void setY(int a)
    {
        y=a;
    }
    
    public void moveRight()
    {
        x+=velX;
    }
    public void moveUp()
    {
        y+=velY;
    }
    
    
            
                
            
    
}