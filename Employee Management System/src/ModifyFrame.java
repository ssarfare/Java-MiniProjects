import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import mu.*;

class ModifyFrame extends JFrame
{
	JPanel jp1,jp2;
	JButton update,back;
	JLabel l1,l2;
	JTextField t1,t2;
	
	public ModifyFrame()
	{
		super("Update Employee");
		setSize(500,150);
		setResizable(false);	
		
		jp1=new JPanel();
		jp1.setLayout(new FlowLayout(FlowLayout.CENTER,10,25));
		
		update= new JButton("Update");
		back= new JButton("Back");
		
		l1=new JLabel("ID:");
		l2=new JLabel("Name:");
		t1= new JTextField(5);
		t2=new JTextField(10);
		jp1.add(l1);
		jp1.add(t1);
		jp1.add(l2);
		jp1.add(t2);
		add(jp1);
		
		jp2= new JPanel();
		jp2.setLayout(new FlowLayout(FlowLayout.CENTER,10,25));
		jp2.add(update);
		jp2.add(back);
		add(jp2,BorderLayout.SOUTH);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
		addWindowListener(new WindowAdapter(){
							public void windowClosing(WindowEvent we)
							{
								HomeFrame h = new HomeFrame();
								dispose();
							}
						      });
		
		back.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent ae)
								{
									HomeFrame h = new HomeFrame();
									dispose();
								}
							    });
		update.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent ae)
								{	int a;
									String id=t1.getText();
									id=id.trim();
									String name = t2.getText();
									if(id.length()==0 | name.length()==0)
									{
										Sound.failure();
										JOptionPane.showMessageDialog(new JDialog(),"All Fields are mandatory");
										return;
									}
									try
									{
										 a =Integer.parseInt(id);
									}
									catch(NumberFormatException e )
									{
										Sound.failure();
										JOptionPane.showMessageDialog(new JDialog(),"Invalid ID");
										return;
									}
									if(a<=0)
									{
										Sound.failure();
										JOptionPane.showMessageDialog(new JDialog(),"ID cannot Be Negative or Zero");
										return;
									}
									
									DataBaseHandler q= new DataBaseHandler();
									q.modify(a,name);
									t1.setText("");
									t2.setText("");
										
								}	
							   });
		update.addKeyListener(new KeyListener (){
							public void keyPressed(KeyEvent ke)
							{
							if(ke.getKeyCode()==KeyEvent.VK_ENTER)
								{
									int a;
									String id=t1.getText();
									id=id.trim();
									String name = t2.getText();
									if(id.length()==0 | name.length()==0)
									{
										Sound.failure();
										JOptionPane.showMessageDialog(new JDialog(),"All Fields are mandatory");
										return;
									}
									try
									{
										 a =Integer.parseInt(id);
									}
									catch(NumberFormatException e )
									{
										Sound.failure();
										JOptionPane.showMessageDialog(new JDialog(),"Invalid ID");
										return;
									}
									if(a<=0)
									{
										Sound.failure();
										JOptionPane.showMessageDialog(new JDialog(),"ID cannot Be Negative or Zero");
										return;
									}
									
									DataBaseHandler q= new DataBaseHandler();
									q.modify(a,name);
									t1.setText("");
									t2.setText("");
								}
							}
							public void keyReleased(KeyEvent ke){}
							public void keyTyped(KeyEvent ke){}
							});
		back.addKeyListener(new KeyListener(){
							public void keyPressed(KeyEvent ke)
							{
								if(ke.getKeyCode()==KeyEvent.VK_ENTER)
								{
									HomeFrame h = new HomeFrame();
									dispose();
								}
							}
							public void keyReleased(KeyEvent ke){}
							public void keyTyped(KeyEvent ke){}
						      });
		}
}


	