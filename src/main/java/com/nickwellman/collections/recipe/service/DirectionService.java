package com.nickwellman.collections.recipe.service;

import com.nickwellman.collections.Nucleus;
import com.nickwellman.collections.recipe.config.RecipeConstants;
import com.nickwellman.collections.recipe.models.DirectionResponse;
import com.nickwellman.collections.repository.GSARepository;
import com.nickwellman.collections.repository.RepositoryItem;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

import static com.nickwellman.collections.recipe.config.DirectionConstants.PROP_DIRECTION;
import static com.nickwellman.collections.recipe.config.DirectionConstants.PROP_DIRECTION_ID;
import static com.nickwellman.collections.recipe.config.DirectionConstants.PROP_ORDINAL;
import static com.nickwellman.collections.recipe.config.DirectionConstants.PROP_RECIPE_ID;
import static com.nickwellman.collections.recipe.config.DirectionConstants.REPOSITORY_ITEM;
import static java.util.stream.Collectors.toList;

@Service
public class DirectionService {

    private final GSARepository repository;

    public DirectionService() {
        this.repository = (GSARepository) Nucleus.getInstance().getGenericService(RecipeConstants.RECIPE_REPOSITORY);
    }

    public List<DirectionResponse> getDirectionsForRecipe(int recipeId) throws SQLException {
        List<RepositoryItem> items = repository.getRepositoryItems(String.valueOf(recipeId), PROP_RECIPE_ID, REPOSITORY_ITEM);
        return items.stream().map(DirectionService::convertRepositoryItemToDirectionResponse).collect(toList());
    }

    private static DirectionResponse convertRepositoryItemToDirectionResponse(RepositoryItem item) {
        DirectionResponse response = new DirectionResponse();
        response.setDirectionId((int) item.getPropertyValue(PROP_DIRECTION_ID));
        response.setRecipeId((int) item.getPropertyValue(PROP_RECIPE_ID));
        response.setOrdinal((int) item.getPropertyValue(PROP_ORDINAL));
        response.setDirection((String) item.getPropertyValue(PROP_DIRECTION));
        return response;
    }
}
