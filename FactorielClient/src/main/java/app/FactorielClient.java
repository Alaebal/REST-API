package app;


import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class FactorielClient {

    private static final String BASE_URL =
            "http://localhost:8383/Factoriel-0.0.1-SNAPSHOT/api/factoriel";

    private final Client client = ClientBuilder.newClient();
    private final WebTarget racine = client.target(BASE_URL);

    private String appeler(WebTarget cible) {
        Response rep = cible.request(MediaType.TEXT_PLAIN).get();
        try {
            String corps = rep.readEntity(String.class);
            return rep.getStatus() == 200
                    ? corps
                    : "Erreur " + rep.getStatus() + " : " + corps;
        } finally {
            rep.close();
        }
    }

    public String factoriel(int n) {
        return appeler(racine.path(String.valueOf(n)));
    }

    private String avecXYZ(String chemin, int x, int y, int z) {
        return appeler(racine.path(chemin)
                .queryParam("x", x)
                .queryParam("y", y)
                .queryParam("z", z));
    }

    public String sommeFactoriels(int x, int y, int z)   { return avecXYZ("somme-factoriels", x, y, z); }
    public String factorielSomme(int x, int y, int z)    { return avecXYZ("factoriel-somme", x, y, z); }
    public String produitFactoriels(int x, int y, int z) { return avecXYZ("produit-factoriels", x, y, z); }
    public String factorielProduit(int x, int y, int z)  { return avecXYZ("factoriel-produit", x, y, z); }

    public void fermer() {
        client.close();
    }

    public static void main(String[] args) {
    	FactorielClient c = new FactorielClient();
        Scanner sc = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n===== Client Factoriel =====");
            System.out.println("1. n!");
            System.out.println("2. x! + y! + z!");
            System.out.println("3. (x + y + z)!");
            System.out.println("4. x! * y! * z!");
            System.out.println("5. (x * y * z)!");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            choix = sc.nextInt();

            switch (choix) {
                case 1:
                    System.out.print("n = ");
                    System.out.println("Résultat : " + c.factoriel(sc.nextInt()));
                    break;

                case 2:
                case 3:
                case 4:
                case 5:
                    System.out.print("x = ");
                    int x = sc.nextInt();
                    System.out.print("y = ");
                    int y = sc.nextInt();
                    System.out.print("z = ");
                    int z = sc.nextInt();

                    String res;
                    switch (choix) {
                        case 2:
                            res = c.sommeFactoriels(x, y, z);
                            break;
                        case 3:
                            res = c.factorielSomme(x, y, z);
                            break;
                        case 4:
                            res = c.produitFactoriels(x, y, z);
                            break;
                        default:
                            res = c.factorielProduit(x, y, z);
                            break;
                    }
                    System.out.println("Résultat : " + res);
                    break;

                case 0:
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide.");
                    break;
            }
        } while (choix != 0);

        sc.close();
        c.fermer();
    }
}