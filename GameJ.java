package src.pingtuGames;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//主界面的代码
public class GameJ extends JFrame implements KeyListener, ActionListener {


    //    创建二维数组打乱图片位置，加载图片会用
    int[][] dat = new int[4][4];
    //    定义一个二维数组win来记录，胜利的结果的二维数组的数字顺序。
    int[][] win = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
    };
    int step = 0;// 定义变量用来统计步数

    // 记录空白方块在数组中的位置
    int x = 0;
    int y = 0;
    //    图片放的位置
    String localTuPian = "D:\\javaproject\\javatest\\src\\src\\pingtuGames\\girl\\";
    String localBeijing = "D:\\javaproject\\javatest\\src\\src\\pingtuGames\\";
    Random r = new Random();
    int rtu = r.nextInt(1, 14);


    //        创建功能的条目，重写的接口需要用到对象条目进行判断

    JMenuItem changeImage = new JMenuItem("更换图片");
    JMenuItem jmi = new JMenuItem("重新游戏");
    JMenuItem jmi1 = new JMenuItem("重新登录");
    JMenuItem jmi2 = new JMenuItem("关闭游戏");
    //        关于我们条目
    JMenuItem jmi3 = new JMenuItem("公众号");


    public GameJ() {
//        界面初始化设置jframe的宽高和显示
        initjframe();

//        菜单制作
        initjenubar();
//            打乱图片
        initDat();
//        初始化图片，导入图片
        initImage();

        this.setVisible(true);//设置显示出来，让人能看到界面this为当前调用者的
//        给调用者添加键盘监听事件
        this.addKeyListener(this);//执行本类里面的代码
    }
//    随机数的生成导入图片


    //    打乱图片的索引，让上传的图片随机
    private void initDat() {
        int[] temp = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};//创建图片位置对应的数字
        Random r = new Random();
        for (int i = 0; i < temp.length; i++) {
            int index = r.nextInt(temp.length);
            int randomValue = temp[i];
            temp[i] = temp[index];
            temp[index] = randomValue;
        }
//            将一维数组的数据分4*4放到二维数组里面

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            {
                dat[i / 4][i % 4] = temp[i];//利用取模和取余给二维数组进行赋值
            }
        }
    }

    //      图片初始化，导入图片
    private void initImage() {
        this.getContentPane().removeAll();//清除已经加载了图片
//        对游戏结果的判断，胜利导入胜利图片，显示胜利图片
        if (victory()) {
            JLabel Vjl = new JLabel(new ImageIcon(localBeijing + "win.png"));
            Vjl.setBounds(203, 283, 197, 73);
            this.getContentPane().add(Vjl);
        }
//        添加计步器到容器中
        JLabel stepJl = new JLabel("步数:" + step);
        stepJl.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepJl);
//        定义三方变量表示，图片地址
        for (int i = 0; i < 4; i++) {//重复执行了4次
            for (int j = 0; j < 4; j++) {//每一行添加图片
                //        创建jlabel容器 创建图片imageicon 合并
                int num = dat[i][j];
                JLabel jlab = new JLabel(new ImageIcon(localTuPian + "girl" + rtu + "\\" + num + ".jpg"));

//        设置他的位置
                jlab.setBounds(j * 105 + 83, i * 105 + 134, 105, 105);
//        通过调用者调取菜单下面的隐藏的界面，在设置图片位置
                this.getContentPane().add(jlab);
//        把管理容器添加到界面中
                this.add(jlab);
            }
        }
//        添加背景图片
        JLabel jlb = new JLabel(new ImageIcon(localBeijing + "background.png"));
        jlb.setBounds(40, 40, 508, 560);
