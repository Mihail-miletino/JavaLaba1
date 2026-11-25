package view.api;

import controller.NewsController;
import model.News;
import view.dto.NewsMapper;
import view.dto.NewsResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/reader")
public class ReaderAPI {

    private final NewsController controller = new NewsController();

    @GET
    @Path("/news/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNews() {
        List<NewsResponse> dto = controller.getAll().stream()
                .map(NewsMapper::toResponse)
                .collect(Collectors.toList());
        return Response.ok(dto).build();
    }

    @GET
    @Path("/news")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOneNews(@QueryParam("id") int id) {
        News news = controller.getById(id);
        if (news == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Новость с id=" + id + " не найдена\"}")
                    .build();
        }
        return Response.ok(NewsMapper.toResponse(news)).build();
    }
}
