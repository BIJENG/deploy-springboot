package com.example.myboard.service;

import com.example.myboard.dao.WineDAO;
import com.example.myboard.vo.WineVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WineServiceImpl implements WineService {

    @Autowired
    private WineDAO wineDAO;

    @Override
    public List<WineVO> get8Wines() {
        return wineDAO.get8Wines();
    }

    @Override
    public List<WineVO> get8Wines(String tagGptWine) {
        return wineDAO.get8Wines(tagGptWine);
    }

    @Override
    public List<WineVO> getWinesByCheckboxes(boolean cbtest1, boolean cbtest2, boolean cbtest3, boolean cbtest4) {
        return wineDAO.getWinesByCheckboxes(cbtest1, cbtest2, cbtest3, cbtest4);
    }

    @Override
    public WineVO getWineByNo(int wineNo) {
        return wineDAO.getWineByNo(wineNo);
    }

    // @Override
    // public List<WineVO> getTopFamousWines() {
    //     return wineDAO.get8Wines();
    // }

    // @Override
    // public List<WineVO> getTopPriceWines() {
    //     return wineDAO.get8Wines();
    // }

    // @Override
    // public List<WineVO> getTopRatingWines() {
    //     return wineDAO.get8Wines();
    // }

    // @Override
    // public List<WineVO> getTopSellWines() {
    //     return wineDAO.get8Wines();
    // }

    public List<WineVO> getWinesBySelection(String selection) {
        return wineDAO.getWinesBySelection(selection);
    }
}
