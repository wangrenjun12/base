package tool;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SyncMenu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SyncMenu sync = new SyncMenu();
		Connection conn = null;
		try {
			conn = sync.getConn();
			
			/*String str = sync.printMenuInfo(conn, "crm9000");
			System.out.println(str);
			boolean b1 = sync.isLowestLevelParentMenu(conn,"crm5823");
			System.out.println(b1);*/
			
			
			List<Menu> list = sync.getAllParentMenu(conn);
			for(Menu m:list)
			{
				//System.out.println(m.getMenuId()+":"+m.getMenuName());
				String str = sync.printMenuInfo(conn, m.getMenuId());
				System.out.println(str);
			}  
			
			conn.close();
		} catch (Exception e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		

	}

	private Connection getConn() throws Exception
	{
		Properties prop = new Properties();
		prop.load(new FileInputStream("src/tool/jdbc.properties"));
		String host = prop.getProperty("host", "");
		String port = prop.getProperty("port", "");
		String servername = prop.getProperty("servername", "");
		String username = prop.getProperty("username", "");
		String password = prop.getProperty("password", "");
		prop.clone();
		StringBuffer sb = new StringBuffer();
		sb.append("jdbc:oracle:thin:@");
		sb.append(host);
		sb.append(":");
		sb.append(port);
		sb.append(":");
		sb.append(servername);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection(sb.toString(),username,password);
	}
	
	/**
	 * 给定一个菜单ID，打印出以该菜单ID为父菜单的子菜单
	 */
	private String printMenuInfo(Connection conn,String menuId)  throws Exception
	{
		String sql1 = "select menu_title from TD_B_SYSTEMGUIMENU where menu_id = ?";
		PreparedStatement pstm1 = conn.prepareStatement(sql1);
		pstm1.setString(1, menuId);
		ResultSet rs1 = pstm1.executeQuery();
		StringBuilder sb = new StringBuilder();
		while(rs1.next())
		{
			sb.append("<h4 class=\"fold\"><a href=\"javascript:void(0)\">");
			sb.append(rs1.getString(1));
			sb.append("</a></h4>");
			sb.append("\r\n");
			sb.append("<ul  style=\"display:none;\">");
			sb.append("\r\n");
		}
		
		
		String sql2 = "select s.menu_id,s.parent_menu_id, s.menu_title ,m.mod_name from  TD_B_SYSTEMGUIMENU s,TD_S_MODFILE m where s.menu_id = m.mod_code  and s.parent_menu_id = ? order by s.show_order";
		PreparedStatement pstm2 = conn.prepareStatement(sql2);
		pstm2.setString(1, menuId);
		ResultSet rs2 = pstm2.executeQuery();
		while(rs2.next())
		{
			
			String mod_name = rs2.getString(4);
			sb.append("    <li><a jwcid=\"@wade:PageRedirect\" value=\"");
			sb.append(rs2.getString(3));
			sb.append("\" onclick=\"redirectToNav(\'");
			sb.append(getPage(mod_name));
			sb.append("','");
			sb.append(getListener(mod_name));
			sb.append("', null, 'contentframe')\"></a></li> ");
			sb.append("\r\n");
		}
		sb.append("</ul>");
		sb.append("\r\n");
		rs1.close();
		pstm1.close();
		rs2.close();
		pstm2.close();
		return sb.toString();
	}
	
	private String getPage(String mod_name)
	{
		int begin = mod_name.indexOf("service=page/");
		int end = mod_name.indexOf("&listener");
		if(end == -1)
	    {
			end = mod_name.length();
	    }
		return mod_name.substring(begin+"service=page/".length(), end);
	}
	
	
	private String getListener(String mod_name)
	{
		if(mod_name.endsWith("listener="))
		{
			return "";
		}
		else if(!mod_name.contains("listener="))
		{
			return "";
		}
		else
		{
			return mod_name.substring(mod_name.indexOf("listener=")+"listener=".length());
		}
	}
	
	
	/**
	 * 获取所有叶子节点菜单的父菜单
	 * 1 根据客管根菜单ID crm5000 开始查询   渠道根菜单crm9000  营销维挽 crm3000
	 * @param conn
	 * @return
	 */
	private List<Menu> getAllParentMenu(Connection conn) throws Exception
	{
		List<Menu> menuIds = new ArrayList<Menu>(200);
		String rootMenuId = "crm5000";
		String sql1 = "select menu_id,menu_title,parent_menu_id,show_order from TD_B_SYSTEMGUIMENU start with menu_id = ? connect by prior menu_id = parent_menu_id ";
		PreparedStatement pstm1 = conn.prepareStatement(sql1);
		pstm1.setString(1, rootMenuId);
		ResultSet rs1 = pstm1.executeQuery();
		String sql2 = "SELECT count(*) FROM  TD_B_SYSTEMGUIMENU where parent_menu_id = ?";
		PreparedStatement pstm2 = conn.prepareStatement(sql2);
		while(rs1.next())
		{
			String menuId2 = rs1.getString(1);
			if(isLowestLevelParentMenu(conn,menuId2,sql2,pstm2))
			{
				Menu menu = new Menu();
				menu.setMenuId(menuId2);
				menu.setMenuName(rs1.getString(2));
				menu.setParentMenuId(rs1.getString(3));
				menu.setShowOrder(rs1.getInt(4));
				menuIds.add(menu);
			}
		}
		return menuIds;
	}
	
	/**
	 * 判断是否是最低级别父菜单
	 * 
	 * 
	 * @param conn
	 * @param menuId
	 * @return
	 * @throws Exception
	 */
	private boolean isLowestLevelParentMenu(Connection conn,String  menuId,String sql1,PreparedStatement pstm1) throws Exception
	{
		// 步骤一 判断是否为有子菜单,没有则返回false
		pstm1.setString(1, menuId);
		ResultSet rs1 = pstm1.executeQuery();
		int count = 0;
		while(rs1.next())
		{
			count = rs1.getInt(1);
		}
		if(count == 0)
		{
			return false;
		}
		
		//如果有子菜单,只有一个子菜单含有子菜单则返回false
		else
		{
			String childMenuId ;
			String sql2 = "SELECT menu_id FROM  TD_B_SYSTEMGUIMENU where parent_menu_id = ?";
			PreparedStatement pstm2 = conn.prepareStatement(sql1);
			pstm2.setString(1, menuId);
			ResultSet rs2 = pstm2.executeQuery();
			while(rs2.next())
			{
				childMenuId = rs2.getString(1);
				pstm1.setString(1, childMenuId);
				rs1 = pstm1.executeQuery();
				while(rs1.next())
				{
					count = rs1.getInt(1);
					return count>0?false:true;
				}
			}
			return true;
		}
	}
	
	
	class Menu
	{
		String menuId;
		String menuName;
		String parentMenuId;
		int showOrder;
		public String getMenuId() {
			return menuId;
		}
		public void setMenuId(String menuId) {
			this.menuId = menuId;
		}
		public String getMenuName() {
			return menuName;
		}
		public void setMenuName(String menuName) {
			this.menuName = menuName;
		}
		public String getParentMenuId() {
			return parentMenuId;
		}
		public void setParentMenuId(String parentMenuId) {
			this.parentMenuId = parentMenuId;
		}
		public int getShowOrder() {
			return showOrder;
		}
		public void setShowOrder(int showOrder) {
			this.showOrder = showOrder;
		}
	}
	
	
}
