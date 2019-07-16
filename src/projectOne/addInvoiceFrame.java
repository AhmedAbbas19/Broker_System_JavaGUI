package projectOne;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.crypto.spec.IvParameterSpec;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ItemListener;
import java.sql.*;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

import org.jdesktop.swingx.prompt.PromptSupport;

public class addInvoiceFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Connection conn = null;
	private JTextField tf_contractNum;
	private JTextField tf_invoiceNum;
	private JTextField tf_agentUsername;
	private JTextField tf_clientUsername;
	private JTextField tf_invoiceAmt;
	private JTextField tf_amtCollected;
	private JTextField tf_paymentAmt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addInvoiceFrame frame = new addInvoiceFrame();
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
	public addInvoiceFrame() {
		conn = sqliteConnection.dbConnection();
		setTitle("Add Inoice");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 80, 505, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JLabel lbl_balanceOfComm = new JLabel("0");
		lbl_balanceOfComm.setFont(new Font("Verdana", Font.PLAIN, 14));
		lbl_balanceOfComm.setBounds(43, 235, 108, 14);
		contentPane.add(lbl_balanceOfComm);
		
		final JComboBox<Double> cb_Commission = new JComboBox<Double>();
		cb_Commission.setUI(new BasicComboBoxUI());
		cb_Commission.setEditable(true);
		cb_Commission.setMaximumRowCount(10);
		cb_Commission.setForeground(Color.WHITE);
		cb_Commission.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cb_Commission.setBackground(new Color(25, 122, 246));
		cb_Commission.setBounds(270, 147, 179, 18);
		for(int i=0;i<100;i++){cb_Commission.addItem(i+1.0);}
		contentPane.add(cb_Commission);
		
		
		JLabel lblCommission = new JLabel("Commission %");
		lblCommission.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblCommission.setBounds(270, 132, 108, 14);
		contentPane.add(lblCommission);
		
		JLabel lblNewLabel = new JLabel("Balance Of Commission");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblNewLabel.setBounds(43, 214, 153, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Invoice Date");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(270, 170, 108, 14);
		contentPane.add(lblNewLabel_1);
		
		final JComboBox<String> cb_year = new JComboBox<String>();
		cb_year.setUI(new BasicComboBoxUI());
		cb_year.setForeground(Color.WHITE);
		cb_year.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cb_year.setBackground(new Color(25, 122, 246));
		cb_year.setBounds(270, 185, 54, 18);
		contentPane.add(cb_year);
		
		final JComboBox<String> cb_month = new JComboBox<String>();
		cb_month.setUI(new BasicComboBoxUI());
		cb_month.setMaximumRowCount(13);
		cb_month.setForeground(Color.WHITE);
		cb_month.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cb_month.setBackground(new Color(25, 122, 246));
		cb_month.setBounds(329, 185, 54, 18);
		contentPane.add(cb_month);
		
		final JComboBox<String> cb_day = new JComboBox<String>();
		cb_day.setUI(new BasicComboBoxUI());
		cb_day.setMaximumRowCount(10);
		cb_day.setForeground(Color.WHITE);
		cb_day.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cb_day.setBackground(new Color(25, 122, 246));
		cb_day.setBounds(393, 185, 56, 18);
		contentPane.add(cb_day);
		for(Integer i=2018;i<2030;i++){cb_year.addItem(i.toString());}
		for(Integer i=1;i<=12;i++){cb_month.addItem((i<10)?'0'+i.toString():i.toString());}
		for(Integer i=1;i<=30;i++){cb_day.addItem((i<10)?'0'+i.toString():i.toString());}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 422, 439, 128);
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getViewport().setOpaque(false);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setViewportView(table);
		
		JLabel lblAddInvoice = new JLabel("Add Invoice");
		lblAddInvoice.setForeground(new Color(25, 122, 246));
		lblAddInvoice.setFont(new Font("Verdana", Font.BOLD, 16));
		lblAddInvoice.setBounds(40, 24, 164, 27);
		contentPane.add(lblAddInvoice);
		
		tf_contractNum = new JTextField();
		PromptSupport.setPrompt("Contract Number", tf_contractNum);
		tf_contractNum.setForeground(new Color(190, 190, 190));
		tf_contractNum.setColumns(10);
		tf_contractNum.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_contractNum.setBackground(Color.WHITE);
		tf_contractNum.setBounds(43, 62, 179, 27);
		contentPane.add(tf_contractNum);
		
		tf_invoiceNum = new JTextField();
		PromptSupport.setPrompt("Invoice Number", tf_invoiceNum);
		tf_invoiceNum.setForeground(new Color(190, 190, 190));
		tf_invoiceNum.setColumns(10);
		tf_invoiceNum.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_invoiceNum.setBackground(Color.WHITE);
		tf_invoiceNum.setBounds(270, 63, 179, 27);
		contentPane.add(tf_invoiceNum);
		
		tf_agentUsername = new JTextField();
		PromptSupport.setPrompt("Agent Username", tf_agentUsername);
		tf_agentUsername.setForeground(new Color(190, 190, 190));
		tf_agentUsername.setColumns(10);
		tf_agentUsername.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_agentUsername.setBackground(Color.WHITE);
		tf_agentUsername.setBounds(43, 100, 179, 27);
		contentPane.add(tf_agentUsername);
		
		tf_clientUsername = new JTextField();
		PromptSupport.setPrompt("Client Username", tf_clientUsername);
		tf_clientUsername.setForeground(new Color(190, 190, 190));
		tf_clientUsername.setColumns(10);
		tf_clientUsername.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_clientUsername.setBackground(Color.WHITE);
		tf_clientUsername.setBounds(270, 101, 179, 27);
		contentPane.add(tf_clientUsername);
		
		tf_invoiceAmt = new JTextField();
		PromptSupport.setPrompt("Invoice Amount", tf_invoiceAmt);
		tf_invoiceAmt.setForeground(new Color(190, 190, 190));
		tf_invoiceAmt.setColumns(10);
		tf_invoiceAmt.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_invoiceAmt.setBackground(Color.WHITE);
		tf_invoiceAmt.setBounds(43, 138, 179, 27);
		contentPane.add(tf_invoiceAmt);
		
		tf_amtCollected = new JTextField();
		PromptSupport.setPrompt("Amount Collected", tf_amtCollected);
		tf_amtCollected.setForeground(new Color(190, 190, 190));
		tf_amtCollected.setColumns(10);
		tf_amtCollected.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_amtCollected.setBackground(Color.WHITE);
		tf_amtCollected.setBounds(43, 176, 179, 27);
		contentPane.add(tf_amtCollected);
		
		JButton btnAddInvoice = new JButton("ADD INVOICE");
		btnAddInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
						if(tf_agentUsername.getText().isEmpty()||tf_clientUsername.getText().isEmpty()||tf_contractNum.getText().isEmpty()||tf_invoiceNum.getText().isEmpty()||tf_invoiceAmt.getText().isEmpty()||tf_amtCollected.getText().isEmpty()){
							JOptionPane.showMessageDialog(null, "Please fill all fields!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
						}else if(cb_year.getSelectedItem().equals("----")||cb_month.getSelectedItem().equals("--")||cb_day.getSelectedItem().equals("--")){
							JOptionPane.showMessageDialog(null, "Enter a valid date!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
						}
						else{
							String query = "select * from Agent where UserName = ?";
							PreparedStatement pst = conn.prepareStatement(query);
							pst.setString(1, tf_agentUsername.getText());
							ResultSet rs = pst.executeQuery();
							int count = 0;
							while(rs.next()){
								count+=1;
							}
							if(count==0){
								JOptionPane.showMessageDialog(null, "Agent Username not found!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
								return;
							}
							
							query = "select * from Client where UserName = ?";
							pst = conn.prepareStatement(query);
							pst.setString(1, tf_clientUsername.getText());
							rs = pst.executeQuery();
							count = 0;
							while(rs.next()){
								count+=1;
							}
							if(count==0){
								JOptionPane.showMessageDialog(null, "Client Username not found!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
								return;
							}
							Double amtColl = Double.parseDouble(tf_amtCollected.getText());
							if(amtColl<0){
								JOptionPane.showMessageDialog(null, "Amount collected can't be negative!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
								return;
							}
							
							query = "insert into Invoice (Agent_UserName,Client_UserName,ContractNumber,InvoiceNumber,InvoiceAmount,CommissionPercent,AmountCollected,BalanceOfCommission,InvoiceDate)";
							query+=" values (?,?,?,?,?,?,?,?,?)";
							pst = conn.prepareStatement(query);
							pst.setString(1, tf_agentUsername.getText());
							pst.setString(2, tf_clientUsername.getText());
							pst.setString(3, tf_contractNum.getText());
							pst.setString(4, tf_invoiceNum.getText());
							int invoiceAmt = Integer.parseInt(tf_invoiceAmt.getText());
							pst.setInt(5, invoiceAmt);
							pst.setDouble(6, (Double) cb_Commission.getSelectedItem());
							pst.setString(7, tf_amtCollected.getText());
							Double amtCollected = Double.parseDouble(tf_amtCollected.getText());
							Double balance;
							balance = invoiceAmt*((Double)cb_Commission.getSelectedItem()/100) - amtCollected;
							lbl_balanceOfComm.setText(balance.toString());
							if(balance < 0){
								JOptionPane.showMessageDialog(null, "Balance Of Commission can't be Negative", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
								return;
							}
							pst.setDouble(8, balance);
							String invoiceDate = "";
							invoiceDate+= cb_year.getSelectedItem()+"-"+cb_month.getSelectedItem()+"-"+cb_day.getSelectedItem();
							pst.setString(9, invoiceDate);
							pst.execute();
							JOptionPane.showMessageDialog(null, "Successful Procces!","Note!", JOptionPane.INFORMATION_MESSAGE);
							
							pst.close();
						}
				} catch (Exception e) {
					String error = "Make sure that:\n\n1- Contract Number is Unique\n2- Invoice Number is Unique\n3- Invoice Amount is valid Number\n4- Amount Collected is valid Number";
					JOptionPane.showMessageDialog(null, error, "Something went wrong!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAddInvoice.setForeground(Color.WHITE);
		btnAddInvoice.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnAddInvoice.setFocusPainted(false);
		btnAddInvoice.setBackground(new Color(25, 122, 246));
		btnAddInvoice.setBounds(270, 216, 179, 33);
		contentPane.add(btnAddInvoice);
		
		JLabel lblSetPaymentDue = new JLabel("Set Payment Due Date");
		lblSetPaymentDue.setForeground(new Color(25, 122, 246));
		lblSetPaymentDue.setFont(new Font("Verdana", Font.BOLD, 16));
		lblSetPaymentDue.setBounds(28, 282, 214, 27);
		contentPane.add(lblSetPaymentDue);
		
		JLabel lblDueDate = new JLabel("Due Date");
		lblDueDate.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblDueDate.setBounds(28, 320, 108, 14);
		contentPane.add(lblDueDate);
		
		final JComboBox<String> cb_payYear = new JComboBox<String>();
		cb_payYear.setForeground(Color.WHITE);
		cb_payYear.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cb_payYear.setBackground(new Color(25, 122, 246));
		cb_payYear.setBounds(28, 335, 54, 18);
		contentPane.add(cb_payYear);
		
		final JComboBox<String> cb_payMonth = new JComboBox<String>();
		cb_payMonth.setMaximumRowCount(13);
		cb_payMonth.setForeground(Color.WHITE);
		cb_payMonth.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cb_payMonth.setBackground(new Color(25, 122, 246));
		cb_payMonth.setBounds(87, 335, 54, 18);
		contentPane.add(cb_payMonth);
		
		final JComboBox<String> cb_payDay = new JComboBox<String>();
		cb_payDay.setMaximumRowCount(10);
		cb_payDay.setForeground(Color.WHITE);
		cb_payDay.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cb_payDay.setBackground(new Color(25, 122, 246));
		cb_payDay.setBounds(151, 335, 56, 18);
		contentPane.add(cb_payDay);
		
		for(Integer i=2018;i<2030;i++){cb_payYear.addItem(i.toString());}
		for(Integer i=1;i<=12;i++){cb_payMonth.addItem((i<10)?'0'+i.toString():i.toString());}
		for(Integer i=1;i<=30;i++){cb_payDay.addItem((i<10)?'0'+i.toString():i.toString());}
		
		tf_paymentAmt = new JTextField();
		PromptSupport.setPrompt("Payment Amount", tf_paymentAmt);
		tf_paymentAmt.setForeground(new Color(190, 190, 190));
		tf_paymentAmt.setColumns(10);
		tf_paymentAmt.setBorder(new LineBorder(new Color(229, 229, 229), 2));
		tf_paymentAmt.setBackground(Color.WHITE);
		tf_paymentAmt.setBounds(270, 326, 179, 27);
		contentPane.add(tf_paymentAmt);
		
		JButton btnAddPayment = new JButton("ADD PAYMENT");
		btnAddPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(tf_invoiceNum.getText().isEmpty()||tf_paymentAmt.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, "Please fill all fields!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
					}else if(cb_payYear.getSelectedItem().equals("----")||cb_payMonth.getSelectedItem().equals("--")||cb_payDay.getSelectedItem().equals("--")){
						JOptionPane.showMessageDialog(null, "Enter a valid date!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
					}else{
						String query1 = "select BalanceOfCommission from Invoice where InvoiceNumber = ?";
						String query2 =	"select PaidAmount from Payments where InvoiceNumber = ?";
							
						PreparedStatement pst = conn.prepareStatement(query1);
						pst.setString(1, tf_invoiceNum.getText());
						ResultSet rs = pst.executeQuery();
						Double paid = Double.parseDouble(tf_paymentAmt.getText()), balance = 0.0;
						while(rs.next()){
							balance = rs.getDouble("BalanceOfCommission");
						}
						
						pst = conn.prepareStatement(query2);
						pst.setString(1, tf_invoiceNum.getText());
						rs = pst.executeQuery();
						while(rs.next()){
							paid += rs.getDouble("PaidAmount");
						}
						
						if(paid>balance){
							JOptionPane.showMessageDialog(null, "Paid Amount is exceeds the BalanceOfCommission!", "Something went wrong!", JOptionPane.ERROR_MESSAGE);
							return;
						}
						else{
							query1 =	"insert into Payments (InvoiceNumber,PaidAmount,DueDate) values (?,?,?)";
							pst = conn.prepareStatement(query1);
							pst.setString(1, tf_invoiceNum.getText());
							pst.setString(2, tf_paymentAmt.getText());
							String invoiceDate = "";
							invoiceDate+= cb_payYear.getSelectedItem()+"-"+cb_payMonth.getSelectedItem()+"-"+cb_payDay.getSelectedItem();
							pst.setString(3, invoiceDate);
							pst.execute();
							query2 = "select * from Payments where InvoiceNumber = ?";
							pst = conn.prepareStatement(query2);
							pst.setString(1, tf_invoiceNum.getText());
							rs = pst.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
							pst.close();
							rs.close();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnAddPayment.setForeground(Color.WHITE);
		btnAddPayment.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		btnAddPayment.setFocusPainted(false);
		btnAddPayment.setBackground(new Color(25, 122, 246));
		btnAddPayment.setBounds(28, 364, 421, 33);
		contentPane.add(btnAddPayment);
		
		
		
		
	}
}
