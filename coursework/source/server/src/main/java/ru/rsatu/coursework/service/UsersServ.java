package ru.rsatu.coursework.service;

import ru.rsatu.coursework.mapper.UsersMapper;
import ru.rsatu.coursework.pojo.dto.UsersSaveModel;
import ru.rsatu.coursework.pojo.dto.UsersViewModel;
import ru.rsatu.coursework.repository.UsersRep;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsersServ {
    @Inject
    UsersMapper mapper;
    @Inject
    UsersRep rep;

    //Получение списка сотрудников
    public List<UsersViewModel> load() {
        return rep.load().stream()
                .map(mapper::toUsersViewModel)
                .collect(Collectors.toList());
    }

    //Сохранить данные сотрудника
    public void save(UsersSaveModel model) {
        rep.save(mapper.toUsers(model));
    }
}
