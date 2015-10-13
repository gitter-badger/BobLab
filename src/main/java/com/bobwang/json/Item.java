package com.bobwang.json;

import java.util.List;

/*
 * {
    "utterances": [
      {
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
    ],
    "sentiment": "Neutral",
    "id": 18230400424067330,
    "content": "Test post for Gennovation Day.",
    "sentiment_score": 0,
    "sentiment_confidence": 5,
    "matchables": [],
    "smart_tags": [
      {
        "confidence": 50,
        "uri": "https://staging.socialoptimizr.com/api/v2.0/smarttags/56169254959a920f57eb58b9",
        "id": "56169254959a920f57eb58b9",
        "name": "CustomerComplain"
      },
      {
        "confidence": 50,
        "uri": "https://staging.socialoptimizr.com/api/v2.0/smarttags/56169266fab3a56ec268fe38",
        "id": "56169266fab3a56ec268fe38",
        "name": "Emergency_Line"
      },
      {
        "confidence": 50,
        "uri": "https://staging.socialoptimizr.com/api/v2.0/smarttags/5616926ffab3a56ebf68fd05",
        "id": "5616926ffab3a56ebf68fd05",
        "name": "General_Inquiry"
      },
      {
        "confidence": 50,
        "uri": "https://staging.socialoptimizr.com/api/v2.0/smarttags/56169289959a920f57eb58bb",
        "id": "56169289959a920f57eb58bb",
        "name": "MembershipInquiry"
      },
      {
        "confidence": 50,
        "uri": "https://staging.socialoptimizr.com/api/v2.0/smarttags/56169293fab3a56ec268fe3a",
        "id": "56169293fab3a56ec268fe3a",
        "name": "Nook_Support"
      },
      {
        "confidence": 50,
        "uri": "https://staging.socialoptimizr.com/api/v2.0/smarttags/5616929c959a920f54eb5783",
        "id": "5616929c959a920f54eb5783",
        "name": "Order_Status"
      },
      {
        "confidence": 50,
        "uri": "https://staging.socialoptimizr.com/api/v2.0/smarttags/561692abfab3a56ec268fe3c",
        "id": "561692abfab3a56ec268fe3c",
        "name": "ReturnsandRefund"
      },
      {
        "confidence": 50,
        "uri": "https://staging.socialoptimizr.com/api/v2.0/smarttags/561692b35b428d4cbdb8531a",
        "id": "561692b35b428d4cbdb8531a",
        "name": "Special_Events"
      },
      {
        "confidence": 50,
        "uri": "https://staging.socialoptimizr.com/api/v2.0/smarttags/561692bcfab3a56ec268fe3e",
        "id": "561692bcfab3a56ec268fe3e",
        "name": "Stock_Inquiry"
      },
      {
        "confidence": 50,
        "uri": "https://staging.socialoptimizr.com/api/v2.0/smarttags/561692c4959a920f57eb58bd",
        "id": "561692c4959a920f57eb58bd",
        "name": "Store_Information"
      }
    ],
    "actionability": 50
  }
 */
public class Item {
	private List<Utterance> utterances;
	private String sentiment;
	private long id;
	private String content;
	private int sentiment_score;
	private int sentiment_confidence;
	private List matchables;
	private List<SmartTag> smart_tags;
	private int actionability;

	public List getUtterances() {
		return utterances;
	}
	public void setUtterances(List utterances) {
		this.utterances = utterances;
	}
	public String getSentiment() {
		return sentiment;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public List getMatchables() {
		return matchables;
	}
	public void setMatchables(List matchables) {
		this.matchables = matchables;
	}
	public List<SmartTag> getSmart_tags() {
		return smart_tags;
	}
	public void setSmart_tags(List<SmartTag> smart_tags) {
		this.smart_tags = smart_tags;
	}
	public int getActionability() {
		return actionability;
	}
	public void setActionability(int actionability) {
		this.actionability = actionability;
	}
	@Override
	public String toString() {
		return "Item [utterance=" + utterances + ", sentiment=" + sentiment + ", id=" + id + ", content=" + content + ", sentiment_score=" + sentiment_score + ", sentiment_confidence=" + sentiment_confidence + ", matchables=" + matchables
				+ ", smart_tags=" + smart_tags + ", actionability=" + actionability + "]";
	}
	
	
}
