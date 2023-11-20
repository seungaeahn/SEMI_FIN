package shinee.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import shinee.vo.User_info_VO;

public class MyPage_DAO {
    private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String jdbcUsername = "shinee";
    private static final String jdbcPassword = "shinee";
    Connection con = null;

    public MyPage_DAO() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public User_info_VO getMember(String user_id) {
    	User_info_VO ui = null;
        try {
            con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            String sql = "SELECT * FROM USER_INFO WHERE user_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user_id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String userId = resultSet.getString("user_id");
                String userName = resultSet.getString("user_name");
                String userNickname = resultSet.getString("user_nickname");
                String userPassword = resultSet.getString("user_password");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                
                Blob blob = resultSet.getBlob("profile_img");
                String profile_img;
				if(blob != null) {
	                byte[] imageData = blob.getBytes(1, (int) blob.length());
	                String imageBase64 = java.util.Base64.getEncoder().encodeToString(imageData);
	                profile_img = "data:image/jpeg;base64, " + imageBase64;
                } else {
                	profile_img = null;
                }

                ui = new User_info_VO(userId, userName, userNickname, userPassword, email, phoneNumber, profile_img);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null && !con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ui;
    }
}