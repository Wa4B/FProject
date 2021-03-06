import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class FixBook extends CenterPanel{
	private JTextField isbntf;
	private JTextField titletf;
	private JTextField authortf;
	private JTextField comtf;
	private JButton saveb;
	private JButton cancleb;
	private JDialog jf;
	private String fisbn;
	private String ftitle;
	private String fauthor;
	private String fcom;
	
	FixBook(String[] row){
		
		this.fisbn = row[0];
		this.ftitle = row[1];
		this.fauthor = row[2];
		this.fcom = row[3];
		
		jf = new JDialog();
		jf.setTitle("도서 수정");
		
		
		jf.setResizable(false);
		jf.getContentPane().setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		jf.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		isbntf = new JTextField();
		isbntf.setBounds(89, 26, 193, 18);
		panel.add(isbntf);
		isbntf.setColumns(10);
		isbntf.setText(fisbn);
		
		JLabel isbnlabel = new JLabel("코드번호");
		isbnlabel.setBounds(20, 26, 57, 15);
		panel.add(isbnlabel);
		
		JLabel titlelabel = new JLabel("제목");
		titlelabel.setBounds(20, 77, 57, 15);
		panel.add(titlelabel);
		
		titletf = new JTextField();
		titletf.setBounds(89, 74, 193, 18);
		panel.add(titletf);
		titletf.setColumns(10);
		titletf.setText(ftitle);
		
		JLabel authorlabel = new JLabel("저자");
		authorlabel.setBounds(20, 121, 57, 15);
		panel.add(authorlabel);
		
		authortf = new JTextField();
		authortf.setBounds(89, 118, 193, 18);
		panel.add(authortf);
		authortf.setColumns(10);
		authortf.setText(fauthor);
		
		JLabel comlabel = new JLabel("출판사");
		comlabel.setBounds(20, 168, 57, 15);
		panel.add(comlabel);
		
		
		comtf = new JTextField();
		comtf.setBounds(89, 165, 193, 18);
		panel.add(comtf);
		comtf.setColumns(10);
		comtf.setText(fcom);
		
		saveb = new JButton("수정");
		saveb.setBounds(48, 256, 97, 23);
		panel.add(saveb);
		
		cancleb = new JButton("취소");
		cancleb.setBounds(171, 256, 97, 23);
		panel.add(cancleb);
		
		
		jf.setVisible(true);
		jf.setSize(300, 334);
		jf.setLocation(400, 400);
		
		
		addAction();
		
	}
	
	public void addAction(){
		cancleb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				jf.dispose();
			}
		});
		saveb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String str;
				String isbns = isbntf.getText();
				String titles = titletf.getText();
				String authors = authortf.getText();
				String coms = comtf.getText();
				
				
				
				
				str = "/fixbook^"+fisbn+"^"+isbns+"^"+titles+"^"+authors+"^"+coms;
				if(str.indexOf("^^")!=-1){
					popup("입력 오류","잘못된 항목이 있습니다.");
				}else{
					try {
						oos.writeObject(str);
						oos.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					jf.dispose();
				}
				
			}
		});
	}
}