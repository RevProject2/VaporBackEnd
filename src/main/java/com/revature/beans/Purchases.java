package com.revature.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="purchases")
@Table(name="purchases")
public class Purchases {

	@Id
	@Column(name="id", insertable=false, updatable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(targetEntity = Users.class)
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private Users userId;
	@ManyToOne(targetEntity = Games.class)
	@JoinColumn(name="game_id", referencedColumnName = "id")
	private Games gameId;
	private Date p_date;
	
	public Purchases() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	public Games getGameId() {
		return gameId;
	}

	public void setGameId(Games gameId) {
		this.gameId = gameId;
	}

	public Date getP_date() {
		return p_date;
	}

	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((p_date == null) ? 0 : p_date.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Purchases other = (Purchases) obj;
		if (gameId == null) {
			if (other.gameId != null)
				return false;
		} else if (!gameId.equals(other.gameId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (p_date == null) {
			if (other.p_date != null)
				return false;
		} else if (!p_date.equals(other.p_date))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Purchases [id=" + id + ", userId=" + userId + ", gameId=" + gameId + ", p_date=" + p_date + "]";
	}
	
	
}
