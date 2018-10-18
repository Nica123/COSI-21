public class DoublyLinkedNode<T>{
	public T data;
	public DoublyLinkedNode<T> next;
	public DoublyLinkedNode<T> prev;
	
	public DoublyLinkedNode(T data, DoublyLinkedNode next, DoublyLinkedNode prev) {
		this.data = data;
		this.next = next;
		this.prev = prev;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public T getData() {
		return this.data;
	}
	
	public void setNext(DoublyLinkedNode<T> next) {
		this.next = next;
	}
	
	public DoublyLinkedNode<T> getNext(){
		return this.next;
	}
	
	public void setPrev(DoublyLinkedNode<T> prev) {
		this.prev = prev;
	}
	
	public DoublyLinkedNode<T> getPrev(){
		return this.prev;
	}
	
	public String toString() {
		return this.data.toString();
	}
}
