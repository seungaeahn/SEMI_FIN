package shinee.servlet;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mypageServlet")
public class MyPage_Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // JDBC �ҷ�����
        String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
        String jdbcUsername = "shinee";
        String jdbcPassword = "shinee";

        try {
            // ����̹� �ε�
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // ���� ó�� �߰�
        }

        try {
            // DB ����
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            String user_id = request.getParameter("user_id");

            // SELECT�� ȸ������ ��������
            String sql = "SELECT * FROM user_info WHERE user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user_id);

            // ���� ���� �� ��� ��������
            ResultSet rs = preparedStatement.executeQuery();
            
            

            // ������ ��� �̵��� ������ �������ְ� �ٽ� ����
            response.sendRedirect("mypageMain.jsp");

        } catch (SQLException e) {
            // ������ ��� �̵��� ������ ����
            response.sendRedirect("mypageLogout.jsp");
            System.out.println("�����ҷ����⿡ �����Ͽ����ϴ�.");
            e.printStackTrace();
        }
    }
}