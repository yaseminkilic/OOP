

public class MyStack<T> extends Grid implements InterfaceStack<T>{  // Implementation of the Stack interface
	
	private int top;  // the top element of the stack.
	private T [] items;  //  the array that holds the element of the stack.
	private int size;  // the size of the stack.
	
	private class Node<T>{
		
		public T data; 
		public Node<T> next; 
		
		public Node(T data){ 
			this(data, null );
		} 
		
		public Node(T data, Node<T> n){ 
			this.data = data; next = n; 
		}
	}
	
	public MyStack() { // Initialize the stack with default capacity
		top = -1;
		size = 0;
		items = (T[]) new Object[100];
	}
	
	public void push(T item) {  // Push a new object on the stack
		
		if ( isFullRow() || isFullCol() || isFull() ) 
			return;
		top++; 
		size++;
		items[top] = item;
	}

	public T pop() { // Pop off the stack element
		
		if( isEmpty( ) ) 
			return null; 
		
		T x = peek(); 
		if(x != null){
			items[top] = null;
			top--; 
			size--;
		}
		return x;
	}
	
	public T peek() {  // Return the top stack element
		if( isEmpty( ) ) 
			return null;
		return items[top];
	}
	
	public int size() {  // Return the current stack size
		return size;
	}
	
	public boolean isEmpty() {  // Return true iff the stack is empty
		return (top == -1);
	}
	
	public boolean isFullRow() {  // an extra methods to return true iff the stack is full
		return ( size > Integer.parseInt(m) );
	}
	
	public boolean isFullCol() {  // an extra methods to return true iff the stack is full
		return ( size > Integer.parseInt(n) );
	}

	public boolean isFull() {  // Return true iff the stack is full
		return (top == items.length-1 );
	}
}
