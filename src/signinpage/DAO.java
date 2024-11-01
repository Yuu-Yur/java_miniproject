
package signinpage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DAO {
	Connection con;
	String sql;
	String sql2;
	PreparedStatement pstmt;
	PreparedStatement pstmt2;
	ResultSet rs = null;
	DTO dto;

	// insert method
	public void insertDAO(DTO dto) {
		try {
			con = Connect.getConnection();
			sql = "INSERT INTO reviewMember(idNo,id,password,name,email) VALUES (reviewMember_seq.NEXTVAL,?,?,?,?)";
			sql2 = "SELECT * from reviewMember WHERE email = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setString(1, dto.getEmail());
			rs = pstmt2.executeQuery();
			if (!rs.next()) {
				int i = 0;
				i = pstmt.executeUpdate();
				if (i > 0) {
					JOptionPane.showMessageDialog(null, "어서오세요!, " + dto.getName() + "님", "삽입 성공",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "삽입에 실패했습니다", "삽입 실패", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "중복된 이메일입니다", "이메일 중복", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//
	public DTO searchDAO(String inputName, String inputEmail) {
		try {
			con = Connect.getConnection();
			sql = "SELECT * from reviewMember WHERE email = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, inputEmail);
			rs = pstmt.executeQuery();
			if (rs.next() && rs.getString("name").equals(inputName)) {
				dto = new DTO(rs.getString("id"), rs.getString("password"), rs.getString("name"),
						rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dto;
	}
}