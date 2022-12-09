package src.pingtuGames;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

//登录界面代码
public class LoginJ extends JFrame implements MouseListener {
    static ArrayList<user> allUsers = new ArrayList<>();

    static {
        allUsers.add(new user("zhangsan", "123"));
        allUsers.add(new user("lisi", "1234"));
    }

    String local = "D:\\javaproject\\javatest\\src\\src\\pingtuGames\\login\\";//登录界面的图片地址

    JTextField code = new JTextField();
    JLabel usernamephoto = new JLabel(new ImageIcon(local + "用户名.png"));
    JTextField username = new JTextField();
    JLabel passwordphoto = new JLabel(new ImageIcon(local + "密码.png"));
    JTextField password = new JTextField();
    JLabel codetext = new JLabel(new ImageIcon(local + "验证码.png"));
    JLabel codejl = new JLabel();
    //        调用工具类实现验证码功能
    String code1 = codeUtil.getCode();
    JButton loginJb = new JButton();//登录按钮
    JButton register = new JButton();//注册按钮


    public LoginJ() {
        initJFrame();//初始化登录界面
        initView();//输入用户信息界面

        this.setVisible(true);//设置显示出来，让人能看到界面this为当前调用者的

    }

    private void initView() {
//        添加用户名文字
        usernamephoto.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernamephoto);//显示到隐藏容器中
        this.setLayout(null);//取消默然布局
//        添加用户名输入框
        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);
//        添加密码文字
        passwordphoto.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordphoto);
//       添加密码输入框
        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);
//        验证码提示
        codetext.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codetext);
//        验证码输入框
        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);

//验证码提示框

//        设置内容
        codejl.setText(code1);
        codejl.addMouseListener(this);
//        设置宽高和位置
        codejl.setBounds(300, 256, 50, 30);
//        添加到隐藏容器中
        this.getContentPane().add(codejl);

//        添加登录按钮
        loginJb.setBounds(123, 310, 128, 47);
        loginJb.setIcon(new ImageIcon(local + "登录按钮.png"));
//        去除按钮的默认边框
        loginJb.setBorderPainted(false);
//       去除按钮的默认背景
        loginJb.setContentAreaFilled(false);
        loginJb.addMouseListener(this);//绑定鼠标监听事件，调用者为登录
        this.getContentPane().add(loginJb);

//        添加注册按钮
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon(local + "注册按钮.png"));
        //       去除按钮的默认背景
        register.setBorderPainted(false);
        //       去除按钮的默认背景
        register.setContentAreaFilled(false);
        register.addMouseListener(this);//绑定鼠标监听事件调用者为注册
        this.getContentPane().add(register);

//        添加登录页面的默认背景
        JLabel loginJl = new JLabel(new ImageIcon(local + "background.png"));
        loginJl.setBounds(0, 0, 470, 390);
        this.getContentPane().add(loginJl);


    }

    // 登录界面的初始化，设置宽高，标题，界面置顶
    private void initJFrame() {
        this.setSize(488, 430);//设置界面的宽和高
        this.setTitle("登录 ");//设置标题界面
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭界面游戏停止运行，虚拟机停止运行
        this.setAlwaysOnTop(true);//让游戏界面置顶
        this.setLocationRelativeTo(null);//设置主界面居中
    }

    //设置一个弹窗，用来提示用户名或者，密码，或者验证码错误
    public void showJDialog(String text) {
        JDialog jdCode = new JDialog();
        jdCode.setSize(200, 150);
        jdCode.setAlwaysOnTop(true);//让弹窗置顶
        jdCode.setLocationRelativeTo(null);//让弹窗居中
        jdCode.setModal(true);//弹窗不关闭不能下一步操作
        //        创建jlabel对象管理文字
        JLabel text1 = new JLabel(text);
        text1.setBounds(0, 0, 200, 150);
        jdCode.getContentPane().add(text1);
        jdCode.setVisible(true);
    }

 //比较用户名和密码的一致性
    private boolean contains(user userNew) {
        for (int i = 0; i < allUsers.size(); i++) {
            user rightuser = allUsers.get(i);
            if (userNew.getUserName().equals(rightuser.getUserName()) && userNew.getUserPassword().equals(rightuser.getUserPassword())) {
                return true;
            }
        }
        return false;
    }
    //点击
    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == loginJb) {
            System.out.println("点击了登录按钮");
            String usernameText = username.getText();//获取用户名输入框的内容
            String passwordText = password.getText();//获取密码输入框的内容
            String codeText = code.getText();//获取验证码输入框的内容
//            创建一个用户对象
            user userNew = new user(usernameText, passwordText);
            System.out.println("用户输入的用户名" + usernameText);
            System.out.println("用户输入的密码" + passwordText);
            if (codeText.length() == 0) {
                showJDialog("验证码不能为空");
            } else if (usernameText.length() == 0 || passwordText.length() == 0) {
                System.out.println("用户名或者密码为空");
                showJDialog("用户名或者密码为空");
            } else if (!codeText.equalsIgnoreCase(code1)) {
                showJDialog("验证码错误");
            } else if (contains(userNew)) {
                System.out.println("用户名和密码正确");
//                关闭页面，进入游戏页面
                this.setVisible(false);
                new GameJ();//进入游戏页面
            } else {
                showJDialog("用户名或密码错误");
            }
        } else if (source == register) {
            System.out.println("点击了注册");
        } else if (source == codejl) { // 点击验证码会刷新验证码
            code1 = codeUtil.getCode();
            codejl.setText(code1);
        }


    }

//    按下不松
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == loginJb){
            loginJb.setIcon(new ImageIcon(local+"登录按下.png") );
        }else if (e.getSource()==register){
            register.setIcon(new ImageIcon(local+"注册按下.png"));
        }
    }
//松开按钮
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource()==loginJb){
            loginJb.setIcon(new ImageIcon(local+"登录按钮.png"));
        }else if (e.getSource() ==register){
            register.setIcon(new ImageIcon(local+"注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
