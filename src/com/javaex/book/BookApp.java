/********************************************
 작성자:  강수빈 <--- 자신의 이름으로 변경
*********************************************/

package com.javaex.book;

import java.util.List;

public class BookApp {

	/********************************************
	 main() 의 내용은 수정하지 않습니다.
	*********************************************/
	public static void main(String[] args) {
		// main 메소드는 수정하지 않습니다.
		BookDAO bookDAO = new BookDAO();
		
		/*
		bookDAO.bookInsert("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		bookDAO.bookInsert("삼국지", "민음사", "2002-03-01", 1);
		bookDAO.bookInsert("토지", "마로니에북스", "2012-08-15", 2);
		bookDAO.bookInsert("유시민의 글쓰기 특강", "생각의길", "2015-04-01", 3);
		bookDAO.bookInsert("패션왕", "중앙북스(books)", "2012-02-22", 4);
		bookDAO.bookInsert("순정만화", "재미주의", "2011-08-03", 5);
		bookDAO.bookInsert("26년", "재미주의", "2012-02-04", 5);
		*/
		
		List<BookVO> bookList = bookDAO.bookSelect();
		
		
		for(int i=0; i<bookList.size(); i++) {
			BookVO bookVO = bookList.get(i);
			
			System.out.println(bookVO.getBookId() + ", " + 
							   bookVO.getTitle() + ", " +
							   bookVO.getPubs() + ", " +
							   bookVO.getPubDate() + ", " +
							   bookVO.getAuthorId() 
			);
			
			
		}
		
	}

}
