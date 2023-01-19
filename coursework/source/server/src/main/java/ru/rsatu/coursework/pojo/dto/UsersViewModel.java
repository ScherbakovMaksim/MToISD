package ru.rsatu.coursework.pojo.dto;

import lombok.Getter;
import lombok.Setter;

//Dto-класс для просмотра записей
@Getter
@Setter
public class UsersViewModel {
    //ИД
    private Long id;
    //Номер сектора сотрудника
    private Long sector_id;
    //Имя сотрудника
    private String name;
    //Дополнительная информация о сотруднике
    private Long phone;
}
