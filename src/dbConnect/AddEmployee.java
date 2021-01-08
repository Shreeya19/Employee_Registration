package dbConnect;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddEmployee
 */
@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		try
		{
			int empid = Integer.parseInt(request.getParameter("empid"));
			String name = request.getParameter("name");
			String contact = request.getParameter("contact");
			String email = request.getParameter("email");
			String domain = request.getParameter("domain");
			
			Connection con = Connect.connect();
			
			PreparedStatement ps = con.prepareStatement("select * from employees where empid='"+empid+"'");
			ResultSet r = ps.executeQuery();
			if(r.next())
			{
				request.getSession().setAttribute("msg", "Duplicate ID, Records Already Exist..!!");
        		response.sendRedirect("index.html"); 
			}
			else
			{
				PreparedStatement ps2 = con.prepareStatement("insert into employees values(?,?,?,?,?)");
				ps2.setInt(1,empid);
				ps2.setString(2,name);
				ps2.setString(3,contact);
				ps2.setString(4,email);;
				ps2.setString(5,domain);
				
				int n = ps2.executeUpdate();
				System.out.println("Record inserted");
				
				if(n==1)
				{
					String someMessage = "Error !";
					PrintWriter out = response.getWriter();
					out.print("<html><head><script type=\\\"text/javascript\\\">alert(\" + someMessage + \");</script></head><body></body></html>");
					
					request.getSession().setAttribute("msg", "Record Inserted Successfully..!!");
					response.sendRedirect("index.html"); 
				}
				else
				{
					request.getSession().setAttribute("msg", "Record Failed To Insert..!!");
					response.sendRedirect("index.html"); 
				}
			}
		}
		catch(Exception e)
		{
			System.err.println(e);
			e.printStackTrace();
		}
	}
}
