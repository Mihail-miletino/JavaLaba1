package view.api;

import controller.NewsController;
import model.News;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/creater")
public class CreaterAPI {

    private final NewsController controller = new NewsController();

    @POST
    @Path("/news")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNews(News incoming) {
        if (incoming == null
                || incoming.getTitle() == null
                || incoming.getTitle().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"Поле title обязательно\"}")
                    .build();
        }
        News created = controller.create(incoming.getTitle(), incoming.getText());
        return Response.status(Response.Status.CREATED).entity(created).build();
    }
}
