package tool;

import java.io.*;

/**
 * 
 * �ļ�����������
 * 
 * @author ����
 * @version [�汾��, 2010-12-8]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class ExportClassFile {
	// ��Ҫ�����ļ�ԭ����·���ĸ�Ŀ¼·�����������̵�·��֧��linux
	public static final String prePaht = "C:/AsiaInfo/Workspaces/Project/Project_crm2/";

	/**
	 * ���ļ�������ָ��·��
	 * 
	 * @param args
	 * @throws Exception
	 *             [����˵��]
	 * 
	 * @return void [��������˵��]
	 * @exception throws [Υ������] [Υ��˵��]
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public static void main(String[] args) throws Exception {
		String normalPath = "";
		String className = "";
		String newPath = "";
		String oldPath = "";
		// �����·�����Ʋ���������ʾ��/src/com/asiainfo/crm/cust/model/CustDetainInfo.java
		File infile = new File("C:/DataFiles/�ͻ�Ԥ������طù��ܷ���˵��/�����ļ��嵥.txt");
		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(infile), "utf-8"));
		while (in.read() != -1) {
			String target = in.readLine();
			normalPath = target;
			System.out.println(normalPath);
			className = normalPath.substring(normalPath.lastIndexOf("/") + 1,
					normalPath.length());
			// ��ԭʼ·���л�ȡ�ļ���·��������д����·�������·��
			oldPath = prePaht + normalPath;
			// ��Ҫ�����ļ��¾���·��
			newPath = "c:/DataFiles/�ͻ�Ԥ������طù��ܷ���˵��/"
					+ normalPath.substring(0, normalPath.lastIndexOf("/") + 1);
			ExportClassFile.createFloder(newPath);
			// File files = new File(oldPath); // ָ���ļ�����·��
			exportFile(oldPath, newPath + className);
		}
		// ���´������ִ�ж��Ŀ¼�µ��ļ����ƵĹ���
		// File[] fileList = files.listFiles();
		// for (String file : files.list()) {
		// exportFile(oldPath + "/" + file, newPath + "/" + file);
		// System.out.println(file);
		// }
		// for (File fileTemp : fileList) {
		// System.out.println(fileTemp.getCanonicalPath());
		// copyFile(fileTemp.getPath(), "c:/lll/");
		// }
	}

	/**
	 *�½��ļ���
	 * 
	 * @param sPath
	 * @return [����˵��]
	 * 
	 * @return boolean [��������˵��]
	 * @exception throws [Υ������] [Υ��˵��]
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public static boolean createFloder(String sPath) {
		File myFilePath = new File(sPath);
		if (!myFilePath.exists()) {
			myFilePath.mkdirs();
			return true;
		} else
			return false;
	}

	/**
	 * �����ļ���ָ��·��
	 * 
	 * @param oldPath
	 * @param newPath
	 *            [����˵��]
	 * 
	 * @return void [��������˵��]
	 * @exception throws [Υ������] [Υ��˵��]
	 * @see [�ࡢ��#��������#��Ա]
	 */
	public static void exportFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // �ļ�����ʱ
				InputStream inStream = new FileInputStream(oldPath); // ����ԭ�ļ�
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // �ֽ��� �ļ���С
					//System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}else{
				System.out.println("û���ҵ������ļ���"+oldPath);
			}
		} catch (Exception e) {
			System.out.println("�ļ������쳣,�ļ�·����"+oldPath);
			e.printStackTrace();
		}
	}
}
