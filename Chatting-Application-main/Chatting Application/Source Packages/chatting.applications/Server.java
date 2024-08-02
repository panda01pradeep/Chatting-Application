import java.awt.*;
import javax.swing.*;


   
    public class Server extends JFrame{
        
         // Constructor
        Server() {
           setSize(450, 700);
           setVisible(true);
           setLocation(200,50);
           getContentPane().setBackground(Color.WHITE);
        }
        // main method
        public static void main(String args[]){
            // This is a anayomous object
            new Server();

        }
    }
