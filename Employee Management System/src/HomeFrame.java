import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import mu.*;
public class HomeFrame extends JFrame
{
	JPanel jp;
	JButton add,modify,delete,view;
	public HomeFrame()
	{	super("Employee Records Management");
		
		setSize(500,150);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);	
		
		
		jp=new JPanel();	
		jp.setLayout(new FlowLayout(FlowLayout.CENTER,10,25));
		
		add=new JButton("Add");
		modify=new JButton("Modify");
		delete=new JButton("Delete");
		view=new JButton("View");
		
		jp.add(add);
		jp.add(modify);
		jp.add(delete);
		jp.add(view);
		add(jp);
		
		
		
		
		addWindowListener(new WindowAdapter(){
							public void windowClosing(WindowEvent e)
							{
								int out=JOptionPane.showConfirmDialog(new JDialog(),"Do you want to exit?");
								if(out==JOptionPane.YES_OPTION)
									{
										System.exit(1);
									}
							}
							});
		add.addActionListener(new ActionListener(){
							 public void actionPerformed(ActionEvent ae)
							 {
								AddFrame a = new AddFrame();			
								dispose();
							  }
							});
		modify.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent ae)
								{
									ModifyFrame a=new ModifyFrame();
									dispose();
								}
							   });
		delete.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent ae)
								{
									DeleteFrame a = new DeleteFrame();
									dispose();	
								}	
							   });
		view.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent ae)
								{
									ViewFrame a = new ViewFrame();
									dispose();
								}
							    });
		add.addKeyListener(new KeyListener(){

							public void keyPressed(KeyEvent ke)
							{
								if(ke.getKeyCode()==KeyEvent.VK_ENTER)
								{
									AddFrame a = new AddFrame();
									dispose();
								}
							}	
							public void keyReleased(KeyEvent ke){}
							public void keyTyped(KeyEvent ke){}
						     });
		modify.addKeyListener(new KeyListener(){

							public void keyPressed(KeyEvent ke)
							{
								if(ke.getKeyCode()==KeyEvent.VK_ENTER)
								{
									ModifyFrame a = new ModifyFrame();
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
									DeleteFrame a = new DeleteFrame();
									dispose();
								}
							}	
							public void keyReleased(KeyEvent ke){}
							public void keyTyped(KeyEvent ke){}
						     });
		view.addKeyListener(new KeyListener(){

							public void keyPressed(KeyEvent ke)
							{
								if(ke.getKeyCode()==KeyEvent.VK_ENTER)
								{
									ViewFrame a = new ViewFrame();
									dispose();
								}
							}	
							public void keyReleased(KeyEvent ke){}
							public void keyTyped(KeyEvent ke){}
						     });
	}

	public static void main(String args[])
	{
		HomeFrame h = new HomeFrame();
		
	}
}



class DataBaseHandler
{
	static Connection con;
	
	public static void getConnection()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kiran","kiran123");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(new JDialog()," "+e);
		}
	}

	public void insert( int id, String name)
	{
		try
		{
			getConnection();
		
			String q="insert into employee values(?,?)";
			PreparedStatement pst=con.prepareStatement(q);
			pst.setInt(1,id);
			pst.setString(2,name);
			int i=pst.executeUpdate();
			Sound.success();
			JOptionPane.showMessageDialog(new JDialog()," 1 Record Added");
		}
		catch(Exception e)
		{	System.out.println(e);
			Sound.failure();
			JOptionPane.showMessageDialog(new JDialog(),"Record Already Exists");
		}
	}
	
	public String query()
	{
		StringBuffer sb=new StringBuffer();
		try	
		{
			getConnection();
			String q="select * from employee order by id";
			Statement st=con.createStatement();
			ResultSet rs = st.executeQuery(q);
			
			sb.append("ID:"+"\t"+"NAME:"+"\n");
			while(rs.next())
			{
				sb.append(rs.getString(1)+"\t"+rs.getString(2)+"\n");
			}
			rs.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(new JDialog()," "+e);
		}
		return sb.toString();
	}
	
	public void modify( int id , String name)
	{
		try
		{
			getConnection();
		
			String q="update employee set name=? where id=?";
			PreparedStatement pst=con.prepareStatement(q);
			pst.setString(1,name);
			pst.setInt(2,id);
			int i =pst.executeUpdate();
			
			if(i==0)
			{	Sound.failure();
				JOptionPane.showMessageDialog(new JDialog(),"ID Does Not Exist");
			}
			else
			{
			Sound.success();
			JOptionPane.showMessageDialog(new JDialog(),"1 Record Updated");
			}
		}
		catch(Exception e)
		{	
			Sound.failure();
			JOptionPane.showMessageDialog(new JDialog(),"Could not Update");
		}
	}
	
	public void delete(int id)
	{
		try
		{
			getConnection();
			
			String q="delete from employee where id=?";
			PreparedStatement pst= con.prepareStatement(q);
			pst.setInt(1,id);
			int i=pst.executeUpdate();
			if( i==0)
			{	Sound.failure();
				JOptionPane.showMessageDialog(new JDialog(),"ID Does Not Exist");
			}
			else
			{
				Sound.success();
				JOptionPane.showMessageDialog(new JDialog(),"1 Record Deleted");
			}
		}
		catch(Exception e)
		{
			Sound.failure();
			JOptionPane.showMessageDialog(new JDialog(),"Could not Delete");
		}
	}
}
			
		
		
