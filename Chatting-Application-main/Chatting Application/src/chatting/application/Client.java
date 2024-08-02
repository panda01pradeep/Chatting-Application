package chatting.application;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
// calendar package ke liye
import java.util.*;
// text msg ke liye
import java.text.*;
import java.net.*;
import java.io.*;
 
    public class Client implements ActionListener {
        
        //JTextFeild text -> globally declare, constructor ke aandar bhi use kr paye
        JTextField text;
        
        //globally declare kr diya
        static JPanel a1;
        
        // msg ko ek ke niche nixhe place krna h
        static Box vertical = Box.createVerticalBox();
        
        static JFrame f = new JFrame();
        static DataOutputStream dout;
        
        // Constructor
        Client() {
           f.setLayout(null);
           
           
           JPanel p1 = new JPanel();
           p1.setBackground(new Color(7,94,84));
           p1.setBounds(0, 0, 450, 70);
           p1.setLayout(null);
           f.add(p1);
           
           // file is much from inside the directory - 
           ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
           Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
           ImageIcon i3 = new ImageIcon(i2);          
           JLabel back = new JLabel(i3);
           back.setBounds(5, 20, 25, 25);
           p1.add(back);
           
           //back button ke click pe action chaiyea
           //mujhe fame se back hone ke liye
           back.addMouseListener(new MouseAdapter() {
               public void mouseClicked(MouseEvent ae) {
                   System.exit(0);
               }
           });
           
           ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));
           Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
           ImageIcon i6 = new ImageIcon(i5);          
           JLabel profile = new JLabel(i6);
           profile.setBounds(40,10,50,50);
           p1.add(profile);
           
           ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
           Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
           ImageIcon i9 = new ImageIcon(i8);          
           JLabel video = new JLabel(i9);
           video.setBounds(300, 20, 30, 30);
           p1.add(video);
           
           ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
           Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
           ImageIcon i12 = new ImageIcon(i11);          
           JLabel phone = new JLabel(i12);
           phone.setBounds(360, 20, 30, 30);
           p1.add(phone);
           
           ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
           Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
           ImageIcon i15 = new ImageIcon(i14);          
           JLabel morevert = new JLabel(i15);
           morevert.setBounds(420, 20, 10, 25);
           p1.add(morevert);
           
           JLabel name = new JLabel("Sinha mam");
           name.setBounds(110, 15, 100, 18);
           name.setForeground(Color.WHITE);
           name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
           p1.add(name);
           
           JLabel status = new JLabel("Active Now");
           status.setBounds(110, 35, 100, 18);
           status.setForeground(Color.WHITE);
           status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
           p1.add(status);
           
           // ye ek alag panel h it means Chatting box
           a1 = new JPanel();
           a1.setBounds(5, 75, 440, 530);
           f.add(a1);
                  
           text = new JTextField();
           text.setBounds(5, 615, 310, 40);
           text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
           f.add(text);
           
           JButton send = new JButton("Send");
           send.setBounds(320, 615, 123, 40);
           send.setBackground(new Color(7, 94, 84));
           send.setForeground(Color.WHITE);
           
           //send button pe click pe action chaiyea
           send.addActionListener(this);
           text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
           f.add(send);
           
           // frame ka header htane ke liye - undecorated used
           f.setUndecorated(true);                                 
           f.setSize(450, 660);
           f.setLocation(800,50);
           f.getContentPane().setBackground(Color.WHITE);
           
           f.setVisible(true);
        }
        
        public void actionPerformed(ActionEvent ae) {
            try {
                //text feild ke aandar se value nikalna h
                 String out = text.getText();

                // eske jarurat nhi h->  JLabel output = new JLabel(out);

                 // yaha pe sidhe formatLabel le skta huit means cl kr skta hu
                 // eske jagah
                 // JPanel p2 = new JPanel();
                 JPanel p2 = formatLabel(out);

                 // border layout works on elements place krta h
                 // top bottom left right center
                 a1.setLayout(new BorderLayout());

                 JPanel right = new JPanel(new BorderLayout());

                 // p2 ke jagah hm String nhi le skte eseliye p2 liye h
                 // so p2 ko ab declare krna h
                 right.add(p2, BorderLayout.LINE_END);

                 //vertical msg h tb ek ke niche ek
                 vertical.add(right);

                // Strut -> components are separated by a fixed amount of space, without relying on margins, padding
                // space 15
                 vertical.add(Box.createVerticalStrut(15));

                // page strt me msg strt hoga
                a1.add(vertical, BorderLayout.PAGE_START);

                // msg ko send krna h
                // yaha pe aap msg receive kro ge but msg client ko send bhi to kro ge 
                dout.writeUTF(out);

                // text feild empty ho jae ga type krna ke baad
                text.setText("");

                // repaint means frame ko cl kre ga
                f.repaint();
                f.invalidate();
                f.validate();  
            } catch  (Exception e) {
                e.printStackTrace();
            }
        }
        
        // JPanel ko return kra rha hu
        public static JPanel formatLabel(String out) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
            output.setFont(new Font("Tahoma", Font.PLAIN, 16));
            output.setBackground(new Color(37, 211, 102));
            output.setOpaque(true);
            output.setBorder(new EmptyBorder(15, 15, 15, 50));

            panel.add(output);
            
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            
            // frame ke aandar kuch likhna h tb JLabel ke aandar
            JLabel time = new JLabel();
            time.setText(sdf.format(cal.getTime()));
            
            panel.add(time);
            
            return panel;
        }
        
        // main method
        public static void main(String[] args){
            // This is a anayomous object
            new Client();
            
            try {
                Socket s = new Socket("127.0.0.1", 6001);
                 DataInputStream din = new DataInputStream(s.getInputStream());
                 dout = new DataOutputStream(s.getOutputStream());
                 
                while(true) {
                    // es msg ko kaha pe dikhana h
                    a1.setLayout(new BorderLayout());
                    
                    //msg ko read krna h tb 
                     String msg = din.readUTF();
                     JPanel panel = formatLabel(msg);

                     // ye sb received msg h
                     JPanel left = new JPanel (new BorderLayout());
                     left.add(panel, BorderLayout.LINE_START);

                     // main fn static fn h
                     // main static fn ke aandar non static fn ko cl krna galat h
                     // vertical non static h to upar me box ko static declare krna pde ga
                     vertical.add(left);
                     
                     // msg ko kaha pe add krna h..
                     
                     //frame ko refresh krne ke liye validate use krna pde ga
                     f.validate();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
