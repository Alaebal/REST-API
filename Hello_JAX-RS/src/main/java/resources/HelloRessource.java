package resources;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

public class HelloRessource {
@GET
@Produces(MediaType.TEXT_PLAIN) 
public String sayHello() {
	return "hello from JAX-RS";}
@GET
@Produces(MediaType.TEXT_PLAIN) 
@Path("{FirstName}/{LastName}")
public String
sayHello1(@PathParam(value="FirstName")String 
		Prenom,@PathParam(value="LastName")String Nom){
return "hello from JAX-RS "+Prenom+" "+Nom;
}
@GET
@Path("test")
@Produces(MediaType.TEXT_PLAIN)
public String sayHello2(@QueryParam(value="FirstName")String Prenom,@QueryParam(value="LastName")String Nom){
return "hello from JAX-RS "+Prenom+" "+Nom;
}


}
