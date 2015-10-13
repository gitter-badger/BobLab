package com.bobwang.speech;

import java.io.*;

public abstract class ConverterUtils
{
	/**
	 * 12 bit constant -- should not be changed
	 **/
	private static final short TWELVE_BITS = 2047;

	/**
	 * optimal voice stepsize constant -- should not be changed
	 **/
	private static final short OPTIMAL_VOICE_STEPSIZE = 48;

	/**
	 * one byte constant -- should not be changed
	 **/
	public static final byte ONE_BYTE = 8;

	/**
	 * two byte constant -- should not be changed
	 **/
	private static final byte TWO_BYTES = 16;

	/**
	 * three byte constant -- should not be changed
	 **/
	private static final byte THREE_BYTES = 24;

	/**
	 * read buffer size -- maybe optimized if needed
	 **/
	public static final int VOX_READ_BUFFER_SIZE = 4096;

	/**
	 * write buffer size -- should not be changed
	 **/
	public static final int WAV_WRITE_BUFFER_SIZE = 2 * VOX_READ_BUFFER_SIZE;

	/**
	 * write buffer size -- should not be changed
	 **/
	public static final int HEADER_LENGTH = (15 * 4) - 8;

	/**
	 *  step size index shift table  -- should not be changed
	 **/
	private static final short STEPSIZE_INDEX_SHIFT_ADJUSTMENT[] = {-1, -1, -1, -1, 2, 4, 6, 8};

	/**
	 *  This returns step accoring to formula -- should not be changed
	 *  return (int)Math.floor(16*((float)(11.0/10.0))^step);
	 **/
	private static final short STEPSIZE_CALCULATED_TABLE[] =
	        {16, 17, 19, 21, 23, 25, 28, 31, 34, 37, 41, 45, 50, 55, 60, 66, 73, 80, 88,
	         97, 107, 118, 130, 143, 157, 173, 190, 209, 230, 253, 279, 307, 337, 371,
	         408, 449, 494, 544, 598, 658, 724, 796, 876, 963, 1060, 1166, 1282, 1411,
	         1552, 1707, 1878, 2066, 2272, 2499, 2749, 3024, 3327, 3660, 4026, 4428,
	         4871, 5358, 5894, 6484, 7132, 7845, 8630, 9493, 10442, 11487, 12635,
	         13899, 15289, 16818, 18500, 20350, 22385, 24623, 27086, 29794};

	/**
	 * 16 bit nibble to appropriate bit adjustment table [16][4]
	 * nibble to bit map  -- should not be changed
	 **/
	private static final short NIBBLE_TO_BIT_CONVERTER[][] = {
		{0, 0, 0, 0}, {0, 0, 0, 1}, {0, 0, 1, 0}, {0, 0, 1, 1},
		{0, 1, 0, 0}, {0, 1, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1},
		{1, 0, 0, 0}, {1, 0, 0, 1}, {1, 0, 1, 0}, {1, 0, 1, 1},
		{1, 1, 0, 0}, {1, 1, 0, 1}, {1, 1, 1, 0}, {1, 1, 1, 1}};

	/**
	 * needed number of channels
	 */
	public static final int NEEDED_NUMBER_OF_CHANNELS = 2;

	/**
	 * needed frequency
	 */
	public static final int NEEDED_FREQUENCY = 8000;

	/**
	 * needed number bits
	 */
	public static final int NEEDED_BITS = 8;

	/**
	 *  sign table  -- should not be changed
	 **/
	private static final short SIGN_TABLE[] = {1, -1};

	/**
	 *  size is 15 since only 15 wave headers exist
	 *  order of bytes is already swapped -- should not be changed
	 **/
	private static final int WAVE_PCM_HEADER[] =
	        {
		        0x52494646,
		        0xffffffff,
		        0x57415645,
		        0x666d7420,
		        0x12000000,
		        0x01000100,
		        0x401f0000,
		        0x803e0000,
		        0x02001000,
		        0x00006661,
		        0x63740600,
		        0x0000ffff,
		        0xffffffff,
		        0x64617461,
		        0xffffffff
	        };

	private static final int WAVE_MULAW_HEADER[] =
	        {
		        0x52494646,
		        0xffffffff,
		        0x57415645,
		        0x666d7420,
		        0x12000000,
		        0x07000100,
		        0x401f0000,
		        0x401f0000,
		        0x01000800,
		        0x00006661,
		        0x63740600,
		        0x0000ffff,
		        0xffffffff,
		        0x64617461,
		        0xffffffff
	        };

