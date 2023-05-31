package tp35.mycashserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tp35.mycashserver.dto.OperationDTO;
import tp35.mycashserver.entity.OperationEntity;
import tp35.mycashserver.model.Operation;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {AccountMapper.class, CategoryMapper.class})
public interface OperationMapper {
    OperationEntity toOperationEntity(Operation operation);

    Operation toOperation(OperationEntity operationEntity);

    @Mapping(source = "account.name", target = "accountName")
    OperationDTO toOperationDTO(Operation operation);


    List<OperationEntity> toOperationEntities(List<Operation> operations);

    List<Operation> toOperations(List<OperationEntity> operationEntities);

    List<OperationDTO> toOperationDTOs(List<Operation> operations);
}
