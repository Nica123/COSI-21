public class SinglyLinkedNode<T>{
  public T data;
  public SinglyLinkedNode<T> next;

  /**
  this construct the object SinglyLinkedNode with two input
  @param data the key field in each Node
  @param next the pointer to the next Node on the SinglyLinkedList
  */
  public SinglyLinkedNode(T data, SinglyLinkedNode<T> next){
    this.data = data;
    this.next = next;
  }

  /**
  set the key field with input data
  @param data the key need to be set for this node
  */
  public void setData(T data){
    this.data = data;
  }

  /**
  get the key field of the node
  @return the key field of this node
  */
  public T getData(){
    return this.data;
  }

  /**
  get the next field of the node
  @return the pointer to the next node
  */
  public SinglyLinkedNode<T> getNext(){
    return this.next;
  }

  /**
  set the next field with the pointer to next field
  @param next the pointer to next field
  */
  public void setNext(SinglyLinkedNode<T> next){
    this.next = next;
  }

  /**
  toString method for SinglyLinkedNode
  @return the toString() of item stored in key field
  */
  public String toString(){
    return data.toString();
  }
}
