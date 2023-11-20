package shinee.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import shinee.vo.User_info_VO;

public class Register_DAO {
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String username = "shinee";
	private static final String password = "shinee";
	private Connection con;
	private PreparedStatement ps;
	private ResultSet result;

	
	public Register_DAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<User_info_VO> getAllUserInfo(){
		List<User_info_VO> userInfo = new ArrayList<>();
		
		try {
			con = DriverManager.getConnection(jdbcURL, username, password);
			String sql = "SELECT * FROM USER_INFO";
			ps = con.prepareStatement(sql);
			result = ps.executeQuery();
			
			while(result.next()) {
				String userId = result.getString("USER_ID");
				String userName = result.getString("USER_NAME");
				String userNickname = result.getString("USER_NICKNAME");
				String userPW = result.getString("USER_PASSWORD");
				String email = result.getString("EMAIL");
				String phoneNumber = result.getString("PHONE_NUMBER");
				Blob profile_img = result.getBlob("profile_img");
				byte[] imageData = profile_img.getBytes(1, (int) profile_img.length());
				String imageBase64 = Base64.getEncoder().encodeToString(imageData);
                String imageUrl = "data:image/jpeg;base64, " + imageBase64;
				
                User_info_VO user = new User_info_VO(userId,userName,userNickname,userPW,email,phoneNumber,imageUrl);
				userInfo.add(user);
					
			}
			result.close();
			ps.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userInfo;
	}
	
	public User_info_VO getSHINeeUserInfo(String userId) {
		User_info_VO user	= new User_info_VO();
		con = null;
		try {
			con = DriverManager.getConnection(jdbcURL,username ,password);
			String sql = "SELECT * FROM USER_INFO WHERE USER_ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, userId);
			
			ResultSet result = ps.executeQuery();

			if(result.next()) {
				String userID = result.getString("USER_ID");
				String userName = result.getString("USER_NAME");
				String userNickname = result.getString("USER_NICKNAME");
				String userPW = result.getString("USER_PASSWORD");
				String email = result.getString("EMAIL");
				String phoneNumber = result.getString("PHONE_NUMBER");
				Blob profile_img = result.getBlob("profile_img");
				
				byte[] imageData = profile_img.getBytes(1, (int) profile_img.length());
				String imageBase64 = Base64.getEncoder().encodeToString(imageData);
                String imageUrl = "data:image/jpeg;base64, " + imageBase64;
				
				user.setUser_id(userID);
				user.setUser_name(userName);
				user.setUser_nickname(userNickname);
				user.setUser_password(userPW);
				user.setEmail(email);
				user.setPhone_number(phoneNumber);
				user.setProfile_img(imageUrl);
			}
			
			result.close();
			ps.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
		
		
	}
	public boolean getSHINeeUserIdCheck(String userId) {
		
		con = null;
		try {
			con = DriverManager.getConnection(jdbcURL, username, password);
			String sql = "SELECT USER_ID FROM USER_INFO WHERE USER_ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, userId);
			
			ResultSet result = ps.executeQuery();

			if(result.next()) {
				return true;
				
			}
			
			result.close();
			ps.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
		}
		return false;
		
	}
	
	
	
}