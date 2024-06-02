package ru.itis.healthserviceimpl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "recipes")
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe {

    @Id
    private UUID id;

    // TODO: тут должна быть сущность юзера
    private UUID author;

    @Indexed
    private String title;

    private List<String> categories;

    private List<Ingredient> ingredients;

    private NutritionalInfo nutritionalInfo;

    private List<String> instructions;

    private List<String> images;

    private int cookingTime;

    public Recipe() {
        this.id = UUID.randomUUID();
    }
}
