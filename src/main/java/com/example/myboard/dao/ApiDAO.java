package com.example.myboard.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.myboard.vo.ApiVO;

@Repository
public class ApiDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveApiVO(ApiVO apiVO) {
        // 데이터베이스에서 가장 큰 ID 값을 가져옵니다.
        String selectMaxIdQuery = "SELECT MAX(NO) FROM WINE_TBL";
        Integer maxId = jdbcTemplate.queryForObject(selectMaxIdQuery, Integer.class);
        
        // 새로운 ID를 생성합니다.
        int newId = maxId != null ? maxId + 1 : 1;
        
        // 새로운 ID를 저장하려는 데이터에 설정합니다.
        apiVO.setId((long) newId);
        
        // 데이터베이스에 해당 NO가 존재하지 않으면 데이터 삽입
        String sql = "INSERT INTO WINE_REDS (NAME, PRODUCER, NO, URL, ORIGIN) " +
                     "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, 
                             apiVO.getWine(),     // name
                             apiVO.getWinery(),   // producer
                             apiVO.getId(),       // no
                             apiVO.getImage(),    // url
                             apiVO.getLocation() // origin
                            );
    }




}