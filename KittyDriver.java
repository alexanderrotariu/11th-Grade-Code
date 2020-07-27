package KittyGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Font;
import java.applet.*;

//imports for drawing Images
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class KittyDriver extends JComponent implements KeyListener, MouseListener
{
    //instance variables for music levels etc
    private int WIDTH;
    private int HEIGHT;
    private AudioClip bgMusic;
    private AudioClip shieldS;
    private AudioClip shotS;
    private int level;
    private long time;
    private long closeTime;
 
    //MAKES A PLAYER UC OBJECT
    PlayerRect a = new PlayerRect();
    
    //Making 5 cats
    Cat b = new Cat();
    Cat c = new Cat();
    Cat d = new Cat();
    Cat e = new Cat();
    Cat f = new Cat();
    
    //BULLET OBJECT
    Bullet bu=new Bullet();
    
    //VARIABLES TO USE IN ORDER TO GET ANIMATION CORRECT
    boolean left;
    boolean right;
    boolean leftSS, rightSS;
    boolean shield;
    boolean shieldRight;
    boolean shieldLeft;
    boolean win;
    boolean lose;
    boolean still;
    
    //VARIABLES THAT DEAL WITH THE LIVES AND KITTY COUNT
    int count=0;
    int lives=5;
    int shot=0;
    
    //Adding
    //Default Constructor
    public KittyDriver()
    {
        //initializing instance variables
        WIDTH = 700;
        HEIGHT = 700;
        level=1;
        time = 0;
        closeTime = 0;
        
        //Setting up the GUI
        JFrame gui = new JFrame(); //This makes the gui box
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes sure program can close
        gui.setTitle("Kitten Catcher"); //This is the title of the game, you can change it
        gui.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT + 30)); //Setting the size for gui
        gui.setResizable(false); //Makes it so the gui cant be resized
        gui.getContentPane().add(this); //Adding this class to the gui

         /*If after you finish everything, you can declare your buttons or other things
          *at this spot. AFTER gui.getContentPane().add(this) and BEFORE gui.pack();
          */

        gui.pack(); //Packs everything together
        gui.setLocationRelativeTo(null); //Makes so the gui opens in the center of screen
        gui.setVisible(true); //Makes the gui visible
        gui.addKeyListener(this);
        gui.addMouseListener(this);
        
        //MUSIC AND SHIELD CLIPS
        bgMusic = Applet.newAudioClip(this.getClass().getResource("Mii.wav"));
        shieldS = Applet.newAudioClip(this.getClass().getResource("ShieldNoise.wav"));
        shotS = Applet.newAudioClip(this.getClass().getResource("GunNoise.wav"));
        bgMusic.loop();
    }
    //This method will acknowledge user input
    public void keyPressed(KeyEvent e) 
    {
        //getting the key pressed
        int key = e.getKeyCode();
        System.out.println(key);
        
        //KEY PRESSES RIGHT 39 LEFT 37
        
        //moving the rectangle
        if(e.getKeyCode()==37&&a.getX()>200)
        {
            a.moveLeft();
        }
        else if(e.getKeyCode()==39&&a.getX()+a.getWidth()<700)
        {
            a.moveRight();
        }
        else
        {
            
        }
        
        //SHIELD BUTTON AND FUNCTION ANIMATION
        if(e.getExtendedKeyCode()==40)
        {
            shield=true;
            shieldS.play();
            
        }
        else
        {
            shield=false;
        }
        
        //ANIMATION CODE
        if(e.getKeyCode()==39)
        {
            right=true;
            rightSS=true;
            leftSS=false;
            shieldRight=true;
            
        }
        //DETERMINES WEATHER THE SPRITE SHOULD BE FACING LEFT OR RIGHT
        else
        {
            right=false;
        }
        //ANIMATION CODE
        if(e.getKeyCode()==37)
        {
            left=true;
            leftSS=true;
            rightSS=false;
            shieldLeft=true;
        }
        else
        {
            left=false;
        }
        
        if(e.getKeyCode()!=37&&e.getKeyCode()!=39&&e.getKeyCode()!=40)
        {
            still=true;
            
            
        }
        else
        {
            still=false;
        }
        

    }   
    //All your UI drawing goes in here
    public void paintComponent(Graphics g)
    {
        //THIS CODE ALLOWS US TO INSERT GRAPHICS
        Graphics2D g2d;
        g2d=(Graphics2D)g;
        
        //ANIMATED BACKGROUND CODE
        ImageIcon Background=new ImageIcon(KittyDriver.class.getResource("spaceBGChoppy.gif"));
        Image bg=Background.getImage();
        g2d.drawImage(bg, 0, 0, 700, 700, null);
        
        
        //g.setColor(Color.BLACK);
        //g.fillRect(0, 0, 1000, 700);
        
        //Drawing a Rect Player
        //g.setColor(Color.YELLOW);
        //g.fillRect(a.getX(), a.getY(), a.getWidth(), a.getHeight());
         
        //THIS CODE DETERMINES WHAT KEY YOU PRESS AND DISPLAYS THE APPROPIATE ANIMATION
        if(still==true)
        {
            ImageIcon alexStill=new ImageIcon(KittyDriver.class.getResource("AlexStandRight.gif"));
            Image image =alexStill.getImage();
            g2d.drawImage(image, a.getX(), a.getY(), a.getWidth(), a.getHeight(), null);
        }
        if(right==true&&left==false)
        {
            ImageIcon alex=new ImageIcon(KittyDriver.class.getResource("alexright.gif"));
            Image image=alex.getImage();
            g2d.drawImage(image, a.getX(), a.getY(), a.getWidth(), a.getHeight(), null);
        }
        else if(right == false &&left == true)
        {
            ImageIcon alexLeft=new ImageIcon(KittyDriver.class.getResource("AlexWalkLeft.gif"));
            Image image=alexLeft.getImage();
            g2d.drawImage(image, a.getX(), a.getY(), a.getWidth(), a.getHeight(), null);
        }
        else  //both false
        {
            //do the standing still image
            if(leftSS==true)
            {
                
                ImageIcon alexLeftStand=new ImageIcon(KittyDriver.class.getResource("AlexStandLeft.gif"));
                Image image=alexLeftStand.getImage();
                g2d.drawImage(image, a.getX(), a.getY(), a.getWidth(), a.getHeight(), null);
                
                ImageIcon alexShieldLeft=new ImageIcon(KittyDriver.class.getResource("AlexStandLeftShield.gif"));
                Image sh=alexShieldLeft.getImage();
                g2d.drawImage(sh, a.getX(), a.getY(), a.getWidth(), a.getHeight(), null);
            }
            else if(rightSS==true)//Standing still right
            {
                ImageIcon alexRightStand=new ImageIcon(KittyDriver.class.getResource("AlexStandRight.gif"));
                Image image=alexRightStand.getImage();
                g2d.drawImage(image, a.getX(), a.getY(), a.getWidth(), a.getHeight(), null);
                
                ImageIcon alexRightShield=new ImageIcon(KittyDriver.class.getResource("AlexStandRightShield.gif"));
                Image shw=alexRightShield.getImage();
                g2d.drawImage(shw, a.getX(), a.getY(), a.getWidth(), a.getHeight(), null);
            }
            else
            {
            ImageIcon alexStill=new ImageIcon(KittyDriver.class.getResource("AlexStandRight.gif"));
            Image image=alexStill.getImage();
            g2d.drawImage(image, a.getX(), a.getY(), a.getWidth(), a.getHeight(), null);
            }
        }
        
        
        //ENEMY SPRITE. NO SPECIFIC FUNCTIONALITY JUST FOR SHOW
        ImageIcon EvilGuy=new ImageIcon(KittyDriver.class.getResource("BadGuy.png"));
        Image evil=EvilGuy.getImage();
        g2d.drawImage(evil, 50, 610, 60, 80, null);
        
        //g.setColor(Color.RED);
        //g.fillRect(50, 610, 60, 80);
        
        //FLOOR SET
        g.setColor(Color.GRAY);
        g.fillRect(0, 690, 1000, 20);
        
        //Painting all the kitties
        //INSERTING ALL THE PNGS TO SKIN THE CATS
        ImageIcon cat1=new ImageIcon(KittyDriver.class.getResource("Cat1.png"));
        Image catO=cat1.getImage();
        g2d.drawImage(catO, b.getX(), b.getY(), b.getDiam(), b.getDiam(), null);
        //g.setColor(Color.WHITE);
        //g.fillOval(b.getX(),b.getY(), b.getDiam(), b.getDiam());
        
        ImageIcon cat2=new ImageIcon(KittyDriver.class.getResource("Cat2.png"));
        Image catT=cat2.getImage();
        g2d.drawImage(catT, c.getX(), c.getY(), c.getDiam(), c.getDiam(), null);
        //g.setColor(Color.ORANGE);
        //g.fillOval(c.getX(),c.getY(), c.getDiam(), c.getDiam());
        
        ImageIcon cat3=new ImageIcon(KittyDriver.class.getResource("Cat3.png"));
        Image catTh=cat3.getImage();
        g2d.drawImage(catTh, d.getX(), d.getY(), d.getDiam(), d.getDiam(), null);
        //g.setColor(Color.ORANGE);
        //g.fillOval(d.getX(),d.getY(), d.getDiam(), d.getDiam());
        
        ImageIcon cat4=new ImageIcon(KittyDriver.class.getResource("Cat4.png"));
        Image catF=cat1.getImage();
        g2d.drawImage(catF, e.getX(), e.getY(), e.getDiam(), e.getDiam(), null);
        //g.setColor(Color.ORANGE);
        //g.fillOval(e.getX(),e.getY(), e.getDiam(), e.getDiam());
        
        ImageIcon cat5=new ImageIcon(KittyDriver.class.getResource("Cat5.png"));
        Image catFi=cat5.getImage();
        g2d.drawImage(catFi, f.getX(), f.getY(), f.getDiam(), f.getDiam(), null);
        //g.setColor(Color.ORANGE);
        //g.fillOval(f.getX(),f.getY(), f.getDiam(), f.getDiam());
       
        //THIS IS THE COLOR AND PLACMENT OF THE BULLET
        g.setColor(Color.GREEN);
        g.fillRect(bu.getX(), bu.getY(), bu.getDiam(), bu.getDiam());
        

        //DRAWING THE TEXT FIELDS THAT HOLD THE COUNTER AND LIVES
        Font a= new Font("Cat counter: "+count, Font.PLAIN, 20);
        g.setFont(a);
        g.setColor(Color.WHITE);
        g.drawString("Cat counter: "+count, 10, 20);

        Font b= new Font("Lives "+lives, Font.PLAIN, 20);
        g.setFont(b);
        g.setColor(Color.WHITE);
        g.drawString("Lives: "+lives, 10, 50);
       
        if(win==true)
        {
            Font win= new Font("YOU HAVE WON" , Font.BOLD, 50);
            g.setFont(win);
            g.setColor(Color.GREEN);
            g.drawString("YOU HAVE WON", (WIDTH/2)-200, HEIGHT/2);
        }
        else if(lose==true)
        {
            Font lose = new Font("YOU HAVE LOST :(", Font.BOLD, 50);
            g.setFont(lose);
            g.setColor(Color.RED);
            g.drawString("YOU HAVE LOST :(", (WIDTH/2)-200, HEIGHT/2);
            closeTime = time + 1000;
            
            //System.exit(0);
        }
      
        
        //Drawing the user-controlled rectangle

        //Drawing the autonomous circle

    }
    public void loop()
    {
        //DETERMINE TIME 
        time++;
        System.out.println("time = " + time);
        //MAKING KITTENS FALL 
        if(win == false&&lose == false)
        {
            //MAKING FALLIN KITTENS
            b.moveDown();
            c.moveDown();
            d.moveDown();
            e.moveDown();
            f.moveDown();
        }
        
        //THE BULLET MOVES RIGHT
        bu.moveRight();
        bu.moveUp();
        
        //HAVING DIFFERENT SPEED LEVELS FOR KITTENS
        if(count==10&&level==1)
        {
            b.setVelocityY(b.getVelocityY()+.5);
            c.setVelocityY(c.getVelocityY()+.5);
            d.setVelocityY(d.getVelocityY()+.5);
            e.setVelocityY(e.getVelocityY()+.5);
            f.setVelocityY(f.getVelocityY()+.5);
            level++;
        }
        else if(count==20&&level==2)
        {
            b.setVelocityY(b.getVelocityY()+.5);
            c.setVelocityY(c.getVelocityY()+.5);
            d.setVelocityY(d.getVelocityY()+.5);
            e.setVelocityY(e.getVelocityY()+.5);
            f.setVelocityY(f.getVelocityY()+.5);
            level++;
        }
        else if(count==35&&level==3)
        {
            b.setVelocityY(b.getVelocityY()+.5);
            c.setVelocityY(c.getVelocityY()+.5);
            d.setVelocityY(d.getVelocityY()+.5);
            e.setVelocityY(e.getVelocityY()+.5);
            f.setVelocityY(f.getVelocityY()+.5);
            level++;
        }
        
        //COUNTER
        /*if(b.getY()+b.getDiam()>a.getY()&&b.getX() >= a.getX() &&( b.getX()<=a.getX()+a.getWidth()|| b.getX()+b.getDiam()<=a.getX()+a.getWidth()))
        {  
            count++;   
        }
        if((c.getY()+c.getDiam()>a.getY()&&c.getX() >= a.getX() &&( c.getX()<=a.getX()+a.getWidth()|| c.getX()+c.getDiam()<=a.getX()+a.getWidth())))
        {
            count++;
            
        }
        if(d.getY()+d.getDiam()>a.getY()&&d.getX() >= a.getX() &&( d.getX()<=a.getX()+a.getWidth()|| d.getX()+d.getDiam()<=a.getX()+a.getWidth()))
        {
            count++;
        }
        if(e.getY()+e.getDiam()>a.getY()&&e.getX() >= a.getX() &&( e.getX()<=a.getX()+a.getWidth()|| e.getX()+e.getDiam()<=a.getX()+a.getWidth()))
        {
            count++;
        }
        if(f.getY()+f.getDiam()>a.getY()&&f.getX() >= a.getX() &&( f.getX()<=a.getX()+a.getWidth()|| f.getX()+f.getDiam()<=a.getX()+a.getWidth()))
        {
            count++;
        }*/
        
        //LIFE SUBTRACTION WHEN YOU MISS A CAT
        if(b.getY()>690)
        {
            lives--;
        }
        if(c.getY()>690)
        {
            lives--;
        }
        if(d.getY()>690)
        {
            lives--;
        }
        if(e.getY()>690)
        {
            lives--;
        }
        if(f.getY()>690)
        {
            lives--;
        }
        
        //WHEN CAT HITS THE FLOOR IT GETS TELEPORTED BACK UP
        if(b.getY()>690)
        {
            b.setY((int)(Math.random()*5000*-1));
            b.setX((int)((Math.random()*473)+201));
        }
        
        if(c.getY()>690)
        {
            c.setY((int)(Math.random()*5000*-1));
            c.setX((int)((Math.random()*474)+201));
        }
       
        if(d.getY()>690)
        {
            d.setY((int)(Math.random()*5000*-1));
            d.setX((int)((Math.random()*474)+201));
        }
       
        if(e.getY()>690)
        {
            e.setY((int)(Math.random()*5000*-1));
            e.setX((int)((Math.random()*474)+201));
        }
       
        if(f.getY()>690)
        {
            f.setY((int)(Math.random()*5000*-1));
            f.setX((int)((Math.random()*474)+201));
        }
     
        //WHEN THE CAT HITS THE PLAYER THEN GET TELEPORTED BACK UP AND ADD 1 TO THE COUNTER
        if(detectCollision(b))
        {
            b.setY((int)(Math.random()*2500*-1));
            b.setX((int)((Math.random()*474)+201));
            count++;
        }
        if(detectCollision(c))
        {
            c.setY((int)(Math.random()*2500*-1));
            c.setX((int)((Math.random()*474)+201));
            count++;
        }
        if(detectCollision(d))
        {
            d.setY((int)(Math.random()*2500*-1));
            d.setX((int)((Math.random()*474)+201));
            count++;
            
        }
        if(detectCollision(e))
        {
            e.setY((int)(Math.random()*2500*-1));
            e.setX((int)((Math.random()*474)+201));
            count++;
        }
        if(detectCollision(f))
        {
            f.setY((int)(Math.random()*2500*-1));
            f.setX((int)((Math.random()*474)+201));
            count++;
        }
        
       /* if(b.getY()+b.getDiam()>a.getY()&&b.getX() >= a.getX() &&( b.getX()<=a.getX()+a.getWidth() &&b.getX()+b.getDiam()<=a.getX()+a.getWidth()))
        {
            b.setY((int)(Math.random()*5000*-1));
            b.setX((int)((Math.random()*474)+201));
        }
        
        if(c.getY()+c.getDiam()>a.getY()&&c.getX() >= a.getX() &&( c.getX()<=a.getX()+a.getWidth()&&c.getX()+c.getDiam()<=a.getX()+a.getWidth()))
        {
            c.setY((int)(Math.random()*5000*-1));
            c.setX((int)((Math.random()*474)+201));
        }
        
        if(d.getY()+d.getDiam()>a.getY()&&d.getX() >= a.getX() &&( d.getX()<=a.getX()+a.getWidth() &&d.getX()+d.getDiam()<=a.getX()+a.getWidth()))
        {
            d.setY((int)(Math.random()*5000*-1));
            d.setX((int)((Math.random()*474)+201));
        }
        
        if(e.getY()+e.getDiam()>a.getY()&&e.getX() >= a.getX() &&( e.getX()<=a.getX()+a.getWidth()&&e.getX()+e.getDiam()<=a.getX()+a.getWidth()))
        {
            e.setY((int)(Math.random()*5000*-1));
            e.setX((int)((Math.random()*474)+201));
        }

        if(f.getY()+f.getDiam()>a.getY()&&f.getX() >= a.getX() &&( f.getX()<=a.getX()+a.getWidth()&& f.getX()<=a.getX()+a.getWidth()))
        {
            f.setY((int)(Math.random()*5000*-1));
            f.setX((int)((Math.random()*474)+201));
        }*/
        
        //WHEN THE CAT HITS THE FLOOR SUBTRACT A LIFE
        if(b.getY()>690||c.getY()>690||d.getY()>690||e.getY()>690||f.getY()>690)
        {
            lives--;
        }
        
      
        
        
        
        //DETECTING COLLISIONS FOR THE BULLETS
        if(detectCollision()==true&&shield==true)
        {
            bu.setVelX(bu.getvelX()*-1);
            bu.setVelY(-1);
            
        }
        else if(detectCollision()==true&&shield==false&&shot==0)
        {
            lives--;
            shot++;
        }
        else if(detectCollision()==true&&shield==false&&shot==1)
        {
            lives--;
            shot++;
        }
        else if(detectCollision()==true&&shield==false&&shot==2)
        {
            lives--;
            shot++;
        }
        else if(detectCollision()==true&&shield==false&&shot==3)
        {
            lives--;
            shot++;
        }
        else if(detectCollision()==true&&shield==false&&shot==4)
        {
            lives--;
            shot++;
        }
        
        //HANDLE SHOOT AGAIN AFTER ITS DEFLECTED
        if(bu.getX()<(int)(Math.random()*-100)-200)
        {
            bu.setX(120);
            bu.setY(635);
            bu.setVelX(3);
            bu.setVelY(0);
            shotS.play();
        }
        
        //SETTIN WINNNING CONDITIONS 
        if((count==50||count>50)&&lives>0)
        {
            win=true;
        }
        //SETTING LOSING CONDITIONS 
        if(lives==0||lives<0)
        {
            lose=true;
        }
  
       
        

        //Do not write below this
        repaint();
    }
    //COLLISIONS METHOD
    public double distance(int x1, int x2, int y1, int y2)
    {
        double output=Math.sqrt(Math.pow(x2-x1,2)+(Math.pow(y2-y1,2)));
        return output;
    }
    public boolean detectCollision()
    {
        boolean output=false;

        int radius = bu.getDiam()/2;
        int centerX;
        int centerY;

        int nextX=bu.getX()+bu.getvelX();
        int nextY=bu.getY()+bu.getVelY();

        centerX=(2*nextX+bu.getDiam())/2;
        centerY=(2*nextY+bu.getDiam())/2;

        for(int i = a.getX(); i<=a.getX()+a.getWidth(); i++)
        {
            for(int n=a.getY(); n<=a.getY()+a.getHeight();n++)
            {
                if(distance(i, centerX, n , centerY)<radius)
                {
                    output=true;
                }
            }
        }
        return output;

    }
    public boolean detectCollision(Cat x)
    {
        boolean output=false;

        int radius = x.getDiam()/2;
        int centerX;
        int centerY;

        int nextX=x.getX();
        int nextY=x.getY()+(int)x.getVelocityY();

        centerX=(2*nextX+x.getDiam())/2;
        centerY=(2*nextY+x.getDiam())/2;

        for(int i = a.getX(); i<=a.getX()+a.getWidth(); i++)
        {
            for(int n=a.getY(); n<=a.getY()+a.getHeight();n++)
            {
                if(distance(i, centerX, n , centerY)<radius)
                {
                    output=true;
                }
            }
        }
        return output;

    }
        //if(bu.getX()+bu.getDiam()==a.getX()&&shield==true)
        //{
        //    bu.setVelX(bu.getvelX()*-1);
        //    bu.setVelY(1);
        //}
    //These methods are required by the compiler.  
    //You might write code in these methods depending on your goal.
    public void keyTyped(KeyEvent e) 
    {
    }
    public void keyReleased(KeyEvent e) 
    {
    }
    public void mousePressed(MouseEvent e)
    {
    }
    public void mouseReleased(MouseEvent e)
    {
    }
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseExited(MouseEvent e)
    {
    }
    public void start(final int ticks){
        Thread gameThread = new Thread(){
            public void run(){
                while(true){
                    loop();
                    try{
                        Thread.sleep(1000 / ticks);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };	
        gameThread.start();
    }

    public static void main(String[] args)
    {
        KittyDriver g = new KittyDriver();
        g.start(60);
    }

    
}
