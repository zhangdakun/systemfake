package cn.eben.system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.app.StatusBarManager;
import android.content.Context;
import android.os.Build;
import android.os.SystemProperties;
import android.provider.Settings;

public class System {
	Context context;

	  private static Map<String, String> modelMap = new HashMap();

	  static
	  {
	        modelMap.put("T1", "T1");
	        modelMap.put("T2", "T2");
	        modelMap.put("T3", "T3");
	        modelMap.put("T4", "T4");
	        modelMap.put("T5", "T5");
	        modelMap.put("T6", "T6");
	        modelMap.put("EBEN T6", "T6");
	        modelMap.put("T8E", "T7");
	        modelMap.put("T8E_TD", "T7");
	        modelMap.put("T8E-TD", "T7");
	        modelMap.put("T8", "T8");
	        modelMap.put("T9B", "T8");
	        modelMap.put("A3+", "T8");
	        modelMap.put("T8F+", "T8");
	        modelMap.put("T8E_S", "T8");
	        modelMap.put("T8E-S", "T8");
	        modelMap.put("M5A", "T8");
	        modelMap.put("M5B", "T8");
	        modelMap.put("B8B", "T8");
	  }

	  public static String getAndroidId(Context context)
	  {
	    String androidId = Settings.System.getString(
	      context.getContentResolver(), 
	      "android_id");
	    return androidId;
	  }

	  private static String getDeviceModel(String model)
	  {
	    if (modelMap.containsKey(model))
	      return ((String)modelMap.get(model));

	    return null;
	  }

	  public static String getSn(Context context) {
	    String product = Build.PRODUCT;

	    String deviceModel = getDeviceModel(product);
	    String serial = null;
	    if (deviceModel != null)
	      if ((deviceModel.equals("T1")) || (deviceModel.equals("T2")) || 
	        (deviceModel.equals("T3")) || (deviceModel.equals("T4")))
	        serial = getCPUSerial();
	      else if ((deviceModel.equals("T5")) || (deviceModel.equals("T6"))) {
	        serial = SystemProperties.get("ro.product.serialno", 
	          null);
	      }
	      else if (deviceModel.equals("T7")) {
	        serial = SystemProperties.get("gsm.scril.sn", null);
	      }
	      else if (product.startsWith("T8")) {
	          serial = SystemProperties.get("gsm.scril.sn", null);
	      }
	        else {
	          serial = getAndroidId(context);
	        }


	    if ((serial == null) || (serial.length() == 0) || ("0000".equals(serial)))
	      serial = getAndroidId(context);

//	    if ((serial == null) || (serial.length() == 0) || ("0000".equals(serial)))
//	      throw new RuntimeException("Can't find serial of the device");

	    return serial;
	  }

	  public static String getSn() {
	    String product = Build.PRODUCT;

	    String deviceModel = getDeviceModel(product);
	    String serial = null;
	    if (deviceModel != null) {
	      if ((deviceModel.equals("T1")) || (deviceModel.equals("T2")) || 
	        (deviceModel.equals("T3")) || (deviceModel.equals("T4")))
	        serial = getCPUSerial();
	      else if ((deviceModel.equals("T5")) || (deviceModel.equals("T6"))) {
	        serial = SystemProperties.get("ro.product.serialno", 
	          null);
	      }
	      else if (deviceModel.equals("T7"))
	        serial = SystemProperties.get("gsm.scril.sn", null);
	      else
	        serial = getCPUSerial();

	    } 
	    else if (product.startsWith("T8")) {
	        serial = SystemProperties.get("gsm.scril.sn", null);
	    }
	     

//	    if ((serial == null) || (serial.length() == 0) || (serial.startsWith("00000000")))
//	      throw new RuntimeException("Can't find serial of the device");

	    return serial;
	  }

	  private static String getCPUSerial() {
//	    String line = "";
//	    BufferedReader bufferedReader = null;
//	    try {
//	      bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"));
//
//	      if (line.startsWith("Serial")) {
//	        String str1;
//	        String[] a = line.split("[\\s:]+");
//	        return a[(a.length - 1)];
//	      }
//	    }
//	    catch (IOException x) {
//	    }
//	    finally {
//	      if (bufferedReader != null)
//	        try {
//	          bufferedReader.close();
//	        } catch (IOException x) {
//	          throw new RuntimeException(x);
//	        }
//	    }
//	    if (bufferedReader != null)
//	      try {
//	        bufferedReader.close();
//	      } catch (IOException x) {
//	        throw new RuntimeException(x);
//	      }


	    return null;
	  }


	public static  void disappearStatusBar(Context context) {
//		String product = Build.PRODUCT;
//		String deviceModel = getDeviceModel(product);
//		if (deviceModel.equals("T7")) 
		{
			StatusBarManager sbm = (StatusBarManager) context
					.getSystemService("statusbar");

			sbm.collapsePanels();
		}
	}

}
