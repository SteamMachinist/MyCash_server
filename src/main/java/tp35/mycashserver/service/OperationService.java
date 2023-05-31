package tp35.mycashserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tp35.mycashserver.mapper.OperationMapper;
import tp35.mycashserver.model.Operation;
import tp35.mycashserver.repository.OperationRepository;

@Service
@RequiredArgsConstructor
public class OperationService {
    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;

    public void add(Operation operation) {
        operationRepository.save(operationMapper.toOperationEntity(operation));
    }
}
