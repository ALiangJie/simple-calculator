package com.ALiangJie.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.Vector;

public class Calculator extends JFrame {

    // 操作数1，为了程序的安全，初值一定设置，这里设置为0。
    String string1 = "0";

    // 操作数2
    String string2 = "0";

    //运算符
    String signsl = "+";

    //运算结果
    String result = " ";

    // 以下k1至k5为状态开关

    // 开关1用于选择输入方向，将要写入str1或str2
    // 为 1 时写入 str1，为 2 时写入 str2
    int k1 = 1;

    // 开关 2 用于记录符号键的次数
    // 如果 k2>1 说明进行的是 2+3-9+8 这样的多符号运算
    int k2 = 1;

    // 开关3用于标识 str1 是否可以被清 0
    // 等于 1 时可以，不等于1时不能被清0
    int k3 = 1;

    // 开关4用于标识 str2 是否可以被清 0
    // 等于 1 时可以，不等于1时不能被清0
    int k4 = 1;

    // 开关5用于控制小数点可否被录入
    // 等于1时可以，不为1时，输入的小数点被丢掉
    int k5 = 1;

    //用于存储被按下的键
    // store的作用类似于寄存器，用于记录是否连续按下符号键
    JButton store;

    //vt存储之前输入的运算符
    Vector vt = new Vector(20,10);

    //创建一个JFrame对象并初始化，JFrame可以理解为程序的主窗口
    JFrame frame=new JFrame("Calculator");


    //创建一个JTextField对象并初始化，JTextField是用于显示操作和计算结果的文本框
    //参数20表示可以显示20列的文本内容
    JTextField result_TextField=new JTextField(result,20);

    //清除按钮
    JButton clear_Button = new JButton("Clear");

    //数字按钮
    JButton button0= new JButton("0");
    JButton button1= new JButton("1");
    JButton button2= new JButton("2");
    JButton button3= new JButton("3");
    JButton button4= new JButton("4");
    JButton button5= new JButton("5");
    JButton button6= new JButton("6");
    JButton button7= new JButton("7");
    JButton button8= new JButton("8");
    JButton button9= new JButton("9");

    //操作符按钮
    JButton button_Point=new JButton(".");
    JButton button_Plus=new JButton("+");
    JButton button_Minus=new JButton("-");
    JButton button_Multiply=new JButton("*");
    JButton button_Division=new JButton("/");

    //计算按钮
    JButton button_Equal=new JButton("=");

