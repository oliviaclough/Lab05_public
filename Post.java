//Post.java
//Olivia Clough 5487343
//Ryan Mitchell 5761994

import java.util.ArrayList;

public class Post {
    private ArrayList<String> tags;
    private String message;
    private User user;
    private int parentID;
    private int postID;

    static int counter = 0;
    private int userID;
    
    public Post(ArrayList<String> tags, String message, User user, int parentID) {
	//constructor
	for (int i=0; i<tags.size(); i++){
	    String adding = tags.get(i).toLowerCase();
	    String there = tags.get(i);
	    
	    tags.remove(there);
	    tags.add(i,adding);
	    
	}
	this.tags = tags;
	this.message = message;
	this.user = user;
	this.parentID = parentID;
	counter++;
	this.postID = counter;
	this.userID = user.getID();
	
    }
    

    public ArrayList<String> getTags() {return this.tags;}
    public String getMessage() {return this.message;}
    public User getUser() {return this.user;}
    public int getPostID() {return this.postID;}
    public int getParentID() {return this.parentID;}
    public int getUserID() {return this.userID;}
    
}
