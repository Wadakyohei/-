package femmefatale;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {
	
		new Main();
		
	}
	
	public Main() {
		//Framインスタンスの作成
		JFrame home = new JFrame();
		//ウィンドウタイトルの名前
		home.setTitle("Mainmenu");
		//ウィンドウの大きさの設定
		home.setSize(700, 400);
	    //初期位置の設定
		home.setLocation(200, 10);
	    //×ボタン押したらウィンドウを閉じる
		home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    // GridBagLayoutを設定
        home.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(5, 5, 5, 5); // パディングを設定
		
		//画像を表示させるファイルパスとラベルの作成
		ImageIcon icon = new ImageIcon("C:\\pleiades\\workspace\\Arcana\\Image\\FEMME_FATALE.png");
		Image smallImg = icon.getImage().getScaledInstance((int) (icon.getIconWidth() * 0.5), -1,Image.SCALE_SMOOTH);
		ImageIcon smallIcon = new ImageIcon(smallImg);
		JLabel label = new JLabel(smallIcon);
		
        // 画像をウィンドウの上部に配置
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        home.add(label, gbc);
		
		// ボタンパネルの作成
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
		
       	// 受注のページに移動するボタンを作成
	    JButton order = new JButton("受注");
	    // 追加・変更のページに移動するボタンを作成
	    JButton add_modify = new JButton("追加・変更");
		// 受注の確認ページに移動するボタンを作成
	    JButton confirm = new JButton("受注の確認");
	    //終了ボタンの作成
	    JButton fin = new JButton("プログラムを終了する");
        
        // ボタンの配置
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonGbc.insets = new java.awt.Insets(5, 5, 5, 5);
        buttonGbc.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(order, buttonGbc);

        buttonGbc.gridy = 1;
        buttonPanel.add(add_modify, buttonGbc);

        buttonGbc.gridy = 2;
        buttonPanel.add(confirm, buttonGbc);

        buttonGbc.gridy = 3;
        buttonPanel.add(fin, buttonGbc);
        
	    // ボタンを押した時の処理を設定
	    order.addActionListener(e -> {
	    	new Order();
	    	home.setVisible(false);
	    });
	    
	    // ボタンを押した時の処理を設定
	    add_modify.addActionListener(e -> {
	    	new Add_Modify();
	    	home.setVisible(false);
	    });
	   
	    // ボタンを押した時の処理を設定
	    confirm.addActionListener(e -> {
	    	new Confirm();
	    	home.setVisible(false);
	    });
	    
	    //ボタンを押したときの処理
	    fin.addActionListener(e -> {
	    	System.exit(0);
	    });
	    
	    // 注文ボタンをウィンドウに追加する
	    buttonPanel.add(order);
	    // 追加・変更ボタンをウィンドウに追加する
	    buttonPanel.add(add_modify);
	    // 受注の確認ボタンをウィンドウに追加する
	    buttonPanel.add(confirm);
	    // 終了ボタンをウィンドウに追加する
	    buttonPanel.add(fin);
	    
	    // ボタンパネルをウィンドウの下部に配置
        gbc.gridy = 1;
        gbc.weighty = 0.5;
        home.add(buttonPanel, gbc);
	    
	    //ウィンドウの表示
	    home.setVisible(true);
	}
}
