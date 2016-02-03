import java.util. *;
public class DoublyLinkedList<T> extends LinkedList<T> 
{
	DoublyLinkedList<Integer> list;
	protected DoublyLinkedListNode<T> head;
	protected DoublyLinkedListNode<T> tail;
	
	public DoublyLinkedList() /** constructor**/
	{
		super();
		head= new DoublyLinkedListNode<T>();
		tail= new DoublyLinkedListNode<T>();
		head=null; /** set both the head and tail to null**/
		tail=null;
	}
	
	/** insert the data as the new head, update head variable**/
	/** if the list is empty create a new node and add as head**/
	public void insertFirst(T data) //works
	{
		DoublyLinkedListNode<T> node= new DoublyLinkedListNode<T>();
		node.setData(data); //create a new node
		if(isEmpty())
		{
			head=node;
			tail=node;
		}
		else
		{
			//head.setPrev(node); //set the previous node
			//head.setPrev(node);
			//System.out.println(head.getData());
			node.setNext(head); //set the next node 
			head.setPrev(node);
			head=node;
			head.setPrev(null); //set the prev of head which is null
		}
	}
	
	/** create a node and insert the new node after currentNode in the list**/
	/** if current node does not exist, do nothing**/ 
	/** if current node is tail, insert the new node and update the tail**/
	public void insertAfter(DoublyLinkedListNode<T> currentNode, T data) /** make a node with the data and add this node after the currentNode**/
	{
		
		//search for currentNode before inserting it
		DoublyLinkedListNode<T> nodeToInsert= new DoublyLinkedListNode<T>();
		nodeToInsert.setData(data); //create a new node with the data given
		DoublyLinkedListNode<T> cursor=head;
		while(!cursor.equals(currentNode) && cursor.getNext()!=null)
		{
			cursor=cursor.getNext();
		}
		//after the while loop we should have the cursor or cursor is null, check to see if we have indeed found
		//currentNode in the list
		System.out.println("The data of cursor is "+cursor.getData());
		if(cursor.equals(currentNode))
		{
			if(cursor.getNext()!=null)
			{
				nodeToInsert.setNext(cursor.getNext());
				cursor.getNext().setPrev(nodeToInsert);
			
				currentNode.setNext(nodeToInsert);
				nodeToInsert.setPrev(currentNode);
			}
			else //when cursor.getNext()==null
			{
				insertLast(data);
				tail=getLastNode();
				
			}
		}
	}
	/** create a new node with the given data and insert it as the tail, update tail node**/
	/** if the list is empty, create a new node and simply insert it, have both head and tail refer to this node**/
	public void insertLast(T data) //works
	{
		DoublyLinkedListNode<T> nodeToInsert= new DoublyLinkedListNode<T>();
		nodeToInsert.setData(data); //create a new node with the data given
		if(isEmpty())
		{
			
			head=nodeToInsert;
			tail=nodeToInsert;
			System.out.println(toString());
			System.out.println("kaya "+getLastNode().getData());
		}
		else
		{
			
			tail.setNext(nodeToInsert);
			nodeToInsert.setPrev(tail);
			tail=nodeToInsert;
		}
	}
	/**checks to see if the list is empty,if the list is empty do nothing**/
	/** else delete the first node in the list and update head value**/
	public void deleteFirst()
	{
		if(size() == 1)
		{
			System.out.println("Setting head and tail to null");
			head = null;
			tail = null; 
			System.out.println("Tail should be null: " + tail);
			System.out.println("Head should be null: " + head);

			return;
		}
		if(head!=null && head.getNext()!=null)
		{
			head=head.getNext();
			head.setPrev(null);
			return;
		}
		if(head==null)
		{
			return;
		}
		
		
	}
	/** delete the last node in the list**/
	/** when the list is empty do nothing, if the list is of size 1, set both head and tail to null**/
	public void deleteLast()
	{
		if(size()==1)
		{
			head=null;
			tail=null; //I JUST CHANGED THIS
			return;
		}
		if(size()>1)
		{
			
			DoublyLinkedListNode<T> secondToLast=tail.getPrev();
			secondToLast.setNext(null);
			tail=secondToLast;
			return;
		}
		if(isEmpty())
		{
			return;
		}
		
	}
	
