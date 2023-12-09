package gui_old;

public class PnlTrangChu extends javax.swing.JPanel {
    
    private static PnlTrangChu instance = new PnlTrangChu();

    public static PnlTrangChu getInstance() {
        return instance;
    }
    
    public static PnlTrangChu newInstance() {
        instance = new PnlTrangChu();
        return instance;
    }

    public PnlTrangChu() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIcon = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridBagLayout());

        lblIcon.setIcon(new javax.swing.ImageIcon("D:\\08_Project\\NetBeans\\Java_AMShop\\files\\hinhAnh\\AM.png")); // NOI18N
        add(lblIcon, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblIcon;
    // End of variables declaration//GEN-END:variables

}
