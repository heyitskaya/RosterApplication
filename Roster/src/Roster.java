
public class Roster 
{
	static private DoublyLinkedList<String> list= new DoublyLinkedList<String>();
	private static int MAX;
	private int currentNum=list.size();
	static private QueueLL<String> waitList= new QueueLL<String>(); 
	
	/** constructor, takes in a parameter to determine maximum number of students in class**/
	public Roster(int max) 
	{
		MAX=max;
	}
	/** returns the maximum number of students accepted in this class**/
	public int getMax()
	{
		return MAX;
	}
	/** returns a queue representing the students on the waitList**/
	public QueueLL<String> getWaitList()
	{
		return waitList;
	}
	/** this method adds a name to the class, but not to the waitList**/
	public void addName(String name) /** this method only adds to the class**/
	{
		System.out.println("This function should also be called");
		DoublyLinkedListNode<String> nodeToInsert= new DoublyLinkedListNode<String>();
		nodeToInsert.setData(name);
		DoublyLinkedListNode<String> cursor=list.getFirstNode();
		if(list.isEmpty())
		{
			list.insertFirst(name);
			return;
		}
		if(list.size()<MAX)
		{
				
				if(name.compareToIgnoreCase(cursor.getData())<=0) //name comes before the head
				{
					System.out.println("We should be in here");
					
					list.insertFirst(name);
					System.out.println("hahahaha "+list.toString());
					return;
				}
				if(name.compareToIgnoreCase(list.getLastNode().getData())>0) //name comes after tail
				{
					list.insertLast(name);
					return;
				}
				if(name.compareToIgnoreCase(cursor.getData())>0) //name should come after cursor
				{	
					
					while(name.compareToIgnoreCase(cursor.getData())>0 && cursor!=null)
					{
						cursor=cursor.getNext();
					}
				//after the while loop 
			//either cursor is null or name compareTo cursor<=0, name comes before cursor
			//when name.compareTo(cursor)<0 
					DoublyLinkedListNode<String> preCursor=cursor.getPrev();
			//insert after precursor
					list.insertAfter(preCursor, name);
					currentNum=list.size(); //increment the current number of students in the class
				}
		}
	}
	/** we use this method when we want to add someone to the class, it checks the current number of students in the class to see if there is anymore space**/
	/** if there is no more space it will add to the waitList**/
	public void add(String nameToAdd) /** adds to both the class and the waitList**/
	{
		if(list.size()<MAX && waitList.isEmpty())
		{
			addName(nameToAdd);
			currentNum=list.size();
			return;
		}
		if(list.size()<MAX && !waitList.isEmpty()) //we can add someone to the class and there is someone on the waitlist
		{
			String temp=waitList.dequeue();
			addName(temp);
			currentNum=list.size();
			return;
		}
		if(list.size()>=MAX) //we can't add anyone to the class
		{
			waitList.enqueue(nameToAdd);
		}
	}
	
	// DONT FORGET ABOUT DELETION
	/** returns a string representation of the roster**/
	public String getRosterString()
	{
		String answer="";
		DoublyLinkedListNode<String> cursor= list.getFirstNode();
		
		while(cursor!=null)
		{
			
			answer+=cursor.getData()+", ";
			cursor=cursor.getNext();
		}
		return answer;
	}
	/** returns a string representation of the waitList**/
	public String getWaitListString()
	{
		StringBuilder string= new StringBuilder();
		//DoublyLinkedListNode<String> cursor=(DoublyLinkedListNode<String>) waitList.l.getFirstNode();
		LinkedList list=waitList.l;
		LinkedListNode<String> cursor=list.getFirstNode();
		//waitList.toString();
		while(cursor!=null)
		{
			string.append(cursor.getData()+" ");
			cursor=cursor.getNext();
		}
		
		return string.toString();
	}
	
	/** get the DoublyLinkedList that represents the students that are currently in the class**/
	public DoublyLinkedList<String> getList()
	{
		return list;
	}
	/** setter for private instance variable list**/
	public void setList(DoublyLinkedList<String> listToSet)
	{
		list=listToSet;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
