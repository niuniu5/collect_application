package com.niu.common;

import com.niu.entity.RecommendEntity;

import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class RecommendUtil {

//    @Autowired
//    private HttpClient httpClient;
//
//
//    private final static String addr = "http://localhost:5000";
    /**
     * 调用py 接口获取推荐结果
     * @param userId
     * @param score
     * @param rank
     * @param provinceId
     * @param subject
     * @return
     */
    public List<RecommendEntity> recommend(Long userId, Integer score, Integer rank, Integer provinceId, String subject) {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(addr))
//                .headers("Content-Type", "application/json;charset=utf-8")
//                .build();
//        try {
//            HttpResponse<String> response = httpClient.send(request,
//                    HttpResponse.BodyHandlers.ofString());
//
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
        return null;
    }

}
