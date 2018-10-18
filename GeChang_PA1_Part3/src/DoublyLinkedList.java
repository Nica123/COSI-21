public class DoublyLinkedList<T>{
	public int size;
	public DoublyLinkedNode<T> head;
	public DoublyLinkedNode<T> tail;
	
	public DoublyLinkedList() {
		this.size = 0;
		this.head = null;
		this.tail = null;
	}
	
	public DoublyLinkedNode<T> getHead(){
	    return this.head;
	}
	
	public void insert(T data, int index){
	    DoublyLinkedNode<T> a = this.head;
	    //checking empty SinglyLinkedList
	    if(a == null){
	      if(index == 0){
	        this.head = new DoublyLinkedNode <T>(data,null,null);
	        this.tail = this.head;
	        this.size +=1;
	        return;
	      }
	      else{
	        throw new IllegalArgumentException("index out of bound");
	      }
	    }
	    //checking index = 0 i.e adding at front
	    if(index == 0){
	    	this.head = new DoublyLinkedNode<T>(data,a,null);
	    	this.size +=1;
	    	return;
	    }
	    for(int i = 1; i<index;i++){
	    	a = a.next;
	    	if(a == null){
	    		throw new IllegalArgumentException("index out of bound");
	      }
	    }
	    DoublyLinkedNode <T>newNode = new DoublyLinkedNode<T>(data, a.next,a);
	    a.next = newNode;
	    if(newNode.next != null) {
	    	newNode.next.prev = newNode;
	    }
	    // checking adding at tail
	    if(newNode.next == null){
	    	this.tail = newNode;
	    }
	    this.size += 1;
	}
	

	public void insert(T data){
		DoublyLinkedNode <T>newNode = new DoublyLinkedNode<T>(data, null, null);
		if(this.size !=0){
			this.tail.next = newNode;
			newNode.prev = this.tail;
			this.tail = newNode;
		} else{
			this.head = newNode;
			this.tail = newNode;
		}
		this.size +=1;
	}
	
	public DoublyLinkedNode<T> remove(int index){
	    DoublyLinkedNode<T> a = this.head;
	    //checking invalid input or function call
	    if(a == null){
	      throw new IllegalArgumentException("empty linked list");
	    }
	    if( index+1 > this.size || index < 0){
	      throw new IllegalArgumentException("index out of bound");
	    }
	    //specifying for DLL with only one element
	    if(a.next == null){
	      this.head = null;
	      this.tail = null;
	      this.size -= 1;
	      return a;
	    }
	    //checking head
	    if(index == 0){
	      this.head = a.next;
	      a.next.prev = null;
	      this.size -= 1;
	      return a;
	    }
	    for(int i = 1; i<index; i++){
	      a = a.next;
	    }
	    DoublyLinkedNode<T> b = a.next;
	    a.next = a.next.next;
	    //checking tail
	    if(a.next == null) {
	    	this.tail = a;
	    } else {
	    	a.next.prev = a;
	    }
	    this.size -= 1;
	    return b;
	}
	

	
	
	
	public DoublyLinkedNode<T> remove(){
	    DoublyLinkedNode<T> a = this.head;
	    //checking empty DLL
	    if(a == null){
	    	throw new IllegalArgumentException("empty linked list");
	    }
	    //checking one element list
	    if(a.next == null){
	    	this.head = null;
	    	this.tail = null;
	    	this.size -= 1;
	    	return a;
	    }
	    while(a.next.next!=null){
	    	a = a.next;
	    }
	    DoublyLinkedNode<T> b = a.next;
	    a.next = a.next.next;
	    this.tail = a;
	    this.size -=1;
	    return b;
	  }
	
	public DoublyLinkedNode<T> remove(T data){
	    DoublyLinkedNode<T> a = this.head;
	    if(a == null){
	      throw new IllegalArgumentException("empty linked list");
	    }
	    //checking if data is in head node
	    if(a.data == data){
	    	//checking one element DLL
	    	if(a.next!=null){
	    		this.head = a.next;
	    		a.next.prev = null;
	    		this.size -=1;
	    		return a;
	    	} else {
	    		this.head = null;
	    		this.tail = null;
	    		this.size -= 1;
	    		return a;
	    	}
	    }
	    while(a.next.data!=data && a.next.next!=null){
	    	a = a.next;
	    }
	    if(a.next.next==null && a.next.data != data){
	    	throw new IllegalArgumentException("not found");
	    }
	    else{
	    	DoublyLinkedNode<T> b = a.next;
	    	a.next = a.next.next;
	    	//check if data is in tail node
	    	if(a.next == null){
	    		this.tail = a;
	    	} else {
	    		a.next.prev = a;
	    	}
	    	this.size -= 1;
	    	return b;
	    }
	}
	
	public int size(){
	    return this.size;
	}

	public String toString(){
	    DoublyLinkedNode <T>a = this.head;
	    String txt = "";
	    while(a != null){
	      txt = txt + a.toString() +" ";
	      a = a.next;
	    }
	    return txt;
	}
	
	
	
	
	
}