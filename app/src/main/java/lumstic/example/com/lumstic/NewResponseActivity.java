package lumstic.example.com.lumstic;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lumstic.example.com.lumstic.Models.Questions;
import lumstic.example.com.lumstic.Utils.IntentConstants;

public class NewResponseActivity extends Activity {

    TextView questionNumber;
    TextView questionText;


       RelativeLayout fieldContainer;
       LayoutInflater inflater;
//
//    TextView answerText;
//    RelativeLayout imageContainer;
//    ImageView imageView;
//    Uri picUri;
//    int CAMERA_REQUEST = 1;
//    final int PIC_CROP = 2;
//    ArrayList<Integer> layouts;
//    Spinner spinner;


    List<Questions> questionsList;
    List<String> questions;
    List<Integer> layouts;


   // int layouts[] = {R.layout.answer_single_line, R.layout.answer_multi_line, R.layout.answer_date_picker, R.layout.answer_image_picker, R.layout.answer_numeric, R.layout.answer_rating, R.layout.answer_multi_choice, R.layout.answer_dropdown,R.layout.answer_dropdown};
   // String questions[] = {"What is your name", "Describe yourself", "Your Date of Birth", "Upload your picture", "Whats is your age", "Rate this app", "Choose multiple options", "Choose any one"};
    int questionCounter = 0;
    Button nextQuestion, previousQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_response);
        getActionBar().setTitle("New Response");

        questionNumber=(TextView)findViewById(R.id.question_number_text);
        questionText=(TextView)findViewById(R.id.question_text);
        layouts=new ArrayList<Integer>();

        questionsList = new ArrayList<Questions>();
        questionsList = (List<Questions>) getIntent().getExtras().getSerializable(IntentConstants.QUESTIONS);
        questions= new ArrayList<String>();
        for(int Counter=0;Counter<questionsList.size();Counter++){
            questions.add(questionsList.get(Counter).getContent());
            String questionType=questionsList.get(Counter).getType();
           checkQuestionType(questionType,Counter);

        }

        fieldContainer = (RelativeLayout) findViewById(R.id.field_container);
        nextQuestion = (Button) findViewById(R.id.next_queation);
        previousQuestion = (Button) findViewById(R.id.previous_question);

        questionText.setText(questions.get(0));
        fieldContainer.removeAllViews();
        inflater = getLayoutInflater();
       fieldContainer.addView(inflater.inflate(layouts.get(0), null));







        nextQuestion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                questionCounter++;
                fieldContainer.removeAllViews();
                fieldContainer.addView(inflater.inflate(layouts.get(questionCounter),null));
              questionText.setText(questions.get(questionCounter));

            }
        });
        previousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionCounter--;
                fieldContainer.removeAllViews();
               fieldContainer.addView(inflater.inflate(layouts.get(questionCounter), null));
                questionText.setText(questions.get(questionCounter));

            }
        });
    }

//    public void CheckQuestion(int layoutId) {
//        //for date questions
//        if (layoutId == R.layout.answer_date_picker) {
//            answerText = (TextView) findViewById(R.id.answer_text);
//            answerText.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Calendar c = Calendar.getInstance();
//                    int mYear = c.get(Calendar.YEAR);
//                    int mMonth = c.get(Calendar.MONTH);
//                    int mDay = c.get(Calendar.DAY_OF_MONTH);
//                    System.out.println("the selected " + mDay);
//                    DatePickerDialog dialog = new DatePickerDialog(NewResponseActivity.this,
//                            new mDateSetListener(), mYear, mMonth, mDay);
//                    dialog.show();
//                }
//            });
//        }
//        //for image questions
//        if (layoutId == R.layout.answer_image_picker) {
//            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.answer_text);
//            RelativeLayout deleteImageRelativeLayout = (RelativeLayout) findViewById(R.id.image_container);
//            imageView = (ImageView) findViewById(R.id.image);
//            imageContainer = (RelativeLayout) findViewById(R.id.image_container);
//            linearLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
//                }
//            });
//
//            deleteImageRelativeLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    imageContainer.setVisibility(View.GONE);
//                }
//            });
//
//        }
//
//        if(layoutId==R.layout.answer_dropdown){
//            spinner =(Spinner)findViewById(R.id.drop_down);
//            List<String> list = new ArrayList<String>();
//            list.add("item 1");
//            list.add("item 2");
//            list.add("item 3");
//            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
//                    android.R.layout.simple_spinner_dropdown_item, list);
//            spinner.setAdapter(dataAdapter);
//        }
//
//
//    }
//
//    //for camera returned picture
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (resultCode == RESULT_OK) {
//            if (requestCode == CAMERA_REQUEST) {
//                Bundle extras = data.getExtras();
//                Bitmap thePic = extras.getParcelable("data");
//                imageView.setImageBitmap(thePic);
//
////                picUri = data.getData();
////                performCrop(picUri);
//            }
//
//        }
////
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.new_response, menu);
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//
//    }
//
//    class mDateSetListener implements DatePickerDialog.OnDateSetListener {
//
//        public void onDateSet(DatePicker view, int year, int monthOfYear,
//                              int dayOfMonth) {
//            int mYear = year;
//            int mMonth = monthOfYear;
//            int mDay = dayOfMonth;
//            answerText.setText(new StringBuilder().append(mMonth + 1).append("/").append(mDay).append("/").append(mYear).append(" ").toString());
//        }
//    }
    public void checkQuestionType(String questionType, int position)
    {
        if (questionType.contains("PhotoQuestion"))
            layouts.add(position,R.layout.answer_image_picker);

        if (questionType.contains("MultilineQuestion"))
            layouts.add(position,R.layout.answer_multi_line);

        if (questionType.contains("NumericQuestion"))
            layouts.add(position,R.layout.answer_numeric);

        if (questionType.contains("DateQuestion"))
            layouts.add(position,R.layout.answer_date_picker);

        if (questionType.contains("RatingQuestion"))
            layouts.add(position,R.layout.answer_rating);

        if (questionType.contains("RadioQuestion"))
            layouts.add(position,R.layout.answer_radio_button);

        if (questionType.contains("MultiChoiceQuestion"))
            layouts.add(position,R.layout.answer_multi_choice);

        if (questionType.contains("SingleLineQuestion"))
            layouts.add(position,R.layout.answer_single_line);

        if(questionType.contains("DropDownQuestion"))
            layouts.add(position,R.layout.answer_dropdown);
    }

}

