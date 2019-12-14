package gui.popups;

import graph.Graph;
import graph.adapters.GraphAdapter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopClickListener extends MouseAdapter {
    PopUpTest pop;

    public PopClickListener(PopUpTest pop) {
        this.pop = pop;
    }
    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e) {
        pop.show(e.getComponent(), e.getX(), e.getY());

    }
}
