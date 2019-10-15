//MessageBoardManager.java
//Olivia Clough 5487343
//Ryan Mitchell 5761994

import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MessageBoardManager implements Subject{
    private ArrayList<Post> postArray;
    private LinkedHashMap<String, HashSet<User>> keyMap;

    public MessageBoardManager(){
	postArray = new ArrayList<Post>();
	keyMap = new LinkedHashMap<String, HashSet<User>>();
    }

    public ArrayList<Post> getPostArray() {return this.postArray;}
    public LinkedHashMap<String, HashSet<User>> getKeyMap() {return this.keyMap;}
    
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
    
    public void addPost(Post p){
    if (!postArray.add(p)) {
        System.out.println("ERROR: Duplicate Post");
    }
	System.out.println("+++ Adding Post to MessageBoard +++");
	System.out.println("--");
	System.out.println("Post ID: " + p.getPostID());
        displayTags(p);
	System.out.println("Posted by UserID: " + p.getUserID());
	System.out.println("Message: "+ p.getMessage());
	System.out.println("--");
	System.out.println("++++++++++++++++++++++++++++++++++++");
	System.out.println();

    notifyUsers(p);
	
    }

    public void addReply(Post reply){
        for (int i = 0; i < postArray.size(); i++){
	    if (reply.getParentID() == postArray.get(i).getPostID()){
		postArray.add(reply);
		System.out.println("+++ Adding Post to MessageBoard +++");
		System.out.println("--");
		System.out.println("Post ID: "+ reply.getPostID());
	        displayTags(reply);
		System.out.println("Posted by UserID: "+ reply.getUserID());
		System.out.println("Re: to Post ID: "+ reply.getParentID());
		System.out.println("Message: "+ reply.getMessage());
		System.out.println("--");
		System.out.println("++++++++++++++++++++++++++++++++++++");
		System.out.println();

		notifyUsers(reply);
		break;
	    }
	    else{
		continue;
	    }
	}
    }

    public void displayTagMessages(String tag){
	    String tagging = tag.toLowerCase();
	System.out.println("##### Displaying posts with tag: "+tagging+" #####");
	for (int  i = 0; i < postArray.size(); i++) {
	    Post p = postArray.get(i);
	    ArrayList<String> check = p.getTags();
	    for (int x=0; x<check.size(); x++){
	    	if (check.get(x).equals(tagging)) {
				if (p.getParentID() == -1) {
					System.out.println("--");
					System.out.println("Post ID: "+p.getPostID());
					displayTags(p);
					System.out.println("Posted by UserID: "+p.getUserID());
					System.out.println("Message: "+p.getMessage());
					System.out.println("--");
				} else {
					System.out.println("--");
					System.out.println("Post ID: "+ p.getPostID());
					displayTags(p);
					System.out.println("Posted by UserID: "+ p.getUserID());
					System.out.println("Re: to Post ID: "+ p.getParentID());
					System.out.println("Message: "+ p.getMessage());
					System.out.println("--");
				}
			}
			else {
				continue;
			}
	    }
	}
	System.out.println("##############################");
	System.out.println("");

    }

    public void displayKeywordMessages(String keyword){
	//Displays all posts containing the String keyword in
	//the message portion of a post
	System.out.println("##### Displaying posts with keyword: " + keyword + " #####");
		for (int  i = 0; i < postArray.size(); i++) {
			Post p = postArray.get(i);
			String check = p.getMessage();
			if (check.contains(keyword)){
				if (p.getParentID() == -1) {
					System.out.println("--");
					System.out.println("Post ID: "+p.getPostID());
					displayTags(p);
					System.out.println("Posted by UserID: "+p.getUserID());
					System.out.println("Message: "+p.getMessage());
					System.out.println("--");
				} else {
					System.out.println("--");
					System.out.println("Post ID: "+ p.getPostID());
					displayTags(p);
					System.out.println("Posted by UserID: "+ p.getUserID());
					System.out.println("Re: to Post ID: "+ p.getParentID());
					System.out.println("Message: "+ p.getMessage());
					System.out.println("--");
				}
			}
		}
	System.out.println("##############################");
	System.out.println("");
    }

    public void displayThread(int postID) {
	//Displays the entire thread that the postID belongs to.
	//Note that the postID may not be the “root” post in the
	//thread hierarchy.
	//The order of the displayed post must be a depth first
	//traversal that starts with the original post (i.e.
	//Parent Post ID == -1).
	//Then the reply posts are displayed (if any).
	//If a reply also contains replies, this is displayed
	//first before moving on with the rest of the replies for
	//the parent post.
	//An example of this will be shown in the sample console
	//output below.
	//If the postID does not exist, then an ERROR stating so
	//is displayed (nothing is displayed).
		Integer ogPostNum = new Integer(0);
		for (int i = 0; i < postArray.size(); i++) {
			if (postArray.get(i).getPostID() == postID){
				ogPostNum = i;
				break;
			} 
			else if (i == postArray.size() - 1) {
				System.out.println("ERROR: POST DOES NOT EXIST");
			} else {
				continue;
			}
		}
		System.out.println("##### Displaying thread for PostID: : " + postID + " #####");
		Post ogPost = postArray.get(ogPostNum);
		Post p = ogPost;
		while (p.getParentID() != -1) {
			for (int i = 0; i < postArray.size(); i++) {
				if (postArray.get(i).getPostID() == p.getParentID()) {
					p = postArray.get(i);
					break;
				}
			}
		}
		System.out.println("--");
		System.out.println("Post ID: "+p.getPostID());
		displayTags(p);
		System.out.println("Posted by UserID: "+p.getUserID());
		System.out.println("Message: "+p.getMessage());
		System.out.println("--");
		boolean done  = false;
		ArrayList<Post> threadPosts = new ArrayList<Post>();
		int start = 0;
		while ( done == false) {
			for (int i = 0; i <postArray.size(); i++) {
				if ((postArray.get(i).getParentID() == p.getPostID()) && !(threadPosts.contains(postArray.get(i)))) {
					done = false;
					start++;
					p = postArray.get(i);
					threadPosts.add(p);
					break;
				}
			}
			if (p.getParentID() == -1) {
				done = true;
				break;
			}
			else if (start > 1) {
				for(int j = 0; j <postArray.size(); j++) {
					if (postArray.get(j).getPostID() == p.getParentID()) {
						p = postArray.get(j);
						done = false;
						break;
					}
				}
			}
		}
		for (int i = 0; i < threadPosts.size(); i++) {
			p = threadPosts.get(i);
			System.out.println("--");
			System.out.println("Post ID: "+ p.getPostID());
			displayTags(p);
			System.out.println("Posted by UserID: "+ p.getUserID());
			System.out.println("Re: to Post ID: "+ p.getParentID());
			System.out.println("Message: "+ p.getMessage());
			System.out.println("--");
		}
		System.out.println("##############################");
		System.out.println("");
	}
    
    public void displayUserPosts(User user) {
		System.out.println("##### Displaying all posts for User ID: " + user.getID() + " #####");
			for (int i = 0; i < postArray.size(); i++) {
				if (postArray.get(i).getUser().getID() == user.getID()) {
					Post p =postArray.get(i);
					if (postArray.get(i).getParentID() == -1) {
						System.out.println("--");
						System.out.println("Post ID: "+p.getPostID());
						displayTags(p);
						System.out.println("Posted by UserID: "+p.getUserID());
						System.out.println("Message: "+p.getMessage());
						System.out.println("--");
					}
					else {
						System.out.println("--");
						System.out.println("Post ID: "+ p.getPostID());
						displayTags(p);
						System.out.println("Posted by UserID: "+ p.getUserID());
						System.out.println("Re: to Post ID: "+ p.getParentID());
						System.out.println("Message: "+ p.getMessage());
						System.out.println("--");
					}
				}
			}
		System.out.println("##############################");
		System.out.println("");
	//Displays all posts (including replies) the user made.
    }

    @Override
    public void registerUserTag(String tag, User user){
	    String tagging = tag.toLowerCase();
	System.out.println("^^^^^ Adding tag: "+tagging+" for User ID: "+user.getID()+" ^^^^^");
	System.out.println();
	boolean blnExists = keyMap.containsKey(tagging);
	if (blnExists) {
		//add to the hash
		HashSet<User> existingOne = keyMap.get(tagging);
		existingOne.add(user);
		keyMap.put(tagging, existingOne);

	}
	else {
		//add key and user
		HashSet<User> addingTo = new HashSet<User>();
		addingTo.add(user);
		keyMap.put(tagging, addingTo);

		
	}

    }

    @Override
    public void removeUserTag(String tag, User user){
	    String tagging = tag.toLowerCase();
	System.out.println("^^^^^ Removing tag: "+tagging+" for User ID: "+user.getID()+" ^^^^^");
	System.out.println();
	//find key of the tag
	//remove the user from that tag's array
	boolean blnExists = keyMap.containsKey(tagging);
	if (blnExists) {
		//remove
		HashSet<User> value = keyMap.get(tagging);
		for (int a=0; a<value.size(); a++){
		    if (value.contains(user)){
				value.remove(user);
			}
		}
	}
    }

    @Override
    public void notifyUsers(Post p){
	ArrayList<String> tagging = p.getTags();
	HashSet<User> users = new HashSet<User>();
	for (int x=0; x<tagging.size(); x++){
	    if (!keyMap.containsKey(tagging.get(x))){
		    continue;
		}
		else{
		    users = keyMap.get(tagging.get(x));
		    if (!users.isEmpty()){
			for (User i : users){
			    if (i.getID() != p.getUserID())
			    i.update(p);
			}
		    }
		}
	}
	}
    
}
