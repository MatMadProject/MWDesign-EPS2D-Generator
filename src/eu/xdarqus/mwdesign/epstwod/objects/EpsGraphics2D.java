package eu.xdarqus.mwdesign.epstwod.objects;

import java.io.IOException;
import java.io.OutputStream;

public class EpsGraphics2D {

    private EpsDocument document;
    private float lastX, lastY;

    public EpsGraphics2D(String title, OutputStream outputStream) throws IOException {
        this.document = new EpsDocument(title, outputStream);
        this.lastX = 0;
        this.lastY = 0;
    }

    public void scale(float x, float y) {
        append(x, y, "scale");
    }

    public void moveTo(float x, float y) {
        this.lastX = x;
        this.lastY = y;
        append(x, y, "moveto");
    }

    public void drawLine(float x, float y) {
        this.lastX = x;
        this.lastY = y;
        append(x, y, "lineto");
    }

    public void stroke() {
        append("stroke");
    }

    private void append(float x, float y, String action) {
        append(x + " " + y + " " + action);
    }

    private void append(String line) {
        this.document.append(line);
    }

    public void flush() throws IOException {
        this.document.flush();
    }

    public void close() throws IOException {
        this.document.close();
    }
}
