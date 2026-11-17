package factoriel;

import java.math.BigInteger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/factoriel")
@Produces(MediaType.TEXT_PLAIN)
public class FactorielResource {

    private static final int MAX = 5000;

    /** Factoriel d'un entier : 0! = 1 ; n! = n * (n-1)! */
    private BigInteger fact(long n) {
        if (n < 0) {
            throw erreur("L'entier doit �tre positif ou nul : " + n);
        }
        if (n > MAX) {
            throw erreur("Valeur trop grande (max " + MAX + ") : " + n);
        }
        BigInteger r = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            r = r.multiply(BigInteger.valueOf(i));
        }
        return r;
    }

    private WebApplicationException erreur(String message) {
        return new WebApplicationException(
            Response.status(Response.Status.BAD_REQUEST)
                    .entity(message).type(MediaType.TEXT_PLAIN).build());
    }

   
    @GET
    @Path("/{n}")
    public String factoriel(@PathParam("n") int n) {
        return fact(n).toString();
    }

    
    @GET
    @Path("/somme-factoriels")
    public String sommeFactoriels(@QueryParam("x") int x,
                                  @QueryParam("y") int y,
                                  @QueryParam("z") int z) {
        return fact(x).add(fact(y)).add(fact(z)).toString();
    }

    
    @GET
    @Path("/factoriel-somme")
    public String factorielSomme(@QueryParam("x") int x,
                                 @QueryParam("y") int y,
                                 @QueryParam("z") int z) {
        return fact((long) x + y + z).toString();
    }

    
    @GET
    @Path("/produit-factoriels")
    public String produitFactoriels(@QueryParam("x") int x,
                                    @QueryParam("y") int y,
                                    @QueryParam("z") int z) {
        return fact(x).multiply(fact(y)).multiply(fact(z)).toString();
    }


    @GET
    @Path("/factoriel-produit")
    public String factorielProduit(@QueryParam("x") int x,
                                   @QueryParam("y") int y,
                                   @QueryParam("z") int z) {
        return fact((long) x * y * z).toString();
    }
}
