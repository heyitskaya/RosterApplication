import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class RosterApplication implements ActionListener
{
	private static int FRAMELENGTH=750; //static variables that determine how big the binary tree is
	private static int FRAMEWIDTH=600;
	Roster roster= new Roster(5);
	JButton enterButton;
	JTextField addField;
	JTextField deleteField;
	JTextArea registeredArea;
	JTextArea waitListedArea;
	JTextArea deleteArea;
	JButton deleteButton;
	/** takes care of creating the frame, JPanels and JButtons**/
	public void initGUI()
	{
		JFrame frame= new JFrame("Roster");
		frame.setSize(FRAMELENGTH,FRAMEWIDTH);
		frame.setLayout(new BorderLayout()); //set the layout to a gridLayout
		JPanel centerPanel= new JPanel();
		
		
		centerPanel.setLayout(new GridLayout(5,1));
		JPanel registeredPanel= new JPanel(); //JPanel with a JTextField that keeps track of students registered for the class
		registeredArea= new JTextArea();
		registeredPanel.add(registeredArea);
		registeredArea.setText("The students currently registered for this class are: "+roster.getRosterString());
		deleteField= new JTextField();
		deleteArea=new JTextArea();
		deleteArea.setText("If you would like to remove a student from this class, please enter their name below");
		centerPanel.add(deleteArea);
		centerPanel.add(deleteField);
		deleteButton= new JButton("Delete");
		deleteButton.addActionListener(this);
		centerPanel.add(deleteButton);
		centerPanel.add(registeredPanel);
		
		
		JPanel waitListedPanel= new JPanel();
		waitListedPanel.setLayout(new GridLayout(3,1));
		waitListedArea= new JTextArea();
		//waitListedStudents()
		waitListedArea.setText("The students currently waitlisted are: "+roster.getWaitListString());
		waitListedPanel.add(waitListedArea);
		centerPanel.add(waitListedPanel);
		frame.add(centerPanel,BorderLayout.CENTER);
		
		enterButton= new JButton("Enter");
		enterButton.addActionListener(this);
		
		JPanel addPanel= new JPanel();
		addPanel.setLayout(new GridLayout(3,1));
		
		JTextArea instructions=new JTextArea();
		instructions.setText("Please enter the name of the student you want to add below");
		addPanel.add(instructions);
		addField= new JTextField();
		addPanel.add(addField);
		addPanel.add(enterButton); 
		frame.add(addPanel,BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Set it to EXIT_ON_CLOSEE
		frame.setVisible(true); //set visibilty to true
	}
	/** what happens when we click one of the buttons**/
	public void actionPerformed(ActionEvent e) 
	{
		String nameToDeleteClass=deleteField.getText();
		
			String name=addField.getText();
			if(!nameToDeleteClass.isEmpty() && !roster.getList().isEmpty())
			{
				
				roster.getList().delete(nameToDeleteClass); 
				registeredArea.setText("The students currently registered for this class are "+roster.getRosterString());
				waitListedArea.setText("The students currently waitlisted are "+roster.getWaitListString());
				deleteField.setText("");
				
				if(roster.getList().size()<roster.getMax() && !roster.getWaitList().isEmpty()) //if there is still room in the class and there is someone in the waitlist
				{
					String studentToAdd=roster.getWaitList().dequeue(); //get the first student in the waitList
					System.out.println("studentToAdd is "+studentToAdd);
					roster.addName(studentToAdd);
					System.out.println("jsdlkfjslfjslfsdk "+roster.getList().toString());
					
					registeredArea.setText("The students currently registered for this class are "+roster.getRosterString());
					waitListedArea.setText("The students currently waitlisted are "+roster.getWaitListString());
					System.out.println("The new roster looks like "+roster.getList().toString());
					System.out.println("The new waitList is "+roster.getWaitList().toString());
				}
			}
			if(!name.isEmpty())
			{
				roster.add(name);
				registeredArea.setText("The students currently registered for this class are "+roster.getRosterString());
				waitListedArea.setText("The students currently waitlisted are "+roster.getWaitListString());
				addField.setText("");
			}
		
		
			
	}
	
	
	
	
	
	
	
	public static void main(String args[])
	{
		//JFrame frame= new JFrame("Roster");
		RosterApplication r= new RosterApplication();
	//	r.roster.constructList();
	
		r.initGUI();
		
		
	}

}
