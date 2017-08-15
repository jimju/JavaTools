package com.jim.demo.view;

import com.jim.demo.controller.JsonPaneController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Administrator on 2016/10/21.
 */
public class JsonPanel extends JPanel{
    private JTextArea mTextArea1,mTextArea2;
    private JTextField mTextField;
    private JComboBox<String> mComboBox;
    private JButton mButton,mButton2,mButton3,mButton4,mButton5;
    private JsonPaneController mController;
    private String[] items = {"public","private"};
    public JsonPanel() {
        mController = new JsonPaneController();
        setLayout(new BorderLayout());
        mTextArea1 = new JTextArea(60,37);
        mTextArea2 = new JTextArea(60,37);
        mButton = new JButton("转换");
        mButton2 = new JButton("对比");
        mButton3 = new JButton("create");
        mButton4 = new JButton("insert");
        mButton5 = new JButton("其他");
        mTextField = new JTextField(10);
        mTextArea1.setLineWrap(true);
        mTextArea2.setLineWrap(true);
        mComboBox = new JComboBox<String>(items);

        add(row1(),BorderLayout.NORTH);
        add(row2(),BorderLayout.CENTER);

//        setSize();
    }

    private JComponent row1() {
        JPanel jp = new JPanel(new FlowLayout());
        jp.add(new Label(" Type: "));
        jp.add(mComboBox);
        jp.add(new Label("     ClassName: "));
        jp.add(mTextField);
        jp.add(new Label("  "));
        jp.add(mButton);
        jp.add(mButton2);
        jp.add(mButton3);
        jp.add(mButton4);
        jp.add(mButton5);
        mButton.addActionListener(beanAction);
        mButton2.addActionListener(compareAction);
        mButton3.addActionListener(createAction);
        mButton4.addActionListener(insertAction);
        mButton5.addActionListener(otherAction);
        return jp;
    }

    private JComponent row2(){
        JPanel jp = new JPanel();
        BoxLayout layout = new BoxLayout(jp,BoxLayout.X_AXIS);
        jp.setLayout(layout);
        JScrollPane pane1 = new JScrollPane(jp);
        mTextArea1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mTextArea2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jp.add(mTextArea1);
        jp.add(mTextArea2);
        return pane1;
    }

    /**
     * 转成java bean
     */
    private ActionListener beanAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String code = mController.transJson(mComboBox.getSelectedItem().toString(),mTextArea1.getText(),mTextField.getText());
            mTextArea2.setText(code);
        }
    };

    /**
     * 对比json
     */
    private ActionListener compareAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String json1 = mTextArea1.getText();
            String json2 = mTextArea2.getText();
            String code = mController.compareJson(json1,json2);
            JOptionPane.showConfirmDialog(mButton2,code,"结果",JOptionPane.DEFAULT_OPTION);
        }
    };

    /**
     * 创建数据表
     */
    private ActionListener createAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String code = mController.transSQL(mTextArea1.getText(), mTextField.getText());
            mTextArea2.setText(code);
        }
    };

    /**
     * 插入数据数据
     */
    private ActionListener insertAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String code = mController.transInsert(mTextArea1.getText(), mTextField.getText());
            mTextArea2.setText(code);
        }
    };

    /**
     *
     */
    private ActionListener otherAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String code = mController.other1(mTextArea1.getText(),mTextField.getText());
            mTextArea2.setText(code);
        }
    };
}
