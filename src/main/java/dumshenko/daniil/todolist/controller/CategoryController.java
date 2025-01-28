package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.CategoryDTO;
import dumshenko.daniil.todolist.controller.dto.CreateCategoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final Map<String, CategoryDTO> categoriesMap = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        List<CategoryDTO> categoryDTOList = new ArrayList<>(categoriesMap.values());

        return ResponseEntity.status(HttpStatus.OK).body(categoryDTOList);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CreateCategoryDTO createcategoryDTO) {
        String id = UUID.randomUUID().toString();
        Instant now = Instant.now();

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(id);
        categoryDTO.setName(createcategoryDTO.getName());
        categoryDTO.setDescription(createcategoryDTO.getDescription());

        categoriesMap.put(id, categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDTO);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable String categoryId) {
        CategoryDTO categoryDTO = categoriesMap.get(categoryId);
        if (categoryDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable String categoryId) {
        if(categoriesMap.containsKey(categoryId)) {
            categoriesMap.remove(categoryId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable String categoryId, @RequestBody CategoryDTO categoryDTO) {
        Instant now = Instant.now();

        CategoryDTO currentCategory = categoriesMap.get(categoryId);

        if(currentCategory == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if(!currentCategory.getName().equals(categoryDTO.getName())) {
            currentCategory.setName(categoryDTO.getName());
        }
        if(!currentCategory.getDescription().equals(categoryDTO.getDescription())) {
            currentCategory.setDescription(categoryDTO.getDescription());
        }
        categoriesMap.put(categoryId, currentCategory);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
