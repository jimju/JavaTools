package com.jim.demo.view;

import com.jim.demo.controller.OtherPanelController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Administrator on 2016/10/20.
 */
public class OtherPanel extends JPanel {
    private JTextArea mTextArea;
    private JTextArea mTextField;
    private OtherPanelController mController;

    public OtherPanel() {
        mController = new OtherPanelController();
        setLayout(new BorderLayout());
        this.add(row1(), BorderLayout.NORTH);
        this.add(row2(), BorderLayout.CENTER);
        this.add(row3(), BorderLayout.SOUTH);

    }

    private JComponent row1() {
        JPanel panel = new JPanel(new FlowLayout());
        mTextField = new JTextArea(2, 60);
        panel.add(mTextField);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10));
        return panel;
    }

    private JComponent row2() {
        mTextArea = new JTextArea(80, 60);
        JScrollPane scrollPane = new JScrollPane(mTextArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return scrollPane;
    }

    private JComponent row3() {
        JPanel panel = new JPanel(new FlowLayout());

        JButton button = new JButton("生成Map");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mTextArea.setText(mController.getCode(mTextField.getText()));
            }
        });
        JButton button1 = new JButton("加密");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = mController.getPassword(mTextField.getText());
                mTextArea.setText(text);
            }
        });

        JButton button2 = new JButton("键值对转Url");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = mController.changeUrl(mTextArea.getText());
                mTextField.setText(text);
            }
        });
        JButton button3 = new JButton("键值对转Url代码");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = mController.map2Url(mTextArea.getText());
                mTextField.setText(text);
            }
        });

        JButton button4 = new JButton("Url转map");
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = mController.Url2Map1(mTextField.getText());
                mTextArea.setText(text);
            }
        });

        JButton button5 = new JButton("设置Text");
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = mController.setTextCode(mTextField.getText());
                mTextArea.setText(text);
            }
        });

        panel.add(new Label(" "));
        panel.add(button);
        panel.add(new Label(" "));
        panel.add(button1);
        panel.add(new Label(" "));
        panel.add(button2);
        panel.add(new Label(" "));
        panel.add(button3);
        panel.add(new Label(" "));
        panel.add(button4);
        panel.add(new Label(" "));
        panel.add(button5);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10));
        return panel;
    }

}
