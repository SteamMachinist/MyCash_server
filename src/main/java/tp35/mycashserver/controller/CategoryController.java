package tp35.mycashserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tp35.mycashserver.dto.CategoryDTO;
import tp35.mycashserver.mapper.CategoryMapper;
import tp35.mycashserver.model.User;
import tp35.mycashserver.service.AuthenticationService;
import tp35.mycashserver.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final AuthenticationService authenticationService;
    private final CategoryMapper categoryMapper;

    @GetMapping("/get_all")
    public List<CategoryDTO> getAllUserCategories() {
        User user = authenticationService.getAuthenticatedUser();
        return categoryMapper.toCategoryDTOs(categoryService.getAllUserCategories(user));
    }

    @PostMapping("/update")
    public void updateCategory(@RequestBody CategoryDTO categoryDTO) {
        User user = authenticationService.getAuthenticatedUser();
        categoryService.updateCategory(user, categoryDTO);
    }
}
