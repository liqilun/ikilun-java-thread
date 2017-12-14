* 线程之间的通信（thread signal）
	* 线程通信的目的是为了能够让线程之间相互发送信号
	* java.lang.Object提供的wait()、notify()、notifyAll()
		* Java提供了一种内联机制可以让线程在等待信号时进入非运行状态。
			当一个线程调用任何对象上的wait()方法时便会进入非运行状态，
			直到另一个线程调用同一个对象上的notify()或notifyAll()方法。
			为了能够调用一个对象的wait()、notify()方法，调用线程必须先获得这个对象的锁。
			因为线程只有在同步块中才会占用对象的锁，所以线程必须在同步块中调用wait()、notify()方法