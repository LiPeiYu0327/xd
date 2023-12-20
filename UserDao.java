package cn.edu.imufe.hcdp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.imufe.hcdp.bean.Customer;
import cn.edu.imufe.hcdp.bean.User;
import cn.edu.imufe.hcdp.utils.DButil;

public class UserDao {
	
	Connection conn = DButil.getInstance().getConnection();
	/**
	 * 根据用户名得到用户信息
	 * @param usercode 用户名
	 * @return
	 * @throws Exception
	 */
	public User login(String usercode)throws Exception{
		String sql = "select * from user where usercode=?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		pst.setString(1, usercode);
		rs = pst.executeQuery();
		User user = new User();
		if(rs.next()) {
			user.setUserid(rs.getInt("userid"));
			user.setUsercode(rs.getString("usercode"));
			user.setPassword(rs.getString("password"));
			user.setStatus(rs.getString("status"));
		}
		return user;
	}
	/**
	 * 根据id得到用户的基本信息
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public User getUser(int userid)throws Exception{
		String sql = "select * from customer where userid=?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		pst.setInt(1, userid);
		rs = pst.executeQuery();
		User user = new User();
		if(rs.next()) {
			user.setUserid(rs.getInt("userid"));
			user.setCid(rs.getInt("cid"));
			user.setName(rs.getString("name"));
			user.setCardid(rs.getString("cardid"));
			user.setPhone(rs.getString("phone"));
			user.setType(rs.getString("type"));
		}
		return user;
	}
	/**
	 * 分页查询显示所有的用户信息
	 * @param cnt1  当前记录数+1
	 * @param cnt2 每页显示记录数
	 * @return
	 * @throws Exception
	 */
	public List<User> selectAllUser(int cnt1,int cnt2)throws Exception{
		String sql = "SELECT user.userid,cid,usercode,PASSWORD,STATUS,NAME,cardid,phone,TYPE\r\n" + 
				"FROM USER,customer\r\n" + 
				"WHERE user.userid=customer.userid limit ?,?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		pst.setInt(1, cnt1);
		pst.setInt(2, cnt2);
		rs = pst.executeQuery();
		List<User> list = new ArrayList<User>();
		User user = null;
		while(rs.next()) {
			user = new User();
			user.setUserid(rs.getInt("userid"));
			user.setCid(rs.getInt("cid"));
			user.setName(rs.getString("name"));
			user.setCardid(rs.getString("cardid"));
			user.setPhone(rs.getString("phone"));
			user.setType(rs.getString("type"));
			user.setStatus(rs.getString("status"));
			user.setUsercode(rs.getString("usercode"));
			user.setPassword(rs.getString("password"));
			list.add(user);
		}
		return list;
	}
	/**
	 * 得到对应用户的用户数
	 * @return
	 * @throws Exception
	 */
	public int getCount()throws Exception{
		String sql = "SELECT COUNT(*) FROM USER,customer WHERE user.userid=customer.userid";
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		return cnt;
	}
	
	
	/**
	 * 分页查询显示所有的用户信息
	 * @param cnt1  当前记录数+1
	 * @param cnt2 每页显示记录数
	 * @return
	 * @throws Exception
	 */
	public List<User> selectAllUser(int cnt1,int cnt2,String name)throws Exception{
		String sql = "SELECT user.userid,cid,usercode,PASSWORD,STATUS,NAME,cardid,phone,TYPE\r\n" + 
				"FROM USER,customer\r\n" + 
				"WHERE user.userid=customer.userid and customer.name like '%"+name+"%' limit ?,?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		pst.setInt(1, cnt1);
		pst.setInt(2, cnt2);
		rs = pst.executeQuery();
		List<User> list = new ArrayList<User>();
		User user = null;
		while(rs.next()) {
			user = new User();
			user.setUserid(rs.getInt("userid"));
			user.setCid(rs.getInt("cid"));
			user.setName(rs.getString("name"));
			user.setCardid(rs.getString("cardid"));
			user.setPhone(rs.getString("phone"));
			user.setType(rs.getString("type"));
			user.setStatus(rs.getString("status"));
			user.setUsercode(rs.getString("usercode"));
			user.setPassword(rs.getString("password"));
			list.add(user);
		}
		return list;
	}
	/**
	 * 得到对应用户的用户数模糊
	 * @return
	 * @throws Exception
	 */
	public int getCount(String name)throws Exception{
		String sql = "SELECT COUNT(*) FROM USER,customer WHERE user.userid=customer.userid and customer.name like '%"+name+"%'";
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		return cnt;
	}
	
	
	/**
	 * 根据userid删除用户信息
	 * @param userid
	 * @throws Exception
	 */
	public void deleteUser(int userid)throws Exception{
		String sql = "delete from user where userid="+userid;
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.executeUpdate();
	}
	/**
	 * 根据id得到用户信息
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public User getUserById(int userid)throws Exception{
		String sql = "SELECT user.userid,cid,usercode,PASSWORD,STATUS,NAME,cardid,phone,TYPE\r\n" + 
				"FROM USER,customer\r\n" + 
				"WHERE user.userid=customer.userid and user.userid="+userid;
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		User user = new User();
		if(rs.next()) {
			user.setUserid(rs.getInt("userid"));
			user.setCid(rs.getInt("cid"));
			user.setName(rs.getString("name"));
			user.setCardid(rs.getString("cardid"));
			user.setPhone(rs.getString("phone"));
			user.setType(rs.getString("type"));
			user.setStatus(rs.getString("status"));
			user.setUsercode(rs.getString("usercode"));
			user.setPassword(rs.getString("password"));
		}
		return user;
	}
	/**
	 * 更新用户信息
	 * @param user
	 * @throws Exception
	 */
	public void userUpdate(User user)throws Exception{
		String sql = "update customer set phone=?,type=? where cid=?";
		String sql1 = "update user set password=? where userid=?";
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.setString(1, user.getPhone());
		pst.setString(2, user.getType());
		pst.setInt(3, user.getCid());
		pst.executeUpdate();
		PreparedStatement pst1 = null;
		pst1 = conn.prepareStatement(sql1);
		pst1.setString(1, user.getPassword());
		pst1.setInt(2, user.getUserid());
		pst1.executeUpdate();
	}

