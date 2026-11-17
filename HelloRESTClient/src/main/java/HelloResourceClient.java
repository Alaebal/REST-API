package client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
public class HelloResourceClient {
	
	public static void main(String[] args) {
		// create new JAX-RS Client
		Client client= ClientBuilder.newClient();
		//The bas URL of the service
		WebTarget target=client.target("http://localhost:8383/ Hello_JAX-RS-0.0.1-SNAPSHOT/rest/greetings");
		//Consuming sayhello Method
		//Get the response from the target URL Response response=target.request().get();
		//read the result as a string
		String result=response.readEntity(String.class);
		//print the result of the standard output System.out.println(result); response.close();
		}


}
