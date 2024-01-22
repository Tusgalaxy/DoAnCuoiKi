package RanSanMoi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.JDialog;
import javax.swing.JEditorPane;

public class Menu extends JDialog{
	
	private JButton startButton;
	private JButton continueButton;
	private JButton recordButton;
	private JButton exitButton;
	
	public Menu(JFrame parent) {
		super(parent, "Menu", true);
		setUndecorated(true);
		
		setLayout(null);
		setSize(300, 400);
		setLocationRelativeTo(parent);
		
		getContentPane().setBackground(new Color(205, 133, 63));
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
        ((JComponent) getContentPane()).setBorder(border);
		
		startButton = new JButton("Play");
		startButton.setBounds(64, 50, 172, 50);
		ImageIcon playIcon = new ImageIcon("res/play.png");
		startButton.setIcon(playIcon);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String playerName = JOptionPane.showInputDialog(parent, "Enter your name: ");
				if(playerName != null && !playerName.trim().isEmpty()) {
				setVisible(false);
				
				Screen.PlayerName = playerName;
				
				((Frame) parent).startGame(playerName);
				}
			}
		});
		startButton.setEnabled(true);
		add(startButton);
		
		continueButton = new JButton("Continue");
        continueButton.setBounds(64, 120, 172, 50);
        ImageIcon continueIcon = new ImageIcon("res/continue.png");
        continueButton.setIcon(continueIcon);
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ((Frame) parent).resumeGame();
            }
        });
        continueButton.setEnabled(false);
        add(continueButton);
        
        recordButton = new JButton("Record");
        recordButton.setBounds(64, 190, 172, 50);
        ImageIcon recordIcon = new ImageIcon("res/record.png");
        recordButton.setIcon(recordIcon);
        recordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRecordDialog();
            }
        });
        recordButton.setEnabled(true);
        add(recordButton);
		
		exitButton = new JButton("Exit");
		exitButton.setBounds(64, 260, 172, 50);
		ImageIcon exitIcon = new ImageIcon("res/exit.png");
        exitButton.setIcon(exitIcon);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setEnabled(true);
		add(exitButton);
		
	}
	public void setExitButtonEnabled(boolean enabled) {
        exitButton.setEnabled(enabled);
    }
	public void setContinueButtonEnabled(boolean enabled) {
        continueButton.setEnabled(enabled);
    }
	public void setStartButtonEnabled(boolean enabled) {
        startButton.setEnabled(enabled);
    }
	
	private static final String url = "jdbc:mysql://127.0.0.1:3306/record";
    private static final String userName = "TU";
    private static final String passWord = "ax00@1cm";
    
    private void showRecordDialog() {
    	
    	try(Connection connection = DriverManager.getConnection(url, userName, passWord)){
    		String query = "SELECT name, score FROM record ORDER BY score DESC";
    		try(PreparedStatement pre = connection.prepareStatement(query)){
    			ResultSet resultSet = pre.executeQuery();
                StringBuilder records = new StringBuilder("<html><body style='font-family: \"Times New Roman\", Times; color: #333; padding: 10px; text-align: center; vertical-align: middle; font-size: 18px;'>");
                
                while(resultSet.next()) {
                	String playerName = resultSet.getString("name");
                    int score = resultSet.getInt("score");
                    records.append("<div style='margin-bottom: 5px;'>")
                    .append("<span style='font-weight: bold;'>").append(playerName).append("</span>: ")
                    .append("<span>").append(score).append("</span>")
                    .append("</div>");
                }
                records.append("</body></html>");
                
                JEditorPane editorPane = new JEditorPane("text/html", records.toString());
                editorPane.setEditable(false);
                editorPane.setPreferredSize(new Dimension(200, 200));
                
                JScrollPane scrollPane = new JScrollPane(editorPane);
                
                JOptionPane.showMessageDialog(this, scrollPane, "Top Players", JOptionPane.INFORMATION_MESSAGE);
    		}
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    		
    }
	
}
