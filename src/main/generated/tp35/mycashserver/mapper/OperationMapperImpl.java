package tp35.mycashserver.mapper;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tp35.mycashserver.dto.OperationDTO;
import tp35.mycashserver.entity.OperationEntity;
import tp35.mycashserver.model.Account;
import tp35.mycashserver.model.Operation;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-05T20:56:37+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class OperationMapperImpl implements OperationMapper {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public OperationEntity toOperationEntity(Operation operation) {
        if ( operation == null ) {
            return null;
        }

        OperationEntity operationEntity = new OperationEntity();

        operationEntity.setId( operation.getId() );
        operationEntity.setAccount( accountMapper.toAccountEntity( operation.getAccount() ) );
        operationEntity.setCategory( categoryMapper.toCategoryEntity( operation.getCategory() ) );
        operationEntity.setValue( operation.getValue() );
        operationEntity.setDateTime( operation.getDateTime() );
        operationEntity.setComment( operation.getComment() );

        return operationEntity;
    }

    @Override
    public Operation toOperation(OperationEntity operationEntity) {
        if ( operationEntity == null ) {
            return null;
        }

        Operation operation = new Operation();

        operation.setId( operationEntity.getId() );
        operation.setAccount( accountMapper.toAccount( operationEntity.getAccount() ) );
        operation.setCategory( categoryMapper.toCategory( operationEntity.getCategory() ) );
        operation.setValue( operationEntity.getValue() );
        operation.setDateTime( operationEntity.getDateTime() );
        operation.setComment( operationEntity.getComment() );

        return operation;
    }

    @Override
    public OperationDTO toOperationDTO(Operation operation) {
        if ( operation == null ) {
            return null;
        }

        OperationDTO operationDTO = new OperationDTO();

        operationDTO.setAccountName( operationAccountName( operation ) );
        operationDTO.setId( operation.getId() );
        operationDTO.setCategory( categoryMapper.toCategoryDTO( operation.getCategory() ) );
        operationDTO.setValue( operation.getValue() );
        if ( operation.getDateTime() != null ) {
            operationDTO.setDateTime( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( operation.getDateTime() ) );
        }
        operationDTO.setComment( operation.getComment() );

        return operationDTO;
    }

    @Override
    public List<OperationEntity> toOperationEntities(List<Operation> operations) {
        if ( operations == null ) {
            return null;
        }

        List<OperationEntity> list = new ArrayList<OperationEntity>( operations.size() );
        for ( Operation operation : operations ) {
            list.add( toOperationEntity( operation ) );
        }

        return list;
    }

    @Override
    public List<Operation> toOperations(List<OperationEntity> operationEntities) {
        if ( operationEntities == null ) {
            return null;
        }

        List<Operation> list = new ArrayList<Operation>( operationEntities.size() );
        for ( OperationEntity operationEntity : operationEntities ) {
            list.add( toOperation( operationEntity ) );
        }

        return list;
    }

    @Override
    public List<OperationDTO> toOperationDTOs(List<Operation> operations) {
        if ( operations == null ) {
            return null;
        }

        List<OperationDTO> list = new ArrayList<OperationDTO>( operations.size() );
        for ( Operation operation : operations ) {
            list.add( toOperationDTO( operation ) );
        }

        return list;
    }

    private String operationAccountName(Operation operation) {
        if ( operation == null ) {
            return null;
        }
        Account account = operation.getAccount();
        if ( account == null ) {
            return null;
        }
        String name = account.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
