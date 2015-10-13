package com.bobwang.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonTest {

	public static void main(String[] args) {
		Gson gson = new Gson();

		Student student1 = new Student();
		student1.setId(1);
		student1.setName("李坤");
		student1.setBirthday(new Date());

		// //////////////////////////////////////////////////////////
		System.out.println("----------简单对象之间的转化-------------");
		// 简单的bean转为json
		String s1 = gson.toJson(student1);
		System.out.println("简单Bean转化为Json===" + s1);

		// json转为简单Bean
		Student student = gson.fromJson(s1, Student.class);
		System.out.println("Json转为简单Bean===" + student);
		// 结果:
		// 简单Bean转化为Json==={"id":1,"name":"李坤","birthDay":"Jun 22, 2012 8:27:52
		// AM"}
		// Json转为简单Bean===Student [birthDay=Fri Jun 22 08:27:52 CST 2012, id=1,
		// name=李坤]
		// //////////////////////////////////////////////////////////

		Student student2 = new Student();
		student2.setId(2);
		student2.setName("曹贵生");
		student2.setBirthday(new Date());

		Student student3 = new Student();
		student3.setId(3);
		student3.setName("柳波");
		student3.setBirthday(new Date());

		List<Student> list = new ArrayList<Student>();
		list.add(student1);
		list.add(student2);
		list.add(student3);

		System.out.println("----------带泛型的List之间的转化-------------");
		// 带泛型的list转化为json
		String s2 = gson.toJson(list);
		System.out.println("带泛型的list转化为json==" + s2);

		// json转为带泛型的list
		List<Student> retList = gson.fromJson(s2, new TypeToken<List<Student>>() {
		}.getType());
		for (Student stu : retList) {
			System.out.println(stu);
		}

		// 结果:
		// 带泛型的list转化为json==[{"id":1,"name":"李坤","birthDay":"Jun 22, 2012
		// 8:28:52 AM"},{"id":2,"name":"曹贵生","birthDay":"Jun 22, 2012 8:28:52
		// AM"},{"id":3,"name":"柳波","birthDay":"Jun 22, 2012 8:28:52 AM"}]
		// Student [birthDay=Fri Jun 22 08:28:52 CST 2012, id=1, name=李坤]
		// Student [birthDay=Fri Jun 22 08:28:52 CST 2012, id=2, name=曹贵生]
		// Student [birthDay=Fri Jun 22 08:28:52 CST 2012, id=3, name=柳波]

		String response = "{\n  \"item\": {\"utterances\": [\n      {\n        \"intention_type\": \"PROBLEM\",\n        \"sentiment\": \"Neutral\",\n        \"topics\": [\n          \"gennovation day\",\n          \"test post\"\n        ],\n        \"content\": \"Test post for Gennovation Day.\",\n        \"sentiment_score\": 0,\n        \"sentiment_confidence\": 5,\n        \"topic_confidence\": 92,\n        \"intention_type_confidence\": 2\n      }\n    ],\n    \"sentiment\": \"Neutral\",\n    \"id\": 18230400424067330,\n    \"content\": \"Test post for Gennovation Day.\",\n    \"sentiment_score\": 0,\n    \"sentiment_confidence\": 5,\n    \"matchables\": [],\n    \"smart_tags\": [\n      {\n        \"confidence\": 50,\n        \"uri\": \"https://staging.socialoptimizr.com/api/v2.0/smarttags/56169254959a920f57eb58b9\",\n        \"id\": \"56169254959a920f57eb58b9\",\n        \"name\": \"CustomerComplain\"\n      },\n      {\n        \"confidence\": 50,\n        \"uri\": \"https://staging.socialoptimizr.com/api/v2.0/smarttags/56169266fab3a56ec268fe38\",\n        \"id\": \"56169266fab3a56ec268fe38\",\n        \"name\": \"Emergency_Line\"\n      },\n      {\n        \"confidence\": 50,\n        \"uri\": \"https://staging.socialoptimizr.com/api/v2.0/smarttags/5616926ffab3a56ebf68fd05\",\n        \"id\": \"5616926ffab3a56ebf68fd05\",\n        \"name\": \"General_Inquiry\"\n      },\n      {\n        \"confidence\": 50,\n        \"uri\": \"https://staging.socialoptimizr.com/api/v2.0/smarttags/56169289959a920f57eb58bb\",\n        \"id\": \"56169289959a920f57eb58bb\",\n        \"name\": \"MembershipInquiry\"\n      },\n      {\n        \"confidence\": 50,\n        \"uri\": \"https://staging.socialoptimizr.com/api/v2.0/smarttags/56169293fab3a56ec268fe3a\",\n        \"id\": \"56169293fab3a56ec268fe3a\",\n        \"name\": \"Nook_Support\"\n      },\n      {\n        \"confidence\": 50,\n        \"uri\": \"https://staging.socialoptimizr.com/api/v2.0/smarttags/5616929c959a920f54eb5783\",\n        \"id\": \"5616929c959a920f54eb5783\",\n        \"name\": \"Order_Status\"\n      }\n    ],\n    \"actionability\": 50\n  },\n  \"ok\": true\n}";
		PostResponse resp = gson.fromJson(response, new TypeToken<PostResponse>() {
		}.getType());
		Item item = resp.getItem();
		System.out.println(item);
		List<Utterance> uttList = item.getUtterances();
		for (Utterance utt : uttList) {
			System.out.println(utt);
		}

		response = "{  \"latency\": 27,  \"list\": [    {      \"utterances\": [        {          \"intention_type\": \"ASKS\",          \"sentiment\": \"Neutral\",          \"topics\": [            \"ipod headphones\",            \"headphones\"          ],          \"content\": \"iPOD headphones are crap can someone recommend some good headphones\",          \"sentiment_score\": 0,          \"sentiment_confidence\": 0,          \"topic_confidence\": 67,          \"intention_type_confidence\": 41        }      ],      \"sentiment_score\": -100,      \"sentiment\": \"Negative\",      \"sentiment_confidence\": 100,      \"classifiers\": {}    }  ],  \"ok\": true}";
		SubmitResponse sresp = gson.fromJson(response, new TypeToken<SubmitResponse>() {
		}.getType());
		List<Item> items = sresp.getList();
		for (Item item1 : items) {
			System.out.println(item1);
			List<Utterance> uttList1 = item1.getUtterances();
			for (Utterance utt : uttList1) {
				System.out.println(utt);
			}
		}
		
		response = "{  \"result\":[    {      \"alternative\":[        {          \"transcript\":\"this is a test\",          \"confidence\":0.97321892        },        {          \"transcript\":\"this is a test for\"        }      ],      \"final\":true    }  ],  \"result_index\":0}";
		GoogleRecognizeResult result = gson.fromJson(response, new TypeToken<GoogleRecognizeResult>() {
		}.getType());
		List<Result> results = result.getResult();
		for(Result res : results){
			List<Alternative> alters = res.getAlternative();
			for(Alternative alter : alters){
				System.out.println(alter.getTranscript());
			}
		}
	}
}
