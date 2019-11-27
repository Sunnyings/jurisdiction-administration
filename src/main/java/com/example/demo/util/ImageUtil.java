package com.example.demo.util;

import java.awt.Rectangle;
import java.io.File;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageUtil {

	/**
	 * 裁剪图片
	 * @param img
	 * @param path
	 * @param suffix
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @throws Exception
	 */
	public static void cutImage(InputStream img, File path, String suffix, int x, int y, int w, int h)
			throws Exception {
		ImageInputStream iis = null;
		try {
			iis = ImageIO.createImageInputStream(img);
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(suffix);
			ImageReader imagereader = (ImageReader) it.next();
			imagereader.setInput(iis);
			ImageReadParam par = imagereader.getDefaultReadParam();
			par.setSourceRegion(new Rectangle(x, y, w, h));
			ImageIO.write(imagereader.read(0, par), suffix, path);
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("裁剪图片出错了!",e);
		}finally {
			img.close();
			iis.flush();
			iis.close();
		}
	}

}