	/** remove node following currentNode if no node exists do nothing**/
	public void deleteNext(DoublyLinkedListNode<T> currentNode) 
	{
		if( currentNode!=null&&currentNode.getNext()!=null)
		{
			if(currentNode.getNext().getNext()!=null) 
			{
				currentNode.setNext(currentNode.getNext().getNext());
				currentNode.getNext().setPrev(currentNode);
				return;
			}
			//when currentNode is second to last node
			if(currentNode.getNext().getNext()==null)
			{
				tail=currentNode;
				tail.setNext(null);
				return;
			}
			
		}
		if(currentNode.equals(tail))
		{
			return;
		}
	}
	
	/** get the tail node of the list**/
	public DoublyLinkedListNode<T> getLastNode() 
	{
	
		return tail;
		
	}
	/** get the head node of the list**/
	public DoublyLinkedListNode<T> getFirstNode()
	{		
			return head;
	}
	
	/** return a string representation of the list**/
	public String toString()
	{
		String line="";
		if(!isEmpty())
		{
			line="<-"; //have and empty string
			LinkedListNode<T> cursor= head; //have a cursor node
		
			while(cursor.getNext()!=null) //when the cursor is not null
			{
				line+= cursor.getData() + "<=>"; //add the data of the currentNode to the string
			
			
				cursor=cursor.getNext(); //move it to the next node in the list
			
			}
		System.out.println(cursor.getData());
			line+=cursor.getData()+"->";
			//System.out.println(line); 
			return line; //return this string
		}
		return line;
		
		
	}
	/** return number of nodes in this list, if list is empty, returns 0**/
	public int size() 
	{
		if(head==null) //this is when the list has no nodes MUST BE ==
		{
			return 0;
		}
		int count=1; //when at least the head is not null, we have at least one node so initialize count to 1

		LinkedListNode<T> cursor= new LinkedListNode<T>(); //have a cursor node
		cursor=head; //initialize it to the head
		while(cursor.getNext()!=null) //when the next one is not null
		{
			cursor=cursor.getNext(); //move it down the list
			count++; //increase our count
		}
		return count; //when we break out of the while loop we have traversed the entire linked list
		//and return count
		
	}
	/** checks to see if the list is empty, returns true of empty**/
	public boolean isEmpty() //checks to see of the list is empty
	{
		if(head==null) //if the head is null, it means that the head is empty
		{
			
			return true; //therefore return true
		}
		return false; //else the list is not empty, and we return false
	}
	/** a reverse function I did for fum**/
	public void reverse(DoublyLinkedList<Integer> list)
	{
		DoublyLinkedListNode<Integer> temp=null;
		DoublyLinkedListNode<Integer> current=list.getFirstNode();
		while(current!=null)
		{
			temp=current.getPrev();
			current.setPrev(current.getNext());
			current.setNext(temp);
			current=current.getPrev();
		}
		if(temp!=null)
		{
			list.head=temp.getPrev();
		}
	}
	/** given an element to delete, go through the list and when we've found this element we delete it, if nothing found, do nothing**/
	/** handles the case where the element we want to delete is head or tail**/
	public void delete(T element)
	{
		DoublyLinkedListNode<T> cursor=getFirstNode();
		System.out.println("The last node is "+tail.getData());
		System.out.println("lalalallala");
		if(element.equals(cursor.getData()))
		{
			deleteFirst();
			return;
		}
		
		while(cursor!=null)
		{
			if(cursor.getData().equals(element)) 
			{
				
				//delete the cursor
				DoublyLinkedListNode pre=cursor.getPrev();
				deleteNext(pre);
				return;
			}
			
			cursor=cursor.getNext();
		}
	}
	public static void main(String args[])
	{
		DoublyLinkedList<Integer> list= new DoublyLinkedList<Integer>();
		list.insertFirst(1);
		
		/**if(list.size()==1)
		{
		
			list.deleteLast();
			System.out.println("The data of tail is "+list.getLastNode());
		}**/
		list.deleteFirst();
		System.out.println("The data of head is "+list.getFirstNode());

		System.out.println("The data of tail is "+list.getLastNode());
		
	}


}
