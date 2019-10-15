//Subject.java
//Olivia Clough 5487343
//Ryan Mitchell 5761994

public interface Subject {
    //interface
    
    public void registerUserTag(String tag, User user);
    //Subscribes a user to receive updates for any posts containing the specific tag.
    
    public void removeUserTag(String tag, User user);
    //Unsubscribes a user from receiving updates for any posts containing the specific tag.
    
    public void notifyUsers(Post p);
    //Whenever a post is added, notifyUsers is called to send updates (if any) to subscribed users for the specific tags in the post.

}
