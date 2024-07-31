package com.example.myboard.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.myboard.vo.WineVO;

public class WineRowMapper implements RowMapper<WineVO> {
    @Override
    public WineVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        WineVO wine = new WineVO();
        
        wine.setNo(rs.getInt("NO"));
        wine.setName(rs.getString("NAME"));
        wine.setAcidity(rs.getInt("ACIDITY"));
        wine.setBalanced(rs.getInt("BALANCED"));
        wine.setBody(rs.getInt("BODY"));
        wine.setBouquet(rs.getInt("BOUQUET"));
        wine.setButtery(rs.getInt("BUTTERY"));
        wine.setCitrus(rs.getInt("CITRUS"));
        wine.setDry(rs.getInt("DRY"));
        wine.setEarthy(rs.getInt("EARTHY"));
        wine.setEasy(rs.getInt("EASY"));
        wine.setFinish(rs.getInt("FINISH"));
        wine.setFlat(rs.getInt("FLAT"));
        wine.setFruity(rs.getInt("FRUITY"));
        wine.setGreen(rs.getInt("GREEN"));
        wine.setMouthfeel(rs.getInt("MOUTHFEEL"));
        wine.setSpicy(rs.getInt("SPICY"));
        wine.setStructure(rs.getInt("STRUCTURE"));
        wine.setTannins(rs.getInt("TANNINS"));
        wine.setTobacco(rs.getInt("TOBACCO"));
        wine.setVanilla(rs.getInt("VANILLA"));
        wine.setProof(rs.getInt("PROOF"));
        wine.setVolume(rs.getInt("VOLUME"));
        wine.setProducer(rs.getString("PRODUCER"));
        wine.setOrigin(rs.getString("ORIGIN"));
        wine.setDescription(rs.getString("DESCRIPTION"));
        wine.setUrl(rs.getString("URL"));
        
        return wine;
    }
}
