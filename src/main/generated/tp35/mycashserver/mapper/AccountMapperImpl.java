package tp35.mycashserver.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tp35.mycashserver.dto.AccountDTO;
import tp35.mycashserver.entity.AccountEntity;
import tp35.mycashserver.model.Account;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-05T20:56:37+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public AccountEntity toAccountEntity(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setId( account.getId() );
        accountEntity.setOwner( userMapper.toUserEntity( account.getOwner() ) );
        accountEntity.setName( account.getName() );
        accountEntity.setBalance( account.getBalance() );
        accountEntity.setTarget( account.getTarget() );
        accountEntity.setIsLimited( account.getIsLimited() );
        accountEntity.setSpendingLimit( account.getSpendingLimit() );

        return accountEntity;
    }

    @Override
    public Account toAccount(AccountEntity accountEntity) {
        if ( accountEntity == null ) {
            return null;
        }

        Account account = new Account();

        account.setId( accountEntity.getId() );
        account.setOwner( userMapper.toUser( accountEntity.getOwner() ) );
        account.setName( accountEntity.getName() );
        account.setBalance( accountEntity.getBalance() );
        account.setTarget( accountEntity.getTarget() );
        account.setIsLimited( accountEntity.getIsLimited() );
        account.setSpendingLimit( accountEntity.getSpendingLimit() );

        return account;
    }

    @Override
    public AccountDTO toAccountDTO(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setName( account.getName() );
        accountDTO.setBalance( account.getBalance() );
        accountDTO.setTarget( account.getTarget() );
        accountDTO.setIsLimited( account.getIsLimited() );
        accountDTO.setSpendingLimit( account.getSpendingLimit() );

        return accountDTO;
    }

    @Override
    public Account toAccount(AccountDTO accountDTO) {
        if ( accountDTO == null ) {
            return null;
        }

        Account account = new Account();

        account.setName( accountDTO.getName() );
        account.setBalance( accountDTO.getBalance() );
        account.setTarget( accountDTO.getTarget() );
        account.setIsLimited( accountDTO.getIsLimited() );
        account.setSpendingLimit( accountDTO.getSpendingLimit() );

        return account;
    }

    @Override
    public List<AccountEntity> toAccountEntities(List<Account> account) {
        if ( account == null ) {
            return null;
        }

        List<AccountEntity> list = new ArrayList<AccountEntity>( account.size() );
        for ( Account account1 : account ) {
            list.add( toAccountEntity( account1 ) );
        }

        return list;
    }

    @Override
    public List<Account> toAccounts(List<AccountEntity> accountEntities) {
        if ( accountEntities == null ) {
            return null;
        }

        List<Account> list = new ArrayList<Account>( accountEntities.size() );
        for ( AccountEntity accountEntity : accountEntities ) {
            list.add( toAccount( accountEntity ) );
        }

        return list;
    }

    @Override
    public List<AccountDTO> toAccountDTOs(List<Account> account) {
        if ( account == null ) {
            return null;
        }

        List<AccountDTO> list = new ArrayList<AccountDTO>( account.size() );
        for ( Account account1 : account ) {
            list.add( toAccountDTO( account1 ) );
        }

        return list;
    }
}
