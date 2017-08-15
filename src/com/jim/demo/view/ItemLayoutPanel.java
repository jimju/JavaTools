package com.jim.demo.view;

import com.jim.demo.controller.ItemLayoutController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Administrator on 2016/11/7.
 */
public class ItemLayoutPanel extends JPanel {
    private ItemLayoutController mController;
    private JTextField mTextField,mTextField1;
    private JTextArea mTextArea,mTextArea1;
    private JButton mButton1,mButton2,mButton3,mButton4,mButton5;

    public ItemLayoutPanel() {
        mController = new ItemLayoutController();
        mButton1 = new JButton("单行信息型");
        mButton2 = new JButton("单行详细型");
        mButton3 = new JButton("单行输入型");

        mTextField = new JTextField(60);
        mTextField1 = new JTextField(60);
        mTextArea = new JTextArea(80,80);
        mTextArea1 = new JTextArea(80,80);

        setLayout(new BorderLayout());
        add(row0(),BorderLayout.NORTH);
        add(row2(),BorderLayout.CENTER);
        add(row1(),BorderLayout.SOUTH);
    }

    public JComponent row2(){
        JScrollPane scrollPane = new JScrollPane(mTextArea);
        JScrollPane scrollPane1 = new JScrollPane(mTextArea1);
        scrollPane.setBorder(new TitledBorder("布局代码"));
        scrollPane1.setBorder(new TitledBorder("Java代码"));
        JPanel jPanel = new JPanel();
        jPanel.add(scrollPane);
        jPanel.add(scrollPane1);
        BoxLayout layout = new BoxLayout(jPanel,BoxLayout.X_AXIS);
        jPanel.setLayout(layout);
        return jPanel;
    }
    public JComponent row0(){
        JPanel jPanel = new JPanel();
        jPanel.add(mTextField);
        jPanel.add(mTextField1);
        BoxLayout layout = new BoxLayout(jPanel,BoxLayout.Y_AXIS);
        jPanel.setLayout(layout);
        jPanel.setBorder(new TitledBorder("文字和Id要以,分开"));
        return jPanel;
    }

    public JComponent row1(){
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(mButton1);
        panel.add(mButton2);
        panel.add(mButton3);
        mButton2.addActionListener(al2);
        mButton1.addActionListener(al1);
        return panel;
    }


    private ActionListener al1 = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = mTextField.getText().trim();
            String text1 = mTextField1.getText().trim();
            String[] tags = text.split(",");
            String[] ids = text1.split(",");
            if (ids.length != tags.length){
                JOptionPane.showConfirmDialog(mButton2,"ID 和 标识的长度不一致 \n id长度:" + ids.length + "\n 标识的长度:" + tags.length ,"提示",JOptionPane.DEFAULT_OPTION);
            }else {
                String layout = mController.productSingleLineInfo1(tags,ids).getResult1();
                String java = mController.productSingleLineInfo1(tags,ids).getResult2();
                mTextArea.setText(layout);
                mTextArea1.setText(java);
            }
        }
    };

 private ActionListener al2 = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = mTextField.getText().trim();
            String text1 = mTextField1.getText().trim();
            String[] tags = text.split(",");
            String[] ids = text1.split(",");
            if (ids.length != tags.length){
                JOptionPane.showConfirmDialog(mButton2,"ID 和 标识的长度不一致 \n id长度:" + ids.length + "\n 标识的长度:" + tags.length ,"提示",JOptionPane.DEFAULT_OPTION);
            }else {
                String code = mController.productSingleLineInput(tags,ids).getResult2();
                String layout = mController.productSingleLineInput(tags,ids).getResult1();
                mTextArea.setText(layout);
                mTextArea1.setText(code);
            }
        }
    };

}
