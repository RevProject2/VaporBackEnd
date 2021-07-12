package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.revature.beans.Employees;
import com.revature.utils.JDBCConnection;

public class EmployeesRepo implements GenericsRepo<Employees>{

	private Connection conn = JDBCConnection.getConnection();
	@Override
	public Employees add(Employees e) {
		String sql = "insert into employees values (default, ?, ?, ?, ?) returning *;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, e.getUsername());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getFirstName());
			ps.setString(4, e.getLastName());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				e.setId(rs.getInt("id"));
				return e;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Employees getById(Integer id) {
		String sql = "select * from employees where id = ?;";
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
	public Map<Integer, Employees> getAll() {
		String sql = "select * from employees";
		try {
			Map<Integer, Employees> map = new HashMap<Integer, Employees>();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Employees e = make(rs);
				map.put(e.getId(), e);
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Employees e) {
		String sql = "update employees set username = ?, password = ?, first = ?, last = ? where id = ? returning *;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, e.getUsername());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getFirstName());
			ps.setString(4, e.getPassword());
			return ps.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Employees e) {
		String sql = "delete from employees where id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, e.getId());
			return ps.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public Employees make(ResultSet rs) throws SQLException {
		Employees e = new Employees();
		e.setId(rs.getInt("id"));
		e.setUsername(rs.getString("username"));
		e.setPassword(rs.getString("password"));
		e.setFirstName(rs.getString("firstName"));
		e.setLastName(rs.getString("lastName"));
		return e;
	}

}
