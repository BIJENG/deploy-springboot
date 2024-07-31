package com.example.myboard.vo;

import lombok.Data;

@Data
public class WineVO {
    
    public int no;
    public String name;
    public int acidity;    //산미
    public int balanced;   //균형
    public int body;       //본도
    public int bouquet;    //향기
    public int buttery;    //버터리
    public int citrus;     //시트러스
    public int dry;        //건조한
    public int earthy;     //흙내음
    public int easy;       //부드러운
    public int finish;     //후맛
    public int flat;       //단조로운
    public int fruity;     //과일향
    public int green;      //생도
    public int mouthfeel;  //입안느낌
    public int spicy;      //향신료
    public int structure;  //구조
    public int tannins;    //타닌
    public int tobacco;    //담배
    public int vanilla;    //바닐라
    public int proof;      //도수
    public int volume;     //볼륨
    public String producer;
    public String origin;
    public String description;
    public String url;
}
