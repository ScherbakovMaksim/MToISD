package ru.rsatu.coursework.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;


//Таблица "Сотрудники"
@Getter
@Setter
@Entity
@Table(name = "Users")

public class Users extends BaseMetaInfo {
    // ИД сотрудника
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "users_id_gen")
    @SequenceGenerator(name = "users_id_gen",
            sequenceName = "users_id_gen_seq",
            initialValue = 10,
            allocationSize = 10)
    @Column(name = "id")
    @Comment(value = "Идентификатор сотрудника")
    private Long id;

    //Сектор сотрудника
    @JoinColumn(name = "sector_id")
    @Comment(value = "Номер сектора")
    private Long sector_id;

    // Имя сотрудника
    @Column(name = "name")
    @Comment(value = "ФИО сотрудника")
    private String name;

    //Дополнительная информация о сотруднике
    @Column(name = "phone")
    @Comment(value = "Внутренний телефон сотрудника")
    private Long phone;
}
