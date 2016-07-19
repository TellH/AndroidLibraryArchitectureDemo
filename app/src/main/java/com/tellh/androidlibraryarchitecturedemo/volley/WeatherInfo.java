package com.tellh.androidlibraryarchitecturedemo.volley;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tlh on 2016/7/19.
 */
public class WeatherInfo {

    /**
     * aqi : {"city":{"aqi":"28","co":"1","no2":"16","o3":"16","pm10":"27","pm25":"17","qlty":"优","so2":"15"}}
     * basic : {"city":"佛山","cnty":"中国","id":"CN101280800","lat":"22.828000","lon":"113.167000","update":{"loc":"2016-07-19 08:52","utc":"2016-07-19 00:52"}}
     * daily_forecast : [{"astro":{"sr":"05:53","ss":"19:14"},"cond":{"code_d":"305","code_n":"305","txt_d":"小雨","txt_n":"小雨"},"date":"2016-07-19","hum":"65","pcpn":"4.1","pop":"95","pres":"1008","tmp":{"max":"35","min":"27"},"vis":"7","wind":{"deg":"185","dir":"无持续风向","sc":"微风","spd":"10"}},{"astro":{"sr":"05:53","ss":"19:14"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2016-07-20","hum":"60","pcpn":"3.9","pop":"87","pres":"1009","tmp":{"max":"35","min":"27"},"vis":"10","wind":{"deg":"179","dir":"无持续风向","sc":"微风","spd":"0"}},{"astro":{"sr":"05:53","ss":"19:13"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2016-07-21","hum":"60","pcpn":"2.9","pop":"78","pres":"1010","tmp":{"max":"36","min":"27"},"vis":"9","wind":{"deg":"189","dir":"无持续风向","sc":"微风","spd":"10"}},{"astro":{"sr":"05:54","ss":"19:13"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-07-22","hum":"64","pcpn":"0.2","pop":"55","pres":"1008","tmp":{"max":"35","min":"28"},"vis":"10","wind":{"deg":"198","dir":"无持续风向","sc":"微风","spd":"5"}},{"astro":{"sr":"05:54","ss":"19:12"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-07-23","hum":"49","pcpn":"0.0","pop":"9","pres":"1006","tmp":{"max":"35","min":"28"},"vis":"10","wind":{"deg":"221","dir":"无持续风向","sc":"微风","spd":"6"}},{"astro":{"sr":"05:55","ss":"19:12"},"cond":{"code_d":"100","code_n":"101","txt_d":"晴","txt_n":"多云"},"date":"2016-07-24","hum":"48","pcpn":"0.0","pop":"8","pres":"1005","tmp":{"max":"35","min":"28"},"vis":"10","wind":{"deg":"201","dir":"无持续风向","sc":"微风","spd":"1"}},{"astro":{"sr":"05:55","ss":"19:12"},"cond":{"code_d":"101","code_n":"305","txt_d":"多云","txt_n":"小雨"},"date":"2016-07-25","hum":"50","pcpn":"0.0","pop":"6","pres":"1007","tmp":{"max":"35","min":"27"},"vis":"10","wind":{"deg":"198","dir":"无持续风向","sc":"微风","spd":"4"}}]
     * hourly_forecast : [{"date":"2016-07-19 10:00","hum":"73","pop":"72","pres":"1009","tmp":"32","wind":{"deg":"191","dir":"西南风","sc":"3-4","spd":"19"}},{"date":"2016-07-19 13:00","hum":"66","pop":"94","pres":"1008","tmp":"34","wind":{"deg":"188","dir":"南风","sc":"3-4","spd":"23"}},{"date":"2016-07-19 16:00","hum":"67","pop":"93","pres":"1007","tmp":"33","wind":{"deg":"183","dir":"南风","sc":"3-4","spd":"24"}},{"date":"2016-07-19 19:00","hum":"78","pop":"31","pres":"1007","tmp":"30","wind":{"deg":"176","dir":"南风","sc":"3-4","spd":"18"}},{"date":"2016-07-19 22:00","hum":"87","pop":"0","pres":"1008","tmp":"28","wind":{"deg":"166","dir":"东南风","sc":"微风","spd":"13"}}]
     * now : {"cond":{"code":"101","txt":"多云"},"fl":"34","hum":"78","pcpn":"0","pres":"1007","tmp":"29","vis":"10","wind":{"deg":"130","dir":"南风","sc":"5-6","spd":"27"}}
     * status : ok
     * suggestion : {"comf":{"brf":"很不舒适","txt":"白天虽然天气以阴为主，但由于天热，加上湿度较大，您会感到很闷热，很不舒适。"},"cw":{"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},"drsg":{"brf":"炎热","txt":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。"},"flu":{"brf":"少发","txt":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"},"sport":{"brf":"较不宜","txt":"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"},"trav":{"brf":"一般","txt":"有较弱降水和微风作伴的一天，且天气热，旅游指数一般，可有选择的进行出游，出游请注意防暑降温并携带雨具。"},"uv":{"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}}
     */

