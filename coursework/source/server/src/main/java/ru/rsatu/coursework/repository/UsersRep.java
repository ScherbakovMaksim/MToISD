package ru.rsatu.coursework.repository;

import ru.rsatu.coursework.pojo.entity.Users;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class UsersRep {
    @Inject
    EntityManager entityManager;

    //Получить список сотрудников
    public List<Users> load() {
        return entityManager.createQuery("from Users tbl order by tbl.number ASC", Users.class)
                .getResultList();
    }

    //Сохранение данных сотрудника в базу данных
    @Transactional
    public void save(Users db_model) {
        if (db_model.getId() != null) {
            entityManager.merge(db_model);
        } else {
            entityManager.persist(db_model);
        }
        entityManager.flush();
    }
}
