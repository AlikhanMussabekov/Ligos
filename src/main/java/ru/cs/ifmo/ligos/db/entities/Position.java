package ru.cs.ifmo.ligos.db.entities;

public enum Position {
	DEFENDER, GOALKEEPER, MIDFIELDER, FORWARD;

	public String getPosition() {
		return name();
	}

}
