package tp35.mycashserver.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.dto.OperationDTO;
import tp35.mycashserver.mapper.OperationMapper;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.CategoryType;
import tp35.mycashserver.model.Operation;
import tp35.mycashserver.model.User;
import tp35.mycashserver.repository.OperationRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;
    private final CategoryService categoryService;
    private final AccountService accountService;

    public Operation setUpOperationForUserFromDto(User user, Operation operation, OperationDTO operationDTO) {
        return setUpOperationForUserFromDto(
                user,
                accountService.getAccountByOwnerAndName(user, operationDTO.getAccountName()),
                operation,
                operationDTO);
    }

    public Operation setUpOperationForUserFromDto(User user, Account account, Operation operation, OperationDTO operationDTO) {
        operation.setCategory(categoryService.getCategoryByUserAndName(user, operationDTO.getCategory().getName()));
        operation.setAccount(account);
        operation.setValue(operationDTO.getValue());
        operation.setDateTime(LocalDateTime.parse(operationDTO.getDateTime()));
        operation.setComment(operationDTO.getComment());
        return operation;
    }

    public Operation getOperationById(Long id) {
        return operationMapper.toOperation(operationRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public void addOperation(User user, OperationDTO operationDTO) {
        Account account = accountService.getAccountByOwnerAndName(user, operationDTO.getAccountName());

        CategoryType type = operationDTO.getCategory().getType();
        double signedValue = type == CategoryType.EXPENSE ? -operationDTO.getValue() : operationDTO.getValue();
        account.setBalance(account.getBalance() + signedValue);

        accountService.addAccount(account);

        Operation operation = new Operation();
        addOperation(setUpOperationForUserFromDto(user, operation, operationDTO));
    }

    public void addOperation(Operation operation) {
        operationRepository.save(operationMapper.toOperationEntity(operation));
    }

    public void updateOperation(User user, OperationDTO operationDTO) {
        Account account = accountService.getAccountByOwnerAndName(user, operationDTO.getAccountName());
        Operation operation = getOperationById(operationDTO.getId());

        CategoryType type = operation.getCategory().getBaseCategory().getType();
        double signedValue = type == CategoryType.EXPENSE ? -operation.getValue() : operation.getValue();
        account.setBalance(account.getBalance() - signedValue);

        type = operationDTO.getCategory().getType();
        signedValue = type == CategoryType.EXPENSE ? -operationDTO.getValue() : operationDTO.getValue();
        account.setBalance(account.getBalance() + signedValue);

        accountService.addAccount(account);

        addOperation(setUpOperationForUserFromDto(user, operation, operationDTO));
    }

    public void deleteOperation(Long id) {
        operationRepository.delete(operationRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
}
