package com.demien.xpath.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: dkovalsky
 * Date: 4/20/12
 * Time: 3:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class DayReportVO {

    // 	информация о сроке прогнозирования:
    private String forecastDay;   //дата, на которую составлен прогноз в данном блоке
    private String forecastMonth; //дата, на которую составлен прогноз в данном блоке
    private String forecastYear;     //дата, на которую составлен прогноз в данном блоке
    private String forecastHour;     //местное время, на которое составлен прогноз
    private String forecastTod;     //время суток, для которого составлен прогноз: 0 - ночь 1 - утро, 2 - день, 3 - вечер
    private String forecastWeekday; //	день недели, 1 - воскресенье, 2 - понедельник, и т.д.
    private String forecastPredict; //	заблаговременность прогноза в часах

    //PHENOMENA  	атмосферные явления:
    private String phenomenaCloudiness; //	облачность по градациям:  0 - ясно, 1- малооблачно, 2 - облачно, 3 - пасмурно
    private String phenomenaPrecipitation; //	тип осадков: 4 - дождь, 5 - ливень, 6,7 – снег, 8 - гроза, 9 - нет данных, 10 - без осадков
    private String phenomenaRpower; //	интенсивность осадков, если они есть. 0 - возможен дождь/снег, 1 - дождь/снег
    private String phenomenaSpower; //	вероятность грозы, если прогнозируется: 0 - возможна гроза, 1 - гроза
    //PRESSURE 	атмосферное давление, в мм.рт.ст.
    private String pressureMax;
    private String pressureMin;
    //TEMPERATURE 	температура воздуха, в градусах Цельсия
    private String temperatureMax;
    private String temperatureMin;
    //WIND 	приземный ветер
    private String windMax; //	минимальное и максимальное значения средней скорости ветра, без порывов
    private String windMin; //	минимальное и максимальное значения средней скорости ветра, без порывов
    private String windDirection; //  	направление ветра в румбах, 0 - северный, 1 - северо-восточный,  и т.д.
    //RELWET 	относительная влажность воздуха, в %
    private String relwetMax;
    private String relwetMin;
    //HEAT 	комфорт - температура воздуха по ощущению одетого по сезону человека, выходящего на улицу
    private String heatMin;
    private String heatMax;

    // constructor
    public DayReportVO() {

    }


    // getters and setters
    public String getForecastDay() {
        return forecastDay;
    }

    public void setForecastDay(String forecastDay) {
        this.forecastDay = forecastDay;
    }

    public String getForecastMonth() {
        return forecastMonth;
    }

    public void setForecastMonth(String forecastMonth) {
        this.forecastMonth = forecastMonth;
    }

    public String getForecastYear() {
        return forecastYear;
    }

    public void setForecastYear(String forecastYear) {
        this.forecastYear = forecastYear;
    }

    public String getForecastHour() {
        return forecastHour;
    }

    public void setForecastHour(String forecastHour) {
        this.forecastHour = forecastHour;
    }

    public String getForecastTod() {
        return forecastTod;
    }

    public void setForecastTod(String forecastTod) {
        this.forecastTod = forecastTod;
    }

    public String getForecastWeekday() {
        return forecastWeekday;
    }

    public void setForecastWeekday(String forecastWeekday) {
        this.forecastWeekday = forecastWeekday;
    }

    public String getForecastPredict() {
        return forecastPredict;
    }

    public void setForecastPredict(String forecastPredict) {
        this.forecastPredict = forecastPredict;
    }

    public String getPhenomenaCloudiness() {
        return phenomenaCloudiness;
    }

    public void setPhenomenaCloudiness(String phenomenaCloudiness) {
        this.phenomenaCloudiness = phenomenaCloudiness;
    }

    public String getPhenomenaPrecipitation() {
        return phenomenaPrecipitation;
    }

    public void setPhenomenaPrecipitation(String phenomenaPrecipitation) {
        this.phenomenaPrecipitation = phenomenaPrecipitation;
    }

    public String getPhenomenaRpower() {
        return phenomenaRpower;
    }

    public void setPhenomenaRpower(String phenomenaRpower) {
        this.phenomenaRpower = phenomenaRpower;
    }

    public String getPhenomenaSpower() {
        return phenomenaSpower;
    }

    public void setPhenomenaSpower(String phenomenaSpower) {
        this.phenomenaSpower = phenomenaSpower;
    }

    public String getPressureMax() {
        return pressureMax;
    }

    public void setPressureMax(String pressureMax) {
        this.pressureMax = pressureMax;
    }

    public String getPressureMin() {
        return pressureMin;
    }

    public void setPressureMin(String pressureMin) {
        this.pressureMin = pressureMin;
    }

    public String getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(String temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public String getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(String temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public String getWindMax() {
        return windMax;
    }

    public void setWindMax(String windMax) {
        this.windMax = windMax;
    }

    public String getWindMin() {
        return windMin;
    }

    public void setWindMin(String windMin) {
        this.windMin = windMin;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getRelwetMax() {
        return relwetMax;
    }

    public void setRelwetMax(String relwetMax) {
        this.relwetMax = relwetMax;
    }

    public String getRelwetMin() {
        return relwetMin;
    }

    public void setRelwetMin(String relwetMin) {
        this.relwetMin = relwetMin;
    }

    public String getHeatMin() {
        return heatMin;
    }

    public void setHeatMin(String heatMin) {
        this.heatMin = heatMin;
    }

    public String getHeatMax() {
        return heatMax;
    }

    public void setHeatMax(String heatMax) {
        this.heatMax = heatMax;
    }

    // implementation
    public Date getDate() {
        Date res = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd.mm.yyyy HH");
            String str_date = getForecastDay() + "." + getForecastMonth() + "." + getForecastYear() + " " + getForecastHour();


            res = (Date) formatter.parse(str_date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public String toString() {
        String res = //getForecastDay()+"."+getForecastMonth()+"."+getForecastYear()+":"+getForecastHour()+"h"+
                getDate().toString() +
                        " PHENOMENA:" + getPhenomenaCloudiness() + " " + getPhenomenaPrecipitation() + " " + getPhenomenaRpower() + " " + getPhenomenaSpower() +
                        " PRESSURE:" + getPressureMin() + " " + getPressureMax() +
                        " TEMPERATURE:" + getTemperatureMin() + " " + getTemperatureMax() +
                        " WIND:" + getWindMin() + " " + getWindMax() + " " + getWindDirection() +
                        " RELWET:" + getRelwetMin() + " " + getRelwetMax() +
                        " HEAT:" + getHeatMin() + " " + getHeatMax();

        return res;
    }

    public static DayReportVO getTemplate() {
        DayReportVO TEMPLATE = new DayReportVO();

        TEMPLATE.setForecastDay("day");
        TEMPLATE.setForecastMonth("month");
        TEMPLATE.setForecastYear("year");
        TEMPLATE.setForecastHour("hour");
        TEMPLATE.setForecastTod("tod");
        TEMPLATE.setForecastWeekday("weekday");

        TEMPLATE.setPhenomenaCloudiness("cloudiness");
        TEMPLATE.setPhenomenaPrecipitation("precipitation");
        TEMPLATE.setPhenomenaRpower("rpower");
        TEMPLATE.setPhenomenaSpower("spower");

        TEMPLATE.setPressureMax("max");
        TEMPLATE.setPressureMin("min");

        TEMPLATE.setTemperatureMax("max");
        TEMPLATE.setTemperatureMin("min");

        TEMPLATE.setWindMax("max");
        TEMPLATE.setWindMin("min");
        TEMPLATE.setWindDirection("direction");

        TEMPLATE.setRelwetMax("max");
        TEMPLATE.setRelwetMin("min");

        TEMPLATE.setHeatMax("max");
        TEMPLATE.setHeatMin("min");

        return TEMPLATE;
    }
}
