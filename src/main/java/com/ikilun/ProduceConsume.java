package com.ikilun;

import java.util.ArrayList;
import java.util.List;

public class ProduceConsume {
	public static void main(String[] args) {
		Store store = new Store();
		Thread t1 = new Thread(new Provider(store));
		Thread t2 = new Thread(new Consume(store));
		t2.start();
		t1.start();
	}
}
class Store{
	List<Integer> strList = new ArrayList(); 
	int i = 1;
	public synchronized void put(Integer i){
		while(i==20){//满了
			try {
				System.out.println("生产已满");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		strList.add(i);
		i++;
		this.notify();
	}
	
	public synchronized int get(){
		//没有
		while(strList.isEmpty()){
			try {
				this.wait();
				System.out.println("等待生产者");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notify();
		return strList.remove(0);
	}
}
class Consume implements Runnable{
	private Store store;
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public Consume(Store store){
		this.store = store;
	}
	@Override
	public void run() {
		for(int i=0;i<20;i++){
			System.out.println("消费"+store.get());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
class Provider implements Runnable{
	private Store store;
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public Provider(Store store){
		this.store = store;
	}
	@Override
	public void run() {
		for(int i=0;i<20;i++){
			store.put(i);
			System.out.println("生产"+i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
