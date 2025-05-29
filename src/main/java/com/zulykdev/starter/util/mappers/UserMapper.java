package com.zulykdev.starter.util.mappers;

import com.zulykdev.starter.dto.request.PhoneRequest;
import com.zulykdev.starter.dto.request.UserRequest;
import com.zulykdev.starter.dto.response.ConstantsResponse;
import com.zulykdev.starter.dto.response.PhoneResponse;
import com.zulykdev.starter.dto.response.UserResponse;
import com.zulykdev.starter.model.Phone;
import com.zulykdev.starter.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "modified", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "active", ignore = true)
    User toUser(UserRequest request);

    @Mapping(source = "citycode", target = "cityCode")
    @Mapping(source = "contrycode", target = "countryCode")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Phone toPhone(PhoneRequest request);

    @Mapping(source = "id", target = "id", qualifiedByName = "uuidToString")
    @Mapping(source = "created", target = "created", qualifiedByName = "formatDateTime")
    @Mapping(source = "modified", target = "modified", qualifiedByName = "formatDateTime")
    @Mapping(source = "lastLogin", target = "last_login", qualifiedByName = "formatDateTime")
    @Mapping(source = "active", target = "isactive")
    UserResponse toUserResponse(User user);

    @Mapping(source = "cityCode", target = "citycode")
    @Mapping(source = "countryCode", target = "contrycode")
    PhoneResponse toPhoneResponse(Phone phone);

    @Named("formatDateTime")
    default String formatDateTime(LocalDateTime dateTime) {
        return dateTime != null
                ? dateTime.format(DateTimeFormatter.ofPattern(ConstantsResponse.DATETIME_FORMAT))
                : null;
    }

    @Named("uuidToString")
    default String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }
}
