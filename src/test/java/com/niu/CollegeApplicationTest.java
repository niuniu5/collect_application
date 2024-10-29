package com.niu;
import com.niu.entity.CollegeEntity;
import com.niu.service.CollegeService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.niu.model.College;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
@DisplayName("Cal_Test")
public class CollegeApplicationTest {

    @Resource
    CollegeService collegeService;

//    @Resource
//    ExcelImporter excelImporter;

    @Test
    public void test(){
//        System.out.println(this); //生成数据表
//        模糊查询
//        List<College> college =  collegeService.getCollegeByNameLike("北京");
//        System.out.println(college.get(0).getAttribution());
         // 读取Excel文件,导入省市数据到cal_district表
//        excelImporter.importExcel("district.xlsx");
//        excelImporter.importExcel("2020.xlsx");
//        excelImporter.importExcel("2021-1.xlsx");
    }


    /**
     * 根据 cityid 查询所在省, 更新到college 表中
     */
    @Test
    public void test3(){
        List<College> list = collegeService.getAllColleges();
        for (College college : list) {
            Integer  id = college.getId();
            Integer cityid = college.getCityId();
            Integer provinceid = collegeService.getProvinceIdByCityId(cityid);
            if(provinceid  !=  null){
                collegeService.updateCollegeProvinceId(provinceid,id);
                System.out.println(college.getName());
            }
        }
    }

    @Test
    public void test4(){
        Pageable pageable = PageRequest.of(1, 10);
        Page<CollegeEntity> pc = collegeService.getCollegeByCondition(null, null, null, null, pageable);
        System.out.println(pc.getContent().get(0).getId());
    }
}
