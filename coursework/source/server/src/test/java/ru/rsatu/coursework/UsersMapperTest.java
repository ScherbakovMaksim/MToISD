package ru.rsatu.coursework;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import ru.rsatu.coursework.mapper.UsersMapper;

import javax.inject.Inject;

@QuarkusTest
public class UsersMapperTest {
    @Inject
    UsersMapper mapper;

    @Test
    public void testUsersViewModel() {
        //@todo Добавить тест маппера
    }

}