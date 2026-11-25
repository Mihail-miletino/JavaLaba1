package view.api;

import controller.NewsController;
import model.News;
import view.dto.CreateNewsRequest;
import view.dto.NewsMapper;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/creater")
public class CreaterAPI {

    private final NewsController controller = new NewsController();
    private final Validator validator =
            Validation.buildDefaultValidatorFactory().getValidator();

    @POST
    @Path("/news")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNews(CreateNewsRequest request) {
        if (request == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"Тело запроса пустое\"}")
                    .build();
        }

        // Валидация DTO (Bean Validation) — аналог Zod-схемы, только в Java
        Set<ConstraintViolation<CreateNewsRequest>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            String errors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("; "));
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"" + errors + "\"}")
                    .build();
        }

        News created = controller.create(request.getTitle(), request.getText());
        return Response.status(Response.Status.CREATED)
                .entity(NewsMapper.toResponse(created))
                .build();
    }
}
