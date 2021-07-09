package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.revature.beans.Games;
import com.revature.beans.Genres;
import com.revature.utils.JDBCConnection;

public class GamesRepo implements GenericsRepo<Games> {

	private Connection conn = JDBCConnection.getConnection();
	
	@Override
	public Games add(Games g) {
		String sql = "insert into games values (default, ?, ?, ?) returning *;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, g.getName());
			ps.setInt(2, g.getPrice());
			ps.setString(3, g.getGenre().getName());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				g.setId(rs.getInt("id"));
				return g;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Games getById(Integer id) {
		String sql = "select * from games where id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) return this.make(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<Integer, Games> getAll() {
		String sql = "Select * from games;";
		try {
			Map<Integer, Games> map = new HashMap<Integer, Games>();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Games g = this.make(rs);
				map.put(g.getId(), g);
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Games g) {
		String sql = "update games set name = ?, price = ?, genre = ? where id = ? returning *;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, g.getName());
			ps.setInt(2, g.getPrice());
			ps.setString(3, g.getGenre().getName());
			ps.setInt(4, g.getId());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Games g) {
		String sql = "delete from stories where id = ?;";
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
	public Games make(ResultSet rs) throws SQLException {
		Games g = new Games();
		g.setId(rs.getInt("id"));
		g.setName(rs.getString("name"));
		g.setPrice(rs.getInt("price"));
		Genres ge = new GenresRepo().getById(rs.getInt("id"));
		g.setGenre(ge);
		return null;
	}
}
