/*
 * package se.leanbit.ticketsystem.testing;
 * 
 * import org.junit.*; import org.junit.rules.ExpectedException; import
 * org.junit.runner.RunWith; import
 * org.springframework.context.annotation.AnnotationConfigApplicationContext;
 * import org.springframework.test.context.ActiveProfiles; import
 * org.springframework.test.context.ContextConfiguration; import
 * org.springframework.test.context.junit4.SpringJUnit4ClassRunner; import
 * se.leanbit.ticketsystem.config.InfraStructureConfig; import
 * se.leanbit.ticketsystem.model.Issue; import
 * se.leanbit.ticketsystem.model.Team; import
 * se.leanbit.ticketsystem.model.User; import
 * se.leanbit.ticketsystem.model.WorkItem; import
 * se.leanbit.ticketsystem.service.TicketSystemService; import static
 * org.hamcrest.CoreMatchers.is; import static org.junit.Assert.assertThat;
 * 
 * import java.util.ArrayList;
 * 
 * import static org.junit.Assert.*; import static
 * org.hamcrest.CoreMatchers.equalTo;
 * 
 * 
 * @ContextConfiguration(classes={InfraStructureConfig.class})
 * 
 * @ActiveProfiles("test")
 * 
 * @RunWith(SpringJUnit4ClassRunner.class) public class Testing { private static
 * TicketSystemService ticketSystemService; private Team team; private static
 * ArrayList<Team> teams; private static ArrayList<WorkItem> workItems; private
 * User user; private static AnnotationConfigApplicationContext context;
 * 
 * @Rule public ExpectedException exception = ExpectedException.none();
 * 
 * @Before public void setUp() throws Exception { context = new
 * AnnotationConfigApplicationContext();
 * context.scan("se.leanbit.ticketsystem.config."); context.refresh();
 * ticketSystemService = context.getBean(TicketSystemService.class);
 * 
 * }
 * 
 * 
 * //*********** User tests **************************************
 * 
 * @Test public void assertThatUserIsSavable(){ user = new User("920406",
 * "Kira", "Erik", "Welander", "Elfen", null); assertEquals(
 * "Added User should be returned", user, ticketSystemService.addUser(user)); }
 * 
 * 
 * @Test public void assertThatUserCanBeDeleted(){ user = new User("920406",
 * "Kira", "Erik", "Welander", "Elfen", null);
 * ticketSystemService.addUser(user); ticketSystemService.removeUser("920406");
 * assertEquals("User was removed", null,
 * ticketSystemService.getUserWithID("920406"));
 * 
 * }
 * 
 * @Test public void assertThatGetUserWithId(){ user = new User("920406",
 * "Kira", "Erik", "Welander", "Elfen", null);
 * ticketSystemService.addUser(user); assertEquals( "920406",
 * ticketSystemService.getUserWithID("920406").getUserID()); }
 * 
 * @Test public void assertThatUserCanBeUpdated(){ user = new User("920406",
 * "Kira", "Erik", "Welander", "Elfen", null);
 * ticketSystemService.addUser(user); user.setFirstName("Vitalij");
 * user.setTeam(team); User user2= ticketSystemService.updateUser(user);
 * assertThat("UserName should now be changed",user, is(user2)); }
 * 
 * @Test public void updateUserTest(){ Team team = new Team("Leanbit");
 * ticketSystemService.addTeam(team); user = new User("920406", "Kira", "Erik",
 * "Welander", "Elfen", team); ticketSystemService.addUser(user); User
 * updatedUser = ticketSystemService.getUserWithUserName("Kira");
 * updatedUser.setPassword("111"); User
 * user1=ticketSystemService.updateUser(updatedUser);
 * assertThat(updatedUser,is(user1) ); }
 * 
 * @Test public void assertThatUserIsFoundByFirstname(){ user = new
 * User("920406", "Kira", "Erik", "Welander", "Elfen", null);
 * ticketSystemService.addUser(user); assertThat(
 * "User is found by it's firstname", "920406",
 * is(ticketSystemService.getUsersWithFirstName("Erik").get(0).getUserID())); }
 * 
 * @Test public void assertThatUserIsFoundByLastname(){ user = new
 * User("920406", "Kira", "Erik", "Welander", "Elfen", null);
 * ticketSystemService.addUser(user); assertThat(
 * "User is found by it's firstname", "920406",
 * is(ticketSystemService.getUsersWithLastName("Welander").get(0).getUserID()));
 * }
 * 
 * @Test public void assertThatUserIsFoundByUserName(){ user = new
 * User("920406", "Kira", "Erik", "Welander", "Elfen", null);
 * ticketSystemService.addUser(user); assertThat(
 * "User is found by it's username", "920406",
 * is(ticketSystemService.getUserWithUserName("Kira").getUserID())); }
 * 
 * @Test public void assertThanUsersCanBeReceivedByWorkItem(){
 * 
 * WorkItem workItem = new WorkItem("Fixdisshit", "BLAH", "DO_IT", 9001);
 * ticketSystemService.addWorkItem(workItem); user = new User("920406", "Kira",
 * "Erik", "Welander", "Elfen", null); user.addWorkItem(workItem);
 * ticketSystemService.addUser(user); assertThat("920406",
 * is(ticketSystemService.getUsersWithWorkItem(workItem).get(0).getUserID())); }
 * 
 * @Test public void getAllUsersFromTeamTest(){ Team team = new Team
 * ("Leanbit"); ticketSystemService.addTeam(team);
 * 
 * user = new User("920406", "Kira", "Erik", "Welander", "Elfen", team); User
 * user1 = new User("110406", "fufelok", "Vitalij", "Bajkov", "Elfen", team);
 * ticketSystemService.addUser(user); ticketSystemService.addUser(user1);
 * 
 * assertThat (2,
 * is(ticketSystemService.getAllUsersFromTeam(team.getTeamName()).size())); }
 * 
 * //************ Team tests **************************************
 * 
 * @Test public void assertThatTeamCanBeSaved() { team = new Team("leanbit");
 * Team team1 = ticketSystemService.addTeam(team); assertEquals(team, team1);
 * assertEquals(team, ticketSystemService.getTeam(team.getTeamName())); }
 * 
 * 
 * @Test public void assertThatTeamCanBeRemoved(){ team = new
 * Team("Leanbit_delete"); ticketSystemService.addTeam(team);
 * ticketSystemService.removeTeam("Leanbit_delete"); assertEquals( null,
 * ticketSystemService.getTeam("Leanbit_delete")); }
 * 
 * @Test public void assertThatCanGetTeamWithTeamName() { team = new
 * Team("leanbit"); ticketSystemService.addTeam(team);
 * 
 * assertThat("Team is gotten by name", team, is(
 * ticketSystemService.getTeam("leanbit"))); }
 * 
 * @Test public void assertThatAllTeamsCanBeReceived(){ Team team1 = new
 * Team("Leanbit1"); Team team2 = new Team("Leanbit2"); Team team3 = new
 * Team("Leanbit3"); ticketSystemService.addTeam(team1);
 * ticketSystemService.addTeam(team2); ticketSystemService.addTeam(team3); teams
 * = (ArrayList<Team>) ticketSystemService.getAllTeams(); assertThat(
 * "Number of teams are matched", 3, is(teams.size())); }
 * 
 * 
 * //************ WorkItem test methods ****************
 * 
 * @Test public void addWorkItemTest(){ WorkItem workItem = new
 * WorkItem("Fixdisshit", "BLAH", "DO_IT", 9001); WorkItem newWorkItem =
 * ticketSystemService.addWorkItem(workItem); newWorkItem =
 * ticketSystemService.getWorkItem(newWorkItem.getName());
 * assertThat(newWorkItem.getName(), is("Fixdisshit")); }
 * 
 * @Test public void updateWorkItemTest(){ WorkItem workItem = new
 * WorkItem("Fixdisshit", "BLAH", "DO_IT", 9001);
 * ticketSystemService.addWorkItem(workItem); WorkItem updatedWorkItem =
 * ticketSystemService.getWorkItem("Fixdisshit");
 * updatedWorkItem.setPriority(12);
 * assertThat(ticketSystemService.updateWorkItem(updatedWorkItem).getPriority(),
 * is(12)); }
 * 
 * @Test public void removeWorkItemTest(){ WorkItem workItem = new
 * WorkItem("Fixdisshit", "BLAH", "DO_IT", 9001);
 * ticketSystemService.addWorkItem(workItem); WorkItem deletedWorkItem =
 * ticketSystemService.getWorkItem(workItem.getName());
 * ticketSystemService.removeWorkItem(deletedWorkItem); assertEquals(
 * ticketSystemService.getWorkItem(deletedWorkItem.getName()), null); }
 * 
 * @Test public void addWorkItemToUserAndGetWorkItemsFromTeamTest(){
 * ticketSystemService.addWorkItem(new WorkItem("heloooo", "BLAH", "DO_IT",
 * 9001)); ticketSystemService.addWorkItem(new WorkItem("meeeee", "Bibib",
 * "DOIT", 3)); ticketSystemService.addWorkItem(new WorkItem("diiiii", "loooo",
 * "DO.IT", 2)); Team team = new Team("woooooo");
 * ticketSystemService.addTeam(team); user = new User("920406", "Aseel",
 * "Aseel", "wwwwww", "Elfen", team); ticketSystemService.addUser(user); User
 * user1 = new User("110433", "Inas", "Inna", "Erkisson", "Elfen", team);
 * ticketSystemService.addUser(user1);
 * ticketSystemService.addWorkItemToUser(ticketSystemService.getUserWithUserName
 * ("Aseel"),ticketSystemService.getWorkItem("heloooo"));
 * ticketSystemService.addWorkItemToUser(ticketSystemService.getUserWithUserName
 * ("Aseel"),ticketSystemService.getWorkItem("meeeee"));
 * ticketSystemService.addWorkItemToUser(ticketSystemService.getUserWithUserName
 * ("Inas"),ticketSystemService.getWorkItem("diiiii")); workItems =
 * (ArrayList<WorkItem>)
 * ticketSystemService.getWorkItemsFromTeam(ticketSystemService.getTeam(
 * "woooooo")); assertThat(workItems.size(), equalTo(3)); }
 * 
 * @Test public void getAllWorkItems(){ ticketSystemService.addWorkItem(new
 * WorkItem("heloooo", "BLAH", "DO_IT", 9001));
 * ticketSystemService.addWorkItem(new WorkItem("meeeee", "Bibib", "DOIT", 3));
 * ticketSystemService.addWorkItem(new WorkItem("diiiii", "loooo", "DO.IT", 2));
 * workItems = (ArrayList<WorkItem>) ticketSystemService.getAllWorkItems();
 * assertThat(workItems.size(), equalTo(3)); };
 * 
 * @Test public void getWorkItemsWithIssue(){ WorkItem workItem = new
 * WorkItem("heloooo", "BLAH", "DO_IT", 9001); WorkItem workItem2 = new
 * WorkItem("meeeee", "Bibib", "DOIT", 3);
 * ticketSystemService.addWorkItem(workItem);
 * ticketSystemService.addWorkItem(workItem2); Issue issue = new
 * Issue("hello","blabla",1); workItem.setIssue(issue);
 * workItem2.setIssue(issue); ticketSystemService.addWorkItem(workItem);
 * ticketSystemService.addWorkItem(workItem2); assertThat(
 * "should have two work items with issuies", 2,
 * is(ticketSystemService.getWorkItemsWithIssue().size())); }
 * 
 * @Test public void getAllWorkItemsWithStatus(){ WorkItem workItem1 = new
 * WorkItem("heloooo", "BLAH", "DO_IT", 9001); WorkItem workItem2 = new
 * WorkItem("meeeee", "Bibib", "DOIT", 3); WorkItem workItem3 = new
 * WorkItem("diiiii", "loooo", "DOIT", 2);
 * ticketSystemService.addWorkItem(workItem1);
 * ticketSystemService.addWorkItem(workItem2);
 * ticketSystemService.addWorkItem(workItem3); assertThat( 2,
 * is(ticketSystemService.getAllWorkItemsWithStatus("DOIT").size())); }
 * 
 * 
 * @Test public void getWorkItemWithDescriptionLike(){ WorkItem workItem1 = new
 * WorkItem("heloooo", "This test contains LIKE in reposirory.", "DO_IT", 9001);
 * ticketSystemService.addWorkItem(workItem1); assertThat( 1,
 * is(ticketSystemService.getWorkItemWithDescriptionLike("contains LIKE"
 * ).size())); }
 * 
 * @Test public void getWorkItemsFromUser(){
 * 
 * WorkItem workItem1 = new WorkItem("heloooo",
 * "This test contains LIKE in reposirory.", "DO_IT", 9001);
 * ticketSystemService.addWorkItem(workItem1); user = new User("920406",
 * "Aseel", "Aseel", "wwwwww", "Elfen", team); user.addWorkItem(workItem1);
 * ticketSystemService.addUser(user); assertThat( 1,
 * is(ticketSystemService.getWorkItemsFromUser("920406").size()));
 * 
 * }
 * 
 * @Test public void canChangeWorkItemStatusTest (){ WorkItem workItem1 = new
 * WorkItem("heloooo", "This test contains LIKE in reposirory.", "DO_IT", 9001);
 * ticketSystemService.addWorkItem(workItem1);
 * ticketSystemService.changeWorkItemStatus(workItem1, "DONE");
 * assertThat("DONE",
 * is(ticketSystemService.getWorkItem("heloooo").getStatus()));
 * 
 * }
 * 
 * //Issue test methods
 * 
 * @Test public void addIssueTest(){ Issue issue = new
 * Issue("hello","blabla",1); Issue newIssue =
 * ticketSystemService.addIssue(issue); newIssue =
 * ticketSystemService.getIssue(issue.getId());
 * assertThat(newIssue.getPriority(), equalTo(1)); }
 * 
 * @Test public void updateIssue(){ Issue issue = new Issue("hello","blabla",1);
 * ticketSystemService.addIssue(issue); Issue updatedIssue =
 * ticketSystemService.getIssue(1L); updatedIssue.setName("hejhej");
 * assertThat(ticketSystemService.updateIssue(updatedIssue).getName(),
 * equalTo("hejhej")); assertEquals(updatedIssue,
 * ticketSystemService.updateIssue(updatedIssue)); }
 * 
 * @Test public void removeIssue(){ Issue issue = new Issue("hello","blabla",1);
 * ticketSystemService.addIssue(issue); Issue deletedIssue =
 * ticketSystemService.getIssue(1L);
 * ticketSystemService.removeIssue(deletedIssue.getId());
 * assertThat(ticketSystemService.getIssue(deletedIssue.getId()),
 * equalTo(null)); }
 * 
 * 
 * @AfterClass public static void tearDown(){ context.close(); }
 * 
 * 
 * }
 */