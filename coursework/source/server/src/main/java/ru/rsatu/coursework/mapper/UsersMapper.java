package ru.rsatu.coursework.mapper;

import io.quarkus.security.identity.SecurityIdentity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.rsatu.coursework.pojo.dto.*;
import ru.rsatu.coursework.pojo.entity.*;

import javax.inject.Inject;
import java.sql.Timestamp;

@Mapper(componentModel = "cdi")
public abstract class UsersMapper {
    @Inject
    SecurityIdentity securityIdentity;

    @Mapping(target = "id", source = "id")
    @Mapping(target = "sector_id", source = "sector_id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "phone", source = "phone")
    public abstract UsersViewModel toUsersViewModel(Users from);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "sector_id", source = "sector_id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "phone", source = "phone")
    public abstract Users toUsers(UsersSaveModel from);

    @AfterMapping
    protected void updateUsersAfterMapping(@MappingTarget Users db_model) {
        db_model.setRecordChangeAuthor(securityIdentity.getPrincipal().getName());
        db_model.setRecordChangeTS(new Timestamp(System.currentTimeMillis()));
    }
}
