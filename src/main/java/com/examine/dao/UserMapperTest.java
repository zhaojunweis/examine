package com.examine.dao;

import com.examine.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapperTest {
   List<User> selectAllUser();
}
