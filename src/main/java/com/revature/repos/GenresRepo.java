package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.revature.beans.Genres;
import com.revature.utils.JDBCConnection;

public class GenresRepo implements GenericsRepo<Genres>{
	
	private Connection conn = JDBCConnection.getConnection();

	@Override
	public Genres add(Genres g) {
		String sql = "insert into genres values (default, ?) returning *;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, g.getName());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				g.setId(rs.getInt("id"));
				return g;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Genres getById(Integer id) {
		String sql = "select * from genres where id = ?;";
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
	public Map<Integer, Genres> getAll() {
		String sql = "select * from genres;";
		try {
			Map<Integer, Genres> map = new HashMap<Integer, Genres>();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Genres g =  this.make(rs);
				map.put(g.getId(), g);
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Genres g) {
		return false;
	}

	@Override
	public boolean delete(Genres g) {
		String sql = "delete from genres where id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, g.getId());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Genres make(ResultSet rs) throws SQLException {
		Genres g = new Genres();
		g.setId(rs.getInt("id"));
		g.setName(rs.getString("name"));
		return null;
	}

}
