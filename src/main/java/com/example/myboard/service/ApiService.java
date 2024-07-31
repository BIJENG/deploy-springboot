package com.example.myboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.myboard.dao.ApiDAO;
import com.example.myboard.vo.ApiVO;

@Service
public class ApiService {
    
    @Autowired
    private ApiDAO dao;
    
    public void fetchDataAndSaveToDatabase() {
        // 외부 API에서 데이터 가져오는 로직
        String apiUrl = "https://api.sampleapis.com/wines/reds";
        RestTemplate restTemplate = new RestTemplate();
        ApiVO[] apiData = restTemplate.getForObject(apiUrl, ApiVO[].class);
        
        // 가져온 데이터를 필요한 정보만을 가지고 있는 새로운 객체로 변환하여 데이터베이스에 저장
        for (ApiVO apiVO : apiData) {
            ApiVO myEntity = convertToMyEntity(apiVO);
            dao.saveApiVO(myEntity);
        }
    }

    private ApiVO convertToMyEntity(ApiVO apiVO) {
        ApiVO myEntity = new ApiVO();
        myEntity.setWine(apiVO.getWine());
        myEntity.setWinery(apiVO.getWinery());
        myEntity.setId(apiVO.getId());
        myEntity.setImage(apiVO.getImage());
        myEntity.setLocation(apiVO.getLocation());
        return myEntity;
    }

}