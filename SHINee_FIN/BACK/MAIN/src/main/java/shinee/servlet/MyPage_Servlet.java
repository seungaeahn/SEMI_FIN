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
        // JDBC 불러오기
        String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
        String jdbcUsername = "shinee";
        String jdbcPassword = "shinee";

        try {
            // 드라이버 로딩
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // 예외 처리 추가
        }

        try {
            // DB 연결
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

            String user_id = request.getParameter("user_id");

            // SELECT로 회원정보 가져오기
            String sql = "SELECT * FROM user_info WHERE user_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user_id);

            // 쿼리 실행 및 결과 가져오기
            ResultSet rs = preparedStatement.executeQuery();
            
            

            // 성공할 경우 이동할 페이지 설정해주고 다시 전송
            response.sendRedirect("mypageMain.jsp");

        } catch (SQLException e) {
            // 실패할 경우 이동할 페이지 설정
            response.sendRedirect("mypageLogout.jsp");
            System.out.println("정보불러오기에 실패하였습니다.");
            e.printStackTrace();
        }
    }
}