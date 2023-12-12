package gui_old;

import data.UtilityJButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class FrmMain extends javax.swing.JFrame {
    
    private static FrmMain instance = new FrmMain();

    public static FrmMain getInstance() {
        return instance;
    }
    
    public static FrmMain newInstance() {
        instance = new FrmMain();
        return instance;
    }

    public JPanel getPnlMain() {
        return pnlMain;
    }

    public void setPnlMain(JPanel pnlMain) {
        this.pnlMain = pnlMain;
    }

    public JPanel getPnlModal() {
        return pnlModal;
    }

    public void setPnlModal(JPanel pnlModal) {
        this.pnlModal = pnlModal;
    }

    public JPanel getPnlPopup() {
        return pnlPopup;
    }

    public void setPnlPopup(JPanel pnlPopup) {
        this.pnlPopup = pnlPopup;
    }

    public FrmMain() {
        initComponents();
        initExtra();
    }
    
    private void initExtra(){
        setLocationRelativeTo(null);
        initiateLayeredPane();
    }
    
    private void initiateLayeredPane(){
        pnlMain.setBounds(0, 0, 1366, 768);
        pnlMain.setLayout(new BorderLayout());
        pnlMain.add(PnlMain.newInstance(), BorderLayout.CENTER);
        lypMain.add(pnlMain, Integer.valueOf(1));
        
        pnlPopup = null;
        pnlModal = null;
    }
    
    public void showPopup(JPanel panel, int x, int y){
        lypMain.remove(panel);
        lypMain.revalidate();
        lypMain.repaint();
        lypMain.add(panel, Integer.valueOf(2));
        panel.setLocation(x, y);
    }
    
    public void createPopupForButton(JButton button, ArrayList<JButton> listAddedButton){
        if(pnlPopup == null){
            createPopup(button, listAddedButton);
        }
        else{
            resetPopupPanel();
        }
    }
    
    public void resetPopupPanel(){
        lypMain.remove(pnlPopup);
        lypMain.revalidate();
        lypMain.repaint();
        pnlPopup = null;
    }
    
    public void createPopup(JButton button, ArrayList<JButton> listAddedButton){
        int listSize = listAddedButton.size();
        int popUpHeight = 40*listSize;
        pnlPopup = new JPanel();
        pnlPopup.revalidate();
        pnlPopup.repaint();
        pnlPopup.setBounds(0, 0, 300, popUpHeight);
        pnlPopup.setLayout(new GridLayout(listSize, 1));
        for(JButton thisButton : listAddedButton) pnlPopup.add(thisButton);
        int x = button.getLocation().x + button.getSize().width;
        int y = button.getLocation().y + button.getSize().height - popUpHeight/3;
        showPopup(pnlPopup, x, y);
        UtilityJButton.removeFocusPainted(UtilityJButton.getAllJButtons(pnlPopup));
        UtilityJButton.changeBackgroundColor(UtilityJButton.getAllJButtons(pnlPopup), "#77EEFF");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlPopup = new javax.swing.JPanel();
        pnlModal = new javax.swing.JPanel();
        lypMain = new javax.swing.JLayeredPane();

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlPopupLayout = new javax.swing.GroupLayout(pnlPopup);
        pnlPopup.setLayout(pnlPopupLayout);
        pnlPopupLayout.setHorizontalGroup(
            pnlPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pnlPopupLayout.setVerticalGroup(
            pnlPopupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlModalLayout = new javax.swing.GroupLayout(pnlModal);
        pnlModal.setLayout(pnlModalLayout);
        pnlModalLayout.setHorizontalGroup(
            pnlModalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        pnlModalLayout.setVerticalGroup(
            pnlModalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        javax.swing.GroupLayout lypMainLayout = new javax.swing.GroupLayout(lypMain);
        lypMain.setLayout(lypMainLayout);
        lypMainLayout.setHorizontalGroup(
            lypMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE)
        );
        lypMainLayout.setVerticalGroup(
            lypMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );

        getContentPane().add(lypMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane lypMain;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlModal;
    private javax.swing.JPanel pnlPopup;
    // End of variables declaration//GEN-END:variables

}
