package actual.Process;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBOperations {
	
	public static void InsertData(String Sno, String Name, String City, String Mobile, String Address)
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123456");
			PreparedStatement ps = con.prepareStatement("insert into profileData values(?,?,?,?,?)");
			ps.setInt(1, Integer.parseInt(Sno));
			ps.setString(2, Name);
			ps.setString(3, City);
			ps.setString(4, Mobile);
			ps.setString(5, Address);
			
			if(ps.executeUpdate()>0)
			{
				System.out.println(Sno+" Inserted Successfully");
			}
		} 
		catch (Exception e) 
		{
			System.out.println(Sno+" Failed to Insert");
		}
	}
	public static ResultSet GetData()
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123456");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from profileData");
			return rs;
		} 
		catch (Exception e) {
			return null;
		}
	}
}
