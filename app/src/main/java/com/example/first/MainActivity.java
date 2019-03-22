package com.example.first;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private EditText test_Address;
    private EditText code_test_Address;
    static String result_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test_Address = (EditText) findViewById(R.id.Address_test);
        code_test_Address = (EditText) findViewById(R.id.address_test_number);
    }

    public void Clickbutton(View v)
    {
        Toast.makeText(getApplicationContext(), "Hello World!", Toast.LENGTH_LONG).show();
    }

    public void ClickAddress(View v)
    {
        Intent intent = new Intent(this, testAddress.class);
        startActivityForResult(intent,3000);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            switch (requestCode){
                // MainActivity 에서 요청할 때 보낸 요청 코드 (3000)
                case 3000:
                    resultAddress Addr = (resultAddress)data.getSerializableExtra("class");
                    code_test_Address.setText(Addr.getZonecode());
                    test_Address.setText(Addr.getAddress());
                    break;
            }
        }
    }
}
