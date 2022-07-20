package com.lijianhong.train.util;

/**
 * @author lijianhong Date: 2022/7/19 Time: 12:24 PM
 * @version $Id$
 */
public class Tab {


    public static String makeLine(int contentTabs) {
        int tabSize = 2;
        StringBuilder tab = new StringBuilder();
        for (int i = 0; i < tabSize; i++) {
            tab.append("-");
        }


        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < contentTabs; i++) {
            stringBuilder.append(tab);
        }

        return stringBuilder.toString();
    }
    public static String makeTab(String content, int contentTabs) {

        int tabSize = 2;
        int length = content.length();
        int tabCnt = 0;
        if (length % tabSize != 0) {
            tabCnt++;
            length = (length + tabSize - 1) / tabSize;
        } else {
            length = length / tabSize;
        }

        if (length < contentTabs) {
            tabCnt = tabCnt + (contentTabs - length);
        }

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tabCnt; i++) {

            s.append("\t");
        }
        return s.toString();
    }

}
