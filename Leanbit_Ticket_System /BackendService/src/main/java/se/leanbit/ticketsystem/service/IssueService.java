package se.leanbit.ticketsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.leanbit.ticketsystem.exception.IssueServiceException;
import se.leanbit.ticketsystem.model.Issue;
import se.leanbit.ticketsystem.repository.IssueRepository;
import se.leanbit.ticketsystem.service.interfaces.IssueServiceInterface;

public class IssueService implements IssueServiceInterface {
	@Autowired
	private IssueRepository issueRepository;

	public Issue addIssue(final Issue issue) {
		try {
			return issueRepository.save(issue);
		} catch (final Exception exception) {
			throw new IssueServiceException("IssueService: Could not save Issue!", exception);
		}
	}

	public Issue getIssue(final long id) {
		try {
			return issueRepository.findOne(id);
		} catch (final Exception exception) {
			throw new IssueServiceException("IssueService: Could not getIssue!", exception);
		}
	}

	@Override
	public Issue updateIssue(Issue issue, Long issueID) {
		return null;
	}

	public Issue updateIssue(final Issue issue) {
		try {
			return issueRepository.save(issue);
		} catch (final Exception exception) {
			throw new IssueServiceException("IssueService: Could not updateIssue!", exception);
		}
	}

	public void removeIssue(final long id) {
		try {
			issueRepository.delete(id);
		} catch (final Exception exception) {
			throw new IssueServiceException("IssueService: Could not removeIssue!", exception);
		}
	}
}
