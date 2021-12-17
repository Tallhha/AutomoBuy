package com.smdproj.automobuy;

import android.provider.BaseColumns;

public class MyUsers {
    public static String DB_NAME="myUsers.db";
    public static int DB_VERSION=1;

    public static class User implements BaseColumns{
        public static String TABLENAME="users";
        public static String _EMAIL="name";
        public static String _PASSWORD ="password";
        public static String _IS_MGR = "is_mgr";
        public static String _IS_DLR = "is_dlr";
    }




}
