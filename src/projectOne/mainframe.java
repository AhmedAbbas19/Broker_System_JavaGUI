package projectOne;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.TableCellRenderer;

import java.awt.Cursor;

import javax.swing.border.LineBorder;

import org.jdesktop.swingx.prompt.PromptSupport;

public class mainframe {

	private JFrame frmActDatabase;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JTable table;
	Connection conn = null;
	private JTextField tf_AgentUsername;
	private JTextField tf_ClientUserName;
	private JTable table_1;
	private JTextField tf_invoiceNum;
	private JTextField tf_AgentDetails;
	private JTextField tf_clientDetails;
	private JTextField tf_payInvoiceNum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainframe frame = new mainframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	
	
	protected void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public  mainframe() {
		conn = sqliteConnection.dbConnection();
		frmActDatabase = new JFrame();
		frmActDatabase.setTitle("ACT Database");
		frmActDatabase.setBounds(80, 20, 1200, 700);
		frmActDatabase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmActDatabase.getContentPane().setLayout(null);
		tabbedPane.setBounds(0, 0, 1184, 661);
		frmActDatabase.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Browse Data", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBounds(406, 159, 749, 437);
		panel.add(scrollPane);
		
		table = new JTable()
		{

		    @Override
		    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		        Component c = super.prepareRenderer(renderer, row, column);

		        if (!isRowSelected(row)) {
		            c.setBackground(getBackground());
		            if (row%2==0) {
		            	c.setBackground(new Color (25,122,246));
		            	c.setForeground(new Color (255,255,255));
		            }
		            else{
		            	c.setBackground(new Color (255,255,255));
		            	c.setForeground(new Color (0,0,0));
		            }
		        }

		        return c;
		    }
		};
		table.setRowHeight(22);
		table.setGridColor(SystemColor.desktop);
		table.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		table.setForeground(SystemColor.desktop);
		table.setBackground(SystemColor.controlLtHighlight);
		scrollPane.setViewportView(table);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 255));
		panel_6.setBounds(0, 0, 382, 633);
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		JButton btn_BrowseClient = new JButton("Browse Clients");
		btn_BrowseClient.setBounds(43, 319, 267, 33);
		panel_6.add(btn_BrowseClient);
		btn_BrowseClient.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_BrowseClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select FirstName, SurName, UserName, EmailAddress, PhoneNumber, ContactPerson from Client";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btn_BrowseClient.setForeground(Color.WHITE);
		btn_BrowseClient.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btn_BrowseClient.setFocusPainted(false);
		btn_BrowseClient.setBackground(new Color(25, 122, 246));
		
		JButton btn_BrowseAgent = new JButton("Browse Agents");
		btn_BrowseAgent.setBounds(43, 265, 267, 33);
		panel_6.add(btn_BrowseAgent);
		btn_BrowseAgent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_BrowseAgent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select FirstName, SurName, UserName, EmailAddress, PhoneNumber, ContactPerson from Agent";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btn_BrowseAgent.setForeground(Color.WHITE);
		btn_BrowseAgent.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btn_BrowseAgent.setFocusPainted(false);
		btn_BrowseAgent.setBackground(new Color(25, 122, 246));
		
		JButton btn_BrowseTrans = new JButton("Browse Transactions");
		btn_BrowseTrans.setBounds(43, 210, 267, 33);
		panel_6.add(btn_BrowseTrans);
		btn_BrowseTrans.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_BrowseTrans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select ContractNumber, InvoiceNumber, Agent_UserName, Client_UserName, InvoiceAmount, CommissionPercent, AmountCollected, BalanceOfCommission, InvoiceDate from Invoice";
					PreparedStatement pst = conn.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btn_BrowseTrans.setForeground(Color.WHITE);
		btn_BrowseTrans.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btn_BrowseTrans.setFocusPainted(false);
		btn_BrowseTrans.setBackground(new Color(25, 122, 246));
		
		JLabel lblBrowseData = new JLabel("Browse Data");
		lblBrowseData.setForeground(new Color(25, 122, 246));
		lblBrowseData.setFont(new Font("Verdana", Font.BOLD, 16));
		lblBrowseData.setBounds(43, 156, 164, 27);
		panel_6.add(lblBrowseData);
		
		JLabel background = new JLabel("");
		background.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		background.setBorder(new EmptyBorder(0, 0, 0, 0));
		background.setIcon(new ImageIcon("resource\\toop.jpg"));
		background.setBounds(0, 0, 1179, 633);
		panel.add(background);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Advanced Search", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(412, 132, 733, 304);
		scrollPane_1.setOpaque(false);
		scrollPane_1.getViewport().setOpaque(false);
		scrollPane_1.setBorder(BorderFactory.createEmptyBorder());
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable()
		{

		    @Override
		    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		        Component c = super.prepareRenderer(renderer, row, column);

		        if (!isRowSelected(row)) {
		            c.setBackground(getBackground());
		            Integer type = (Integer) table_1.getModel().getValueAt(table_1.convertRowIndexToModel(row), 6);
		            if (type.equals(1)) {
		            	c.setBackground(new Color (25,122,246));
		            	c.setForeground(new Color (255,255,255));
		            }
		            if (type.equals(0)) {
		            	c.setBackground(new Color (255,255,255));
		            	c.setForeground(new Color (0,0,0));
		            }
		        }

		        return c;
		    }
		};
		table_1.setSelectionBackground(new Color(119, 136, 153));
		table_1.setRowHeight(20);
		
		table_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		table_1.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane_1.setViewportView(table_1);
		final JLabel lbl_balanceComm = new JLabel("");
		lbl_balanceComm.setForeground(Color.WHITE);
		lbl_balanceComm.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lbl_balanceComm.setBounds(908, 589, 165, 14);
		panel_1.add(lbl_balanceComm);
		
		final JLabel lbl_balance = new JLabel("");
		lbl_balance.setForeground(Color.WHITE);
		lbl_balance.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lbl_balance.setBounds(1083, 589, 73, 14);
		panel_1.add(lbl_balance);
		
		final JLabel lbl_totalCollected = new JLabel("");
		lbl_totalCollected.setForeground(new Color(25, 122, 246));
		lbl_totalCollected.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lbl_totalCollected.setBounds(908, 564, 116, 14);
		panel_1.add(lbl_totalCollected);
		
		final JLabel lbl_collected = new JLabel("");
		lbl_collected.setForeground(new Color(25, 122, 246));
		lbl_collected.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lbl_collected.setBounds(1034, 564, 57, 14);
		panel_1.add(lbl_collected);
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(0, 0, 382, 633);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblSearchPayments = new JLabel("Search Payments");
		lblSearchPayments.setBounds(46, 54, 164, 27);
		panel_3.add(lblSearchPayments);
		lblSearchPayments.setForeground(new Color(25, 122, 246));
		lblSearchPayments.setFont(new Font("Verdana", Font.BOLD, 16));
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 76, 337, 362);
		panel_3.add(panel_2);
		panel_2.setBackground(SystemColor.text);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Agent % between");
		lblNewLabel.setForeground(new Color(192, 192, 192));
		lblNewLabel.setBounds(39, 168, 111, 18);
		panel_2.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		
		final JComboBox<Integer> comboBox = new JComboBox<Integer>();
		comboBox.setUI(new BasicComboBoxUI());
		comboBox.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		comboBox.setBackground(new Color(25, 122, 246));
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.setBounds(150, 168, 54, 18);
		panel_2.add(comboBox);
		comboBox.setMaximumRowCount(10);
		
		final JComboBox<Integer> comboBox_1 = new JComboBox<Integer>();
		comboBox_1.setUI(new BasicComboBoxUI());
		comboBox_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		comboBox_1.setBackground(new Color(25, 122, 246));
		comboBox_1.setForeground(new Color(255, 255, 255));
		comboBox_1.setBounds(254, 168, 54, 18);
		comboBox_1.addItem(100);
		panel_2.add(comboBox_1);
		comboBox_1.setMaximumRowCount(10);
		
		JLabel lblAnd = new JLabel("&");
		lblAnd.setForeground(new Color(192, 192, 192));
		lblAnd.setBounds(224, 168, 23, 18);
		panel_2.add(lblAnd);
		lblAnd.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setForeground(new Color(192, 192, 192));
		lblDate.setBounds(39, 203, 30, 18);
		panel_2.add(lblDate);
		lblDate.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		
		final JComboBox<String> cb_day = new JComboBox<String>();
		cb_day.setUI(new BasicComboBoxUI());
		cb_day.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cb_day.setForeground(new Color(255, 255, 255));
		cb_day.setBackground(new Color(25, 122, 246));
		cb_day.setBounds(267, 203, 41, 18);
		cb_day.addItem("--");
		panel_2.add(cb_day);
		cb_day.setMaximumRowCount(10);
		
		final JComboBox<String> cb_year = new JComboBox<String>();
		cb_year.setUI(new BasicComboBoxUI());
		cb_year.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cb_year.setForeground(new Color(255, 255, 255));
		cb_year.setBackground(new Color(25, 122, 246));
		cb_year.setBounds(150, 203, 54, 18);
		cb_year.addItem("----");
		panel_2.add(cb_year);
		
		final JComboBox<String> cb_month = new JComboBox<String>();
		cb_month.setUI(new BasicComboBoxUI());
		cb_month.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cb_month.setForeground(new Color(255, 255, 255));
		cb_month.setBackground(new Color(25, 122, 246));
		cb_month.setBounds(209, 203, 54, 18);
		cb_month.addItem("--");
		panel_2.add(cb_month);
		cb_month.setMaximumRowCount(13);
		
		for(int i=0;i<100;i++){comboBox.addItem(i+1);}
		for(Integer i=0;i<100;i++){comboBox_1.addItem(i+1);}
		for(Integer i=1;i<=30;i++){cb_day.addItem((i<10)?'0'+i.toString():i.toString());}
		for(Integer i=2018;i<2030;i++){cb_year.addItem(i.toString());}
		for(Integer i=1;i<=12;i++){cb_month.addItem((i<10)?'0'+i.toString():i.toString());}
		
		
		tf_invoiceNum = new JTextField();
		tf_invoiceNum.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_invoiceNum.setForeground(new Color(190, 190, 190));
		PromptSupport.setPrompt("Invoice Number", tf_invoiceNum);
		tf_invoiceNum.setBounds(39, 105, 271, 33);
		tf_invoiceNum.setColumns(10);
		panel_2.add(tf_invoiceNum);
		
		JButton btn_searchAgent = new JButton("Filter");
		btn_searchAgent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_searchAgent.setFocusPainted(false);
		btn_searchAgent.setBackground(new Color(25, 122, 246));
		btn_searchAgent.setForeground(new Color(255, 255, 255));
		btn_searchAgent.setBounds(39, 240, 269, 33);
		panel_2.add(btn_searchAgent);
		btn_searchAgent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "select Agent_UserName, Client_UserName,Invoice.InvoiceNumber,CommissionPercent,PaidAmount,DueDate, Paid from Invoice"; 
							query+= " join Payments on Invoice.InvoiceNumber = Payments.InvoiceNumber and Invoice.Agent_UserName like ?";
							query+= " and Invoice.Client_UserName like ? and Payments.InvoiceNumber like ? and (CommissionPercent >=? and CommissionPercent <=?)";
							if(cb_year.getSelectedItem() !="----"){
								if(cb_month.getSelectedItem() !="--" &&cb_day.getSelectedItem() !="--"){
									query+= " and DueDate >= date('now') and DueDate <= date(?)";
								}else{
									JOptionPane.showMessageDialog(null, "Please enter full date!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
								}
							}
					PreparedStatement pst = conn.prepareStatement(query);
					pst.setString(1, '%'+tf_AgentUsername.getText()+'%');
					pst.setString(2, '%'+tf_ClientUserName.getText()+'%');
					pst.setString(3, '%'+tf_invoiceNum.getText()+'%');
					pst.setInt(4, (Integer) comboBox.getSelectedItem());
					pst.setInt(5, (Integer) comboBox_1.getSelectedItem());
					
					String Date = "";
					Date+= cb_year.getSelectedItem()+"-"+cb_month.getSelectedItem()+"-"+cb_day.getSelectedItem();
					try{pst.setString(6, Date);}catch(Exception e){}
					
					ResultSet rs = pst.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					rs = pst.executeQuery();
					
					Double balance = 0.0, collected = 0.0;
					while(rs.next()){
						if(rs.getInt("Paid")==0){
							balance+= rs.getDouble("PaidAmount");
						}else{
							collected+= rs.getDouble("PaidAmount");
						}
					}
					
					if(balance > 0){
						lbl_balanceComm.setText("Balance Of Commission =");
						lbl_balance.setText(balance.toString());
					}else{
						lbl_balanceComm.setText("");
						lbl_balance.setText("");
					}
					
					lbl_totalCollected.setText("Total Collected =");
					lbl_collected.setText(collected.toString());
					
					pst.close();
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btn_searchAgent.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		
		JButton btnNewButton = new JButton("reset");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 11));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNewButton.setBackground(new Color(25, 122, 246));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tf_AgentUsername.setText("");
				tf_ClientUserName.setText("");
				tf_invoiceNum.setText("");
				comboBox.setSelectedItem(1);
				comboBox_1.setSelectedItem(100);
				cb_day.setSelectedItem("--");
				cb_month.setSelectedItem("--");
				cb_year.setSelectedItem("----");
			}
		});
		btnNewButton.setBounds(39, 278, 269, 33);
		panel_2.add(btnNewButton);
		
		tf_ClientUserName = new JTextField();
		tf_ClientUserName.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_ClientUserName.setBounds(39, 61, 271, 33);
		panel_2.add(tf_ClientUserName);
		tf_ClientUserName.setForeground(new Color(190, 190, 190));
		PromptSupport.setPrompt("Client Username", tf_ClientUserName);
		tf_ClientUserName.setColumns(10);
		
		tf_AgentUsername = new JTextField();
		tf_AgentUsername.setBounds(39, 17, 271, 33);
		panel_2.add(tf_AgentUsername);
		tf_AgentUsername.setForeground(new Color(190, 190, 190));
		PromptSupport.setPrompt("Agent Username", tf_AgentUsername);
		tf_AgentUsername.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_AgentUsername.setBackground(SystemColor.text);
		tf_AgentUsername.setColumns(10);
		
		JLabel label_3 = new JLabel("-------------------------------------------------");
		label_3.setForeground(new Color(192, 192, 192));
		label_3.setBounds(71, 337, 244, 14);
		panel_2.add(label_3);
		
		JLabel lblPayAPayment_1 = new JLabel("Pay A Payment");
		lblPayAPayment_1.setForeground(new Color(25, 122, 246));
		lblPayAPayment_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblPayAPayment_1.setBounds(46, 438, 164, 27);
		panel_3.add(lblPayAPayment_1);
		
		tf_payInvoiceNum = new JTextField();
		PromptSupport.setPrompt("Invoice Number", tf_payInvoiceNum);
		tf_payInvoiceNum.setForeground(new Color(190, 190, 190));
		tf_payInvoiceNum.setColumns(10);
		tf_payInvoiceNum.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_payInvoiceNum.setBackground(Color.WHITE);
		tf_payInvoiceNum.setBounds(46, 476, 271, 33);
		panel_3.add(tf_payInvoiceNum);
		
		JLabel label = new JLabel("Date");
		label.setForeground(Color.LIGHT_GRAY);
		label.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		label.setBounds(48, 520, 30, 18);
		panel_3.add(label);
		
		final JComboBox<String> cb_payYear = new JComboBox<String>();
		cb_payYear.setUI(new BasicComboBoxUI());
		cb_payYear.setForeground(Color.WHITE);
		cb_payYear.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cb_payYear.setBackground(new Color(25, 122, 246));
		cb_payYear.setBounds(159, 520, 54, 18);
		panel_3.add(cb_payYear);
		
		final JComboBox<String> cb_payMonth = new JComboBox<String>();
		cb_payMonth.setUI(new BasicComboBoxUI());
		cb_payMonth.setMaximumRowCount(13);
		cb_payMonth.setForeground(Color.WHITE);
		cb_payMonth.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cb_payMonth.setBackground(new Color(25, 122, 246));
		cb_payMonth.setBounds(218, 520, 54, 18);
		panel_3.add(cb_payMonth);
		
		final JComboBox<String> cb_payDay = new JComboBox<String>();
		cb_payDay.setUI(new BasicComboBoxUI());
		cb_payDay.setMaximumRowCount(10);
		cb_payDay.setForeground(Color.WHITE);
		cb_payDay.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cb_payDay.setBackground(new Color(25, 122, 246));
		cb_payDay.setBounds(276, 520, 41, 18);
		panel_3.add(cb_payDay);
		
		for(Integer i=2018;i<=2030;i++){cb_payYear.addItem((i<10)?'0'+i.toString():i.toString());}
		for(Integer i=1;i<=12;i++){cb_payMonth.addItem((i<10)?'0'+i.toString():i.toString());}
		for(Integer i=1;i<=30;i++){cb_payDay.addItem((i<10)?'0'+i.toString():i.toString());}
		
		JButton btnPay = new JButton("PAY");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tf_payInvoiceNum.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Please Enter Invoice Number!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
				}else if(cb_payYear.getSelectedItem()=="----"||cb_payMonth.getSelectedItem()=="--"||cb_payDay.getSelectedItem()=="--"){
					JOptionPane.showMessageDialog(null, "Enter full date!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
				}else{
					try {
						String query = "select * from Payments where InvoiceNumber = ? and DueDate = date(?)";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.setString(1, tf_payInvoiceNum.getText());
						String DueDate = cb_payYear.getSelectedItem()+"-"+cb_payMonth.getSelectedItem()+"-"+cb_payDay.getSelectedItem();
						pst.setString(2, DueDate);
						ResultSet rs = pst.executeQuery();
						int count = 0,state = 0;
						while(rs.next()){
							state = rs.getInt("Paid");
							count+=1;
							break;
						}
						if(count>0){
							if(state==0){
								query = "update Payments set Paid = 1 where InvoiceNumber = ? and DueDate = date(?)";
								pst = conn.prepareStatement(query);
								pst.setString(1, tf_payInvoiceNum.getText());
								DueDate = cb_payYear.getSelectedItem()+"-"+cb_payMonth.getSelectedItem()+"-"+cb_payDay.getSelectedItem();
								pst.setString(2, DueDate);
								pst.execute();
								JOptionPane.showMessageDialog(null, "Paid Successfuly", "Something went wrong!", JOptionPane.INFORMATION_MESSAGE);
							}else{
								JOptionPane.showMessageDialog(null, "Already Paid!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
							}
						}else{
							JOptionPane.showMessageDialog(null, "Enter a valid Invoice Number and Date!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
						}
						pst.close();
						rs.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnPay.setForeground(Color.WHITE);
		btnPay.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnPay.setFocusPainted(false);
		btnPay.setBackground(new Color(25, 122, 246));
		btnPay.setBounds(46, 549, 271, 33);
		panel_3.add(btnPay);
		
		
		
		JLabel lbl_background = new JLabel("");
		lbl_background.setBackground(SystemColor.activeCaption);
		lbl_background.setIcon(new ImageIcon("resource\\toop.jpg"));
		lbl_background.setBounds(0, 0, 1179, 633);
		panel_1.add(lbl_background);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Agents & Clients", null, panel_5, null);
		panel_5.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 255, 255));
		panel_7.setBounds(0, 0, 382, 633);
		panel_5.add(panel_7);
		panel_7.setLayout(null);
		
		tf_AgentDetails = new JTextField();
		tf_AgentDetails.setBounds(49, 104, 271, 33);
		panel_7.add(tf_AgentDetails);
		PromptSupport.setPrompt("Agent Username", tf_AgentDetails);
		tf_AgentDetails.setForeground(new Color(190, 190, 190));
		tf_AgentDetails.setColumns(10);
		tf_AgentDetails.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_AgentDetails.setBackground(Color.WHITE);
		
		JButton btnShowDetails_1 = new JButton("Show Details");
		btnShowDetails_1.setBounds(49, 148, 271, 33);
		panel_7.add(btnShowDetails_1);
		btnShowDetails_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tf_AgentDetails.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Please Enter Username!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
				}else{
					
					try {
						String query = "select * from Agent where UserName like ?";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.setString(1, '%'+tf_AgentDetails.getText()+'%');
						ResultSet rs = pst.executeQuery();
						int count = 0;
						while(rs.next()){
							count+=1;
						}
						if(count>0){
							String param = tf_AgentDetails.getText();
							AgentDetails AD = new AgentDetails(param);
							AD.setVisible(true);
						}else{
							JOptionPane.showMessageDialog(null, "Username not found!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
						}
						pst.close();
						rs.close();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnShowDetails_1.setForeground(Color.WHITE);
		btnShowDetails_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnShowDetails_1.setFocusPainted(false);
		btnShowDetails_1.setBackground(new Color(25, 122, 246));
		
		JButton btnAddDelete = new JButton("Add / Delete Agent");
		btnAddDelete.setBounds(49, 374, 271, 33);
		panel_7.add(btnAddDelete);
		btnAddDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addAgentframe f = new addAgentframe();
				f.setVisible(true);
			}
		});
		btnAddDelete.setForeground(Color.WHITE);
		btnAddDelete.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnAddDelete.setFocusPainted(false);
		btnAddDelete.setBackground(new Color(25, 122, 246));
		
		JButton btnAddDelete_1 = new JButton("Add / Delete Client");
		btnAddDelete_1.setBounds(49, 418, 271, 33);
		panel_7.add(btnAddDelete_1);
		btnAddDelete_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addClientFrame AC = new addClientFrame();
				AC.setVisible(true);
			}
		});
		btnAddDelete_1.setForeground(Color.WHITE);
		btnAddDelete_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnAddDelete_1.setFocusPainted(false);
		btnAddDelete_1.setBackground(new Color(25, 122, 246));
		
		JButton btnNewTransaction = new JButton("New Transaction");
		btnNewTransaction.setBounds(49, 462, 271, 33);
		panel_7.add(btnNewTransaction);
		btnNewTransaction.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addInvoiceFrame AI = new addInvoiceFrame();
				AI.setVisible(true);
			}
		});
		btnNewTransaction.setForeground(Color.WHITE);
		btnNewTransaction.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnNewTransaction.setFocusPainted(false);
		btnNewTransaction.setBackground(new Color(25, 122, 246));
		
		tf_clientDetails = new JTextField();
		tf_clientDetails.setBounds(49, 234, 271, 33);
		panel_7.add(tf_clientDetails);
		PromptSupport.setPrompt("Client Username", tf_clientDetails);
		tf_clientDetails.setForeground(new Color(190, 190, 190));
		tf_clientDetails.setColumns(10);
		tf_clientDetails.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_clientDetails.setBackground(Color.WHITE);
		
		JButton btnShowDetails_2 = new JButton("Show Details");
		btnShowDetails_2.setBounds(49, 278, 271, 33);
		panel_7.add(btnShowDetails_2);
		btnShowDetails_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tf_clientDetails.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Please Enter Username!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
				}else{
					
					try {
						String query = "select * from Client where UserName like ?";
						PreparedStatement pst = conn.prepareStatement(query);
						pst.setString(1, '%'+tf_clientDetails.getText()+'%');
						ResultSet rs = pst.executeQuery();
						int count = 0;
						while(rs.next()){
							count+=1;
						}
						pst.close();
						rs.close();
						
						if(count>0){
							String param = tf_clientDetails.getText();
							ClientDetailsFrame AD = new ClientDetailsFrame(param);
							AD.setVisible(true);
						}else{
							JOptionPane.showMessageDialog(null, "Username not found!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnShowDetails_2.setForeground(Color.WHITE);
		btnShowDetails_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnShowDetails_2.setFocusPainted(false);
		btnShowDetails_2.setBackground(new Color(25, 122, 246));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("resource\\toop.jpg"));
		lblNewLabel_1.setBounds(0, 0, 1179, 633);
		panel_5.add(lblNewLabel_1);
		
	}
}
