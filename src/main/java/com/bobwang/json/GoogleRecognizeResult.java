package com.bobwang.json;

import java.util.List;

/*
{
  "result":[
    {
      "alternative":[
        {
          "transcript":"this is a test",
          "confidence":0.97321892
        },
        {
          "transcript":"this is a test for"
        }
      ],
      "final":true
    }
  ],
  "result_index":0
}
 */
public class GoogleRecognizeResult {
	private List<Result> result;
	private int result_index;
	public List<Result> getResult() {
		return result;
	}
	public void setResult(List<Result> result) {
		this.result = result;
	}
	public int getResult_index() {
		return result_index;
	}
	public void setResult_index(int result_index) {
		this.result_index = result_index;
	}
}

class Result {
	private List<Alternative> alternative;
	public List<Alternative> getAlternative() {
		return alternative;
	}
	public void setAlternative(List<Alternative> alternative) {
		this.alternative = alternative;
	}
}

class Alternative{
	private String transcript;
	private float confidence;
	public String getTranscript() {
		return transcript;
	}
	public void setTranscript(String transcript) {
		this.transcript = transcript;
	}
	public float getConfidence() {
		return confidence;
	}
	public void setConfidence(float confidence) {
		this.confidence = confidence;
	}
	
}
