package br.net.triangulohackerspace.thsspaceapi.domain.to;

import br.net.triangulohackerspace.thsspaceapi.domain.Space;
import br.net.triangulohackerspace.thsspaceapi.domain.State;
import br.net.triangulohackerspace.thsspaceapi.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

public class StateTO implements Serializable {

	private static final long serialVersionUID = 5470957854048224005L;

	@JsonIgnore
	private br.net.triangulohackerspace.thsspaceapi.domain.State state;

	public StateTO(State state) {
		super();
		this.state = state;
		if (this.state == null) {
			this.state = new State();
		}
	}

	public Boolean getOpen() {
		return state.getStatus();
	}

	public void setOpen(Boolean open) {
		this.state.setStatus(open);
	}

	@JsonIgnore
	public Space getSpace() {
		return state.getSpace();
	}

	public void setSpace(Space space) {
		this.state.setSpace(space);
	}

	@JsonIgnore
	public User getUser() {
		return state.getUser();
	}

	public void setUser(User user) {
		this.state.setUser(user);
	}

	@JsonIgnore
	public Date getDate() {
		return state.getDate();
	}

	public void setDate(Date date) {
		this.state.setDate(date);
	}
}
