package pl.w_kowalczyk.mytraining.ui.application.model;

public class CpmModel {
    private int basic_cpm;
    private int gain_mass_cpm;
    private int reduce_mass_cpm;

    public int getBasic_cpm() {
        return basic_cpm;
    }

    public void setBasic_cpm(int basic_cpm) {
        this.basic_cpm = basic_cpm;
    }

    public int getGain_mass_cpm() {
        return gain_mass_cpm;
    }

    public void setGain_mass_cpm(int gain_mass_cpm) {
        this.gain_mass_cpm = gain_mass_cpm;
    }

    public int getReduce_mass_cpm() {
        return reduce_mass_cpm;
    }

    public void setReduce_mass_cpm(int reduce_mass_cpm) {
        this.reduce_mass_cpm = reduce_mass_cpm;
    }

    public CpmModel(int basic_cpm) {
        this.basic_cpm = basic_cpm;
        this.gain_mass_cpm = basic_cpm+250;
        this.reduce_mass_cpm = basic_cpm-250;
    }
}
