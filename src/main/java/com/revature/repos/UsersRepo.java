package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Users;
import com.revature.utils.HibernateUtil;
import com.revature.utils.JDBCConnection;

public class UsersRepo implements GenericsRepo<Users>{
	
	private Connection conn = JDBCConnection.getConnection();
	
	@Override
	public Users add(Users u) {
		
		String sql = "insert into users values (default, ?, ?, ?, ?, ?) returning *;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setInt(5, u.getBalance());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				u.setId(rs.getInt("id"));
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Users getById(Integer id) {
		String sql = "select * from users where id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) return this.make(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Map<Integer, Users> getAll() {
		String sql = "select * from users;";
		try {
			Map<Integer, Users> map = new HashMap<Integer, Users>();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Users u = make(rs);
				map.put(u.getId(), u);
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Users u) {
		String sql = "update users set balance = ? where id = ? returning *;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getBalance());
			ps.setInt(2, u.getId());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Users u) {
		String sql = "delete from users where id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getId());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Users make(ResultSet rs) throws SQLException {
		Users u = new Users();
		u.setId(rs.getInt("id"));
		u.setFirstName(rs.getString("first_name"));
		u.setLastName(rs.getString("last_name"));
		u.setBalance(rs.getInt("balance"));
		u.setUsername(rs.getString("username"));
		u.setPassword(rs.getString("password"));
		return u;
	}
}

