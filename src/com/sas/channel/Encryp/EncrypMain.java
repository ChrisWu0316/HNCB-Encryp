package com.sas.channel.Encryp;

import java.awt.Button;
import java.awt.Color;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EncrypMain {
	static TextField jtf = new TextField();
	static TextField jtf2 = new TextField();
	static TextField jtf3 = new TextField();

	static TextField jtf4 = new TextField();
	static TextField jtf5 = new TextField();
	static TextField jtf6 = new TextField();
	static JFrame jf = new JFrame("變更連線設定");
	
	public static void main(String args[]) {
		jf.setSize(400, 400);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		

		Label jl = new Label();
		jl.setText("請輸入SMS介接FTP IP:");
		jl.setBounds(0, 0, 200, 30);
		jf.add(jl);
		
		jtf.setColumns(10);
		jtf.setBounds(0, 30, 100, 30);
		jf.add(jtf);
		
		Label jl2 = new Label();
		jl2.setText("請輸入SMS介接FTP 帳號:");
		jl2.setBounds(0, 60, 200, 30);
		jf.add(jl2);
		
		jtf2.setColumns(10);
		jtf2.setBounds(0, 90, 100, 30);
		jf.add(jtf2);
		
		Label jl3 = new Label();
		jl3.setText("請輸入SMS介接FTP 密碼:");
		jl3.setBounds(0, 120, 200, 30);
		jf.add(jl3);
		
		jtf3.setColumns(10);
		jtf3.setBounds(0, 150, 100, 30);
		jf.add(jtf3);	
		
		Button button = new Button("確定");
		button.setBounds(0, 200, 100, 30);
		button.addActionListener(new ActLis());
		jf.add(button);
		//----------------------------------------------
		Label jl4 = new Label();
		jl4.setText("請輸入CMS IP:");
		jl4.setBounds(200, 0, 200, 30);
		jf.add(jl4);
		
		jtf4.setColumns(10);
		jtf4.setBounds(200, 30, 100, 30);
		jf.add(jtf4);
		
		Label jl5 = new Label();
		jl5.setText("請輸入CMS 帳號:");
		jl5.setBounds(200, 60, 200, 30);
		jf.add(jl5);
		
		jtf5.setColumns(10);
		jtf5.setBounds(200, 90, 100, 30);
		jf.add(jtf5);
		
		Label jl6 = new Label();
		jl6.setText("請輸入CMS 密碼:");
		jl6.setBounds(200, 120, 200, 30);
		jf.add(jl6);
		
		jtf6.setColumns(10);
		jtf6.setBounds(200, 150, 100, 30);
		jf.add(jtf6);
		
		Button button2 = new Button("確定");
		button2.setBounds(200, 200, 100, 30);
		button2.addActionListener(new ActLis2());
		jf.add(button2);
		//------------------------------------
		Label jl7 = new Label();
		jl7.setText("每次僅能修改一種");
		jl7.setBounds(0, 230, 200, 30);
		jl7.setForeground(Color.red);
		jf.add(jl7);
		
		jf.setLayout(null);
		jf.setVisible(true);
	}
	
	static class ActLis implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String ftpIp = jtf.getText();
			String ftpUser = jtf2.getText();
			String ftpPws = jtf3.getText();
			String showMessage = "";
			
			if (null != ftpIp && !"".equals(ftpIp) && null != ftpUser && !"".equals(ftpUser) && 
					null != ftpPws && !"".equals(ftpPws)) {
				String[] ipArr = ftpIp.split("\\.");
				if (null == ipArr || ipArr.length < 4) {
					showMessage = "IP格式不正確!";
				} else {
					for (String ip : ipArr) {
						try {
							Integer ipTest = new Integer(ip);
							if (ipTest > 255) {
								showMessage = "IP格式不正確!";
							}
						} catch (Exception ex) {
							showMessage = "IP格式不正確!";
						}
					}
				}
			} else if ((null == ftpIp || "".equals(ftpIp)) && (null == ftpUser || "".equals(ftpUser)) && 
					(null == ftpPws || "".equals(ftpPws))) {
				showMessage = "尚未填入任何參數!";
			} else {
				showMessage = "尚有未填入的參數!";
			}
			
			if (!"".equals(showMessage)) {
				JOptionPane.showMessageDialog(jf, showMessage);
			} else {
				JOptionPane.showMessageDialog(jf, "修改成功!");
				File outFile = new File("connectionSMS.properties");
				BufferedWriter fw;
				try {
					fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
					byte[] encode = ftpIp.getBytes();
					for (byte b : encode) {
						fw.write(Byte.toString(b));
						fw.write(" ");
					}
					fw.newLine();
					encode = ftpUser.getBytes();
					for (byte b : encode) {
						fw.write(Byte.toString(b));
						fw.write(" ");
					}
					fw.newLine();
					encode = ftpPws.getBytes();
					for (byte b : encode) {
						fw.write(Byte.toString(b));
						fw.write(" ");
					}
					fw.newLine();
					fw.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(jf, "修改失敗!");
				}
				jf.dispose();
			}
		}
	}
	
	static class ActLis2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String ftpIp = jtf4.getText();
			String ftpUser = jtf5.getText();
			String ftpPws = jtf6.getText();
			String showMessage = "";
			
			if (null != ftpIp && !"".equals(ftpIp) && null != ftpUser && !"".equals(ftpUser) && 
					null != ftpPws && !"".equals(ftpPws)) {
				String[] ipArr = ftpIp.split("\\.");
				if (null == ipArr || ipArr.length < 4) {
					showMessage = "IP格式不正確!";
				} else {
					for (String ip : ipArr) {
						try {
							Integer ipTest = new Integer(ip);
							if (ipTest > 255) {
								showMessage = "IP格式不正確!";
							}
						} catch (Exception ex) {
							showMessage = "IP格式不正確!";
						}
					}
				}
			} else if ((null == ftpIp || "".equals(ftpIp)) && (null == ftpUser || "".equals(ftpUser)) && 
					(null == ftpPws || "".equals(ftpPws))) {
				showMessage = "尚未填入任何參數!";
			} else {
				showMessage = "尚有未填入的參數!";
			}
			
			if (!"".equals(showMessage)) {
				JOptionPane.showMessageDialog(jf, showMessage);
			} else {
				JOptionPane.showMessageDialog(jf, "修改成功!");
				File outFile = new File("connectionCMS.properties");
				BufferedWriter fw;
				try {
					fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
					byte[] encode = ftpIp.getBytes();
					for (byte b : encode) {
						fw.write(Byte.toString(b));
						fw.write(" ");
					}
					fw.newLine();
					encode = ftpUser.getBytes();
					for (byte b : encode) {
						fw.write(Byte.toString(b));
						fw.write(" ");
					}
					fw.newLine();
					encode = ftpPws.getBytes();
					for (byte b : encode) {
						fw.write(Byte.toString(b));
						fw.write(" ");
					}
					fw.newLine();
					fw.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(jf, "修改失敗!");
				}
				jf.dispose();
			}
		}
	}
}
