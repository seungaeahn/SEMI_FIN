package shinee.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import shinee.vo.Music_info_VO;
import shinee.vo.Playlist_info_VO;

//�÷��̸���Ʈ ���� ������ �߰��ϱ� ���� DAO�����Դϴ�
public class Insert_music_toPlaylist_DAO {
	
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String jbdcUsername = "shinee";
	private static final String jdbcPassword = "shinee";
	
	Connection connection = null;

	//�÷��̸���Ʈid �Է¹ް�, �ش��Ͽ� �߰��ϴ�...�޼���
	public Music_info_VO insertMusictoPlaylist(int playlist_id, String song_id) {

		Connection connection = null;
		PreparedStatement insertState = null;
		ResultSet resultSet = null;
		
		String insertSQL = "INSERT INTO playlist_song VALUES(?,?)";
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection(jdbcURL, jbdcUsername, jdbcPassword);
			
			insertState = connection.prepareStatement(insertSQL);
			
			insertState.setString(1,"%"+playlist_id+"%");
			insertState.setString(2,"%"+song_id+"%");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	//���� id�� ������ ��ü �÷��̸���Ʈ ��ȸ �޼���
	public ArrayList<Playlist_info_VO> getAllPlaylist(String user_id){
		
		String searchSQL = null;

		Connection connection = null;
		PreparedStatement searchState = null;
		ResultSet resultSet = null;
		
		//2. �迭 ���鷯�ӽ��ϴ� ����
		ArrayList<Playlist_info_VO> myplaylists = new ArrayList<>();
		
		try {
			
			searchSQL = "SELECT playlist_id, playlist_name FROM playlist_info WHERE user_id=?";
			
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection(jdbcURL, jbdcUsername, jdbcPassword);
			
			searchState = connection.prepareStatement(searchSQL);
			
			searchState.setString(1,user_id);
			
			resultSet = searchState.executeQuery();
	
			
			while(resultSet.next()) {
				
				Playlist_info_VO playlist_info = new Playlist_info_VO();
				
				playlist_info.setPlaylist_id(resultSet.getInt("playlist_id"));
				playlist_info.setPlaylist_name(resultSet.getString("playlist_name"));

				//1. �迭�� ��ü �������� �ϴ� �迭 �����ðԿ�
				//3. �����ӽ��ϴ� ����ٰԿ�
				myplaylists.add(playlist_info);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return myplaylists;
		
	}
}
