package com.mn.pp.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ByteUtils {
	private static Logger logger = LoggerFactory.getLogger(ByteUtils.class.getName());

	public static float bytes2float(byte[] b) {
		int intNum = 0;
		intNum = intNum | (b[0] & 0xff) << 0;
		intNum = intNum | (b[1] & 0xff) << 8;
		intNum = intNum | (b[2] & 0xff) << 16;
		intNum = intNum | (b[3] & 0xff) << 24;
		return Float.intBitsToFloat(intNum);
	}

	public static byte[] littleLong2byte(long res) {
		byte[] targets = new byte[8];

		targets[0] = (byte) (res & 0xff);// 最低位
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
		targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
		targets[3] = (byte) ((res >> 24) & 0xff);// .
		targets[4] = (byte) ((res >> 32) & 0xff);// .
		targets[5] = (byte) ((res >> 40) & 0xff);// .
		targets[6] = (byte) ((res >> 48) & 0xff);// .
		targets[7] = (byte) (res >>> 56);// 最高位,无符号右移。
		return targets;
	}

	public static byte[] bigLong2Byte(long res) {
		byte[] targets = new byte[8];
		targets[7] = (byte) (res & 0xff);// 最低位
		targets[6] = (byte) ((res >> 8) & 0xff);// 次低位
		targets[5] = (byte) ((res >> 16) & 0xff);// 次高位
		targets[4] = (byte) ((res >> 24) & 0xff);// .
		targets[3] = (byte) ((res >> 32) & 0xff);// .
		targets[2] = (byte) ((res >> 40) & 0xff);// .
		targets[1] = (byte) ((res >> 48) & 0xff);// .
		targets[0] = (byte) (res >>> 56);// 最高位,无符号右移。
		return targets;
	}

	public static byte[] LittleInt2byte(int res) {
		byte[] targets = new byte[4];

		targets[0] = (byte) (res & 0xff);// 最低位
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
		targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
		targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
		return targets;
	}

	public static byte[] BigInt2byte(int res) {
		byte[] targets = new byte[4];

		targets[0] = (byte) (res & 0xff);// 最低位
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
		targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
		targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
		return targets;
	}

	public static byte[] LittleShort2byte(short res) {
		byte[] targets = new byte[2];

		targets[0] = (byte) (res & 0xff);// 最低位
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
		return targets;
	}

	public static byte[] BigShort2byte(short res) {
		byte[] targets = new byte[2];

		targets[1] = (byte) (res & 0xff);// 最低位
		targets[0] = (byte) ((res >> 8) & 0xff);// 次低位
		return targets;
	}

	// byte数组转成long
	public static long byte2BigLong(byte[] b) {
		long s = 0;
		long s0 = b[0] & 0xff;// 最低位
		long s1 = b[1] & 0xff;
		long s2 = b[2] & 0xff;
		long s3 = b[3] & 0xff;
		long s4 = b[4] & 0xff;// 最低位
		long s5 = b[5] & 0xff;
		long s6 = b[6] & 0xff;
		long s7 = b[7] & 0xff;

		// s0不变
		s1 <<= 8;
		s2 <<= 16;
		s3 <<= 24;
		s4 <<= 8 * 4;
		s5 <<= 8 * 5;
		s6 <<= 8 * 6;
		s7 <<= 8 * 7;
		s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
		return s;
	}

	// byte数组转成long
	public static long byte2LittleLong(byte[] b) {
		long s = 0;
		long s0 = b[7] & 0xff;// 最低位
		long s1 = b[6] & 0xff;
		long s2 = b[5] & 0xff;
		long s3 = b[4] & 0xff;
		long s4 = b[3] & 0xff;// 最低位
		long s5 = b[2] & 0xff;
		long s6 = b[1] & 0xff;
		long s7 = b[0] & 0xff;

		// s0不变
		s1 <<= 8;
		s2 <<= 16;
		s3 <<= 24;
		s4 <<= 8 * 4;
		s5 <<= 8 * 5;
		s6 <<= 8 * 6;
		s7 <<= 8 * 7;
		s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
		return s;
	}

	public static int byte2BigInt(byte[] res) {
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000
		int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // | 表示按位或
				| ((res[2] << 24) >>> 8) | (res[3] << 24);
		return targets;
	}

	public static int byte2Littleint(byte[] res) {
		int v0 = (res[0] & 0xff) << 24;// &0xff将byte值无差异转成int,避免Java自动类型提升后,会保留高位的符号位
		int v1 = (res[1] & 0xff) << 16;
		int v2 = (res[2] & 0xff) << 8;
		int v3 = (res[3] & 0xff);
		return v0 + v1 + v2 + v3;
	}

	public static short byte2Bigshort(byte[] res) {
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000

		short targets = (short) ((res[1] & 0xff) | ((res[0] << 8) & 0xff00)); // |
		// 表示按位或
		return targets;
	}

	public static short byte2LittleShort(byte[] res) {
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000

		short targets = (short) ((res[0] & 0xff) | ((res[1] << 8) & 0xff00)); // |
		// 表示按位或
		return targets;
	}

	public static byte[] len12Mac2Bytes(String len12Mac) {
		if (StringUtils.isNullorWhiteSpace(len12Mac) || len12Mac.length() != 12) {
			return null;
		}
		try {
			byte[] macBytes = new byte[6];
			int len = macBytes.length;
			for (int i = 0; i < len; i++) {
				int value = Integer.parseInt(len12Mac.substring(i * 2, i * 2 + 2), 16);
				macBytes[i] = (byte) value;
			}
			return macBytes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String bytes2Len12Mac(byte[] macBytes) {
		if (macBytes == null || macBytes.length != 6) {
			return null;
		}
		String value = "";
		for (int i = 0; i < 6; i++) {
			String sTemp = null;
			if (macBytes[i] <= 0x0f && macBytes[i] >= 0x00) {
				sTemp = "0" + Integer.toHexString(0xFF & macBytes[i]);
			} else {
				sTemp = Integer.toHexString(0xFF & macBytes[i]);
			}
			value = value + sTemp;
		}
		return value;
	}

	public static String bytes2Mac(byte[] macBytes) {
		if (macBytes == null || macBytes.length != 6) {
			logger.warn("Mac address is illegal");
			return "";
		}
		StringBuffer value = new StringBuffer(32);
		for (int i = 0; i < macBytes.length; i++) {
			String sTemp = Integer.toHexString(0xFF & macBytes[i]).toString();
			if (sTemp.length() == 1) {
				sTemp = "0" + sTemp;
			}
			value.append(sTemp);
			if (i < (macBytes.length - 1)) {
				value.append(":");
			}
		}
		return value.toString().toUpperCase();
	}

	public static byte[] mac2Bytes(String mac) {
		byte[] macBytes = new byte[6];
		try {
			String[] strArr = mac.split(":");
			int len = strArr.length;
			if (len != 6) {
				return null;
			}
			for (int i = 0; i < len; i++) {
				int value = Integer.parseInt(strArr[i], 16);
				macBytes[i] = (byte) value;
			}
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		return macBytes;
	}

	public static byte[] int2byte(int res) {
		byte[] targets = new byte[4];

		targets[0] = (byte) (res & 0xff);// 最低位
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
		targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
		targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
		return targets;
	}

	public static byte[] short2byte(short res) {
		byte[] targets = new byte[2];

		targets[0] = (byte) (res & 0xff);// 最低位
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
		return targets;
	}

	public static long byte2long(byte[] bb) {
		return ((((long) bb[0] & 0xff) << 56) | (((long) bb[1] & 0xff) << 48) | (((long) bb[2] & 0xff) << 40)
				| (((long) bb[3] & 0xff) << 32) | (((long) bb[4] & 0xff) << 24) | (((long) bb[5] & 0xff) << 16)
				| (((long) bb[6] & 0xff) << 8) | (((long) bb[7] & 0xff) << 0));
	}

	public static int byte2int(byte[] res) {
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000

		int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // | 表示按位或
				| ((res[2] << 24) >>> 8) | (res[3] << 24);
		return targets;
	}

	public static short byte2short(byte[] res) {
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000

		short targets = (short) ((res[0] & 0xff) | ((res[1] << 8) & 0xff00)); // | 表示按位或
		return targets;
	}

	/**
	 * 合并两个byte数组
	 *
	 * @param pByteA
	 * @param pByteB
	 * @return
	 */
	public static byte[] getMergeBytes(byte[] pByteA, byte[] pByteB) {
		int aCount = pByteA.length;
		int bCount = pByteB.length;
		byte[] b = new byte[aCount + bCount];
		for (int i = 0; i < aCount; i++) {
			b[i] = pByteA[i];
		}
		for (int i = 0; i < bCount; i++) {
			b[aCount + i] = pByteB[i];
		}
		return b;
	}

	/**
	 * 从一个byte[]数组中截取一部分
	 *
	 * @param src
	 * @param begin
	 * @param count
	 * @return
	 */
	public static byte[] getSubBytes(byte[] src, int begin, int count) {
		byte[] bs = new byte[count];
		for (int i = begin; i < begin + count; i++)
			bs[i - begin] = src[i];
		return bs;
	}

	/**
	 * 合并多个byte数组
	 *
	 * @param pByteA
	 * @return
	 */
	static ByteArrayOutputStream _bos = new ByteArrayOutputStream();

	public static byte[] getMergeBytes(byte[]... pByteA) {

		byte alldata[] = null;
		synchronized (_bos) {
			try {
				for (int i = 0; i < pByteA.length; i++) {
					_bos.write(pByteA[i]);
				}
				alldata = _bos.toByteArray();
			} catch (IOException e) {
				logger.error("", e);
			} finally {
				if (_bos != null)
					_bos.reset();
			}
		}
		return alldata;
	}

	public static byte[] getMergeBytes(List<byte[]> alldata) {
		byte data[] = null;
		synchronized (_bos) {
			try {
				for (int i = 0; i < alldata.size(); i++) {
					_bos.write(alldata.get(i));
				}
				data = _bos.toByteArray();
			} catch (Exception e) {
				logger.error("", e);
			} finally {
				if (_bos != null)
					_bos.reset();
			}
		}
		return data;
	}


	public static boolean isEqualContent(byte[] src, byte[] dst, int len) {
		for (int j = 0; j < len; j++) {
			if (src[j] != dst[j]) {
				return false;
			}
		}
		return true;
	}

	static ByteArrayOutputStream _gzipBaos = new ByteArrayOutputStream();

	/**
	 * 压缩
	 */
	public static byte[] gZip(byte[] data) {
		byte[] b = null;
		try {
			GZIPOutputStream gzip = new GZIPOutputStream(_gzipBaos);
			gzip.write(data);
			gzip.finish();
			gzip.close();
			b = _gzipBaos.toByteArray();
			_gzipBaos.reset();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	/**
	 * 解压GZip
	 */
	public static byte[] unGZip(byte[] data, int len) {
		byte[] back = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(data, 0, len);
			GZIPInputStream gzip = new GZIPInputStream(bis);
			byte[] unzipBuf = new byte[len];
			int num = -1;
			while ((num = gzip.read(unzipBuf)) != -1) {
				_gzipBaos.write(unzipBuf, 0, num);
			}
			back = _gzipBaos.toByteArray();
			_gzipBaos.flush();
			_gzipBaos.reset();
			gzip.close();
			bis.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return back;
	}

}
