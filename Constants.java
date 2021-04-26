package com.a.testdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

/***
 * Created by comp1 on  06-04-2017.
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class Constants {

     private static ProgressDialog pDialog;
     public static String vendor_type_flag="0";
     public static File vendor_sign,customer_sign;
     public  static String reason,is_accept,cancellation_reason,customer_expected_price,vendors_offer;
     public static String checked_flag="0";
     public static final String User_name="user_name",CONTACT_US="contact_us",PRIVACY_POLICY="privacy_policy",ABOUT_US_URL="about_us",IMAGE_EXT="jpg",VENDOR_ID="vendor_id",PROFILE_ID="u_id",USER_NAME="user_name",NAME="f_name",PHONE_NUMBER="phone_no",EMAIL_ID="email_id",ALT_NUMBER="alt_number",ADDRESS="address",SHOP_NAME="shop_name",GST_NO="gst_number",DOB="dob",SHOP_ADDRESS="shop_adress",CITY="city",PINCODE="pincode",BALANCE="balance",UIID_NO="UIID_no",PROFILE_IMG="profile_img",IS_ACTIVE="is_active", ADHAR_CARD="adhar_card",PAN_CARD="pan_card",PASSPORT_IMG="passport_img",GCM_ID = "gcm_id",OTP="otp",YOUR_REFERRAL_CODE="your_referral_code";
     public static String TODAYS_ORDERS_COUNT="todays_order_count";
    public static String UPCOMING_ORDERS_COUNT="upcoming_order_count";
    public static String DELIVERED_ORDERS_COUNT="delivered_order_count";
    public static String CANCELLED_ORDERS_COUNT="cancelled_order_count";
    public static String LEAD_COUNT="lead_count";
    public static String ORDER_STATUS="0";
    public static final String ADDR_FLAT_NO="flat_no";
    public static final String ADDR_AREA="area";
    public static final String ADDR_CITY="city";
    public static final String ADDR_LANDMARK="landmark";
    public static final String ADDR_LPOSTAL_CODE="postal_code";
    public static final String DELIVERY_TIME="delivery_time";
    public static final String DELIVERY_BOY_INSTRUCTION="delivery_boy_instruction";
    public static String MY_WALLET_BALANCE="";
    public static String I_AM_FROM_NOTIFICATION="iam_from_notification";

    public  static String switch_on="2",front_camera="2",back_camera="2",chargingport="2",battery="2",wifi="2",volumn="2";
    public  static String device_display="2",speaker="2";
    public  static String phone_body="2";
    public   static String under_warranty="2";
    public  static String touch_screen="2";
    public  static String spot_on_screen="2";
    public  static String lines_on_screen="2";
    public  static String original_charger="2";
    public  static String original_earphone="2";
    public  static String box_with_same_IMEI="2";
    public  static String bill_with_same_IMEI="2";
    public  static String fingure_touch="2";
    public  static String power_button="2";
    public  static String sensor="2";
    public  static String silent_button="2";
    public  static String audio_jack = "2";


    // public static ArrayList<HomeHolder>list_home=new ArrayList<>();

    @SuppressLint("SimpleDateFormat")
    public static final SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
   /* public static ArrayList<TodaysOrderData> list_todays_orders = new ArrayList<>();
    public static ArrayList<TodaysOrderData> list_upcoming_orders = new ArrayList<>();
    public static ArrayList<TodaysOrderData> list_delivered_orders = new ArrayList<>();
    public static ArrayList<ProductDetailData> list_product_details = new ArrayList<>();
    public static ArrayList<OrderDetailData> list_order_details = new ArrayList<>();
    public static ArrayList<CustomerRatingData> list_CustomerRating = new ArrayList<>();
    public static ArrayList<AdminData> list_admin_rating = new ArrayList<>();
    public static ArrayList<WalletData> list_wallet_details = new ArrayList<>();
    public static ArrayList<NotificationData> list_notifications = new ArrayList<>();  */
    /*isNetworkAvailable function*/
    public static boolean isNetworkAvailable(Context p_context) {
        ConnectivityManager cm = (ConnectivityManager) p_context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public  static int calculateNoOfColumns(Context context, float columnWidthDp) { // For example columnWidthdp=180
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
        return noOfColumns;
    }
    /*Dialog show function*/
    public static void show_progress_dialog(Context p_context ) {
        try {
            /* check dialog is null or not */
            if (pDialog == null) {
                pDialog = new ProgressDialog(p_context);
                pDialog.setMessage("Loading...");
                pDialog.setCanceledOnTouchOutside(false);
                pDialog.setCancelable(false);

            }

            /* check dialog is null or not and showing */
            if (!((Activity) p_context).isFinishing()) {
                if (!pDialog.isShowing() && null != pDialog) {
                    pDialog.show();
                }
            }
        } catch (Exception e) {
             /*if exception is occur then set dialog to null  */
            if (null != pDialog) {
                pDialog = null;
            }
            e.printStackTrace();
        }
    }
    /*Dialog show function*/
    public static void show_progress_dialog_percentage(Context p_context, String per) {
        try {
            /* check dialog is null or not */
            if (pDialog == null) {
                pDialog = new ProgressDialog(p_context);
                pDialog.setMessage(per+"");
                pDialog.setCanceledOnTouchOutside(false);
                pDialog.setCancelable(false);


            }

            /* check dialog is null or not and showing */
            if (!((Activity) p_context).isFinishing()) {
                if (!pDialog.isShowing() && null != pDialog) {
                    pDialog.show();
                }
            }
        } catch (Exception e) {
             /*if exception is occur then set dialog to null  */
            if (null != pDialog) {
                pDialog = null;
            }
            e.printStackTrace();
        }
    }
    /*Dialog dismiss function*/
    public static void dismiss_progress_dialog() {
        try {
            try {
                 /* check dialog is null or not and showing then dismiss dialog and set to null */
                if (null != pDialog && pDialog.isShowing()) {
                    pDialog.dismiss();
                    pDialog = null;
                }
            } catch (Exception e) {
                pDialog = null;
                e.printStackTrace();
            }
        } catch (Exception e) {
            /*if exception is occur then set dialog to null  */
            e.printStackTrace();
        }
    }

    public static void clear_notifications() {
  /*      if (com.farmandfablescutomer.GCMIntentService.notificationManager != null) {
            com.farmandfablescutomer.GCMIntentService.notificationManager.cancelAll();
        }*/
    }


    /** check device type */
    public static boolean isTabletDevice(Context activityContext)
    {
        boolean device_large = ((activityContext.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_LARGE  || (activityContext.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) ==
                Configuration.SCREENLAYOUT_SIZE_XLARGE);

        if (device_large) {
            DisplayMetrics metrics = new DisplayMetrics();
            Activity activity = (Activity) activityContext;
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

            if (metrics.densityDpi == DisplayMetrics.DENSITY_DEFAULT || metrics.densityDpi == DisplayMetrics.DENSITY_HIGH || metrics.densityDpi == DisplayMetrics.DENSITY_TV || metrics.densityDpi == DisplayMetrics.DENSITY_XHIGH) {
                return true;
            }
        }
        return false;
    }

    public static Bitmap decodeFile(File f) {
        try {
            int REQUIRED_SIZE = 500;
            try {
                //decode image size
                BitmapFactory.Options o = new BitmapFactory.Options();
                o.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(new FileInputStream(f), null, o);

                //Find the correct scale value. It should be the power of 2.
                // final int REQUIRED_SIZE=200;
                int width_tmp = o.outWidth, height_tmp = o.outHeight;
                int scale = 1;
                while (true) {
                    if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
                        break;
                    width_tmp /= 2;
                    height_tmp /= 2;
                    scale *= 2;
                }

                //decode with inSampleSize
                BitmapFactory.Options o2 = new BitmapFactory.Options();
                o2.inSampleSize = scale;
                return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
            } catch (FileNotFoundException e) {
                Log.d("TAG", "" + e.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getRealPathFromURI(Activity activity, Uri contentUri) {
        String str = "";
        try {
            String[] project = {MediaStore.Images.Media.DATA};
            Cursor cursor = activity.getContentResolver().query(contentUri, project, null, null, null);

            //Cursor cursor = managedQuery(contentUri, proj, null, null, null);
            assert cursor != null;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            str = cursor.getString(column_index);

            Log.d("TAG", "getRealPathFromURI path:" + str);
            if (str == null) {
                try {
                    str = getPath(activity, contentUri);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            cursor.close();
            return str;
        } catch (Exception e) {

            try {
                str = getPath(activity, contentUri);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return str;
    }

    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    if ("primary".equalsIgnoreCase(type)) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }

                    // TODO handle non-primary volumes
                }
                // DownloadsProvider
                else if (isDownloadsDocument(uri)) {

                    final String id = DocumentsContract.getDocumentId(uri);
                    final Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                    return getDataColumn(context, contentUri, null, null);
                }
                // MediaProvider
                else if (isMediaDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }

                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[]{
                            split[1]
                    };

                    return getDataColumn(context, contentUri, selection, selectionArgs);
                }
            }
            // MediaStore (and general)
            else if ("content".equalsIgnoreCase(uri.getScheme())) {
                return getDataColumn(context, uri, null, null);
            }
            // File
            else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static File create_file() {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/mars_vendor");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);

        String fName = "Image_" + n + ".jpg";
        File file = new File(myDir, fName);
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }


    public static void setSharePreferenceEmpty(Context mContext) {
        SFStore sfStore = new SFStore(mContext);

        sfStore.setString(Constants.VENDOR_ID, "");
        sfStore.setString(Constants.NAME, "");
        sfStore.setString(Constants.EMAIL_ID, "");
    }
























}
