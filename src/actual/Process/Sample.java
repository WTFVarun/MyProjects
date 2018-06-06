package actual.Process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.util.Scanner;

public class Sample {

	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		System.out.println("1.Upload File\n2.Generate Fiile\nEnter your Choice");
		int choice = s.nextInt();
		switch (choice) 
		{
		case 1:
			System.out.println("Enter FilePath:");
			String sPath = s.next();
			ReadFlatFile(sPath);
			break;
			
		case 2:GenerateFlatFile();			
			break;

		default: System.out.println("Wrong Input");
		}
	}
	public static void ReadFlatFile(String fPath) throws IOException
	{
		String line="";
		BufferedReader br= null;
		try 
		{
			br = new BufferedReader(new FileReader(fPath)); 
			while ((line = br.readLine())!=null) 
			{
				String[] data = line.split("\\|");
				DBOperations.InsertData(data[0], data[1], data[2], data[3], data[4]);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally 
		{
			if(br!=null)
			{
				try 
				{
					br.close();
				} 
				catch (IOException e2) 
				{
					e2.printStackTrace();
				}
			}
		}
	}
	public static void GenerateFlatFile()
	{
		try 
		{
			String OutPath = "E://Nikhil//output.txt";
			int rows = 0;
			ResultSet rs = DBOperations.GetData();
			File f1 = new File(OutPath);
			if(!f1.exists())
			{
				f1.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(OutPath);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write("Sno|Name|City|Mobile|Address");
			bw.newLine();
			while (rs.next()) 
			{
				bw.write(rs.getString(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5));
				bw.newLine();
				rows++;
			}
			bw.close();
			System.out.println(rows+" Rows Exported Successfully....!");
			System.out.println("Please find the file under path "+OutPath);
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
			System.out.println("Failed to Export");
		}
	}

}
