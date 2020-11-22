
public interface InterfaceStack<T>  // accessor methods
{
	void push(T item); // push an element onto the stack
	T pop();  // return and remove the top element of the stack
	T peek(); // return null if stack is empty, otherwise return the top element
	boolean isEmpty(); // see if the stack is empty
	boolean isFull(); // see if the stack is full
	int size(); // return the number of elements in the stack
	
}