package com.niu.common;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.niu.entity.RecommendEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class RecommendUtil {

    @Autowired
    private HttpClient httpClient;

    private final static String addr = "http://192.168.90.250:8064/api/prediction?score=%d&rank=%d&subject=%s";

    /**
     * 调用py 接口获取推荐结果
     * @param userId
     * @param score
     * @param rank
     * @param provinceId
     * @param subject
     * @return
     */
    public  List<Integer> recommend(Long userId, Integer score, Integer rank, Integer provinceId, String subject) {
        URI uri = URI.create(String.format(addr, score, rank, subject));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            JSONObject obj = JSONObject.parseObject(response.body());
            int status = obj.getInteger("status");
            if (status == 200) {
                JSONArray results = obj.getJSONArray("result");
                ArrayList<Integer> scoreRecordIDs = new ArrayList<>();
                for (int i = 0; i < results.size(); i++) {
                    JSONObject jsonObject = results.getJSONObject(i);
                    Integer university_id = jsonObject.getInteger("university_id");
                    scoreRecordIDs.add(university_id);
                }
                return scoreRecordIDs;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
