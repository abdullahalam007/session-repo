package dAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDAL implements IBookDAO {
	final String DataBase_URL = "jdbc:mysql://localhost:3306/kalimakitaab";
	final String DataBase_USER = "root";
	final String DataBase_PASSWORD = "";

	public void AddBook(String str1, String str2, String str3) {
		String sqlInsertQuery = "INSERT INTO books(Title,Author,Author_details) VALUES (?,?,?)";
		try {
			Connection connection = DriverManager.getConnection(DataBase_URL, DataBase_USER, DataBase_PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertQuery);
			preparedStatement.setString(1, str1);
			preparedStatement.setString(2, str2);
			preparedStatement.setString(3, str3);
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Insertion successful " + rowsAffected);
			} else {
				System.out.println("Insertion failed.");
			}
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String SelectBook() {
		String sqlSelectQuery = "SELECT * FROM books";
		try {
			Connection connection = DriverManager.getConnection(DataBase_URL, DataBase_USER, DataBase_PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectQuery);
			ResultSet Rs = preparedStatement.executeQuery();
			while (Rs.next()) {
				int num = Rs.getInt("ID");
				String str = Rs.getString("Title");
				String str1 = Rs.getString("Author");
				Date dat = Rs.getDate("Author_details");
				System.out.println("The Book ID is = " + num + " The Title of the book is = " + str
						+ " The name of the author is = " + str1 + " The death date of the author is  = " + dat);
			}
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sqlSelectQuery;
	}

	public int searchBookID(String BookName)
	{
		int id = 0;
		String Temp;
		String searchBookQuery= "SELECT ID FROM book WHERE Title = ?";
		try(Connection connection = DriverManager.getConnection(DataBase_URL, DataBase_USER, DataBase_PASSWORD))
		{
			PreparedStatement preparedStatement = connection.prepareStatement(searchBookQuery);
		    preparedStatement.setString(1, BookName);
		    ResultSet resultSet = preparedStatement.executeQuery();
		    if(resultSet.isFirst())
		    {
		    	while(resultSet.next())
		    	{
		    		Temp = resultSet.getString(2);
		    		if(Temp == BookName)
		    		{
		    			id = resultSet.getInt(1);
		    			return id;
		    		}
		    	}
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
		return -1;
	}
	public void updateBook(String str1, String str2, String str3) {
		int id= searchBookID(str1);
		if(id>=0)
		{
		String sqlUpdateQuery = "UPDATE books SET Title = ? WHERE ID = ?";
		try {
			Connection connection = DriverManager.getConnection(DataBase_URL, DataBase_USER, DataBase_PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdateQuery);
			preparedStatement.setString(1, str1);
			preparedStatement.setString(2, str2);
			preparedStatement.setString(3, str3);
			int Rs = preparedStatement.executeUpdate();
			if (Rs > 0) {
				System.out.println("The query has been sucessfully executed");
			} else {
				System.out.println("The query has been not sucessfully executed");
			}
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}

	@Override
	public void deleteBook(String str1) {
		String sqlDeleteQuery = "DELETE FROM books WHERE Title = ?";
		try {
			Connection connection = DriverManager.getConnection(DataBase_URL, DataBase_USER, DataBase_PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteQuery);
			preparedStatement.setString(1, "book20");
			int Rs = preparedStatement.executeUpdate();
			if (Rs > 0) {
				System.out.println("The query has been successfully executed");
			} else {
				System.out.println("The query has not been successfully executed");
			}
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	@Override
	public boolean searchBook(String str1)
	{
		String sqlsearchQuery = "SELECT * FROM books WHERE Title = ?";
		try
		{
			Connection connection = DriverManager.getConnection(DataBase_URL, DataBase_USER, DataBase_PASSWORD);
			PreparedStatement preparedStatement = connection.prepareStatement(sqlsearchQuery);
			preparedStatement.setString(1, str1);
            ResultSet rs;
            rs = preparedStatement.executeQuery();
            
            if (!rs.isBeforeFirst()) {
                return false;
            } else {
                while (rs.next()) {
                	String temp;
                	temp = rs.getString("Title");
                	
                	if(temp == str1)
                	{
                		return true;
                	}                	
                    return true;
                }
            }
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
			
		}
		return false;
		
	}

}
