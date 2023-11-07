package gui;

public class PnlMain extends javax.swing.JPanel {
    
    private static PnlMain instance = new PnlMain();
    
    public static PnlMain getInstance() {
        return instance;
    }
    
    public static PnlMain newInstance() {
        instance = new PnlMain();
        return instance;
    }

    public PnlMain() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTitle = new javax.swing.JPanel();
        pnlButton = new javax.swing.JPanel();
        pnlWork = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        pnlTitle.setBackground(new java.awt.Color(170, 238, 255));
        pnlTitle.setPreferredSize(new java.awt.Dimension(1366, 68));

        javax.swing.GroupLayout pnlTitleLayout = new javax.swing.GroupLayout(pnlTitle);
        pnlTitle.setLayout(pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE)
        );
        pnlTitleLayout.setVerticalGroup(
            pnlTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 68, Short.MAX_VALUE)
        );

        add(pnlTitle, java.awt.BorderLayout.NORTH);

        pnlButton.setBackground(new java.awt.Color(119, 238, 255));
        pnlButton.setPreferredSize(new java.awt.Dimension(200, 700));

        javax.swing.GroupLayout pnlButtonLayout = new javax.swing.GroupLayout(pnlButton);
        pnlButton.setLayout(pnlButtonLayout);
        pnlButtonLayout.setHorizontalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        pnlButtonLayout.setVerticalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        add(pnlButton, java.awt.BorderLayout.WEST);

        javax.swing.GroupLayout pnlWorkLayout = new javax.swing.GroupLayout(pnlWork);
        pnlWork.setLayout(pnlWorkLayout);
        pnlWorkLayout.setHorizontalGroup(
            pnlWorkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1166, Short.MAX_VALUE)
        );
        pnlWorkLayout.setVerticalGroup(
            pnlWorkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        add(pnlWork, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JPanel pnlWork;
    // End of variables declaration//GEN-END:variables

}
