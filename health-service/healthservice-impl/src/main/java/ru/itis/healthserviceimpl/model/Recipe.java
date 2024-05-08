package ru.itis.healthserviceimpl.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "recipes")
@Data
@AllArgsConstructor
public class Recipe {

    @Id
    private ObjectId id;

    // TODO: тут должна быть сущность юзера
    private String author;

    private List<String> categories;

    private List<Ingredient> ingredients;

    private NutritionalInfo nutritionalInfo;

    private List<String> instructions;

    private List<String> images;

    private Integer cookingTime;
}
