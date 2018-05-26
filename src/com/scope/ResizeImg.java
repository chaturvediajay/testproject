package com.scope;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import javax.imageio.ImageIO;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;


public class ResizeImg {
//	final static Logger logger = Logger.getLogger(OrderController.class);
	public static boolean imgsizeConvertor(String spath, String imgName) {
		boolean bol = false;
		String folderName[] = { "smaller", "fixed","backup" };
		folderName(folderName,spath,"img");
		int num = 0;
		for (int i = 0; i < folderName.length; i++) {
			File path = new File(spath + "/img/" + folderName[i]);
//			if (!path.exists()) {
//				path.mkdirs();
//			}
			switch (folderName[i]) {
			case "smaller":
				ResizeImg.resize(spath + "/temp/img/" + imgName, path.getPath() + "/" + imgName, 100, 100);
				bol = true;
				break;
			case "fixed":
				ResizeImg.resize(spath + "/temp/img/" + imgName, path.getPath() + "/" + imgName, 440, 600);
				bol = true;
				break;
			case "backup":
				try {
					File uploadedFile = new File(System.getProperty("catalina.base") + "/tmp_img");
					if (!uploadedFile.exists())
						uploadedFile.mkdirs();
					File source = new File(spath + "/temp/img/" + imgName);
					uploadedFile = new File(System.getProperty("catalina.base") + "/tmp_img/" + imgName);
					copyFileUsingChannel(source, uploadedFile);
					
					
				} catch (Exception e) {
					System.out.println("backup:- " + e.toString());
					e.printStackTrace();
				}
				break;
			}
			
		}

		
		try {
			/* copy file one location to another  */
			File path = new File(System.getProperty("catalina.base") + "/tmp_img");
			if (!path.exists())
				path.mkdirs();
			File source = new File(spath + "/temp/img/" + imgName);
			path = new File(System.getProperty("catalina.base") + "/tmp_img/" + imgName);
			copyFileUsingChannel(source, path);
			
			/* remove file current location */
			path = new File(spath + "/temp/img/" + imgName);
//			if(path.delete())
				//logger.info("File delete sucessfully! ");
			
//			else
				//logger.error("File not delete!-error ");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bol;
	}

	private static void folderName(String subFolder[], String spath, String folder) {
		File path = new File(spath + "/" + folder);
		if (!path.exists()) {
			path.mkdirs();
		}
		if (path.exists()) {
			for (int i = 0; i < subFolder.length; i++) {
				path = new File(spath + "/" +folder+"/"+ subFolder[i]);
				if (!path.exists()) {
					path.mkdirs();
				}
			}
		}
	}

	private static void copyFileUsingChannel(File source, File dest) throws IOException {
		FileChannel sourceChannel = null;
		FileChannel destChannel = null;
		try {
			sourceChannel = new FileInputStream(source).getChannel();
			destChannel = new FileOutputStream(dest).getChannel();
			destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		} catch (Exception ex) {
			System.out.println(source.getPath() + "\n" + dest + "\n" + ex.toString());
		}

		finally {
			sourceChannel.close();
			destChannel.close();
		}
	}

	public static void resize(String inputImagePath, String outputImagePath, int scaledWidth, int scaledHeight) {
		// reads input image
		try {
			File inputFile = new File(inputImagePath);
			BufferedImage inputImage = ImageIO.read(inputFile);

			// creates output image
			BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

			// scales the input image to the output image
			Graphics2D g2d = outputImage.createGraphics();
			g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
			g2d.dispose();

			// extracts extension of output file
			String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);

			// writes to output file
			ImageIO.write(outputImage, formatName, new File(outputImagePath));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			logger.error("File not delete!-error "+ e.toString());
		}
	}

	/**
	 * Resizes an image by a percentage of original size (proportional).
	 * 
	 * @param inputImagePath
	 *            Path of the original image
	 * @param outputImagePath
	 *            Path to save the resized image
	 * @param percent
	 *            a double number specifies percentage of the output image over
	 *            the input image.
	 * @throws IOException
	 */
	public static void resize(String inputImagePath, String outputImagePath, double percent) {
		try {
			File inputFile = new File(inputImagePath);
			BufferedImage inputImage = ImageIO.read(inputFile);
			int scaledWidth = (int) (inputImage.getWidth() * percent);
			int scaledHeight = (int) (inputImage.getHeight() * percent);
			resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		String inputImagePath = "/home/ajay/Documents/img/12.jpg";
		String outputImagePath1 = "/home/ajay/Documents/img/convert/12_Fixed.jpg";
		String outputImagePath2 = "/home/ajay/Documents/img/convert/12_Smaller.jpg";
		String outputImagePath3 = "/home/ajay/Documents/img/convert/12_Bigger.jpg";

		try {
			// resize to a fixed width (not proportional)

			// 440 * 600---100 * 100

			int scaledWidth = 1024;
			int scaledHeight = 768;
			ResizeImg.resize(inputImagePath, outputImagePath1, scaledWidth, scaledHeight);

			// resize smaller by 50%
			double percent = 0.5;
			ResizeImg.resize(inputImagePath, outputImagePath2, percent);

			// resize bigger by 50%
			percent = 1.5;
			ResizeImg.resize(inputImagePath, outputImagePath3, percent);

		} catch (Exception ex) {
			System.out.println("Error resizing the image.");
			ex.printStackTrace();
		}

	}

}