package resources;

import domain.Ingredient;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("ingredienten")
@Transactional
public class Ingredienten {
    
    @PersistenceContext
    private EntityManager em;
    
    private long ingredientId;
    private String ingredientNaam;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ingredient> getAllIngredienten(@QueryParam("start")@DefaultValue("0")int start,@QueryParam("results")@DefaultValue("10")int results){
        TypedQuery<Ingredient> query =  em.createNamedQuery("Ingredient.findAll", Ingredient.class);
        query.setFirstResult(start);
        query.setMaxResults(results);
        return query.getResultList();
    }
    
    @GET
    @Path("{ingredientid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIngredient(@PathParam("ingredientid") long id)
    {
        Ingredient ingredient = em.find(Ingredient.class, id);

        return Response.ok(ingredient).build();
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientNaam() {
        return ingredientNaam;
    }

    public void setIngredientNaam(String ingredientNaam) {
        this.ingredientNaam = ingredientNaam;
    }
    
    
}
