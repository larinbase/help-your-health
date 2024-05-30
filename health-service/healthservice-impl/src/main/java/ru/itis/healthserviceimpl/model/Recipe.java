package ru.itis.healthserviceimpl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "recipes")
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe {

    @Id
    private ObjectId id;

    // TODO: тут должна быть сущность юзера
    private ObjectId author;

    @Indexed
    private String title;

    private List<String> categories;

    private List<Ingredient> ingredients;

    private NutritionalInfo nutritionalInfo;

    private List<String> instructions;

    private List<String> images;

    private int cookingTime;
}
