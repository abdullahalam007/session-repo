package pL;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PoemView1 extends JFrame{
    private List<String> poems;
    private JFrame mainFrame;
    private JTextArea poemsTextArea;
    private PoemView importPoems;

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

    public void setaction()
    {
    	mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    public PoemView1() {
        poems = new ArrayList<>();

        
        mainFrame = new JFrame("موسوعة الشعر العربية في العصر الجاهلية");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);

        try {
            setContentPane(new BackgroundPanel("background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        
        JPanel buttonPanel = new JPanel();
       buttonPanel.setBackground(new Color(0,0,0,0));
        mainFrame.add(buttonPanel, BorderLayout.NORTH);

        JButton addPoemButton = new JButton("Add Poem");
        addPoemButton.setBackground(new Color(0,150,200));
        addPoemButton.setForeground(Color.WHITE);
        JButton importPoemsButton = new JButton("Import Poems");
        importPoemsButton.setBackground(new Color(0,150,200));
        importPoemsButton.setForeground(Color.WHITE);
        JButton editPoemButton = new JButton("Edit Poem");
        editPoemButton.setBackground(new Color(0,150,200));
        editPoemButton.setForeground(Color.WHITE);

        buttonPanel.add(addPoemButton);
        buttonPanel.add(importPoemsButton);
        buttonPanel.add(editPoemButton);

        poemsTextArea = new JTextArea(20, 40);
        poemsTextArea.setBackground(new Color(0,0,0,0));
        poemsTextArea.setLineWrap(true);
        poemsTextArea.setWrapStyleWord(true);
        poemsTextArea.setEditable(false);

        mainFrame.add(new JScrollPane(poemsTextArea), BorderLayout.CENTER);

        addPoemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openAddPoemWindow(null);
            }
        });

        importPoemsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement importing poems logic
            	importPoems = new PoemView();
            	importPoems.setVisible(true);
            	importPoems.setSize(550,640);
            	importPoems.setDefaultCloseOperation(HIDE_ON_CLOSE);
            }
        });

        editPoemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedPoem = getSelectedPoem();
                if (selectedPoem != null) {
                    poems.remove(selectedPoem); // Remove the selected poem
                    openAddPoemWindow(selectedPoem);
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Please select a poem to edit.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        mainFrame.setVisible(true);
    }

    private String getSelectedPoem() {
        int selectedStart = poemsTextArea.getSelectionStart();
        int selectedEnd = poemsTextArea.getSelectionEnd();

        if (selectedStart >= 0 && selectedEnd > selectedStart) {
            return poemsTextArea.getText().substring(selectedStart, selectedEnd);
        }
        return null;
    }

 // ...
 // ...
    private void openAddPoemWindow(String poemToEdit) {
        JFrame addFrame = new JFrame("Add Poem");
        addFrame.setSize(600, 400);
        addFrame.setLocationRelativeTo(mainFrame);

        JPanel addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));

        addFrame.add(new JScrollPane(addPanel), BorderLayout.CENTER);

        JPanel textFieldsPanel = new JPanel();
        textFieldsPanel.setLayout(new BoxLayout(textFieldsPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField();
        titleField.setPreferredSize(new Dimension(200, 25)); // Set the preferred size

        textFieldsPanel.add(titleLabel);
        textFieldsPanel.add(titleField);

        List<JTextField> misraFields = new ArrayList<>();
        JTextField misraField1 = new JTextField();
        misraField1.setPreferredSize(new Dimension(400, 25)); // Set the preferred size
        JTextField misraField2 = new JTextField();
        misraField2.setPreferredSize(new Dimension(400, 25)); // Set the preferred size

        textFieldsPanel.add(new JLabel("Misra 1:"));
        textFieldsPanel.add(misraField1);
        misraFields.add(misraField1);

        textFieldsPanel.add(new JLabel("Misra 2:"));
        textFieldsPanel.add(misraField2);
        misraFields.add(misraField2);

        addPanel.add(textFieldsPanel);

        JPanel buttonsPanel = new JPanel();
        JButton addMisraButton = new JButton("+");
        JButton saveButton = new JButton("Save");

        buttonsPanel.add(addMisraButton);
        buttonsPanel.add(saveButton);

        addPanel.add(buttonsPanel);

    
        addMisraButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField newMisraField = new JTextField();
                misraFields.add(newMisraField);
                textFieldsPanel.add(newMisraField);
                addFrame.revalidate();
                addFrame.repaint();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                StringBuilder poemBuilder = new StringBuilder(title);
                boolean emptyMisra = false;

                for (JTextField misraField : misraFields) {
                    String misra = misraField.getText();
                    if (!misra.isEmpty()) {
                        poemBuilder.append("\n").append(misra);
                    } else {
                        emptyMisra = true;
                    }
                }

                if (emptyMisra) {
                    JOptionPane.showMessageDialog(addFrame, "Misra field cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    // Add a line of underscores to differentiate poems
                    poemBuilder.append("\n______");
                    String poem = poemBuilder.toString();
                    poems.add(poem);

                    // Clear the text fields
                    titleField.setText("");
                    for (JTextField misraField : misraFields) {
                        misraField.setText("");
                    }

                    // Remove dynamically added Misra text fields
                    for (int i = misraFields.size() - 1; i >= 2; i--) {
                        JTextField fieldToRemove = misraFields.get(i);
                        textFieldsPanel.remove(fieldToRemove);
                        misraFields.remove(i);
                    }

                    // Refresh the poems text area
                    refreshPoemsTextArea();

                    addFrame.revalidate();
                    addFrame.repaint();
                }
            }
        });

        if (poemToEdit != null) {
            // Load the poem for editing
            String[] poemLines = poemToEdit.split("\n");
            if (poemLines.length > 0) {
                titleField.setText(poemLines[0]);
            }
            for (int i = 1; i < poemLines.length; i++) {
                JTextField misraField = new JTextField(20);
                misraField.setText(poemLines[i]);
                misraFields.add(misraField);
                textFieldsPanel.add(misraField);
                addFrame.revalidate();
                addFrame.repaint();
            }
        }

        addFrame.setVisible(true);
    }

    private void refreshPoemsTextArea() {
        poemsTextArea.setText("");
        for (String poem : poems) {
            poemsTextArea.append(poem + "\n\n");
        }
    }

    /*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PoemView();
        });
    }
    */
}
