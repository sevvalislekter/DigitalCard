package com.cardvisit.Utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QRCodeGenerator {
    public static void generateQRCodeImage(String text, String filePath, int width, int height)
            throws WriterException, IOException {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());

        
        int qrColor =0xFFFFFFFF;
        int backgroundColor = 0x00FFFFFF; 

        MatrixToImageConfig config = new MatrixToImageConfig(qrColor, backgroundColor);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path, config);

        System.out.println("Şeffaf QR oluşturuldu: " + new File(filePath).getAbsolutePath());
    }
}
