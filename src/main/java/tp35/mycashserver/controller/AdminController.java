package tp35.mycashserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tp35.mycashserver.service.AdminService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping
    public String index() {
        return "admin_index";
    }

    @GetMapping("/users_stats")
    public String usersStats(Model model) {
        model.addAllAttributes(adminService.getUsersStats());
        return "users_stats";
    }

    @GetMapping("/category_stats")
    public String categoryStats(Model model) {
        var cat = adminService.getCategoriesStats();
        model.addAttribute("months", cat.getFirst());
        model.addAttribute("categoriesAverages", cat.getSecond());
        return "category_stats";
    }
}
