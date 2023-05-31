package tp35.mycashserver.mapper;

import org.mapstruct.Mapper;
import tp35.mycashserver.dto.AccountDTO;
import tp35.mycashserver.entity.AccountEntity;
import tp35.mycashserver.model.Account;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {UserMapper.class})
public interface AccountMapper {
    AccountEntity toAccountEntity(Account account);

    Account toAccount(AccountEntity accountEntity);

    AccountDTO toAccountDTO(Account account);

    Account toAccount(AccountDTO accountDTO);


    List<AccountEntity> toAccountEntities(List<Account> account);

    List<Account> toAccounts(List<AccountEntity> accountEntities);

    List<AccountDTO> toAccountDTOs(List<Account> account);
}
