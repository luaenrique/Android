package www.projetotaurus.com.br.wolfoxagenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


import static www.projetotaurus.com.br.wolfoxagenda.R.id.editNome;

public class Indexpage extends AppCompatActivity implements View.OnClickListener{


    EditText enome;
    EditText esobrenome;
    EditText eemail;
    EditText etelefone;
    EditText erelacao;

    Button bcadastra;
    Button blistar;

    public static final String KEY_USERNAME="email";
    public static final String KEY_LASTNAME="sobrenome";
    public static final String KEY_EMAIL="email";
    public static final String KEY_PHONE="telefone";
    public static final String KEY_RELATION="relacionamento";

    public static final String insertContato = "http://luaenrique.com.br/jsonagendainsere.php";

    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private String relacionamento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indexpage);
        enome = (EditText)findViewById(R.id.editNome);
        esobrenome = (EditText)findViewById(R.id.editSobrenome);
        eemail     = (EditText)findViewById(R.id.editEmail);
        etelefone  = (EditText)findViewById(R.id.editTelefone);
        erelacao   = (EditText)findViewById(R.id.editRelacionamento);

        bcadastra     = (Button)findViewById(R.id.buttonCad);
        blistar       = (Button)findViewById(R.id.botaolist);

        blistar.setOnClickListener(this);
        bcadastra.setOnClickListener(this);
    }

    private void cadastraContato(){
        nome = enome.getText().toString().trim();
        sobrenome = esobrenome.getText().toString().trim();
        email = eemail.getText().toString().trim();
        telefone = etelefone.getText().toString().trim();
        relacionamento = erelacao.getText().toString().trim();
        StringRequest strreq = new StringRequest(Request.Method.POST, insertContato, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("sucesso")) {
                    Toast.makeText(Indexpage.this, "Inserido", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Indexpage.this, "Dados incorretos!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Indexpage.this,"Sem conexão com a Internet",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put(KEY_USERNAME, nome);
                map.put(KEY_LASTNAME, sobrenome);
                map.put(KEY_EMAIL, email);
                map.put(KEY_PHONE, telefone);
                map.put(KEY_RELATION, relacionamento);
                return map; // 3 min de video https://www.youtube.com/watch?v=KU1osfgDOqU

            }

        };
        RequestQueue request = Volley.newRequestQueue(this);
        request.add(strreq);
    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonCad:
                cadastraContato();
                break;
            case R.id.botaolist:
                Intent listarint = new Intent(this, listcontacts.class);
                startActivity(listarint);
                break;
        }

    }
}
