package txt.com.webview_loading;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TestBackActivity extends AppCompatActivity {
    Button btnBack;
    TextView txtBack, txtContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_back);

        btnBack = (Button) findViewById(R.id.btnexit);
        String ubtnBack= getString(R.string.exit);
        SpannableString contentubtnBack = new SpannableString(ubtnBack);
        contentubtnBack.setSpan(new UnderlineSpan(), 0, ubtnBack.length(), 0);
        btnBack.setText(contentubtnBack);

        txtBack = (TextView) findViewById(R.id.txtback);
        String utxtBack= getString(R.string.back);
        SpannableString contentutxtBack = new SpannableString(utxtBack);
        contentutxtBack.setSpan(new UnderlineSpan(), 0, utxtBack.length(), 0);
        txtBack.setText(contentutxtBack);

        txtContinue = (TextView) findViewById(R.id.txtcontinue);
        String utxtContinue= getString(R.string.gocontinue);
        SpannableString contentutxtContinue = new SpannableString(utxtContinue);
        contentutxtContinue.setSpan(new UnderlineSpan(), 0, utxtContinue.length(), 0);
        txtContinue.setText(contentutxtContinue);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent testBackMain = new Intent(TestBackActivity.this,MainActivity.class);
//                startActivity(testBackMain);
                confirmexit();

            }
        });

        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent textBackMain = new Intent(TestBackActivity.this, MainActivity.class);
                startActivity(textBackMain);
                TestBackActivity.this.finish();
            }
        });
        txtContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent textContinuePagenew = new Intent(TestBackActivity.this, MainActivity.class);
                startActivity(textContinuePagenew);
            }
        });

    }
    public void exitphanmemvietshop(){
//        finishAndRemoveTask();
        //cách 1
        this.finish();
        System.exit(0);
        //cách 2
        int p = android.os.Process.myPid();
        android.os.Process.killProcess(p);
    }

    public void confirmexit(){

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setTitle("Bạn chưa xem nhiều sản phẩm mà đã thoát rồi ;)?")
                    .setMessage("Chọn No để tiếp tục sử dụng Việt Shop nào <3")
                    .setCancelable(false)
                    .setPositiveButton("Vẫn Thoát", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            exitphanmemvietshop();
                        }
                    })
                    .setNegativeButton("Tiếp Tục", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
    }

}
