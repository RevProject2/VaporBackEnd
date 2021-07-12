package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.revature.beans.Carts;
import com.revature.beans.Games;
import com.revature.beans.Users;
import com.revature.utils.JDBCConnection;

public class CartsRepo implements GenericsRepo<Carts> {
	
	private Connection conn = JDBCConnection.getConnection();

	@Override
	public Carts add(Carts c) {
		String sql = "insert into carts values (default, ?, ?) returning *;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, c.getId());
			ps.setInt(2, c.getUserId().getId());
			ps.setInt(3, c.getGameId().getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				c.setId(rs.getInt("id"));
				return c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Carts getById(Integer id) {
		String sql = "select * from carts where id = ?;";
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
	public Map<Integer, Carts> getAll() {
		String sql = "select * from carts;";
		try {
			Map<Integer, Carts> map = new HashMap<Integer, Carts>();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Carts c = make(rs);
				map.put(c.getId(), c);
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Carts c) {
		String sql = "update carts set user id = ?, game id = ? where id = ? returning *;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, c.getUserId().getId());
			ps.setInt(2, c.getGameId().getId());
			ps.setInt(3, c.getId());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Carts c) {
		String sql = "delete from carts where id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, c.getId());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Carts make(ResultSet rs) throws SQLException {
		Carts c = new Carts();
		c.setId(rs.getInt("id"));
		Users u = new UsersRepo().getById(rs.getInt("id"));
		c.setUserId(u);
		Games g = new GamesRepo().getById(rs.getInt("id"));
		c.setGameId(g);
		return null;
	}

}
