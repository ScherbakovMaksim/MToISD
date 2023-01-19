package ru.rsatu.coursework.resource;

import ru.rsatu.coursework.pojo.dto.UsersSaveModel;
import ru.rsatu.coursework.pojo.dto.UsersViewModel;
import ru.rsatu.coursework.service.UsersServ;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/coursework/api/v1/Users")
public class UsersRes {
    @Inject
    UsersServ serv;

    //Получение списка view-моделей
    @GET
    @Path("/loadUsers")
    @RolesAllowed("readUsers")
    public List<UsersViewModel> load() {
        return serv.load();
    }

    //Добавить сотрудника
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createUsers")
    @RolesAllowed("editUsers")
    public void create(UsersSaveModel model) {
        serv.save(model);
    }

    //Обновление данных сотрудника
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateUsers")
    @RolesAllowed("editUsers")
    public void update(UsersSaveModel model) {
        serv.save(model);
    }
}
