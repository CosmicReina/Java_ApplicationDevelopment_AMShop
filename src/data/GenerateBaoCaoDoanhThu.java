package data;

import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import dao.DAO_ChiTietHoaDon;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GenerateBaoCaoDoanhThu {
    public static boolean createBaoCaoDoanhThu(ArrayList<HoaDon> listHD, LocalDate ngayBatDau, LocalDate ngayKetThuc) throws IOException{
        String baocao_file_path = "files//baoCao//" + "baoCaoDoanhThu.pdf";
        PdfWriter pdfWriter = new PdfWriter(baocao_file_path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        
        String font_path = "files//font//Inconsolata-VariableFont_wdth,wght.ttf";
        pdfDocument.setDefaultPageSize(new PageSize(800, 600));
        document.setMargins(5, 5, 0, 5);
        document.setFont(PdfFontFactory.createFont(font_path, PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED));
        
        double tongDoanhThu = 0;
        for(HoaDon thisHoaDon : listHD){
            ArrayList<ChiTietHoaDon> listCTHD = DAO_ChiTietHoaDon.getAllChiTietHoaDonTheoMaHoaDon(thisHoaDon.getMaHoaDon());
            double doanhThuThanhPhan = 0;
            for(ChiTietHoaDon thisChiTietHoaDon : listCTHD){
                doanhThuThanhPhan += thisChiTietHoaDon.getSoLuong() * thisChiTietHoaDon.getDonGia();
            }
            tongDoanhThu += doanhThuThanhPhan;
        }
        
        Paragraph prgBaoCao = new Paragraph("Báo Cáo Doanh Thu");
        prgBaoCao
                .setFontSize(16)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMargin(0);
        
        Paragraph prgKhoangThoiGian = new Paragraph();
        prgKhoangThoiGian.add(new Text("Thống kê từ: ").setBold());
        prgKhoangThoiGian.add(new Text(FormatLocalDate.fromLocalDate(ngayBatDau)));
        prgKhoangThoiGian.add(new Text(" đến ").setBold());
        prgKhoangThoiGian.add(new Text(FormatLocalDate.fromLocalDate(ngayKetThuc)));
        prgKhoangThoiGian.setMarginLeft(50);
        
        Paragraph prgTongHoaDon = new Paragraph();
        prgTongHoaDon.add(new Text("Tổng số hóa đơn: ").setBold());
        prgTongHoaDon.add(new Text(Integer.toString(listHD.size())));
        prgTongHoaDon.setMarginLeft(50);
        
        Paragraph prgTongDoanhThu = new Paragraph();
        prgTongDoanhThu.add(new Text("Tổng doanh thu: ").setBold());
        prgTongDoanhThu.add(new Text(FormatDouble.toMoney(tongDoanhThu)));
        prgTongDoanhThu.setMarginLeft(50);
        
        Paragraph prgBaoCaoDoanhThu = new Paragraph("Danh Sách Hóa Đơn Thống Kê");
        prgBaoCaoDoanhThu
                .setFontSize(16)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMargin(0);
        
        float[] tblHeaderSize = {150, 150, 150, 150, 150};
        String[] tblHeaderList = {
            "Mã Hóa Đơn",
            "Tên Nhân Viên",
            "Tên Khách Hàng",
            "Thời Gian Lập Đơn",
            "Tổng Tiền"
        };
        
        Table tblDetail = new Table(tblHeaderSize);
        for(String thisString : tblHeaderList) {
                tblDetail.addCell(new Paragraph(thisString).setBold().setTextAlignment(TextAlignment.CENTER));
        }

        for(HoaDon thisHoaDon : listHD){
            ArrayList<ChiTietHoaDon> listCTHD = DAO_ChiTietHoaDon.getAllChiTietHoaDonTheoMaHoaDon(thisHoaDon.getMaHoaDon());
            double doanhThuThanhPhan = 0;
            for(ChiTietHoaDon thisChiTietHoaDon : listCTHD){
                doanhThuThanhPhan += thisChiTietHoaDon.getSoLuong() * thisChiTietHoaDon.getDonGia();
            }
            tblDetail.addCell(new Paragraph(thisHoaDon.getMaHoaDon()));
            tblDetail.addCell(new Paragraph(thisHoaDon.getNhanVien().getHoTen()));
            tblDetail.addCell(new Paragraph(thisHoaDon.getKhachHang().getHoTen()));
            tblDetail.addCell(new Paragraph(FormatLocalDateTime.toFormattedLocalDateTime(thisHoaDon.getThoiGianTao())));
            tblDetail.addCell(new Paragraph(FormatDouble.toMoney(doanhThuThanhPhan)));
        }
        tblDetail
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setMargin(0);
        
        document.add(prgBaoCao);
        document.add(prgKhoangThoiGian);
        document.add(prgTongHoaDon);
        document.add(prgTongDoanhThu);
        document.add(prgBaoCaoDoanhThu);
        document.add(tblDetail);
        
        document.close();
        return true;
    }
}
