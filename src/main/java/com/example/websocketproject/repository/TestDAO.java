package com.example.websocketproject.repository;

import com.example.websocketproject.entity.TestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestDAO {
    List<TestDTO> getTestData();
}

