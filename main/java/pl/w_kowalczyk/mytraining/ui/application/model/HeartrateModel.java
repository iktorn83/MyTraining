package pl.w_kowalczyk.mytraining.ui.application.model;

import java.util.TreeMap;

public class HeartrateModel {
    private TreeMap heartrate_progress;

    public TreeMap getHeartrate() {
        return heartrate_progress;
    }

    public void setHeartrate(TreeMap heartrate) {
        this.heartrate_progress = heartrate;
    }

    public HeartrateModel(TreeMap heartrate) {
        this.heartrate_progress = heartrate;
    }
}
