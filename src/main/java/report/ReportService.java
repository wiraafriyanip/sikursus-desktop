package report;

import config.Database;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Layanan cetak laporan SIKURSUS untuk Praktikum PBO Pertemuan 12 dan 13.
 * Seluruh laporan mengambil data langsung dari db_sikursus.
 */
public final class ReportService {
    private ReportService() { }

    public static void tampilkanLaporanPeserta() {
        tampilkan("/report/laporan_peserta.jasper", new HashMap<String, Object>(),
                "Laporan Peserta");
    }

    public static void cetakPendaftaran(int idPendaftaran) {
        if (idPendaftaran <= 0) {
            JOptionPane.showMessageDialog(null, "ID pendaftaran tidak valid.",
                    "Laporan Pendaftaran", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("P_ID_PENDAFTARAN", Integer.valueOf(idPendaftaran));
        tampilkan("/report/laporan_pendaftaran.jasper", params, "Laporan Pendaftaran");
    }

    public static void cetakPembayaran(Date tanggalAwal, Date tanggalAkhir) {
        if (tanggalAwal == null || tanggalAkhir == null) {
            JOptionPane.showMessageDialog(null, "Tanggal awal dan tanggal akhir wajib diisi.",
                    "Laporan Pembayaran", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (tanggalAwal.after(tanggalAkhir)) {
            JOptionPane.showMessageDialog(null, "Tanggal awal tidak boleh setelah tanggal akhir.",
                    "Laporan Pembayaran", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("P_TANGGAL_AWAL", tanggalAwal);
        params.put("P_TANGGAL_AKHIR", tanggalAkhir);
        tampilkan("/report/laporan_pembayaran.jasper", params, "Laporan Pembayaran");
    }

    private static void tampilkan(String resource, Map<String, Object> params, String judul) {
        try (Connection connection = Database.getConnection();
             InputStream reportStream = ReportService.class.getResourceAsStream(resource)) {
            if (reportStream == null) {
                throw new IllegalStateException("File report belum ditemukan: " + resource
                        + ". Compile file JRXML di Jaspersoft Studio 6.21.5, lalu simpan hasil .jasper "
                        + "ke src/main/resources/report/.");
            }
            JasperPrint print = JasperFillManager.fillReport(reportStream, params, connection);
            if (print == null || print.getPages().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Tidak ada data untuk ditampilkan pada laporan ini.",
                        judul, JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            JasperViewer.viewReport(print, false);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Gagal membuka " + judul.toLowerCase() + ":\n" + ex.getMessage(),
                    judul, JOptionPane.ERROR_MESSAGE);
        }
    }
}
