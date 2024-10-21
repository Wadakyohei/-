package femmefatale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FemmeFataleDBConnect {
	/*
	 * 「商品」テーブルへのアクセス
	 * を行うクラスです。
	 */
		private final String URL = "jdbc:postgresql://localhost:5432/shop";
		private final String USER = "postgres";
		private final String PASSWORD = "test";
		
		// コンストラクタ
		public FemmeFataleDBConnect() {
	        /* JDBCドライバの準備（接続前に1度行えばよいため、コンストラクタで行っている） */
	    	try {
	    		Class.forName("org.postgresql.Driver");
	    	} catch (ClassNotFoundException e) {
				e.printStackTrace();
				}
			}

		//販売する商品の検索
	    public List<String>  selectID() {
			List<String> id = new ArrayList<String>();

	        /* 1) SQL文の準備 */
	        String sql = "SELECT shohin_id FROM femmefatale;";

	        /* 2) PostgreSQLへの接続 */
	    	try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement st = con.createStatement();) {

	    		/* 3) SQL文の実行 */
				ResultSet rs = st.executeQuery(sql);
				
				/* 4) 結果を入れる */
//				List<String> wepon = new ArrayList<String>();
		    	StringBuilder sb = new StringBuilder();

		    	while (rs.next()) {
		    		sb = new StringBuilder();

		            sb.append(rs.getString("shohin_id"));
		            
		            id.add(sb.toString());
		    	}				
			} catch (Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
	    	return id;
	    }
		
		//販売する商品の検索
	    public List<String>  selectShohin_mei() {
			List<String> wepon = new ArrayList<String>();

	        /* 1) SQL文の準備 */
	        String sql = "SELECT shohin_mei FROM femmefatale;";

	        /* 2) PostgreSQLへの接続 */
	    	try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement st = con.createStatement();) {

	    		/* 3) SQL文の実行 */
				ResultSet rs = st.executeQuery(sql);
				
				/* 4) 結果を入れる */
//				List<String> wepon = new ArrayList<String>();
		    	StringBuilder sb = new StringBuilder();

		    	while (rs.next()) {
		    		sb = new StringBuilder();

		            sb.append(rs.getString("shohin_mei"));
		            
		            wepon.add(sb.toString());
		    	}				
			} catch (Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
	    	return wepon;
	    }
		
	    //値段の検索
	    public List<Integer>  selectHanbai_tanka() {
			List<Integer> sell = new ArrayList<Integer>();

	        /* 1) SQL文の準備 */
	        String sql = "SELECT hanbai_tanka FROM femmefatale;";

	        /* 2) PostgreSQLへの接続 */
	    	try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement st = con.createStatement();) {

	    		/* 3) SQL文の実行 */
				ResultSet rs = st.executeQuery(sql);
				
				/* 4) 結果を入れる */
//				List<String> wepon = new ArrayList<String>();
		    	StringBuilder sb = new StringBuilder();

		    	while (rs.next()) {
		    		sb = new StringBuilder();

		            sb.append(rs.getInt("hanbai_tanka"));
		            
		            sell.add(Integer.parseInt(sb.toString()));
		    	}				
			} catch (Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
	    	return sell;
	    }
	    
	    //組織名の検索
	    public List<String>  selectSoshiki_mei() {
			List<String> soshiki = new ArrayList<String>();

	        /* 1) SQL文の準備 */
	        String sql = "SELECT soshiki_mei FROM femmefatale;";

	        /* 2) PostgreSQLへの接続 */
	    	try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement st = con.createStatement();) {

	    		/* 3) SQL文の実行 */
				ResultSet rs = st.executeQuery(sql);
				
				/* 4) 結果を入れる */
//				List<String> wepon = new ArrayList<String>();
		    	StringBuilder sb = new StringBuilder();

		    	while (rs.next()) {
		    		sb = new StringBuilder();

		            sb.append(rs.getString("soshiki_mei"));
		            
		            soshiki.add(sb.toString());
		    	}				
			} catch (Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
	    	return soshiki;
	    }
		
	    //受注した内容の登録
	    public void AddJutyu(String smei, String bmei, int kosu, String tokyuu, String torokubi) {
	    	/* 1) SQL文の準備 */
	    	String sql = "INSERT INTO jutyu VALUES(? ,? ,? ,? ,?);";
	    	
	    	/* 2) PostgreSQLへの接続 */
			try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
					PreparedStatement st = con.prepareStatement(sql);) {

				/* 3) SQL文の?部分を置き換え */
				st.setString(1, smei);
				st.setString(2, bmei);
				st.setInt(3, kosu);
				st.setString(4, tokyuu);
				st.setString(5, torokubi);
				
				/* 4) SQL文の実行 */
				int insCnt = st.executeUpdate();
				System.out.println(insCnt + "行登録されました。");
				
				} catch (Exception e) {
					System.out.println("DBアクセス時にエラーが発生しました。");
					e.printStackTrace();
				}
	    }
	    
	    
	    //テーブルで変更した内容の登録
	    public void Update(String wepon, String soshiki, int sell, String id) {
	        /* 1) SQL文の準備 */
	        String sql = "UPDATE femmefatale SET shohin_mei = ?, soshiki_mei = ?, hanbai_tanka = ? WHERE shohin_id = ?;";

	        /* 2) PostgreSQLへの接続 */
	    	try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
	    			PreparedStatement st = con.prepareStatement(sql);){

	    		/* 3) SQL文の?部分を置き換え */
				st.setString(1, wepon);
				st.setString(2, soshiki);
				st.setLong(3, sell);
				st.setString(4, id);

				/* 4) SQL文の実行 */
				int updCnt = st.executeUpdate();
				System.out.println(updCnt + "行更新されました。");
				
			} catch (Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
	    }
	    
	    //入力した商品を新しく登録
	    public void Add(String id, String shohin, int sell, String soshiki) {
	        /* 1) SQL文の準備 */
	        String sql = "INSERT INTO femmefatale VALUES (?, ?, ?, null,?);";

	        /* 2) PostgreSQLへの接続 */
	    	try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
	    			PreparedStatement st = con.prepareStatement(sql);){

	    		/* 3) SQL文の?部分を置き換え */
				st.setString(1, id);
				st.setString(2, shohin);
				st.setLong(3, sell);
				st.setString(4, soshiki);

				/* 4) SQL文の実行 */
				int updCnt = st.executeUpdate();
				System.out.println(updCnt + "行更新されました。");
				
			} catch (Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
	    }

	   
	  //登録した商品の検索
	    public List<String>  searchShohin_mei() {
			List<String> wepon = new ArrayList<String>();

	        /* 1) SQL文の準備 */
	        String sql = "SELECT DISTINCT buki_mei FROM jutyu;";

	        /* 2) PostgreSQLへの接続 */
	    	try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement st = con.createStatement();) {

	    		/* 3) SQL文の実行 */
				ResultSet rs = st.executeQuery(sql);
				
				/* 4) 結果を入れる */
//				List<String> wepon = new ArrayList<String>();
		    	StringBuilder sb = new StringBuilder();

		    	while (rs.next()) {
		    		sb = new StringBuilder();

		            sb.append(rs.getString("buki_mei"));
		            
		            wepon.add(sb.toString());
		    	}				
			} catch (Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
	    	return wepon;
	    }
	    
	  //登録した個数の検索
	    public List<Integer>  searchkosu() {
			List<Integer> kosu = new ArrayList<Integer>();

	        /* 1) SQL文の準備 */
	        String sql = "SELECT DISTINCT kosu FROM jutyu;";

	        /* 2) PostgreSQLへの接続 */
	    	try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement st = con.createStatement();) {

	    		/* 3) SQL文の実行 */
				ResultSet rs = st.executeQuery(sql);
				
				/* 4) 結果を入れる */
//				List<String> wepon = new ArrayList<String>();
		    	StringBuilder sb = new StringBuilder();

		    	while (rs.next()) {
		    		sb = new StringBuilder();

		            sb.append(rs.getInt("kosu"));
		            
		            kosu.add(Integer.parseInt(sb.toString()));
		    	}				
			} catch (Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
	    	return kosu;
	    }
	    
	  //登録した組織名の検索
	    public List<String>  searchSoshiki_mei() {
			List<String> soshiki = new ArrayList<String>();

	        /* 1) SQL文の準備 */
	        String sql = "SELECT DISTINCT sosiki_mei FROM jutyu;";

	        /* 2) PostgreSQLへの接続 */
	    	try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement st = con.createStatement();) {

	    		/* 3) SQL文の実行 */
				ResultSet rs = st.executeQuery(sql);
				
				/* 4) 結果を入れる */
//				List<String> wepon = new ArrayList<String>();
		    	StringBuilder sb = new StringBuilder();

		    	while (rs.next()) {
		    		sb = new StringBuilder();

		            sb.append(rs.getString("sosiki_mei"));
		            
		            soshiki.add(sb.toString());
		    	}				
			} catch (Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
	    	return soshiki;
	    }
	    
	  //登録した特急かどうかの検索
	    public List<String>  search_tokkyu() {
			List<String> tokkyu = new ArrayList<String>();

	        /* 1) SQL文の準備 */
	        String sql = "SELECT tokkyu FROM jutyu;";

	        /* 2) PostgreSQLへの接続 */
	    	try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement st = con.createStatement();) {

	    		/* 3) SQL文の実行 */
				ResultSet rs = st.executeQuery(sql);
				
				/* 4) 結果を入れる */
//				List<String> wepon = new ArrayList<String>();
		    	StringBuilder sb = new StringBuilder();

		    	while (rs.next()) {
		    		sb = new StringBuilder();

		            sb.append(rs.getString("tokkyu"));
		            
		            tokkyu.add(sb.toString());
		    	}				
			} catch (Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
	    	return tokkyu;
	    }
	   
	  //登録した登録日の検索
	    public List<String>  search_torokubi() {
			List<String> torokubi = new ArrayList<String>();

	        /* 1) SQL文の準備 */
	        String sql = "SELECT DISTINCT torokubi FROM jutyu ORDER BY torokubi DESC;";

	        /* 2) PostgreSQLへの接続 */
	    	try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement st = con.createStatement();) {

	    		/* 3) SQL文の実行 */
				ResultSet rs = st.executeQuery(sql);
				
				/* 4) 結果を入れる */
//				List<String> wepon = new ArrayList<String>();
		    	StringBuilder sb = new StringBuilder();

		    	while (rs.next()) {
		    		sb = new StringBuilder();

		            sb.append(rs.getString("torokubi"));
		            
		            torokubi.add(sb.toString());
		    	}				
			} catch (Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
	    	return torokubi;
	    }
	  
	  //検索
	    public List<List<String>> Search(String wepon, String soshiki, String torokubi) {
	    	List<List<String>> list = new ArrayList<List<String>>();
	    	
	    	 // SQL文の準備
		    String sql = "SELECT * FROM jutyu WHERE 1=1"; // ベースとなるクエリ
		    
		    if (!wepon.isEmpty()) {
		        sql += " AND buki_mei = ?";
		    }
		    if (!soshiki.isEmpty()) {
		        sql += " AND sosiki_mei = ?";
		    }
		    if (!torokubi.isEmpty()) {
		        sql += " AND torokubi = ?";
		    }
	    	
	  	        /* 2) PostgreSQLへの接続 */
			try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
					//PreparedStatement st = con.prepareStatement(sql);)  {
					PreparedStatement ps = con.prepareStatement(sql)) {
				
				int index = 1;
		        if (!wepon.isEmpty()) {
		            ps.setString(index++, wepon);
		        }
		        if (!soshiki.isEmpty()) {
		            ps.setString(index++, soshiki);
		        }
		        if (!torokubi.isEmpty()) {
		            ps.setString(index++, torokubi);
		        }
				
				//* 3) SQL文の実行 */
		        ResultSet rs = ps.executeQuery();
				
				/* 4) 結果を入れる */
				while (rs.next()) {
					List<String> search = new ArrayList<String>();
					
		    		search.add(rs.getString("sosiki_mei"));
		    		search.add(rs.getString("buki_mei"));
		    		search.add(rs.getString("kosu"));
		    		search.add(rs.getString("tokkyu"));
		    		search.add(rs.getString("torokubi"));
		    		
		    		list.add(search);
				}
				
				
			} catch (Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
			return list;
	    }
}
