package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PollRowMapper implements RowMapper<Poll> {
	@Override
	public Poll mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		Poll poll = new Poll();
		poll.setId(resultSet.getInt("id"));
		poll.setItem(resultSet.getString("item"));
		return poll;
	}
}
