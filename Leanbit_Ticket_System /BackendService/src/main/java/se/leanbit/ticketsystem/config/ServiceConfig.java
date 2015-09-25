package se.leanbit.ticketsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import se.leanbit.ticketsystem.repository.IssueRepository;
import se.leanbit.ticketsystem.service.*;

@Configuration
public class ServiceConfig {
	@Bean
	public IssueService issueService() {
		return new IssueService();
	}

	@Bean
	public TeamService teamService() {
		return new TeamService();
	}

	@Bean
	public UserService userService() {
		return new UserService();
	}

	@Bean
	public WorkItemService workItemService() {
		return new WorkItemService();
	}

	@Bean
	public TicketSystemService ticketSystemService() {
		return new TicketSystemService();
	}

}
