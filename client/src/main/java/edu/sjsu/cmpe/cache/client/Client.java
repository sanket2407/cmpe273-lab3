package edu.sjsu.cmpe.cache.client;
import java.util.ArrayList;

public class Client {

    public static void main(String[] args) throws Exception {
	
        System.out.println("Starting Cache Client...");
		
		ArrayList<String> urlList = new ArrayList<String>();
        urlList.add("http://localhost:3000");
		urlList.add("http://localhost:3001");
		urlList.add("http://localhost:3002");
		
        DistributedCacheService cache = new DistributedCacheService(urlList);

        cache.put(1,"a");
        System.out.println("putting a to key 1");

        Thread.sleep(30000); //sleep thread for 30 seconds

        cache.put(1,"b");
        System.out.println("putting b to key 1" );

        Thread.sleep(30000); //sleep thread for 30 seconds

        System.out.println("get(1) => " + cache.get(1)); // getting value for key = 1

        //System.out.println("deleting value" + cache.delete(1);

    }

}
