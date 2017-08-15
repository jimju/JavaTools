package com.jim.demo.view;


import com.jim.demo.controller.IModelPanelController;
import com.jim.demo.controller.ModelPanelController1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Administrator on 2016/10/20.
 */
public class ModelPanel extends JPanel {
    private IModelPanelController mController;
    JTextField mTextField1 = new JTextField(10);
    JTextField mTextField2 = new JTextField(10);
    JTextField mTextField3 = new JTextField(39);
    JTextArea mTextArea = new JTextArea(80,60);
    JButton mButton = new JButton("生成");
    String[] mMethods = {"GET","POST","PUT"};
    JComboBox<String> mComboBox;
    public ModelPanel() {
        mController = new ModelPanelController1();
        this.setLayout(new BorderLayout());
        this.add(row1(), BorderLayout.NORTH);
//        this.add(row1(),BorderLayout.NORTH);
        this.add(row2(),BorderLayout.CENTER);
    }



    private JComponent row1(){
        JPanel jp = new JPanel();
        mComboBox = new JComboBox<String>(mMethods);
        BoxLayout boxLayout = new BoxLayout(jp,BoxLayout.X_AXIS);
        jp.setLayout(boxLayout);

        jp.add(mComboBox);
        jp.add(mTextField2);
        jp.add(mTextField3);

        jp.add(mButton);
        jp.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
        mButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = mController.getCode(mComboBox.getSelectedItem().toString(), mTextField2.getText(), mTextField3.getText());
                mTextArea.setText(text);
            }
        });
        return jp;
    }


    private JComponent row2(){
        mTextArea.setEditable(false);
        JScrollPane jsp = new JScrollPane(mTextArea);
        jsp.setBorder(BorderFactory.createEmptyBorder(10,10,10,0));
        return jsp;
    }
}
