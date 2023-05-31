package tp35.mycashserver.mapper;

import org.mapstruct.Mapper;
import tp35.mycashserver.dto.AccountDTO;
import tp35.mycashserver.entity.AccountEntity;
import tp35.mycashserver.model.Account;

@Mapper(componentModel = "spring",
        uses = {UserMapper.class})
public interface AccountMapper {
    AccountEntity toAccountEntity(Account account);

    Account toAccount(AccountEntity accountEntity);

    AccountDTO toAccountDTO(Account account);
}
