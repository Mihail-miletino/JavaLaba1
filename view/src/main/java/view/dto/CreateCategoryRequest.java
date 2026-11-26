package view.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateCategoryRequest {
    @NotNull(message = "Поле name обязательно")
    @Size(min = 1, max = 50)
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
