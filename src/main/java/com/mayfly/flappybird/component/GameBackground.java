package com.mayfly.flappybird.component;

import java.awt.*;
import java.awt.image.BufferedImage;
import com.mayfly.flappybird.util.*;

/*
 * 游戏背景，实现游戏背景的绘制*/
public class GameBackground {

    /*
     * 背景图片
     * 背景层速度
     * 背景层坐标
     * */
    private static final BufferedImage backGorundImg;
    private final int speed;
    private int layerX;
    public static final int GROUND_HRIGHT;//背景高度

    static {
        backGorundImg = GameUtil.loadBufferedImage(Constant.BG_IMG_PATH);
        assert backGorundImg != null;
        GROUND_HRIGHT = backGorundImg.getHeight() / 2;
    }

    /*构造器中处初始化*/
    public GameBackground() {
        this.speed = Constant.GAME_SPEED;
        this.layerX = 0;
    }

    /*绘制方法*/
    public void draw(Graphics g, Bird bird) {
        /*绘制背景色*/
        g.setColor(Constant.BG_COLOR);
        g.fillRect(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);

        /*获得背景图片的尺寸*/
        int imgWidth = backGorundImg.getWidth();
        int imgHeight = backGorundImg.getHeight();

        int count =Constant.FRAME_WIDTH/imgWidth+2; //根据窗口宽度得到图片的绘制次数
        for (int i = 0; i < count; i++) {
            g.drawImage(backGorundImg, imgWidth * i - layerX, Constant.FRAME_HEIGHT - imgHeight, null);
        }


    }


}
