package lumstic.example.com.lumstic.UI;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lumstic.example.com.lumstic.Adapters.CompleteResponsesAdapter;
import lumstic.example.com.lumstic.Adapters.DBAdapter;
import lumstic.example.com.lumstic.Models.CompleteResponses;
import lumstic.example.com.lumstic.Models.Questions;
import lumstic.example.com.lumstic.Models.Surveys;
import lumstic.example.com.lumstic.R;
import lumstic.example.com.lumstic.Utils.IntentConstants;

public class CompleteResponsesActivity extends Activity {
    ListView listView;
    DBAdapter dbAdapter;
    Surveys surveys;
    int completeResponseCount=0;
    List<CompleteResponses> completeResponseses;
    Questions identifierQuestion;
    int identifierQuestionId=0;
    ActionBar actionBar;
    List<Integer> completeResponsesId;

    List<String> identifierQuestionAnswers;


    CompleteResponsesAdapter completeResponsesAdapter;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_responses);
        actionBar= getActionBar();
        actionBar.setTitle("Completed Responses");

        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_ic_back);
        actionBar.setDisplayShowTitleEnabled(true);
        dbAdapter= new DBAdapter(CompleteResponsesActivity.this);
        completeResponseses = new ArrayList<CompleteResponses>();
        completeResponsesId= new ArrayList<Integer>();
        identifierQuestionAnswers= new ArrayList<String>();


        surveys = new Surveys();
        surveys = (Surveys) getIntent().getExtras().getSerializable(IntentConstants.SURVEY);
        completeResponseCount=dbAdapter.getCompleteResponse(surveys.getId());
        completeResponsesId=dbAdapter.getCompleteResponsesIds(surveys.getId());

        for(int j=0;j<surveys.getQuestions().size();j++){
            if(surveys.getQuestions().get(j).getIdentifier()==1){
                identifierQuestion=surveys.getQuestions().get(j);
                identifierQuestionId=surveys.getQuestions().get(j).getId();
            }
        }

        for(int i=0;i<completeResponseCount;i++){

            identifierQuestionAnswers.add(dbAdapter.getAnswer(completeResponsesId.get(i), identifierQuestionId));

            completeResponseses.add(i,new CompleteResponses(String.valueOf(completeResponsesId.get(i)),identifierQuestion.getContent()+" :"+"  "+identifierQuestionAnswers.get(i)));
        }



        listView = (ListView) findViewById(R.id.listview);

        completeResponsesAdapter = new CompleteResponsesAdapter(getApplicationContext(), completeResponseses,surveys);
        listView.setAdapter(completeResponsesAdapter);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.complete_responses, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
