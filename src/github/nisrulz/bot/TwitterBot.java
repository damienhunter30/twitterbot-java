package github.nisrulz.bot;

import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@SuppressWarnings("unused")
public class TwitterBot {
	static boolean debug = false;

	// if something goes wrong, we might see a TwitterException
	public static void main(String... args){
		// send a tweet
		if(!debug){
			try{
				sendTweet("Bot test tweet. #Linux"); // Send Tweet
				getHomeTimeLine(); // Get tweets from Home Timeline

				// Seach for tweets
				// searchForTweets("@gdg_nd");
			}
			catch(TwitterException e){
				e.printStackTrace();
			}
		}
		else{
			System.out.println("Debug Mode Enabled!");
		}
	}

	private static Status sendTweet(String text) throws TwitterException {
		// access the twitter API using your twitter4j.properties file
		// The factory instance is re-useable and thread safe.
		Twitter twitter = TwitterFactory.getSingleton();
		Status status = null;

		status = twitter.updateStatus(text);
		System.out.println("Successfully updated the status to [" + status.getText() + "].");

		return status;
	}

	private static void getHomeTimeLine() throws TwitterException{
		Twitter twitter = TwitterFactory.getSingleton();
		List<Status> statuses = null;
		statuses = twitter.getHomeTimeline();

		System.out.println("Showing home timeline.");

		if(statuses != null){
			for(Status status : statuses){
				System.out.println(status.getUser().getName() + ":" + status.getText());
			}
		}
	}

	private static void searchForTweets(String query_text) throws TwitterException {
		Twitter twitter = TwitterFactory.getSingleton();
		Query query = new Query(query_text);
		QueryResult result;
		result = twitter.search(query);

		for (Status status : result.getTweets()){
			System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
		}
	}
}