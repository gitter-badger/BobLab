package com.bobwang.json;

/*
 * "smart_tags": [
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
    ]
 */
public class SmartTag {
	private int confidence;
	private String uri;
	private String id;
	private String name;
	public int getConfidence() {
		return confidence;
	}
	public void setConfidence(int confidence) {
		this.confidence = confidence;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "SmartTag [confidence=" + confidence + ", uri=" + uri + ", id=" + id + ", name=" + name + "]";
	}
	
	
}
