package it.polito.tdp.PremierLeague.model;

public class Avversario implements Comparable<Avversario> {
	
	private Team team;
	private Integer differenza;
	
	public Avversario(Team team, Integer differenza) {
		super();
		this.team = team;
		this.differenza = differenza;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Integer getDifferenza() {
		return differenza;
	}

	public void setDifferenza(Integer differenza) {
		this.differenza = differenza;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((differenza == null) ? 0 : differenza.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
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
		Avversario other = (Avversario) obj;
		if (differenza == null) {
			if (other.differenza != null)
				return false;
		} else if (!differenza.equals(other.differenza))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}

	@Override
	public int compareTo(Avversario o) {
		// TODO Auto-generated method stub
		return this.differenza-o.getDifferenza();
	}

	@Override
	public String toString() {
		return team.getName()+" "+"("+differenza+")";
	}
	
	
	
	
	
	
	

}
