package com.tools;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileBase64 {
	private static Logger logger = LoggerFactory.getLogger(FileBase64.class);
	/**
	 * 把一个文件使用BASE64编码
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String args[]) throws IOException {
		String infilename = "c:\\Myproject.zip";
		File infile = new File(infilename);

		String sdata = fileToBase64(infile);
		 logger.info(sdata);

		// 将字符串写入到文本文件中
		writeStringtofile(sdata,new File("C:\\test\\myprojectbase64.txt"));
		
		logger.info("");
		logger.info("begin read");
		// String outfilename="c:\\testout.zip";
		// File outfile = new File(outfilename);
		base64ToFile(sdata);
	}

	private static void writeStringtofile(String s1, File fout) throws IOException {
		byte[] buffer = s1.getBytes();
		FileOutputStream out = new FileOutputStream(fout);
		//int bytesum = 0;
		//int byteread = 0;
		logger.info("...");
		out.write(buffer); // 文件写操作
		out.close();
	}

	/**
	 * 文件转base64字符串
	 * 
	 * @param file
	 * @return
	 */
	public static String fileToBase64(File file) {
		String base64 = null;
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			byte[] bytes = new byte[in.available()];
			@SuppressWarnings("unused")
			int length = in.read(bytes);
			base64 = new String(Base64.getEncoder().encode(bytes));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return base64;
	}

	/**
	 * base64字符串转文件
	 * 
	 * @param base64
	 * @return
	 */
	public static File base64ToFile(String base64) {
		File file = null;
		String fileName = "c:\\test\\out.zip";
		FileOutputStream out = null;
		try {
			// 解码，然后将字节转换为文件
			file = new File(fileName);
			if (!file.exists())
				file.createNewFile();

			byte[] bytes = Base64.getDecoder().decode(base64);
			// 将字符串转换为byte数组
			ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			byte[] buffer = new byte[1024];
			out = new FileOutputStream(file);
			//int bytesum = 0;
			int byteread = 0;
			while ((byteread = in.read(buffer)) != -1) {
				logger.info("...");
				//bytesum += byteread;
				out.write(buffer, 0, byteread); // 文件写操作
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file;
	}

}
