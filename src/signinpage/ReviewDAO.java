package signinpage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ReviewDAO {
	Connection con;
	PreparedStatement pstmt;
	String sql;
	ResultSet rs = null;
	ReviewDTO dto;
	List<ReviewDTO> dtoList = new ArrayList<ReviewDTO>();

	public List<ReviewDTO> loadReview(String bookName) {

		try {
			con = Connect.getConnection();
			sql = "SELECT userName,review FROM reviews WHERE bookName = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new ReviewDTO(rs.getString("userName"), rs.getString("review"));
				dtoList.add(dto);
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
		return dtoList;
	}

	public void insertReview(ReviewDTO reviewDTO) {
		try {
			con = Connect.getConnection();
			sql = "INSERT INTO reviews(id,bookName,userName,review) VALUES (reviews_seq.NEXTVAL,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reviewDTO.getBookName());
			pstmt.setString(2, reviewDTO.getUserName());
			pstmt.setString(3, reviewDTO.getReview());
			int i = 0;
			i = pstmt.executeUpdate();
			if (i > 0) {
				JOptionPane.showMessageDialog(null, "리뷰 등록 성공");
			} else {
				JOptionPane.showMessageDialog(null, "리뷰 등록 실패 다시 입력해주세요");
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
}
