package femmefatale;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Add_Modify {
	FemmeFataleDBConnect ff = new FemmeFataleDBConnect();
	JTable table;
	
	public Add_Modify() {
		List<String> id = ff.selectID();
		List<String> shohin = ff.selectShohin_mei();
		List<String> soshiki_mei = ff.selectSoshiki_mei();
		List<Integer> sell = ff.selectHanbai_tanka();
		String[][] now = new String[shohin.size()][4];
		String[] titl = {"ID", "武器名", "値段", "組織名"};
	    table = new JTable(now, titl);
	    JScrollPane sp = new JScrollPane(table);
	    sp.setPreferredSize(new Dimension(300, 150));
		
		//Framインスタンスの作成
		JFrame Add_Modify = new JFrame();
		//ウィンドウタイトルの名前
		Add_Modify.setTitle("Add_Modify");
		//ウィンドウの大きさの設定
		Add_Modify.setSize(700, 400);
	    //初期位置の設定
		Add_Modify.setLocation(200, 10);
	    //×ボタン押したらウィンドウを閉じる
		Add_Modify.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		// GridBagLayout を設定
		Add_Modify.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		// テーブルの配置
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		Add_Modify.add(sp, gbc);
		
		//表示させてるテーブルに値を入れる
		for(int i = 0; i < shohin.size(); i++) {
			//IDの割り振り
			table.setValueAt(id.get(i), i, 0);
			//武器名をtableに表示
			table.setValueAt(shohin.get(i), i, 1);
			//値段をtableに表示
			table.setValueAt(String.valueOf(sell.get(i)), i, 2);
			//組織名をtableに表示
			table.setValueAt(soshiki_mei.get(i), i, 3);
		}
		
		 // 商品を変更するボタンを作成
	    JButton modify = new JButton("変更");
	    // 変更ボタンの配置
	    gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		Add_Modify.add(modify, gbc);
	    // ボタンを押した時の処理を設定
	    modify.addActionListener(e -> {
	    	if (table.isEditing()) {
                table.getCellEditor().stopCellEditing();
            }
	    	//全件アップデートの文入れたいなの気分
	    	for(int i = 0; i < shohin.size();i++) {
	    		ff.Update(
	    				String.valueOf(table.getValueAt(i,1)),
	    				String.valueOf(table.getValueAt(i,3)),
	    				Integer.parseInt(String.valueOf(table.getValueAt(i,2))),
	    				String.valueOf(table.getValueAt(i,0))		
	    				);
	    	}
	    });
		
	    //商品IDラベルとテキストフィールド
	    Label shohin_id = new Label("商品ID");
	    JTextField num = new JTextField();
		num.setPreferredSize(new Dimension(100, 25));
		
		// 商品名ラベルとテキストフィールド
		JLabel shohin_mei = new JLabel("商品名");
		JTextField mei = new JTextField();
		mei.setPreferredSize(new Dimension(100, 25));
		
		// 商品の値段ラベルとテキストフィールド
		JLabel nedan = new JLabel("商品の値段");
		JTextField n = new JTextField();
		n.setPreferredSize(new Dimension(100, 25));
	 
		// 組織名ラベルとテキストフィールド
		JLabel soshiki = new JLabel("組織名");
		JTextField s = new JTextField();
		s.setPreferredSize(new Dimension(100, 25));
	  	
		 // 商品の追加するボタンを作成
	    JButton add = new JButton("商品の追加");
	    // ボタンを押した時の処理を設定
	    add.addActionListener(e -> {
	    	if (table.isEditing()) {
                table.getCellEditor().stopCellEditing();
            }
	    	ff.Add(num.getText(), mei.getText(), Integer.parseInt(n.getText()), soshiki.getText());
	    });
	    
		 // メニューのページに移動するボタンを作成
	    JButton back = new JButton("メインメニュー");
	    // ボタンを押した時の処理を設定
	    back.addActionListener(e -> {
	    	new Main();
	    	Add_Modify.setVisible(false);
	    });
	    // ラベルとテキストフィールドの配置
 		gbc.gridx = 0;
 		gbc.gridy = 2;
 		gbc.gridwidth = 1;
 		gbc.fill = GridBagConstraints.NONE;
 		Add_Modify.add(shohin_id, gbc);

 		gbc.gridx = 1;
 		Add_Modify.add(num, gbc);

 		gbc.gridx = 2;
 		Add_Modify.add(shohin_mei, gbc);

 		gbc.gridx = 3;
 		Add_Modify.add(mei, gbc);

 		gbc.gridx = 0;
 		gbc.gridy = 3;
 		Add_Modify.add(nedan, gbc);
    
 		gbc.gridx = 1;
		Add_Modify.add(n, gbc);

		gbc.gridx = 2;
		Add_Modify.add(soshiki, gbc);

		gbc.gridx = 3;
		Add_Modify.add(s, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		Add_Modify.add(add, gbc);

		// メインメニューに戻るボタンの配置
		gbc.gridx = 3;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		Add_Modify.add(back, gbc);
			
	    //ウィンドウの表示
	    Add_Modify.setVisible(true);
	}
}
