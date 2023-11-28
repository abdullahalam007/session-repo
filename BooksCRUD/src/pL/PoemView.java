/*
package pL;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bLL.verseFetcherBO;

public class PoemView extends JFrame{

	private DefaultTableModel verseTable;
	private verseFetcherBO fetchVerse;
	private JLabel bookLabel;
	private JLabel poemLabel;
	private JTable jTable;
	private JButton importFiles;
	private JButton displayVerses;
	private JTextField bookName;
	private JTextField poemName;
	private JPanel buttonPanel; 
	private JPanel displayInfoPanel;
	private JScrollPane scroll;
	Object[] Columns= {"PoemName","Verse1","Verse2"};
	Object[][]dummyData= {{"-"},{"-"},{"-"}};
	public PoemView() 
	{
		verseTable = new DefaultTableModel(dummyData, Columns) ;
		jTable = new JTable(verseTable);
		importFiles = new JButton("Import Poems");
		displayVerses = new JButton("Display Verses");
		displayInfoPanel = new JPanel();
		bookLabel = new JLabel("Book Name");
		poemLabel = new JLabel("Poem Name");
		
		scroll = new JScrollPane();
		scroll.add(jTable);
		bookName = new JTextField(10);
		poemName = new JTextField(10);
		
	
		displayInfoPanel.add(bookLabel);
		displayInfoPanel.add(bookName);
		displayInfoPanel.add(poemLabel);
		displayInfoPanel.add(poemName);
		
		buttonPanel = new JPanel();
		buttonPanel.add(importFiles);
		buttonPanel.add(displayVerses);
		setLayout(new BorderLayout());
	
		this.add(displayInfoPanel,BorderLayout.NORTH);
		this.add(scroll,BorderLayout.CENTER);
		this.add(buttonPanel,BorderLayout.SOUTH);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		importFiles.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}
			
				});
		
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		PoemView UI = new PoemView();
		UI.setVisible(true);
		UI.setSize(300, 300);
	}

}
*/
package pL;
//pL
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import bLL.BLLFascade;
import bLL.verseFetcherBO;
import transferObject.VersesTO;
public class PoemView extends JFrame {

	private bLL.BLLFascade bl;
    private DefaultTableModel verseTable;
    private boolean animationInProgress = false;
    private JLabel bookLabel;
    private JLabel poemLabel;
    private JTable jTable;
    private JButton importFiles;
    private JButton displayVerses;
    private JTextField bookName;
    private JTextField poemName;
    private JPanel buttonPanel;
    private JPanel displayInfoPanel;
    JPanel centerPanel;
    Object[] Columns = {"PoemName", "Verse1", "Verse2"};
    Object[][] dummyData = {{"-", "-", "-"}};

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

    public PoemView() 
    {
    	bl=new BLLFascade(new verseFetcherBO());
    	Color buttonColor = new Color(0,180,200);
    	this.setTitle("KalimahKitaab");
    	//this.setTitleBarOpacity(0.0f);
        verseTable = new DefaultTableModel(dummyData, Columns);
        jTable = new JTable(verseTable);
       // jTable.setBackground(new Color(0,0,0,0));
        
        bookLabel = new JLabel("Book Name:");
        bookLabel.setBackground(new Color(0,0,0,0));
        bookLabel.setForeground(new Color(0,120,200));
        poemLabel = new JLabel("Poem Name:");
        
        bookName = new JTextField(15);
        bookName.setBackground(new Color(0,0,0,0));
        poemName = new JTextField(15);

        importFiles = new JButton("Import Poems");
        displayVerses = new JButton("Display Verses");

        importFiles.setBackground(buttonColor);
        importFiles.setForeground(Color.WHITE);
        displayVerses.setBackground(buttonColor);
        displayVerses.setForeground(Color.WHITE);
        
        
        displayInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        displayInfoPanel.setOpaque(false); // Make the panel transparent
        displayInfoPanel.add(bookLabel);
        displayInfoPanel.add(bookName);
        //displayInfoPanel.add(poemLabel);
        //displayInfoPanel.add(poemName);
        // Create separate BoxLayout instances for the buttons
        buttonPanel = new JPanel();
       // buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false); // Make the panel transparent
        buttonPanel.add(importFiles);
        buttonPanel.add(displayVerses);

        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);
        leftPanel.setBackground(new Color(0,0,0,0));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setOpaque(false); // Make the panel transparent
        topPanel.add(displayInfoPanel);
        
        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
       //centerPanel.setOpaque(false); // Make the panel transparent
        centerPanel.add(new JScrollPane(jTable));
        centerPanel.setBackground(new Color(0,0,0,0));
        //centerPanel.setForeground(buttonColor);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(leftPanel, BorderLayout.SOUTH);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.setBackground(new Color(0,0,0,0));
        // Set the content pane to a custom JPanel with a background image
 
        try {
            setContentPane(new BackgroundPanel("background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add the main panel to the custom JPanel
        getContentPane().add(mainPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
 
        importFiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	bl.insertIntoDB();
            	/*ArrayList<VersesTO>verses = new ArrayList<>();
           
                // TODO: Handle the action when the "Import Poems" button is clicked.
            verses=bl.fetchPoems();
           for(int i=0;i<verses.size();++i)
           {
        	   verseTable.addRow(getObjectArray(verses.get(i)));
           }
           */
            /*JTextField errorMsg = new JTextField(10);
            	JFrame popup_msg = new JFrame();
            	popup_msg.add(errorMsg,BorderLayout.CENTER);
            	popup_msg.setBackground(new Color(0,0,0,0));
            	popup_msg.setSize(300,300);
            	popup_msg.setTitle("Error Msg");
            	popup_msg.setVisible(true);
            	*/
            	
            }
        });
        displayVerses.addActionListener(new ActionListener()
        		{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						ArrayList<VersesTO>fetchPoems = bl.fetchPoems();
						for(int i=0;i<fetchPoems.size();++i)
						{
							verseTable.addRow(getObjectArray(fetchPoems.get(i)));
						}
					}
        	
        		});
        }
    public Object[] getObjectArray(VersesTO temp)
    {
    	Object [] tempObject = {temp.getPoemTitle(),temp.getVerse1(),temp.getVerse2()};
    	return tempObject;

    }
    /*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PoemView UI = new PoemView();
            UI.pack();
            UI.setVisible(true);
        });
    }
    */
}
