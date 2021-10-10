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
	 * ��һ���ļ�ʹ��BASE64����
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String args[]) throws IOException {
		String infilename = "c:\\Myproject.zip";
		File infile = new File(infilename);

		String sdata = fileToBase64(infile);
		 logger.info(sdata);

		// ���ַ���д�뵽�ı��ļ���
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
		out.write(buffer); // �ļ�д����
		out.close();
	}

	/**
	 * �ļ�תbase64�ַ���
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
	 * base64�ַ���ת�ļ�
	 * 
	 * @param base64
	 * @return
	 */
	public static File base64ToFile(String base64) {
		File file = null;
		String fileName = "c:\\test\\out.zip";
		FileOutputStream out = null;
		try {
			// ���룬Ȼ���ֽ�ת��Ϊ�ļ�
			file = new File(fileName);
			if (!file.exists())
				file.createNewFile();

			byte[] bytes = Base64.getDecoder().decode(base64);
			// ���ַ���ת��Ϊbyte����
			ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			byte[] buffer = new byte[1024];
			out = new FileOutputStream(file);
			//int bytesum = 0;
			int byteread = 0;
			while ((byteread = in.read(buffer)) != -1) {
				logger.info("...");
				//bytesum += byteread;
				out.write(buffer, 0, byteread); // �ļ�д����
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
