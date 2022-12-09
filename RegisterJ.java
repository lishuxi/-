package src.pingtuGames;

import javax.swing.*;
//注册界面代码
public class RegisterJ extends JFrame {




    public RegisterJ(){
        this.setSize(488,500);//设置界面的宽和高
        this.setTitle("注册 ");//设置标题界面
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭界面游戏停止运行，虚拟机停止运行
        this.setAlwaysOnTop(true);//让游戏界面置顶
        this.setLocationRelativeTo(null);//设置主界面居中
        this.setVisible(true);//设置显示出来，让人能看到界面this为当前调用者的

    }
}