	/**
	 * Our mulaw header is 15 bytes and we need to validate all 15
	 */
	public static final int LENGTH_TO_VALIDATE_OF_OUR_MULAW_HEADER = 15;

	/**
	 * VWS 2.0 mulaw header is 15 bytes but we only need to validate first 10
	 */
	public static final int LENGTH_TO_VALIDATE_OF_VWS2_MULAW_HEADER = 10;

	/**
	 * RMS at which sound file should be according to NUANCE (prevents echo cancellation)
	 */
	public static final double NEEDED_RMS = 2200.0;

	/**
	 * @return waveHeader the wave header
	 */
	public static int[] getWavePcmHeader()
	{
		int[] tempArray = new int[WAVE_PCM_HEADER.length];
		System.arraycopy(WAVE_PCM_HEADER, 0, tempArray, 0, WAVE_PCM_HEADER.length);
		return tempArray;
	}

	/**
	 * @return waveHeader the wave header
	 */
	public static int[] getWaveMulawHeader()
	{
		int[] tempArray = new int[WAVE_MULAW_HEADER.length];
		System.arraycopy(WAVE_MULAW_HEADER, 0, tempArray, 0, WAVE_MULAW_HEADER.length);
		return tempArray;
	}

	/**
	 * Truncates signal to 12 bits, since those are the only acceptable values
	 * @param signal current signal that was read
	 * @return signal	possibly modified signal
	 **/
	public static short truncateSignalIfNeeded(short signal)
	{
		if (signal > TWELVE_BITS)
			return TWELVE_BITS;
		else if (signal < -TWELVE_BITS)
			return (-TWELVE_BITS);

		return signal;
	}


	/**
	 * Does the actual adpcm decode -- SHOULD NOT BE CHANGED
	 * @param encoded the incoded nibble from the adpcm file
	 * @return diff the decoded difference
	 **/
	public static int ADPCMdecode(int encoded, short[] stepSizeIndex)
	{
		int diff;
		//--short stepSizeIndex = 0;


		diff = (
		        SIGN_TABLE[NIBBLE_TO_BIT_CONVERTER[encoded][0]] *
		        ((STEPSIZE_CALCULATED_TABLE[stepSizeIndex[0]] / 1) * NIBBLE_TO_BIT_CONVERTER[encoded][1] +
		        (STEPSIZE_CALCULATED_TABLE[stepSizeIndex[0]] / 2) * NIBBLE_TO_BIT_CONVERTER[encoded][2] +
		        (STEPSIZE_CALCULATED_TABLE[stepSizeIndex[0]] / 4) * NIBBLE_TO_BIT_CONVERTER[encoded][3] +
		        (STEPSIZE_CALCULATED_TABLE[stepSizeIndex[0]] / 8)));

		stepSizeIndex[0] += STEPSIZE_INDEX_SHIFT_ADJUSTMENT[(encoded % 8)];

		if (stepSizeIndex[0] < 0)
			stepSizeIndex[0] = 0;
		else if (stepSizeIndex[0] > OPTIMAL_VOICE_STEPSIZE)
			stepSizeIndex[0] = OPTIMAL_VOICE_STEPSIZE;

		return (diff);
	}


	/**
	 * Does the actual adpcm encode -- SHOULD NOT BE CHANGED
	 * @param csig the decoded 2 bytes from the wav file
	 * @return encoded the encoded value
	 **/
	public static byte ADPCMencode(short csig, short[] stepSizeIndex, short[] signal)
	{
		int diff;
		byte encoded = 0;

		csig = truncateSignalIfNeeded(csig);
		diff = csig - signal[0];

		if (diff < 0)
		{
			encoded = 8;
			diff = -diff;
		}
		if (diff >= STEPSIZE_CALCULATED_TABLE[stepSizeIndex[0]])
		{
			encoded += 4;
			diff -= STEPSIZE_CALCULATED_TABLE[stepSizeIndex[0]];
		}
		if (diff >= STEPSIZE_CALCULATED_TABLE[stepSizeIndex[0]] / 2)
		{
			encoded += 2;
			diff -= STEPSIZE_CALCULATED_TABLE[stepSizeIndex[0]] / 2;
		}
		if (diff >= STEPSIZE_CALCULATED_TABLE[stepSizeIndex[0]] / 4)
		{
			encoded += 1;
		}

		signal[0] += ADPCMdecode(encoded, stepSizeIndex);

		return encoded;
	}


