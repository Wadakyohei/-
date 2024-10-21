package femmefatale;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class Order implements TableModelListener{
	
	FemmeFataleDBConnect ff = new FemmeFataleDBConnect();
	List<String> shohin = ff.selectShohin_mei();
	List<String> soshiki_mei = ff.selectSoshiki_mei();
	int cnt = 0;
	int fast = 0;
	
	public Order() {
		String[][] add = new String[shohin.size()][3];
		
		//Framインスタンスの作成
		JFrame order = new JFrame();
		//ウィンドウタイトルの名前
		order.setTitle("Order");
		//ウィンドウの大きさの設定
		order.setSize(700, 400);
	    //初期位置の設定
		order.setLocation(200, 10);
	    //×ボタン押したらウィンドウを閉じる
		order.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// GridBagLayoutを設定
		order.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new java.awt.Insets(5, 5, 5, 5); // パディングを設定
		
		//プルダウンボックスの追加
		JLabel lblWeapon = new JLabel("武器名:");
		JComboBox<String> wepon = new JComboBox<>();
		for(String s : shohin) {
			wepon.addItem(s);
		}
		
		//テキストボックスの追加
		JLabel lblQuantity = new JLabel("個数:");
		JTextField num = new JTextField();
		num.setPreferredSize(new Dimension(50, 25));
		
		 // 特急を受けるボタンを作成
		JLabel lblFast = new JLabel("FASTの有無:");
	    JButton fas = new JButton("FAST");
	    // ボタンを押した時の処理を設定
	    fas.addActionListener(e -> {
	    	fast = 1;
	    	
	    });		
	    
	    //一時表示のテーブルの作成
	    String[] titl = {"武器名","個数","FAST有無"};
	    JTable table = new JTable(add, titl);
	    JScrollPane sp = new JScrollPane(table);
	    sp.setPreferredSize(new Dimension(100, 50)); // スクロールパネルのサイズ設定
	    
		 // 受注を受けるボタンを作成
	    JButton od = new JButton("追加");
	    // ボタンを押した時の処理を設定
	    od.addActionListener(e -> {
	    	String str = wepon.getItemAt(wepon.getSelectedIndex());
    		for(int i = 0;i < 3;i++) {
    			if(i == 0) {
    				//addに注文する武器名を入れる
    				  table.setValueAt(str, cnt, i);
    			}else if(i == 1) {
    				//addに個数を入れる
    				table.setValueAt(num.getText(),cnt,i);
    			}else {
    				//addに特急かどうかを入れる
    				if(fast == 1) {
    					table.setValueAt("あり",cnt,i);
    				}else {
    					table.setValueAt("なし",cnt,i);
    				}
    			}
    		}
	    	cnt++;
	    	fast = 0;
	    });
	    
		//プルダウンボックスの追加
	    JLabel lblSoshiki = new JLabel("組織名:");
		JComboBox<String> soshiki = new JComboBox<>();
		for(String s : soshiki_mei) {
			soshiki.addItem(s);
		}
		
		//受注日をいれるための日付確認
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String day = sdf.format(date);
		
	    // 受注完了のボタンを作成
	    JButton odfin = new JButton("受注完了");
	    // ボタンを押した時の処理を設定
	    odfin.addActionListener(e -> {
	    	String str = soshiki.getItemAt(soshiki.getSelectedIndex());
	    	for(int i = 0; i < cnt; i++) {
	    		ff.AddJutyu(
	    		str,		
	    		String.valueOf(table.getValueAt(i,0)),//武器名
	    		Integer.parseInt(String.valueOf(table.getValueAt(i,1))),//個数
	    		String.valueOf(table.getValueAt(i,2)),//FAST
	    		day
	    		);
	    	}
	    	cnt = 0;
	    	new Main();
			order.setVisible(false);
	    });
		
		 // メニューのページに移動するボタンを作成
		JButton back = new JButton("メインメニュー");
		// ボタンを押した時の処理を設定
		back.addActionListener(e -> {
			new Main();
			order.setVisible(false);
		});
	    
		 // コンポーネントの配置
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        order.add(lblWeapon, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        order.add(wepon, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        order.add(lblQuantity, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        order.add(num, gbc);

        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        order.add(lblFast, gbc);

        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        order.add(fas, gbc);

        gbc.gridx = 7;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        order.add(od, gbc); // 追加ボタンをスクロールパネルの上に配置

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 8;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        order.add(sp, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        order.add(lblSoshiki, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        order.add(soshiki, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        order.add(odfin, gbc);

        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        order.add(back, gbc);
        
	    //ウィンドウの表示
	    order.setVisible(true);
	}
	public void tableChanged(TableModelEvent e) {	
	}
}






