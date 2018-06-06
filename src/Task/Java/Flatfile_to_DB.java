package Task.Java;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Flatfile_to_DB {
	
	public static ArrayList<Details> getListFromTextFile(String filepath)
	{
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		ArrayList<Details> listResult = new ArrayList<Details>();
		try 
		{
			fis = new FileInputStream(filepath);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			
			//To Save the line which we get from the Text File
			String line = null;
			//An Array To Save Details
			String[] strDetails = null;
			//Loop to get all the details from the text file
			while (true) 
			{	//Check the First Line
				line = br.readLine();
				//To check if the is Empty, and exit loop
				if (line == null) 
				{
					break;					
				} 
				else 
				{
					strDetails = line.split("//|");
					listResult.add(new Details(Integer.parseInt(strDetails[0]), strDetails[1], strDetails[2], strDetails[3], strDetails[4]));
				}
				
			}
		} 
		catch (Exception e) {
			System.out.println("Read Fiile Error");
			e.printStackTrace();
		}
		return listResult;
		
	}

	public static void main(String[] args) {
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loaded Successfully");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123456");
			String query = "insert into Details values(?,?,?,?,?)";
			
			PreparedStatement ps = con.prepareStatement(query);
			//Get the list from text file Details.txt
			
			ArrayList<Details> listDetaiils = getListFromTextFile("C:\\Flat_File\\abc.txt");
			for (int i = 0; i < listDetaiils.size(); i++)
			{
				ps.setInt(1, listDetaiils.get(i).getSno());
				ps.setString(2, listDetaiils.get(i).getName());
				ps.setString(3, listDetaiils.get(i).getCity());
				ps.setString(4, listDetaiils.get(i).getMobile());
				ps.setString(5, listDetaiils.get(i).getAddress());
				
				ps.executeUpdate();
				System.out.println("Number of Records Inserted:"+(i+1)); 
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

}
