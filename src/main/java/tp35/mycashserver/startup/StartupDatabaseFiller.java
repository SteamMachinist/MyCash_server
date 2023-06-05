package tp35.mycashserver.startup;

import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import tp35.mycashserver.model.BaseCategory;
import tp35.mycashserver.model.CategoryType;
import tp35.mycashserver.service.BaseCategoryService;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StartupDatabaseFiller implements ApplicationListener<ContextRefreshedEvent> {
    private final BaseCategoryService baseCategoryService;

    @Override public void onApplicationEvent(ContextRefreshedEvent event) {
        if (baseCategoryService.getAllBaseCategories().size() == 0) {
            List<BaseCategory> baseCategories = ReadBaseCategoriesFromFile();
            baseCategoryService.addBaseCategories(baseCategories);
        }
    }

    private List<BaseCategory> ReadBaseCategoriesFromFile() {
        List<BaseCategory> baseCategories = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/basecategories.csv"))) {
            List<String[]> r = reader.readAll();
            r.forEach(x -> baseCategories.add(new BaseCategory(null, x[1], CategoryType.valueOf(x[2]), Integer.parseInt(x[0]))));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return baseCategories;
    }
}
