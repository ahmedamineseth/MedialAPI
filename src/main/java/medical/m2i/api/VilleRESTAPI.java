package medical.m2i.api;

import entities.VilleEntity;
import medical.m2i.dao.DbConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/ville")
public class VilleRESTAPI {

    EntityManager em = DbConnection.getInstance();

    //ville
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public List<VilleEntity> getAll(){
        List<VilleEntity> p = em.createNativeQuery("SELECT * FROM ville", VilleEntity.class).getResultList();
        return p;
    }

    //ville/1
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public VilleEntity getOne( @PathParam("id") int id ){
        return em.find(VilleEntity.class , id);
    }

    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Path("")
    public void addVille( VilleEntity p ){
        // Récupération d’une transaction
        EntityTransaction tx = em.getTransaction();
        // Début des modifications
        try {
            tx.begin();
            em.persist(p);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            // em.close();
            // emf.close();
        }
    }
}
