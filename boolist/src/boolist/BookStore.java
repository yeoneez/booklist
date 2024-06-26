package boolist;

import java.sql.DriverManager;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import java.sql.SQLException;

public class BookStore 
{
	//1.멤버변수
	private Connection con;//멤버변수가 클래스로 만든 객체 변수 
	private Statement stmt;
	private ResultSet rs;
	
	//방법1: 변수
	private int bookid;
	private String bookname;
	private String publisher;
	private int price;
	
	//방법2: 배열
	private int bookidArr[];
	private String booknameArr[];
	private String publisherArr[];
	private int priceArr[];
	

	//밖으로 독립
//	//방법3과 방법4를 위해서, 내부 클래스 정의
//	private class Book//내부 클래스(inner class)
//	{
//		int bookid;
//		String bookname;
//		String publisher;
//		int price;
//	}	
	
	//방법3: 객체
	private Book book;
	
	//방법4: 객체배열
	private Book bookArr[];
	
	public Book[] getBookArr()//getter메소드
	{
		return bookArr;
	}
	

	//2.생성자:역할은 멤버변수 초기화
	public BookStore(){
		con = null;
		stmt = null;
		rs = null;
		
		//방법2: 배열초기화
		bookidArr = new int[10];
		booknameArr = new String[10];
		publisherArr = new String[10];
		priceArr = new int[10];
		
		//방법3: 객체 초기화
		book = new Book();
		
		//방법4: 객체배열 초기화
		bookArr = new Book[10];
		for(int i=0; i<bookArr.length;i++)
		{
			bookArr[i] = new Book();
		}
	}
	
	//메소드로 변경
	public void getCon()  {
		
		//con = new Connection();//이런 형태가 되어야 할꺼 같은데... 
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid = "c##madang"; // c## 필요하면 앞에 추가
		String pwd = "c##madang"; // c## 필요하면 앞에 추가

		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("데이터베이스 연결 준비 .....");
			
			con = DriverManager.getConnection(url, userid, pwd);
			
			System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//3.메소드
	public void selectBookList() { // 생성자
		//String query = "SELECT * FROM book";
		try {
			
			stmt = con.createStatement(); // 2
			
			int id = 1;
			rs = stmt.executeQuery("SELECT * FROM book where bookid = "+ id +" "); // 3
			
			System.out.println("BOOK ID \tBOOK NAME \t\tPUBLISHER \t\t\tPRICE");
			
			int index = 0; //객체용
			int index2 = 0; //객체배열용
			while (rs.next()) {
				
				//방법0 : 그냥 바로 출력
//				System.out.print("\t" + rs.getInt(1));
//				System.out.print("\t" + rs.getString(2));
//				System.out.print("\t\t\t" + rs.getString(3));
//				System.out.println("\t\t\t\t" + rs.getInt(4));
				
				//방법1: 변수
				bookid = rs.getInt(1);
				bookname = rs.getString(2);
				publisher = rs.getString(3);
				price = rs.getInt(4);
				
				//printBook(); //반복 10번
				
				//방법2: 배열
				bookidArr[index] = rs.getInt(1);
				booknameArr[index] = rs.getString(2);
				publisherArr[index] = rs.getString(3);
				priceArr[index] = rs.getInt(4);
				
				index++;
				
				//printBookArr(); //이거슨 한꺼번에 다 출력 가능함// 고로 여기서 사용하는게 알맞지 않다!!
				
				//방법3: 객체
				book.bookid = rs.getInt(1);
				book.bookname = rs.getString(2);
				book.publisher = rs.getString(3);
				book.price = rs.getInt(4);
				
//				printBookObject();//반복 10번
				
				//방법4: 객체배열
				bookArr[index2].bookid = rs.getInt(1);
				bookArr[index2].bookname = rs.getString(2);
				bookArr[index2].publisher = rs.getString(3);
				bookArr[index2].price = rs.getInt(4);
				index2++;
				

			}
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//방법1: 변수 출력용
	private void printBook()
	{
		System.out.print("\t" + bookid);
		System.out.print("\t" + bookname);
		System.out.print("\t" + publisher);
		System.out.println("\t" + price);
	}
	
	//방법2: 배열 출력용
	public void printBookArr()
	{
		for(int i=0; i<bookidArr.length ;i++)
		{
			System.out.print("\t" + bookidArr[i]);
			System.out.print("\t" + booknameArr[i]);
			System.out.print("\t" + publisherArr[i]);
			System.out.println("\t" + priceArr[i]);
		}
	}
	
	//방법3: 객체 출력용
	private void printBookObject()
	{
		System.out.print("\t" + book.bookid);
		System.out.print("\t" + book.bookname);
		System.out.print("\t" + book.publisher);
		System.out.println("\t" + book.price);
	}
	
	//방법4: 객체배열 출력용
	public void printBookObjectArr()//객체 배열 출력
	{
		for(int i=0; i<bookArr.length; i++)
		{
			System.out.print("\t" + bookArr[i].bookid);
			System.out.print("\t" + bookArr[i].bookname);
			System.out.print("\t" + bookArr[i].publisher);
			System.out.println("\t" + bookArr[i].price);
		}
	}
	
	public void selectCustomerList() { // 생성자
		//String query = "SELECT * FROM book";
		try {
			
			stmt = con.createStatement(); // 2
			
			rs = stmt.executeQuery("SELECT * FROM customer"); // 3
			
			System.out.println("사용자ID \t사용자 이름 \t\t주소 \t\t전화번호");
			while (rs.next()) {
				
				//방법0: 그냥 출력
				System.out.print("\t" + rs.getInt(1));
				System.out.print("\t" + rs.getString(2));
				System.out.print("\t\t\t" + rs.getString(3));
				System.out.println("\t\t\t\t" + rs.getString(4));
				
				//방법1:
				//방법2:
				//방법3:
				//방법4:

			}
			
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


}
