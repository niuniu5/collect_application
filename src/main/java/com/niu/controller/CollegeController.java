package com.niu.controller;

import com.niu.entity.CollegeEntity;
import com.niu.entity.DistrictEntity;
import com.niu.entity.FillEntity;
import com.niu.entity.MajorEntity;
import com.niu.model.*;
import com.niu.service.CollegeService;
import com.niu.vo.CollegeCategory;
import com.niu.vo.CollegeNature;
import com.niu.vo.DistrictDTO;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class CollegeController {

    @Resource
    private CollegeService collegeService;

    /** 查询所有省份,显示所有省和直辖市,用于筛选学校**/
    @RequestMapping("/getAllProvince")
    public List<SimpleForm> getAllProvince(){
        List<DistrictEntity> districts = collegeService.getAllDistrict();
        List<SimpleForm> results = new ArrayList<SimpleForm>();
        for (DistrictEntity district : districts) {
            if (district.getParent() != null) continue;
            SimpleForm simpleForm = new SimpleForm(district.getId(), district.getName());
            results.add(simpleForm);
        }
        return results;
    }

    /**
     * 查询所有省份及所包含的市,用于学生筛选
     * @return
     */
    @RequestMapping("/getAllDistrict")
    public List<DistrictDto> getAllDistrict(){
        List<DistrictDto> districtForms  = new ArrayList<DistrictDto>();
        List<DistrictEntity> districts = collegeService.getAllDistrict();
        districtForms =convertToDtoList(districts);
        return districtForms;
    }


    /** 查询所有大学,选院校页面 **/

    @RequestMapping("/searchColleges")
    public Page<CollegeEntity> SearchColleges(@RequestParam(required = false) Integer provinceId,
                                                @RequestParam int page,
                                                @RequestParam(required = false) String collegeName,
                                                @RequestParam(required = false) CollegeCategory category,
                                                @RequestParam(required = false) CollegeNature nature){
        Pageable pageable = PageRequest.of(page, 10);
        Page<CollegeEntity> pc = collegeService.getCollegeByCondition(provinceId, collegeName, category, nature, pageable);
        return pc;
    }


    /** 查询所有大学,选院校页面 **/
    @CrossOrigin
    @RequestMapping("/getColleges")
    public List<College> getAllColleges(){
        return collegeService.getAllColleges();
    }

    /** 模糊查询大学**/
    @RequestMapping("/college/getCollegeByName/{name}")
    public List<College> getCollegeByNameLike(@PathVariable("name") String name) {
        return collegeService.getCollegeByNameLike(name);
    }

    /** 根据id查大学,查询详情**/
    @RequestMapping("/college/getCollegeById/{id}")
    public College getCollegeById(@PathVariable("id") Integer id) {
        return collegeService.getCollegeById(id);
    }

    /** 根据省份id查大学**/
    @RequestMapping("/college/getCollegeByProvinceId/{provinceId}")
    public Page<CollegeEntity> getCollegesByProvinceId(@PathVariable("provinceId") Integer provinceId, @RequestParam(value = "page", defaultValue = "1") int page,
                                                 @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return collegeService.getCollegesByProvinceId(provinceId, pageable);
    }

    /** 根据大学类别和省份查大学**/
    @RequestMapping("/college/getCollegesByProvinceIdAndCategory/{category}/{provinceId}")
    public Page<CollegeEntity>  getCollegesByProvinceIdAndCategory(@PathVariable("provinceId")Integer provinceId, @PathVariable("category")CollegeCategory category, @RequestParam(value = "page", defaultValue = "1") int page,
                                                                   @RequestParam(value = "size", defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return collegeService.getCollegesByProvinceIdAndCategory(provinceId, category, pageable);
    }

    /**
     * 根据大学类别、性质和省份查大学
     **/
    @RequestMapping("/college/getCollegesByProvinceIdAndCategoryAndNature/{category}/{provinceId}/{nature}")
    public Page<CollegeEntity> getCollegesByProvinceIdAndCategoryAndNature(@PathVariable("provinceId")Integer provinceId, @PathVariable("category") CollegeCategory category, @PathVariable("nature") CollegeNature nature, Pageable pageable) {
        return collegeService.getCollegesByProvinceIdAndCategoryAndNature(provinceId, category, nature, pageable);
    }

    /** 根据大学id查专业**/
    @RequestMapping("/college/getMajorByCollegeId/{id}")
    public Page<MajorEntity> getMajorByCollegeId(@PathVariable("id") Integer id, Pageable pageable) {
        return collegeService.getMajorByCollegeId(id,pageable);
    }

    /** 查询分数计划**/
    @RequestMapping("/college/getScoreRecordByCollegeId/{collegeId}")
    public List<ScoreRecord> getScoreRecordByCollegeId(@PathVariable("collegeId")Integer collegeId) {
        return collegeService.getScoreRecordByCollegeId(collegeId);
    }


    @PostMapping("/college/saveFill")
    public void saveFill(@RequestBody  FillEntity fillEntity) {
        collegeService.saveFill(fillEntity);
    }

    @PostMapping("/user/volunteers")
    public ResponseEntity<FillEntity> addUserVolunteer(@RequestBody FillEntity userVolunteer) {
        FillEntity addedUserVolunteer = collegeService.addUserVolunteer(userVolunteer);
        return new ResponseEntity<FillEntity>(addedUserVolunteer, HttpStatus.CREATED);
    }



    public List<DistrictDto> convertToDtoList(List<DistrictEntity> entities) {
        // 创建一个 map，用于快速查找父节点
        Map<Integer, DistrictDto> entityMap = new HashMap<Integer, DistrictDto>();
        for (DistrictEntity entity : entities) {
            DistrictDto dto = new DistrictDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setParent(entity.getParent());
            entityMap.put(entity.getId(), dto);
        }

        // 遍历所有实体，建立父子关系
        for (DistrictEntity entity : entities) {
            DistrictDto dto = entityMap.get(entity.getId());
            if (entity.getParent() != null) {
                DistrictDto parentDto = entityMap.get(entity.getParent());
                if (parentDto != null) {
//                    parentDto.setParent(entity.getParent());
                    if (parentDto.getChildren() == null) {
                        parentDto.setChildren(new ArrayList<>());
                    }
                    parentDto.getChildren().add(dto);
                }
            }
        }

        // 找到所有顶级节点
        List<DistrictDto> rootDtos = entityMap.values().stream()
                .filter(dto -> dto.getParent() == null)
                .collect(Collectors.toList());

        return rootDtos;
    }

}
