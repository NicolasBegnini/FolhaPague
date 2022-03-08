package com.example.folhadepagamento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private EditText e_SB, e_ND;
    private Button btCalc;
    private RadioButton va_s, vr_s, vt_s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        e_SB = findViewById(R.id.edit_SB);
        e_ND = findViewById(R.id.edit_ND);
        btCalc = findViewById(R.id.btCalcular);
        vr_s = findViewById(R.id.simVR);
        va_s = findViewById(R.id.simVA);
        vr_s = findViewById(R.id.simVR);
        vt_s = findViewById(R.id.simVT);


        Spinner Plano_Saude = (Spinner) findViewById(R.id.plano_saude);
        spinner = findViewById(R.id.plano_saude);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lista_planos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Plano_Saude.setAdapter(adapter);


        btCalc.setOnClickListener(v -> {


            if (e_SB.getText().toString().isEmpty()) {
                e_SB.setError(getString(R.string.valida_salb));
                Toast.makeText(getBaseContext(), R.string.valida_salb, Toast.LENGTH_SHORT).show();
            } else if (e_ND.getText().toString().isEmpty()) {
                e_ND.setError(getString(R.string.valida_dep));
                Snackbar.make(btCalc, R.string.valida_dep, Snackbar.LENGTH_SHORT).show();
            } else {
                double sb = Double.parseDouble((e_SB.getText().toString()));
                double dependentes = Double.parseDouble(e_ND.getText().toString());
                double vr = 0, vt, va, inss = 0, irrf, planoS = 0;
                double salarioLiquido;
                String PS = spinner.getSelectedItem().toString();

                switch (PS) {
                    case "Standard":
                        if (sb <= 3000.00) {
                            planoS = 60.00;
                        } else {
                            planoS = 80.00;
                        }

                        break;


                    case "Básico":
                        if (sb <= 3000.00) {
                            planoS = 80.00;
                        } else {
                            planoS = 110.00;
                        }

                        break;


                    case "Super":
                        if (sb <= 3000.00) {
                            planoS = 95.00;
                        } else {
                            planoS = 135.00;
                        }

                        break;


                    case "Master":
                        if (sb <= 3000.00) {
                            planoS = 130.00;
                        } else {
                            planoS = 180.00;
                        }

                        break;


                    default:

                        break;

                }


                if (vt_s.isChecked()) {
                    vt = (0.06 * sb);

                } else {
                    vt = 0;

                }
                if (vr_s.isChecked()) {
                    if (sb <= 3000) {
                        vr = 22 * 2.60;

                    } else if (sb <= 5000) {
                        vr = 22 * 3.65;

                    } else if (sb >= 5001) {
                        vr = 22 * 6.50;

                    }
                } else {
                    vr = 0;

                }
                if (va_s.isChecked()) {
                    if (sb <= 3000) {
                        va = 15.00;

                    } else if (sb <= 5000) {
                        va = 25.00;

                    } else {
                        va = 35.00;

                    }
                } else {
                    va = 0;

                }


                if (sb <= 1212) {
                    inss = 0.075 * sb;

                } else if (sb <= 2427.35) {
                    inss = 0.09 * sb - 18.18;

                } else if (sb <= 3641.03) {
                    inss = 0.12 * sb - 91.00;

                } else if (sb <= 7087.22) {
                    inss = 0.14 * sb - 163.82;

                } else {
                    inss = 0828.39;


                }

                irrf = sb - inss - (189.59 * dependentes);
                if (irrf <= 1903.99) {
                    irrf = 0;

                } else if (irrf <= 2826.65) {
                    irrf = irrf * 0.075 - 142.80;

                } else if
                (irrf <= 3751.05) {
                    irrf = irrf * 0.15 - 354.80;

                } else if (irrf <= 4664.68) {
                    irrf = irrf * 0.225 - 636.13;


                } else {
                    irrf = irrf * 0.275 - 869.36;


                }

                salarioLiquido = sb - inss - vt - va - vr - irrf - planoS;


                Intent intent = new Intent(this, ResultadoActivity.class);
                //intent = intenção de abrir algo, exemplo abrir a câmera
                //precisa de uma activity base, nesse caso é a que estamos, ent usasse this
                //dps da virgula é qual activity vc quer abrir, no caso a Resultado activity

                intent.putExtra("salarioLiquido", String.format("R$ %.2f", salarioLiquido));
                intent.putExtra("inss", String.format("R$ %.2f", inss));
                intent.putExtra("salarioBruto", String.format("R$ %.2f", sb));
                intent.putExtra("irrf", String.format("R$ %.2f", irrf));
                intent.putExtra("planoSaude", String.format("R$ %.2f", planoS));
                intent.putExtra("vale_r", String.format("R$ %.2f", vr));
                intent.putExtra("vale_a", String.format("R$ %.2f", va));
                intent.putExtra("vale_t", String.format("R$ %.2f", vt));
                startActivity(intent);
            }
        });

    }
}


















