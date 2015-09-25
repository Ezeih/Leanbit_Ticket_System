package se.leanbit.ticketsystem.model;

import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import se.leanbit.ticketsystem.model.abstra.ModelEntity;

import javax.persistence.*;

@Entity
public class WorkItem extends ModelEntity {
	@Expose
	private String name;
	@Expose
	private String description;
	@Expose
	private String status;
	@Expose
	private int priority;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(unique = false)
	@Expose
	private Issue issue;

	protected WorkItem() {
	}

	public WorkItem(final String name, final String description, final String status, final int priority) {
		this.name = name;
		this.description = description;
		this.status = status;
		this.priority = priority;
	}

	public WorkItem(final String name, final String description, final String status, final int priority,
			final Issue issue) {
		this.name = name;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.issue = issue;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(final int priority) {
		this.priority = priority;
	}

	public void setIssue(final Issue issue) {
		this.issue = issue;
	}

	public Issue getIssue() {
		return this.issue;
	}

	public boolean hasIssue() {
		if (null != issue) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	@Override
	public final boolean equals(final Object other) {
		if (this == other) {
			return true;
		} else if (other instanceof WorkItem) {
			final WorkItem otherWorkItem = (WorkItem) other;
			if (this.getId() == otherWorkItem.getId() && this.name.equals(otherWorkItem.name)
					&& this.description.equals(otherWorkItem.description) && this.priority == otherWorkItem.priority) {
				if (null != this.issue && null != otherWorkItem.issue) {
					if (this.issue.equals(otherWorkItem.issue)) {
						return true;
					}
					return false;
				} else if (null == this.issue && null == otherWorkItem.issue) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
}
