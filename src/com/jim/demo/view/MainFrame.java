package com.jim.demo.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Administrator on 2016/10/20.
 */
public class MainFrame extends JFrame {

    private JPanel mainPane = new JPanel(new BorderLayout());
    public MainFrame() throws HeadlessException {
        setTitle("工具程序");
        initGUI();
        setSize(900,680);
    }

    private void initGUI() {
        this.mainPane.add(createMainTabs(), BorderLayout.CENTER);
        mainPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        this.getContentPane().add(mainPane);

    }

    private JComponent createMainTabs() {
        JTabbedPane tbs = new JTabbedPane();
       /* JScrollPane jScrollPane = new JScrollPane(new JTextArea(100, 50));
        tbs.add(jScrollPane,"测试布局");*/
        tbs.add(new ModelPanel(),"Model生成");
        tbs.add(new JsonPanel(),"Json生成bean");
        tbs.add(new ItemLayoutPanel(),"Item布局代码");
        tbs.add(new IbennewPanel(),"BN规则");
        tbs.add(new OtherPanel(),"其他");
        return tbs;
    }
}