	/**
	 * Simulates making of an unsigned byte in "C"
	 * We add 256 because 8th signed bit changes value by 2^8
	 * Contract for this method is to receive a Valid byte
	 *
	 * @param signedByte	signed "Java" byte
	 * @return unsignedByte	simulated unsigned byte represented by a short
	 **/
	public static short makeUnsignedByte(byte signedByte)
	{
		short unsignedByte = signedByte;
		if (unsignedByte < 0)
			return (short) (unsignedByte + 256);
		return unsignedByte;
	}


	/**
	 * Simulates making of an unsigned short in "C"
	 * We add 65536 because 16th signed bit changes value by 2^16
	 *
	 * @param signedShort		signed "Java" short
	 * @return unsignedShort	simulated unsigned short represented by an int
	 **/
	public static int makeUnsignedShort(short signedShort)
	{
		int unsignedShort = signedShort;
		if (unsignedShort < 0)
			return (unsignedShort + 65536);
		return unsignedShort;
	}


	/**
	 * Making a little endian short from a big endian short
	 * Here the lower 8 bits in a short are swapped with higher 8 bits
	 *
	 * @param oneShort	big endian short which will be made into little endian
	 * @return oneShort	now, little endian short
	 **/
	public static short swapByteOrder(short oneShort)
	{
		oneShort = (short) (((oneShort & 0x00ff) << ONE_BYTE) |
		        ((oneShort & 0xff00) >>> ONE_BYTE));

		return oneShort;
	}


	/**
	 * Making a little endian char from a big endian char
	 * Here the lower 8 bits in a char are swapped with higher 8 bits
	 *
	 * @param oneChar	big endian char which will be made into little endian
	 * @return oneChar	now, little endian char
	 **/
	public static char swapByteOrder(char oneChar)
	{
		oneChar = (char) (((oneChar & 0x00ff) << ONE_BYTE) |
		        ((oneChar & 0xff00) >>> ONE_BYTE));

		return oneChar;
	}


	/**
	 * Making a little endian int from a big endian int
	 *
	 * @param i		big endian int which will be made into little endian
	 * @return i	now, little endian int
	 **/
	public static int swapByteOrder(int i)
	{
		i = (((i & 0x000000ff) << THREE_BYTES) |
		        ((i & 0x0000ff00) << ONE_BYTE) |
		        ((i & 0x00ff0000) >>> ONE_BYTE) |
		        ((i & 0xff000000) >>> THREE_BYTES));

		return i;
	}


	/**
	 * Making an int from four bytes that are contained in a byte array
	 *
	 * @param byteArr	byte array that contains 4 bytes
	 * @return int		int that was made from the parameter
	 **/
	public static int makeIntFromFourBytes(byte[] byteArr)
	{
		return (((byteArr[0] & 0xff) << THREE_BYTES) |
		        ((byteArr[1] & 0xff) << TWO_BYTES) |
		        ((byteArr[2] & 0xff) << ONE_BYTE) |
		        (byteArr[3] & 0xff));
	}


	/**
	 * Making a char from two bytes that are contained in a byte array
	 *
	 * @param byteArr	byte array that contains 2 bytes
	 * @return char		char that was made from the parameter
	 **/
	public static char makeCharFromTwoBytes(byte[] byteArr)
	{
		return (char) ((byteArr[0] << ONE_BYTE) |
		        (byteArr[1] & 0xff));
	}


	/**
	 * Making a short from two bytes that are contained in a byte array
	 *
	 * @param byteArr	byte array that contains 2 bytes
	 * @return short	short that was made from the parameter
	 **/
	public static short makeShortFromTwoBytes(byte[] byteArr)
	{
		return (short) ((byteArr[0] << ONE_BYTE) |
		        (byteArr[1] & 0xff));
	}


	/**
	 * Change header and write it to sound file or stream
	 *
	 * @param soundOutStream           Sound file data stream to which we write
	 * @param soundFileHeader       Sound file header which we modify and write
	 * @param fileSize              Sound file size in bytes
	 */
	public static void writeHeaderToSoundFile(DataOutputStream soundOutStream, int[] soundFileHeader, int fileSize)
	        throws IOException
	{
		soundFileHeader[14] = swapByteOrder(fileSize);
		soundFileHeader[1] = swapByteOrder(fileSize + HEADER_LENGTH);

		for (int ctr = 0; ctr < soundFileHeader.length; ctr++)
		{
			// writeInt is used to write actual "int" value -- not byte
			soundOutStream.writeInt(soundFileHeader[ctr]);
		}
	}

	/**
	 * Reads data from the sound file into a byte array and returns file size
	 *
	 * @param soundInStream           Sound file data stream from which we read
	 * @return soundByteData         Sound data stored in bytes
	 */
	public static byte[] readSoundData(InputStream soundInStream)
	        throws IOException
	{
		byte readBytesFromWave[] = new byte[10240];
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

		while (true)
		{
			int length = soundInStream.read(readBytesFromWave);
			if (length == -1)
				break;
			byteStream.write(readBytesFromWave, 0, length);
		}

		return byteStream.toByteArray();
	}