    public Calculator() {
        button0.setMnemonic(KeyEvent.VK_0);

        result_TextField.setHorizontalAlignment(JTextField.RIGHT);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));
        panel.add(button7);
        panel.add(button8);
        panel.add(button9);
        panel.add(button_Division);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button_Multiply);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button_Plus);
        panel.add(button0);
        panel.add(button_Point);
        panel.add(button_Equal);
        panel.add(button_Minus);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(result_TextField, BorderLayout.WEST);
        panel2.add(clear_Button, BorderLayout.EAST);

        frame.setLocation(300, 200);
        frame.setResizable(true);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel2, BorderLayout.NORTH);
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);

        class Listener_Clear implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                store = (JButton) e.getSource();
                vt.add(store);
                k5 = 1;
                k2 = 1;
                k1 = 1;
                k3 = 1;
                k4 = 1;
                string1 = "0";
                string2 = "0";
                signsl = "";
                result = "";
                result_TextField.setText(result);
                vt.clear();
            }
        }

        //数字键
        class Listener_Number implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                // 获取事件源，并从事件源中获取输入的数据
                String ss = ((JButton) e.getSource()).getText();

                store = (JButton) e.getSource();
                vt.add(store);

                if (k1 == 1) {
                    if (k3 == 1) {
                        string1 = "";

                        // 还原开关k5状态
                        k5 = 1;
                    }
                    string1 = string1 + ss;

                    k3 = k3 + 1;

                    // 显示结果
                    result_TextField.setText(string1);

                } else if (k1 == 2) {
                    if (k4 == 1) {
                        string2 = "";

                        // 还原开关k5状态
                        k5 = 1;
                    }
                    string2 = string2 + ss;
                    k4 = k4 + 1;
                    result_TextField.setText(string2);
                }
            }
        }

        class Listener_Point implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                store = (JButton) e.getSource();
                vt.add(store);
                if (k5 == 1) {
                    String ss2 = ((JButton) e.getSource()).getText();
                    if (k1 == 1) {
                        if (k3 == 1) {
                            string1 = "";
                            // 还原开关k5状态
                            k5 = 1;
                        }
                        string1 = string1 + ss2;

                        k3 = k3 + 1;

                        // 显示结果
                        result_TextField.setText(string1);

                    } else if (k1 == 2) {
                        if (k4 == 1) {
                            string2 = "";
                            // 还原开关k5的状态
                            k5 = 1;
                        }
                        string2 = string2 + ss2;

                        k4 = k4 + 1;

                        result_TextField.setText(string2);
                    }
                }

                k5 = k5 + 1;
            }
        }

        class Listener_Signal implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String ss2 = ((JButton) e.getSource()).getText();
                store = (JButton) e.getSource();
                vt.add(store);
                if (k2 == 1) {
                    // 开关 k1 为 1 时向数 1 写输入值，为 2 时向数2写输入值。
                    k1 = 2;
                    k5 = 1;
                    signsl = ss2;
                    k2 = k2 + 1;// 按符号键的次数
                } else {
                    int a = vt.size();
                    JButton c = (JButton) vt.get(a - 2);

                    if (!(c.getText().equals("+"))
                            && !(c.getText().equals("-"))
                            && !(c.getText().equals("*"))
                            && !(c.getText().equals("/"))) {
                        cal();
                        string1 = result;
                        // 开关 k1 为 1 时，向数 1 写值，为2时向数2写
                        k1 = 2;
                        k5 = 1;
                        k4 = 1;
                        signsl = ss2;
                    }
                    k2 = k2 + 1;
                }
            }
        }

        class Listener_Equal implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                store = (JButton) e.getSource();
                vt.add(store);
                cal();

                // 还原开关k1状态
                k1 = 1;

                // 还原开关k2状态
                k2 = 1;

                // 还原开关k3状态
                k3 = 1;

                // 还原开关k4状态
                k4 = 1;

                // 为 7+5=12 +5=17 这种计算做准备
                string1 = result;
            }
        }


        // 监听等于键
        Listener_Equal listener_equal = new Listener_Equal();

        Listener_Signal listener_signal = new Listener_Signal();

        Listener_Clear listener_clear = new Listener_Clear();

        Listener_Point listener_point = new Listener_Point();

        Listener_Number listener_number = new Listener_Number();

        button7.addActionListener(listener_number);
        button8.addActionListener(listener_number);
        button9.addActionListener(listener_number);
        button_Division.addActionListener(listener_signal);
        button4.addActionListener(listener_number);
        button5.addActionListener(listener_number);
        button6.addActionListener(listener_number);
        button_Multiply.addActionListener(listener_signal);
        button1.addActionListener(listener_number);
        button2.addActionListener(listener_number);
        button3.addActionListener(listener_number);
        button_Minus.addActionListener(listener_signal);
        button0.addActionListener(listener_number);
        button_Point.addActionListener(listener_point);
        button_Equal.addActionListener(listener_equal);
        button_Plus.addActionListener(listener_signal);
        clear_Button.addActionListener(listener_clear);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        }

    // 计算逻辑
    public void cal() {
        // 操作数1
        double a2;
        // 操作数2
        double b2;
        // 运算符
        String c = signsl;
        // 运算结果
        double result2 = 0;

        if (c.equals("")) {
            result_TextField.setText("Please input operator");
        } else {
            // 手动处理小数点的问题
            if (string1.equals("."))
                string1 = "0.0";
            if (string2.equals("."))
                string2 = "0.0";
            a2 = Double.valueOf(string1).doubleValue();
            b2 = Double.valueOf(string2).doubleValue();

            if (c.equals("+")) {
                result2 = a2 + b2;
            }
            if (c.equals("-")) {
                result2 = a2 - b2;
            }
            if (c.equals("*")) {
                BigDecimal m1 = new BigDecimal(Double.toString(a2));
                BigDecimal m2 = new BigDecimal(Double.toString(b2));
                result2 = m1.multiply(m2).doubleValue();
            }
            if (c.equals("/")) {
                if (b2 == 0) {
                    result2 = 0;
                } else {
                    result2 = a2 / b2;
                }

            }
            result = ((new Double(result2)).toString());
            result_TextField.setText(result);
        }
    }

    public static  void main(String[] args){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch (Exception e){
            e.printStackTrace();
        }
        Calculator calculator = new Calculator();
    }
}
