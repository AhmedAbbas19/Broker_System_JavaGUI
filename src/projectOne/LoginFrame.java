package projectOne;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JPasswordField;

import org.jdesktop.swingx.prompt.PromptSupport;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tf_username;
	private JPasswordField passwordField;
	Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public LoginFrame(){
		initialize();
	}
	
	public void initialize() {
		conn = sqliteConnection.dbConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 80, 550, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tf_username = new JTextField();
		PromptSupport.setPrompt("Username", tf_username);
		tf_username.setForeground(new Color(190, 190, 190));
		tf_username.setColumns(10);
		tf_username.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_username.setBackground(Color.WHITE);
		tf_username.setBounds(123, 200, 281, 33);
		contentPane.add(tf_username);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query = "select * from Admin where UserName = ? and Password = ?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, tf_username.getText());
					pst.setString(2, passwordField.getText());
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next()){
						count+=1;
					}
					if(count>0){
						mainframe mf = new mainframe();
						mf.setVisible(true);
						
					}
					
				}catch(Exception e){
					
				}
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnLogin.setFocusPainted(false);
		btnLogin.setBackground(new Color(25, 122, 246));
		btnLogin.setBounds(123, 303, 281, 33);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		PromptSupport.setPrompt("Password", passwordField);
		passwordField.setForeground(new Color(190, 190, 190));
		passwordField.setColumns(10);
		passwordField.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		passwordField.setBackground(Color.WHITE);
		passwordField.setBounds(123, 244, 281, 33);
		contentPane.add(passwordField);
	}
}
