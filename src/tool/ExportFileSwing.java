package tool;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * 
 * <一句话功能简述>
 * 
 * @author ibm
 * @version [版本号, 2010-12-12]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ExportFileSwing {
	// 需要导出文件原具体路径 的父目录路径，即到工程的路径支持linux
	public static final String prePaht = System.getProperty("user.dir")+"/";
	private JFrame frame = new JFrame("文件导出");
	private JButton oldButton = new JButton("导入文件清单路径");
	private JTextField nameText1 = new JTextField(21);
	private JButton newButton = new JButton("导出文件新路径");
	private JTextField nameText2 = new JTextField(21);
	private JButton submitButton = new JButton("导出文件");
	private String normalPath = "";
	private String className = "";
	private String newPath = "";
	private String oldPath = "";

	public ExportFileSwing() {
		Container container = frame.getContentPane();
		container.add(oldButton);
		nameText1.setEditable(false);
		container.add(nameText1);
		container.add(newButton);
		nameText2.setEditable(false);
		container.add(nameText2);
		container.add(submitButton);
		oldButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "你确定修改了prePaht地址了吗？");
				JFileChooser chooser = new JFileChooser(".");
				int i = chooser.showOpenDialog(frame);
				File file = chooser.getSelectedFile();
				nameText1.setText(file.getAbsolutePath());
			}
		});
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser parseDir = new JFileChooser();
				parseDir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				parseDir.showOpenDialog(frame);
				String targerPath = parseDir.getCurrentDirectory()
						.getAbsolutePath();
				System.out.println(targerPath);
				nameText2.setText(targerPath);
				JOptionPane.showMessageDialog(null, "以下地址请看清楚，查看是否有问题？");
			}
		});
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File infile = new File(nameText1.getText());
				BufferedReader in;
				File file = null;
				try {
					in = new BufferedReader(new InputStreamReader(
							new FileInputStream(infile), "utf-8"));
					while (in.read() != -1) {
						String target = in.readLine();
						normalPath = target;
						System.out.println(normalPath);
						className = normalPath.substring(normalPath
								.lastIndexOf("/") + 1, normalPath.length());
						// 从原始路径中获取文件的路径，可以写绝对路径和相对路径
						oldPath = prePaht + normalPath;
						file = new File(oldPath);
						if (!file.exists()) {
							System.out.println("文件不存在");
							JOptionPane.showMessageDialog(null,
									"系统中prePath地址未进行修改，查无此文件请修改PrePath，系统关闭!");
							System.exit(0);
						} else {

							// 需要导出文件新具体路径
							newPath = nameText2.getText()
									+ "/"
									+ normalPath.substring(0, normalPath
											.lastIndexOf("/") + 1);
							ExportClassFile.createFloder(newPath);
							// File files = new File(oldPath); // 指定文件名及路径
							ExportClassFile.exportFile(oldPath, newPath
									+ className);
						}

					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					JOptionPane.showMessageDialog(null, "文件导入成功，系统关闭");
					System.exit(0);

				}

			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		frame.setLocation(333, 333);
		frame.setSize(333, 333);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new ExportFileSwing();
	}

}