    @SerializedName("HeWeather data service 3.0")
    private List<HeWeatherEntity> HeWeatherList;

    public List<HeWeatherEntity> getHeWeatherList() {
        return HeWeatherList;
    }

    public void setHeWeatherList(List<HeWeatherEntity> HeWeatherList) {
        this.HeWeatherList = HeWeatherList;
    }

    public static class HeWeatherEntity {
        /**
         * cond : {"code":"101","txt":"多云"}
         * fl : 34
         * hum : 78
         * pcpn : 0
         * pres : 1007
         * tmp : 29
         * vis : 10
         * wind : {"deg":"130","dir":"南风","sc":"5-6","spd":"27"}
         */

        private NowEntity now;
        /**
         * comf : {"brf":"很不舒适","txt":"白天虽然天气以阴为主，但由于天热，加上湿度较大，您会感到很闷热，很不舒适。"}
         * cw : {"brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"}
         * drsg : {"brf":"炎热","txt":"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。"}
         * flu : {"brf":"少发","txt":"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"}
         * sport : {"brf":"较不宜","txt":"有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。"}
         * trav : {"brf":"一般","txt":"有较弱降水和微风作伴的一天，且天气热，旅游指数一般，可有选择的进行出游，出游请注意防暑降温并携带雨具。"}
         * uv : {"brf":"弱","txt":"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。"}
         */

        private SuggestionEntity suggestion;
        /**
         * astro : {"sr":"05:53","ss":"19:14"}
         * cond : {"code_d":"305","code_n":"305","txt_d":"小雨","txt_n":"小雨"}
         * date : 2016-07-19
         * hum : 65
         * pcpn : 4.1
         * pop : 95
         * pres : 1008
         * tmp : {"max":"35","min":"27"}
         * vis : 7
         * wind : {"deg":"185","dir":"无持续风向","sc":"微风","spd":"10"}
         */

        private List<DailyForecastEntity> daily_forecast;
        /**
         * date : 2016-07-19 10:00
         * hum : 73
         * pop : 72
         * pres : 1009
         * tmp : 32
         * wind : {"deg":"191","dir":"西南风","sc":"3-4","spd":"19"}
         */

        private List<HourlyForecastEntity> hourly_forecast;

        public NowEntity getNow() {
            return now;
        }

        public void setNow(NowEntity now) {
            this.now = now;
        }

        public SuggestionEntity getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(SuggestionEntity suggestion) {
            this.suggestion = suggestion;
        }

        public List<DailyForecastEntity> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastEntity> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public List<HourlyForecastEntity> getHourly_forecast() {
            return hourly_forecast;
        }

        public void setHourly_forecast(List<HourlyForecastEntity> hourly_forecast) {
            this.hourly_forecast = hourly_forecast;
        }

        public static class NowEntity {
            /**
             * code : 101
             * txt : 多云
             */

            private CondEntity cond;
            private String fl;
            private String hum;
            private String pcpn;
            private String pres;
            private String tmp;
            private String vis;
            /**
             * deg : 130
             * dir : 南风
             * sc : 5-6
             * spd : 27
             */

            private WindEntity wind;

            public CondEntity getCond() {
                return cond;
            }

