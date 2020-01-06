import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import mu.*;

public class DeleteFrame extends JFrame
{
	JPanel jp1, jp2;
	JLabel l1;
	JButton delete, back;
	JTextField t1;
	
	public DeleteFrame()
	{
		super("Delete Employee");
		setSize(500,150);
		setResizable(false);
			
		jp1=new JPanel();
		jp1.setLayout(new FlowLayout(FlowLayout.CENTER,10,25));
		
		delete= new JButton("Delete");
		back= new JButton("Back");
		l1=new JLabel("ID:");
		t1= new JTextField(5);
		jp1.add(l1);
		jp1.add(t1);
		add(jp1);
		jp2= new JPanel();
		jp2.setLayout(new FlowLayout(FlowLayout.CENTER,10,25));
		jp2.add(delete);
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
		delete.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent ae)
								{	int a;
									String id = t1.getText();
									id=id.trim();
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
									q.delete(a);
									t1.setText("");
									
										
								}	
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
		delete.addKeyListener(new KeyListener(){	
							public void keyPressed(KeyEvent ke)
							{
								if(ke.getKeyCode()==KeyEvent.VK_ENTER)
								{	
									int a;
									String id = t1.getText();
									id=id.trim();
									try{
									a=Integer.parseInt(id);
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
									q.delete(a);
									t1.setText("");
									
								}
							}
							public void keyReleased(KeyEvent ke){}
							public void keyTyped(KeyEvent ke){}
						      });
		}
}
									