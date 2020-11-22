import java.util.ArrayList;

public class MyBTree< T extends Comparable<T> >{

    Node<T> myRoot;
    int heightTree = 0;
    int numberTree = 0;
    String myPath = "";
    
    class Node<T> {

        private T data;
        private Node<T> left, right, parent;

        public Node(T data) {
            left = right = null;
            this.data = data;
        }
        
        boolean isLeaf() {
            return ( (left == null) && (right == null) );
        }
        boolean isNode() {
            return !isLeaf();
        }
        boolean hasLeftNode() {
            return (null != left);
        }
        boolean hasRightNode() {
            return (right != null);
        }
        boolean isLeftNode() {
            return (parent.left == this);
        }
        boolean isRightNode() {
            return (parent.right == this);
        }
    }
    
    MyBTree() {  numberTree = 0;  heightTree=0; myRoot = null;   }
    
    public int mySize(){   return numberTree;  }
    public int myHeight(){   return heightTree;  }
    public boolean isEmpty() {  return (mySize() == 0);   }
    void setRoot(Node<T> root) {  myRoot = root;   }
	Node<T> root() { return myRoot; }
	
    boolean isLessThan (Object o1, Object o2) throws Exception {
        Character myChar1;
        Character myChar2;
        try {
            myChar1 = (Character) o1;
            myChar2 = (Character) o2;
        }
        catch (ClassCastException exc) {
            throw new Exception();
        }
        return ( myChar1.charValue() < myChar2.charValue() );
    }
	
	boolean isGreThan (Object o1, Object o2) throws Exception {
        return ( isLessThan (o2, o1) );
    }
	
	boolean isEqual (Object o1, Object o2) throws Exception {
        return ( (! isLessThan (o1, o2)) && (! isLessThan (o2, o1)) );
    }
	
    boolean isLessThanOrEqualTo (Object o1, Object o2) throws Exception {
        return ( ! isLessThan (o2, o1) );
    }
    
    boolean isGreaterThanOrEqualTo (Object o1, Object o2) throws Exception {
        return ( ! isLessThan (o1, o2) );
    }
    
    Node<T> ÝnsertItem(T a, Node<T> b) {
    	
        if (myRoot == null) {
            myRoot = new Node<T>(a);
            b = myRoot;
        } 
        else if (b == null)
            b = new Node<T>(a); 
        else if (a.compareTo(b.data) < 0)
            b.left = ÝnsertItem(a, b.left);
        else if (a.compareTo(b.data) > 0)
            b.right = ÝnsertItem(a, b.right);
        else;
        
        numberTree++;
        return b;
    }

    Node<T> deleteItem(T a, Node<T> b) {
        if (b == null)
            return b;
        if (a.compareTo(b.data) < 0)
            b.left = deleteItem(a, b.left);
        else if (a.compareTo(b.data) > 0)
            b.right = deleteItem(a, b.right);
        else if (b.left != null && b.right != null) {
            b.data = findMin(b.right).data;
            b.right = deleteItem(b.data, b.right);
        } else {
            boolean state = false;
            if (b.equals(myRoot))
                state = true;
            if (state) {
                if (b.right != null) {
                    b.data = findMin(b.right).data;
                    b.right = deleteItem(b.data, b.right);
                } else {
                    b = (b.left != null) ? b.left : b.right;
                }
                myRoot = b;
            } else
                b = (b.left != null) ? b.left : b.right;
        }
        
        numberTree--;
        return b;
    }

    Node<T> findMin(Node<T> t) {
        if (t == null)
            return null;
       else if (t.left == null)
            return t;
        
        return findMin(t.left);
    }
    
    Node<T> findMax(Node<T> t ){
    	if( t != null )
    		while( t.right != null )
    			t = t.right;
    	return t;
    }
    
    String writePathInConsole(Node<T> root) {
        ArrayList<ArrayList<T>> tmpArrayList = new ArrayList<ArrayList<T>>();
        findPath(root, new ArrayList<T>(), tmpArrayList);
        if (!tmpArrayList.isEmpty()) {
        	myPath = "";
            for (int x = 0; x < tmpArrayList.size(); x++) {
                for (int y = 0; y < tmpArrayList.get(x).size(); y++)
                    myPath = myPath + "." + tmpArrayList.get(x).get(y);
                return myPath.substring(myPath.indexOf(".") + 1);
            }
        }
		return myPath.substring(myPath.indexOf(".") + 1);
    }
    
    String writePath(Node<T> root) {
        ArrayList<ArrayList<T>> tmpArrayList = new ArrayList<ArrayList<T>>();
        findPath(root, new ArrayList<T>(), tmpArrayList);
        String temp = null;
        if (!tmpArrayList.isEmpty()) {
        	myPath = "";
            for (int x = 0; x < tmpArrayList.size(); x++) {
                for (int y = 0; y < tmpArrayList.get(x).size(); y++)
                    myPath = myPath + "." + tmpArrayList.get(x).get(y);
                
                temp += myPath.substring(myPath.indexOf(".") + 1) ;
            }
        }
        return temp;
    }
    
    void findPath(Node<T> root, ArrayList<T> path, ArrayList<ArrayList<T>> tmpArrayList) {
        if (root == null)
            return;
        path.add(root.data);
        if (root.left == null && root.right == null) {
                ArrayList<T> curPath = new ArrayList<T>(path);
                tmpArrayList.add(curPath);
        }
        findPath(root.left, path, tmpArrayList);
        findPath(root.right, path, tmpArrayList);
        path.remove(path.size() - 1);
    }

}
