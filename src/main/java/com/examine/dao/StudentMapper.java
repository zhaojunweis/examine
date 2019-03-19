package com.examine.dao;

import com.examine.domain.TTeacher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapperTest {
   List<TTeacher> selectAllUser();
}
