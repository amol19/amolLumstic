package lumstic.example.com.lumstic.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import lumstic.example.com.lumstic.R;

public class ForgotPasswordActivity extends Activity {

    Button requestPasswordButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getActionBar().setTitle("Forgot Password");
        requestPasswordButton= (Button) findViewById(R.id.request_password);
        requestPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}