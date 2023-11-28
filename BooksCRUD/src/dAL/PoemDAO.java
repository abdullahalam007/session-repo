package dAL;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PoemDAO implements iPoemDAO{
    private File databaseFile;
    private List<String> poems;  // Storing poems in memory for this example
	private static final String DB_URL = "jdbc:mysql://localhost:3306/kalimakitaab";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";
    public PoemDAO() {
        // Initialize the database file or connect to a database
        this.poems = new ArrayList<>();
    }
@Override
    public void addPoem(String title, List<String> verses) {
        // Save the poem to the database or file (for this example, we are storing poems in memory)
       try(Connection connection = DriverManager.getConnection(title))
       {
    	   
       }
       catch(SQLException e )
       {
    	   
       }
       StringBuilder poemBuilder = new StringBuilder(title);
        for (String verse : verses) {
            poemBuilder.append("\n").append(verse);
        }
        poems.add(poemBuilder.toString());
    }
@Override
    public void editPoem(String oldTitle, String newTitle, List<String> verses) {
        // Edit the poem in the database or file (for this example, we are updating poems in memory)
       int poem_id = getID(oldTitle);
    	StringBuilder updatedPoem = new StringBuilder(newTitle);
        for (String verse : verses) {
            updatedPoem.append("\n").append(verse);
        }
        int index = poems.indexOf(oldTitle);
        if (index != -1) {
            poems.set(index, updatedPoem.toString());
        }
    }

    public int getID(String title)
    {
    	String searchQuery = "SELECT * FROM poem WHERE Poem_Title = ?";
    	try(Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD))
    	{
    		PreparedStatement prepareStatement = connection.prepareStatement(title);
            prepareStatement.setString(2, searchQuery);
    		ResultSet resultSet = prepareStatement.executeQuery();
            if(resultSet.next())
            {
            	return resultSet.getInt(0);
            	
            }
            else
            {
             return -3;
            }
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    	return -3;
    }
@Override
    public void deletePoem(String title) {
        // Delete the poem from the database or file (for this example, we are removing poems from memory)
       int poem_id = getID(title);
    	poems.remove(title);
    }

    public List<String> getAllPoems() {
        // Fetch all poems from the database or file (in this example, we return the in-memory list)
        return poems;
    }
}

