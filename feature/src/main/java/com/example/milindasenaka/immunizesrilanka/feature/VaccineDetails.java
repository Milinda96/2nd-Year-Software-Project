package com.example.milindasenaka.immunizesrilanka.feature;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.widget.TextView;

public class VaccineDetails extends AppCompatActivity
{
    Toolbar toolbar;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_details);

        toolbar = (Toolbar)findViewById(R.id.toolbar1);
        textView = (TextView)findViewById(R.id.textView);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            toolbar.setTitle(bundle.getString("VaccineName"));

            if (toolbar.getTitle().toString().equalsIgnoreCase("Hepatitis B(HepB)"))
            {
                textView.setText(R.string.Hepatitis_B);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    textView.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
                }
            }
            else if (toolbar.getTitle().toString().equalsIgnoreCase("Rotavirus(RV)"))
            {
                textView.setText(R.string.Rotavirus);
            }
            else if (toolbar.getTitle().toString().equalsIgnoreCase("Diphtheria,tetanus and acellular Pertussis(DTap)")){
                textView.setText(R.string.Diphtheria_tetanus_and_acellular_Pertussis);
            }
            else if (toolbar.getTitle().toString().equalsIgnoreCase("Haemophilus influenzae type b(Hib)")){
                textView.setText(R.string.Haemophilus_influenzae_type_b);
            }
            else if(toolbar.getTitle().toString().equalsIgnoreCase("Pneumcoccal Conjugate(PCV13)")){
                textView.setText(R.string.Pneumcoccal_Conjugate);
            }
            else if (toolbar.getTitle().toString().equalsIgnoreCase("Inactivated poliovirus(IPV)")){
                textView.setText(R.string.Inactivated_poliovirus);
            }
            else if (toolbar.getTitle().toString().equalsIgnoreCase("Influenza(IIV)")){
                textView.setText(R.string.Influenza);
            }
            else if (toolbar.getTitle().toString().equalsIgnoreCase("Measles,mumps,rubella(MMR)")){
                textView.setText(R.string.Measles_mumps_rubella);
            }
            else if (toolbar.getTitle().toString().equalsIgnoreCase("Varicella(VAR)")){
                textView.setText(R.string.Varicella);
            }
            else if (toolbar.getTitle().toString().equalsIgnoreCase("Hepatitis A(HepA)")){
                textView.setText(R.string.Hepatitis_A);
            }
            else if (toolbar.getTitle().toString().equalsIgnoreCase("Meningococcal")){
                textView.setText(R.string.Meningococcal);
            }
            else if (toolbar.getTitle().toString().equalsIgnoreCase("Tetanus,diphtheria,and acelluar pertussis(Tdap)")){
                textView.setText(R.string.Tetanus_diphtheria_and_acelluar_pertussis);
            }
            else if (toolbar.getTitle().toString().equalsIgnoreCase("Human papillomavirus(HPV)")){
                textView.setText(R.string.Human_papillomavirus);
            }

        }

    }
}
