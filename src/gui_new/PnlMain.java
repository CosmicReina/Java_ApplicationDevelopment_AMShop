package gui_new;

import data.UtilityJButton;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PnlMain extends javax.swing.JPanel {
    
    private static PnlMain instance = new PnlMain();
    
    private ArrayList<JButton> listBtnNhanVien = new ArrayList<>();
    private ArrayList<JButton> listBtnKhachHang = new ArrayList<>();
    private ArrayList<JButton> listBtnHoaDon = new ArrayList<>();
    private ArrayList<JButton> listBtnQuanAo = new ArrayList<>();
    private ArrayList<JButton> listBtnLichLamViec = new ArrayList<>();
    private ArrayList<JButton> listBtnThongKe = new ArrayList<>();

    public static PnlMain getInstance() {
        return instance;
    }
    
    public static PnlMain newInstance() {
        instance = new PnlMain();
        return instance;
    }

    public PnlMain() {
        initComponents();
        initExtra();
    }
    
    private void initExtra(){
        UtilityJButton.removeFocusPainted(UtilityJButton.getAllJButtons(this));
        addBtnToList();
    }
    
    private void addBtnToList(){
        listBtnNhanVien.add(btnLapHoaDon);
        listBtnNhanVien.add(btnDanhSachDonDatHang);
        listBtnNhanVien.add(btnThemNhanVien);
        listBtnNhanVien.add(btnCapNhatNhanVien);
        listBtnNhanVien.add(btnThongTinNhanVien);
    }
    
    public void showPanel(JPanel panel){
        pnlWork.removeAll();
    	pnlWork.revalidate();
    	pnlWork.repaint();
    	pnlWork.setLayout(new BorderLayout());
    	pnlWork.add(panel, BorderLayout.CENTER);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLapHoaDon = new javax.swing.JButton();
        btnDanhSachDonDatHang = new javax.swing.JButton();
        btnThemNhanVien = new javax.swing.JButton();
        btnCapNhatNhanVien = new javax.swing.JButton();
        btnThongTinNhanVien = new javax.swing.JButton();
        pnlTitle = new javax.swing.JPanel();
        pnlTitleIcon = new javax.swing.JPanel();
        lblTitleIcon = new javax.swing.JLabel();
        pnlTitleName = new javax.swing.JPanel();
        lblTitleName = new javax.swing.JLabel();
        pnlButton = new javax.swing.JPanel();
        pnlBtnAction = new javax.swing.JPanel();
        btnTrangChu = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        btnQuanAo = new javax.swing.JButton();
        btnLichLamViec = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        pnlBtnLogOut = new javax.swing.JPanel();
        btnDangXuat = new javax.swing.JButton();
        pnlWork = new javax.swing.JPanel();

        btnLapHoaDon.setText("Lập Hóa Đơn");
        btnLapHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLapHoaDonActionPerformed(evt);
            }
        });

        btnDanhSachDonDatHang.setText("Danh Sách Đơn Đặt Hàng");
        btnDanhSachDonDatHang.setToolTipText("");
        btnDanhSachDonDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhSachDonDatHangActionPerformed(evt);
            }
        });

        btnThemNhanVien.setText("Thêm Nhân Viên");
        btnThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVienActionPerformed(evt);
            }
        });

        btnCapNhatNhanVien.setText("Cập Nhật Thông Tin Nhân Viên");
        btnCapNhatNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatNhanVienActionPerformed(evt);
            }
        });

        btnThongTinNhanVien.setText("Xem Thông Tin");
        btnThongTinNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongTinNhanVienActionPerformed(evt);
            }
        });

        setLayout(new java.awt.BorderLayout());

        pnlTitle.setBackground(new java.awt.Color(170, 238, 255));
        pnlTitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlTitle.setPreferredSize(new java.awt.Dimension(1366, 68));
        pnlTitle.setLayout(new java.awt.BorderLayout());

        pnlTitleIcon.setOpaque(false);
        pnlTitleIcon.setLayout(new java.awt.GridBagLayout());
        pnlTitleIcon.add(lblTitleIcon, new java.awt.GridBagConstraints());

        pnlTitle.add(pnlTitleIcon, java.awt.BorderLayout.WEST);

        pnlTitleName.setOpaque(false);
        pnlTitleName.setLayout(new java.awt.GridBagLayout());

        lblTitleName.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblTitleName.setText("PHẦN MỀM QUẢN LÝ CỬA HÀNG BÁN QUẦN ÁO - AMSHOP");
        pnlTitleName.add(lblTitleName, new java.awt.GridBagConstraints());

        pnlTitle.add(pnlTitleName, java.awt.BorderLayout.CENTER);

        add(pnlTitle, java.awt.BorderLayout.NORTH);

        pnlButton.setBackground(new java.awt.Color(119, 238, 255));
        pnlButton.setPreferredSize(new java.awt.Dimension(200, 700));
        pnlButton.setLayout(new javax.swing.BoxLayout(pnlButton, javax.swing.BoxLayout.Y_AXIS));

        pnlBtnAction.setOpaque(false);
        pnlBtnAction.setPreferredSize(new java.awt.Dimension(200, 500));
        pnlBtnAction.setLayout(new java.awt.GridLayout(7, 1));

        btnTrangChu.setBackground(new java.awt.Color(170, 238, 255));
        btnTrangChu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTrangChu.setText("Trang Chủ");
        btnTrangChu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangChuActionPerformed(evt);
            }
        });
        pnlBtnAction.add(btnTrangChu);

        btnNhanVien.setBackground(new java.awt.Color(170, 238, 255));
        btnNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnNhanVien.setText("Nhân Viên");
        btnNhanVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });
        pnlBtnAction.add(btnNhanVien);

        btnKhachHang.setBackground(new java.awt.Color(170, 238, 255));
        btnKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnKhachHang.setText("Khách Hàng");
        btnKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });
        pnlBtnAction.add(btnKhachHang);

        btnHoaDon.setBackground(new java.awt.Color(170, 238, 255));
        btnHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnHoaDon.setText("Hóa Đơn");
        btnHoaDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });
        pnlBtnAction.add(btnHoaDon);

        btnQuanAo.setBackground(new java.awt.Color(170, 238, 255));
        btnQuanAo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnQuanAo.setText("Quần Áo");
        btnQuanAo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnQuanAo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanAoActionPerformed(evt);
            }
        });
        pnlBtnAction.add(btnQuanAo);

        btnLichLamViec.setBackground(new java.awt.Color(170, 238, 255));
        btnLichLamViec.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLichLamViec.setText("Lịch Làm Việc");
        btnLichLamViec.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnLichLamViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLichLamViecActionPerformed(evt);
            }
        });
        pnlBtnAction.add(btnLichLamViec);

        btnThongKe.setBackground(new java.awt.Color(170, 238, 255));
        btnThongKe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThongKe.setText("Thống Kê");
        btnThongKe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });
        pnlBtnAction.add(btnThongKe);

        pnlButton.add(pnlBtnAction);
        pnlButton.add(filler1);

        pnlBtnLogOut.setOpaque(false);
        pnlBtnLogOut.setLayout(new java.awt.GridLayout(1, 1));

        btnDangXuat.setBackground(new java.awt.Color(255, 153, 153));
        btnDangXuat.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnDangXuat.setText("Đăng Xuất");
        btnDangXuat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        pnlBtnLogOut.add(btnDangXuat);

        pnlButton.add(pnlBtnLogOut);

        add(pnlButton, java.awt.BorderLayout.WEST);

        pnlWork.setBackground(new java.awt.Color(68, 136, 255));

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

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().dispose();
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().createPopupForButton(btnNhanVien, listBtnNhanVien);
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void btnQuanAoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanAoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuanAoActionPerformed

    private void btnLichLamViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLichLamViecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLichLamViecActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnLapHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLapHoaDonActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnLapHoaDonActionPerformed

    private void btnDanhSachDonDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhSachDonDatHangActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnDanhSachDonDatHangActionPerformed

    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienActionPerformed
        // TODO add your handling code here:
        showPanel(PnlThemNhanVien.newInstance());
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnThemNhanVienActionPerformed

    private void btnCapNhatNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatNhanVienActionPerformed
        // TODO add your handling code here:
        showPanel(PnlCapNhatNhanVien.newInstance());
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnCapNhatNhanVienActionPerformed

    private void btnThongTinNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongTinNhanVienActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnThongTinNhanVienActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatNhanVien;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnDanhSachDonDatHang;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnLapHoaDon;
    private javax.swing.JButton btnLichLamViec;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnQuanAo;
    private javax.swing.JButton btnThemNhanVien;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnThongTinNhanVien;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel lblTitleIcon;
    private javax.swing.JLabel lblTitleName;
    private javax.swing.JPanel pnlBtnAction;
    private javax.swing.JPanel pnlBtnLogOut;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JPanel pnlTitleIcon;
    private javax.swing.JPanel pnlTitleName;
    private javax.swing.JPanel pnlWork;
    // End of variables declaration//GEN-END:variables

}
