package nguyenhoanganhkhoa.com.thirdlink;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Pattern;

public class AppUtil {
    public static String eMessage = "";
    public static String eMessageForSignUp = "";
    public static int mSelectedIndex = 0;
    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +
                    "(?=.*[a-z])" +
                    "(?=.*[A-Z])" +
                    "(?=.*[@#$%^&+=])" +
                    "(?=\\S+$)" +
                    ".{8,15}" +
                    "$");
    public static String LOCK_CONDITION_SIGNUP = "LOCK";
    public static String LOCK_CONDITION_FORGOTPASS = "KEY";

    // Thông tin dữ liệu để validate APP.
    public static String VERIFICATION_CODE_APP = "1234";
    public static String PHONE_APP = "0908315280";


    public static SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
    public static SimpleDateFormat dateFormat2 = new SimpleDateFormat("MMM dd, yyyy", Locale.US);

    //Khai báo database
    public static DatabaseReference databaseReference =  FirebaseDatabase.getInstance().getReference("account");

    public static final String DATA_OBJECT = "Student";

    public static String USERNAME_S = "";
    public static String PASSWORD_S = "";
    public static String PHONE_S = "";
    public static String FULLNAME_S = "";
    public static String EMAIL_S = "";


    public final static String top_up = "Top Up";
    public final static String qr_code = "QR Code";
    public final static String news = "News";
    public final static String about_us = "About Us";
    public final static String history = "History";
    public final static String help_center = "Help Center";
    public final static String security_center = "Security Center";

    public static final String SELECTED_ITEM = "my_clicked_item";
    public static final String SELECTED_ITEM_TRANS = "my_clicked_item_trans";
    public static final String MY_BUNDLE = "my_bundle";
    public static final String MY_BUNDLE_TRANS = "my_bundle_trans";

    public static String PROBLEM = "";
    public static String HELP_PROBLEM_CONTEXT = "";

    public static String USERNAME_AFTER_LOGGIN = "";

    public static String FB_DATE_OF_BIRTH = "dateOfBirthStudent";
    public static String FB_FACULTY = "facultyStudent";
    public static String FB_MAJOR = "majorStudent";
    public static String FB_PHONE = "phoneStudent";
    public static String FB_FULLNAME = "fullnameStudent";
    public static String FB_ID = "idstudent";
    public static String FB_GENDER = "genderStudent";
    public static String FB_AVATAR = "avatarStudent";
    public static String FB_EMAIL = "emailStudent";
    public static String FB_USERNAME = "usernameStudent";
    public static String FB_IMAGES_BITMAP = "uriImageStudent";
    public static String FB_BALANCE = "balanceStudent";
    public static String FB_PASSWORD = "passwordStudent";

    public static String AMOUNT_SEND = "";
    public static int SIGNAL_COMEBACK_FOR_SETTING = 0;
    public static int SIGNAL_TO_HOME = 123;












}
