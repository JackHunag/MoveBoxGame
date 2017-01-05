package com.android.moveboxgame.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 据关卡号获取游戏关卡的开局的工具类
 * @author hsssf
 * 2016-11-29
 */
public class GameLevels {

    public static final int DEFAULT_ROW_NUM = 12;
    public static final int DEFAULT_COLUMN_NUM = 12;
    //游戏区单元格放了什么
    public static final char NOTHING = ' ';         //该单元格啥也没有
    public static final char BOX = 'B';             //该单元格放的是箱子
    public static final char FLAG = 'F';            //红旗，表示箱子的目的地
    public static final char MAN = 'M';              //搬运工
    public static final char WALL = 'W';             //墙
    public static final char MAN_FLAG = 'R';        //搬运工 + 红旗
    public static final char BOX_FLAG = 'X';        //箱子 + 红旗
    public static ArrayList<String[]> OriginalLevels = new ArrayList<String[]>();  //存储多个开局的列表
    //采用字符串数组来存储开局信息    LEVEL_1第一关，LEVEL_2第二关
    public static final String [] LEVEL_1 = {
        "WWWWWWWWWWWW",
        "W         FW",
        "W          W",
        "W          W",
        "W   WWWW   W",
        "W          W",
        "W    B     W",
        "W    M     W",
        "W          W",
        "W          W",
        "W          W",
        "WWWWWWWWWWWW"
};

    public static final String [] LEVEL_2 = {
        "            ",
        "            ",
        "  WWWWWWW   ",
        "  W FFB W   ",
        "  W W B W   ",
        "  W W W W   ",
        "  W BMW W   ",
        "  WFB   W   ",
        "  WFWWWWW   ",
        "  WWW       ",
        "            ",
        "            "
};

    public static final String [] LEVEL_3 = {
        "            ",
        "            ",
        "  WWWWWWW   ",
        "  W FFB W   ",
        "  W W B W   ",
        "  W W W W   ",
        "  W BM W   ",
        "  WFB   W   ",
        "  WFWWW   ",
        "        ",
        "            ",
        "            "
};
    
    public static final String [] LEVEL_4 = {
        "            ",
        "            ",
        "  WWWWWWW   ",
        "  W FFB W   ",
        "  W  B W   ",
        "  W  W   ",
        "  W BMW W   ",
        "  WFB   W   ",
        "  WFWWWWW   ",
        "  WWW       ",
        "            ",
        "            "
};
  //loadGameLevels()的作用是加载关卡列表
    public static void loadGameLevels(){
        if (OriginalLevels.isEmpty()) {
            OriginalLevels.add(LEVEL_1);    //把第1关开局添加到开局列表中
            OriginalLevels.add(LEVEL_2);    //把第2关开局添加到开局列表中
            OriginalLevels.add(LEVEL_3); 
            OriginalLevels.add(LEVEL_4); 
        }
    }
   
    //getLevel()是根据关卡号level得到该关卡的开局（用String[]实现的矩阵）
    public static String [] getLevel(int level){   //level参数是关卡号
        loadGameLevels();                 //加载关卡列表
        return OriginalLevels.get(level - 1);  //返回关卡号level对应的开局。level从1开始编号，列表下标从0开始。
    }
	
    public static  List<String> getLevelList() {
	   
		loadGameLevels();            //加载关卡列表
	    List<String> levelList = new ArrayList<String>();  //创建关卡名列表对象levelList，并分配存储空间
	    int levelNum = OriginalLevels.size();         //得到关卡的数目
	    for (int i = 1; i <= levelNum; i++){         //对每一关i (i=1, 2, …)，
	        levelList.add(new String("第" + i + "关"));  //把关卡名（如第1关，第2关）加入到关卡名列表
	    }

	    return levelList;    //返回关卡名列表
	}

}
