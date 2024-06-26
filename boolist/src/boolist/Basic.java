package boolist;

public class Basic {
	public static void main(String args[]) { // 메소드이면서 프로그램실행에 관여함
		
		BookStore bs1 = new BookStore();
		
		bs1.getCon();
		bs1.selectBookList();
//		bs1.printBookArr();//한꺼번에 출력//방법2 출력
//		bs1.printBookArr();//한꺼번에 출력//방법2 출력
		
//		bs1.printBookObjectArr();//방법4 출력
//		System.out.println();
//		bs1.printBookObjectArr();//방법4 출력
		
		//방법4를 활용하는 또 다른예시
		Book arr1[] = bs1.getBookArr();
		
		System.out.println("방법4의 또다른 예시");
		for(int i=0; i<arr1.length; i++) {
			System.out.print("\t" + arr1[i].bookid);
			System.out.print("\t" + arr1[i].bookname);
			System.out.print("\t" + arr1[i].publisher);
			System.out.println("\t" + arr1[i].price);
		}
		
		bs1.getCon();
		bs1.selectCustomerList();
		
//		BookStore bs2 = new BookStore();
//		bs2.selectCustomerList();
	}
}
