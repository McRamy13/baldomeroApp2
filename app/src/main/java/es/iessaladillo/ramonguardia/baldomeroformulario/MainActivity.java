package es.iessaladillo.ramonguardia.baldomeroformulario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckBox chkCasado;
    private Button btnAceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVistas();
    }

    private void initVistas() {
        //Llamamos a la funcion validarEmail
        validarEmail();
        //Llamamos al checkbox
        CheckBox chkCasado = (CheckBox)findViewById(R.id.chkCasado);
        //Llamamos a las vistas
        final View lblcasado = findViewById(R.id.lblNombre);
        final View txtCasado = findViewById(R.id.txtNombre);
        Switch switchJamon = (Switch) findViewById(R.id.switchJamon);
        EditText txtEdad = (EditText) findViewById(R.id.txtEdad);
        //Ponemos el check a false para asegurarnos que el usuario lo marque.
        chkCasado.setChecked(false);
        //Llamamos al botón
        btnAceptar = (Button) findViewById(R.id.btnAceptar);
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
                    //Si no, volverán a ser invisibles.
                    lblcasado.setVisibility(View.INVISIBLE);
                    //hacemos lo mismo con el campo de texto
                    txtCasado.setVisibility(View.INVISIBLE);
                }
            }
        });
        //Cuando pulsemos en aceptar en el botón enviará un mensaje
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
                //Si el usuario ha escrito un email (antes verificado por la función validarEmail)
                //Importante, no funciona.
                if(txtEmail != null){
                    //Si el email está puesto, enviamos datos
                    Toast.makeText(MainActivity.this, "Los datos se están enviando",
                            Toast.LENGTH_LONG).show();
                }else{
                    //Si no, enviamos un mensaje al usuario
                    Toast.makeText(MainActivity.this, "Por favor, introduzca un email antes de enviar los datos.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        //Hacemos lo mismo con el inputText de la Edad, para limitar sus caracteres.
        //Para ello creamos un array de la clase InputFilter para filtrar los caracteres a 3.
        //Se puede hacer también sobre el XML  con --> android:maxLength="3" (pero quería probar la forma dinámica).
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(3);
        txtEdad.setFilters(filters);

        //Por último, controlamos el switch
        switchJamon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //Si lo seleccionamos, enviamos un mensaje al usuario
                    Toast.makeText(MainActivity.this, "Va a enviar un jamón a Baldomero.",
                            Toast.LENGTH_LONG).show();
                }else{
                    //Si no, no enviamos nada.
                    Toast.makeText(MainActivity.this, "No quieres enviar un jamón a Baldomero",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    //Hacemos la función para validar el email
    private void validarEmail(){

        //Si el ususario no introduce nada en el email
        final EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
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
                            //Activamos el botón
                            btnAceptar.setEnabled(true);
                        } else {
                            //Si está vacío, en ese caso no habilitamos el botón
                            btnAceptar.setEnabled(false);
                        }
                    }

                }
            });
        }
    }
}
