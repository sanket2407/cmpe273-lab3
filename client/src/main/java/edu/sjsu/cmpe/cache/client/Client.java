package edu.sjsu.cmpe.cache.client;

import java.util.List;
import java.util.ArrayList;
import com.google.common.hash.Hashing;

public class Client {

    public static void main(String[] args) throws Exception {
        
         System.out.println("Starting Cache Client…\n");
         
        List<CacheServiceInterface> link = new ArrayList<CacheServiceInterface>();
        link.add(new DistributedCacheService("http://localhost:3000"));
        link.add(new DistributedCacheService("http://localhost:3001"));
        link.add(new DistributedCacheService("http://localhost:3002"));
         
        char[] values = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
    	
        //Putting data
        for(int i=0; i<10; i++)
        {
        	int bucket = Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(i+1)), link.size()); //md5 hash function is used.
        	link.get(bucket).put(i, Character.toString(values[i])); //i = key
        	System.out.println("http://localhost:300" + bucket + " put key-value pair " + i+1 +"->"+ values[i]);
        }
    
        //getting data
        for(int j=0; j<10; j++)
        {
        	int bucket1 = Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(j+1)), link.size());
        	link.get(bucket1).get(j+1); //j = key
        	System.out.println("http://localhost:300" + bucket1 + " get key-Value pair " + j+1 +"->"+ link.get(bucket1).get(j+1));

        }
    }

}
