package tp35.mycashserver.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tp35.mycashserver.dto.OperationDTO;
import tp35.mycashserver.mapper.OperationMapper;
import tp35.mycashserver.model.Operation;
import tp35.mycashserver.model.User;
import tp35.mycashserver.response.OperationAddResponse;
import tp35.mycashserver.service.AuthenticationService;
import tp35.mycashserver.service.LimitCheckService;
import tp35.mycashserver.service.OperationService;

@Tag(name = "Operation", description = "Interaction with user's operations")
@RestController
@RequestMapping("/api/operation")
@RequiredArgsConstructor
public class OperationController {
    private final AuthenticationService authenticationService;
    private final LimitCheckService limitCheckService;
    private final OperationService operationService;
    private final OperationMapper operationMapper;

    @io.swagger.v3.oas.annotations.Operation(summary = "Get operation by id")
    @GetMapping("/get/{id}")
    public OperationDTO getOperation(@PathVariable Long id) {
        return operationMapper.toOperationDTO(operationService.getOperationById(id));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Add new user's operation")
    @PostMapping("/add")
    public OperationAddResponse addOperation(@RequestBody OperationDTO operationDTO) {
        User user = authenticationService.getAuthenticatedUser();
        operationService.addOperation(user, operationDTO);
        return limitCheckService.checkLimit(operationService.setUpOperationForUserFromDto(user, new Operation(), operationDTO));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Update user's operation")
    @PostMapping("/update")
    public OperationAddResponse updateOperation(@RequestBody OperationDTO operationDTO) {
        User user = authenticationService.getAuthenticatedUser();
        operationService.updateOperation(user, operationDTO);
        return limitCheckService.checkLimit(operationService.getOperationById(operationDTO.getId()));
    }

    @io.swagger.v3.oas.annotations.Operation(summary = "Delete user's operation")
    @DeleteMapping("/delete/{id}")
    public void deleteOperation(@PathVariable Long id) {
        operationService.deleteOperation(id);
    }
}
