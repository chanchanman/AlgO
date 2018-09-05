package com.happyballoons.saket.algon.activity;

/**
 * Created by Saket on 16-Apr-17.
 */

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    // declare list of Question objects
    List <Question> list = new ArrayList<>();
    MyDataBaseHelper myDataBaseHelper;

    // method returns number of questions in list
    public int getLength(){
        return list.size();
    }

    // method returns question from list based on list index
    public String getQuestion(int a) {
        return list.get(a).getQuestion();
    }

    // method return a single multiple choice item for question based on list index,
    // based on number of multiple choice item in the list - 1, 2, 3 or 4
    // as an argument
    public String getChoice(int index, int num) {
        return list.get(index).getChoice(num-1);
    }

    //  method returns correct answer for the question based on list index
    public String getCorrectAnswer(int a) {
        return list.get(a).getAnswer();
    }


    //Initialize Database in case its empty at very beginning
    public void initQuestions(Context context) {
        myDataBaseHelper = new MyDataBaseHelper(context);
        list = myDataBaseHelper.getAllQuestionsList();//get questions/choices/answers from database

        if (list.isEmpty()) {//if list is empty, populate database with default questions/choices/answers
            myDataBaseHelper.addInitialQuestion(new Question("What is the average cost of a Linked List access?",
                    new String[]{"O(n)", "O(n.k)", "O(n.log(n))", "O(n+k)"}, "O(n)"));
            myDataBaseHelper.addInitialQuestion(new Question("What is the worst cost of a Red-Black Tree deletion?",
                    new String[]{"O(n.k)", "O(1)", "O(n)", "O(log(n))"}, "O(log(n))"));
            myDataBaseHelper.addInitialQuestion(new Question("What is the best cost of Radix Sort Algorithm?",
                    new String[]{"O(n.k)", "O(n)", "O(1)", "O(n.2)"}, "O(n.k)"));
            myDataBaseHelper.addInitialQuestion(new Question("What is the average cost of Binary Search?",
                    new String[]{"O(1)", "O(n^2)", "O(log(n))", "O(n/2)"}, "O(log(n))"));
            myDataBaseHelper.addInitialQuestion(new Question("What is the cost of worst case deletion of Hash Map?",
                    new String[]{"O(n+k)", "O(n)", "O(log(n))", "O(1)"}, "O(log(n))"));

            list = myDataBaseHelper.getAllQuestionsList();//get list from database again

        }
    }
}
