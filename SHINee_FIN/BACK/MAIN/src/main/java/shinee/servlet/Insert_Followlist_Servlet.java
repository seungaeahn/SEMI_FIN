package shinee.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertFollowlist")
public class Insert_Followlist_Servlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "shinee";
		String userPassword = "shinee";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			
			Connection connection = DriverManager.getConnection(jdbcURL, userName, userPassword);
			
			//
			String follower_id = request.getParameter("follower_id");
			String following_id = request.getParameter("following_id");
			
			String insertSQL = "INSERT INTO follow_list VALUES(?,?)";
			
			PreparedStatement insertState = connection.prepareStatement(insertSQL);
			
			insertState.setString(1, follower_id); //현재 세션 user_id
			insertState.setString(2,following_id);
			
			int rs = insertState.executeUpdate();
			
			//삽입성공..? if rs rownum?
			
			//다시 검색창으로
			response.sendRedirect("MyPage.jsp");
			request.getRequestDispatcher("MyPage.jsp");	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
