package com.mayfly.flappybird.component;

import com.mayfly.flappybird.util.Constant;
import com.mayfly.flappybird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* * 前景层，目前管理云朵的生成逻辑并绘制容器中的云朵
 */
public class GameForeground {
    private final List<Cloud> clouds;
    private final BufferedImage[] cloudImages;
    private long time;
    public static final int CLOUD_INTERVAL = 100;

    public GameForeground() {
        clouds = new ArrayList<>();
        cloudImages = new BufferedImage[Constant.CLOUD_IMAGE_COUNT];
        for (int i = 0; i < Constant.CLOUD_IMAGE_COUNT; i++)
            cloudImages[i] = GameUtil.loadBufferedImage(Constant.CLOUDS_IMG_PATH[i]);
        time = System.currentTimeMillis();//获取当前运算时间，用于控制云的逻辑运算周期
    }

    //绘制方法
    public void draw(Graphics g, Bird bird) {
        cloudBornLogic();
        for (Cloud cloud : clouds) {
            cloud.draw(g, bird);
        }
    }

    //云朵的控制
    private void cloudBornLogic() {
        if(System.currentTimeMillis() - time > CLOUD_INTERVAL) {
            time = System.currentTimeMillis();
            if (clouds.size() < Constant.MAX_CLOUD_COUNT)
                try {
                    if (GameUtil.isInProbability(Constant.CLOUD_BORN_PERCENT, 100)) {
                        int index = GameUtil.getRandomNumber(0, Constant.CLOUD_IMAGE_COUNT);
                        int x = Constant.FRAME_WIDTH;
                        int y = GameUtil.getRandomNumber(Constant.TOP_BAR_HEIGHT, Constant.FRAME_HEIGHT / 3);
                        Cloud cloud = new Cloud(cloudImages[index], x, y);
                        clouds.add(cloud);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        for (int i = 0; i < clouds.size(); i++) {
            Cloud tempCloud = clouds.get(i);
        }
    }
}
