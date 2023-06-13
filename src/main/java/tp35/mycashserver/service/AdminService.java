package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.model.BaseCategory;
import tp35.mycashserver.model.Role;
import tp35.mycashserver.utils.Pair;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserService userService;
    private final BaseCategoryService baseCategoryService;
    private final OperationAverageGetterService operationAverageGetterService;

    public Map<String, Integer> getUsersStats() {
        Map<String, Integer> usersStats = new HashMap<>();
        Map<Role, Integer> usersNumber = userService.getUsersNumber();
        usersStats.put("totalUsers", usersNumber.get(Role.UNREGISTERED) + usersNumber.get(Role.REGISTERED) + usersNumber.get(Role.ADMIN));
        usersStats.put("unregisteredUsers", usersNumber.get(Role.UNREGISTERED));
        usersStats.put("registeredUsers", usersNumber.get(Role.REGISTERED));
        usersStats.put("adminUsers", usersNumber.get(Role.ADMIN));
        return usersStats;
    }

    public Pair<List<String>, Map<String, List<Double>>> getCategoriesStats() {
        List<LocalDate> months = new ArrayList<>(IntStream.range(0, 6)
                .mapToObj(value -> LocalDate.now().minusMonths(value)).toList());
        Collections.reverse(months);

        List<BaseCategory> baseCategories = baseCategoryService.getAllBaseCategories();

        Map<String, List<Double>> categoryStats = baseCategories.stream()
                .collect(Collectors
                        .toMap(
                                BaseCategory::getName,
                                baseCategory -> months.stream()
                                        .map(month -> operationAverageGetterService
                                                .getOperationsAverageBy(baseCategory, month.getYear(), month.getMonthValue()))
                                        .collect(Collectors.toList())
                        ));

        return new Pair<>(
                months.stream()
                        .map(localDate -> localDate.getMonth().toString())
                        .collect(Collectors.toList()),
                categoryStats);
    }
}
