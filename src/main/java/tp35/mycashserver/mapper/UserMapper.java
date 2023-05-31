package tp35.mycashserver.mapper;

import org.mapstruct.Mapper;
import tp35.mycashserver.entity.UserEntity;
import tp35.mycashserver.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toUserEntity(User user);

    User toUser(UserEntity userEntity);
}