	/**
	 * Writes data from the sound byte array into a sound stream
	 *
	 * @param soundOutStream           Sound file data stream to which we write
	 * @param soundByteData         Sound data stored in bytes
	 */
	public static void writeSoundData(DataOutputStream soundOutStream, byte[] soundByteData)
	        throws IOException
	{
		soundOutStream.write(soundByteData);
	}

	/**
	 * Reads in an int. Doesn't swap byte order.
	 *
	 * @param soundInStream           Sound file data stream from which we read
	 * @return int                    int read from sound file
	 */
	public static int readInt(DataInputStream soundInStream)
	        throws IOException
	{
		return soundInStream.readInt();
	}

	/**
	 * Reads in a char. Swaps byte order.
	 *
	 * @param soundInStream           Sound file data stream from which we read
	 * @return char                    int read from sound file
	 */
	public static char readChar(DataInputStream soundInStream)
	        throws IOException
	{
		return swapByteOrder(soundInStream.readChar());
	}

	/**
	 * Converts in stream to buffered data sound stream
	 *
	 * @return soundInStream     sound in stream that is created
	 * @param in                in stream to become soundInStream
	 */
	public static DataInputStream initInStream(InputStream in)
	{
		return (new DataInputStream(new BufferedInputStream(in)));
	}

	/**
	 * Converts out streams to buffered data sound stream
	 *
	 * @return soundOutStream    sound out sream that is created
	 * @param out               out stream to become soundOutStream
	 */
	public static DataOutputStream initOutStream(OutputStream out)
	{
		return (new DataOutputStream(new BufferedOutputStream(out)));
	}

	/**
	 * Closes in and out sound streams
	 *
	 * @param soundInStream     sound in stream that is closed
	 * @param soundOutStream    sound out sream that is closed
	 **/
	public static void closeInAndOutStreams(DataInputStream soundInStream, DataOutputStream soundOutStream)
	        throws IOException
	{
		soundInStream.close();
		soundOutStream.close();
	}

	/**
	 * makes sound file size even
	 *
	 * @param soundFileSize     future file size
	 * @return soundFileSize    changed files size now it is even
	 */
	public static int makeFileSizeEven(int soundFileSize)
	{
		soundFileSize &= 0xfffffffe;
		return soundFileSize;
	}

	/**
	 * converts a byte array into short array
	 *
	 * @param wavData       byte data to be converted to shorts
	 * @return outBuffer    array of shorts made from byte data
	 */
	public static short[] convertByteArrayToShortArray(byte[] wavData)
	{
		int soundFileSize = wavData.length;
		//each short has 2 bytes
		short[] outBuffer = new short[soundFileSize / 2];
		byte twoTempBytes[] = new byte[2];
		int index = 0;

		for (int byteCtr = 0; byteCtr < soundFileSize; byteCtr++)
		{
			// taking care of the case when wavData is an odd number (odd number of samples provided)
			if (byteCtr + 2 > soundFileSize)
				break;
			twoTempBytes[0] = wavData[byteCtr];
			twoTempBytes[1] = wavData[++byteCtr];
			outBuffer[index++] = swapByteOrder(makeShortFromTwoBytes(twoTempBytes));
		}

		return outBuffer;
	}

	/**
	 * converts a short array into byte array
	 *
	 * @param outBuffer       short data to be converted to bytes
	 * @return outBytes        array of bytes made from array of shorts
	 */
	public static byte[] convertShortArrayToByteArray(short[] outBuffer)
	{
		// here put them back into messed up endian and make them bytes
		byte[] outBytes = new byte[outBuffer.length * 2];

		for (int i = 0; i < outBuffer.length; i++)
		{
			outBuffer[i] = swapByteOrder(outBuffer[i]);
			outBytes[i * 2] = (byte) (outBuffer[i] & 0xff00);
			outBytes[i * 2 + 1] = (byte) (outBuffer[i] & 0x00ff);
		}

		return outBytes;
	}

