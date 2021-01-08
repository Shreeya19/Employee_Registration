package dbConnect;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	static Connection con = null;
	public static Connection connect()
	{
		try
		{
			if(con == null)
			{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Driver Loaded...");
				
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","");
				System.out.println("Connection Established...");
			}
		}
		catch(Exception e)
		{
			System.err.println(e);
			e.printStackTrace();
		}
		return con;
	}
}
