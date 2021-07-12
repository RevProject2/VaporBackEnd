package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.revature.beans.Games;
import com.revature.beans.Libraries;
import com.revature.beans.Users;
import com.revature.utils.JDBCConnection;

public class LibrariesRepo implements GenericsRepo<Libraries> {
	
	private Connection conn = JDBCConnection.getConnection();

	@Override
	public Libraries add(Libraries l) {
		String sql = "insert into libraries values (default, ?, ?) returning *;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, l.getId());
			ps.setInt(2, l.getUserId().getId());
			ps.setInt(3, l.getGameId().getId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				//I'm not sure how to set these without an ID row
				//in the table.
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Libraries getById(Integer id) {
		String sql = "select * from libraries where id = ?;";
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
	public Map<Integer, Libraries> getAll() {
		String sql = "select * from libraries;";
		try {
			Map<Integer, Libraries> map = new HashMap<Integer, Libraries>();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Libraries l = this.make(rs);
				map.put(l.getId(), l);
			}
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean update(Libraries l) {
		return false;
	}

	@Override
	public boolean delete(Libraries l) {
		String sql = "delete from genres where id = ?;";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  l.getId());
			return ps.execute();
		} catch (SQLException e) {
		}
		return false;
	}

	@Override
	public Libraries make(ResultSet rs) throws SQLException {
		Libraries l = new Libraries();
		l.setId(rs.getInt("id"));
		Users userId = new UsersRepo().getById(rs.getInt("id"));
		l.setUserId(userId);
		Games gameId = new GamesRepo().getById(rs.getInt("id"));
		l.setGameId(gameId);
		return null;
	}

}
