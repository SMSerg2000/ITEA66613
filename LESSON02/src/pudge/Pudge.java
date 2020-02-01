package pudge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.*;      

public class Pudge {
Setting setting;
	
Pudge(){    
JFrame f=new JFrame("Home Work 1");
JButton b1,b2,b3,save,load;
JTextField txt1, txt2, txt3;

b1=new JButton(new ImageIcon("images" + File.separator + "en.png"));
b1.setBounds(220,50,60,40);
f.add(b1);

b2=new JButton(new ImageIcon("images" + File.separator + "fr.png"));  
b2.setBounds(300,50,60,40);
f.add(b2);   

b3=new JButton(new ImageIcon("images" + File.separator + "ge.png"));
b3.setBounds(380,50,60,40);
f.add(b3);

save=new JButton("Сохранить");
save.setBounds(180,300,120,40);
f.add(save);
load=new JButton("Загрузить");
load.setBounds(330,300,120,40);
f.add(load);
JLabel pudge = new JLabel(new ImageIcon("images" + File.separator + "pudge.png"));
pudge.setBounds(10,80, 200,200);   
f.add(pudge);

txt1= new JTextField();
txt1.setBounds(220,120,220,30); 
f.add(txt1);

txt2= new JTextField();
txt2.setBounds(220,165,220,30); 
f.add(txt2);

txt3= new JTextField();
txt3.setBounds(220,210,220,30); 
f.add(txt3);

reloadButton(b1, save, load, txt1, txt2, txt3, "en", "GB");
reloadButton(b2, save, load, txt1, txt2, txt3, "fr", "FR");
reloadButton(b3, save, load, txt1, txt2, txt3, "ge", "GE");

load.addActionListener(new ActionListener(){	
public void actionPerformed(ActionEvent e) 
	{
	try(DataInputStream dos=new DataInputStream(new FileInputStream("pudge.bin"));){
		Setting settingCopy=new Setting(dos.readUTF(), dos.readUTF());
		Locale locale = new Locale(settingCopy.getLanguage(), settingCopy.getCountry());
		ResourceBundle bundle=
		ResourceBundle.getBundle("MessageBundle",locale);
		txt1.setText(bundle.getString("greetings"));
		txt2.setText(bundle.getString("farewell"));
		txt3.setText(bundle.getString("inquiry"));
		b2.setText(bundle.getString("save"));
		b3.setText(bundle.getString("load"));      
	}catch(Exception ex) {
		ex.printStackTrace();
	}
	}
});

save.addActionListener(new ActionListener(){	
public void actionPerformed(ActionEvent e) 
	{

	try(DataOutputStream dos=new DataOutputStream(new FileOutputStream("pudge.bin"));){
		dos.writeUTF(setting.getLanguage());
		dos.writeUTF(setting.getCountry());
	}catch(Exception ex) {
		ex.printStackTrace();
	}
	}		
});

f.setSize(500,400);  
f.setLocationRelativeTo(null);
f.setLayout(null);    
f.setVisible(true);    
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    }      

public static void main(String[] args) {  
    new Pudge();    
}

public void reloadButton(JButton button1, JButton button2, JButton button3, 
		JTextField text1, JTextField text2, JTextField text3, String l, String c) {
	button1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) 
			{
				setting = new Setting(l, c);
				Locale locale = new Locale(l, c);
				ResourceBundle bundle=
				ResourceBundle.getBundle("MessageBundle",locale);
		        text1.setText(bundle.getString("greetings"));
		        text2.setText(bundle.getString("farewell"));
		        text3.setText(bundle.getString("inquiry"));
		        button2.setText(bundle.getString("save"));
		        button3.setText(bundle.getString("load"));
		    }  
		});
	}
}