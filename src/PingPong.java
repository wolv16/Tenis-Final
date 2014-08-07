import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Random;

class Controlador extends JPanel implements Runnable, KeyListener {
    //posicion inicial balon
    private double posXPelota; 
    private double posYPelota;
    //posicion inicial barra p1
    private double posXBarra1;
    private double posYBarra1;
    //posicion inicial barra p2
    private double posXBarra2;
    private double posYBarra2;
    private double tamanoYBarra;
    private double tamanoXBarra;

    private double velocidadBarritas = 6.0; //velocidad barras
    private double velocidadPelota = 5.0; //velocidad pelotita
    
    private int player1 = 1;
    private int player2 = 1;
    
    private int bolitaX = 1;
    private int bolitaY = 1;
    
    private int countP1 = 0;
    private int countP2 = 0;
    
    private int vidasP1 = 3;
    private int vidasP2 = 3;
    
    protected Controlador() {
        super();
        this.posXPelota = 0;
        this.posYPelota = 0;

        this.posXBarra1 = 565;
        this.posYBarra1 = 0;

        this.posXBarra2 = 0;
        this.posYBarra2 = 0;
        
        this.tamanoYBarra = 200;        
        this.tamanoXBarra = 15;
        
        
    }

    public void paint(Graphics gr) {
        super.paint(gr);
        Graphics2D g = (Graphics2D)gr;
                
        g.setColor(Color.WHITE);//600 * 400
        g.fill(new Rectangle2D.Double(298, 0, 4, 400));
        
        g.setColor(Color.GREEN);
        g.fill(new Ellipse2D.Double(this.posXPelota, this.posYPelota, 20, 20));
        
        g.setColor(Color.BLUE);
        g.fill(new Rectangle2D.Double(this.posXBarra1, this.posYBarra1, tamanoXBarra, tamanoYBarra));
        
        g.setColor(Color.RED);
        g.fill(new Rectangle2D.Double(this.posXBarra2, this.posYBarra2, tamanoXBarra, tamanoYBarra));
        return;
    }

    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();

        switch (c) {
            case KeyEvent.VK_DOWN:
                this.player1 = 0;
                break;
            case KeyEvent.VK_UP:
                this.player1 = 1;
                break;                
            case KeyEvent.VK_Z:
                this.player2 = 0;
                break;
            case KeyEvent.VK_A:
                this.player2 = 1;
                break;
        }

        super.repaint();
        return;
    }

    public void keyReleased(KeyEvent e) {
        super.repaint();
        return;
    }

    public void keyTyped(KeyEvent e) {
        super.repaint();
        return;
    }
    
    public void jugadorUnoBaja(){
        this.posYBarra1 += this.velocidadBarritas;
        if (this.posYBarra1 > 315) {
            this.posYBarra1 = 315;
        }
    }
    
    public void jugadorUnoSube(){
        this.posYBarra1 -= this.velocidadBarritas;
        if (this.posYBarra1 < 0) {
            this.posYBarra1 = 0;
        }        
    }
    public void jugadorDosBaja(){
        this.posYBarra2 += this.velocidadBarritas;
        if (this.posYBarra2 > 315) {
            this.posYBarra2 = 315;
        }
    }
    
    public void jugadorDosSube(){
        this.posYBarra2 -= this.velocidadBarritas;
        if (this.posYBarra2 < 0) {
            this.posYBarra2 = 0;
        }
    }
    
    public void saquePelotaCentro(){
        this.posXPelota = 298; //
        this.posYPelota = 200; //        
    }
    
    public void cambioDireccionHorizontalPelota(int i){
        this.bolitaX = i;
    }
    
    public void cambioDireccionVerticalPelota(int i){
        this.bolitaY = i;
    }
    @Override
    public void run() {
        while (true) {
            if (player1 == 0) {
                jugadorUnoBaja();
            }
            
            if (player1 == 1) {
                jugadorUnoSube();
            }

            if (player2 == 0) {
                jugadorDosBaja();
            }
            
            if (player2 == 1) {
                jugadorDosSube();
            }

            if (bolitaX == 0) { // si la pelota esta hacia la izquierda <---                
                this.posXPelota -= this.velocidadPelota;
                if (this.posXPelota < 0) {
                    cambioDireccionHorizontalPelota(1);
                    saquePelotaCentro();//
                    //punto p1
                }
                
            }
            if (bolitaX == 1) { // si la pelota esta hacia la derecha --->                
                this.posXPelota += this.velocidadPelota;
                if (this.posXPelota > 570) {
                    cambioDireccionHorizontalPelota(0);
                    saquePelotaCentro();
                    //punto p2
                }
            }
            if (bolitaY == 0) { //si la pelota esta subiendo
                this.posYPelota -= this.velocidadPelota;
                if (this.posYPelota < 0) {
                    cambioDireccionVerticalPelota(1);
                }
            }
            if (bolitaY == 1) { // si la pelota esta bajando
                this.posYPelota += this.velocidadPelota;
                if (this.posYPelota > 350) {
                    cambioDireccionVerticalPelota(0);
                }
            }

            if(this.vidasP1 < 0) {
                System.out.println("Ganador del juego >> Naranja");
                System.exit(1);
            }

            if(this.vidasP2 < 0) {
                System.out.println("Ganador del juego >> Azul");
                System.exit(1);
            }

            try {
                Thread.sleep(40);
                //Thread.sleep((int)Math.round(30));
            } catch (Exception e) {}

            double check1 = this.posYBarra1;
            while (check1 <= this.posYBarra1+tamanoYBarra) {
                if (this.posXBarra1 == this.posXPelota+20 && check1 == this.posYPelota) {
                    if (player1 == 0) {
                        this.bolitaX = 0;
                        this.bolitaY = 1;
                    }
                    if (player1 == 1) {
                        this.bolitaX = 0;
                        this.bolitaY = 0;
                    }
                }
                check1 += 1.0;
            }

            double check2 = this.posYBarra2;
            while (check2 <= this.posYBarra2+tamanoYBarra) {
                if (this.posXBarra2+tamanoXBarra == this.posXPelota && check2 == this.posYPelota) {
                    if (player2 == 0) {
                        this.bolitaX = 1;
                        this.bolitaY = 1;
                    }
                    if (player2 == 1) {
                        this.bolitaX = 1;
                        this.bolitaY = 0;
                    }
                }
                check2 += 1.0;
            }

            super.repaint();
        }
    }
}

public class PingPong extends JFrame {
        public void inicia(){
            JFrame aux = new JFrame();
            aux.setSize(600, 400);
            aux.setLocation(200, 100);
            aux.setTitle("Ping-Pong");
            aux.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Controlador c = new Controlador();
            aux.add(c);
            aux.addKeyListener(c);
            c.setBackground(Color.BLACK);

            Thread t = new Thread(c);
            t.start();

            aux.setVisible(true);
            aux.setResizable(false);
            aux.setLocationRelativeTo(null);
            //Tenis tenis = new Tenis();            
            
            return;
        }
}