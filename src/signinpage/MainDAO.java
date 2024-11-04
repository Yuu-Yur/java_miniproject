package signinpage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainDAO {
	Connection con;
	String sql;
	String sql2;
	String sql3;
	PreparedStatement pstmt;
	PreparedStatement pstmt2;
	PreparedStatement pstmt3;
	ResultSet rs = null;
	ResultSet rs2 = null;
	ResultSet rs3 = null;
	MainDTO dto;
	List<MainDTO> dtoList;

	public List<MainDTO> loadBooks() {
		try {
			dtoList = new ArrayList<MainDTO>();
			con = Connect.getConnection();
			sql = "SELECT * from books";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			dto = new MainDTO(rs.getString("id"), rs.getString("author"), rs.getDate("releaseDate"),
					rs.getFloat("rating"));
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
}