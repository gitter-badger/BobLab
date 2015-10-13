package com.bobwang.json;

import java.util.List;

/*
 * {
        "intention_type": "PROBLEM",
        "sentiment": "Neutral",
        "topics": [
          "gennovation day",
          "test post"
        ],
        "content": "Test post for Gennovation Day.",
        "sentiment_score": 0,
        "sentiment_confidence": 5,
        "topic_confidence": 92,
        "intention_type_confidence": 2
      }
 */
public class Utterance {
	private String intention_type;
	private String sentiment;
	private List<String> topics;
	private String content;
	private int sentiment_score;
	private int sentiment_confidence;
	private int topic_confidence;
	private int intention_type_confidence;
	public String getIntention_type() {
		return intention_type;
	}
	public void setIntention_type(String intention_type) {
		this.intention_type = intention_type;
	}
	public String getSentiment() {
		return sentiment;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	public List<String> getTopics() {
		return topics;
	}
	public void setTopics(List<String> topics) {
		this.topics = topics;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getSentiment_score() {
		return sentiment_score;
	}
	public void setSentiment_score(int sentiment_score) {
		this.sentiment_score = sentiment_score;
	}
	public int getSentiment_confidence() {
		return sentiment_confidence;
	}
	public void setSentiment_confidence(int sentiment_confidence) {
		this.sentiment_confidence = sentiment_confidence;
	}
	public int getTopic_confidence() {
		return topic_confidence;
	}
	public void setTopic_confidence(int topic_confidence) {
		this.topic_confidence = topic_confidence;
	}
	public int getIntention_type_confidence() {
		return intention_type_confidence;
	}
	public void setIntention_type_confidence(int intention_type_confidence) {
		this.intention_type_confidence = intention_type_confidence;
	}
	@Override
	public String toString() {
		return "Utterance [intention_type=" + intention_type + ", sentiment=" + sentiment + ", topics=" + topics + ", content=" + content + ", sentiment_score=" + sentiment_score + ", sentiment_confidence=" + sentiment_confidence
				+ ", topic_confidence=" + topic_confidence + ", intention_type_confidence=" + intention_type_confidence + "]";
	}
	
}
