package tp35.mycashserver.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.dto.OperationDTO;
import tp35.mycashserver.mapper.OperationMapper;
import tp35.mycashserver.model.Operation;
import tp35.mycashserver.model.User;
import tp35.mycashserver.repository.OperationRepository;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;
    private final CategoryService categoryService;
    private final AccountService accountService;

    private Operation setUpOperationForUserFromDto(User user, Operation operation, OperationDTO operationDTO) {
        operation.setCategory(categoryService.getCategoryByUserAndName(user, operationDTO.getCategory().getName()));
        operation.setAccount(accountService.getAccountByOwnerAndName(user, operationDTO.getAccountName()));
        operation.setValue(operationDTO.getValue());
        operation.setDateTime(operationDTO.getDateTime());
        operation.setComment(operationDTO.getComment());
        return operation;
    }

    public Operation getOperationById(Long id) {
        return operationMapper.toOperation(operationRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public void addOperation(User user, OperationDTO operationDTO) {
        Operation operation = new Operation();
        addOperation(setUpOperationForUserFromDto(user, operation, operationDTO));
    }

    public void addOperation(Operation operation) {
        operationRepository.save(operationMapper.toOperationEntity(operation));
    }

    public void updateOperation(User user, OperationDTO operationDTO) {
        Operation operation = getOperationById(operationDTO.getId());
        addOperation(setUpOperationForUserFromDto(user, operation, operationDTO));
    }

    public void deleteOperation(Long id) {
        operationRepository.delete(operationRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
}
