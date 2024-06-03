package ru.itis.healthserviceimpl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.itis.healthserviceimpl.util.UuidConvertor;
import ru.itis.healthserviceimpl.util.UuidToObjectIdConverter;

import java.util.List;
import java.util.UUID;

@Document(collection = "recipes")
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe {

    @Id
    private UUID id;

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
