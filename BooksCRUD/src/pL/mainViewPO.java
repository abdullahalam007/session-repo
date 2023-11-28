package pL;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class mainViewPO extends JFrame {

    private JButton Books;
    private JButton Poems;
    private JLabel Label;
    private JButton welcomeButton;
    private JButton Import_Poems;
    private JButton Roots;
    private JPanel Button_Panel;
    private JPanel mainPanel;
    private JPanel infoPanel;
    private BookPL book_View;
    private PoemView importPoems;
    private PoemView1 poems;

    private class BackgroundPanel extends JPanel {
        private Image background;

        public BackgroundPanel(String path) throws IOException {
            background = ImageIO.read(getClass().getResource(path));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }
   public mainViewPO()
   {
	   Label = new JLabel("موسوعة الشعر العربية في العصر الجاهلية");
	   welcomeButton = new JButton("Welcome");
	   welcomeButton.setBackground(new Color(0,150,200,0));
	   welcomeButton.setBorder(new LineBorder(Color.GREEN,2));
	   welcomeButton.setForeground(Color.WHITE);
	   infoPanel = new JPanel();
	   
	   Font customFont = new Font("Italic", Font.PLAIN, 32);
	   Label.setFont(customFont);
	   Label.setBorder(new LineBorder(Color.BLACK,3)); 
	   infoPanel.setLayout(new BorderLayout());
	   infoPanel.add(Label,BorderLayout.NORTH);
	   infoPanel.add(welcomeButton,BorderLayout.SOUTH);
	   infoPanel.setBackground(new Color(0,0,0,0));
	   
	   Button_Panel = new JPanel();
	   Button_Panel.setBackground(new Color(0,0,0,0));
	   Color color = new Color(0,150,200);
	Books = new JButton("Books");
	Books.setBackground(color);
	Books.setVisible(false);
	Books.setForeground(Color.WHITE);
	
	Poems = new JButton("Poems");
	Poems.setBackground(color);
	Poems.setVisible(false);
	Poems.setForeground(Color.WHITE);;
   
	Import_Poems = new JButton("Import Poems");
	Import_Poems.setVisible(false);
   Import_Poems.setBackground(color);
   Import_Poems.setForeground(Color.WHITE);

   
   Roots = new JButton("Roots");
   Roots.setVisible(false);
   Roots.setBackground(color);
   Roots.setForeground(Color.WHITE);

   Button_Panel.add(Books);
   Button_Panel.add(Poems);
   Button_Panel.add(Import_Poems);
   Button_Panel.add(Roots);
   try {
       setContentPane(new BackgroundPanel("background.jpg"));
   } catch (IOException e) {
       e.printStackTrace();
   }
   this.setTitle("موسوعة الشعر العربية في العصر الجاهلية");
   this.welcomeButton.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
           // Add the book_View (BookPL) to the contentPanel
          Books.setVisible(true);
          Roots.setVisible(true);
          Import_Poems.setVisible(true);
          Poems.setVisible(true);
       }
   });
   
   this.Books.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
           // Add the book_View (BookPL) to the contentPanel
           book_View = new BookPL();
           book_View.setVisible(true);
           book_View.setDefaultCloseOperation(HIDE_ON_CLOSE);
       }
   });
   this.Poems.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
           // Add the book_View (BookPL) to the contentPanel
           poems = new PoemView1();
           poems.setaction();
           //poems.setDefaultCloseOperation(HIDE_ON_CLOSE);
       }
   });
   this.Import_Poems.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
           // Add the book_View (BookPL) to the contentPanel
           importPoems = new PoemView();
           importPoems.setSize(550, 640);
           importPoems.setVisible(true);
           importPoems.setDefaultCloseOperation(HIDE_ON_CLOSE);
       }
   });
   this.setLayout(new BorderLayout());
   
   
   getContentPane().add(Button_Panel,BorderLayout.SOUTH);
   getContentPane().add(infoPanel,BorderLayout.CENTER);
   this.setVisible(true);
   this.setSize(600,400);
   
   }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                mainViewPO view = new mainViewPO();
                view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
    
}