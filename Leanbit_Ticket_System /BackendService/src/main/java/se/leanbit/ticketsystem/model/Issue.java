package se.leanbit.ticketsystem.model;

import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import se.leanbit.ticketsystem.model.abstra.ModelEntity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
public class Issue extends ModelEntity {
	@Expose
	private String name;
	@Expose
	private String description;
	@Expose
	private int priority;

	protected Issue() {
	}

	public Issue(final String name, final String description, final int priority) {
		this.name = name;
		this.description = description;
		this.priority = priority;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(final int priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	@Override
	public final boolean equals(final Object other) {
		if (this == other) {
			return true;
		} else if (other instanceof Issue) {
			final Issue otherIssue = (Issue) other;
			if (this.getId() == otherIssue.getId() && this.getName().equals(otherIssue.getName())
					&& this.getDescription().equals(otherIssue.getDescription())
					&& this.getPriority() == otherIssue.getPriority()) {
				return true;
			}
		}
		return false;
	}
}
