package pl.w_kowalczyk.mytraining.util.devices;

import java.util.Random;

public class HeartrateGenerator implements Device {
    private int x;
    private int y;
    private static final Random RANDOM = new Random();
    public HeartrateGenerator() {
        this.x = 0;
        this.y = 0;
    }

    @Override
    public int getTime() {
      return   ++x;
    }

    @Override
    public int getHeartrate() {
         y=RANDOM.nextInt(50) + 60;
         return y;
    }
}
