//User.java
//Olivia Clough 5487343
//Ryan Mitchell 5761994

import java.util.ArrayList;

public class User implements Observer{
    static int counter = 0;
    private int userID;

    public User(){
	//constructor
	counter++;
	userID = counter;
    }

    public int getID() {return this.userID;}

    public void displayTags(Post p) {
	System.out.print("Tags: ");
	ArrayList<String> tagging = p.getTags();
	for (int i=0; i<tagging.size(); i++){
	    if (i == tagging.size()-1)
		System.out.println(tagging.get(i));
	    else
		System.out.print(tagging.get(i)+ " ");
	}
    }

    @Override
    public void update(Post post){
	//define method
	System.out.println("***** Updating UserID: "+userID+" *****");
	System.out.println("--");
	System.out.println("Post ID: "+post.getPostID());
        displayTags(post);
	System.out.println("Posted by UserID: "+post.getUserID());
	if (post.getParentID() != -1){
	    System.out.println("Re: to Post ID: "+ post.getParentID());
	}
	System.out.println("Message: "+post.getMessage());
	System.out.println("--");
	System.out.println("******************************");
	System.out.println();

    }

}