	/**
	 * 禁用用户信息
	 * @param user
	 * @throws Exception
	 */
	public void disabledUser(int userId)throws Exception{
		String sql = "update user set status='000' where userid=?";
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.setInt(1, userId);
		pst.executeUpdate();
	}

	/**
	 * 解除禁用用户信息
	 * @param user
	 * @throws Exception
	 */
	public void enabledUser(int userId)throws Exception{
		String sql = "update user set status='0' where userid=?";
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.setInt(1, userId);
		pst.executeUpdate();
	}

	/**
	 * 查询用户名是否存在
	 * @param usercode
	 * @return
	 * @throws Exception
	 */
	public int getUserCode(String usercode)throws Exception{
		String sql = "select count(*) from user where usercode='"+usercode+"'";
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		int cnt = -1;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		return cnt;
	}
	
	
	/**
	 * 用户注册
	 * @param customer
	 * @param user
	 * @throws Exception
	 */
	public void reg(Customer customer,User user)throws Exception{
		this.insertUser(user);
		this.insertCustomer(customer, user.getUsercode());
	}
	
	public void insertUser(User user)throws Exception{
		String sql = "insert into user(usercode,password) values(?,?)";
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.setString(1, user.getUsercode());
		pst.setString(2, user.getPassword());
		pst.executeUpdate();
	}
	
	public int getUserId(String usercode)throws Exception{
		String sql = "select userid from user where usercode='"+usercode+"'";
		PreparedStatement pst = null;
		ResultSet rs = null;
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		int cnt = -1;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		return cnt;
	}
	
	public void insertCustomer(Customer customer,String usercode)throws Exception{
		System.out.println("rfty");
		String sql = "insert into customer(userid,name,cardid,phone,type) values(?,?,?,?,?)";
		PreparedStatement pst = null;
		pst = conn.prepareStatement(sql);
		pst.setInt(1, getUserId(usercode));
		pst.setString(2, customer.getName());
		pst.setString(3, customer.getCardid());
		pst.setString(4, customer.getPhone());
		pst.setString(5, customer.getType());
		pst.executeUpdate();
	}
	
}
