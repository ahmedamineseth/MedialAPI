package medical.m2i.api;

import entities.VilleEntity;
import medical.m2i.dao.DbConnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/ville")
public class VilleRESTAPI {

    EntityManager em = DbConnection.getInstance();

    //ville
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public List<VilleEntity> getAll( @QueryParam("nom") String pnom ){
        List<VilleEntity> lv;

        if( pnom == null || pnom.length() == 0  ){
            lv = em.createNativeQuery("SELECT * FROM ville", VilleEntity.class).getResultList();
        }else{
            lv = em.createNamedQuery("ville.findAllByNom" ).setParameter("nom" , "%"+pnom+"%").getResultList();
        }

        return lv;
    }



    private VilleEntity getVille( int id ){
        VilleEntity v = em.find(VilleEntity.class , id);
        if(  v == null ){
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return v;
    }

    //ville/1
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public VilleEntity getOne( @PathParam("id") int id ) throws Exception {
        return getVille(id);
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

    @DELETE
    @Path("/{id}")
    public void deleteVille( @PathParam("id") int id  ){

        EntityTransaction tx = em.getTransaction();
        // Début des modifications
        try {
            tx.begin();
            em.remove( getVille(id) );
            tx.commit();
        // }catch ( IllegalArgumentException e ){
        //    throw new WebApplicationException(Response.Status.NOT_FOUND); // sol 2
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Exception " + e.getMessage() );
            throw e;
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes( MediaType.APPLICATION_JSON )
    public void updateVille( @PathParam("id") int id , VilleEntity vparam ){

        VilleEntity v = getVille(id);

        v.setNom( vparam.getNom() );
        v.setCodePostal( vparam.getCodePostal() );
        v.setPays( vparam.getPays() );


        EntityTransaction tx = em.getTransaction();
        // Début des modifications
        try {
            tx.begin();
            em.persist( v );
            tx.commit();
            // }catch ( IllegalArgumentException e ){
            //    throw new WebApplicationException(Response.Status.NOT_FOUND); // sol 2
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Exception " + e.getMessage() );
            throw e;
        }
    }
}
