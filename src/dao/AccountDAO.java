package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Account;
import model.Login;

public class AccountDAO {
	//DBに接続に使用する情報
	private final String JDBC_URL =
			"jdbc:h2:~/ServletLoginApp";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	
	public Account findByLogin(Login login) {
		Account account = null;
		
		//DBへ接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)) {
			//select文を準備
			String sql = "SELECT USER_ID, PASSWORD, NAME FROM ACCOUNT WHERE USER_ID = ? AND PASSWORD = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, login.getUserId());
			pStmt.setString(2, login.getPass());
			
			//select文を実行し、結果票を取得
			ResultSet rs = pStmt.executeQuery();
			
			//一致したユーザーが存在した場合
			//そのユーザーを表すAccountインスタンスを生成
			if (rs.next()) {
				//結果表からデータを取得
				String userId = rs.getString("USER_ID");
				String pass = rs.getString("PASSWORD");
				String name = rs.getString("NAME");
				
				account = new Account(userId,pass,name);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		//ユーザーが見つかった場合
		return account;
	}
}
