package www.projetotaurus.com.br.wolfoxagenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class listcontacts extends AppCompatActivity implements View.OnClickListener{

    Button voltacad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listcontacts);

        voltacad = (Button)findViewById(R.id.irpcad);
        voltacad.setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.irpcad:
                Intent voltacadd = new Intent(this, Indexpage.class);
                startActivity(voltacadd);
                break;
        }
    }
}
