package pL;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bLL.BLLFascade;
import bLL.BookBLL;

public class BookPL extends JFrame {
	private static final long serialVersionUID = -1715101882915748261L;
	private JTextField bookNameField, AuthorNameField, AuthorDeathField;
	private JTextArea resultArea;
	private BLLFascade pbookfetcher;

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

	public BookPL() {
		setTitle("Books");
		setSize(700, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		pbookfetcher = new BLLFascade(new BookBLL());

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(new Color(0, 0, 0, 0));

		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new FlowLayout());
		inputPanel.setBackground(new Color(0, 0, 0, 0));

		JLabel BookNameLabel = new JLabel("Enter Book Name ");
		BookNameLabel.setBackground(new Color(0, 0, 0, 0));
		JLabel AuthorNameLabel = new JLabel("Enter the author name ");
		AuthorNameLabel.setBackground(new Color(0, 0, 0, 0));
		JLabel AuthorDeathLabel = new JLabel("Enter the death date ");
		AuthorDeathLabel.setBackground(new Color(0, 0, 0, 0));
		bookNameField = new JTextField(20);
		AuthorNameField = new JTextField(20);
		AuthorDeathField = new JTextField(20);
		JButton AddButton = new JButton("Add book");
		AddButton.setBackground(new Color(0, 150, 200));
		JButton UpdateButton = new JButton("Update book");
		UpdateButton.setBackground(new Color(0, 150, 200));
		JButton DeleteButton = new JButton("Delete book");
		DeleteButton.setBackground(new Color(0, 150, 200));
		JButton DisplayButton = new JButton("Display book");
		DisplayButton.setBackground(new Color(0, 150, 200));

		UpdateButton.setForeground(Color.WHITE);
		AddButton.setForeground(Color.WHITE);
		DeleteButton.setForeground(Color.WHITE);
		DisplayButton.setForeground(Color.WHITE);

		inputPanel.add(BookNameLabel);
		inputPanel.add(bookNameField);
		inputPanel.add(AuthorNameLabel);
		inputPanel.add(AuthorNameField);
		inputPanel.add(AuthorDeathLabel);
		inputPanel.add(AuthorDeathField);
		inputPanel.add(AddButton);
		inputPanel.add(UpdateButton);
		inputPanel.add(DeleteButton);
		inputPanel.add(DisplayButton);
		Font customFont = new Font("Berlin Sans FB", Font.ITALIC, 14);
		BookNameLabel.setFont(customFont);
		AuthorNameLabel.setFont(customFont);
		AuthorDeathLabel.setFont(customFont);
		BookNameLabel.setOpaque(true);
		BookNameLabel.setForeground(Color.WHITE);
		AuthorNameLabel.setOpaque(true);
		AuthorNameLabel.setForeground(Color.WHITE);
		AuthorDeathLabel.setOpaque(true);
		AuthorDeathLabel.setForeground(Color.WHITE);

		try {
			setContentPane(new BackgroundPanel("background.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		resultArea = new JTextArea(10, 30);
		resultArea.setEditable(true);

		mainPanel.add(inputPanel, BorderLayout.NORTH);
		getContentPane().add(mainPanel);

		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

		AddButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String str1, str2, str3;

				str1 = bookNameField.getText();
				str2 = AuthorNameField.getText();
				str3 = AuthorDeathField.getText();
				pbookfetcher.addBook(str1, str2, str3);
			}

		});
		UpdateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (pbookfetcher.searchBook(bookNameField.getText())) {

					setTitle("Update Book Information");
					setSize(400, 300);
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
					panel.setOpaque(false);

					Font customFont = new Font("Berlin Sans FB", Font.ITALIC, 14);

					JLabel CurrBookNameLabel = new JLabel("Current book name ");
					JTextField CurrBookNameTextField = new JTextField();
					JLabel CurrAuthorNameLabel = new JLabel("Current Author name ");
					JTextField CurrAuthorNameTextField = new JTextField();
					JLabel CurrDeathDateLabel = new JLabel("Current Death date ");
					JTextField CurrDeathDateTextField = new JTextField();

					JLabel newBookNameLabel = new JLabel("New book name ");
					JTextField newBookNameTextField = new JTextField();
					JLabel newAuthorNameLabel = new JLabel("New Author name ");
					JTextField newAuthorNameTextField = new JTextField();
					JLabel newDeathDateLabel = new JLabel("New Death date ");
					JTextField newDeathDateTextField = new JTextField();

					CurrBookNameLabel.setFont(customFont);
					CurrBookNameTextField.setFont(customFont);
					CurrAuthorNameLabel.setFont(customFont);
					CurrAuthorNameTextField.setFont(customFont);
					CurrDeathDateLabel.setFont(customFont);
					CurrDeathDateTextField.setFont(customFont);

					newBookNameLabel.setFont(customFont);
					newBookNameTextField.setFont(customFont);
					newAuthorNameLabel.setFont(customFont);
					newAuthorNameTextField.setFont(customFont);
					newDeathDateLabel.setFont(customFont);
					newDeathDateTextField.setFont(customFont);

					CurrBookNameLabel.setForeground(Color.BLACK);
					CurrAuthorNameLabel.setForeground(Color.BLACK);
					CurrDeathDateLabel.setForeground(Color.BLACK);
					newBookNameLabel.setForeground(Color.BLACK);
					newAuthorNameLabel.setForeground(Color.BLACK);
					newDeathDateLabel.setForeground(Color.BLACK);

					JButton updateData = new JButton();

					updateData.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {

							
							
						}

					});

					panel.add(updateData);

					panel.add(CurrBookNameLabel);
					panel.add(CurrBookNameTextField);
					panel.add(newBookNameLabel);
					panel.add(newBookNameTextField);

					panel.add(CurrAuthorNameLabel);
					panel.add(CurrAuthorNameTextField);
					panel.add(newAuthorNameLabel);
					panel.add(newAuthorNameTextField);

					panel.add(CurrDeathDateLabel);
					panel.add(CurrDeathDateTextField);
					panel.add(newDeathDateLabel);
					panel.add(newDeathDateTextField);

					panel.setAlignmentX(Component.CENTER_ALIGNMENT);
					panel.setAlignmentY(Component.CENTER_ALIGNMENT);

					add(panel);
					setVisible(true);
				}
			}
		});

		DeleteButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String strin1, strin2, strin3;

				strin1 = bookNameField.getText();
				strin2 = AuthorNameField.getText();
				strin3 = AuthorDeathField.getText();
				pbookfetcher.deleteBook();

			}

		});

	}

}
