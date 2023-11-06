package frame;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: caochaojie
 * @Date: 2023-11-06 14:22
 */
public class FrameTest {

    public static void main(String[] args) {

        JPanel outerPanel = new JPanel(new FlowLayout());
        JPanel upPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel downPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        upPanel.setBackground(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        JPanel upLeftPanel = new JPanel();
        upLeftPanel.setBackground(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        JPanel upRightPanel = new JPanel();
        upRightPanel.setBackground(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        JFrame frame = new JFrame("My Window");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(new JLabel("Hello World!"));
        BorderLayout flowLayout = new BorderLayout();
        frame.setLayout(flowLayout);
        setFrameSize(upPanel, leftPanel, upLeftPanel, upRightPanel, frame);
        outerPanel.add(upPanel);
        outerPanel.add(leftPanel);
        outerPanel.add(downPanel);
        outerPanel.add(rightPanel);
        frame.getContentPane().add(outerPanel);
    }

    public static void setFrameSize(JPanel upPanel, JPanel leftPanel, JPanel upLeftPanel, JPanel upRightPanel, Frame frame) {
        Dimension size = frame.getSize();
        int width = size.width; // 800
        int height = size.height; // 600

        int panelWidth = width / 4; // 每个 JPanel 宽 200
        int panelHeight = height / 2; // 每个 JPanel 高 300
        upPanel.setBounds(0, 0, panelWidth, panelHeight);
        leftPanel.setBounds(0, panelHeight, panelWidth, panelHeight);
        upLeftPanel.setBounds(0, 0, panelWidth / 2, panelHeight / 2);
        upRightPanel.setBounds(panelWidth / 2, 0, panelWidth / 2, panelHeight / 2);
    }
}
