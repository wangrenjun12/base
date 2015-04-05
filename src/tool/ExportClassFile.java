package tool;

import java.io.*;

/**
 * 
 * 文件导出工具类
 * 
 * @author 陈洋
 * @version [版本号, 2010-12-8]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ExportClassFile {
	// 需要导出文件原具体路径的父目录路径，即到工程的路径支持linux
	public static final String prePaht = "C:/AsiaInfo/Workspaces/Project/Project_crm2/";

	/**
	 * 把文件导出到指定路径
	 * 
	 * @param args
	 * @throws Exception
	 *             [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static void main(String[] args) throws Exception {
		String normalPath = "";
		String className = "";
		String newPath = "";
		String oldPath = "";
		// 具体的路径名称参照如下所示：/src/com/asiainfo/crm/cust/model/CustDetainInfo.java
		File infile = new File("C:/DataFiles/客户预警任务回访功能发布说明/发布文件清单.txt");
		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(infile), "utf-8"));
		while (in.read() != -1) {
			String target = in.readLine();
			normalPath = target;
			System.out.println(normalPath);
			className = normalPath.substring(normalPath.lastIndexOf("/") + 1,
					normalPath.length());
			// 从原始路径中获取文件的路径，可以写绝对路径和相对路径
			oldPath = prePaht + normalPath;
			// 需要导出文件新具体路径
			newPath = "c:/DataFiles/客户预警任务回访功能发布说明/"
					+ normalPath.substring(0, normalPath.lastIndexOf("/") + 1);
			ExportClassFile.createFloder(newPath);
			// File files = new File(oldPath); // 指定文件名及路径
			exportFile(oldPath, newPath + className);
		}
		// 以下代码可以执行多个目录下的文件搬移的功能
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
	 *新建文件夹
	 * 
	 * @param sPath
	 * @return [参数说明]
	 * 
	 * @return boolean [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
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
	 * 复制文件到指定路径
	 * 
	 * @param oldPath
	 * @param newPath
	 *            [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static void exportFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					//System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}else{
				System.out.println("没有找到以下文件："+oldPath);
			}
		} catch (Exception e) {
			System.out.println("文件导出异常,文件路径："+oldPath);
			e.printStackTrace();
		}
	}
}
