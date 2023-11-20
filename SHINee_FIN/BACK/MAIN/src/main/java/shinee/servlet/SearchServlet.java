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

//검색하는..Servlet인데 사용하지 않을듯 일단 놔둘게요
public class SearchServlet extends HttpServlet {
	
	
	//어디서든지 DB에 접근할 수 있게 메서드 밖 static에 올려버리기
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jbdcUsername = "shinee";
	private static final String jdbcPassword = "shinee";
	
	Connection connection = null;
	
	//doGet() : 값을 가지고오는 메서드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//DB연결
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection(jdbcURL, jbdcUsername, jdbcPassword);
			
			String search_Text = request.getParameter("searchText");
			
			String searchSQL = "SELECT song_name, artist_name FROM music_info WHERE song_name =? ";
			PreparedStatement searchState = connection.prepareStatement(searchSQL);
			
			searchState.setString(1,search_Text);
			
			ResultSet resultSet = searchState.executeQuery();
			
			//2. 배열 만들러왓습니다 총총
			ArrayList<Music_info_VO> musicList = new ArrayList<>();
			
			while(resultSet.next()) {
				
				String song_name = resultSet.getString("song_name");
				String artist_name = resultSet.getString("artist_name");
				
				//MusicSearch_info클래스 객체 생성
				Music_info_VO music_info = new Music_info_VO(song_name, artist_name);

				//1. 배열에 객체 담고싶은디 일단 배열 만들고올게용
				//3. 만들고왓습니다 담아줄게요
				musicList.add(music_info);
			}
			
			//음악목록을 전달합시다
			request.setAttribute("musicList", musicList);
			//request.getRequestDispatcher("/search.jsp").forward(request, response);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

} //class
