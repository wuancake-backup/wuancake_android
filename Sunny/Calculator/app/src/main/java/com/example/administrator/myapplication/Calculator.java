package com.example.administrator.myapplication;

class Calculator {

    private StringBuffer viewText;

    Calculator() {
        viewText = new StringBuffer();
    }

    Calculator(String text) {
        viewText = new StringBuffer(text);
    }

    void appendChar(char c) {
        viewText.append(c);
    }

    void delTailChar() {
        viewText.deleteCharAt(viewText.length() - 1);
    }

    private String calText(String s) {

        double val = 0.0;
        int offset = 0;
        String[] tmp = s.split("[-/+\\*]");
        char[] operator = new char[tmp.length - 1];

        offset += tmp[0].length();
        operator[0] = s.charAt(offset);

        for (int i = 1; i < (tmp.length - 1); i++) {
            offset += tmp[i].length() + 1;
            operator[i] = s.charAt(offset);
        }

        for (int i = 0; i < operator.length; i++) {
            if (operator[i] == '/') {
                tmp[i + 1] = Double
                        .valueOf(Double.valueOf(tmp[i]) / Double.valueOf(tmp[i + 1]))
                        .toString();
                if (i > 0)
                    operator[i] = operator[i - 1];
                else
                    operator[i] = '+';
                tmp[i] = "0";
            } else if (operator[i] == '*') {
                tmp[i + 1] = Double
                        .valueOf(Double.valueOf(tmp[i]) * Double.valueOf(tmp[i + 1]))
                        .toString();
                if (i > 0)
                    operator[i] = operator[i - 1];
                else
                    operator[i] = '+';
                tmp[i] = "0";
            }
        }

        val += Double.valueOf(tmp[0]);
        for (int i = 1; i < tmp.length; i++) {

            switch (operator[i - 1]) {
                case '+':
                    val += Double.valueOf(tmp[i]);
                    break;
                case '-':
                    val -= Double.valueOf(tmp[i]);
                    break;
            }
        }
        return String.valueOf(val);
    }

    private boolean checkText() {
        int index = 0;
        StringBuffer sb = viewText;
        String s1 = "-/+*)";
        String s2 = "-/+*(";

        do {
            index = sb.indexOf(")", index + 1);
            if ((index > 0) && ((index + 1) != sb.length())) {
                if (s1.indexOf(sb.charAt(index + 1)) == -1) {
                    return false;
                }
            }
        } while (index != -1);

        index = 0;

        do {
            index = sb.indexOf("(", index + 1);
            if (index > 0) {
                if (s2.indexOf(sb.charAt(index - 1)) == -1) {
                    return false;
                }
            }
        } while (index != -1);

        return true;
    }

    boolean parseText() {
        StringBuilder s = new StringBuilder(viewText);
        int index1, index2;

        if (!checkText()) {
            return false;
        }

        if (s.indexOf("(") > 0) {
            do {
                index2 = s.indexOf(")");
                index1 = s.lastIndexOf("(", index2);
                if ((index1 != -1) && (index2 != -1)) {
                    String tmp = s.substring(index1 + 1, index2);
                    tmp = this.calText(tmp);
                    s.replace(index1, index2 + 1, tmp);
                } else {
                    break;
                }
            } while (true);
        }

        if ((s.indexOf("(") >= 0) || (s.indexOf(")") >= 0)) {
            return false;
        }

        viewText.append("=").append(this.calText(s.toString()));
        if (viewText.toString().matches(".*\\.0")) {
            viewText.setLength(viewText.length() - 2);
        }

//		System.out.println(viewText);
        return true;
    }

    String getText() {
        return viewText.toString();
    }

    void clearText() {
        viewText.delete(0, viewText.length());
    }

    public void setText(String s) {
        viewText.replace(0, viewText.length(), s);
    }
}
