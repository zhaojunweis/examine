package com.examine.common.util;


import com.examine.domain.TExam;
import com.examine.domain.TStudent;

import java.util.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @ProjectName: examine
 * @Package: com.examine.common.util
 * @ClassName: LimitPage
 * @Description: java类作用描述
 */
public  class LimitPage {

    /**
     * 用于分页
     * @param examId 考试ID
     * @param count 考试包含的学生数
     * @param pageSize 每页大小
     * @param nowPage 当前的页码
     * @param type 分页类型
     * @return
     */
    public static Map<String,Object> limitPage(Integer examId,Integer count,Integer pageSize,Integer nowPage,Integer type){

        int startNum = 0,pSize = 0;
        Map<String,Object> map = new HashMap<>();
        switch (type){
            case 0:
                int page = count/pageSize;
                int abovecount = count%pageSize;
                if(abovecount!=0){
                    startNum = page*pageSize-1;
                    if(startNum<=0){
                        startNum = 0;
                    }
                    pSize = abovecount;
                }else if (abovecount == 0){
                    startNum = (page-1)*pageSize-1;
                    if(startNum<=0){
                        startNum = 0;
                    }
                    pSize = pageSize;
                }
                break;
            case 1:
                if (count>pageSize){
                    startNum = 0;
                    pSize = pageSize;
                }else{
                    startNum = 0;
                    pSize = count;
                }
                break;
            default:
                int precount = pageSize*(nowPage-1),lastcount = pageSize*nowPage;
                if((precount<count) && (lastcount<=count)){
                    startNum = precount-1;
                    if(startNum<=0){
                        startNum = 0;
                    }
                    pSize = pageSize;
                }else if(precount<count && lastcount>count){
                    startNum = precount-1;
                    if(startNum<=0){
                        startNum = 0;
                    }
                    pSize = count-precount;
                }else if (precount>=count){
                    return null;
                }
                break;
        }
        map.put("examId",examId);
        map.put("startNum",startNum);
        map.put("pageSize",pSize);
        return map;
    }

    /**
     * 对获得的考试列表进行处理
     * @param tExams
     * @return
     */
    public static  List<Map>  TransforToMap(List<TExam> tExams){
        List<Map> listmap = new ArrayList<>();
        if (tExams != null) {
            for (TExam tExam : tExams) {
                Map<Object, Object> resultMap = new HashMap<>();
                resultMap.put("examname", tExam.getExamName());
                resultMap.put("exam_time", tExam.getExamStartTime());
                resultMap.put("create_name", tExam.gettName());
                resultMap.put("examId", tExam.getId());
                resultMap.put("isautostart", tExam.getIsAutoStart());
                resultMap.put("isexam", tExam.getIsStart());
                resultMap.put("isfinished", tExam.getIsFinished());
                resultMap.put("ispageonhole", tExam.getIsPigeonhole());
                resultMap.put("isdelete", tExam.getIsDelete());
                resultMap.put("exampaper_url", tExam.getExamPaperUrl());
                listmap.add(resultMap);
            }
        }

        return listmap;
    }

}
