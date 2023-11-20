package shinee.servlet;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

//���� ����ִ� class (null�� �����߸� ������)
public class BlobTest {
 
    public static void main(String[] args) {
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        Connection con = null;
        PreparedStatement stmt = null;
        
        
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
                    "shinee","shinee");
            
            File f = new File("C://Users//������//Desktop/����.jpg");    
            FileInputStream fis = new FileInputStream(f);
            
            stmt = con.prepareStatement(
            		//update ���̺�� set ������Ʈ�÷��� = ������Ʈ�� �� where ����
                    "UPDATE user_info SET profile_img=? WHERE user_name=?");
           
            stmt.setBinaryStream(1, fis,(int)f.length());
            stmt.setString(2, "������");
            //stmt.setString(2, "������");
            //stmt.setString(3, "ĭ��");
            //stmt.setString(4, "kh123456!");
            //stmt.setString(5, "kankan@kakao.com");
            //stmt.setString(6, "01037134606");
            //stmt.setBinaryStream(7, fis,(int)f.length());
            
            int rownum = stmt.executeUpdate();
            
            if(rownum >0){
                System.out.println("���Լ���");
            }else
            {
                System.out.println("����");
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
                //����� ��ü close
                try {
                    if(con != null) con.close();
                    if(stmt != null) stmt.close();
                } catch (Exception e) {
                    
                }
            
        }
        
    }
 
}