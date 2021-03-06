package br.com.ezeqlabs.jumper.elementos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import br.com.ezeqlabs.jumper.R;
import br.com.ezeqlabs.jumper.engine.Som;
import br.com.ezeqlabs.jumper.engine.Tela;
import br.com.ezeqlabs.jumper.engine.Tempo;

public class Passaro {
    public static final int X = 100;
    public static final int RAIO = 50;
    private final Tela tela;
    private final Bitmap passaro;
    private final Som som;
    private final Tempo tempo;
    public static final int DESLOCAMENTO_DO_PULO = 10;

    private int altura;

    public Passaro(Context context, Tela tela, Som som, Tempo tempo){
        this.altura = 300;
        this.tela = tela;
        this.som = som;
        this.tempo = tempo;
        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.passaro);
        this.passaro = Bitmap.createScaledBitmap(bp, RAIO*2, RAIO*2, false);
    }

    public void desenhaNo(Canvas canvas){
        canvas.drawBitmap(this.passaro, X - RAIO, this.altura - RAIO, null);
    }

    public void voa(){
        double tempo = this.tempo.atual();
        double novaAltura = -DESLOCAMENTO_DO_PULO + ((10 * (tempo * tempo)) / 2.0); // 10 = gravidade

        if(!chegouNoChao()) {
            this.altura += novaAltura;
        }
    }

    public boolean chegouNoChao(){
        return this.altura + RAIO > tela.getAltura();
    }

    public void pula() {
        if (this.altura > RAIO) {
            this.som.toca(Som.PULO);
            this.tempo.reinicia();
        }
    }

    public int getAltura(){
        return this.altura;
    }

    public int getTamanhoPassaro(){
        return 2 * RAIO;
    }
}
