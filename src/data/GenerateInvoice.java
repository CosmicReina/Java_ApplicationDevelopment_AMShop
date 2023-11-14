package data;

import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.borders.DottedBorder;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class GenerateInvoice {
    public static void createAMShopInvoice(HoaDon hoaDon, ArrayList<ChiTietHoaDon> list, double tongTien, double tienKhachDua) throws IOException{
        String invoice_file_path = "files//hoaDon//" + hoaDon.getMaHoaDon() + ".pdf";
        PdfWriter pdfWriter = new PdfWriter(invoice_file_path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        
        String font_path = "files//font//Inconsolata-VariableFont_wdth,wght.ttf";
        
        pdfDocument.setDefaultPageSize(new PageSize(200, 800));
        document.setMargins(5, 5, 0, 5);
        document.setFont(PdfFontFactory.createFont(font_path, PdfFontFactory.EmbeddingStrategy.FORCE_EMBEDDED));
        
        Paragraph prgShop = new Paragraph();
        Text textShopHeader = new Text("Store AM Shop");
        textShopHeader
                .setFontSize(12);
        Text textShopURL = new Text("www.amshop.com");
        textShopURL
                .setFontSize(6);
        prgShop
                .add(textShopHeader)
                .add("\n")
                .add(textShopURL)
                .setTextAlignment(TextAlignment.CENTER)
                .setFixedLeading(9f);

                //Store Detail
        Paragraph prgShopDetail = new Paragraph();
        Text textShopAddressHeader = new Text("Địa chỉ: ");
        textShopAddressHeader
                .setBold();
        Text textShopAddressDetail = new Text(hoaDon.getCuaHang().getDiaChi());
        Text textShopPhoneHeader = new Text("Số điện thoại: ");
        textShopPhoneHeader
                .setBold();
        Text textShopPhoneDetail = new Text(hoaDon.getCuaHang().getSoDienThoai());
        prgShopDetail
                .add(textShopAddressHeader)
                .add(textShopAddressDetail)
                .add("\n")
                .add(textShopPhoneHeader)
                .add(textShopPhoneDetail)
                .setFixedLeading(9f)
                .setFontSize(6)
                .setMarginTop(0f);

                //Div
        Div div_01 = new Div();
        div_01
                .setHeight(0.5f)
                .setWidth(UnitValue.createPercentValue(50f))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setBorderTop(new DashedBorder(0.5f))
                .setMargin(0);

                //Invoice
        Paragraph prgInvoice = new Paragraph("Hóa đơn bán hàng");
        prgInvoice
                .setFontSize(16)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMargin(0);

                //Invoice Header
        float[] tblInvoiceHeaderSize = {90, 90};
//        Table tblInvoiceHeader = new Table(tblInvoiceHeaderSize);
        Paragraph prgInvoiceID = new Paragraph();
        Text textInvoiceIDHeader = new Text("Mã hóa đơn: ");
        textInvoiceIDHeader
                .setBold();
        Text textInvoiceIDDetail = new Text(hoaDon.getMaHoaDon());
        prgInvoiceID
                .add(textInvoiceIDHeader)
                .add(textInvoiceIDDetail)
                .setFontSize(6);

        Paragraph prgInvoiceDate = new Paragraph();
        Text textInvoiceDateHeader = new Text("Thời gian lập đơn: ");
        textInvoiceDateHeader
                .setBold();
        Text textInvoiceDateDetail = new Text(hoaDon.getThoiGianTao().toString());
        prgInvoiceDate
                .add(textInvoiceDateHeader)
                .add(textInvoiceDateDetail)
                .setFontSize(6);

        Paragraph prgInvoiceMaker = new Paragraph();
        Text textInvoiceMakerHeader = new Text("Nhân viên: ");
        textInvoiceMakerHeader
                .setBold();
        Text textInvoiceMakerDetail = new Text(hoaDon.getNhanVien().getHoTen());
        prgInvoiceMaker
                .add(textInvoiceMakerHeader)
                .add(textInvoiceMakerDetail)
                .setFontSize(6);

        Paragraph prgInvoiceCustomer = new Paragraph();
        Text textInvoiceCustomerHeader = new Text("Khách hàng: ");
        textInvoiceCustomerHeader
                .setBold();
        Text textInvoiceCustomerDetail = new Text(hoaDon.getKhachHang().getHoTen());
        prgInvoiceCustomer
                .add(textInvoiceCustomerHeader)
                .add(textInvoiceCustomerDetail)
                .setFontSize(6);

//        tblInvoiceHeader
//                .addCell(new Cell(1,2)
//                                .add(prgInvoiceID)
//                                .setBorder(Border.NO_BORDER))	
//                .addCell(new Cell(1,2)
//                                .add(prgInvoiceDate)
//                                .setBorder(Border.NO_BORDER))
//                .addCell(new Cell(1,2)
//                                .add(prgInvoiceMaker)
//                                .setBorder(Border.NO_BORDER))
//                .addCell(new Cell(1,2)
//                                .add(prgInvoiceCustomer)
//                                .setBorder(Border.NO_BORDER));
//
//        tblInvoiceHeader
//                .setMargin(0)
//                .setHorizontalAlignment(HorizontalAlignment.CENTER);

                //Div
        Div div_02 = new Div();
        div_02
                .setHeight(0.5f)
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setBorderTop(new DottedBorder(0.5f))
                .setMargin(0);

                //Invoice Detail
        float tblInvoiceDetailSize[] = {30, 60, 60};
        String[] tblInvoiceHeaderList = {
                        "Số lượng",
                        "Đơn giá",
//                        "VAT",
                        "Thành tiền"
        };
//        String[][] tblInvoiceProductList = {
//                        {"Áo thi đấu CKTG LEVI 20232023", "1", "250000", "10"},
//                        {"Áo đá banh hình Ronaldo", "1", "200000", "10"},
//                        {"Áo đá banh hình Messi", "1", "400000", "10"}
//        };
        Table tblInvoiceDetail = new Table(tblInvoiceDetailSize);
        for(String thisString : tblInvoiceHeaderList) {
                tblInvoiceDetail.addCell(new Cell()
                                .add(new Paragraph(thisString)
                                                .setFontSize(6)
                                                .setBold())
                                .setTextAlignment(TextAlignment.CENTER)
                                .setBorder(new SolidBorder(DeviceRgb.BLACK, 2))
                                .setBorderTop(Border.NO_BORDER)
                                .setBorderLeft(Border.NO_BORDER)
                                .setBorderRight(Border.NO_BORDER));
        }

        for(ChiTietHoaDon thisChiTietHoaDon : list) {
                Double price = thisChiTietHoaDon.getDonGia() * thisChiTietHoaDon.getSoLuong();
                NumberFormat nbf = NumberFormat.getCurrencyInstance(new Locale("vi", "vn"));
//                moneyTotal += price;
                tblInvoiceDetail
                        .addCell(new Cell(1,4)
                                .add(new Paragraph(thisChiTietHoaDon.getQuanAo().getTenQuanAo())
                                                .setFontSize(6)))
                        .addCell(new Cell()
                                .add(new Paragraph(Integer.toString(thisChiTietHoaDon.getSoLuong()))
                                                .setFontSize(6)
                                                .setTextAlignment(TextAlignment.RIGHT)))
                        .addCell(new Cell()
                                .add(new Paragraph(Double.toString(thisChiTietHoaDon.getDonGia()))
                                                .setFontSize(6)
                                                .setTextAlignment(TextAlignment.RIGHT)))
//                        .addCell(new Cell()
//                                .add(new Paragraph(thisStringArray[3])
//                                                .setFontSize(6)
//                                                .setTextAlignment(TextAlignment.RIGHT)))
                        .addCell(new Cell()
                                .add(new Paragraph(nbf.format(price))
                                                .setFontSize(6)
                                                .setTextAlignment(TextAlignment.RIGHT)));
        }

        tblInvoiceDetail
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setMargin(0);
                //Invoice Result
        Table tblInvoiceResult = new Table(tblInvoiceHeaderSize);
        tblInvoiceResult
                .addCell(new Cell()
                                .add(new Paragraph("Tổng tiền thanh toán: ")
                                                .setBold()
                                                .setFontSize(6))
                                .setBorder(Border.NO_BORDER))
                .addCell(new Cell()
                                .add(new Paragraph(Double.toString(tongTien))
                                                .setFontSize(6))
                                .setTextAlignment(TextAlignment.RIGHT)
                                .setBorder(Border.NO_BORDER))
                .addCell(new Cell()
                                .add(new Paragraph("Tiền khách đưa: ")
                                                .setBold()
                                                .setFontSize(6))
                                .setBorder(Border.NO_BORDER))
                .addCell(new Cell()
                                .add(new Paragraph(Double.toString(tienKhachDua))
                                                .setFontSize(6))
                                .setTextAlignment(TextAlignment.RIGHT)
                                .setBorder(Border.NO_BORDER))
                .addCell(new Cell()
                                .add(new Paragraph("Tiền thừa: ")
                                                .setBold()
                                                .setFontSize(6))
                                .setBorder(Border.NO_BORDER))
                .addCell(new Cell()
                                .add(new Paragraph(Double.toString(tienKhachDua-tongTien))
                                                .setFontSize(6))
                                .setTextAlignment(TextAlignment.RIGHT)
                                .setBorder(Border.NO_BORDER));
        tblInvoiceResult
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setBorderTop(new SolidBorder(DeviceRgb.BLACK, 2))
                .setMargin(0);

                //Footer
        Paragraph prgFooter = new Paragraph("Xin cảm ơn quý khách\nHẹn gặp lại");
        prgFooter
                .setFontSize(6)
                .setTextAlignment(TextAlignment.CENTER)
                .setFixedLeading(8f);

                //Div
        Div div_03 = new Div();
        div_03
                .setHeight(0.5f)
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setBorderTop(new DottedBorder(0.5f))
                .setMargin(0);

        //Add content to document
        document.add(prgShop);
        document.add(prgShopDetail);
        document.add(div_01);
        document.add(prgInvoice);
//        document.add(tblInvoiceHeader);
        document.add(prgInvoiceID);
        document.add(prgInvoiceDate);
        document.add(prgInvoiceMaker);
        document.add(prgInvoiceCustomer);
        document.add(div_02);
        document.add(tblInvoiceDetail);
        document.add(tblInvoiceResult);
        document.add(div_03);
        document.add(prgFooter);

        //End document
        document.close();
    }
}
