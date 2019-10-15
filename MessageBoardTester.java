//MessageBoardTester.java
//Olivia Clough 5487343
//Ryan Mitchell 5761994

import org.junit.Test;
import org.junit.Before;
import org.junit.After;


import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashSet;

public class MessageBoardTester {

    private MessageBoardManager message;
    
    @Before // Executed before each test in this class
    public void executeBeforeEachTest() {
        System.out.println("@Before: before test ... ");
        message = new MessageBoardManager();
    }

    @Test
    public void testingAddPost() {
	System.out.println("TESTING ADD POST");
	System.out.println();
	
        ArrayList<String> tags = new ArrayList<String>();
        tags.add("cooking");
        tags.add("food");
        tags.add("yum");
        User user = new User();
        Post p1 = new Post(tags, "I love making pasta!", user, -1);
        message.addPost(p1);


	ArrayList<Post> testingAdd = message.getPostArray();

	assertEquals(testingAdd.get(0), p1);


    }

    @Test
    public void testingReplyPost() {
	System.out.println("TESTING REPLY POST");
	System.out.println();
	
        ArrayList<String> tags = new ArrayList<String>();
        tags.add("cooking");
        tags.add("food");
        tags.add("yum");
        User user = new User();
        Post p1 = new Post(tags, "I love making pasta!", user, -1);
        message.addPost(p1);
        
        ArrayList<String> tags1 = new ArrayList<String>();
        tags1.add("cook");
        tags1.add("food");
        tags1.add("burger");
        User user1 = new User();
        Post p2 = new Post(tags, "My favorite is burgers", user1, p1.getPostID());
        message.addReply(p2);

	ArrayList<Post> testingAdd = message.getPostArray();

	assertEquals(testingAdd.get(0), p1);
	assertEquals(testingAdd.get(1), p2);
	
    }

    @Test
    public void testingRegisterTag() {
	System.out.println("TESTING REGISTER TAG");
	System.out.println();
	
	User u1 = new User();
	message.registerUserTag("soccer", u1);
	message.registerUserTag("sports", u1);
	User u2 = new User();
	message.registerUserTag("soccer", u2);
	User u3 = new User();
	message.registerUserTag("cleaning", u3);

	LinkedHashMap<String, HashSet<User>> testingReg = message.getKeyMap();
	
	assertEquals(testingReg.containsKey("soccer"), true);
	assertEquals(testingReg.containsKey("sports"), true);
	assertEquals(testingReg.containsKey("cleaning"), true);

	HashSet<User> testHash = new HashSet<User>();
	
	testHash.add(u1);
	testHash.add(u2);
	assertEquals(testingReg.get("soccer"), testHash);
	testHash.clear();

	testHash.add(u1);
	assertEquals(testingReg.get("sports"), testHash);
	testHash.clear();

	testHash.add(u3);
	assertEquals(testingReg.get("cleaning"), testHash);

    }

    @Test
    public void testingRemovingTag(){
        System.out.println("TESTING REMOVE TAG");
	System.out.println();
	
	User u1 = new User();
	message.registerUserTag("computer science", u1);
	message.registerUserTag("school", u1);
	User u2 = new User();
	message.registerUserTag("java", u2);
	User u3 = new User();
	message.registerUserTag("school", u3);

	LinkedHashMap<String, HashSet<User>> testingReg = message.getKeyMap();
	
	assertEquals(testingReg.containsKey("computer science"), true);
	assertEquals(testingReg.containsKey("school"), true);
	assertEquals(testingReg.containsKey("java"), true);

	HashSet<User> testHash = new HashSet<User>();
	
	testHash.add(u1);
	testHash.add(u3);
	assertEquals(testingReg.get("school"), testHash);
	testHash.clear();

	testHash.add(u1);
	assertEquals(testingReg.get("computer science"), testHash);
	testHash.clear();

	testHash.add(u2);
	assertEquals(testingReg.get("java"), testHash);
	testHash.clear();

	message.removeUserTag("school", u1);
	message.removeUserTag("java", u2);

	LinkedHashMap<String, HashSet<User>> testingRemove = message.getKeyMap();
	assertEquals(testingRemove.containsKey("java"), true);

	testHash.add(u3);
	assertEquals(testingRemove.get("school"), testHash);

	testHash.clear();
	assertEquals(testingRemove.get("java"), testHash);
	
    }

    @After
    public void executeAfterTest() {
        System.out.println("@After: test completed");
	System.out.println();
    }

}
