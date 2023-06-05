package tp35.mycashserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tp35.mycashserver.service.AdminService;

@Controller
@Tag(name = "Admin", description = "Admin features")
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @Operation(summary = "Admin panel index")
    @GetMapping
    public String index() {
        return "admin_index";
    }

    @Operation(summary = "User statistics")
    @GetMapping("/users_stats")
    public String usersStats(Model model) {
        model.addAllAttributes(adminService.getUsersStats());
        return "users_stats";
    }

    @Operation(summary = "Category statistics")
    @GetMapping("/category_stats")
    public String categoryStats(Model model) {
        var cat = adminService.getCategoriesStats();
        model.addAttribute("months", cat.getFirst());
        model.addAttribute("categoriesAverages", cat.getSecond());
        return "category_stats";
    }
}
