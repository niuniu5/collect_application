package com.niu.common;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.niu.entity.CollegeEntity;
import com.niu.entity.DistrictEntity;
import com.niu.entity.MajorEntity;
import com.niu.entity.ScoreRecordEntity;
import com.niu.repository.CollegeRepository;
import com.niu.repository.DistrictRepository;
import com.niu.repository.MajorRepository;
import com.niu.repository.ScoreRecordRepository;
import com.niu.vo.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelImporter {

    @Resource
    private CollegeRepository collegeRepository;

    @Resource
    private MajorRepository majorRepository;

    @Resource
    private ScoreRecordRepository scoreRecordRepository;

    @Resource
    private DistrictRepository districtRepository;

    public void importExcel(String filePath) {


//        System.out.println("开始读取Excel文件");
//        EasyExcel.read(filePath, DistrictDTO.class, new AnalysisEventListener<DistrictDTO>() {
//            @Override
//            public void invoke(DistrictDTO data, AnalysisContext context) {
//                saveToDistrict(data);
//            }
//            @Override
//            public void doAfterAllAnalysed(AnalysisContext context) {
//                System.out.println("所有数据解析完成");
//            }
//        }).sheet().doRead();

//        System.out.println("开始读取Excel文件");
//        EasyExcel.read(filePath, ScoreRecordDTO.class, new AnalysisEventListener<ScoreRecordDTO>() {
//            @Override
//            public void invoke(ScoreRecordDTO data, AnalysisContext context) {
//                saveToScoreRecord(data);
//            }
//            @Override
//            public void doAfterAllAnalysed(AnalysisContext context) {
//                System.out.println("所有数据解析完成");
//            }
//        }).sheet().doRead();

        System.out.println("开始读取Excel文件");
        EasyExcel.read(filePath, ScoreRecordDTONew2.class, new AnalysisEventListener<ScoreRecordDTONew2>() {
            @Override
            public void invoke(ScoreRecordDTONew2 data, AnalysisContext context) {
                saveToScoreRecord_new2(data);
            }
            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                System.out.println("所有数据解析完成");
            }
        }).sheet().doRead();


    }

    public void saveToDistrict(DistrictDTO data) {

        String provinceName = data.getProvinceName();
        String cityName = data.getCityName();
        String pattern = "%" + provinceName + "%";
        List<DistrictEntity> ents =  districtRepository.findByNameLike(pattern);

        DistrictEntity district = districtRepository.findByName(cityName);
        if(district == null) {
            if (data.getProvinceName() != null && ents.size() == 0) {
                district = new DistrictEntity();
                district.setName(data.getProvinceName());
                district.setParent(null); // 省的父级ID为null
                district = districtRepository.save(district);
            }
            // 保存市信息
            if (cityName != null && !cityName.isEmpty()) {
                DistrictEntity city = districtRepository.findByName(cityName);
                if (city == null) {
                    city = new DistrictEntity();
                    city.setName(cityName);
                    if (!ents.isEmpty()) {
                        city.setParent(ents.get(0).getId()); // 市的父级ID为省的ID
//                        city.setProvinceId(district.getId());
                    }
                    if(district != null){
                        city.setParent(district.getId());
                    }

                    districtRepository.save(city);
                }
            }

        }

    }


    public void saveToMajor(MajorDTO data){
        MajorEntity major =majorRepository.getMajorByName(data.getMajorName());
        if (major == null) {
            major = new MajorEntity();
            major.setName(data.getMajorName());
            major.setType(AcademicType.fromDescription(data.getCategory()));
            major.setSubject(data.getSubject());
//          major.setCode(data.getCode());
//          major.setCharacteristic(data.getCharacteristic());
            CollegeEntity college = collegeRepository.getCollegeByName(data.getCollegeName());
            major.setCollegeId(college.getId());
            majorRepository.save(major);
        }
    }

    public void saveToScoreRecord_new2(ScoreRecordDTONew2 data){
        ScoreRecordEntity scoreRecord = new ScoreRecordEntity();
        String collegeName = data.getCollegeName();
        if(collegeName.contains("(")){
            collegeName = collegeName.substring(0,collegeName.indexOf("("));
        }
        CollegeEntity college = collegeRepository.getCollegeByName(collegeName);
        if (college == null) {
            return;
        }
        int provinceId = college.getProvinceId() == null ? -1 : college.getProvinceId();
        if(provinceId == -1){
            provinceId =  college.getCityId();
        }

        scoreRecord.setCollegeId(college.getId());
        scoreRecord.setProvinceId(provinceId);
        if(majorRepository.getMajorByName(data.getMajorName()) == null) {
            MajorEntity major = new MajorEntity();
            major.setName(data.getMajorName());
            major.setCollegeId(college.getId());
            //2021年 科目
            major.setSubjectGroup(data.getSubject());
            major.setCode(data.getMajorCode());
            if (data.getCollegeName().contains("(国家专项)")) {
                major.setCharacteristic(true);
            }
            //2021 之前
//            major.setType(AcademicType.fromDescription(data.getCategory()));
//            major.setSubject(data.getSubject());
            major.setSubjectGroup(data.getSubject());
            majorRepository.save(major);
        }

        scoreRecord.setMajorId(majorRepository.getMajorByName(data.getMajorName()).getId());
        scoreRecord.setYear(data.getYear());
        scoreRecord.setBatchNumber(data.getBatchNumber());

        int lowScore =  convertStringToInt(data.getLowScore());
        int lowRank = convertStringToInt(data.getLowRank());
        int avgScore = convertStringToInt(data.getAvgScore());
        int highScore = convertStringToInt(data.getHighScore());
        scoreRecord.setLowScores(lowScore);
        scoreRecord.setLowRank(lowRank);
        scoreRecord.setAvgScores(avgScore);
        scoreRecord.setHighScores(highScore);
        scoreRecord.setNumberof(convertStringToInt(data.getCount()));
//        scoreRecord.setType(EnrollmentType.fromDescription(data.getType()));
        scoreRecordRepository.save(scoreRecord);
    }

    public void saveToScoreRecord(ScoreRecordDTO data){
        ScoreRecordEntity scoreRecord = new ScoreRecordEntity();
        String provinceName = data.getProvinceName();
        String collegeName = data.getCollegeName();
        String cityName = data.getCityName();
        CollegeEntity college = collegeRepository.getCollegeByName(collegeName);
        String pattern = "%" + provinceName + "%";
        List<DistrictEntity> entities = districtRepository.findByNameLike(pattern);
        DistrictEntity districtEntity = null;
        if(!entities.isEmpty()){
            districtEntity = entities.get(0);
        }
//        DistrictEntity districtEntity = districtRepository.findByNameLike(pattern).get(0);
        if(college == null){
            college =  new CollegeEntity();
            college.setName(data.getCollegeName());
            DistrictEntity district = districtRepository.findByName(cityName);
            if(district == null){
                if(data.getProvinceName() != null && entities.isEmpty()){
                    district = new DistrictEntity();
                    district.setName(data.getProvinceName());
                    district.setParent(null); // 省的父级ID为null
                    district = districtRepository.save(district);
                }
                // 保存市信息
                if (cityName != null && !cityName.isEmpty()) {
                    DistrictEntity city = districtRepository.findByName(cityName);
                    if (city == null) {
                        city = new DistrictEntity();
                        city.setName(cityName);
                        city.setParent(district.getId()); // 市的父级ID为省的ID
                        districtRepository.save(city);
                    }
                }
            }
            Integer provinceId = district.getParent();

            Integer cityId = district.getId();

            college.setProvinceId(provinceId);
            college.setCityId(cityId);
            college.setIs211(data.getIs211().equals("是")?true:false);
            college.setIs985(data.getIs985().equals("是")?true:false);
            college.setSorted(data.getSort());
            String nature = data.getNatureName();
            college.setNature(CollegeNature.fromDescription(nature));
            college.setEnrollmentCode(data.getCode());
            college.setFormerName(data.getOldName());
            college.setIsDoubleFirstClass(data.getIsdouble().equals("是")?true:false);
            String category = data.getType();
            college.setCategory(CollegeCategory.fromDescription(category));
            college.setAttribution(data.getCollegeBelong());
            collegeRepository.save(college);
        }
        scoreRecord.setCollegeId(college.getId());
        scoreRecord.setProvinceId(college.getProvinceId()==null?college.getCityId():college.getProvinceId());
        if(majorRepository.getMajorByName(data.getMajorName()) == null){
            MajorEntity major = new MajorEntity();
            major.setName(data.getMajorName());
            major.setType(AcademicType.fromDescription(data.getCategory()));
            major.setSubject(data.getSubject());
            majorRepository.save(major);
        }

        scoreRecord.setMajorId(majorRepository.getMajorByName(data.getMajorName()).getId());
        scoreRecord.setYear(data.getYear());
        scoreRecord.setBatchNumber(data.getBatchNumber());

        int lowScore =  convertStringToInt(data.getLowScore());
        int lowRank = convertStringToInt(data.getLowRank());
        int avgScore = convertStringToInt(data.getAvgScore());
        int highScore = convertStringToInt(data.getHighScore());
        scoreRecord.setLowScores(lowScore);
        scoreRecord.setLowRank(lowRank);
        scoreRecord.setAvgScores(avgScore);
        scoreRecord.setHighScores(highScore);

        scoreRecord.setType(EnrollmentType.fromDescription(data.getType()));
        scoreRecordRepository.save(scoreRecord);
    }


    public void saveToCollege(CollegeDTO data){
        CollegeEntity college = collegeRepository.getCollegeByName(data.getCollegeName());
        if (college == null) {
            college =  new CollegeEntity();
            college.setName(data.getCollegeName());
            String cityname = data.getCityName();
            DistrictEntity district = districtRepository.findByName(cityname);
            Integer provinceId = district.getParent();
            Integer cityId = district.getId();

            college.setProvinceId(provinceId);
            college.setCityId(cityId);
            college.setIs211(data.getIs211().equals("是")?true:false);
            college.setIs985(data.getIs985().equals("是")?true:false);
            college.setSorted(data.getSort());
            String nature = data.getNatureName();
            college.setNature(CollegeNature.fromDescription(nature));
            college.setEnrollmentCode(data.getCode());
            college.setFormerName(data.getOldName());
            college.setIsDoubleFirstClass(data.getIsdouble().equals("是")?true:false);
            String category = data.getType();
            college.setCategory(CollegeCategory.fromDescription(category));
            college.setAttribution(data.getCollegeBelong());
            collegeRepository.save(college);
        }
    }


    public void saveToDatabase(DistrictDTO data){
        //先存省市表 cal_district
        DistrictEntity province = districtRepository.findByName(data.getProvinceName());
        if (province == null) {
            if(data.getProvinceName() != null || data.getCityName()!=null){
                province = new DistrictEntity();
                province.setName(data.getProvinceName());
                province.setParent(null); // 省的父级ID为null
                province = districtRepository.save(province);
            }

        }
        // 保存市信息
        if (data.getCityName() != null && !data.getCityName().isEmpty()) {
            DistrictEntity city = districtRepository.findByName(data.getCityName());
            if (city == null) {
                city = new DistrictEntity();
                city.setName(data.getCityName());
                city.setParent(province.getId()); // 市的父级ID为省的ID
                districtRepository.save(city);
            }
        }
    }

    public static int convertStringToInt(String str) {
        // 判断字符串是否为数字
        if (isNumeric(str)) {
            // 转换为整数
            double doubleValue = Double.parseDouble(str);
            return (int) Math.round(doubleValue);
        } else {
            // 不是数字，返回 -1
            return -1;
        }
    }

    public static boolean isNumeric(String str) {
        try {
            // 尝试将字符串转换为 double
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
