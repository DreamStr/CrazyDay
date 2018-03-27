package com.lanfanxing.goodsapplication.mvp.bean;

import java.util.List;

/**
 * Created by lanfanxing on 2017/11/9.
 */

public class MenuBean {

    public List<Items> datas;

    public List<Items> getDatas() {
        return datas;
    }

    public void setDatas(List<Items> datas) {
        this.datas = datas;
    }

    public static class Items{
        private String title;
        private int resouse;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getResouse() {
            return resouse;
        }

        public void setResouse(int resouse) {
            this.resouse = resouse;
        }
    }
}
