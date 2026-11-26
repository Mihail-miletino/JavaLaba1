package view.api;

import controller.FacadeController;
import model.News;
import view.dto.CreateNewsRequest;
import view.dto.Mapper;
import view.dto.NewsItemDTO;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/news")
public class NewsAPI {
    private final FacadeController facade = new FacadeController();
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNews() {
        List<NewsItemDTO> dtos = facade.getAllNews().stream().map(Mapper::toDTO).collect(Collectors.toList());
        return Response.ok(dtos).build();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchNews(@QueryParam("keyword") String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"Keyword required\"}").build();
        }
        List<NewsItemDTO> dtos = facade.searchNewsByKeywords(keyword).stream().map(Mapper::toDTO).collect(Collectors.toList());
        return Response.ok(dtos).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNews(CreateNewsRequest req) {
        Set<ConstraintViolation<CreateNewsRequest>> violations = validator.validate(req);
        if (!violations.isEmpty()) {
            String err = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"" + err + "\"}").build();
        }
        News created = facade.addNews(req.getTitle(), req.getText(), req.getCategoryId());
        return Response.status(Response.Status.CREATED).entity(Mapper.toDTO(created)).build();
    }
}
