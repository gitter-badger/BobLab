package com.bobwang.json;

import java.util.List;

/*
 {
  "latency": 0,
  "list": [
    {
      "utterances": [
        {
          "intention_type": "PROBLEM",
          "sentiment": "Negative",
          "topics": [
            "java"
          ],
          "content": "I hate JAVA.",
          "sentiment_score": -100,
          "sentiment_confidence": 100,
          "topic_confidence": 100,
          "intention_type_confidence": 100
        }
      ],
      "sentiment_score": -100,
      "sentiment": "Negative",
      "sentiment_confidence": 100,
      "classifiers": {}
    }
  ],
  "ok": true
}
 */

public class SubmitResponse {
	private int latency;
	private List<Item> list;;
	private boolean ok;

	public List<Item> getList() {
		return list;
	}
	public void setList(List<Item> list) {
		this.list = list;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public int getLatency() {
		return latency;
	}
	public void setLatency(int latency) {
		this.latency = latency;
	}
	@Override
	public String toString() {
		return "SubmitResponse [latency=" + latency + ", list=" + list + ", ok=" + ok + "]";
	}
	
	
}