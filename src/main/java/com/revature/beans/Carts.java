package com.revature.beans;

public class Carts {
	
	private Users userId;
	private Games gameId;
	
	public Carts() {}
	
	public Carts(Users userId, Games gameId) {
		this.userId = userId;
		this.gameId = gameId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gameId == null) ? 0 : gameId.hashCode());
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
		Carts other = (Carts) obj;
		if (gameId == null) {
			if (other.gameId != null)
				return false;
		} else if (!gameId.equals(other.gameId))
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
		return "Carts [userId=" + userId + ", gameId=" + gameId + "]";
	}


}
