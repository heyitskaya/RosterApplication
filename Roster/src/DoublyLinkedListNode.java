
public class DoublyLinkedListNode<T> extends LinkedListNode<T> 
{
	private T data;
	private DoublyLinkedListNode<T> next; //is this of type t
	private DoublyLinkedListNode<T> prev;
	
	public DoublyLinkedListNode()
	{
		
	}
	public DoublyLinkedListNode<T> getPrev()
	{
		if(prev!=null)
		{
			return prev;
		}
		return null;
	}
	public void setPrev(DoublyLinkedListNode<T> node)
	{
		prev=node;
	}
	public void setNext(DoublyLinkedListNode<T> node)  //set the next node of the current node
	{
		next=node;
		
	}
	public DoublyLinkedListNode<T> getNext() //get the next node
	{
		return next;
	}
	
	
}
