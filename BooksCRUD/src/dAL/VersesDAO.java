package dAL;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import transferObject.VersesTO;

public class VersesDAO implements iVersesDAO{
	private String file = "F:\\New folder (2)\\SCDProject\\src\\dAL\\Poem.txt";
	private String verseStart = "(";
	private String verseEnd = ")";
	private String verseSplit = "...";
	private String bookTitle = "الكتاب";
	private String bookContCheck = ":";
	private String poemtitleStart = "[";
	private String poemtitleEnd = "]";
	private String skipStartCheck = "_________";
	private String skipEndCheck = "==========";
	private String line;
	private boolean isBookAvailable;
	private static final String DB_URL = "jdbc:mysql://localhost:3306/kalimakitaab";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
	private boolean isInserted = false;

	@Override
	public int getPoemID(String PoemTitle) {
		String searchTitle = PoemTitle;
		String searchQuery = "SELECT Poem_Id FROM Poem WHERE Poem_Title = ?";
		int poem_id = 0;
		try (Connection connection = DriverManager.getConnection(this.DB_URL, this.DB_USER, this.DB_PASSWORD)) {
			PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
			preparedStatement.setString(1, searchTitle);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return -3;
			}
			else  
			{
				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	@Override
	public boolean insertPoemsintoDB() {
		String poemTitle = " ";
		int poemID=0;
		String insertQuery = "INSERT INTO verses(PoemID,Verse1,Verse2) VALUES (?, ?, ?)";
		String booktitle;
		StringTokenizer token;
		String tempString;
		String bookName = " ";
		// Read Data from File
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
			while ((line = reader.readLine()) != null) {
				// Checks if line contains check for BookTitle
				if (line.contains(bookTitle) && line.contains(bookContCheck)) {

				}
				// check if there is need to skip description
				else if (line == skipStartCheck) {
					while (((line = reader.readLine()) != skipEndCheck) && (line != null)) {
						continue;
					}
				}
				// checks for any line that is not part of poem
				else if ((line == skipStartCheck) || line == skipEndCheck) {
					continue;
				} else if (line.contains(poemtitleStart) && line.contains(poemtitleEnd)) {
					
					poemTitle = line.substring(0, line.length() - 3);
					poemID = getPoemID(poemTitle);
					if (poemID > 0) {

					} else if (poemID == -3) {
						String insertPoem = "INSERT INTO poem(Poem_Title) VALUES(?)";
						try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
							PreparedStatement preparedStatement = connection.prepareStatement(insertPoem);
						preparedStatement.setString(1,poemTitle);
							if (preparedStatement.execute() == true) {
								continue;
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					} else if (poemID == -1) {
						break;
					}
				}
				// this condition will Split the verse by verseSplit
				// and convert into String Builder if (line.contains(verseStart) &&
				// line.contains(verseEnd)) {
				else if (line.contains(verseStart) && line.contains(verseEnd)) {
					
					//int tempPoemId = getPoemID(poemTitle);
						String verse1, verse2;
						token = getSplittedString(
								line.substring((line.length() - line.length()) + 1, line.length() - 1), verseSplit);
						// StringBuilder verse = new StringBuilder();
						while (token.hasMoreTokens()) {
							verse2 = token.nextToken().toString();
							verse1 = token.nextToken().toString();
							try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
								PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
								preparedStatement.setInt(1, poemID);
								preparedStatement.setString(2, verse1);
								preparedStatement.setString(3, verse2);
								if (preparedStatement.executeUpdate() == 1) {
									// isInserted = true;
									continue;
								}

							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}

				}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return isBookAvailable;
	}
 @Override
	public ArrayList<VersesTO> fetchPoems() {
		String fetchQuery = "SELECT * FROM Poem p LEFT JOIN verses v ON p.Poem_id = v.PoemID";
		String PoemName;
		String Verse1;
		String Verse2;
		ArrayList<VersesTO> fetch = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			PreparedStatement preparedStatement = connection.prepareStatement(fetchQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				PoemName = resultSet.getString("Poem_title");
				Verse1 = resultSet.getString("Verse1");
				Verse2 = resultSet.getString("Verse2");
				fetch.add(getObject(PoemName, Verse1, Verse2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fetch;
	}


	public VersesTO getObject(String Poem, String Verse1, String Verse2) {
		return new VersesTO(Poem, Verse1, Verse2);
	}

	public StringTokenizer getSplittedString(String line, String del) {
		return new StringTokenizer(line, del);
	}

	public VersesDAO() {
		// TODO Auto-generated constructor stub
	}

}
/*
 * try { Connection connection = DriverManager.getConnection(DB_URL, DB_USER,
 * DB_PASSWORD); PreparedStatement preparedStatement =
 * connection.prepareStatement(queryToVerifyBook);
 * preparedStatement.setString(1, queryToVerifyBook); ResultSet resultSet =
 * preparedStatement.executeQuery();
 * 
 * } catch (SQLException obj) { obj.printStackTrace(); }
 */