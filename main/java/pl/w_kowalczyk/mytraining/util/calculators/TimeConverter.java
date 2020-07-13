package pl.w_kowalczyk.mytraining.util.calculators;

public class TimeConverter {
    static public int getHour(int time){
        return  time / 3600;
    }
    static public int getMinute(int time){
        return  ((time % 3600) / 60);
    }
    static public int getSecond(int time){
        return time % 60;
    }
}
