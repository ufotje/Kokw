package be.kokw.utility.converting;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public interface PdfToImage {


    static List<BufferedImage> convert(File file) throws IOException {

        PDDocument document = PDDocument.load(file);
        PDFRenderer renderer = new PDFRenderer(document);
        List<BufferedImage> bufferedImages = new ArrayList<>();
        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            BufferedImage image = renderer.renderImage(page);
            bufferedImages.add(image);
        }
        document.close();
        return bufferedImages;
    }
}




