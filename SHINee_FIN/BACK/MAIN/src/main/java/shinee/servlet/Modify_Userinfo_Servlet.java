package shinee.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/ModifyServlet")
@MultipartConfig
public class Modify_Userinfo_Servlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "shinee";
		String userPassword = "shinee";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, userName, userPassword);
			
			System.out.println();
			String user_id = request.getParameter("user_id");
			String user_name = request.getParameter("user_name");			
			String user_nickname = request.getParameter("user_nickname");
			String user_password = request.getParameter("user_password");
			String email = request.getParameter("email");
			String phone_number = request.getParameter("phone_number");
			Part profileInput = request.getPart("profileInput");
			
			System.out.println(user_id);
			System.out.println(user_name);
			String sql = "UPDATE user_info SET user_name=?, user_nickname=?, user_password=?, email=?, phone_number=?, profile_img=? WHERE user_id=?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1,user_name);
			ps.setString(2,user_nickname);
			ps.setString(3,user_password);
			ps.setString(4,email);
			ps.setString(5,phone_number);
			ps.setBinaryStream(6, profileInput.getInputStream(),(int) profileInput.getSize());
			ps.setString(7,user_id);
			
			
			ps.executeUpdate();
			
			request.getSession().setAttribute("user_name", user_name);
            request.getSession().setAttribute("user_nickname", user_nickname);
            request.getSession().setAttribute("user_password", user_password);
            request.getSession().setAttribute("email", email);
			
			//������ ��� �̵��� ������ �������ְ� �ٽ� ����
		       
			response.sendRedirect("MyPage.jsp");
			
	         
		} catch (SQLException e) {
			// ������ ��� �̵��� ������ ����
			response.sendRedirect("Modify_user_info.jsp");
			System.out.println("���� ������ �����Ͽ����ϴ�.");
			e.printStackTrace();
		}
		
	}
}