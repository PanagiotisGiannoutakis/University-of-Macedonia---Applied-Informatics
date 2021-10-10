import java.util.*;
import java.util.concurrent.*;

/**
 *
 * Based on www.codejava.net exmaple
 */

public class Askisi1bSemaphore {

		static Semaphore sem = new Semaphore(1);
		
		public static void main(String[] args) {
		  
			ConcurrentHashMap<Integer,Integer> conHashMap = new ConcurrentHashMap<Integer,Integer>();
			  
			conHashMap.putIfAbsent(0, 0);
			conHashMap.putIfAbsent(1, 1);
			conHashMap.putIfAbsent(2, 2);
			conHashMap.putIfAbsent(3, 3);
			
			//int count = 4;
			//conHashMap.putIfAbsent(count, count);
			
			while(true) {
				new AddThread(conHashMap).start();
				new RemoveThread(conHashMap).start();
				new ReadThread(conHashMap).start();
				new UpdateThread(conHashMap).start();
			}
		}
		
		public static void delay (int d) {
			try {
				Thread.sleep((int)(Math.random()*d));
			} catch (InterruptedException e) { }
		}	
		
		static class AddThread extends Thread{
			
			private ConcurrentHashMap<Integer,Integer> map;
			
			public AddThread(ConcurrentHashMap<Integer,Integer> map) {
					this.map = map;
			}
				
			public void run() {
				int count = 4;
							
				while (true) {
					delay(2000);
					try {
						sem.acquire();
					} catch (InterruptedException e) {}
					map.putIfAbsent(count, count);
					System.out.println("Add done");
					count++;
					sem.release();
				}
			}
		}
		  
		static class UpdateThread extends Thread{
			
			private ConcurrentHashMap<Integer,Integer> map;
			
			public UpdateThread(ConcurrentHashMap<Integer,Integer> map) {
				this.map = map;
			}
				
			public void run() {
				int count = 0;
							
				while (true) {
					delay(4000);
					try {
						sem.acquire();
					} catch (InterruptedException e) {}
					Integer value = map.get(count);
					if (value != null)
						map.computeIfPresent(count, (key, oldValue) -> oldValue + 100);
					System.out.println("Update done");
					count++;
					sem.release();
				}
			}
		}
		  
		static class RemoveThread extends Thread{
			
			private ConcurrentHashMap<Integer,Integer> map;
			
			public RemoveThread(ConcurrentHashMap<Integer,Integer> map) {
				this.map = map;
			}
				
			public void run() {
				int count = 0;
							
				while (true) {
					delay(5000);
					try {
						sem.acquire();
					} catch (InterruptedException e) {}
					Integer value = map.remove(count);
					System.out.println("Remove done");
					count++;
					sem.release();
				}
			}
		}
		  
		  static class ReadThread extends Thread {
		  
				private ConcurrentHashMap<Integer,Integer> map;
			
			public ReadThread(ConcurrentHashMap<Integer,Integer> map) {
					this.map = map;
				}
			
			public void run() {
			    while (true) {
					try {
						sem.acquire();
					} catch (InterruptedException e) {}
					System.out.print("Current Map :");
					Iterator<Integer> iterator = map.keySet().iterator();
				    while(iterator.hasNext()){
					   Integer key = iterator.next();
					   System.out.print(key+" : " + map.get(key)+" ; ");
					}
					System.out.println();
					delay(5000);
					sem.release();
				}	  
			}
		}	  
} 