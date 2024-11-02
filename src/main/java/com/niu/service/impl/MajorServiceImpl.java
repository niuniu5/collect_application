package com.niu.service.impl;

import com.niu.entity.MajorEntity;
import com.niu.entity.RecommendEntity;
import com.niu.model.Major;
import com.niu.model.Recommend;
import com.niu.model.ScoreRecord;
import com.niu.model.Student;
import com.niu.repository.MajorRepository;
import com.niu.repository.RecommendRepository;
import com.niu.service.CollegeService;
import com.niu.service.MajorService;
import com.niu.service.RecommendService;
import com.niu.service.StudentService;
import com.niu.vo.RecommendDTO;
import com.niu.vo.RecommendMajorVO;
import com.niu.vo.RecommendVO;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class MajorServiceImpl implements MajorService {



    @Resource
    private MajorRepository majorRepository;


    @Override
    public Major getMajorById(int id) {
        return convert2Major(majorRepository.getMajorById(id));
    }

    private  Major convert2Major(MajorEntity entity){
        return Major.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .characteristic(entity.getCharacteristic())
                .subjectGroup(entity.getSubjectGroup())
                .build();

    }
}
