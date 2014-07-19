package com.skoryk.gymhelper.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import com.skoryk.gymhelper.R;
import com.skoryk.gymhelper.adapter.ExpandableListAdapter;
import com.skoryk.gymhelper.dao.ProgramExerciseDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.widget.ExpandableListView.*;
public class ExpandableActivity extends Activity {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

//        prepareListData();
//
//        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
//
//        expListView.setAdapter(listAdapter);

//        expListView.setOnGroupClickListener(new OnGroupClickListener() {
//
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v,
//                                        int groupPosition, long id) {
//                System.out.println("333");
////                Toast.makeText(getApplicationContext(),
////                "Group Clicked " + listDataHeader.get(groupPosition),
////                Toast.LENGTH_SHORT).show();
////                return false;
//                return false;
//            }
//        });
//
//        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
////                Toast.makeText(getApplicationContext(),
////                        listDataHeader.get(groupPosition) + " Expanded",
////                        Toast.LENGTH_SHORT).show();
//                System.out.println("333");
//            }
//        });
//
//        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
//
//            @Override
//            public void onGroupCollapse(int groupPosition) {
////                Toast.makeText(getApplicationContext(),
////                        listDataHeader.get(groupPosition) + " Collapsed",
////                        Toast.LENGTH_SHORT).show();
//                System.out.println("333");
//            }
//        });
//
//        expListView.setOnChildClickListener(new OnChildClickListener() {
//
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v,
//                                        int groupPosition, int childPosition, long id) {
//                // TODO Auto-generated method stub
////                Toast.makeText(
////                        getApplicationContext(),
////                        listDataHeader.get(groupPosition)
////                                + " : "
////                                + listDataChild.get(
////                                listDataHeader.get(groupPosition)).get(
////                                childPosition), Toast.LENGTH_SHORT)
////                        .show();
////                return false;
//                System.out.println("333");
//                return false;
//            }
//        });
    }

//    private void prepareListData() {
//        ProgramExerciseDao programExerciseDao = new ProgramExerciseDao(this);
//        listDataChild = programExerciseDao.getProgramExercisesByTrainingId(1);
//        listDataHeader = new ArrayList<String>(listDataChild.keySet());
//    }

}