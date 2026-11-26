package view.api;

import controller.FacadeController;
import model.Category;
import view.dto.CreateCategoryRequest;
import view.dto.CategoryDTO;
import view.dto.Mapper;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/categories")
public class CategoryAPI {
    private final FacadeController facade = new FacadeController();
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategories() {
        List<CategoryDTO> dtos = facade.getAllCategories().stream().map(Mapper::toDTO).collect(Collectors.toList());
        return Response.ok(dtos).build();
    }

    @GET
    @Path("/with-news")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoriesWithNews() {
        List<CategoryDTO> dtos = facade.getCategoriesWithNews().stream().map(Mapper::toDTO).collect(Collectors.toList());
        return Response.ok(dtos).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCategory(CreateCategoryRequest req) {
        Set<ConstraintViolation<CreateCategoryRequest>> violations = validator.validate(req);
        if (!violations.isEmpty()) {
            String err = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; "));
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"" + err + "\"}").build();
        }
        Category created = facade.addCategory(req.getName());
        return Response.status(Response.Status.CREATED).entity(Mapper.toDTO(created)).build();
    }
}
