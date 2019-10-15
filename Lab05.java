//Lab05.java
//Olivia Clough 5487343
//Ryan Mitchell 5761994

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.ArrayList;

public class Lab05 {

    public static void main(String[] args) {
        MessageBoardManager messageBoard = new MessageBoardManager();
        
        // Create users and register tags for them.
        User u1 = new User();
        messageBoard.registerUserTag("dogs", u1);
        User u2 = new User();
        messageBoard.registerUserTag("dogs", u2);
        User u3 = new User();
        messageBoard.registerUserTag("pets", u3);
        
        // Constructing a Post with tags
        ArrayList<String> tags = new ArrayList<String>();
        tags.add("DOGS");
        tags.add("pets");
        Post p1 = new Post(tags, "Dogs are my favorite pet!", u1, -1);
        messageBoard.addPost(p1);
        
        // Removing u2 from the "cooking" tag
        messageBoard.removeUserTag("dogs", u2);
        
        // Creating a reply to p1
        Post p1_1 = new Post(p1.getTags(), "I like cats ):", u3, p1.getPostID());
        messageBoard.addReply(p1_1);
        
        // Creating another reply to p1
        Post p1_2 = new Post(p1.getTags(), "No! Definitely dogs!", u1, p1.getPostID());
        messageBoard.addReply(p1_2);
        
        // Creating a reply to reply p1_1
        Post p1_1_1 = new Post(p1_1.getTags(), "They are so grumpy", u2, p1_1.getPostID());
        messageBoard.addReply(p1_1_1);
        
        // Displays entire thread for p1_1_1's hierarchy
        messageBoard.displayThread(p1_1_1.getPostID());
        
        // Displays all posts for user u1
        messageBoard.displayUserPosts(u1);

	User u4 = new User();
	messageBoard.registerUserTag("FRIes", u4);
        User u5 = new User();
        messageBoard.registerUserTag("BURGers", u5);
	
        // Creates a new post with new tags
        ArrayList<String> tags2 = new ArrayList<String>();
        tags2.add("Burgers");
        tags2.add("fRIes");
        Post p2 = new Post(tags2, "In-n-Out is my favorite!", u3, -1);
        messageBoard.addPost(p2);
        
        messageBoard.displayTagMessages("pets");
        
        messageBoard.displayKeywordMessages("favorite");
        
        messageBoard.displayKeywordMessages("cats");

	messageBoard.displayUserPosts(u3);

	messageBoard.displayTagMessages("FrieS");

	messageBoard.displayKeywordMessages("In");

	messageBoard.displayKeywordMessages("fav");

	messageBoard.displayThread(p1_1.getPostID());
    }
}