	/**
	 * We normalize the sound file.  We only do it if we have too (ie original rms is aboce 2200)
	 *
	 * @param waveByteData wav data of the sound file
	 * @return normalized wavData or the opriginal wavData
	 */
	public static byte[] normalize(byte[] waveByteData)
	{
		short[] samples = ConverterUtils.convertByteArrayToShortArray(waveByteData);
		byte[] normalizedWaveByteData = new byte[waveByteData.length];
		double currentRMS = ConverterUtils.calculateRMS(samples);

		if (currentRMS > NEEDED_RMS)
		{
			//-- calculate the rms factor needed to normalize
			double factor = (NEEDED_RMS / currentRMS);
			ConverterUtils.normalize(samples, factor);
			normalizedWaveByteData = ConverterUtils.convertShortArrayToByteArray(samples);
		}

		return normalizedWaveByteData;
	}

	/**
	 * RMS = squareRoot of averaged sum of samples squared
	 *
	 * @param samples
	 * @return rms value
	 */
	public static double calculateRMS(short[] samples)
	{
		double numOfSamples = samples.length;
		double sumOfSquares = 0;

		//-- get sum of squares
		for (int i = 0; i < numOfSamples; i++)
			sumOfSquares += Math.pow(samples[i], 2.0);
		//-- average the sum of squares
		double sumOfSquaresOverNumberOfSamples = sumOfSquares / numOfSamples;
		//-- take a square root of sum of the squares
		double wavRMS = Math.pow(sumOfSquaresOverNumberOfSamples, (1.0 / 2.0));
		return wavRMS;
	}

	/**
	 * normalize the sound file by a certain rms factor
	 *
	 * @param samples
	 * @param factor
	 */
	public static void normalize(short[] samples, double factor)
	{
		for (int i = 0; i < samples.length; i++)
			samples[i] = (short) Math.floor((double) samples[i] * factor);
	}


	/*
	// DEBUG print
	public static void s(String say)
	{
	    System.out.println(say);
	}
	*/


/* EXPLANATION OF WAVE HEADER IS BELOW  --- DO NOT REMOVE THIS */
	/*********************************************************************
	 * Array#	Byte Sz	Description
	 * 1	4	RIFF
	 * 2	4	length_of_data  (actual length of file in bytes - 8)
	 * 3	4	WAVE
	 * 4	4	fmt
	 * 5	4	length_of_format_chunk [18] -- const value
	 * 6..1	2	format_tag [1] --> waveFormat 1=PCM
	 * 6..2	2	number_of_channles [1] --> mono
	 * 7	4	playback_frequency [8000](must be 8000)
	 * 8	4	avg_bytes_per_sec [16000] (must be 16000)
	 * 9..1	2	block_allign [2] (must be 2)
	 * 9..2	2	some number -- better be 0 [x]
	 * 10-dmy	x	many bytes of junk (must be even, rounded up)
	 * 11	4	fact (must be 'fact)
	 * 12	4	size_of_fact (x)
	 * 13-dmy	x	many bytes of fact - number of samples
	 * 14	4	data (must be 'data')
	 * 15	4	size_of_real_data (length of actual (RAW) data in bytes)
	 *
	 *
	 * protected int waveHeader[] = {
	 * 	"RIFF",				0x52494646
	 * 	futureFileLngth+52, 0xffffffff
	 * 	"WAVE",				0x57415645
	 * 	"fmt",				0x666d7420
	 * 	18,				    0x12000000
	 * 	1,1,				0x01000100
	 * 	8000,				0x401f0000
	 * 	16000,				0x803e0000
	 * 	2,				    0x02001000
	 * 	0,				    0x00006661
	 * 	"fact",				0x63740600
	 * 	0,			    	0x0000ffff
	 * 	0,		    		0xffffffff
	 * 	"data",				0x64617461
	 * 	futureFileLngth     0xffffffff
	 * }
	 *
	 *
	 *  protected int waveMulawHeader[] = {
	 * 	"RIFF",				0x52494646
	 * 	futureFileLngth+52, 0xffffffff
	 * 	"WAVE",				0x57415645
	 * 	"fmt",				0x666d7420
	 * 	18,				    0x12000000
	 * 	7,1,				0x07000100 <----- 7 not 1
	 * 	8000,				0x401f0000
	 * 	8000,				0x401f0000 <----- 8000 not 16000
	 * 	2,			    	0x01000800 <----- 1 not 2, due to above
	 * 	0,			    	0x00006661
	 * 	"fact",				0x63740600 <----- in au there was only 4 not 6
	 * 	0,				    0x0000ffff
	 * 	0,	    			0xffffffff
	 * 	"data",				0x64617461
	 * 	futureFileLngth     0xffffffff
	 * }
	 *
	 * //futureFileLngth = vox_file_size * 4;
	 * //refer to this website for more info
	 * //http://www.delphizine.com/features/1999/11/di199911re_f/di199911re_f.asp
	 ************************************************************/


}