//        给图片添加边框,里面的new为接口，里面传递的参数有0 1
        jlb.setBorder(new BevelBorder(BevelBorder.RAISED));
        this.getContentPane().add(jlb);
        this.getContentPane().repaint();//刷新加载进去的图片

    }

    //菜单创建
    private void initjenubar() {
        JMenuBar jm = new JMenuBar();//创建整个菜单对象

        JMenu jme = new JMenu("功能");//菜单中的功能按钮
        JMenu jme1 = new JMenu("关于我们");//菜单中的关于我们按钮
//       将重新游戏，重新登录添加得到jmenu里面
//        给条目绑定事件,让鼠标点击他触发事件
        jmi.addActionListener(this);
        jmi1.addActionListener(this);
        jmi2.addActionListener(this);
        jmi3.addActionListener(this);
        changeImage.addActionListener(this);

//        功能模块
        jme.add(jmi);
        jme.add(jmi1);
        jme.add(jmi2);
        jme.add(changeImage);
//     关于我们模块
        jme1.add(jmi3);
//        功能和关于我们添加到JMenuBar里面
        jm.add(jme);
        jm.add(jme1);

//        添加得到菜单放在主界面里面
        this.setJMenuBar(jm);
    }

    //        界面初始化数据
    private void initjframe() {
        this.setSize(603, 680);//设置界面的宽和高
        this.setTitle("阿珊拼图单机版 v1.0  ");//设置标题界面
        this.setAlwaysOnTop(true);//让游戏界面置顶
        this.setLocationRelativeTo(null);//设置主界面居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭界面游戏停止运行，虚拟机停止运行
//        取消默认布局，便于我们放置图片
        this.setLayout(null);
    }

    //键盘监听
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //    按下不松时候，显示完整的图片
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        System.out.println(code);//记录查看全图的键位
        if (code == 65) {
            this.getContentPane().removeAll();//清除所有图片
            JLabel jlAll = new JLabel(new ImageIcon(localTuPian + "girl" + rtu + "\\" + "all.jpg"));
            jlAll.setBounds(83, 134, 420, 420);
            this.getContentPane().add(jlAll);
            JLabel jlb = new JLabel(new ImageIcon(localBeijing + "background.png"));
            jlb.setBounds(40, 40, 508, 560);
//        给图片添加边框,里面的new为接口，里面传递的参数有0 1
            jlb.setBorder(new BevelBorder(BevelBorder.RAISED));
            this.getContentPane().add(jlb);
            this.getContentPane().repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (victory()) {
            return;//如果游戏胜利则结束下面的方法
        }
        int code = e.getKeyCode();
        System.out.println(code);//上下左右记录的值38，40,37,39
        if (code == 38) {
//            其中x表示行，y表示列
            if (x == 3) {//空白在最下边则无法移动
                return;
            }
            dat[x][y] = dat[x + 1][y];//向上移动的交换位置，
            dat[x + 1][y] = 0;//被交换的图片，变为空图
            x++;//空白下移，则空白位置的x增加
            step++;
            initImage();//调用方法重新加载图片
        } else if (code == 40) {
            if (x == 0) {//空白在最上边则无法移动
                return;
            }
            dat[x][y] = dat[x - 1][y];//向下移动的交换位置，
            dat[x - 1][y] = 0;//被交换的图片，变为空图
            x--;//空白下移，则空白位置的x增加
            step++;
            initImage();
        } else if (code == 37) {//左移
            if (y == 3) {//空白在最右边则无法移动
                return;
            }
            dat[x][y] = dat[x][y + 1];
            dat[x][y + 1] = 0;
            y++;
            step++;
            initImage();
        } else if (code == 39) {//空白在最左边则无法移动,右移动
            if (y == 0) {
                return;
            }
            dat[x][y] = dat[x][y - 1];
            dat[x][y - 1] = 0;
            y--;
            step++;
            initImage();//调用方法重新加载图片
        } else if (code == 65) {//松开则显示原图
            initImage();
        } else if (code == 87) {
            dat = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 16}
            };
            initImage();
        }
    }


    //    定义一个方法来判断游戏胜利结果,判断win数组的数据和dat数组数据是否相同。
    public boolean victory() {
        for (int i = 0; i < dat.length; i++) {
            for (int j = 0; j < dat.length; j++) {
                if (dat[i][j] != win[i][j]) {
                    return false;//如果dat里面的顺序和win里面的有一个不一样，那抹就返回false
                }
            }
        }
        return true;//循环完了，顺序全部一样则返回true
    }

    // 重新游戏的点击绑定事件，绑定鼠标,公众号，功能
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();//监听鼠标点击的事件
        if (source == jmi) {
//           计步器清0
            step = 0;
//            打乱顺序
            initDat();
//            初始化图片
            initImage();
        } else if (source == jmi1) {
//          关闭游戏界面
            this.setVisible(false);
//          打开登录界面
            new LoginJ();
        } else if (source == jmi2) {
//            关闭虚拟机
            System.exit(0);
        } else if (source == jmi3) {
            JDialog jd = new JDialog();
            JLabel gzhJl = new JLabel(new ImageIcon(localBeijing + "gzh.jpg"));
            gzhJl.setBounds(0, 0, 785, 847);//设置图片的大小宽高
            jd.getContentPane().add(gzhJl);//添加到弹窗的界面
            jd.setSize(785, 847);//弹框设置大小
//            置顶
            jd.setAlwaysOnTop(true);
//            居中
            jd.setLocationRelativeTo(null);
//            不关闭则没法操作
            jd.setModal(true);
//            显示出来
            jd.setVisible(true);
        } else if (source == changeImage) {
            rtu = r.nextInt(1, 14);//更换图片功能
//            计步器清0
            step = 0;
//            打乱顺序
            initDat();
//            初始化图片
            initImage();

        }
    }
}
