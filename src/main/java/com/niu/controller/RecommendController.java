package com.niu.controller;

import com.niu.model.Recommend;
import com.niu.service.CollegeService;
import com.niu.service.RecommendService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecommendController {

    @Resource
    CollegeService collegeService;

    @Resource
    RecommendService recommendService;

    /**
     * 通过分数,省份,排位,科目 进行志愿推荐, 同时保存这些考生信息
     * **/
    @RequestMapping("/volunteer/recommend")
    public void recommend(@RequestParam Integer score,@RequestParam Integer rank,@RequestParam Integer provinceId,@RequestParam String subject) {
        //调用python 训练的模型
        System.out.println("score:"+score+" rank:"+rank+" provinceId:"+provinceId+" subject:"+subject);

        //推荐大学ID列表
        System.out.println("recommend collegeId list");
        int[] recommendCollegeIdList = {1,2,3,4,5,6,7,8,9,10};

    }

    /**志愿推荐并保存**/
    @PostMapping("/api/recommend/save")
    public void saveVolunteer(@RequestParam Integer collegeId, @RequestParam Integer majorId,@RequestParam Long userId,@RequestParam Integer score) {
        recommendService.saveRecommend(userId, collegeId, majorId, score);
    }

    /**志愿填报列表**/
    @RequestMapping("/api/volunteer/list/{userId}")
    public List<Recommend> getVolunt0eersByUserId(@PathVariable Long userId) {
        return recommendService.findByStudentId(userId);
    }

    /**志愿排名更新**/
    @RequestMapping("/api/volunteer/updateRank")
    public void updateVolunteer(@RequestParam Long id, @RequestParam Integer rank) {
        recommendService.updateRank(rank, id);
    }

    /**志愿删除**/
    @RequestMapping("/api/volunteer/delete")
    public void deleteVolunteer(@RequestParam Long id) {
        recommendService.deleteRecommend(id);
    }



   /**
    @PutMapping("/api/volunteer/updateOrder")
    public void updateVolunteerOrder(@RequestParam Long volunteerId, @RequestParam Integer newOrder) {
        volunteerService.updateVolunteerOrder(volunteerId, newOrder);
    }

    @RequestMapping("/api/volunteer/delete")
    public void deleteVolunteer(@RequestParam Long volunteerId) {
        volunteerService.deleteVolunteer(volunteerId);
    }

    @PostMapping("/api/volunteer/save")
    public Volunteer saveVolunteer(@RequestBody Volunteer volunteer) {
        return volunteerService.saveVolunteer(volunteer);
    }

    @GetMapping("/api/volunteer/list/{userId}")
    public List<Volunteer> getVolunteersByUserId(@PathVariable Long userId) {
        return volunteerService.getVolunteersByUserId(userId);
    }

    @DeleteMapping("/api/volunteer/delete/{id}")
    public void deleteVolunteer(@PathVariable Long id) {
        volunteerService.deleteVolunteer(id);
    }

    @PostMapping("/api/volunteer/update")
    public void updateVolunteerOrder(@RequestParam Long userId, @RequestBody List<Volunteer> volunteers) {
        volunteerService.updateVolunteerOrder(userId, volunteers);
    }
    **/
}
