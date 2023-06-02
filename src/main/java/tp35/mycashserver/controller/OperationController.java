package tp35.mycashserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tp35.mycashserver.dto.OperationDTO;
import tp35.mycashserver.mapper.OperationMapper;
import tp35.mycashserver.model.User;
import tp35.mycashserver.service.AuthenticationService;
import tp35.mycashserver.service.OperationService;

@RestController
@RequestMapping("/api/operation")
@RequiredArgsConstructor
public class OperationController {
    private final AuthenticationService authenticationService;
    private final OperationService operationService;
    private final OperationMapper operationMapper;

    @GetMapping("/get/{id}")
    public OperationDTO getOperation(@PathVariable Long id) {
        return operationMapper.toOperationDTO(operationService.getOperationById(id));
    }

    @PostMapping("/add")
    public void addOperation(@RequestBody OperationDTO operationDTO) {
        User user = authenticationService.getAuthenticatedUser();
        operationService.addOperation(user, operationDTO);
    }

    @PostMapping("/update")
    public void updateOperation(@RequestBody OperationDTO operationDTO) {
        User user = authenticationService.getAuthenticatedUser();
        operationService.updateOperation(user, operationDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOperation(@PathVariable Long id) {
        operationService.deleteOperation(id);
    }
}
