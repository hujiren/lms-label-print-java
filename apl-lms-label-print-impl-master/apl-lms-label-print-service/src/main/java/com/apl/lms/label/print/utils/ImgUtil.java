package com.apl.lms.label.print.utils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import sun.misc.BASE64Decoder;

public class ImgUtil {
	// 顺时针旋转90度（通过交换图像的整数像素RGB 值）
	public static BufferedImage rotateClockwise90(BufferedImage bi) {
		int width = bi.getWidth();
		int height = bi.getHeight();
		BufferedImage bufferedImage = new BufferedImage(height, width, bi.getType());
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				bufferedImage.setRGB(height - 1 - j, width - 1 - i, bi.getRGB(i, j));
		return bufferedImage;
	}

	// 逆时针旋转90度（通过交换图像的整数像素RGB 值）
	public static BufferedImage rotateCounterclockwise90(BufferedImage bi) {
		int width = bi.getWidth();
		int height = bi.getHeight();
		BufferedImage bufferedImage = new BufferedImage(height, width, bi.getType());
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				bufferedImage.setRGB(j, i, bi.getRGB(i, j));
		return bufferedImage;
	}

	// 旋转180度（通过交换图像的整数像素RGB 值）
	public static BufferedImage rotate180(BufferedImage bi) {
		int width = bi.getWidth();
		int height = bi.getHeight();
		BufferedImage bufferedImage = new BufferedImage(width, height, bi.getType());
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				bufferedImage.setRGB(width - i - 1, height - j - 1, bi.getRGB(i, j));
		return bufferedImage;
	}

	// 水平翻转
	public static BufferedImage rotateHorizon(BufferedImage bi) {
		int width = bi.getWidth();
		int height = bi.getHeight();
		BufferedImage bufferedImage = new BufferedImage(width, height, bi.getType());
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				bufferedImage.setRGB(width - i - 1, j, bi.getRGB(i, j));
		return bufferedImage;
	}

	// 垂直翻转
	public static BufferedImage rotateVertical(BufferedImage bi) {
		int width = bi.getWidth();
		int height = bi.getHeight();
		BufferedImage bufferedImage = new BufferedImage(width, height, bi.getType());
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				bufferedImage.setRGB(i, height - 1 - j, bi.getRGB(i, j));
		return bufferedImage;
	}

	public static BufferedImage scale(BufferedImage src, int scale, boolean flag) {

		int width = src.getWidth(); // 得到源图宽
		int height = src.getHeight(); // 得到源图长
		if (flag) {
			// 放大
			width = width * scale;
			height = height * scale;
		} else {
			// 缩小
			width = width / scale;
			height = height / scale;
		}

		Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = tag.getGraphics();
		g.drawImage(image, 0, 0, null); // 绘制缩小后的图
		g.dispose();
		return tag;
	}

	public static BufferedImage GetBufferedImage(String base64string) throws IOException {
		BufferedImage image = null;
		InputStream stream = BaseToInputStream(base64string);
		image = ImageIO.read(stream);
		return image;

	}

	public static InputStream BaseToInputStream(String base64string) throws IOException {
		ByteArrayInputStream stream = null;
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] bytes1 = decoder.decodeBuffer(base64string);
		stream = new ByteArrayInputStream(bytes1);
		return stream;
	}

	// 图片转pdf
	public static void toPdf(String imageFolderPath, String pdfPath) throws Exception {
		String imagePath = null;
		FileOutputStream fos = new FileOutputStream(pdfPath);
		Document doc = new Document(null, 0, 0, 0, 0);
		PdfWriter.getInstance(doc, fos);
		BufferedImage img = null;
		com.itextpdf.text.Image image = null;
		File file = new File(imageFolderPath);
		File[] files = file.listFiles();
		for (File file1 : files) {
			if (file1.getName().endsWith(".png") || file1.getName().endsWith(".jpg") || file1.getName().endsWith(".gif")
					|| file1.getName().endsWith(".jpeg") || file1.getName().endsWith(".tif")) {
				imagePath = imageFolderPath + file1.separator + file1.getName();
				System.out.println("---" + file1.getName());
				img = ImageIO.read(new File(imagePath));
				doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
				image = com.itextpdf.text.Image.getInstance(imagePath);
				if (!doc.isOpen()) {
					doc.open();
				}
				doc.newPage();
				doc.add(image);

			}
		}
		doc.close();
	}

	public static void addPdfImage(Document doc, BufferedImage image, String formatName)
			throws Exception {

		com.itextpdf.text.Image imagePdf = null;
		doc.setPageSize(new Rectangle(image.getWidth(), image.getHeight()));

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(image, formatName, out);
		byte[] ImagByte = out.toByteArray();
		imagePdf = com.itextpdf.text.Image.getInstance(ImagByte);

		if (!doc.isOpen())
			doc.open();
		doc.newPage();
		doc.add(imagePdf);
	}
	
	public static void addPdfPage(Document doc, BufferedImage image)
			throws Exception {


		if (!doc.isOpen())
			doc.open();
		
	}

}
