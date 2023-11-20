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

/**
 * Servlet implementation class FollowingServlet
 */
@WebServlet("/FollowingServlet")
public class Following_Servlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// JDBC �ҷ�����
		
	// JDBC �ҷ�����
	
			String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
			String jdbcUsername = "shinee";
			String jdbcPassword = "shinee";
			try {
				//����̹� �ε�
				Class.forName("oracle.jdbc.OracleDriver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				//DB ����
				Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
				
	            String follower_id = request.getParameter("follower_id");
	            String following_id = request.getParameter("following_id");
	            String user_nickname = request.getParameter("user_nickname");
	            String profile_img = request.getParameter("profile_img");
	         
				//SELECT�� ȸ��������������
					//sql ���� 
	            String sql = "SELECT *"
						+ "FROM follow_list"
						+ "INNER JOIN user_info ON follow_list.following_id = user_info.user_id"
						+ "WHERE follow_list.follower_id = ?";
				
		            PreparedStatement preparedStatement = connection.prepareStatement(sql);
		            preparedStatement.setString(1, follower_id);
		            //���� => ��� ���� 
		            preparedStatement.executeUpdate();
				
				
		           
			
				//������ ��� �̵��� ������ �������ְ� �ٽ� ����
		       
				response.sendRedirect("mypageMain.jsp");
				
			} catch (SQLException e) {
				// ������ ��� �̵��� ������ ����
				response.sendRedirect("mypageMain.jsp");
				System.out.println("�ȷο� �����ҷ����⿡ �����Ͽ����ϴ�.");
				e.printStackTrace();
			}
		}

}