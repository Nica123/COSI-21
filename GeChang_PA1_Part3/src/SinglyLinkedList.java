public class SinglyLinkedList<T>{
  //public variable size, head, tail, could be change to private if used getter and setter
  public int size;
  public SinglyLinkedNode <T>head;
  public SinglyLinkedNode <T>tail;
  //assuming SinglyLinkedList's index start at 0

  /**
  this construct the object SinglyLinkedList
  call constructor with no input create a empty SinglyLinkedList
  */
  public SinglyLinkedList(){
    this.size = 0;
    this.head = null;
    this.tail = null;
  }

  /**
  get the pointer to the first node of SinglyLinkedList
  @return the pointer to the first node
  */
  public SinglyLinkedNode<T> getHead(){
    return this.head;
  }

  /**
  insert a node with data as key field into Linkedlist at given index
  @param data the key field of the node needed to insert
  @param index the index of list to put the node in
  */
  public void insert(T data, int index){
    SinglyLinkedNode <T>a = this.head;
    //checking empty SinglyLinkedList
    if(a == null){
      if(index == 0){
        this.head = new SinglyLinkedNode <T>(data,null);
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
      this.head = new SinglyLinkedNode<T>(data,a);
      this.size +=1;
      return;
    }
    for(int i = 1; i<index;i++){
      a = a.next;
      if(a == null){
        throw new IllegalArgumentException("index out of bound");
      }
    }
    SinglyLinkedNode <T>newNode = new SinglyLinkedNode<T>(data, a.next);
    a.next = newNode;
    // checking adding at tail
    if(newNode.next == null){
      this.tail = newNode;
    }
    this.size += 1;
  }


  /**
  insert a node with data as key field into Linkedlist at the end
  @param data the key field of the node needed to insert
  */
  public void insert(T data){
    SinglyLinkedNode <T>newNode = new SinglyLinkedNode<T>(data, null);
    if(this.size !=0){
      this.tail.next = newNode;
      this.tail = newNode;
    } else{
      this.head = newNode;
      this.tail = newNode;
    }
    this.size +=1;
  }

  /**
  remove the node given index in the SinglyLinkedList and return the data of that node
  @param index the index of the node which needed to be deleted
  @return a pointer to the removed node
  */
  public SinglyLinkedNode<T> remove(int index){
    SinglyLinkedNode<T> a = this.head;
    //checking invalid input or function call
    if(a == null){
      throw new IllegalArgumentException("empty linked list");
    }
    if( index+1 > this.size || index < 0){
      throw new IllegalArgumentException("index out of bound");
    }
    //specifying for SLL with only one element
    if(a.next == null){
      this.head = null;
      this.tail = null;
      this.size -= 1;
      return a;
    }
    //checking head
    if(index == 0){
      this.head = a.next;
      this.size -= 1;
      return a;
    }
    for(int i = 1; i<index; i++){
      a = a.next;
    }
    SinglyLinkedNode<T> b = a.next;
    a.next = a.next.next;
    //checking tail
    if(a.next == null){
      this.tail = a;
    }
    this.size -= 1;
    return b;
  }

  /**
  remove the node at the end of the SinglyLinkedList and return the data of that node
  @return a pointer to the removed node
  */
  public SinglyLinkedNode<T> remove(){
    SinglyLinkedNode<T> a = this.head;
    //checking empty SLL
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
    SinglyLinkedNode<T> b = a.next;
    a.next = a.next.next;
    this.tail = a;
    this.size -=1;
    return b;
  }

  /**
  delete the first node contain the given data in the SinglyLinkedList
  @param data the node contain such data will be deleted
  */
  public SinglyLinkedNode<T> remove(T data){
    SinglyLinkedNode<T> a = this.head;
    if(a == null){
      throw new IllegalArgumentException("empty linked list");
    }
    //checking if data is in head node
    if(a.data == data){
      //checking one element SLL
      if(a.next!=null){
        this.head = a. next;
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
      SinglyLinkedNode<T> b = a.next;
      a.next = a.next.next;
      this.size -= 1;
      //check if data is in tail node
      if(a.next == null){
        this.tail = a;
      }
      return b;
    }
  }

  /**
  get the size of the SinglyLinkedList
  @return the size of the SinglyLinkedList
  */
  public int size(){
    return this.size;
  }

  /**
  get a combined string of data in all nodes in the list orderly
  @return a string contain the string of data in all nodes in the list
  */
  public String toString(){
    SinglyLinkedNode <T>a = this.head;
    String txt = "";
    while(a != null){
      txt = txt + a.toString() +" ";
      a = a.getNext();
    }
    return txt;
  }
}
