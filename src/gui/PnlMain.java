package gui;

import connect.ConnectDB;
import data.UtilityJButton;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PnlMain extends javax.swing.JPanel {

    private static PnlMain instance = new PnlMain();
    
    public static PnlMain getInstance() {
        return instance;
    }
    
    public static PnlMain newInstance() {
        instance = new PnlMain();
        return instance;
    }
    
    private ArrayList<JButton> listBtnNhanVien = new ArrayList<>();
    private ArrayList<JButton> listBtnKhachHang = new ArrayList<>();
    private ArrayList<JButton> listBtnHoaDon = new ArrayList<>();
    private ArrayList<JButton> listBtnQuanAo = new ArrayList<>();
    private ArrayList<JButton> listBtnLichLamViec = new ArrayList<>();
    private ArrayList<JButton> listBtnThongKe = new ArrayList<>();
    
    public PnlMain(){
        initComponents();
        initExtra();
    }
    
    private void initExtra(){
        UtilityJButton.removeFocusPainted(UtilityJButton.getAllJButtons(this));
        addButtonToList();
    }
    
    private void addButtonToList(){
        listBtnNhanVien.add(btnLapHoaDon);
        listBtnNhanVien.add(btnDanhSachDonDatHang);
        listBtnNhanVien.add(btnThongTinNhanVien);
        listBtnNhanVien.add(btnThemNhanVien);
        listBtnNhanVien.add(btnCapNhatNhanVien);
        
        listBtnKhachHang.add(btnCapNhatKhachHang);
        
        listBtnHoaDon.add(btnDanhSachHoaDon);
        
        listBtnQuanAo.add(btnThemQuanAo);
        listBtnQuanAo.add(btnCapNhatQuanAo);
        
        listBtnLichLamViec.add(btnDanhSachLichLamViec);
        
        listBtnThongKe.add(btnThongKeDoanhThu);
        listBtnThongKe.add(btnThongKeKhachHang);
        listBtnThongKe.add(btnThongKeSanPhamThem);
        listBtnThongKe.add(btnThongKeSanPhamBan);
        listBtnThongKe.add(btnThongKeSanPhamHet);
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
        btnThongTinNhanVien = new javax.swing.JButton();
        btnThemNhanVien = new javax.swing.JButton();
        btnCapNhatNhanVien = new javax.swing.JButton();
        btnCapNhatKhachHang = new javax.swing.JButton();
        btnDanhSachHoaDon = new javax.swing.JButton();
        btnThemQuanAo = new javax.swing.JButton();
        btnCapNhatQuanAo = new javax.swing.JButton();
        btnDanhSachLichLamViec = new javax.swing.JButton();
        btnThongKeDoanhThu = new javax.swing.JButton();
        btnThongKeKhachHang = new javax.swing.JButton();
        btnThongKeSanPhamThem = new javax.swing.JButton();
        btnThongKeSanPhamBan = new javax.swing.JButton();
        btnThongKeSanPhamHet = new javax.swing.JButton();
        pnlTitle = new javax.swing.JPanel();
        pnlTitleIcon = new javax.swing.JPanel();
        lblTitleIcon = new javax.swing.JLabel();
        pnlTItleName = new javax.swing.JPanel();
        lblTitleName = new javax.swing.JLabel();
        pnlButton = new javax.swing.JPanel();
        pnlButtonAction = new javax.swing.JPanel();
        btnTrangChu = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnKhachHang = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        btnQuanAo = new javax.swing.JButton();
        btnLich = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        pnlButtonLogOut = new javax.swing.JPanel();
        btnLogOut = new javax.swing.JButton();
        pnlWork = new javax.swing.JPanel();

        btnLapHoaDon.setText("Lập Hóa Đơn");
        btnLapHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLapHoaDonActionPerformed(evt);
            }
        });

        btnDanhSachDonDatHang.setText("Danh Sách Đơn Đặt Hàng");
        btnDanhSachDonDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhSachDonDatHangActionPerformed(evt);
            }
        });

        btnThongTinNhanVien.setText("Thông Tin Nhân Viên");
        btnThongTinNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongTinNhanVienActionPerformed(evt);
            }
        });

        btnThemNhanVien.setText("Thêm Nhân Viên");
        btnThemNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhanVienActionPerformed(evt);
            }
        });

        btnCapNhatNhanVien.setText("Cập Nhật Nhân Viên");
        btnCapNhatNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatNhanVienActionPerformed(evt);
            }
        });

        btnCapNhatKhachHang.setText("Cập Nhật Khách Hàng");
        btnCapNhatKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatKhachHangActionPerformed(evt);
            }
        });

        btnDanhSachHoaDon.setText("Danh Sách Hóa Đơn");
        btnDanhSachHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhSachHoaDonActionPerformed(evt);
            }
        });

        btnThemQuanAo.setText("Thêm Quần Áo");
        btnThemQuanAo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemQuanAoActionPerformed(evt);
            }
        });

        btnCapNhatQuanAo.setText("Cập Nhật Quần Áo");
        btnCapNhatQuanAo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatQuanAoActionPerformed(evt);
            }
        });

        btnDanhSachLichLamViec.setText("Danh Sách Lịch Làm Việc");
        btnDanhSachLichLamViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhSachLichLamViecActionPerformed(evt);
            }
        });

        btnThongKeDoanhThu.setText("Thống Kê Doanh Thu");
        btnThongKeDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeDoanhThuActionPerformed(evt);
            }
        });

        btnThongKeKhachHang.setText("Thống Kê Khách Hàng");
        btnThongKeKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeKhachHangActionPerformed(evt);
            }
        });

        btnThongKeSanPhamThem.setText("Thống Kê Sản Phẩm Đã Nhập");
        btnThongKeSanPhamThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeSanPhamThemActionPerformed(evt);
            }
        });

        btnThongKeSanPhamBan.setText("Thống Kê Sản Phẩm Đã Bán");
        btnThongKeSanPhamBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeSanPhamBanActionPerformed(evt);
            }
        });

        btnThongKeSanPhamHet.setText("Thống Kê Sản Phẩm Đã Hết");
        btnThongKeSanPhamHet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeSanPhamHetActionPerformed(evt);
            }
        });

        setLayout(new java.awt.BorderLayout());

        pnlTitle.setBackground(new java.awt.Color(170, 238, 255));
        pnlTitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        pnlTitle.setPreferredSize(new java.awt.Dimension(1366, 68));
        pnlTitle.setLayout(new java.awt.BorderLayout());

        pnlTitleIcon.setOpaque(false);
        pnlTitleIcon.setLayout(new java.awt.GridBagLayout());
        pnlTitleIcon.add(lblTitleIcon, new java.awt.GridBagConstraints());

        pnlTitle.add(pnlTitleIcon, java.awt.BorderLayout.WEST);

        pnlTItleName.setOpaque(false);
        pnlTItleName.setPreferredSize(new java.awt.Dimension(0, 68));
        pnlTItleName.setLayout(new java.awt.GridBagLayout());

        lblTitleName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblTitleName.setText("PHẦN MỀM QUẢN LÝ CỬA HÀNG BÁN QUẦN ÁO - AMSHOP");
        pnlTItleName.add(lblTitleName, new java.awt.GridBagConstraints());

        pnlTitle.add(pnlTItleName, java.awt.BorderLayout.CENTER);

        add(pnlTitle, java.awt.BorderLayout.NORTH);

        pnlButton.setBackground(new java.awt.Color(119, 238, 255));
        pnlButton.setPreferredSize(new java.awt.Dimension(200, 700));
        pnlButton.setLayout(new javax.swing.BoxLayout(pnlButton, javax.swing.BoxLayout.Y_AXIS));

        pnlButtonAction.setOpaque(false);
        pnlButtonAction.setPreferredSize(new java.awt.Dimension(200, 500));
        pnlButtonAction.setLayout(new java.awt.GridLayout(7, 1));

        btnTrangChu.setBackground(new java.awt.Color(170, 238, 255));
        btnTrangChu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTrangChu.setText("Trang Chủ");
        btnTrangChu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnTrangChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrangChuActionPerformed(evt);
            }
        });
        pnlButtonAction.add(btnTrangChu);

        btnNhanVien.setBackground(new java.awt.Color(170, 238, 255));
        btnNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnNhanVien.setText("Nhân Viên");
        btnNhanVien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });
        pnlButtonAction.add(btnNhanVien);

        btnKhachHang.setBackground(new java.awt.Color(170, 238, 255));
        btnKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnKhachHang.setText("Khách Hàng");
        btnKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });
        pnlButtonAction.add(btnKhachHang);

        btnHoaDon.setBackground(new java.awt.Color(170, 238, 255));
        btnHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnHoaDon.setText("Hóa Đơn");
        btnHoaDon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });
        pnlButtonAction.add(btnHoaDon);

        btnQuanAo.setBackground(new java.awt.Color(170, 238, 255));
        btnQuanAo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnQuanAo.setText("Quần Áo");
        btnQuanAo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnQuanAo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuanAoActionPerformed(evt);
            }
        });
        pnlButtonAction.add(btnQuanAo);

        btnLich.setBackground(new java.awt.Color(170, 238, 255));
        btnLich.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLich.setText("Lịch Làm Việc");
        btnLich.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnLich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLichActionPerformed(evt);
            }
        });
        pnlButtonAction.add(btnLich);

        btnThongKe.setBackground(new java.awt.Color(170, 238, 255));
        btnThongKe.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnThongKe.setText("Thống Kê");
        btnThongKe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });
        pnlButtonAction.add(btnThongKe);

        pnlButton.add(pnlButtonAction);
        pnlButton.add(filler1);

        pnlButtonLogOut.setOpaque(false);
        pnlButtonLogOut.setLayout(new java.awt.GridLayout(1, 1));

        btnLogOut.setBackground(new java.awt.Color(255, 102, 102));
        btnLogOut.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLogOut.setForeground(new java.awt.Color(255, 255, 255));
        btnLogOut.setText("Đăng Xuất");
        btnLogOut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        pnlButtonLogOut.add(btnLogOut);

        pnlButton.add(pnlButtonLogOut);

        add(pnlButton, java.awt.BorderLayout.WEST);

        pnlWork.setLayout(new java.awt.BorderLayout());
        add(pnlWork, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        // TODO add your handling code here:
        ConnectDB.getInstance().disconnectDatabase();
        FrmMain.getInstance().dispose();
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnTrangChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrangChuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTrangChuActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().createPopupForButton(btnNhanVien, listBtnNhanVien);
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().createPopupForButton(btnKhachHang, listBtnKhachHang);
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().createPopupForButton(btnHoaDon, listBtnHoaDon);
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void btnQuanAoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuanAoActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().createPopupForButton(btnQuanAo, listBtnQuanAo);
    }//GEN-LAST:event_btnQuanAoActionPerformed

    private void btnLichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLichActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().createPopupForButton(btnLich, listBtnLichLamViec);
    }//GEN-LAST:event_btnLichActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().createPopupForButton(btnThongKe, listBtnThongKe);
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnLapHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLapHoaDonActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnLapHoaDonActionPerformed

    private void btnDanhSachDonDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhSachDonDatHangActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnDanhSachDonDatHangActionPerformed

    private void btnThongTinNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongTinNhanVienActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnThongTinNhanVienActionPerformed

    private void btnThemNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhanVienActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnThemNhanVienActionPerformed

    private void btnCapNhatNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatNhanVienActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnCapNhatNhanVienActionPerformed

    private void btnCapNhatKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatKhachHangActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnCapNhatKhachHangActionPerformed

    private void btnDanhSachHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhSachHoaDonActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnDanhSachHoaDonActionPerformed

    private void btnThemQuanAoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemQuanAoActionPerformed
        // TODO add your handling code here:
        showPanel(new PnlThemQuanAo());
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnThemQuanAoActionPerformed

    private void btnCapNhatQuanAoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatQuanAoActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnCapNhatQuanAoActionPerformed

    private void btnDanhSachLichLamViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhSachLichLamViecActionPerformed
        // TODO add your handling code here:
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnDanhSachLichLamViecActionPerformed

    private void btnThongKeDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeDoanhThuActionPerformed
        // TODO add your handling code here:
        showPanel(new PnlThongKeDoanhThu());
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnThongKeDoanhThuActionPerformed

    private void btnThongKeKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeKhachHangActionPerformed
        // TODO add your handling code here:
        showPanel(new PnlThongKeKhachHang());
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnThongKeKhachHangActionPerformed

    private void btnThongKeSanPhamThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeSanPhamThemActionPerformed
        // TODO add your handling code here:
        showPanel(new PnlThongKeQuanAoDaNhap());
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnThongKeSanPhamThemActionPerformed

    private void btnThongKeSanPhamBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeSanPhamBanActionPerformed
        // TODO add your handling code here:
        showPanel(new PnlThongKeQuanAoDaBan());
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnThongKeSanPhamBanActionPerformed

    private void btnThongKeSanPhamHetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeSanPhamHetActionPerformed
        // TODO add your handling code here:
        showPanel(new PnlThongKeQuanAoDaHetHang());
        FrmMain.getInstance().resetPopupPanel();
    }//GEN-LAST:event_btnThongKeSanPhamHetActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatKhachHang;
    private javax.swing.JButton btnCapNhatNhanVien;
    private javax.swing.JButton btnCapNhatQuanAo;
    private javax.swing.JButton btnDanhSachDonDatHang;
    private javax.swing.JButton btnDanhSachHoaDon;
    private javax.swing.JButton btnDanhSachLichLamViec;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnLapHoaDon;
    private javax.swing.JButton btnLich;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnQuanAo;
    private javax.swing.JButton btnThemNhanVien;
    private javax.swing.JButton btnThemQuanAo;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnThongKeDoanhThu;
    private javax.swing.JButton btnThongKeKhachHang;
    private javax.swing.JButton btnThongKeSanPhamBan;
    private javax.swing.JButton btnThongKeSanPhamHet;
    private javax.swing.JButton btnThongKeSanPhamThem;
    private javax.swing.JButton btnThongTinNhanVien;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel lblTitleIcon;
    private javax.swing.JLabel lblTitleName;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlButtonAction;
    private javax.swing.JPanel pnlButtonLogOut;
    private javax.swing.JPanel pnlTItleName;
    private javax.swing.JPanel pnlTitle;
    private javax.swing.JPanel pnlTitleIcon;
    private javax.swing.JPanel pnlWork;
    // End of variables declaration//GEN-END:variables

}
