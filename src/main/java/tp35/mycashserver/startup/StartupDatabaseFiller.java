package tp35.mycashserver.startup;

import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import tp35.mycashserver.model.BaseCategory;
import tp35.mycashserver.model.CategoryType;
import tp35.mycashserver.model.Role;
import tp35.mycashserver.model.User;
import tp35.mycashserver.service.BaseCategoryService;
import tp35.mycashserver.service.UserService;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StartupDatabaseFiller implements ApplicationListener<ContextRefreshedEvent> {
    private final BaseCategoryService baseCategoryService;
    private final UserService userService;
    private final ResourceLoader resourceLoader;

    @Override public void onApplicationEvent(ContextRefreshedEvent event) {
        if (baseCategoryService.getAllBaseCategories().size() == 0) {
            List<BaseCategory> baseCategories = ReadBaseCategoriesFromFile();
            baseCategoryService.addBaseCategories(baseCategories);
        }
        if (userService.getUserByUsername("admin") == null)
        {
            userService.addUser(new User(null, "admin", "admin", Role.ADMIN));
        }
    }

    private List<BaseCategory> ReadBaseCategoriesFromFile() {
        List<BaseCategory> baseCategories = new ArrayList<>();
        Resource resource = resourceLoader.getResource("classpath:basecategories.csv");
        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream()))) {
            List<String[]> r = reader.readAll();
            r.forEach(x -> baseCategories.add(new BaseCategory(null, x[1], CategoryType.valueOf(x[2]), Integer.parseInt(x[0]))));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return baseCategories;
    }
}
