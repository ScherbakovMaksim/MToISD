package ru.rsatu.coursework.pojo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Getter
@Setter
@MappedSuperclass
public class BaseMetaInfo {

    @Column(name = "record_change_ts")
    @Comment(value = "Время внесения изменений")
    private Timestamp recordChangeTS;
    @Column(name = "record_change_author")
    @Comment(value = "Последний пользователь, который внёс изменения в записи в БД")
    private String recordChangeAuthor;
}