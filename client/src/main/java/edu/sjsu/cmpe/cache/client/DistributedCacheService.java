package edu.sjsu.cmpe.cache.client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class DistributedCacheService implements CacheServiceInterface {
    
	ArrayList<String> cacheServerUrl =  new ArrayList<String>();
	CRDTClient crdtClient;
	
    public DistributedCacheService( ArrayList<Strings> urlList) {
        this.cacheServerUrl = urlList;
		crdtClient = new CRDTClient(urlList);
    }

    @Override
    public String get(long key) {
		return crdtClient.getValues(key);
    }

    @Override
    public void put(long key, String value) {
       List<String> response = crdtClient.putAsync(key, value);
		   int min = urlList.size()/2 ;
           if (response.size() >= min) {
               delete(key); // deleting inserted values
           }
       }
    }

    @Override
    public void delete(long key) {
        if(crdtClient.deleteVal(key));
		{
			 System.out.println("successfully deleted");
		}
		else
		{
			 System.out.println("error in deleting");
		}
    }

}
