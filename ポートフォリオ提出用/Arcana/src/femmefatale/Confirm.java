package femmefatale;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Confirm {
	FemmeFataleDBConnect ff = new FemmeFataleDBConnect();
	JTable table;
	
	public Confirm() {
		List<String> shohin = ff.searchShohin_mei();
		List<String> soshiki_mei = ff.searchSoshiki_mei();
		List<String> torokubi = ff.search_torokubi();
		String[][] now = new String[ff.Search("", "", "").size()][5];
		String[] titl = {"組織名", "武器名", "個数", "FAST","登録日"};
	    table = new JTable(now, titl);
	    JScrollPane sp = new JScrollPane(table);
	    sp.setPreferredSize(new Dimension(400, 150));

		
		//Framインスタンスの作成
		JFrame Confirm = new JFrame();
		//ウィンドウタイトルの名前
		Confirm.setTitle("Confirm");
		//ウィンドウの大きさの設定
		Confirm.setSize(700, 400);
	    //初期位置の設定
		Confirm.setLocation(200, 10);
	    //×ボタン押したらウィンドウを閉じる
		Confirm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		// GridBagLayout を設定
		Confirm.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		//プルダウンボックスの追加
		shohin.add(0, "");
		JComboBox<String> wepon = new JComboBox<>();
		for(String s : shohin) {
			wepon.addItem(s);
		}
		//プルダウンボックスの追加
		soshiki_mei.add(0, "");
		JComboBox<String> soshiki = new JComboBox<>();
		for(String s : soshiki_mei) {
			soshiki.addItem(s);
		}
		//プルダウンボックスの追加
		torokubi.add(0, "");
		JComboBox<String> toroku = new JComboBox<>();
		for(String s : torokubi) {
			toroku.addItem(s);
		}
		
        // ラベルの追加
        JLabel lblWeapon = new JLabel("武器名:");
        JLabel lblSoshiki = new JLabel("組織名:");
        JLabel lblToroku = new JLabel("登録日:");
		
		
		//検索するボタンを作成
	    JButton confirm = new JButton("検索");
	    // ボタンを押した時の処理を設定
	    confirm.addActionListener(e -> {
	    	String strw = wepon.getItemAt(wepon.getSelectedIndex());
	    	String strs = soshiki.getItemAt(soshiki.getSelectedIndex());
	    	String strt = toroku.getItemAt(toroku.getSelectedIndex());
    		
	    	//テーブルのリセット
	    	for(int i = 0; i < ff.Search("", "", "").size(); i++) {
	    		table.setValueAt("", i, 0);
	    		table.setValueAt("", i, 1);
	    		table.setValueAt("", i, 2);
	    		table.setValueAt("", i, 3);
	    		table.setValueAt("", i, 4);	   
	    	}
	    	// 検索を実行
	        List<List<String>> results = ff.Search(strw, strs, strt);

	        // 結果をテーブルに設定
	        for (int i = 0; i < results.size(); i++) {
	            List<String> row = results.get(i);
	            for (int j = 0; j < row.size(); j++) {
	                table.setValueAt(row.get(j), i, j);
	            }
	        }
	    });
	    
		 // メニューのページに移動するボタンを作成
	    JButton back = new JButton("メインメニュー");
	    // ボタンを押した時の処理を設定
	    back.addActionListener(e -> {
	    	new Main();
	    	Confirm.setVisible(false);
	    });
	    

        // コンポーネントの配置
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Confirm.add(lblWeapon, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        Confirm.add(wepon, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        Confirm.add(lblSoshiki, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        Confirm.add(soshiki, gbc);

        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        Confirm.add(lblToroku, gbc);

        gbc.gridx = 7;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        Confirm.add(toroku, gbc);

        gbc.gridx = 9;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Confirm.add(confirm, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 10;
        gbc.fill = GridBagConstraints.BOTH;
        Confirm.add(sp, gbc);

        gbc.gridx = 9;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        Confirm.add(back, gbc);
			
	    //ウィンドウの表示
	    Confirm.setVisible(true);
	}
}
