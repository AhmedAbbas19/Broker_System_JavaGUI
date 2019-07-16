package projectOne;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

import net.proteanit.sql.DbUtils;
import java.awt.Cursor;

public class ClientDetailsFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Connection conn = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientDetailsFrame frame = new ClientDetailsFrame();
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
	public ClientDetailsFrame(){}
//	final String param
	
	public ClientDetailsFrame(final String param) {
		conn = sqliteConnection.dbConnection();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 80, 550, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setIcon(new ImageIcon("C:\\Users\\ahmed\\Downloads\\Misc-User-icon (2).png"));
		label.setBounds(29, 46, 128, 156);
		contentPane.add(label);
		
		JLabel lblFirstName = new JLabel("Full Name:");
		lblFirstName.setForeground(new Color(25,122,246));
		lblFirstName.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblFirstName.setBounds(224, 67, 70, 14);
		contentPane.add(lblFirstName);
		
		JLabel lbl_fname = new JLabel("Mahmoud");
		lbl_fname.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lbl_fname.setBounds(330, 67, 89, 14);
		contentPane.add(lbl_fname);
		
		JLabel lbl_sname = new JLabel("Mahmoud");
		lbl_sname.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lbl_sname.setBounds(394, 67, 81, 14);
		contentPane.add(lbl_sname);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(new Color(25,122,246));
		lblUsername.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblUsername.setBounds(224, 92, 70, 14);
		contentPane.add(lblUsername);
		
		final JLabel lbl_username = new JLabel("MahmoudKabaka10");
		lbl_username.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lbl_username.setBounds(330, 92, 145, 14);
		contentPane.add(lbl_username);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(25,122,246));
		lblEmail.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblEmail.setBounds(224, 117, 70, 14);
		contentPane.add(lblEmail);
		
		JLabel lbl_email = new JLabel("hamada201466@yahoo.com");
		lbl_email.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lbl_email.setBounds(330, 117, 197, 14);
		contentPane.add(lbl_email);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setForeground(new Color(25,122,246));
		lblPhoneNumber.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		lblPhoneNumber.setBounds(224, 142, 99, 14);
		contentPane.add(lblPhoneNumber);
		
		JLabel lbl_phone = new JLabel("01270978450");
		lbl_phone.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lbl_phone.setBounds(330, 142, 99, 14);
		contentPane.add(lbl_phone);
		
		JLabel label_1 = new JLabel("Contact Person:");
		label_1.setForeground(new Color(25,122,246));
		label_1.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		label_1.setBounds(224, 167, 99, 14);
		contentPane.add(label_1);
		
		JLabel lbl_contactPerson = new JLabel("Ahmed Mamdouh");
		lbl_contactPerson.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		lbl_contactPerson.setBounds(330, 167, 99, 14);
		contentPane.add(lbl_contactPerson);
		
		
		try {
			String query = "select * from Client where UserName like ?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1, '%'+param+'%');
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
			lbl_fname.setText(rs.getString("FirstName"));
			lbl_sname.setText(rs.getString("SurName"));
			lbl_username.setText(rs.getString("UserName"));
			lbl_email.setText(rs.getString("EmailAddress"));
			lbl_phone.setText(rs.getString("PhoneNumber"));
			lbl_contactPerson.setText(rs.getString("ContactPerson"));
			
			break;
			}
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		final JLabel lblTotalGain = new JLabel("");
		lblTotalGain.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblTotalGain.setBounds(29, 520, 161, 14);
		contentPane.add(lblTotalGain);
		
		final JLabel lbl_gain = new JLabel("");
		lbl_gain.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lbl_gain.setBounds(198, 520, 77, 14);
		contentPane.add(lbl_gain);
		
		final JLabel lbl_totalReceived = new JLabel("");
		lbl_totalReceived.setForeground(new Color(25, 122, 246));
		lbl_totalReceived.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lbl_totalReceived.setBounds(330, 520, 116, 14);
		contentPane.add(lbl_totalReceived);
		
		final JLabel lbl_received = new JLabel("");
		lbl_received.setForeground(new Color(25, 122, 246));
		lbl_received.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lbl_received.setBounds(456, 520, 57, 14);
		contentPane.add(lbl_received);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 395, 514, 114);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(scrollPane);
		
		table = new JTable()
		{

		    @Override
		    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		        Component c = super.prepareRenderer(renderer, row, column);

		        if (!isRowSelected(row)) {
		            c.setBackground(getBackground());
		            Integer type = (Integer) table.getModel().getValueAt(table.convertRowIndexToModel(row), 6);
		            if (type.equals(1)) c.setBackground(new Color (25,122,246));
		            if (type.equals(0)) c.setBackground(new Color (255,255,255));
		        }

		        return c;
		    }
		};
		table.setRowHeight(22);
		table.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));

		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setViewportView(table);
		
		JLabel lblSearchTransactions = new JLabel("Browse Transactions");
		lblSearchTransactions.setForeground(new Color(25, 122, 246));
		lblSearchTransactions.setFont(new Font("Verdana", Font.BOLD, 16));
		lblSearchTransactions.setBounds(29, 243, 197, 27);
		contentPane.add(lblSearchTransactions);
		
		JLabel label_5 = new JLabel("Agent % between");
		label_5.setForeground(Color.LIGHT_GRAY);
		label_5.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		label_5.setBounds(39, 281, 111, 18);
		contentPane.add(label_5);
		
		final JComboBox<Integer> comboBox = new JComboBox<Integer>();
		comboBox.setMaximumRowCount(10);
		comboBox.setForeground(Color.WHITE);
		comboBox.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		comboBox.setBackground(new Color(25, 122, 246));
		comboBox.setBounds(150, 281, 54, 18);
		contentPane.add(comboBox);
		
		JLabel label_6 = new JLabel("&");
		label_6.setForeground(Color.LIGHT_GRAY);
		label_6.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		label_6.setBounds(224, 281, 23, 18);
		contentPane.add(label_6);
		
		final JComboBox<Integer> comboBox_1 = new JComboBox<Integer>();
		comboBox_1.setMaximumRowCount(10);
		comboBox_1.setForeground(Color.WHITE);
		comboBox_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		comboBox_1.setBackground(new Color(25, 122, 246));
		comboBox_1.setBounds(254, 281, 54, 18);
		comboBox_1.addItem(100);
		contentPane.add(comboBox_1);
		
		JLabel label_4 = new JLabel("Date");
		label_4.setForeground(Color.LIGHT_GRAY);
		label_4.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		label_4.setBounds(39, 310, 30, 18);
		contentPane.add(label_4);
		
		final JComboBox<String> cb_year = new JComboBox<String>();
		cb_year.setForeground(Color.WHITE);
		cb_year.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cb_year.setBackground(new Color(25, 122, 246));
		cb_year.setBounds(150, 310, 54, 18);
		cb_year.addItem("----");
		contentPane.add(cb_year);
		
		final JComboBox<String> cb_month = new JComboBox<String>();
		cb_month.setMaximumRowCount(13);
		cb_month.setForeground(Color.WHITE);
		cb_month.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cb_month.setBackground(new Color(25, 122, 246));
		cb_month.setBounds(209, 310, 54, 18);
		cb_month.addItem("--");
		contentPane.add(cb_month);
		
		final JComboBox<String> cb_day = new JComboBox<String>();
		cb_day.setMaximumRowCount(10);
		cb_day.setForeground(Color.WHITE);
		cb_day.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cb_day.setBackground(new Color(25, 122, 246));
		cb_day.setBounds(267, 310, 41, 18);
		cb_day.addItem("--");
		contentPane.add(cb_day);
		
		for(Integer i=0;i<100;i++){comboBox.addItem(i+1);}
		for(Integer i=0;i<100;i++){comboBox_1.addItem(i+1);}
		for(Integer i=2018;i<2030;i++){cb_year.addItem(i.toString());}
		for(Integer i=1;i<=12;i++){cb_month.addItem((i<10)?'0'+i.toString():i.toString());}
		for(Integer i=1;i<=30;i++){cb_day.addItem((i<10)?'0'+i.toString():i.toString());}
		
		JButton button = new JButton("Filter");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select Agent_UserName, Client_UserName,Invoice.InvoiceNumber,CommissionPercent,PaidAmount,DueDate, Paid from Invoice"; 
							query+= " join Payments on Invoice.InvoiceNumber = Payments.InvoiceNumber and Invoice.Client_UserName like ? and (CommissionPercent >=? and CommissionPercent <=?)";
							if(cb_year.getSelectedItem() !="----"){
								if(cb_month.getSelectedItem() !="--" &&cb_day.getSelectedItem() !="--"){
									query+= " and DueDate >= date('now') and DueDate <= date(?)";
								}else{
									JOptionPane.showMessageDialog(null, "Please enter full date!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
								}
							}
							
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, '%'+param+'%');
					pst.setInt(2, (Integer) comboBox.getSelectedItem());
					pst.setInt(3, (Integer) comboBox_1.getSelectedItem());
					String Date = "";
					Date+= cb_year.getSelectedItem()+"-"+cb_month.getSelectedItem()+"-"+cb_day.getSelectedItem();
					try{pst.setString(4, Date);}catch(Exception e){}
					
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					rs = pst.executeQuery();
					
					Double cost = 0.0,received = 0.0;
					while(rs.next()){
						if(rs.getInt("Paid")==0){
							cost+= rs.getDouble("PaidAmount");
						}else{
							received+= rs.getDouble("PaidAmount");
						}
					}
					
					if(cost > 0){
						lblTotalGain.setText("Balance Of Commission =");
						lbl_gain.setText(cost.toString());
					}else{
						lblTotalGain.setText("");
						lbl_gain.setText("");
					}
					
					lbl_totalReceived.setText("Total Collected =");
					lbl_received.setText(received.toString());
					
					pst.close();
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		button.setFocusPainted(false);
		button.setBackground(new Color(25, 122, 246));
		button.setBounds(39, 339, 269, 33);
		contentPane.add(button);
		
		JButton btnNewButton = new JButton("Edit");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateClientFrame UC = new updateClientFrame(lbl_username.getText());
				UC.setVisible(true);
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnNewButton.setBounds(424, 22, 89, 23);
		contentPane.add(btnNewButton);
		
		
	}

}
