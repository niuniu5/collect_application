package com.niu;

import com.niu.common.ExcelImporter;
import com.niu.common.RecommendUtil;
import com.niu.entity.CollegeEntity;
import com.niu.entity.RecommendEntity;
import com.niu.model.College;
import com.niu.service.CollegeService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
@DisplayName("Cal_Test")
public class ApiTest {


    @Resource
    RecommendUtil recommendUtil;

    @Test
    public void test(){
        List<Integer> recommend = recommendUtil.recommend(1L, 33, 44, 1, "物理，化学，生物");
        recommend.forEach(System.out::println);
    }


}
