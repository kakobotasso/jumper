package br.com.ezeqlabs.jumper.elementos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import br.com.ezeqlabs.jumper.R;
import br.com.ezeqlabs.jumper.engine.Cores;
import br.com.ezeqlabs.jumper.engine.Tela;

public class Cano {
    private static final int TAMANHO_DO_CANO = 250;
    private static final int LARGURA_DO_CANO = 100;
    private static final Paint VERDE = Cores.getCorDoCano();
    private int alturaDoCanoInferior;
    private Tela tela;
    private int posicao;
    private int alturaDoCanoSuperior;
    private Bitmap canoInferior;
    private Bitmap canoSuperior;

    public Cano(Context context, Tela tela, int posicao){
        this.tela = tela;
        this.posicao = posicao;
        this.alturaDoCanoInferior = tela.getAltura() - TAMANHO_DO_CANO - valorAleatorio();
        this.alturaDoCanoSuperior = 0 + TAMANHO_DO_CANO + valorAleatorio();

        Bitmap bp = BitmapFactory.decodeResource(context.getResources(), R.drawable.cano);
        this.canoInferior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, this.alturaDoCanoInferior, false);
        this.canoSuperior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, this.alturaDoCanoSuperior, false);
    }

    public void desenhaNo(Canvas canvas){
        desenhaCanoSuperiorNo(canvas);
        desenhaCanoInferiorNo(canvas);
    }

    private void desenhaCanoSuperiorNo(Canvas canvas){
        canvas.drawBitmap(this.canoSuperior, this.posicao, 0, null);
    }

    private void desenhaCanoInferiorNo(Canvas canvas){
        canvas.drawBitmap(this.canoInferior, this.posicao, this.alturaDoCanoInferior, null);
    }

    public void move(){
        this.posicao -= 5;
    }

    private int valorAleatorio(){
        return (int) (Math.random() * 150);
    }

    public boolean saiuDaTela(){
        return this.posicao + LARGURA_DO_CANO < 0;
    }

    public int getPosicao(){
        return  this.posicao;
    }

    public boolean cruzouVerticalmenteCom(Passaro passaro){
        return passaro.getAltura() - Passaro.RAIO < this.alturaDoCanoSuperior ||
                passaro.getAltura() + Passaro.RAIO > this.alturaDoCanoInferior;
    }

    public boolean cruzouHorizontalmenteComPassaro(){
        return this.posicao - Passaro.X < Passaro.RAIO;
    }
}