package com.zulykdev.starter.util.mappers;

import com.zulykdev.starter.dto.request.PhoneRequest;
import com.zulykdev.starter.dto.request.UserRequest;
import com.zulykdev.starter.dto.response.PhoneResponse;
import com.zulykdev.starter.dto.response.UserResponse;
import com.zulykdev.starter.model.Phone;
import com.zulykdev.starter.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-29T05:43:58-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Amazon.com Inc.)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserRequest request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        user.setName( request.getName() );
        user.setEmail( request.getEmail() );
        user.setPassword( request.getPassword() );
        user.setPhones( phoneRequestListToPhoneList( request.getPhones() ) );

        return user;
    }

    @Override
    public Phone toPhone(PhoneRequest request) {
        if ( request == null ) {
            return null;
        }

        Phone phone = new Phone();

        phone.setCityCode( request.getCitycode() );
        phone.setCountryCode( request.getContrycode() );
        phone.setNumber( request.getNumber() );

        return phone;
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( uuidToString( user.getId() ) );
        userResponse.setCreated( formatDateTime( user.getCreated() ) );
        userResponse.setModified( formatDateTime( user.getModified() ) );
        userResponse.setLast_login( formatDateTime( user.getLastLogin() ) );
        userResponse.setIsactive( user.isActive() );
        userResponse.setToken( user.getToken() );

        return userResponse;
    }

    @Override
    public PhoneResponse toPhoneResponse(Phone phone) {
        if ( phone == null ) {
            return null;
        }

        PhoneResponse phoneResponse = new PhoneResponse();

        phoneResponse.setCitycode( phone.getCityCode() );
        phoneResponse.setContrycode( phone.getCountryCode() );
        phoneResponse.setNumber( phone.getNumber() );

        return phoneResponse;
    }

    protected List<Phone> phoneRequestListToPhoneList(List<PhoneRequest> list) {
        if ( list == null ) {
            return null;
        }

        List<Phone> list1 = new ArrayList<Phone>( list.size() );
        for ( PhoneRequest phoneRequest : list ) {
            list1.add( toPhone( phoneRequest ) );
        }

        return list1;
    }
}
