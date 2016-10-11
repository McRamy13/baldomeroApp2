package es.iessaladillo.ramonguardia.baldomeroformulario;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox chkCasado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVistas();
    }

    private void initVistas() {
        //Llamamos al checkbox
        CheckBox chkCasado = (CheckBox)findViewById(R.id.chkCasado);
        //Llamamos a las vistas de casado
        final View lblcasado = findViewById(R.id.lblNombre);
        final View txtCasado = findViewById(R.id.txtNombre);
        //Ponemos el check a false para asegurarnos que el usuario lo marque.
        chkCasado.setChecked(false);
        //Llamamos al botón y le pasamos la función validarEmail.
        Button btnAceptar = (Button) findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarEmail();
            }
        });

        //Si el usuario presiona el checkbox, se van a mostrar el campo casado para poner el nombre
        chkCasado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Si está chequeado
                if(isChecked){
                    //haremos que se visualicen los campos que hemos desactivado.
                    lblcasado.setVisibility(View.VISIBLE);
                    //hacemos lo mismo con el campo de texto
                    txtCasado.setVisibility(View.VISIBLE);
                }else{

                }
            }
        });
    }

    //Hacemos la función para validar el email
    private void validarEmail(){
        final EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
        //Si el ususario no introduce nada en el email

        if (txtEmail != null){
            txtEmail.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    //Si no está vacío
                    if (!TextUtils.isEmpty(txtEmail.getText().toString())){
                        if (!Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText().toString()).matches()) {
                            txtEmail.setError("El formato es el siguiente: email@dominio.com");
                        } else {

                        }
                    }
                    //Si está vacío, en ese caso no habilitamos el botón
                }
            });
        }


    }
}
