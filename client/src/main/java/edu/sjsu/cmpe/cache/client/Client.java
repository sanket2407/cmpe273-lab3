package edu.sjsu.cmpe.cache.client;

import java.util.List;
import java.util.ArrayList;
import com.google.common.hash.Hashing;

public class Client {

    public static void main(String[] args) throws Exception {
        
         System.out.println("Starting Cache Client…");
         
        List<CacheServiceInterface> link = new ArrayList<CacheServiceInterface>();
        link.add(new DistributedCacheService("http://localhost:3000"));
        link.add(new DistributedCacheService("http://localhost:3001"));
        link.add(new DistributedCacheService("http://localhost:3002"));
         
        char[] values = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
    	
        //Putting data
        //md5 hash function
        for(int i=0; i<10; i++)
        {
        	int bucket = Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(i+1)), link.size()); 
        	link.get(bucket).put(i, Character.toString(values[i])); 
        	System.out.println("http://localhost:300" + bucket + " put key-value pair " + i+1 +"->"+ values[i]);
        }
    
        //getting data
        for(int j=0; j<10; j++)
        {
        	//int bucket1 = Hashing.consistentHash(Hashing.md5().hashString(Integer.toString(j+1)), link.size());
            for(int k=0; k<link.size(); k++)
            {
                if(link.get(k).get(j+1) != null)
                {
                System.out.println("http://localhost:300" + k + " get key-Value pair " +j+1 + "->" + link.get(k).get(j+1));
                }
            }
        }
        
        System.out.println("Ending Cache Client…");
    }

}
