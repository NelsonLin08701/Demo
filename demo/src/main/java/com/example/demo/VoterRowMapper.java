package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class VoterRowMapper implements RowMapper<Voter> {
	@Override
	public Voter mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
		Voter voter = new Voter();
		voter.setName(resultSet.getString("name"));
		voter.setId(resultSet.getInt("id"));
		return voter;
	}

}
