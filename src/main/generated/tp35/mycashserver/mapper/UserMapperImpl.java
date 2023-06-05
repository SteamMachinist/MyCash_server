package tp35.mycashserver.mapper;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tp35.mycashserver.entity.UserEntity;
import tp35.mycashserver.model.Role;
import tp35.mycashserver.model.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-05T20:56:37+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toUserEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( user.getId() );
        userEntity.setUsername( user.getUsername() );
        userEntity.setPassword( user.getPassword() );
        Set<Role> set = user.getRoles();
        if ( set != null ) {
            userEntity.setRoles( new LinkedHashSet<Role>( set ) );
        }

        return userEntity;
    }

    @Override
    public User toUser(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        User user = new User();

        user.setId( userEntity.getId() );
        user.setUsername( userEntity.getUsername() );
        user.setPassword( userEntity.getPassword() );
        Set<Role> set = userEntity.getRoles();
        if ( set != null ) {
            user.setRoles( new LinkedHashSet<Role>( set ) );
        }

        return user;
    }
}