            public void setCond(CondEntity cond) {
                this.cond = cond;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindEntity getWind() {
                return wind;
            }

            public void setWind(WindEntity wind) {
                this.wind = wind;
            }

            public static class CondEntity {
                private String code;
                private String txt;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class WindEntity {
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class SuggestionEntity {
            /**
             * brf : 很不舒适
             * txt : 白天虽然天气以阴为主，但由于天热，加上湿度较大，您会感到很闷热，很不舒适。
             */

            private ComfEntity comf;
            /**
             * brf : 不宜
             * txt : 不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。
             */

            private CwEntity cw;
            /**
             * brf : 炎热
             * txt : 天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。
             */

            private DrsgEntity drsg;
            /**
             * brf : 少发
             * txt : 各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。
             */

            private FluEntity flu;
            /**
             * brf : 较不宜
             * txt : 有降水，推荐您在室内进行健身休闲运动；若坚持户外运动，须注意携带雨具并注意避雨防滑。
             */

            private SportEntity sport;
            /**
             * brf : 一般
             * txt : 有较弱降水和微风作伴的一天，且天气热，旅游指数一般，可有选择的进行出游，出游请注意防暑降温并携带雨具。
             */

            private TravEntity trav;
            /**
             * brf : 弱
             * txt : 紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。
             */

            private UvEntity uv;

            public ComfEntity getComf() {
                return comf;
            }

            public void setComf(ComfEntity comf) {
                this.comf = comf;
            }

            public CwEntity getCw() {
                return cw;
            }

            public void setCw(CwEntity cw) {
                this.cw = cw;
            }

            public DrsgEntity getDrsg() {
                return drsg;
            }

            public void setDrsg(DrsgEntity drsg) {
                this.drsg = drsg;
            }

            public FluEntity getFlu() {
                return flu;
            }

            public void setFlu(FluEntity flu) {
                this.flu = flu;
            }

            public SportEntity getSport() {
                return sport;
            }

            public void setSport(SportEntity sport) {
                this.sport = sport;
            }

            public TravEntity getTrav() {
                return trav;
            }

            public void setTrav(TravEntity trav) {
                this.trav = trav;
            }

            public UvEntity getUv() {
                return uv;
            }

            public void setUv(UvEntity uv) {
                this.uv = uv;
            }

            public static class ComfEntity {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class CwEntity {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class DrsgEntity {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class FluEntity {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class SportEntity {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class TravEntity {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class UvEntity {
                private String brf;
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
        }

        public static class DailyForecastEntity {
            /**
             * sr : 05:53
             * ss : 19:14
             */

            private AstroEntity astro;
            /**
             * code_d : 305
             * code_n : 305
             * txt_d : 小雨
             * txt_n : 小雨
             */

            private CondEntity cond;
            private String date;
            private String hum;
            private String pcpn;
            private String pop;
            private String pres;
            /**
             * max : 35
             * min : 27
             */

            private TmpEntity tmp;
            private String vis;
            /**
             * deg : 185
             * dir : 无持续风向
             * sc : 微风
             * spd : 10
             */

            private WindEntity wind;

            public AstroEntity getAstro() {
                return astro;
            }

            public void setAstro(AstroEntity astro) {
                this.astro = astro;
            }

            public CondEntity getCond() {
                return cond;
            }

            public void setCond(CondEntity cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public TmpEntity getTmp() {
                return tmp;
            }

            public void setTmp(TmpEntity tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindEntity getWind() {
                return wind;
            }

            public void setWind(WindEntity wind) {
                this.wind = wind;
            }

            public static class AstroEntity {
                private String sr;
                private String ss;

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }

            public static class CondEntity {
                private String code_d;
                private String code_n;
                private String txt_d;
                private String txt_n;

                public String getCode_d() {
                    return code_d;
                }

                public void setCode_d(String code_d) {
                    this.code_d = code_d;
                }

                public String getCode_n() {
                    return code_n;
                }

                public void setCode_n(String code_n) {
                    this.code_n = code_n;
                }

                public String getTxt_d() {
                    return txt_d;
                }

                public void setTxt_d(String txt_d) {
                    this.txt_d = txt_d;
                }

                public String getTxt_n() {
                    return txt_n;
                }

                public void setTxt_n(String txt_n) {
                    this.txt_n = txt_n;
                }
            }

            public static class TmpEntity {
                private String max;
                private String min;

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }

            public static class WindEntity {
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class HourlyForecastEntity {
            private String date;
            private String hum;
            private String pop;
            private String pres;
            private String tmp;
            /**
             * deg : 191
             * dir : 西南风
             * sc : 3-4
             * spd : 19
             */

            private WindEntity wind;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public WindEntity getWind() {
                return wind;
            }

            public void setWind(WindEntity wind) {
                this.wind = wind;
            }

            public static class WindEntity {
                private String deg;
                private String dir;
                private String sc;
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }
    }

}
