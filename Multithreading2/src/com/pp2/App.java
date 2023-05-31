package com.pp2;

public class App {
	public static void main(String[] args) {
	// TODO Auto-generated method stub
	Thread t1 = new Thread (new Runner1());
	Thread t2 = new Thread (new Runner2());
	t1.start();
	t2.start();
	}

}