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
 * <һ�仰���ܼ���>
 * 
 * @author ibm
 * @version [�汾��, 2010-12-12]
 * @see [�����/����]
 * @since [��Ʒ/ģ��汾]
 */
public class ExportFileSwing {
	// ��Ҫ�����ļ�ԭ����·�� �ĸ�Ŀ¼·�����������̵�·��֧��linux
	public static final String prePaht = System.getProperty("user.dir")+"/";
	private JFrame frame = new JFrame("�ļ�����");
	private JButton oldButton = new JButton("�����ļ��嵥·��");
	private JTextField nameText1 = new JTextField(21);
	private JButton newButton = new JButton("�����ļ���·��");
	private JTextField nameText2 = new JTextField(21);
	private JButton submitButton = new JButton("�����ļ�");
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
				JOptionPane.showMessageDialog(null, "��ȷ���޸���prePaht��ַ����");
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
				JOptionPane.showMessageDialog(null, "���µ�ַ�뿴������鿴�Ƿ������⣿");
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
						// ��ԭʼ·���л�ȡ�ļ���·��������д����·�������·��
						oldPath = prePaht + normalPath;
						file = new File(oldPath);
						if (!file.exists()) {
							System.out.println("�ļ�������");
							JOptionPane.showMessageDialog(null,
									"ϵͳ��prePath��ַδ�����޸ģ����޴��ļ����޸�PrePath��ϵͳ�ر�!");
							System.exit(0);
						} else {

							// ��Ҫ�����ļ��¾���·��
							newPath = nameText2.getText()
									+ "/"
									+ normalPath.substring(0, normalPath
											.lastIndexOf("/") + 1);
							ExportClassFile.createFloder(newPath);
							// File files = new File(oldPath); // ָ���ļ�����·��
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
					JOptionPane.showMessageDialog(null, "�ļ�����ɹ���ϵͳ�ر�");
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
