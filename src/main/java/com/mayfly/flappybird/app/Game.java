package com.mayfly.flappybird.app;


/*
* 游戏主体，管理游戏的组件和窗口绘制*/


import com.mayfly.flappybird.component.Bird;
import com.mayfly.flappybird.component.GameBackground;
import com.mayfly.flappybird.component.GameForeground;

import static com.mayfly.flappybird.util.Constant.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;


public class Game extends Frame {
    private static final long serialVersionUID = 1L;
    private static int gameState;
    public static final int GAME_READY = 0;
    public static final int GAME_START = 1;
    public static final int GAME_OVER = 2;
    private Bird bird; // 小鸟对象


    private GameBackground background;
    private GameForeground gameForeground; //游戏未开始时对象

    private final BufferedImage bufImg = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);

    //构造器中初始化
    public Game() {
        initFrame();//初始窗口
        setVisible(true);//设置窗口可见
        initGame();//初始游戏对象
    }
    //初始化游戏窗口
    private void initFrame() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);     //设置窗口大小
        setTitle(GAME_TITLE);                   //设置窗口标题
        setLocation(FRAME_X, FRAME_Y);          //设置窗口出现的默认位置
        setResizable(false);                    //设置窗口大小不可变
        /*添加关闭窗口事件，设置关闭窗口退出游戏，监听窗口发生事件，*/
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        /*添加按钮监听*/
        addKeyListener(new BirdKeyListener());
    }
    /*建立一个接受按键事件的内部类*/
     class BirdKeyListener implements KeyListener{

        // 按键按下，根据游戏当前的状态调用不同的方法
        @Override
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            switch (gameState) {
                case GAME_READY:
                    if (keycode == KeyEvent.VK_SPACE) {
                        // 游戏启动界面时按下空格，小鸟振翅一次并开始受重力影响

                    }
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {}
        @Override
        public void keyTyped(KeyEvent e) {}
    }

    //初始化游戏中的各个对象
    private void initGame() {
        background=new GameBackground();
        setGameState(GAME_READY);
        //启动用于刷新窗口的线程
        new Thread(() ->{
            while (true) {
                repaint(); // 通过调用repaint(),让JVM调用update()
                try {
                    Thread.sleep(FPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    /*设置游戏状态的方法*/
    public static void setGameState(int gameState) {
        /*回调函数*/
        Game.gameState = gameState;
    }

    // 项目中存在两个线程：系统线程，自定义的线程：调用repaint()。
    // 系统线程：屏幕内容的绘制，窗口事件的监听与处理
    // 两个线程会抢夺系统资源，可能会出现一次刷新周期所绘制的内容，并没有在一次刷新周期内完成
    // （双缓冲）单独定义一张图片，将需要绘制的内容绘制到这张图片，再一次性地将图片绘制到窗口

    /**
     * 绘制游戏内容 当repaint()方法被调用时，JVM会调用update()，参数g是系统提供的画笔，由系统进行实例化
     * 单独启动一个线程，不断地快速调用repaint()，让系
     */
    public void update(Graphics g) {
        Graphics bufG = bufImg.getGraphics();//
        background.draw(bufG, bird);

        g.drawImage(bufImg, 0, 0, null); // 一次性将图片绘制到屏幕上

    }
}






