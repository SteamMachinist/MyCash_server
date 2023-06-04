package tp35.mycashserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tp35.mycashserver.dto.CategoryDTO;
import tp35.mycashserver.mapper.CategoryMapper;
import tp35.mycashserver.model.User;
import tp35.mycashserver.service.AuthenticationService;
import tp35.mycashserver.service.CategoryService;

import java.util.List;

@Tag(name = "Category", description = "Interaction with user categories")
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final AuthenticationService authenticationService;
    private final CategoryMapper categoryMapper;

    @Operation(summary = "Get all user's categories")
    @GetMapping("/get_all")
    public List<CategoryDTO> getAllUserCategories() {
        User user = authenticationService.getAuthenticatedUser();
        return categoryMapper.toCategoryDTOs(categoryService.getAllUserCategories(user));
    }

    @Operation(summary = "Update user's category")
    @PostMapping("/update")
    public void updateCategory(@RequestBody CategoryDTO categoryDTO) {
        User user = authenticationService.getAuthenticatedUser();
        categoryService.updateCategory(user, categoryDTO);
    }
}
