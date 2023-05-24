package it.federico.rota.cordova;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;

import android.os.Build;

public class AppVersion extends CordovaPlugin {
  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

    try {
      ApplicationInfo appInfo;
      PackageInfo packageInfo;
      PackageManager packageManager = this.cordova.getActivity().getPackageManager();
      String packageName = this.cordova.getActivity().getPackageName();
      
      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
        appInfo = packageManager.getApplicationInfo(packageName, PackageManager.ApplicationInfoFlags.of(0));
        packageInfo = packageManager.getPackageInfo(packageName, PackageManager.ApplicationInfoFlags.of(0));
      } else {
        appInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA);
        // appInfo = packageManager.getApplicationInfo(packageName, 0);
        packageInfo = packageManager.getPackageInfo(packageName, 0);
      }
      
      if (action.equals("getAppName")) {
        // PackageManager packageManager = this.cordova.getActivity().getPackageManager();
        // ApplicationInfo app = packageManager.getApplicationInfo(this.cordova.getActivity().getPackageName(), 0);
        callbackContext.success((String)packageManager.getApplicationLabel(appInfo));
        return true;
      }
      if (action.equals("getPackageName")) {
        // callbackContext.success(this.cordova.getActivity().getPackageName());
        callbackContext.success(packageName);
        return true;
      }
      if (action.equals("getVersionNumber")) {
        // PackageManager packageManager = this.cordova.getActivity().getPackageManager();
        // callbackContext.success(packageManager.getPackageInfo(this.cordova.getActivity().getPackageName(), 0).versionName);
        callbackContext.success(packageInfo.versionName);
      return true;
      }
      if (action.equals("getVersionCode")) {
        // PackageManager packageManager = this.cordova.getActivity().getPackageManager();
        if(android.os.Build.VERSION.SDK_INT>=28) {
          // long longVersionCode = packageManager.getPackageInfo(this.cordova.getActivity().getPackageName(), 0).getLongVersionCode();
          long longVersionCode = packageInfo.getLongVersionCode();
          callbackContext.success((int)longVersionCode);
        } else {
          // callbackContext.success(packageManager.getPackageInfo(this.cordova.getActivity().getPackageName(), 0).versionCode);
          callbackContext.success(packageInfo.versionCode);
        }
      return true;
      }
      return false;
    } catch (NameNotFoundException e) {
      callbackContext.success("N/A");
      return true;
    }
  }

}
