package data;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Slider extends JPanel {

    public Slider() {
        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Dimension size = new Dimension(this.getWidth(), this.getHeight());
    }

    public void nextSlidPanel(Component ShowPanel) {
        nextSlidPanel(10, 40, ShowPanel, true);
    }

    public void nextSlidPanel(int SpeedPanel, Component ShowPanel) {
        nextSlidPanel(SpeedPanel, 40, ShowPanel, true);
    }

    public void nextSlidPanel(int SpeedPanel, Component ShowPanel,
            boolean DirectionMove) {
        nextSlidPanel(SpeedPanel, 40, ShowPanel, DirectionMove);
    }

    public void nextSlidPanel(int SpeedPanel, int TimeSpeed,
            Component ShowPanel, boolean DirectionMove) {

        Component currentComp = getCurrentComponent(this);
        
        ShowPanel.setVisible(true);
        libSlideListener sl = new libSlideListener(SpeedPanel,
                currentComp, ShowPanel, DirectionMove);
        Timer t = new Timer(TimeSpeed, sl);
        sl.timer = t;
        t.start();
    }

    public Component getCurrentComponent(Container parent) {
        Component comp = null;
        int n = parent.getComponentCount();
        for (int i = 0; i < n; i++) {
            comp = parent.getComponent(i);
            if (comp.isVisible()) {
                return comp;
            }
        }
        return comp;
    }

    public String getCurrentComponentShow(Container parent) {
        String PanelName = null;
        Component comp = null;
        int n = parent.getComponentCount();
        for (int i = 0; i < n; i++) {
            comp = parent.getComponent(i);
            if (comp.isVisible()) {
                PanelName = comp.getName();
                return PanelName;
            }
        }
        return PanelName;
    }

    public class libSlideListener implements ActionListener {

        Component HidePanel;
        
        Component ShowPanel;
        int steps;
        int step = 0;
        Timer timer;
        boolean isNext;

        public libSlideListener(int steps, Component HidePanel, Component ShowPanel, boolean isNext) {
            this.steps = steps;
            this.HidePanel = HidePanel;
            this.ShowPanel = ShowPanel;
            this.isNext = isNext;
        }

        public void actionPerformed(ActionEvent e) {

             Rectangle bounds = HidePanel.getBounds();
            int shift = 550 / (steps);
            if (!isNext) {
                HidePanel.setLocation(bounds.x - shift, bounds.y);
                ShowPanel.setLocation(1045 - shift + 550, 0);

            } else {
                HidePanel.setLocation(bounds.x + shift, bounds.y);
                ShowPanel.setLocation(495 + shift - 550, 0);

            }
//             if (!isNext) {
//                HidePanel.setLocation(bounds.x - shift, bounds.y);
//                ShowPanel.setLocation(bounds.x - shift + 550, bounds.y);
//
//            } else {
//                HidePanel.setLocation(bounds.x + shift, bounds.y);
//                ShowPanel.setLocation(bounds.x + shift - 550, bounds.y);
//
//            }

            repaint();
            step++;

            if (step == steps) {
                timer.stop();
                HidePanel.setVisible(false);
                

            }

        }
    }

    public void refresh() {
        revalidate();
        repaint();
    }
}
