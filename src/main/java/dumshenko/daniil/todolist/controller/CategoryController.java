package dumshenko.daniil.todolist.controller;

import dumshenko.daniil.todolist.controller.dto.CategoryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final Map<String, CategoryDto> categoriesMap = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories() {
        List<CategoryDto> categoryDtoList = new ArrayList<>(categoriesMap.values());

        return ResponseEntity.status(HttpStatus.OK).body(categoryDtoList);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDTO) {
        String id = UUID.randomUUID().toString();
        Instant now = Instant.now();

        categoryDTO.setId(id);
        categoryDTO.setName(categoryDTO.getName());
        categoryDTO.setDescription(categoryDTO.getDescription());

        if(categoryDTO.getUserId() != null) {
            categoryDTO.setUserId(null);
        }

        categoriesMap.put(id, categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDTO);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable String categoryId) {
        CategoryDto categoryDTO = categoriesMap.get(categoryId);
        if (categoryDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String categoryId) {
        if(categoriesMap.containsKey(categoryId)) {
            categoriesMap.remove(categoryId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable String categoryId, @RequestBody CategoryDto categoryDTO) {
        Instant now = Instant.now();

        CategoryDto currentCategory = categoriesMap.get(categoryId);

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
