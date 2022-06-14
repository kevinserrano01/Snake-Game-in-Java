import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Point;
// import java.awt.Color.*;

public class Snake extends JFrame{
    
    int width = 650;
    int height = 500;

    int direccion = KeyEvent.VK_LEFT; //VARIABLE GLOBAL

    long frecuencia = 20; //actualizar el juego cada 20mm

    Point snake;
    Point comida;

    // rectangulo de 10x10
    int widthPoint = 10;
    int heightPoint = 10;

    ImageSnake ImageSnake;

    public Snake(){
        setTitle("Snake");

        setSize(width, height);

        // PARA QUE APARESCA LA INTERFAZ AL CENTRO DEL MONITOR O RESOLUCION DE CADA MONITOR.
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dimension.width/2-width/2, dimension.height/2-height/2);

        //operacion de cierre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Teclas teclas = new Teclas();
        this.addKeyListener(teclas);

        startGame();

        ImageSnake = new ImageSnake();
        this.getContentPane().add(ImageSnake);

        setVisible(true);

        // manejo del juego
        Momento momento = new Momento();
        Thread trid = new Thread(momento);
        trid.start();
    }

    public void startGame(){
        comida = new Point(200, 200);
        snake = new Point(width/2, height/2);
    }

// MAIN ----------------------------------------------------------------------------------------------------------->
    public static void main(String[] args ) throws Exception {
        Snake s = new Snake();
    }

    public void actualizar() {
        ImageSnake.repaint();
    }
    
// CLASE IMAGESNAKE --------------------------------------------------------------------------------------------------->
    public class ImageSnake extends JPanel{

        public void paintComponent(Graphics g){
            super.paintComponent(g);

            g.setColor(new Color(0,0,255));;
            g.fillRect(snake.x, snake.y, widthPoint, heightPoint);

            g.setColor(new Color(255,0,0));
            g.fillRect(comida.x, comida.y,widthPoint, heightPoint);
        }
    }


//responda los eventos del telcado
// CLASE TECLAS --------------------------------------------------------------------------------------------------->
    public class Teclas extends KeyAdapter{

        public void KeyPressed(KeyEvent e){
            //evaluar el codigo de la tecla
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);;
            }else if(e.getKeyCode() == KeyEvent.VK_UP){
                if (direccion != KeyEvent.VK_DOWN) {
                        direccion = KeyEvent.VK_UP;
                }
            } else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                if (direccion != KeyEvent.VK_UP) {
                    direccion = KeyEvent.VK_DOWN;
                }
            } else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                if (direccion != KeyEvent.VK_RIGHT) {
                    direccion = KeyEvent.VK_LEFT;
                }
            } else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                if (direccion != KeyEvent.VK_LEFT) {
                    direccion = KeyEvent.VK_RIGHT;
                }
            }
        }

    }

    // CLASE MOMENTO ------------------------------------------------------------------------------------------------------->
    public class Momento extends Thread{
        long last = 0;

        public void run(){
            while (true) {
                if ((java.lang.System.currentTimeMillis() - last) > frecuencia) {

                    if (direccion == KeyEvent.VK_UP) {
                        snake.y = snake.y - widthPoint;
                        if (snake.y > height) {
                            snake.y = 0;
                        } 
                        if (snake.y < 0) {
                            snake.y = height - heightPoint;
                        }

                    } else if(direccion == KeyEvent.VK_DOWN){
                        snake.y = snake.y - heightPoint;
                        if (snake.y > height) {
                            snake.y = 0;
                        }

                        if (snake.y < 0) {
                            snake.y = height - heightPoint;
                        }
                    } else if(direccion == KeyEvent.VK_LEFT){
                        snake.x = snake.x - widthPoint;
                        if (snake.x > width) {
                            snake.x = 0;
                        }

                        if (snake.x < 0) {
                            snake.y = width - widthPoint;
                        }
                    } else if(direccion == KeyEvent.VK_RIGHT){
                        snake.x = snake.x - widthPoint;
                        if (snake.x > width) {
                            snake.x = 0;
                        }

                        if (snake.x < 0) {
                            snake.y = width - widthPoint;
                        }
                    } 
                        
                }

                    actualizar();
                    last = java.lang.System.currentTimeMillis();
            }
        }
    }
    
}

// 26:58