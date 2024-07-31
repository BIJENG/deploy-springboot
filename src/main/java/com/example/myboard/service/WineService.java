package com.example.myboard.service;

import com.example.myboard.vo.WineVO;
import java.util.List;

public interface WineService {
    List<WineVO> get8Wines();
    List<WineVO> get8Wines(String tagGptWine);
    List<WineVO> getWinesByCheckboxes(boolean cbtest1, boolean cbtest2, boolean cbtest3, boolean cbtest4);
    WineVO getWineByNo(int wineNo);
    // List<WineVO> getTopFamousWines();
    // List<WineVO> getTopPriceWines();
    // List<WineVO> getTopRatingWines();
    // List<WineVO> getTopSellWines();
    List<WineVO> getWinesBySelection(String selection);
}

