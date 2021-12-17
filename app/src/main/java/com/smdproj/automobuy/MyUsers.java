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

    public static class Employee implements BaseColumns{
        public static String TABLENAME="emp";
        public static String _EMAIL="email";
        public static String _CNIC="cnic";
        public static String _FNAME="fname";
        public static String _LNAME="lname";
        public static String _PHONENO="phoneno";
        public static String _ADDRESS="address";
        public static String _TYPE="type";
        public static String _SALARY="salary";
        public static String _SALES="sales";
        public static String _ISMGR="ismgr";

    }






}
