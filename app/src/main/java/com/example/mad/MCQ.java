package com.example.mad;

public class MCQ {
    private String Question;
    private String Ans1;
    private String Ans2;
    private String Ans3;
    private String Ans4;
    private int CorrectAns;

    public MCQ() {
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAns1() {
        return Ans1;
    }

    public void setAns1(String ans1) {
        Ans1 = ans1;
    }

    public String getAns2() {
        return Ans2;
    }

    public void setAns2(String ans2) {
        Ans2 = ans2;
    }

    public String getAns3() {
        return Ans3;
    }

    public void setAns3(String ans3) {
        Ans3 = ans3;
    }

    public String getAns4() {
        return Ans4;
    }

    public void setAns4(String ans4) {
        Ans4 = ans4;
    }

    public int getCorrectAns() {
        return CorrectAns;
    }

    public void setCorrectAns(int correctAns) {
        CorrectAns = correctAns;
    }
}
