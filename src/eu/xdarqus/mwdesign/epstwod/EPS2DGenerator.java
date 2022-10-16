package eu.xdarqus.mwdesign.epstwod;

import eu.xdarqus.mwdesign.epstwod.objects.EpsGraphics2D;

import java.io.FileOutputStream;
import java.io.IOException;

public class EPS2DGenerator {

    private FileOutputStream fileOutputStream;
    EpsGraphics2D epsGraphics2D;
    private float startX = 0f;
    private float startY = 0f;

    public EPS2DGenerator(FileOutputStream fileOutputStream) {
        this.fileOutputStream = fileOutputStream;
        try {
            this.epsGraphics2D = new EpsGraphics2D("MWDesign EPS2D Generator", this.fileOutputStream);
            this.epsGraphics2D.scale(2.834646f, 2.834646f);
        } catch (IOException e) {}

    }

    public void close() {
        try {
            this.epsGraphics2D.close();
        } catch (IOException e) {}
    }

    public void generateCode(float a, float b, float c, float d, float e, float f, float g, float h, float i, float j, float k, float l, float addC, float addD, boolean przetnij) {
        if(przetnij) {
            this.epsGraphics2D.moveTo(this.startX + 0f, this.startY + k);
            this.epsGraphics2D.drawLine(this.startX + 0f, this.startY + k + a); //A
            this.epsGraphics2D.drawLine(this.startX + e, this.startY + k + a); //E
            this.epsGraphics2D.drawLine(this.startX + e, this.startY + k + a + f); //F
            this.epsGraphics2D.drawLine(this.startX + e + (c/2), this.startY + j + b + g); // POLOWA C
            this.epsGraphics2D.drawLine(this.startX + e + (c/2), this.startY); // PRZECIECIE W POL
            this.epsGraphics2D.drawLine(this.startX + l, this.startY); // POLOWA D
            this.epsGraphics2D.drawLine(this.startX + l, this.startY + k); //K
            this.epsGraphics2D.drawLine(this.startX, this.startY + k); //L
            this.epsGraphics2D.stroke();

            this.epsGraphics2D.moveTo(this.startX + e + (c/2), this.startY);
            this.epsGraphics2D.drawLine(this.startX + e + (c/2), this.startY + j + b + g); // PRZECIECIE
            this.epsGraphics2D.drawLine(this.startX + e + c + addC, this.startY + j + b + g); // 2 POLOWA C
            this.epsGraphics2D.drawLine(this.startX + e + c, this.startY + j + b); //G
            this.epsGraphics2D.drawLine(this.startX + e + c + h, this.startY + j + b); //H
            this.epsGraphics2D.drawLine(this.startX + l + d + i, this.startY + j); //B
            this.epsGraphics2D.drawLine(this.startX + l + d, this.startY + j); //I
            this.epsGraphics2D.drawLine(this.startX + l + d + addD, this.startY); //J
            this.epsGraphics2D.drawLine(this.startX + e + (d/2), this.startY); // 2 POLOWA D
            this.epsGraphics2D.stroke();
        }else {
            this.epsGraphics2D.moveTo(this.startX + 0f, this.startY + k);
            this.epsGraphics2D.drawLine(this.startX + 0f, this.startY + k + a);
            this.epsGraphics2D.drawLine(this.startX + e, this.startY + k + a);
            this.epsGraphics2D.drawLine(this.startX + e, this.startY + k + a + f);
            this.epsGraphics2D.drawLine(this.startX + e + c + addC, this.startY + j + b + g);
            this.epsGraphics2D.drawLine(this.startX + e + c, this.startY + j + b);
            this.epsGraphics2D.drawLine(this.startX + e + c + h, this.startY + j + b);
            this.epsGraphics2D.drawLine(this.startX + l + d + i, this.startY + j);
            this.epsGraphics2D.drawLine(this.startX + l + d, this.startY + j);
            this.epsGraphics2D.drawLine(this.startX + l + d + addD, this.startY);
            this.epsGraphics2D.drawLine(this.startX + l, this.startY);
            this.epsGraphics2D.drawLine(this.startX + l, this.startY + k);
            this.epsGraphics2D.drawLine(this.startX, this.startY + k);
            this.epsGraphics2D.stroke();
        }
//        this.epsGraphics2D.moveTo(this.startX + 0f, this.startY + k);
//        this.epsGraphics2D.drawLine(this.startX + 0f, this.startY + k + a);
//        this.epsGraphics2D.drawLine(this.startX + e, this.startY + k + a);
//        this.epsGraphics2D.drawLine(this.startX + e, this.startY + k + a + f);
//        this.epsGraphics2D.drawLine(this.startX + e + c + addC, this.startY + j + b + g);
//        this.epsGraphics2D.drawLine(this.startX + e + c, this.startY + j + b);
//        this.epsGraphics2D.drawLine(this.startX + e + c + h, this.startY + j + b);
//        this.epsGraphics2D.drawLine(this.startX + l + d + i, this.startY + j);
//        this.epsGraphics2D.drawLine(this.startX + l + d, this.startY + j);
//        this.epsGraphics2D.drawLine(this.startX + l + d + addD, this.startY);
//        this.epsGraphics2D.drawLine(this.startX + l, this.startY);
//        this.epsGraphics2D.drawLine(this.startX + l, this.startY + k);
//        this.epsGraphics2D.drawLine(this.startX, this.startY + k);
//        this.epsGraphics2D.stroke();
        this.startY += k + a + f >= g + b + j ? k + a + f : g + b + j;
        this.startY += 10f;
    }
}