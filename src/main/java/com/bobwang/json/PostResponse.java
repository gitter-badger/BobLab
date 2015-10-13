package com.bobwang.json;

/*
 * {
  "item": {
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
  },
  "ok": true
}
 */

public class PostResponse {
	private Item item;
	private boolean ok;
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	@Override
	public String toString() {
		return "Response [item=" + item + ", ok=" + ok + "]";
	}
	
}