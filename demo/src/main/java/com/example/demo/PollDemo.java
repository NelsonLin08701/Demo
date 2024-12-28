package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PollDemo {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@RequestMapping("/poll")
	public List<Poll> showPoll() {
		String sql = "SELECT * FROM poll";
		Map<String, Object> map = new HashMap<>();
		List<Poll> pollList = namedParameterJdbcTemplate.query(sql, map, new PollRowMapper());
		return pollList;
	}

	@PostMapping("/poll/insert")
	public String insertPoll(@RequestBody Poll poll) {
		String sql = "INSERT INTO poll(item) VALUES (:pollItem)";
		Map<String, Object> map = new HashMap<>();
		map.put("pollItem", poll.getItem());
		namedParameterJdbcTemplate.update(sql, map);
		return "Successful!";
	}

	@PostMapping("/poll/update")
	public String updatePoll(@RequestBody Poll poll) {
		String sql = "UPDATE poll SET item = :pollItem where (id = :pollId)";
		Map<String, Object> map = new HashMap<>();
		map.put("pollId", poll.getId());
		map.put("pollItem", poll.getItem());
		namedParameterJdbcTemplate.update(sql, map);
		return "Successful!";
	}

	@RequestMapping("/voter")
	public List<Voter> showVoter() {
		String sql = "SELECT * FROM voter";
		Map<String, Object> map = new HashMap<>();
		List<Voter> voterList = namedParameterJdbcTemplate.query(sql, map, new VoterRowMapper());
		return voterList;
	}

	@PostMapping("/voter/insert")
	public String insertVoter(@RequestBody List<Voter> list) {
		String sql = "INSERT INTO voter(name, id) VALUES (:voterName, :voterId)";
		Map<String, Object> map = new HashMap<>();
		for (Voter voter : list) {
			map.put("voterName", voter.getName());
			map.put("voterId", voter.getId());
			namedParameterJdbcTemplate.update(sql, map);
		}
		return "Successful!";
	}
}
