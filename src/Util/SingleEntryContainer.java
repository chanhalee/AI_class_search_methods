package Util;

public class SingleEntryContainer<T, S>{
	private T item1;
	private S item2;
	public SingleEntryContainer(T item1, S item2){
		this.item1 = item1;
		this.item2 = item2;
	}
	public boolean equals(SingleEntryContainer<T, S> compare_with){
		return (compare_with.item1 == this.item1 && compare_with.item2 == this.item2);
	}
	public T getItem1(){return item1;}
	public S getItem2(){return item2;}
	public void setItem1(T value){
		item1 = value;
	}
	public void setItem2(S value){
		item2 = value;
	}
}