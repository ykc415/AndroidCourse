package com.bignerdranch.android.remote_retrofitwithokhttp;

import java.util.List;

/**
 * Created by YKC on 2016. 10. 26..
 */

public class RemoteData {
    SeoulRoadNameInfo SeoulRoadNameInfo;
    public SeoulRoadNameInfo getSeoulRoadNameInfo() {
        return SeoulRoadNameInfo;
    }
    class SeoulRoadNameInfo{
        String list_total_count;
        Result RESULT;
        List<Row> row;
        public String getList_total_count() {
            return list_total_count;
        }
        public Result getRESULT() {
            return RESULT;
        }
        public List<Row> getRow() {
            return row;
        }
    }
    class Result{
        String CODE;
        String MESSAGE;

        public String getCODE() {
            return CODE;
        }

        public String getMESSAGE() {
            return MESSAGE;
        }
    }
    class Row{
        String ROAD_NM;
        String ROAD_TYPE;
        String ROAD_FUNC;
        String ROAD_SCALE;
        String ROAD_WIDTH;
        String CGG_DIV;

        public String getROAD_NM() {
            return ROAD_NM;
        }

        public String getROAD_TYPE() {
            return ROAD_TYPE;
        }

        public String getROAD_FUNC() {
            return ROAD_FUNC;
        }

        public String getROAD_SCALE() {
            return ROAD_SCALE;
        }

        public String getROAD_WIDTH() {
            return ROAD_WIDTH;
        }

        public String getCGG_DIV() {
            return CGG_DIV;
        }
    }
}
