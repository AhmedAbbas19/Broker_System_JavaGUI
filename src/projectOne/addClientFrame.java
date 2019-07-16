package projectOne;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jdesktop.swingx.prompt.PromptSupport;

public class addClientFrame extends JFrame {

	private JPanel contentPane;
	Connection conn = null;
	private JTextField tf_fname;
	private JTextField tf_surname;
	private JTextField tf_username;
	private JTextField tf_contactPerson;
	private JTextField tf_email;
	private JTextField tf_phone;
	private JLabel lblDeleteAgent;
	private JTextField tf_delUsername;
	private JButton btnDelete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addClientFrame frame = new addClientFrame();
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
	public addClientFrame() {
		setTitle("Add Client");
		conn = sqliteConnection.dbConnection();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 80, 520, 550);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tf_fname = new JTextField();
		PromptSupport.setPrompt("First Name", tf_fname);
		tf_fname.setForeground(new Color(190, 190, 190));
		tf_fname.setColumns(10);
		tf_fname.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_fname.setBackground(Color.WHITE);
		tf_fname.setBounds(31, 68, 202, 33);
		contentPane.add(tf_fname);
		
		JLabel lblAddAgent = new JLabel("Add Client");
		lblAddAgent.setForeground(new Color(25, 122, 246));
		lblAddAgent.setFont(new Font("Verdana", Font.BOLD, 16));
		lblAddAgent.setBounds(31, 30, 164, 27);
		contentPane.add(lblAddAgent);
		
		tf_surname = new JTextField();
		PromptSupport.setPrompt("Sur Name", tf_surname);
		tf_surname.setForeground(new Color(190, 190, 190));
		tf_surname.setColumns(10);
		tf_surname.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_surname.setBackground(Color.WHITE);
		tf_surname.setBounds(270, 68, 202, 33);
		contentPane.add(tf_surname);
		
		tf_username = new JTextField();
		PromptSupport.setPrompt("Client Username", tf_username);
		tf_username.setForeground(new Color(190, 190, 190));
		tf_username.setColumns(10);
		tf_username.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_username.setBackground(Color.WHITE);
		tf_username.setBounds(31, 112, 202, 33);
		contentPane.add(tf_username);
		
		tf_contactPerson = new JTextField();
		PromptSupport.setPrompt("Contact Person", tf_contactPerson);
		tf_contactPerson.setForeground(new Color(190, 190, 190));
		tf_contactPerson.setColumns(10);
		tf_contactPerson.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_contactPerson.setBackground(Color.WHITE);
		tf_contactPerson.setBounds(270, 112, 202, 33);
		contentPane.add(tf_contactPerson);
		
		tf_email = new JTextField();
		PromptSupport.setPrompt("Email", tf_email);
		tf_email.setForeground(new Color(190, 190, 190));
		tf_email.setColumns(10);
		tf_email.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_email.setBackground(Color.WHITE);
		tf_email.setBounds(31, 156, 202, 33);
		contentPane.add(tf_email);
		
		tf_phone = new JTextField();
		PromptSupport.setPrompt("Phone Number", tf_phone);
		tf_phone.setForeground(new Color(190, 190, 190));
		tf_phone.setColumns(10);
		tf_phone.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_phone.setBackground(Color.WHITE);
		tf_phone.setBounds(270, 156, 202, 33);
		contentPane.add(tf_phone);
		
		JButton btnAddAgent = new JButton("ADD");
		btnAddAgent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "insert into Client (FirstName,SurName,UserName,EmailAddress,ContactPerson,PhoneNumber) values (?,?,?,?,?,?)";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, tf_fname.getText());
					pst.setString(2, tf_surname.getText());
					pst.setString(3, tf_username.getText());
					pst.setString(4, tf_email.getText());
					pst.setString(5, tf_contactPerson.getText());
					pst.setString(6, tf_phone.getText());
					if(tf_fname.getText().isEmpty()||tf_surname.getText().isEmpty()||tf_username.getText().isEmpty()||tf_email.getText().isEmpty()||tf_contactPerson.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Please fill all fields!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
					}else{
						pst.execute();
						JOptionPane.showMessageDialog(null, "Client added successfuly", "Note!", JOptionPane.INFORMATION_MESSAGE);
					}
					pst.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Username is already used!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAddAgent.setForeground(Color.WHITE);
		btnAddAgent.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnAddAgent.setFocusPainted(false);
		btnAddAgent.setBackground(new Color(25, 122, 246));
		btnAddAgent.setBounds(31, 200, 441, 33);
		contentPane.add(btnAddAgent);
		
		lblDeleteAgent = new JLabel("Delete Client");
		lblDeleteAgent.setForeground(new Color(25, 122, 246));
		lblDeleteAgent.setFont(new Font("Verdana", Font.BOLD, 16));
		lblDeleteAgent.setBounds(31, 307, 164, 27);
		contentPane.add(lblDeleteAgent);
		
		tf_delUsername = new JTextField();
		PromptSupport.setPrompt("Client Username", tf_delUsername);
		tf_delUsername.setForeground(new Color(190, 190, 190));
		tf_delUsername.setColumns(10);
		tf_delUsername.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_delUsername.setBackground(Color.WHITE);
		tf_delUsername.setBounds(31, 349, 202, 33);
		contentPane.add(tf_delUsername);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select * from Client where Username = ?";
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, tf_delUsername.getText());
					ResultSet rs = pst.executeQuery();
					
					int count = 0;
					while(rs.next()){count+=1;}
					
					if(count==0){
						JOptionPane.showMessageDialog(null, "Username not found!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
					}else{
						query = "delete from Client where UserName = ?";
						pst = conn.prepareStatement(query);
						pst.setString(1, tf_delUsername.getText());
						pst.execute();
						JOptionPane.showMessageDialog(null, "Client deleted successfuly", "Note!", JOptionPane.INFORMATION_MESSAGE);
					}
					pst.close();
					rs.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e, "Something went wrong!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnDelete.setFocusPainted(false);
		btnDelete.setBackground(new Color(25, 122, 246));
		btnDelete.setBounds(254, 349, 218, 33);
		contentPane.add(btnDelete);
	}

}
