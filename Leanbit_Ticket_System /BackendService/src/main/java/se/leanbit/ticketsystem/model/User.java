package se.leanbit.ticketsystem.model;

import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import se.leanbit.ticketsystem.exception.ModelException;
import se.leanbit.ticketsystem.model.abstra.ModelEntity;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.*;

@Entity
public class User extends ModelEntity {
	@Column(name = "user_id", unique = true)
	@Expose
	private String userID;

	@Column(name = "user_name")
	@Expose
	private String userName;

	@Column(name = "first_name")
	@Expose
	private String firstName;

	@Column(name = "last_name")
	@Expose
	private String lastName;

	@Column
	@Expose
	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	private Team team;

	@Expose
	private String teamName;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "User_Workitems")
	@MapKeyColumn(name = "ID")
	@Expose
	private Map<String, WorkItem> workItems;

	protected User() {
	}

	public User(final String userID, final String userName, final String firstName, final String lastName,
			final String password, final Team team) {
		this.userID = userID;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		setTeam(team);

		this.workItems = new HashMap<>();

	}

	public String getUserID() {
		return this.userID;
	}

	public void setUserID(final String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(final Team team) {
		/*
		 * if(null != team) { this.team = team; } else { throw new
		 * ModelException("User: User must be part of a team!"); }
		 */
		this.team = team;
		this.teamName = this.team.getTeamName();
	}

	public void addWorkItem(final WorkItem workItem) {
		this.workItems.put(UUID.randomUUID().toString(), workItem);
	}

	public void removeWorkItem(final WorkItem workItem) {
		while (this.workItems.values().remove(workItem))
			;
	}

	public List<WorkItem> getAllWorkItems() {
		List<WorkItem> workItems = new ArrayList<WorkItem>(this.workItems.values());
		return workItems;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public final boolean equals(final Object other) {
		if (this == other) {
			return true;
		} else if (other instanceof User) {
			final User otherUser = (User) other;
			final Map<String, WorkItem> otherUserWorkItems = new HashMap<>(otherUser.workItems);

			if (this.getId() == otherUser.getId() && this.userID.equals(otherUser.userID)
					&& this.userName.equals(otherUser.userName) && this.firstName.equals(otherUser.firstName)
					&& this.lastName.equals(otherUser.lastName) && this.password.equals(otherUser.password)
					&& this.workItems.equals(otherUserWorkItems)) {
				if (null != this.team && null != otherUser.team) {
					if (this.team.equals(otherUser.team)) {
						return true;
					}
					return false;
				} else if (null == this.team && null == otherUser.team) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
}
