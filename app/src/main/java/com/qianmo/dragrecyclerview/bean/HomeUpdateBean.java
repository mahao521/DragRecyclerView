package com.qianmo.dragrecyclerview.bean;

import java.util.List;

public class HomeUpdateBean {

    private String categroy_title;
    private int categroy_image;
    private List<CardBean> cardList;

    public class CardBean {
        private String card_name;
        private boolean isopen;

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }

        public boolean isIsopen() {
            return isopen;
        }

        public void setIsopen(boolean isopen) {
            this.isopen = isopen;
        }

        @Override public String toString() {
            return "CardBean{" +
                "card_name='" + card_name + '\'' +
                ", isopen=" + isopen +
                '}';
        }
    }

    public String getCategroy_title() {
        return categroy_title;
    }

    public void setCategroy_title(String categroy_title) {
        this.categroy_title = categroy_title;
    }

    public int getCategroy_image() {
        return categroy_image;
    }

    public void setCategroy_image(int categroy_image) {
        this.categroy_image = categroy_image;
    }

    public List<CardBean> getCardList() {
        return cardList;
    }

    public void setCardList(List<CardBean> cardList) {
        this.cardList = cardList;
    }

    @Override public String toString() {
        return "HomeUpdateBean{" +
            "categroy_title='" + categroy_title + '\'' +
            ", categroy_image=" + categroy_image +
            ", cardList=" + cardList +
            '}';
    }
}
