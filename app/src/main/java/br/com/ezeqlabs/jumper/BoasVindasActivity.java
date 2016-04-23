package br.com.ezeqlabs.jumper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import br.com.ezeqlabs.jumper.elementos.Pontuacao;

public class BoasVindasActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boas_vindas);

        TextView texto = (TextView) findViewById(R.id.menu_principal_texto);
        Pontuacao pontuacao = new Pontuacao(null, getSharedPreferences(Pontuacao.JUMPER_PREF, 0));

        texto.setText("Recorde atual \n " + pontuacao.getPontuacaoMaxima() + " canos");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Button jogar = (Button) findViewById(R.id.menu_principal_jogar);
        jogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(BoasVindasActivity.this, MainActivity.class);
                startActivity(main);
                finish();
            }
        });
    }
}
