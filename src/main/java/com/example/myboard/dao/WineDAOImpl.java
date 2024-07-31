package com.example.myboard.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.myboard.vo.WineVO;

@Repository
public class WineDAOImpl implements WineDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<WineVO> get8Wines() {
        String query = "SELECT * FROM WINE_TBL WHERE NO IN (1, 2, 3, 4, 5, 6, 7, 8)";
        return jdbcTemplate.query(query, new WineRowMapper());
    }

    @Override
    public List<WineVO> get8Wines(String tagGptWine){

        String query = "SELECT * FROM WINE_TBL WHERE NO IN (" + tagGptWine +")";
        return jdbcTemplate.query(query, new WineRowMapper());
    
    }

    
    @Override
    public List<WineVO> getWinesByCheckboxes(boolean cbtest1, boolean cbtest2, boolean cbtest3, boolean cbtest4) {
        int combination = (cbtest1 ? 1 : 0) + (cbtest2 ? 2 : 0) + (cbtest3 ? 4 : 0) + (cbtest4 ? 8 : 0);
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM WINE_TBL WHERE NO IN (");
    
        // 16가지의 경우의 수에 따라 적절한 와인들을 가져올 수 있도록 쿼리를 생성
        switch (combination) {
            case 0: // 0000 (아무것도 선택되지 않은 경우)
                queryBuilder.append("1, 2, 3, 4, 5, 6, 7, 8");
                break;
            case 1: // 0001 (산도만 선택된 경우)
                queryBuilder.append("552, 553, 554, 555, 556, 559, 562, 563");
                break;
            case 2: // 0010 (타닌만 선택된 경우)
                queryBuilder.append("743, 744, 790, 791, 792, 794, 796, 800");
                break;
            case 3: // 0011 (산도와 타닌이 선택된 경우)
                queryBuilder.append("559, 562, 563, 564, 565, 567, 571, 574");
                break;
            case 4: // 0100 (바디만 선택된 경우)
                queryBuilder.append("394, 2269, 2924, 2931, 2932, 2934, 2935, 2937");
                break;
            case 5: // 0101 (산도와 바디가 선택된 경우)
                queryBuilder.append("40, 210, 354, 394, 475, 523, 603, 605");
                break;
            case 6: // 0110 (타닌과 바디가 선택된 경우)
                queryBuilder.append("354, 472, 525, 773, 1075, 2150, 2364, 2467");
                break;
            case 7: // 0111 (산도, 타닌, 바디가 선택된 경우)
                queryBuilder.append("354, 2021, 2144, 2507");
                break;
            case 8: // 1000 (당도만 선택된 경우)
                queryBuilder.append("736, 743, 744, 746, 749, 753, 755, 756");
                break;
            case 9: // 1001 (산도와 당도가 선택된 경우)
                queryBuilder.append("736, 743, 744, 746, 749, 753, 755, 756");
                break;
            case 10: // 1010 (타닌과 당도가 선택된 경우)
                queryBuilder.append("743, 744, 790, 968, 1032, 1104, 1138, 1146");
                break;
            case 11: // 1011 (산도, 타닌, 당도가 선택된 경우)
                queryBuilder.append("743, 744, 790, 968, 1032, 1104, 1146, 1158");
                break;
            case 12: // 1100 (바디와 당도가 선택된 경우)
                queryBuilder.append("736, 743, 744, 746, 749, 753, 755, 756, 394, 2269, 2924, 2931, 2932, 2934, 2935, 2937");
                break;
            case 13: // 1101 (산도, 바디, 당도가 선택된 경우)
                queryBuilder.append("1061, 1238, 1008, 1150");
                break;
            case 14: // 1110 (타닌, 바디, 당도가 선택된 경우)
                queryBuilder.append("1176, 191");
                break;
            case 15: // 1111 (모두 선택된 경우)
                queryBuilder.append("2220, 2488");
                break;
            default:
                queryBuilder.append("1, 2, 3, 4, 5, 6, 7, 8");
                break;
        }
        queryBuilder.append(")");
        
        return jdbcTemplate.query(queryBuilder.toString(), new WineRowMapper());
    }

    @Override
    public WineVO getWineByNo(int wineNo) {
        String query = "SELECT * FROM WINE_TBL WHERE NO = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{wineNo}, new WineRowMapper());
    }

    // @Override
    // public List<WineVO> getTopFamousWines() {
    //     String query = "SELECT * FROM WINE_TBL WHERE NO IN (1, 2, 3, 4, 5, 6, 7, 8)";
    //     return jdbcTemplate.query(query, new WineRowMapper());
    // }

    // @Override
    // public List<WineVO> getTopPriceWines() {
    //     String query = "SELECT * FROM WINE_TBL WHERE NO IN (101, 2, 3, 4, 5, 6, 7, 8)";
    //     return jdbcTemplate.query(query, new WineRowMapper());
    // }

    // @Override
    // public List<WineVO> getTopRatingWines() {
    //     String query = "SELECT * FROM WINE_TBL WHERE NO IN (202, 2, 3, 4, 5, 6, 7, 8)";
    //     return jdbcTemplate.query(query, new WineRowMapper());
    // }

    // @Override
    // public List<WineVO> getTopSellWines() {
    //     String query = "SELECT * FROM WINE_TBL WHERE NO IN (303, 2, 3, 4, 5, 6, 7, 8)";
    //     return jdbcTemplate.query(query, new WineRowMapper());
    // }
    
    @Override
    public List<WineVO> getWinesBySelection(String selection) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM WINE_TBL WHERE ");
    
        switch (selection) {
            case "famous":
                queryBuilder.append("NO IN (1, 2, 3, 4, 5, 6, 7, 8)");
                break;
            case "price":
                queryBuilder.append("NO IN (552, 553, 554, 555, 556, 559, 562, 563)");
                break;
            case "rating":
                queryBuilder.append("NO IN (743, 744, 790, 791, 792, 794, 796, 800)");
                break;
            case "sell":
                queryBuilder.append("NO IN (559, 562, 563, 564, 565, 567, 571, 574)");
                break;
            default:
                queryBuilder.append("NO IN (1, 2, 3, 4, 5, 6, 7, 8)");
                break;
        }
    
        return jdbcTemplate.query(queryBuilder.toString(), new WineRowMapper());
    }

}
