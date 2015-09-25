package se.leanbit.ticketsystem.service.interfaces;

import se.leanbit.ticketsystem.model.Issue;

public interface IssueServiceInterface {
	public Issue addIssue(final Issue issue);

	public Issue getIssue(final long id);

	public Issue updateIssue(Issue issue, Long issueID);

	public void removeIssue(final long id);
}
