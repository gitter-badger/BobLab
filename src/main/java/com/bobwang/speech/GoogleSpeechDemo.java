package com.bobwang.speech;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import com.bobwang.json.Item;
import com.bobwang.json.SubmitResponse;
import com.bobwang.json.Utterance;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mstr.ValidationException;

public class GoogleSpeechDemo {
	/**
	 * The base URL for the API
	 */
	private static final String GOOGLE_SPEECH_BASE = "https://www.google.com/speech-api/v2/recognize?output=json&lang=en-us&key=AIzaSyATzOE0deDFT0gA-2oVM0rdQ51ZHtnKBuw";

	public static void main(String[] args) {
		Map<String, String> parameters = new HashMap<String, String>();
		// parameters.put("output", "json");
		// parameters.put("lang", "en-us");
		// parameters.put("key", "AIzaSyATzOE0deDFT0gA-2oVM0rdQ51ZHtnKBuw ");

		try {
			// String path =
			// "http://10.52.106.66:8080/messages/0a3f14c2-04-14085aa9528-6ae3ab73-c4a/2015/10/7/18/0a346a4f-30-15043a4a9da-63ac13af-740.wav";
			// String path =
			// "/Users/cwang/O;703-673-1644;Genovation+Demo;New+Voicemail+Page;0a346a4f-30-150435ef4c3-c0ba413b-740.wav";
			// System.out.println("TRANSCRIPTION:\n"+transcribe(GOOGLE_SPEECH_BASE,
			// parameters, path));
			// getTranscription("{\"result\":[]}\n{\"result\":[{\"alternative\":[{\"transcript\":\"testing
			// testing testing 1 2
			// 3\",\"confidence\":0.7699607},{\"transcript\":\"testing testing
			// testing one two three\"},{\"transcript\":\"testing testing
			// testing one two Street\"},{\"transcript\":\"testing testing
			// testing one to three\"},{\"transcript\":\"testing testing testing
			// one two string\"}],\"final\":true}],\"result_index\":0}");
//			String input = "{\"latency\": 31,\"list\": [{\"utterances\": [  {  \"intention_type\": \"PROBLEM\",\"sentiment\": \"Negative\",\"topics\": [ \n \"computer\"],\"content\": \"Hi I have a problem with my computer, please call back my number 202-802-1326, it's very urgent, please help\",\"sentiment_score\": -100,\"sentiment_confidence\": 100,\"topic_confidence\": 89,\"intention_type_confidence\": 100  }],\"sentiment_score\": -100,\"sentiment\": \"Negative\",\"sentiment_confidence\": 100,\"classifiers\": {}    }  ],  \"ok\": true}";
//			System.out.println(getSentiment(input));
//			System.out.println(getTopic(input));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (ValidationException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		
		SubmitResponse result = getSentimentAndTopic("I hate JAVA.");
		List<Item> items = result.getList();
		for (Item item1 : items) {
			System.out.println(item1);
			List<Utterance> uttList1 = item1.getUtterances();
			for (Utterance utt : uttList1) {
				System.out.println(utt.getSentiment());
				for(String topic : utt.getTopics()){
					System.out.println(topic);
				}
			}
		}

	}

	public static String transcribe(String postUrl, Map<String, String> params, String inputFile) throws ParseException, IOException, ValidationException {
		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

		HttpPost httppost = new HttpPost(postUrl);

		// WavToWavConverter waveToWave = new WavToWavConverter();

		String outFile = "/Users/cwang/test.wav";
		//
		// File wavFileOut = new File(outFile);
		// wavFileOut.createNewFile();
		//
		// File wavFileIn = new File(inputFile);
		// FileInputStream wavInputStream = new FileInputStream(wavFileIn);
		// DataInputStream diStream = new DataInputStream(new
		// BufferedInputStream(wavInputStream));
		// byte[] wavData = readSoundData(diStream);
		//
		// byte[] waveByteData = convert8bitTo16Bit(wavData);
		// DataOutputStream soundOutStream =
		// ConverterUtils.initOutStream(FileUtils.createFileOutputStream(wavFileOut));
		// int soundFileSize =
		// ConverterUtils.makeFileSizeEven(waveByteData.length);
		//
		// ConverterUtils.writeHeaderToSoundFile(soundOutStream,
		// ConverterUtils.getWavePcmHeader(), soundFileSize);
		// ConverterUtils.writeSoundData(soundOutStream, waveByteData);
		// ConverterUtils.closeInAndOutStreams(diStream, soundOutStream);

		String soxCommand = "/usr/local/bin/sox -b 8 -r 8000 " + inputFile + " -b 16 -r 8000 " + outFile + "";
		String[] soxCommands = soxCommand.split(" ");

		System.out.print("Sox command is: ");
		for (String command : soxCommands) {
			System.out.print(" " + command);
		}
		System.out.println();
		Process p = Runtime.getRuntime().exec(soxCommand);
		try {
			System.out.println(p.waitFor());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		// long fileSize = wavFileIn.length();
		// int frameSize = 160;
		// long numFrames = fileSize / frameSize;
		// AudioFormat audioFormat = new AudioFormat(Encoding.ULAW, 8000, 8, 1,
		// frameSize, 50, true);
		// AudioInputStream audioInputStream = new AudioInputStream(new
		// FileInputStream(wavFileIn), audioFormat, numFrames);
		// AudioSystem.write(audioInputStream, Type.WAVE, wavFileOut);

		// FlacEncoder flacEncoder = new FlacEncoder();
		// String flacFile = "/Users/cwang/test.flac";
		// flacEncoder.convertWaveToFlac(outFile, flacFile);

		// if (inputFile.endsWith(".wav"))
		// waveToWave.convertWavToWav(new File(inputFile), file);

		// FileEntity reqEntity = new FileEntity(new File(outFile), "audio/wav;
		// rate=8000;");
		FileEntity reqEntity = new FileEntity(new File(outFile), "audio/l16; rate=8000;");

		httppost.setEntity(reqEntity);

		BasicHttpParams httpParams = new BasicHttpParams();
		for (Entry<String, String> entry : params.entrySet()) {
			httpParams.setParameter(entry.getKey(), entry.getValue());
		}
		httppost.setParams(httpParams);

		System.out.println("executing request " + httppost.getRequestLine());
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity resEntity = response.getEntity();

		System.out.println(response.getStatusLine());

		String transcript = "";
		if (resEntity != null) {
			transcript = getTranscription(EntityUtils.toString(resEntity));
			System.out.println(transcript);
		}
		if (resEntity != null) {
			resEntity.consumeContent();
		}

		httpclient.getConnectionManager().shutdown();

		return transcript;
	}

	static String getTranscription(String input) {
		input = input.substring("{\"result\":[]}\n{\"result\":[{\"alternative\":[{\"transcript\":\"".length());
		input = input.substring(0, input.indexOf('\"'));
		System.out.println(input);
		return input;

	}

	private static String getSentiment(String input) {
		String prefix = "\"sentiment\": \"";
		int index = input.indexOf(prefix) + prefix.length();
		input = input.substring(index);
		input = input.substring(0, input.indexOf('\"'));
		return input;
	}

	private static String getTopic(String input) {
		String prefix = "\"topics\": [";
		int index = input.indexOf(prefix) + prefix.length();
		input = input.substring(index);
		input = input.substring(0, input.indexOf(']'));
		return input.trim().replace("\"", "");
	}
	
	private static SubmitResponse getSentimentAndTopic(String transcription) {
		String SENTIMENT_BASE = "https://staging.socialoptimizr.com/api/v2.0/analyzer/submit";

		HttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

		HttpPost httpPost = new HttpPost(SENTIMENT_BASE);
		String jsonStr = "{\"token\": \"820b0587200e5952651c57fcff819ade2c702c3e\", \"samples\": [\""+transcription+"\"]}";
		SubmitResponse sresp = null;
		try {

//			httpPost.setEntity(new StringEntity(jsonStr, ContentType.APPLICATION_JSON));
			StringEntity params =new StringEntity(jsonStr);
			httpPost.addHeader("content-type", "application/json");
			httpPost.addHeader("Accept","application/json");
			httpPost.setEntity(params);
			HttpResponse response = httpclient.execute(httpPost);
			HttpEntity resEntity = response.getEntity();

			System.out.println(response.getStatusLine());

			if (resEntity != null) {
				String res = EntityUtils.toString(resEntity);
				System.out.println(res);
				Gson gson = new Gson();
				sresp = gson.fromJson(res, new TypeToken<SubmitResponse>() {
				}.getType());
			}
			if (resEntity != null) {
				resEntity.consumeContent();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return sresp;
	}
}
