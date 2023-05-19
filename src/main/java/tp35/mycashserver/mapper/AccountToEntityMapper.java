package tp35.mycashserver.mapper;

import org.mapstruct.Mapper;
import tp35.mycashserver.entity.AccountEntity;
import tp35.mycashserver.model.Account;

@Mapper(componentModel = "spring")
public interface AccountToEntityMapper {
    AccountEntity toAccountEntity(Account account);
    Account toAccount(AccountEntity accountEntity);
}
