package tp35.mycashserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tp35.mycashserver.dto.OperationDTO;
import tp35.mycashserver.mapper.OperationMapper;
import tp35.mycashserver.model.User;
import tp35.mycashserver.response.OperationAddResponse;
import tp35.mycashserver.service.AuthenticationService;
import tp35.mycashserver.service.LimitCheckService;
import tp35.mycashserver.service.OperationService;

@RestController
@RequestMapping("/api/operation")
@RequiredArgsConstructor
public class OperationController {
    private final AuthenticationService authenticationService;
    private final LimitCheckService limitCheckService;
    private final OperationService operationService;
    private final OperationMapper operationMapper;

    @GetMapping("/get/{id}")
    public OperationDTO getOperation(@PathVariable Long id) {
        return operationMapper.toOperationDTO(operationService.getOperationById(id));
    }

    @PostMapping("/add")
    public OperationAddResponse addOperation(@RequestBody OperationDTO operationDTO) {
        User user = authenticationService.getAuthenticatedUser();
        operationService.addOperation(user, operationDTO);
        return limitCheckService.checkLimit(operationService.getOperationById(operationDTO.getId()));
    }

    @PostMapping("/update")
    public OperationAddResponse updateOperation(@RequestBody OperationDTO operationDTO) {
        User user = authenticationService.getAuthenticatedUser();
        operationService.updateOperation(user, operationDTO);
        return limitCheckService.checkLimit(operationService.getOperationById(operationDTO.getId()));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOperation(@PathVariable Long id) {
        operationService.deleteOperation(id);
    }
}
