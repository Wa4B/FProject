import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;


public class RtBook extends CenterPanel{
	private JTable table;
	private JTextField IDtf;
	private JTextField PWtf;
	private JButton cab;
	private JButton okb;
	private String fisbn;
	
	RtBook(String fisbn){
		this.fisbn = fisbn;
		JDialog jf=new JDialog();
		jf.getContentPane().setLayout(null);
		
		JLabel IDlb = new JLabel("ID");
		IDlb.setBounds(36, 59, 57, 15);
		jf.getContentPane().add(IDlb);
		
		IDtf = new JTextField();
		IDtf.setBounds(120, 56, 195, 21);
		jf.getContentPane().add(IDtf);
		IDtf.setColumns(10);
		
		JLabel PWlb = new JLabel("PW");
		PWlb.setBounds(36, 107, 57, 15);
		jf.getContentPane().add(PWlb);
		
		PWtf = new JTextField();
		PWtf.setBounds(120, 107, 195, 21);
		jf.getContentPane().add(PWtf);
		PWtf.setColumns(10);
		
		okb = new JButton("대여");
		okb.setBounds(57, 167, 97, 23);
		jf.getContentPane().add(okb);
		
		cab = new JButton("취소");
		cab.setBounds(200, 167, 97, 23);
		jf.getContentPane().add(cab);
	}
	
	public void setAction(){
		okb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(IDtf.getText().length() != 0 && PWtf.getText().length() != 0){
					try {
						oos.writeObject("/rtbook "+IDtf.getText()+" "+PWtf.getText()+" "+fisbn);
						oos.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}
}
