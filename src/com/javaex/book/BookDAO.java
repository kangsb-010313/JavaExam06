package com.javaex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

	//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/web_db";
	private String id = "web";
	private String pw = "web";
	
	//생성자
	public BookDAO() {
	}
	
	//메소드 gs
	
	
	//메소드 일반
	private void connect() {

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}
	

	// 자원 정리 메소드
	private void close() {

		// 5. 자원정리
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}
	
	// ////////////////////////////////////////////////////////////
	
	// 책 등록 ////////////////////////////////////
	public int bookInsert(String title, String pubs, String pubDate, int authorId) {
		
	      int count = -1;
	      
	      // 0. import java.sql.*;

	      // 1. JDBC 드라이버 (Oracle) 로딩
	      // 2. Connection 얻어오기
	      this.connect();
	      
	      try {

	         // 3. SQL문 준비 / 바인딩 / 실행
	         // SQL문 준비
	         String query = "";
	         query += " insert into book ";
	         query += " values(null, ?, ?, ?, ?) ";
	         System.out.println(query);

	         // 바인딩ff
	         pstmt = conn.prepareStatement(query);
	         pstmt.setString(1, title);
	         pstmt.setString(2, pubs);
	         pstmt.setString(3, pubDate);
	         pstmt.setInt(4, authorId);

	         // 실행
	         count = pstmt.executeUpdate();

	         // 4.결과처리

	      } catch (SQLException e) {
	         System.out.println("error:" + e);
	      }

	      this.close();

	      return count;
		
		
	}// insert
	
	
	// 책 조회 ////////////////////////////////////
	public List<BookVO> bookSelect(){
		
		List<BookVO> bookList = new ArrayList<BookVO>();
		
		// 0. import java.sql.*;

		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		this.connect();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += " select	book_id, ";
			query += " 			title, ";
			query += " 			pubs, ";
			query += " 			date_format(pub_date, '%Y-%m-%d') as 'pub_date', ";
			query += " 			author_id ";
			query += " from book ";

			// 바인딩
			pstmt = conn.prepareStatement(query);

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {

				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");

				// 데이터 객체로 만들기 (묶기)
				BookVO bookVo = new BookVO(bookId, title, pubs, pubDate, authorId);
				// 묶은 테이블을 리스트에 담기
				bookList.add(bookVo);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return bookList;
		
	}// select 
	
	
}
