package test;

import dao.AccountDAO;
import model.Account;
import model.Login;

public class AccountDAOTest {
	public static void main(String[] args) {
		testFindByLogin1(); //ユーザーが見つかる場合のテスト
		testFindByLogin2(); //ユーザーが見つからない場合のテスト
	}
	
	public static void testFindByLogin1() {
		Login login = new Login("1", "1234");
		AccountDAO dao = new AccountDAO();
		Account result = dao.findByLogin(login);
		
		if (result != null &&
				result.getUserId().equals("1") &&
				result.getPass().equals("1234") &&
				result.getName().equals("TEST")) {
			System.out.println("testFindByLogin1:成功しました");
		} else {
			System.out.println("testFindByLogin1:失敗しました");
		}			
	}
	
	public static void testFindByLogin2() {
		Login login = new Login("1", "12334");
		AccountDAO dao = new AccountDAO();
		Account result = dao.findByLogin(login);
		
		if (result != null ) {
			System.out.println("testFindByLogin2:成功しました");
		} else {
			System.out.println("testFindByLogin2:失敗しました");
		}			
	}
}
