package com.hunfrit.repeattest.SetGet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Artem Shapovalov on 18.12.2017.
 */

public class SetGet {
        @SerializedName("r030")
        @Expose
        private Integer r030;
        @SerializedName("txt")
        @Expose
        private String txt;
        @SerializedName("rate")
        @Expose
        private Float rate;
        @SerializedName("currency")
        @Expose
        private String currency;
        @SerializedName("exchangedate")
        @Expose

        private String exchangedate;

        public Integer getR030() {
            return r030;
        }

        public void setR030(Integer r030) {
            this.r030 = r030;
        }

        public String getTxt() {
            return txt;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public Float getRate() {return rate;
        }

        public void setRate(Float rate) {
            this.rate = rate;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String cc) {
            this.currency = currency;
        }

        public String getExchangedate() {
            return exchangedate;
        }

        public void setExchangedate(String exchangedate) {
            this.exchangedate = exchangedate;
        }

    }
