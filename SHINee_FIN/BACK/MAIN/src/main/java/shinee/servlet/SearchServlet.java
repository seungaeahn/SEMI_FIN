package shinee.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shinee.vo.Music_info_VO;

@WebServlet("/SearchServlet")

//�˻��ϴ�..Servlet�ε� ������� ������ �ϴ� ���ѰԿ�
public class SearchServlet extends HttpServlet {
	
	
	//��𼭵��� DB�� ������ �� �ְ� �޼��� �� static�� �÷�������
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jbdcUsername = "shinee";
	private static final String jdbcPassword = "shinee";
	
	Connection connection = null;
	
	//doGet() : ���� ��������� �޼���
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//DB����
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection(jdbcURL, jbdcUsername, jdbcPassword);
			
			String search_Text = request.getParameter("searchText");
			
			String searchSQL = "SELECT song_name, artist_name FROM music_info WHERE song_name =? ";
			PreparedStatement searchState = connection.prepareStatement(searchSQL);
			
			searchState.setString(1,search_Text);
			
			ResultSet resultSet = searchState.executeQuery();
			
			//2. �迭 ���鷯�ӽ��ϴ� ����
			ArrayList<Music_info_VO> musicList = new ArrayList<>();
			
			while(resultSet.next()) {
				
				String song_name = resultSet.getString("song_name");
				String artist_name = resultSet.getString("artist_name");
				
				//MusicSearch_infoŬ���� ��ü ����
				Music_info_VO music_info = new Music_info_VO(song_name, artist_name);

				//1. �迭�� ��ü �������� �ϴ� �迭 �����ðԿ�
				//3. �����ӽ��ϴ� ����ٰԿ�
				musicList.add(music_info);
			}
			
			//���Ǹ���� �����սô�
			request.setAttribute("musicList", musicList);
			//request.getRequestDispatcher("/search.jsp").forward(request, response);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

} //class
