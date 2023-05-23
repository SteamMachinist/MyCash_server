package tp35.mycashserver.mapper;

import org.mapstruct.Mapper;
import tp35.mycashserver.entity.OperationEntity;
import tp35.mycashserver.model.Operation;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OperationToEntityMapper {
    OperationEntity toOperationEntity(Operation operation);

    Operation toOperation(OperationEntity operationEntity);

    List<OperationEntity> toOperationEntityList(List<Operation> operationList);

    List<Operation> toOperationList(List<OperationEntity> operationEntityList);
}
